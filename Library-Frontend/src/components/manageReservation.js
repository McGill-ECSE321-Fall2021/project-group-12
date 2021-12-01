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
			userReservations: [],
			reservations: [],
            error: '',
            response: ''
        }
    },
    methods: {
		gotoLibrarian: function() {
			if (localStorage.getItem('isHead'===true)) {
				Router.push({
					path: "/headlibrarian",
					name: "HeadLibrarian",
				});
			} else {
				Router.push({
					path: "/librarian",
					name: "Librarian",
				});
			}
		},
		searchReservationByUserId: function(userId) {
			if (this.userReservations.length > 0){
                this.userReservations = []
                return
            }
            AXIOS.get('/reservations/'+userId)
            .then(response => {
				console.log(response)
                this.userReservations = response.data;
            })
            .catch(e => {
				console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		},
		deleteReservation: function(reservationId) {
			AXIOS.delete('/reservation/delete/'+reservationId)
            .then(response => {
				console.log(response)
                this.reservations = response.data;
            })
            .catch(e => {
				console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		},
		getReservations: function() {
			if (this.reservations.length > 0){
                this.reservations = []
                return
            }
            AXIOS.get('/reservations')
            .then(response => {
				console.log(response)
                this.reservations = response.data;
            })
            .catch(e => {
				console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		}
	}
}