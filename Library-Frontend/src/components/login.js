import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "login",
    data(){
        return {
            username: '',
            password: '',
            userId: null,
            error: '',
            response: '',
            isALibrarian: false,
            isHeadLibrarian: false
        }
    },

    methods: {
        loginUser: function (username, password) {
            console.log('username: ' + username)
            console.log('password: ' + password)
            AXIOS.get('librarian/islibrarian/'+username)
            .then(response => {
                this.isALibrarian = response.data;
                if (this.isALibrarian){
                    AXIOS.get('librarian/isheadlibrarian/'+username)
                    .then(response => {
                        this.isHeadLibrarian = response.data;
                        if (this.isHeadLibrarian){
                            AXIOS.post('onlineuser/login/?username='+username+'&password='+password)
                            .then(response => {
                                localStorage.setItem('userType', 'headlibrarian')
                                localStorage.setItem('username', username);
                                this.gotoHeadLibrarianView();
                            })
                            .catch(e => {
                                this.error = e;
                            })
                        }else {
                            AXIOS.post('onlineuser/login/?username='+username+'&password='+password)
                            .then(response => {
                                localStorage.setItem('userType', 'librarian')
                                localStorage.setItem('username', username);
                                this.gotoLibrarianView();
                            })
                            .catch(e => {
                                this.error = e;
                            })
                        }
                    })
                } else {
                    AXIOS.post('onlineuser/login/?username='+username+'&password='+password)
                    .then(response => {
                        localStorage.setItem('userType', 'onlineuser')
                        localStorage.setItem('username', username);
                        this.gotoOnlineUserView();
                    })
                    .catch(e => {
                        this.error = e;
                    })
                }
            })
        },

        loginLibrarian: function (username, password) {
            console.log('username: ' + username)
            console.log('password: ' + password)
            AXIOS.post('librarian/login/?username='+username+'&password='+password)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('username', username);
                if (response.data.isHead == true) {
                    this.gotoHeadLibrarianView();
                }
                this.gotoLibrarianView();
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = 'Invalid username or password';
            })
        },

        gotoHeadLibrarianView: function() {
            Router.push({
                path: "/headlibrarian",
                name: "HeadLibrarian",
            });
        },

        gotoOnlineUserView: function() {
            Router.push({
                path: "/onlineuser",
                name: "OnlineUser",
            });
        },

        gotoLibrarianView: function() {
            Router.push({
                path: "/librarian",
                name: "Librarian",
            });
        },
        
        gotoSignUp: function() {
            Router.push({
                path: "/signup",
                name: "SignUp",
            });
        },

        togglePasswordVisibility: function() {
            const icon = document.getElementById('hidden-icon')
            const field = document.getElementById('password-field')
            if (icon.classList.contains('bi-eye')){
                icon.classList.remove('bi-eye')
                icon.classList.add('bi-eye-slash')
                field.setAttribute('type', 'text') 
            } else {
                icon.classList.remove('bi-eye-slash')
                icon.classList.add('bi-eye')
                field.setAttribute('type', 'password')
            }
        },
        gotoLibrarianView: function() {
            Router.push({
                path: "/librarian",
                name: "Librarian",
            });
        },

        gottoOnlineUserView: function() {
            Router.push({
                path: "/onlineuser",
                name: "OnlineUser",
            });
        },

        breakEgg: function() {
            const egg = document.getElementById('egg')
            egg.classList.remove('bi-egg')
            egg.classList.add('bi-egg-fried')
            localStorage.setItem("egg", true)
        },

        checkEgg: function(){
            var status = localStorage.getItem('egg')
            console.log(status)
            if (status !== null){
                this.breakEgg();
            }
        }
    },
    mounted() {
        this.checkEgg();
    }
};
