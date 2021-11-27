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
            error: '',
            response: ''
        }
    },

    methods: {
        loginUser: function (username, password) {
            console.log('username: ' + username)
            console.log('password: ' + password)
            AXIOS.post('onlineuser/login/?username='+username+'&password='+password)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('username', username);
                gottoOnlineUserView();
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        gotoSignUp: function() {
            Router.push({
                path: "/signup",
                name: "SignUp",
            });
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
