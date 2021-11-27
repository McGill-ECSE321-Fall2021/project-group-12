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
            isLibrarian: false
        }
    },

    methods: {
        loginUser: function (username, password, isLibrarian) {
            console.log('username: ' + username)
            console.log('password: ' + password)
            AXIOS.post('onlineuser/login/?username='+username+'&password='+password)
            .then(response => {
                this.response = response.data;
                console.log(response);
                console.log(response.data)
                localStorage.setItem('username', response.data.username);
                localStorage.setItem('userId', response.data.userId);
                localStorage.setItem('email', response.data.email);
                localStorage.setItem('firstName', response.data.firstName);
                localStorage.setItem('lastName', response.data.lastName);
                if (isLibrarian){
                    localStorage.setItem('userType', 'librarian')
                    this.gotoLibrarianView();
                }else {
                    localStorage.setItem('userType', 'onlineuser')
                    this.gotoOnlineUserView();
                }
                localStorage.setItem('username', username);
                gottoOnlineUserView();
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        loginLibrarian: function (username, password) {
            console.log('username: ' + username)
            console.log('password: ' + password)
            AXIOS.post('librarian/login/?username='+username+'&password='+password)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('username', username);
                gotoLibrarianView();
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = 'Invalid username or password';
            })
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
    },
};
