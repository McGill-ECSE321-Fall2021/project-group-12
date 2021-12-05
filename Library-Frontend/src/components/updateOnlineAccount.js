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
                response: '',
                username1: '',
                password1: '',
                username2: '',
                password2: '',
                username3: '',
                password3: '',
                newPass: '',
                newUser: '',
                curUserId: ''
            }
},

methods: {
    updateOnlineUserAccount: function(firstName,lastName,address,isLocal,username1,password1,email){
        console.log('first name: ' + firstName)
        console.log('last name: ' + lastName)
        console.log('address: ' + address)
        console.log('is Local: ' + isLocal)
        console.log('username: ' + username1)
        console.log('password: ' + password1)
        console.log('email: ' + email)
        console.log('localusername: ' + localStorage.getItem("username"))
        AXIOS.get('/onlineuser/username/'+localStorage.getItem("username"))
        	.then(response => {
	            this.curUserId = response.data.userId;
	            console.log('curUserId: ' + this.curUserId)
	            AXIOS.put('onlineuser/update?userId='+this.curUserId+'&firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username1+'&password='+password1+'&email='+email)
		        .then(response => {
		            this.response = response.data;
		            localStorage.setItem('username', username1);
		            this.gotoOnlineUserView();
		        })
		        .catch(e => {
		            console.log('frontend url: ' + frontendUrl)
		            console.log('backend url:' + backendUrl)
		            this.error = e;
	        	})
	        })
	        .catch(e => {
	            console.log('frontend url: ' + frontendUrl)
	            console.log('backend url:' + backendUrl)
	            this.error = e;
        	})
        
    },

    updatePassword: function(username2,password2,newPass){
        console.log('username: ' + username2)
        console.log('password: ' + newPass)
        AXIOS.put('onlineuser/update/password?username='+username2+'&password='+password2+'&newPassword='+newPass)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', username2);
            this.gotoOnlineUserView();
        })
        .catch(e => {
            console.log('frontend url: ' + frontendUrl)
            console.log('backend url:' + backendUrl)
            this.error = e;
        })
    },

    updateUsername: function(username3,password3,newUser){
        console.log('username: ' + newUser)
        console.log('password: ' + password3)
        AXIOS.put('/onlineuser/update/username?username='+username3+'&password='+password3+'&newUsername='+newUser)
        .then(response => {
            this.response = response.data;
            localStorage.setItem('username', newUser);
            this.gotoOnlineUserView();
        })
        .catch(e => {
            console.log('frontend url: ' + frontendUrl)
            console.log('backend url:' + backendUrl)
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