import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "onlineuser",
    data(){
        return {
            username: localStorage.getItem('username'),
            item_query: '',
            item_response: [],
            reserved_response: [],
            error: '',
            response: ''
        }
    },

    methods: {
        searchItem: function (query) {
            if (query === null || query === ""){
                this.error = "";
                return
            }
            console.log('item query: ' + query);
            AXIOS.get('items/getbytitle/'+query)
            .then(response => {
                this.item_response = response.data;
                this.item_query = '';
                console.log(item_response);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('\nbackend url:' + backendUrl);
                this.error = e;
            })
        },

        toggleReserved: function(item) {

        },

        logout: function() {
            localStorage.setItem('username', '');
            Router.push({
                path: "/login",
                name: "Login",
            });
        },

        getReservedItems: function() {
            var username = localStorage.getItem('username');
            if (username === null || username === ""){
                this.logout();
            }
            AXIOS.get('/onlineuser/reservations/username/'+localStorage.getItem('username'))
            .then(response => {
                this.reserved_response = response.data;
                console.log(this.reserved_response);
            })
        }
    },
};
