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
function EventDto () {

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
    
    created: function() {
        const lib = new LibrarianDto('Bob', 'Ross', '123 street', true, 'BobisRoss', 'absCdc!', 'bob@ross.com', true, 2)
        this.librarian = lib
        const lib2 = new LibrarianDto('Van', 'Gogh', '124 street', true, 'VanisGogh', 'absCdc!', 'van@gogh.com', true, 2)
        this.librarians = [lib, lib2]

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
            AXIOS.post('librarianh/create/head?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&email='+email+'&password='+password+'&username='+username)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('username', username);
                this.librarians.push(response.data);
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
                this.librarians.push(response);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        removeLibrarian: function (delId) {
            AXIOS.get('/librarians/delete/'+delId+'?librarianUsername='+localStorage.getItem('username'))
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
            AXIOS.get('/librarians/update/'+localStorage.getItem('username')+'?firstname='+nwfirstName+'&lastName='+nwlastName+
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
            AXIOS.get('/librarians/change-password/?username='+localStorage.getItem('username')+'&oldPassword='+oldPassword+'&newPassword='+newPassword)
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