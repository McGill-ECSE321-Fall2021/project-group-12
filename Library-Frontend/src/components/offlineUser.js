import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function OfflineUserDto (firstName, lastName, address, isLocal) {
    this.firstName = firstName
    this.lastName = lastName
    this.address = address
    this.isLocal = isLocal
}

export default {
    name: "offlineuser",
    data(){
        return {
            username: localStorage.getItem('username'),
            item_query: '',
            item_response: [],
            reserved_response: [],
            error: '',
            response: '',
            createFirstName: '',
            updateFirstName: '',
            createLastName: '',
            updateLastName: '',
            lastName: '',
            createAddress: '',
            updateAddress: '',
            updateIsLocal: false,
            createIsLocal: false,
            updateId: '',
            deleteId: '',
            offlineId: ''

        }
    },

    methods: {
        
        signUpOffline: function (firstName, lastName, address, isLocal) {
            console.log('firstName: ' + firstName)
            console.log('lastName: ' + lastName)
            console.log('address: ' + address)
            console.log('isLocal: ' + isLocal)
            AXIOS.post('offlineuser/create?firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('firstName', firstName);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteOffline: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/offlineuser/delete?id='+id)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        updateOffline: function (id, firstName, lastName, address, isLocal) {
            console.log('id: ' + id)
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('isLocal: ' + isLocal)
            AXIOS.put('offlineuser/update?id='+id+'&firstName='+firstName+'&lastName='+lastName+'&address='+address+'&isLocal='+isLocal)
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

        gotoLibrarianView: function() {
            Router.push({
                path: "/librarian",
                name: "Librarian",
            });
        },

        gotoOfflineFunctionView: function() {
            Router.push({
                path: "/offlinefunction",
                name: "OfflineFunction",
            });
        },
        
    },
};