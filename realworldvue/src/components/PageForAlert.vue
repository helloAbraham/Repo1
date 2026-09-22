<template>

  <div class="alert-box">
    <MarqueeStyle />
    <img
      class="alert-icon"
      src="https://cdn-icons-png.flaticon.com/512/463/463612.png"
      alt="Alert Icon"
    />
    <span><strong>Alert!!!</strong> Information</span>
  </div>
  <p>
    A new phishing email is circulating that claims your message “wasn’t delivered” or 
    that there was a “failure to deliver email.” At first glance, it appears to come 
    from a legitimate mail service or system administrator, warning you that your recent 
    email could not be sent. However, this message is a scam designed to trick you into 
    clicking a malicious link or opening an infected attachment to “resend” or “verify” 
    your email. Once clicked, it can lead to fake login pages that steal your credentials 
    or download malware onto your device. It’s important to be cautious with any 
    unexpected “delivery-failure” notices — always check the sender’s address carefully, 
    avoid clicking links, and verify the issue directly through your official 
    email provider instead. Stay safe and secure online, don’t forget to know 
    where you are!
  </p>
</template>

<script>
import MarqueeStyle from '@/components/MarqueeStyle.vue';
export default {
  name: 'PageForAlert',
  components: {
    MarqueeStyle,
  },
};
</script>

<style scoped>
.alert-box {
  display: flex;
  align-items: center;
  background-color: #fff3cd;
  border: 1px solid #ffeeba;
  color: #856404;
  padding: 12px 16px;
  margin: 10px 0;
  border-radius: 8px;
  font-family: Arial, sans-serif;
  font-size: 16px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.alert-icon {
  width: 30px;
  height: 30px;
  margin-right: 12px;
}
.p {
  font-family: Arial, sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #333;
  margin-top: 20px;
  margin-left: 100px;
  margin-right: 100px;
}
</style>
