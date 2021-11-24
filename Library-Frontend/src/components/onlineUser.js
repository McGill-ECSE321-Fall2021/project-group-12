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
            userId: localStorage.getItem('userId'),
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
                console.log(response)
                this.item_response = response.data;
                this.item_query = '';
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('\nbackend url:' + backendUrl);
                this.error = e;
            })
        },

        reserveItem: function(item) {
            if (item === null){
                this.error = "";
                return
            }
            console.log("item: "+item)
            console.log('reservation'+reservation)
            console.log('userId'+userId)
            if (localStorage.getItem('reservation') === null){
                AXIOS.post('/reservation/create/'+localStorage.getItem('userId'))
                .then(response => {
                    console.log(response)
                    localStorage.setItem('reservation', response.data.reservationId) // save reservation id
                })
            }
            var reservationId = localStorage.getItem('reservation')
            AXIOS.post('onlineuser/additem/userId/'+localStorage.getItem('userId')+'?reservationId='+localStorage.getItem('reservation')+'&itemId='+item.data.itemId)
            .then(response => {
                console.log(response)
            })
        },

        removeItem: function(item) {

        },


        logout: function() {
            localStorage.clear();
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
