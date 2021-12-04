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
	name: "modifyLibraryHour",
	data() {
		return {
			libraryHours: [],
			error: '',
            response: ''
		}
	},
	methods: {
		searchLibraryHour: function (libId) {
			this.error = "";
            console.log('librarianId: ' + libId)
            AXIOS.get('/libraryHours/'+ libId)
            .then(response => {
                console.log(response)
                this.libraryHours = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		},
		deleteLibraryHour: function(libraryHourId, libId) {
			console.log('libraryHourId: ' + libraryHourId)
            console.log('LibrarianId: ' + libId)
            AXIOS.delete('libraryHour/delete/'+libraryHourId+'?librarianId='+libId)
            .then(response => {
                console.log(response)
                AXIOS.get('/libraryHours/'+ libId)
                .then(response => {
                	console.log(response)
                	this.libraryHours = response.data;
           		})
            	.catch(e => {
                	console.log('frontend url: ' + frontendUrl)
                	console.log('backend url:' + backendUrl)
                	this.error = e;
            	})
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		},
		createLibraryHour: function(startTime, endTime, day, libId) {
			console.log('startTime: ' + startTime)
            console.log('endTime: ' + endTime)
            console.log('day: ' + day)
            AXIOS.post('/libraryHour/create?day='+day+'&startTime='+startTime+'&endTime='+endTime)
            .then(response => {
                console.log(response)
                AXIOS.put('/libraryHours/assign?libraryHourId='+response.data.libraryHourId+'&librarianId='+libId)
                .then(response => {
                	console.log(response)
                	AXIOS.get('/libraryHours/'+ libId)
                	.then(response => {
                		console.log(response)
                		this.libraryHours = response.data;
           			})
            		.catch(e => {
                		console.log('frontend url: ' + frontendUrl)
                		console.log('backend url:' + backendUrl)
                		this.error = e;
            		})
            	})
            	.catch(e => {
                	console.log('frontend url: ' + frontendUrl)
                	console.log('backend url:' + backendUrl)
                	this.error = e;
            	})
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('backend url:' + backendUrl)
                this.error = e;
            })
		},
		gotoHeadLibrarian: function() {
			Router.push({
				path: "/headlibrarian",
				name: "HeadLibrarian",
			});
		}
	}
}