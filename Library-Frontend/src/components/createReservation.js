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
    name: "createReservation",
    data(){
        return {
            username: localStorage.getItem('username'),
            userId: localStorage.getItem('userId'),
            all_albums: [],
            all_books: [],
            all_movies: [],
            all_newspapers: [],
            item_query: '',
            item_response: [],
            reserved_response: [],
            current_item: null,
            current_reservation: null,
            year: '',
            month: '',
            day: '',
            error: '',
            response: ''
        }
    },

    methods: {

        createTimeSlot: function(year, month, day) {
            var date = new Date();
            var startDate = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDay()
            var endDate = year + '-' + month + '-' + day
            var startTime = date.getHours() + ':' + date.getHours() + ':' + date.getMinutes()
            var endTime = "11:59:59" // end of the day

            AXIOS.post('timeSlot/create?startTime='+startTime+'&endTime='+endTime+'&startDate='+startDate+'&endDate='+endDate)
            .then(response => {
                console.log(response)
                if (response != null)
                    this.current_timeslot = response.data;
                    this.year = '';
                    this.month = '';
                    this.day = '';
            }).catch(e => {
                console.log(e)
                this.error = e;
            })

            AXIOS.post('')

        },

        getAllMovies: function() {
            if (this.all_movies.length > 0){
                this.all_movies = []
                return
            }
            AXIOS.get('/movies')
            .then(response => {
                this.all_movies = response.data;
            })
            .catch(e => {
                this.error = e;
            })
        },

        getAllAlbums: function() {
            if (this.all_albums.length > 0){
                this.all_albums = []
                return
            }
            AXIOS.get('/albums')
            .then(response => {
                this.all_albums = response.data;
            })
            .catch(e => {
                this.error = e;
            })
        },

        getAllBooks: function() {
            if (this.all_books.length > 0){
                this.all_books = []
                return
            }
            AXIOS.get('/books')
            .then(response => {
                this.all_books = response.data;
            })
            .catch(e => {
                this.error = e;
            })
        },

        getAllNewspapers: function() {
            if (this.all_newspapers.length > 0){
                this.all_newspapers = []
                return
            }
            AXIOS.get('/newspapers')
            .then(response => {
                this.all_newspapers = response.data;
            })
            .catch(e => {
                this.error = e;
            })
        },

        gotoOnlineUser: function() {
            Router.push({
                path: "/onlineuser",
                name: "OnlineUser",
            });
        },

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
            if (time)
            console.log("item: "+item)  
            console.log('reservation: '+localStorage.getItem('reservation'))
            console.log('userId: '+localStorage.getItem('userId'))
            if (localStorage.getItem('reservation') === null){
                Router.push({
                    path: "/createreservation",
                    name: "CreateReservation",
                });
                AXIOS.post('/onlineuser/reserve/username/'+localStorage.getItem('username'))
                .then(response => {
                    console.log(response)
                    localStorage.setItem('reservation', response.data.reservationId) // save reservation id
                    console.log('new reservation: '+localStorage.getItem('reservation'))
                })
            }
            var reservationId = localStorage.getItem('reservation')
            console.log('reservationId: '+reservationId)
            AXIOS.post('onlineuser/additem/userId/'+localStorage.getItem('userId')+'?reservationId='+localStorage.getItem('reservation')+'&itemId='+item)
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
        },

        checkLoggedIn: function() {
            console.log(localStorage.getItem('username'))
            if (localStorage.getItem('username') === null){
                this.logout();
            }
        }
    },
    beforeMount() {
        this.checkLoggedIn();
    },
};
