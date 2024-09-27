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
import PhotoGalleryCard from '../components/PhotoGalleryCard.vue';

export default {
    name:'BoardPhotoGallery',
    
  components: {
    PhotoGalleryCard
  },
  data() {
    return {
      boardMembers: [
        { id: 1, name: 'Abdul Kader', position: 'Board of Director', imageUrl: 'https://abrahamparker.com/kader.png' },
        { id: 2, name: 'Abraham Parker', position: 'Executive Director', imageUrl: 'https://via.placeholder.com/150' },
        { id: 3, name: 'Asif Mizan', position: 'Treasurer', imageUrl: 'https://via.placeholder.com/150' },
        { id: 4, name: 'Sharmin Borona', position: 'Admin/HR', imageUrl: 'https://via.placeholder.com/150' },
        { id: 5, name: 'Kazi Haque', position: 'Development Director', imageUrl: 'https://via.placeholder.com/150' },
        { id: 6, name: 'Abrayar', position: 'Volunteer Director', imageUrl: 'https://via.placeholder.com/150' },
        { id: 7, name: 'Jannat Mawa', position: 'Member', imageUrl: 'https://via.placeholder.com/150' },
        { id: 8, name: 'Wahidur Rahman', position: 'Member', imageUrl: 'https://via.placeholder.com/150' },
        { id: 8, name: 'Somrat ', position: 'Member', imageUrl: 'https://via.placeholder.com/150' }
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
