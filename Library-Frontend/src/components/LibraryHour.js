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
            libraryHours: [],
            error: '',
            response: ''
        }
    },
    
    methods: {
		searchLibraryHour: function (librarianId) {
			this.error = "";
            console.log('librarianId: ' + librarianId);
            AXIOS.get('/libraryHours/'+ librarianId)
            .then(response => {
                console.log(response)
                this.libraryHours = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('backend url:' + backendUrl);
                this.error = e;
            })
		}
	}
}

