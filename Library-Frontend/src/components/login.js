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
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
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
    },
};
