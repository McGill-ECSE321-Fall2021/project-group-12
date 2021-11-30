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
    name: "createOfflineReservation",
    data(){
        return {
            userId: '',
            all_albums: [],
            all_books: [],
            all_movies: [],
            all_newspapers: [],
            item_query: '',
            item_response: [],
            reserved_items: [],
            current_item: null,
            current_reservation: null,
            current_timeslot: null,
            year: '',
            month: '',
            day: '',
            error: '',
            response: '',
            alert: '',
            selected_timeslot: '',
            previous_query: ''
        }
    },

    methods: {

        reserveItem: function(item, userId) {
            if (item === null){
                this.error = "";
                return
            }
            if (this.current_timeslot === null){
                this.error = "Please select a return date before selecting your items."
                return
            }
            if (this.current_reservation === null){
                var reservation_items = [item.itemId]
                AXIOS.post('offlineuser/reserve/userId/'+userId+'?itemIds='+item.itemId+'&timeSlotId='+this.current_timeslot.timeSlotId)
                .then (response => {
                    this.current_reservation = response.data
                    console.log(this.current_reservation)
                    this.reserved_items.push(item)
                    localStorage.setItem('reservation', this.current_reservation.reservationId)
                })
                .catch(e => {
                    this.e = "Item already reserved."
                })
            } else {
                AXIOS.post('offlineuser/additem/userId/'+userId+'?reservationId='+this.current_reservation.reservationId+'&itemId='+item.itemId)
                .then (response => {
                    this.reserved_items.push(item)
                })
                .catch(e => {
                    this.e = "Item already reserved."
                })
            }
        },

        removeItem: function(item, userId) {
            for (let i=0;i<this.reserved_items.length;i++){
                if (this.reserved_items[i].itemId === item.itemId){
                    AXIOS.post('/offlineuser/removeitem/userId/'+userId+'?reservationId='+this.current_reservation.reservationId+'&itemId='+item.itemId)
                    .then(response => {
                        console.log(response)
                        this.reserved_items.splice(i, 1) // remove the item
                        if (this.reserved_items.length == 0){
                            localStorage.setItem('reservation', null)
                            this.current_reservation = null;
                            // refresh the lists
                            if(this.all_albums.length > 0){
                                AXIOS.get('/albums')
                                .then(response => {
                                    this.all_albums = response.data;
                                })
                                .catch(e => {
                                    this.error = e;
                                })
                            }
                            if(this.all_books.length > 0){
                                AXIOS.get('/books')
                                .then(response => {
                                    this.all_books = response.data;
                                })
                                .catch(e => {
                                    this.error = e;
                                })
                            }
                            if(this.all_movies.length > 0){
                                AXIOS.get('/movies')
                                .then(response => {
                                    this.all_movies = response.data;
                                })
                                .catch(e => {
                                    this.error = e;
                                })
                            }
                            if(this.all_newspapers > 0){
                                AXIOS.get('/newspapers')
                                .then(response => {
                                    this.all_newspapers = response.data;
                                })
                                .catch(e => {
                                    this.error = e;
                                })
                            }
                            if (this.item_response.length > 0){
                                AXIOS.get('items/getbytitle/'+this.previous_query)
                                .then(response => {
                                    console.log(response)
                                    this.item_response = response.data;
                                })
                                .catch(e => {
                                    console.log('frontend url: ' + frontendUrl);
                                    console.log('\nbackend url:' + backendUrl);
                                    this.error = e + ': Invalid search';
                                })
                            }
                        }
                    })
                    .catch(e => {
                        console.log(e)
                    })
                }
            }
        },

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
                    this.selected_timeslot = "Return Date Selected!   "+this.current_timeslot.endDate
                    localStorage.setItem('timeslot', this.current_timeslot.timeSlotId.toString())
            }).catch(e => {
                console.log(e)
                this.error = e;
            })
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

        gotoOfflineUser: function() {
            Router.push({
                path: "/offlinefunction",
                name: "OfflineFunction",
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
                this.previous_query = this.item_query
                this.item_query = '';
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('\nbackend url:' + backendUrl);
                this.error = e + ': Invalid search';
            })
        },


        logout: function() {
            localStorage.clear();
            Router.push({
                path: "/login",
                name: "Login",
            });
        },

        getReservedItems: function(userId) {

            if (userId === null || userId === ""){
                this.logout();
            }
            AXIOS.get('/offlineuser/reservations/userId/'+userId)
            .then(response => {
                this.reserved_items = response.data;
                console.log(this.reserved_items);
            })
        },

        checkLoggedIn: function() {
            console.log(localStorage.getItem('userId'))
            if (localStorage.getItem('userId') === null){
                this.logout();
        }
        },

        displayTimeSlotStatus: function() {
            var timeSlotId = parseInt(localStorage.getItem('timeslot'))
            console.log('timeSlotId: '+timeSlotId)
            if(timeSlotId !== null && !isNaN(timeSlotId)){
                AXIOS.get('timeSlot/'+timeSlotId)
                .then(response =>{
                    this.current_timeslot = response.data;
                    console.log(this.current_timeslot)
                    this.selected_timeslot = 'Return Date: '+this.current_timeslot.endDate
                })
            }
        },

        getReservationStatus: function() {
            var reservationId = parseInt(localStorage.getItem('reservation'))
            console.log('reservationId: '+reservationId)
            if(reservationId !== null && !isNaN(reservationId)){
                AXIOS.get('reservation/'+reservationId)
                .then(response =>{
                    this.current_reservation = response.data;
                    this.reserved_items = this.current_reservation.items
                    console.log(this.current_reservation)
                })
            }
        }
    },
    //beforeMount() {
    //    this.checkLoggedIn();
    //    this.displayTimeSlotStatus();
    //    this.getReservationStatus();
    //},
};
