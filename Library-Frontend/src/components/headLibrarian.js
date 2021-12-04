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
    name: "librarian",
    data(){
        return {
            username: localStorage.getItem('username'),
            error: '',
            response: '',
            librarian: '',
            foundLibrarian: '',
            librarianUsername: '',
            librarians: [],
            firstName: '',
            lastName: '',
            address: '',
            email: '',
            libusername: '',
            password: '',
            id: '',
            isLocal: false,
            isHead: false,
            nwfirstName: '',
            nwlastName: '',
            nwaddress: '',
            nwemail: '',
            nwusername: '',
            newPassword: '',
            oldPassword: '',
            delId: '',
            nwisLocal: false,
            nwisHead: false,
            event: '',
            events: [],
            eventsUser: [],
            eventName: '',
            eventStartTime:'',
            eventEndTime: '',
            eventStartDate: '',
            eventEndDate: '',
            eventIsPrivate: false,
            eventIsAccepted: false,
            eventUserFirstname: '',
            eventUserLastname: '',
            eventUserId: '',
            isRejected: false,
            isAccepted: false,
            eventId: ''
        }
    },

    methods: {
        findLibrarian: function (librarianUsername) {
            AXIOS.get('/librarian/'+librarianUsername)
            .then(response => {
                this.response = response.data;
                this.foundLibrarian = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        signUpLibrarian: function (firstName, lastName, address, username, password, email) {
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('username: ' + username)
            console.log('password: ' + password)
            console.log('email: ' + email)
            AXIOS.post('/librarian/create/?librarianUsername='+this.username+'&firstname='+firstName+'&lastname='+lastName+'&address='+address+'&email='+email+'&password='+password+'&username='+username)
            .then(response => {
                this.response = response.data;
                this.librarians.push(response.data);
		        this.librarian = response.data;
                this.lastName = '';
                this.address = '';
                this.firstName = '';
                this.email = '';
                this.libusername = '';
                this.password = '';
                this.id = 0;
                this.isLocal = false;
                this.isHead = false;
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
                this.librarians = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        removeLibrarian: function (delId) {
            AXIOS.delete('/librarians/delete/'+delId+'?librarianUsername='+localStorage.getItem('username'))
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
            AXIOS.put('/librarians/update/'+localStorage.getItem('username')+'?firstname='+nwfirstName+'&lastName='+nwlastName+
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
            AXIOS.put('/librarians/change-password/?username='+localStorage.getItem('username')+'&oldPassword='+oldPassword+'&newPassword='+newPassword)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        findAllEvents: function () {
            AXIOS.get('/librarian/events')
            .then(response => {
                this.response = response.data;
                this.events = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        findEventsByUser: function (eventUserId) {
            AXIOS.get('/librarian/events/'+eventUserId)
            .then(response => {
                this.response = response.data;
                this.eventsUser = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        findEvent: function (eventId) {
            AXIOS.get('/event/'+eventId)
            .then(response => {
                this.response = response.data;
                this.event = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        eventRequest: function (isAccepted, eventId) {
            console.log(isAccepted);
            console.log(eventId);
            
            if (isAccepted === "Accepted") {
                AXIOS.put('/librarian/event/accept/'+eventId)
                .then(response => {
                    this.response = response.data;
                    this.event = response.data;
                })
                .catch(e => {
                    console.log('frontend url: ' + frontendUrl)
                    console.log('\nbackend url:' + backendUrl)
                    this.error = e;
                })
            }
            if (isAccepted === "Rejected") {
                AXIOS.put('/librarian/event/reject/'+eventId)
                .then(response => {
                    this.response = response.data;
                    this.event = response.data;
                })
                .catch(e => {
                    console.log('frontend url: ' + frontendUrl)
                    console.log('\nbackend url:' + backendUrl)
                    this.error = e;
                })
            }
            
        },
        gotoReservationManagement: function() {
			Router.push({
                path: "/librarian/managereservation",
      			name: "ManageReservation",
            });
		},
        gotoModifyLibraryHour: function() {
			Router.push({
                path: "//headlibrarian/modifylibraryhour",
                name: "ModifyLibraryHour",
            });
		},

        logout: function() {
            localStorage.clear();
            Router.push({
              path: "/login",
              name: "Login"
            });
        },
    },
};