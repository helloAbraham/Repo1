<template>
<div class="qr-code-view">
  <div class="qr-container">
    <h2>Scan or Click to Visit</h2>
    <h2>You can generate a QR code for any website URL or Email</h2>

    <!-- URL input -->
    
    <BaseInput
      v-model="websiteUrl"
      label="Enter Website URL or Email "
      style="width: 350px; padding: 8px;"
      placeholder="Enter website URL"
    />

    <!-- Clickable QR -->
    <a :href="websiteUrl" target="_blank" title="Click to open website">
      <canvas ref="qrCanvas" class="qr-canvas"></canvas>
    </a>

    <!-- Download button -->
    <BaseButtonStyle style="padding: 5px 10px; cursor: pointer;" @click="downloadQr">Download QR Code</BaseButtonStyle>
  </div>
  </div>
</template>

<script>
import QRCode from 'qrcode'
import BaseButtonStyle from '@/components/BaseButtonStyle.vue'
import BaseInput from '@/components/BaseInput.vue';

export default {
  name: 'QrCode',
  data() {
    return {
      websiteUrl: 'https://www.probasalapon.com'
    }
  },
  components: {
    BaseButtonStyle,
    BaseInput,
  },
  mounted() {
    this.generateQrCode()
  },
  watch: {
    websiteUrl() {
      this.generateQrCode()
    }
  },
  methods: {
    generateQrCode() {
      QRCode.toCanvas(this.$refs.qrCanvas, this.websiteUrl, {
        width: 220,
        margin: 2
      })
    },
    downloadQr() {
      const canvas = this.$refs.qrCanvas
      const image = canvas.toDataURL('image/png')

      // Get URL without protocol
      let fileName = this.websiteUrl.split('//')[1] || this.websiteUrl

      // Remove 'www.', dots, and '-com'
      fileName = fileName.replace(/^www\./, '')
                        .replace(/\./g, '-')
                        .replace(/-com/g, '')

      // Replace slashes with dashes
      fileName = fileName.replace(/\//g, '-')

      // Add suffix
      fileName += '.png'
      // fileName += '_QR_SCAN.png'

      // Trigger download
      const link = document.createElement('a')
      link.href = image
      link.download = fileName
      link.click()
    }
  }
}
</script>

<style scoped>
.qr-code-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  
}
.qr-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-family: Arial, sans-serif;
  margin-top: 100px;
}

h2 {
  margin-bottom: 2em;
}

.long-input {
 width: 100%;
  max-width: 500px;
  padding: 8px 10px;
  font-size: 14px;
}

.qr-canvas {
  cursor: pointer;
  margin: 10px 0;
}

button {
  margin-top: 10px;
  padding: 2px 14px;
  font-size: 14px;
  cursor: pointer;
}
</style>
