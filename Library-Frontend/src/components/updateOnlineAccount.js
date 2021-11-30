import axios, { Axios } from 'axios';
import { use } from 'chai';
import { data } from 'jquery';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "updateOnlineUserAccount",
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
    updateOnlineUserAccount: function(firstName,lastName,address,isLocal,username,password,email){
        console.log('first name: ' + firstName)
        console.log('last name: ' + lastName)
        console.log('address: ' + address)
        console.log('isLocal: ' + isLocal)
        console.log('username: ' + username)
        console.log('password: ' + password)
        console.log('email: ' + email)
        AXIOS.post('onlineuser/update?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username+'&password='+password+'&email='+email)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', username);
        })
        .catch(e => {
            this.error = e;
        })
    },

    updatePassword: function(username,password,newPass){
        console.log('username: ' + username)
        console.log('password: ' + newPass)
        AXIOS.post('onlineuser/update/password?username='+username+'&password='+password+'&newPassword='+newPass)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', username);
        })
        .catch(e => {
            this.error = e;
        })
    },

    updateUsername: function(username,password,newUser){
        console.log('username: ' + newUser)
        console.log('password: ' + password)
        AXIOS.post('onlineuser/update/username?username='+username+'&password='+password+'&newPassword='+newPass)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', newUser);
        })
        .catch(e => {
            this.error = e;
        })
    },

    gotoOnlineUserView: function() {
        Router.push({
            path: "/onlineuser",
            name: "OnlineUser",
        });
    },
}
};