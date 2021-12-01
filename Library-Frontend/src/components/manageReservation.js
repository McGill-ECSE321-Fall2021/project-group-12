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
	name: "manageReservation",
	data(){
        return {
			reservations: [],
            error: '',
            response: ''
        }
    },
    methods: {
		gotoLibrarian: function() {
			Router.push({
				path: "/librarian",
				name: "Librarian",
			});
		},
		getReservations: function() {
			if (this.reservations.length > 0){
                this.reservations = []
                return
            }
            AXIOS.get('/reservations')
            .then(response => {
                this.reservations = response.data;
            })
            .catch(e => {
                this.error = e;
            })
		}
	
	
	
	}
	
	
	
}