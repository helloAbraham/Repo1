
<template>
  <div class="business-card-page">
    <div class="business-card">
      <!-- Brand header -->
      <div class="card-header">
        <div class="brand-mark">m<span>2</span>M</div>
        <div class="brand-name">
          <strong>m2M TV USA</strong>
          <small>NEWS · MEDIA · COMMUNITY</small>
        </div>
      </div>

      <!-- Profile -->
      <div class="profile">
        <!-- <div class="profile-icon">AP</div> -->
        <!-- <h1>Abraham Parker</h1>
        <p class="job-title">CEO, m2M TV</p> -->
        <!-- <div class="gold-line"></div>
        <p class="tagline">
          Connecting communities through news and media.
        </p> -->
      </div>

      <!-- QR code -->
      <div class="qr-section">
       
        <div class="qr-frame">
          <img
            v-if="qrCode"
            :src="qrCode"
            alt="Scan QR code to visit m2musa.tv"
            class="qr-image"
          />
          <div v-else class="qr-placeholder">
            Generating QR code...
          </div>
        </div>

        <!-- <h2>Scan to Visit Our Website</h2>
        <p class="qr-description">
          Open your phone camera and scan this QR code.
       
        <a
          class="website-link"
          :href="website"
          target="_blank"
          rel="noopener noreferrer"
        >
          <span>↗</span> m2musa.tv
        </a> -->
         <h1 class="name-heading">{{name}}</h1>
        <p class="job-title">{{designation}}</p>
      </div>

      <!-- Contact details -->
      <div class="contact-section">
        <div class="contact-row">
          <div class="contact-icon">⌖</div>
          <div class="contact-row">
  <div class="contact-icon">☎</div>
  <div>
    <small>BUSINESS PHONE</small>
    <p>
      <a class="phone-link" :href="'tel:' + phone">
        {{ phone.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3') }}
      </a>
    </p>
  </div>
</div>
          <div>
            <small>BUSINESS ADDRESS</small>
            <p>37-14 73rd St #204</p>
            <p>Jackson Heights, NY 11372</p>
          </div>
        </div>

        <!-- <div class="contact-row">
          <div class="contact-icon">◎</div>
          <div>
            <small>COMPANY</small>
            <p>m2M TV USA</p>
          </div>
        </div>-->
      </div> 

      <!-- Actions -->
      <div class="card-actions">
        <button
          class="primary-button"
          type="button"
          @click="saveContact"
        >
          <span>↓</span> Save Contact
        </button>

        <!-- <a
          class="secondary-button"
          :href="website"
          target="_blank"
          rel="noopener noreferrer"
        >
          Visit Website ↗
        </a> -->
      </div>

      <p class="footer-text">
        Thank you for connecting with m2M TV USA
      </p>
    </div>
  </div>
</template>

<script>
import QRCode from 'qrcode'

export default {
  name: 'BusinessCard_2',

  data() {
    return {
    website: 'https://m2musa.tv/#/bc2', // Replace with dynamcic value if needed instance https://m2musa.tv/#/BC1, https://m2musa.tv/#/BC2
    phone: '+8801929260456',
    qrCode: '',
    name: 'Maruf Hassan',
    designation: 'Journalist, m2M TV, Rangpur, Bangladesh',
    officeLocation: 'USA office - 37-14 73rd St #204, Jackson Heights, NY 11372',
    }
  },

  mounted() {
    this.generateQRCode()
  },

  methods: {
    async generateQRCode() {
      try {
        this.qrCode = await QRCode.toDataURL(this.website, {
          width: 320,
          margin: 2,
          errorCorrectionLevel: 'H',
          color: {
            dark: '#10223b',
            light: '#ffffff'
          }
        })
      } catch (error) {
        console.error('Could not generate QR code:', error)
      }
    },

    saveContact() {
    const vCard = [
      'BEGIN:VCARD',
      'VERSION:3.0',
      'N:Hassan;Maruf;;;',
      'FN:Maruf Hassan',
      'ORG:m2M TV USA',
      'TITLE:Journalist, Rangpur, Bangladesh',
      'TEL;TYPE=WORK,VOICE:+8801929260456',
      'ADR;TYPE=WORK:;;37-14 73rd St #204;Jackson Heights;NY;11372;USA',
      'URL:https://m2musa.tv/#/bc2',
      'END:VCARD'
    ].join('\r\n') + '\r\n'

  const blob = new Blob(
    [vCard],
    { type: 'text/vcard;charset=utf-8' }
  )

  const url = URL.createObjectURL(blob)
  const link = document.createElement('a')

  link.href = url
  link.download = 'Maruf-Hassan-m2M-TV.vcf'

  document.body.appendChild(link)
  link.click()
  link.remove()

  setTimeout(() => URL.revokeObjectURL(url), 1000)
}
  }
}
</script>

<style scoped>
.business-card-page {
  min-height: 100vh;
  padding: 135px 12px 14px;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  background: #eef1f6;
  font-family: Arial, Helvetica, sans-serif;
  color: #17243a;
  box-sizing: border-box;
}

.business-card {
  width: 100%;
  max-width: 430px;
  overflow: hidden;
  background: #fff;
  border-radius: 22px;
  border: 1px solid #dce2eb;
  box-shadow: 0 20px 55px rgba(16, 34, 59, 0.13);
  text-align: center;
  padding-top:-10px;
}

.card-header {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 5px 28px;
  background: #10223b;
  color: #fff;
  text-align: left;
}

.brand-mark {
  font-size: 30px;
  font-weight: 900;
  letter-spacing: -2px;
  color: #fff;
}

.brand-mark span {
  color: #e9bd64;
}

.brand-name {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.brand-name strong {
  font-size: 17px;
  letter-spacing: 0.4px;
}

.brand-name small {
  color: #d8e0ed;
  font-size: 9px;
  letter-spacing: 1.5px;
}

.profile {
  padding: -5px 24px 16px;
}

.profile-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 76px;
  height: 76px;
  margin: 0 auto 18px;
  border-radius: 50%;
  background: #10223b;
  border: 3px solid #e9bd64;
  color: #fff;
  font-size: 26px;
  font-weight: bold;
}

.eyebrow {
  margin: 0 0 9px;
  color: #9b752b;
  font-size: 10px;
  font-weight: 700;
  letter-spacing: 2px;
}

.profile h1 {
  margin: 0;
  font-size: 29px;
  line-height: 1.2;
  color: #10223b;
}

.name-heading {
  background-color: rgb(184, 244, 231);
  color: #040403;
  font-weight: 900;
  text-shadow: 3px 5px 2px #f9f6f3;
}

.job-title {
  margin: 9px 0;
  color: #5e6c80;
  font-size: 15px;
}

.gold-line {
  width: 48px;
  height: 3px;
  margin: 18px auto;
  border-radius: 5px;
  background: #d6a84d;
}

.tagline {
  max-width: 290px;
  margin: 0 auto;
  color: #5e6c80;
  font-size: 13px;
  line-height: 1.6;
  padding: -10 10px;
}

.qr-section {
  padding: -10px 24px 24px;
}

.qr-frame {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 202px;
  height: 202px;
  padding: -15px;
  margin: 0 auto 17px;
  box-sizing: border-box;
  background: #fff;
  border: 1px solid #e1e6ee;
  border-radius: 14px;
}

.qr-image {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: contain;
  padding-top: -10px;
}

.qr-placeholder {
  color: #667085;
  font-size: 12px;
}

.qr-section h2 {
  margin: 0 0 8px;
  color: #10223b;
  font-size: 17px;
}

.qr-description {
  margin: 0 0 14px;
  color: #697586;
  font-size: 12px;
  line-height: 1.5;
}

.website-link {
  display: inline-block;
  color: #9b752b;
  font-size: 15px;
  font-weight: 700;
  text-decoration: none;
}

.website-link span {
  margin-right: 5px;
}

.contact-section {
  margin: 0 25px;
  padding: 19px 0;
  border-top: 1px solid #e5e9f0;
  border-bottom: 1px solid #e5e9f0;
  text-align: left;
}

.contact-row {
  display: flex;
  align-items: flex-start;
  gap: 13px;
}

.contact-row + .contact-row {
  margin-top: 17px;
}

.contact-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 35px;
  height: 35px;
  border-radius: 10px;
  background: #f0f3f8;
  color: #10223b;
  font-size: 20px;
}

.contact-row small {
  display: block;
  margin: 2px 0 6px;
  color: #788395;
  font-size: 9px;
  font-weight: 700;
  letter-spacing: 1.1px;
}

.contact-row p {
  margin: 0 0 4px;
  color: #26364c;
  font-size: 13px;
  line-height: 1.4;
}

.card-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: -5px 25px 12px;
}

