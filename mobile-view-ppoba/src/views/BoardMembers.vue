// BoardMembers.vue
<template>
  <div class="board-members">
    <MemberCard
      v-for="member in boardMembers"
      :key="member.id"
      :name="member.name"
      :position="member.position"
      :imageUrl="member.imageUrl"
    />
  </div>
</template>

<script>
// Import the Card component
import MemberCard from '../components/MemberCard.vue';

export default {
    name:'BoardMembers',
    
  components: {
    MemberCard
  },
  data() {
    return {
      boardMembers: [
        { id: 1, name: 'Abdul Kader', position: 'Board of Director', imageUrl: 'https://abrahamparker.com/kader.png' },
        { id: 2, name: 'Abraham Parker', position: 'Executive Director', imageUrl: 'https://abrahamparker.com/abraham.png' },
        { id: 3, name: 'Asif Mizan', position: 'Treasurer', imageUrl: 'https://abrahamparker.com/asif.png' },
        { id: 4, name: 'Sharmin Borona', position: 'Admin/HR', imageUrl: 'https://abrahamparker.com/borona.png' },
        { id: 5, name: 'Kazi Haque', position: 'Development Director', imageUrl: 'https://abrahamparker.com/' },
        { id: 6, name: 'Abrayar', position: 'Volunteer Director', imageUrl: 'https://abrahamparker.com/' },
        { id: 7, name: 'Jannat Mawa', position: 'Member', imageUrl: 'https://abrahamparker.com/' },
        { id: 8, name: 'Wahidur Rahman', position: 'Member', imageUrl: 'https://abrahamparker.com/' },
        { id: 9, name: 'Somrat ', position: 'Member', imageUrl: 'https://abrahamparker.com/' }
      ]
    };
  }
};
</script>

<style scoped>
.board-members {
  display: flex;
  flex-wrap: wrap;
  gap: 16px; /* Space between cards */
}

.board-members .card {
  flex: 1 1 calc(33.333% - 16px); /* Responsive card width */
  max-width: 300px; /* Max width of card */
}
</style>
