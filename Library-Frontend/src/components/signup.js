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

function LibrarianDto (firstName, lastName, address, isLocal, username, password, email, isHead, id) {
    this.firstName = firstName
    this.lastName = lastName
    this.address = address
    this.isLocal = isLocal
    this.username = username
    this.password = password
    this.email = email
    this.isHead = isHead
    this.id = id
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
            response: '',
            librarians: [],
            created_librarian: false
        }
    },

    methods: {
        gotoOnlineUserView: function() {
            Router.push({
                path: "/onlineuser",
                name: "OnlineUser",
            });
        },

        signUpUser: function (firstName, lastName, address, isLocal, username, password, email) {
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('isLocal: ' + isLocal)
            console.log('username: ' + username)
            console.log('password: ' + password)
            console.log('email: ' + email)
            AXIOS.get('librarians')
            .then(response => {
                this.librarians = response.data
                console.log(this.librarians);
                if (this.librarians == null || this.librarians.length == 0){
                    console.log('null or empty')
                    AXIOS.post('librarian/create/head?firstname='+firstName+'&lastname='+lastName+'&address='+address+'&email='+email+'&password='+password+'&username='+username)       
                    console.log('created librarian')
                    this.created_librarian = true;
                    this.gotoHeadLibrarianView();
                }else {
                    console.log('not null or empty')
                    AXIOS.post('onlineuser/create?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username+'&password='+password+'&email='+email)
                    .then(response => {
                        this.response = response.data;
                        localStorage.setItem('username', username);
                        console.log('created onlineuser')
                        this.gotoOnlineUserView();
                    })
                    .catch(e => {
                        console.log('frontend url: ' + frontendUrl)
                        console.log('\nbackend url:' + backendUrl)
                        this.error = e; 
                    })
                }
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

        gotoHeadLibrarianView: function() {
            Router.push({
                path: "/headlibrarian",
                name: "HeadLibrarian",
            });
        },

        gotoLogin: function() {
            Router.push({
                path: "/login",
                name: "Login",
            });
        },

        gotoLibrarianView: function() {
            Router.push({
                path: "/librarian",
                name: "Librarian",
            });
        },
    },
};
