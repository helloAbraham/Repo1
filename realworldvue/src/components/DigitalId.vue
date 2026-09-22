<template>
    <div class="id-card">
        <div class="card-header">
            <h1>Digital ID Card</h1>
        </div>
        <div class="card-body">
            <div class="profile-image">
            <!-- <img :src="handlock.jpg" alt="Profile Picture" /> -->
            </div>
            <div class="card-details">
                <p><strong>Name:</strong>{{ name }}</p>
                <p><strong>Occupation:</strong>{{ occupation }}</p>
                <p><strong>Email:</strong>{{ email }}</p>
                <p><strong>Phone:</strong>{{ phone }}</p>
                <p><strong>Address:</strong>{{ address }}</p>
        </div>
        <div class="card-footer">
            <BaseButtonStyle buttonClass="btn-primary">Manage ID</BaseButtonStyle>
            <BaseButtonStyle buttonClass="btn-secondary">Logout</BaseButtonStyle>
        </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DigitalId",
        data() {
            return {
                profileImage: 'https://via.placeholder.com/150',
                name: 'Abraham Parker',
                occupation: 'Software Engineer at USPS',
                email: 'abraham.parker@hotmail.com',
                phone: '7184504150',
                address: 'Elmhurst, NYC, USA'
            };
        }
    }
</script>

<style scoped>
.id-card {
  width: 300px;
  padding: 20px;
  border-radius: 10px;
  background-color: #f7f7f7;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
}

.card-header {
  text-align: center;
}

.card-body {
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.profile-image img {
  border-radius: 50%;
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-bottom: 20px;
}

.card-details {
  width: 100%;
  text-align: left;
  font-size: 14px;
}

.card-footer {
  margin-top: 20px;
  text-align: center;
  font-size: 12px;
  color: #888;
}
</style>