import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function OnlineUserDto (firstName, lastName, address, isLocal, username, password, email) {
    this.firstName = firstName
    this.lastName = lastName
    this.address = address
    this.isLocal = isLocal
    this.username = username
    this.password = password
    this.email = email
}

export default {
    name: "signup",
    data(){
        return {
            firstName: '',
            lastName: '',
            address: '',
            isLocal: false,
            username: '',
            password: '',
            email: '',
            error: '',
            response: ''
        }
    },

    methods: {
        signUpUser: function (firstName, lastName, address, isLocal, username, password, email) {

        },

        gotoLogin: function() {
            Router.push({
                path: "/login",
                name: "Login",
            });
        },
    },
};
