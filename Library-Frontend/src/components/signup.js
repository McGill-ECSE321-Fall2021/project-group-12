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
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('isLocal: ' + isLocal)
            console.log('username: ' + username)
            console.log('password: ' + password)
            console.log('email: ' + email)
            AXIOS.post('onlineuser/create?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username+'&password='+password+'&email='+email)
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

        gotoLogin: function() {
            Router.push({
                path: "/login",
                name: "Login",
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
