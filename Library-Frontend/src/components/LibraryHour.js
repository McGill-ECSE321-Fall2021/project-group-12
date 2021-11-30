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
	name: "libraryhour",
	data(){
        return {
			username: '',
            libraryHours: [],
            searchedLibrarianId: '',
            error: '',
            response: ''
        }
    },
    
    mounted() {
			this.getLibraryHour();
    },
    
    methods: {
		searchLibraryHour: function (librarianId) {
			this.error = "";
            console.log('librarianId: ' + librarianId)
            AXIOS.get('/libraryHours/'+ librarianId)
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
		searchLibraryHourByUsername: function (username) {
			this.error = "";
            console.log('username: ' + username)
            AXIOS.get('/librarian/'+ username)
            .then(response => {
                console.log(response)
                this.searchedLibrarianId = response.data.userId;
                console.log(this.searchedLibrarianId)
                AXIOS.get('/libraryHours/'+ this.searchedLibrarianId)
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
		getLibraryHour: function () {
			this.error = "";
            console.log('librarianId: ' + localStorage.getItem("userId"))
            AXIOS.get('/libraryHours/'+ localStorage.getItem("userId"))
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
		gotoLibrarian: function() {
			Router.push({
				path: "/librarian",
				name: "Librarian",
			});
		}
	}
}

