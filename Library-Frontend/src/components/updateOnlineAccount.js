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
        console.log('is Local: ' + isLocal)
        console.log('username: ' + username)
        console.log('password: ' + password)
        console.log('email: ' + email)
        AXIOS.put('onlineuser/update?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username+'&password='+password+'&email='+email)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', username);
            this.gotoOnlineUserView();
        })
        .catch(e => {
            console.log('frontend url: ' + frontendUrl)
            console.log('\nbackend url:' + backendUrl)
            this.error = e;
        })
    },

    updatePassword: function(username,password,newPass){
        console.log('username: ' + username)
        console.log('password: ' + newPass)
        AXIOS.put('onlineuser/update/password?username='+username+'&password='+password+'&newPassword='+newPass)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', username);
            this.gotoOnlineUserView();
        })
        .catch(e => {
            console.log('frontend url: ' + frontendUrl)
            console.log('\nbackend url:' + backendUrl)
            this.error = e;
        })
    },

    updateUsername: function(username,password,newUser){
        console.log('username: ' + newUser)
        console.log('password: ' + password)
        AXIOS.put('onlineuser/update/username?username='+username+'&password='+password+'&newPassword='+newPass)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', newUser);
            this.gotoOnlineUserView();
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
}
};