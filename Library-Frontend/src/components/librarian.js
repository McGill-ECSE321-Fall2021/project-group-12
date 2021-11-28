import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

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
    name: "librarian",
    data(){
        return {
            username: localStorage.getItem('username'),
            item_query: '',
            item_response: [],
            reserved_response: [],
            error: '',
            response: '',
            librarian: '',
            librarians: []
        }
    },

    methods: {
        findLibrarian: function (librarianUsername) {
            AXIOS.get('/librarian/'+librarianUsername)
            .then(response => {
                this.response = response.data;
                this.librarian = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        signUpLibrarian: function (firstName, lastName, address, username, password, email) {
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('username: ' + username)
            console.log('password: ' + password)
            console.log('email: ' + email)
            AXIOS.post('/librarian/create/head?firstname='+firstName+'&lastname='+lastName+'&address='+address+'&email='+email+'&password='+password+'&username='+username)
            .then(response => {
                this.response = response.data;
                this.librarians.push(response.data);
		        this.librarian = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        findAllLibrarians: function () {
            AXIOS.get('/librarians/')
            .then(response => {
                this.response = response.data;
                this.librarians = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        removeLibrarian: function (delId) {
            AXIOS.delete('/librarians/delete/'+delId+'?librarianUsername='+localStorage.getItem('username'))
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        updateLibrarian: function (nwfirstName, nwlastName, nwaddress, nwusername, nwemail, nwIsHead) {
            AXIOS.put('/librarians/update/'+localStorage.getItem('username')+'?firstname='+nwfirstName+'&lastName='+nwlastName+
            '&address='+nwaddress+'&email='+nwemail+'&password='+librarian.password+'&username='+nwusername+'&isHead='+nwIsHead)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        changePassword: function (oldPassword, newPassword) {
            AXIOS.put('/librarians/change-password/?username='+localStorage.getItem('username')+'&oldPassword='+oldPassword+'&newPassword='+newPassword)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        
    },
};