.primary-button,
.secondary-button {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 9px;
  width: 100%;
  min-height: 48px;
  box-sizing: border-box;
  border-radius: 10px;
  font-size: 14px;
  font-weight: 700;
  cursor: pointer;
  text-decoration: none;
  transition: background 0.2s, transform 0.2s;
}

.primary-button {
  border: 1px solid #10223b;
  background: #10223b;
  color: #fff;
}

.primary-button:hover {
  background: #1d385b;
}

.secondary-button {
  border: 1px solid #d9e0e9;
  background: #fff;
  color: #10223b;
}

.secondary-button:hover {
  background: #f3f5f9;
}

.primary-button:active,
.secondary-button:active {
  transform: scale(0.99);
}

.footer-text {
  padding: 8px 15px 20px;
  margin: 0;
  color: #8993a2;
  font-size: 10px;
  line-height: 1.5;
}

@media (max-width: 480px) {
  .business-card-page {
    padding: 16px 10px;
  }

  .business-card {
    border-radius: 17px;
  }

  .profile h1 {
    font-size: 26px;
  }


/* Mobile: move business card below the fixed navigation bar */
@media (max-width: 768px) {
  .business-card-page {
    padding-top: 135px !important;
    box-sizing: border-box;
    align-items: flex-start;
  }
}

/* Desktop and larger screens */
@media (min-width: 769px) {
  .business-card-page {
    padding-top: 175px;
  }
}

}
</style>
