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
    name: "offlineuser",
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
        
        gotoLibrarianView: function() {
            Router.push({
                path: "/librarian",
                name: "Librarian",
            });
        },
        
    },
};