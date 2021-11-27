import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

//would this just be imported?
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
            librarian: ''
        }
    },

    methods: {
        findLibrarian: function (librarianUsername) {
            AXIOS.get('/librarian/', librarianUsername)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        signUpLibrarian: function (firstName, lastName, address, isLocal, username, password, email, isHead) {
            AXIOS.post('librarian/create?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal+'&username='+username+'&password='+password+'&email='+email+'&isHead='+isHead)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('username', username);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        
    },
};