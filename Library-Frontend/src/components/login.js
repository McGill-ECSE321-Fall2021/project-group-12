import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function LoginDto (username, password) {
    this.username = username
    this.password = password
}

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

        },

        gotoSignUp: function() {
            Router.push({
                path: "/signup",
                name: "SignUp",
            });
        },
    },
};
