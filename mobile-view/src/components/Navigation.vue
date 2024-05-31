<template>
   <header :class="{'scrolled-nav' : scrolledNav }">
   <nav>
    
    <div class="branding">
        <!-- Below router-link to="/abc" way image can be taken different page -->
        <router-link  id="logo" to="/"><img src="@/assets/logo.png"  alt="logo" to="/">Masjid</router-link>
    </div>
    <ul v-show="!mobile" class="navigation">
        <li><router-link class="link" :to="{name: 'Home'}">Home</router-link></li>
        <li><router-link class="link" :to="{name: 'Dashboard'}">Dashboard</router-link></li>
        <li><router-link class="link" :to="{name: 'BackgroundImageAdd'}">Ramadan</router-link></li>
        <li><router-link class="link" :to="{name: ''}">Latest News</router-link></li>
        <li><router-link class="link" :to="{name: 'PrayerTimes'}">Prayer Time</router-link></li>
        <li><router-link class="link" :to="{name: 'About'}">About Us</router-link></li>
        
        <li v-if="!loggedIn" @click="login"><router-link class="link" :to="{name: 'LoginForm'}">Login</router-link></li>
        <li v-else="loogedIn" @click="login"><router-link class="link" :to="{name: 'LogoutComponent'}">Logout</router-link></li>
        
        <li><router-link class="link" :to="{name: ''}">Contact</router-link></li>        
    </ul>
    <div class="icon">
        <i @click="toggleMobileNav" v-show="mobile" class="far fa-bars" :class=" {'icon-active' : mobileNav }"></i>
    </div>
    <transition name="mobile-nav">
        <ul v-show="mobileNav" class="dropdown-nav">
        <li><router-link class="link" :to="{name: 'Home'}">Home</router-link>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<i @click="toggleMobileNav" v-show="mobile" class="fa fa-window-close" :class=" {'icon-active' : mobileNav }"></i></li>
        <li><router-link class="link" :to="{name: 'Dashboard'}">Dashboard</router-link></li>
        <li><router-link class="link" :to="{name: 'BackgroundImageAdd'}">Ramadan</router-link></li>
        <li><router-link class="link" :to="{name: ''}">Latest News</router-link></li>
        <li><router-link class="link" :to="{name: 'PrayerTimes'}">Prayer Time</router-link></li>
        <li><router-link class="link" :to="{name: 'About'}">About Us</router-link></li>
        <li><router-link class="link" :to="{name: 'LoginForm'}">Login</router-link></li>
        <li><router-link class="link" :to="{name: ''}">Contact</router-link></li>        
    </ul>
    </transition>
   </nav>
</header> 
</template>

<script>
import LoginService from '@/services/LoginService';
import axios from 'axios';
    export default {
        name: "navigation",
        props: {
            childMethod: {
                type: Function,
                required: true
            }
        },
        data(){
            return {
                scrolledNav: null,
                mobile: null,
                mobileNav: null,
                windowWidth: null,

                loggedIn: false,
                
            };
        },
        created(){
            window.addEventListener('resize', this.checkScreen);
            this.checkScreen();
            
        },
        mounted(){
                window.addEventListener("scroll", this.updateScroll);
            },
        methods: {
            toggleMobileNav(){
                this.mobileNav = !this.mobileNav;
            },
            updateScroll(){
                const scrollPosition = window.scrollY;
                if(scrollPosition >50){
                    this.scrolledNav = true;
                    return;
                }
                this.scrolledNav = false;
            },
            checkScreen(){
                this.windowWidth = window.innerWidth;
                if(this.windowWidth <= 750){
                    this.mobile = true;
                    return;
                }
                this.mobile = false;
                this.mobileNav = false;
            },
            executeParentMethod() {
                this.childMethod();
            },
            login() {
                this.loggedIn = true;
            },

            logout() {
                this.isLoggedIn = false;
                axios.post('http://localhost:8090/api/auth/logout')
                 .then(response => {
               // Redirect to the home page or perform any other action
               this.$router.push('/')
               
          })
          .catch(error => {
            console.error('Logout failed:', error);
          });
      }

        }
    }
</script>

<style lang="scss" scoped>
header{
    background-color: rgba(0,0,0,0.8);
    z-index: 99;
    width: 100%;
    position: fixed;
    transition: 0.3s ease all;
    color: #fff;

    nav {
        position: relative;
        display: flex;
        flex-direction: row;
        padding: 12px 0;
        transition: 0.3s ease all;
        width: 90%;
        margin: 0 auto;
        @media(min-width: 1140px){
            max-width: 1140px;
        }

        ul,
        .link{
            font-weight: 500;
            color: #fff;
            list-style: none;
            text-decoration: none;
        }
        li{
            text-transform: uppercase;
            padding: 16px;
            margin-left: 16px;

        }
        .link{
            font-size: 14px;
            transition: .5s ease all;
            padding-bottom: 4px;
            border-bottom: 1px solid transparent;

            &:hover{
                color: #d125ef;
                border-color: #00afea;
                font-weight: bold;
                font-weight: 700;
                
            }
        }
        .branding{
            display: flex;
            align-items: center;

            img{
                width: 100px;
                transition: .3s ease all;
            }
        }

        .navigation{
            display: flex;
            align-items: center;
            flex: 1;
            justify-content: flex-end;
        }
        .icon{
            display: flex;
            align-items: center;
            position: absolute;
            top: 0;
            right: 24px;
            height: 100%;
            i {
                cursor: pointer;
                font-size: 24px;
                transition: 0.8s ease all;
            }
        }
        .icon-active{
            transform: rotate(90deg);
            color: #00afea;
        }
        .dropdown-nav{
            display: flex;
            flex-direction: column;
            position: fixed;
            width: 100%;
            max-width: 250px;
            height: 100%;
            background-color: #f6e6d0;
            top: 75px;
            left: 0;
            li{
                margin-left: 0;
                .link{
                    color: #000;  
                    &:hover{
                    color: #d125ef;
                    border-color: #00afea;
                    font-weight: 700;
                    }
                }
              
            }
           

            

        .mobile-nav-enter-active,
        .mobile-nav-leave-active{
            transition: 2s ease all;
        }
        .mobile-nav-enter-from,
        .mobile-nav-leave-to {
            transform: translateX(-250px);
        }
            .mobile-nav-enter-to{
                transform: translateX(0);
            }
        }
    }
}
.scrolled-nav{
    background-color: #000;
    box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1), 0 2px 4px -1px rgba(0,0,0,0.06);

    nav{
        padding: 6px 0;
        .branding{
            img{
                width: 45px;
                box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1), 0 2px 4px -1px rgba(0,0,0, 0.06);

            }
        }
    }
}
</style>