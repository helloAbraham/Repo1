<template>
  <div class="editable-container">
    <h2>Admin Editable Page</h2>
    <h2>Cybersecurity News will be published here:</h2>

    <!-- Headline -->
    <div v-if="content" class="editable-section">
    <BaseButtonStyle @click="editHeadline">✏️ Edit</BaseButtonStyle>

      <div v-if="!isEditingHeadline" class="text-display">
        <h3>{{ content.headline }}</h3>
        
      </div>

      <div v-else>
        <input v-model="tempHeadline" class="headline-input" />
        <div style="display:flex; justify-content:center; gap:10px; margin-top:10px;">
          <!-- Before using ConfirmModal, the method call was directly on the Save button saveHeadLine() --> 
           <!-- <BaseButtonStyle @click="saveHeadLine">💾 Save</BaseButtonStyle> -->
          <!-- When the user clicks the 💾 Save button, show the confirm modal -->
        <BaseButtonStyle @click="askConfirmHeadline">💾 Save</BaseButtonStyle>
        <BaseButtonStyle @click="cancelHeadlineEdit">❌ Cancel</BaseButtonStyle>
        </div>
        <ConfirmModal
        :visible="showConfirmHeadline"
        title="Confirm Edit"
        message="Are you sure you want to save this headline?"
        confirmText="Yes, Save"
        @confirm="saveHeadline"
        @cancel="cancelHeadlineEdit"
      />
      </div>
    </div>

    <!-- Paragraph 1 -->
    <div v-if="content" class="editable-section">
      <label>Paragraph 1:</label>
      <div v-if="!isEditing1" class="text-display">
        <p>{{ content.paragraph1 }}</p>
        <BaseButtonStyle @click="editParagraph(1)">✏️ Edit</BaseButtonStyle>
      </div>
      <div v-else>
        <textarea v-model="tempParagraph1" rows="4"></textarea>
        <div style="display:flex; justify-content:center; gap:10px; margin-top:10px;">
        <BaseButtonStyle @click="saveParagraph(1)">💾 Save</BaseButtonStyle>
        <BaseButtonStyle @click="cancelEdit(1)">❌ Cancel</BaseButtonStyle>
        </div>
      </div>
  
    </div>

    <!-- Paragraph 2 -->
    <div v-if="content" class="editable-section">
      <label>Paragraph 2:</label>
      <div v-if="!isEditing2" class="text-display">
        <p>{{ content.paragraph2 }}</p>
        <BaseButtonStyle @click="editParagraph(2)">✏️ Edit</BaseButtonStyle>
      </div>
      <div v-else>
        <textarea v-model="tempParagraph2" rows="4"></textarea>
        <div style="display:flex; justify-content:center; gap:10px; margin-top:10px;">
        <BaseButtonStyle @click="saveParagraph(2)">💾 Save</BaseButtonStyle>
        <BaseButtonStyle @click="cancelEdit(2)">❌ Cancel</BaseButtonStyle>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButtonStyle from "@/components/BaseButtonStyle.vue";
import ConfirmModal from "@/components/ConfirmModal.vue";
import axios from "axios";

export default {
  name: "EditableParagraphs",
  components: {
    BaseButtonStyle,
    ConfirmModal,
  },
  data() {
    return {
      content: null,
      isEditingHeadline: false,
      isEditing1: false,
      isEditing2: false,
      tempHeadline: "",
      tempParagraph1: "",
      tempParagraph2: "",
      showConfirmHeadline: false,
      showConfirmParagraph1: false,
      showConfirmParagraph2: false,
    };
  },
  mounted() {
    this.fetchContent();
  },
  methods: {
    async fetchContent() {
      try {
        //For localtest
        //const response = await axios.get("http://localhost:8081/api/content/1");
        //For production
        const response = await axios.get("https://api.abrahamparker.com/api/content/1");
        this.content = response.data;
      } catch (error) {
        console.error("Error fetching content:", error);
      }
    },

    // When the user clicks the 💾 Save button, show the confirm modal
  askConfirmHeadline() {
    this.showConfirmHeadline = true;
  },
  askConfirmParagraph1() {
    this.showConfirmParagraph1 = true;
  },
  askConfirmParagraph2() {
    this.showConfirmParagraph2 = true;
  },

    // Headline methods
    editHeadline() {
      this.tempHeadline = this.content.headline;
      this.isEditingHeadline = true;

    },
    async saveHeadline() {
      this.showConfirmHeadline = false;
      this.content.headline = this.tempHeadline;
      this.isEditingHeadline = false;
      await this.saveToServer();
      
    },
    cancelHeadlineEdit() {
      this.isEditingHeadline = false;
    },

    // Paragraph methods
    editParagraph(num) {
      if (num === 1) {
        this.tempParagraph1 = this.content.paragraph1;
        this.isEditing1 = true;
      } else {
        this.tempParagraph2 = this.content.paragraph2;
        this.isEditing2 = true;
      }
    },
    async saveParagraph(num) {
      if (num === 1) {
        this.content.paragraph1 = this.tempParagraph1;
        this.isEditing1 = false;
      } else {
        this.content.paragraph2 = this.tempParagraph2;
        this.isEditing2 = false;
      }
      await this.saveToServer();
    },
    cancelEdit(num) {
      if (num === 1) this.isEditing1 = false;
      else this.isEditing2 = false;
    },

    // Save all content to backend
    async saveToServer() {
      this.showConfirmHeadline = false;
      this.showConfirmParagraph1 = false;
      this.showConfirmParagraph2 = false;
      try {
        //For localtest 
        //await axios.post("http://localhost:8081/api/content/save", this.content, {  
        //For production
        await axios.post("https://api.abrahamparker.com/api/content/save", this.content, {
          headers: { "Content-Type": "application/json" }
        });
        console.log("Content saved successfully");
        
      } catch (error) {
        console.error("Error saving content:", error);
      }
    }
  }
};
</script>

<style scoped>
.editable-container {
  max-width: 750px;
  margin: 20px auto;
  font-family: Arial, sans-serif;
}

.editable-section {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 6px;
}

.text-display {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

textarea {
  width: 100%;
  resize: vertical;
  margin-bottom: 8px;
}

.headline-input {
  width: 100%;
  font-size: 1.2em;
  padding: 5px;
  margin-bottom: 8px;
}
</style>
