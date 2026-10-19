<template>
  <main class="contact-page">
    <header class="site-header">
      <a class="brand" href="/" aria-label="m2M TV USA home">
        <span class="brand-mark">m2M</span>
        <span class="brand-copy">
          <strong>m2M TV USA</strong>
          <small>Live Bangla Television</small>
        </span>
      </a>
    </header>

    <section class="hero" aria-labelledby="page-title">
      <p class="eyebrow">WE'RE HERE TO HELP</p>
      <h1 id="page-title">Contact m2M TV USA</h1>
      <p class="intro">
        Questions about the live stream, technical issues, or a concern about
        information shown in our breaking-news ticker? Please contact us.
      </p>
      <router-link class="email-button" :to="{ name: 'ContactUsNew' }">
        <span aria-hidden="true">✉</span>
        Email m2M TV USA
      </router-link>

      <p class="email-address">
        <span>Support email</span>
        <a href="mailto:admin@m2musa.tv">admin@m2musa.tv</a>
      </p>
    </section>

    <section class="info-grid" aria-label="About and support information">
      <article class="info-card">
        <h2>About the app</h2>
        <p>
          m2M TV USA is an Android TV application for watching a live Bangla
          television stream. The app also displays a channel logo, date and
          time, and a breaking-news ticker.
        </p>
      </article>

      <article class="info-card">
        <h2>Technical support</h2>
        <p>
          If the stream does not play, displays an error, or you have another
          app-related problem, email us with your device model and a short
          description of the issue. Please do not include passwords or other
          sensitive information.
        </p>
      </article>

      <article class="info-card">
        <h2>News and ticker corrections</h2>
        <p>
          If you believe information in the breaking-news ticker is inaccurate
          or out of date, email us with the displayed text and, if available,
          a link to a reliable original publisher or source. We can review
          information submitted to us.
        </p>
      </article>

      <article class="info-card">
        <h2>Content and sources</h2>
        <p>
          The app's primary function is live television streaming. News
          headlines displayed in the ticker should be checked against their
          original publisher or other reliable source before publication.
          m2M TV USA does not claim third-party reporting as its own.
        </p>
      </article>
    </section>

    <section class="contact-strip">
      <div>
        <h2>Need help?</h2>
        <p>Contact our support team by email.</p>
      </div>
      <a  href="https://mail.google.com/mail/?view=cm&fs=1&to=admin@m2musa.tv"
            target="_blank"
            class="BaseButtonStyle" >admin@m2musa.tv</a>
        <br>
          <a  href="https://mail.google.com/mail/?view=cm&fs=1&to=admin@m2musa.tv"
            target="_blank"
            class="BaseButtonStyle" >m2musatv@gmail.com</a>
            <br>
            <a
            href="tel:+17184504150"
            class="BaseButtonStyle"
            >
            718-450-4150
            </a>
    </section>

    <footer class="site-footer">
      <p>Copyright© {{ currentYear }} JFmedia.inc, NY. All rights reserved.</p>
      <nav aria-label="Footer navigation" >
        <router-link class="link" :to="{ name: 'Home' }">Home</router-link>
        <router-link class="link" :to="{ name: 'PrivacyPolicy' }">Privacy Policy</router-link>
        <router-link class="link" :to="{ name: 'ContactUsNew' }">Contact</router-link>
      </nav>
    </footer>
  </main>
</template>

<script>
import router from '@/router';
import BaseButtonStyle from '@/components/BaseButtonStyle.vue';
export default {
  name: "ContactPage",
  components: {
    router,
    BaseButtonStyle,
  },
  data() {
    return {
      currentYear: new Date().getFullYear(),
    };
  },
};
</script>

<style scoped>
:global(*) {
  box-sizing: border-box;
}

:global(body) {
  margin: 0;
  background: #f4f7fb;
  color: #172033;
  font-family: Inter, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
}

.contact-page {
  min-height: 100vh;
  padding: 0 24px 28px;
}

.site-header {
  max-width: 1080px;
  margin: 0 auto;
  padding: 24px 0;
}

.brand {
  display: inline-flex;
  align-items: center;
  gap: 12px;
  color: inherit;
  text-decoration: none;
}

.brand-mark {
  display: grid;
  place-items: center;
  width: 54px;
  height: 54px;
  border-radius: 14px;
  background: #c8202f;
  color: #fff;
  font-size: 20px;
  font-weight: 900;
  letter-spacing: -1px;
}

.brand-copy {
  display: grid;
  gap: 3px;
}

.brand-copy strong {
  font-size: 18px;
}

.brand-copy small {
  color: #667085;
  font-size: 13px;
}

.hero {
  max-width: 1080px;
  margin: 12px auto 24px;
  padding: clamp(30px, 6vw, 64px);
  border-radius: 24px;
  background: #101b35;
  color: #fff;
  box-shadow: 0 18px 50px rgb(16 27 53 / 14%);
}

.eyebrow {
  margin: 0 0 12px;
  color: #ffb7be;
  font-size: 12px;
  font-weight: 800;
  letter-spacing: 0.14em;
}

h1 {
  max-width: 760px;
  margin: 0;
  font-size: clamp(34px, 6vw, 58px);
  line-height: 1.08;
  letter-spacing: -0.04em;
}

.intro {
  max-width: 700px;
  margin: 20px 0 28px;
  color: #e1e7f2;
  font-size: 17px;
  line-height: 1.7;
}

.email-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  min-height: 48px;
  padding: 0 22px;
  border-radius: 10px;
  background: #e42b3b;
  color: #fff;
  font-weight: 750;
  text-decoration: none;
}

.email-button:hover {
  background: #c8202f;
}

.email-button:focus-visible {
  outline: 3px solid #528bff;
  outline-offset: 3px;
  border-radius: 4px;
}

.email-address {
  display: flex;
  flex-wrap: wrap;
  gap: 8px 14px;
  margin: 20px 0 0;
  color: #c7d0e0;
  font-size: 14px;
}

.email-address a {
  color: #fff;
  text-underline-offset: 3px;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));
  gap: 18px;
  max-width: 1080px;
  margin: 0 auto 24px;
}

.info-card {
  padding: 26px;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  background: #fff;
}

.info-card h2,
.contact-strip h2 {
  margin: 0 0 10px;
  font-size: 19px;
  
}

.info-card p,
.contact-strip p {
  margin: 0;
  color: #586174;
  font-size: 15px;
  line-height: 1.75;
}

.contact-strip {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 20px;
  max-width: 1080px;
  margin: 0 auto 32px;
  padding: 24px 26px;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  background: #fff;
}

.contact-strip a {
  color: #b7192a;
  font-weight: 750;
  overflow-wrap: anywhere;
  text-underline-offset: 3px;
}
.contact-strip a:hover {
  color: #e00ae4;
}

.site-footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  max-width: 1080px;
  margin: 0 auto;
  color: #667085;
  font-size: 13px;
}

.site-footer p {
  margin: 0;
}

.site-footer nav {
  display: flex;
  flex-wrap: wrap;
  gap: 18px;
}

.site-footer a {
  color: #4b5565;
  text-underline-offset: 3px;
}

a:focus-visible {
  outline: 3px solid #528bff;
  outline-offset: 3px;
  border-radius: 4px;
}


@media (max-width: 640px) {
  .contact-page {
    padding: 0 14px 22px;
  }

  .info-grid {
    grid-template-columns: 1fr;
  }

  .contact-strip,
  .site-footer {
    align-items: flex-start;
    flex-direction: column;
  }

  .hero {
    border-radius: 18px;
  }
}
</style>
