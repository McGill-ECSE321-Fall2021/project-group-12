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
            item_query: '',
            item_response: [],
            reserved_response: [],
            error: '',
            response: '',
            librarian: '',
            firstname: '',
            lastName: '',
            address: '',
            email: '',
            username: '',
            password: '',
            id: 0,
            isLocal: false,
            isHead: false
        }
    },

    methods: {
        
        createAlbum: function (title, isArchive, isReservable, releaseDate, numSongs, available, genre, creatorId) {
            console.log('title: ' + title)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('release date: ' + releaseDate)
            console.log('num songs: ' + numSongs)
            console.log('available: ' + available)
            console.log('genre: ' + genre)
            console.log('creator id: ' + creatorId)
            AXIOS.post('librarian/album/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&releaseDate='+releaseDate+'&numSongs='+numSongs+'&available='+available+'&genre='+genre+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('title', title);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteAlbum: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/ablum/delete?id='+id)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        updateAlbum: function (id, isArchive, isReservable, available) {
            console.log('id: ' + id)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('available: ' + available)
            AXIOS.put('librarian/album/update?id='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&available='+available)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        createBook: function (title, isArchive, isReservable, releaseDate, numPages, available, genre, creatorId) {
            console.log('title: ' + title)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('release date: ' + releaseDate)
            console.log('num pages: ' + numPages)
            console.log('available: ' + available)
            console.log('genre: ' + genre)
            console.log('creator id: ' + creatorId)
            AXIOS.post('librarian/book/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&releaseDate='+releaseDate+'&numPages='+numPages+'&available='+available+'&genre='+genre+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('title', title);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteBook: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/book/delete?id='+id)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        updateBook: function (id, isArchive, isReservable, available) {
            console.log('id: ' + id)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('available: ' + available)
            AXIOS.put('librarian/book/update?id='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&available='+available)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        createMovie: function (title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creatorId) {
            console.log('title: ' + title)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('is available: ' + isAvailable)
            console.log('release date: ' + releaseDate)
            console.log('duration: ' + duration)
            console.log('genre: ' + genre)
            console.log('creator id: ' + creatorId)
            AXIOS.post('librarian/book/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&isAvailable='+isAvailable+'&releaseDate='+releaseDate+'&duration='+duration+'&genre='+genre+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('title', title);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteMovie: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/movie/delete?id='+id)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        updateMovie: function (id, isArchive, isReservable, isAvailable) {
            console.log('id: ' + id)
            console.log('is archive: ' + isArchive)
            console.log('is reservable: ' + isReservable)
            console.log('is available: ' + isAvailable)
            AXIOS.put('librarian/movie/update?id='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&available='+isAvailable)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        createNewspaper: function (title, isArchive, releaseDate, creatorId) {
            console.log('title: ' + title)
            console.log('is archive: ' + isArchive)
            console.log('release date: ' + releaseDate)
            console.log('creator id: ' + creatorId)
            AXIOS.post('librarian/newspaper/create?title='+title+'&isArchive='+isArchive+'&releaseDate='+releaseDate+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
                localStorage.setItem('title', title);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteNewspaper: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/newspaper/delete?id='+id)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        updateNewspaper: function (id, title, isArchive, releaseDate, creatorId) {
            console.log('id: ' + id)
            console.log('title: ' + title)
            console.log('is archive: ' + isArchive)
            console.log('release date: ' + releaseDate)
            console.log('creator id: ' + creatorId)
            AXIOS.put('librarian/newspaper/update?id='+id+'&title='+title+'&isArchive='+isArchive+'&releaseDate='+releaseDate+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        searchOfflineUser: function (query) {
            if (query === null || query === ""){
                this.error = "";
                return
            }
            console.log('offlineUser query: ' + query);
            AXIOS.get('librarian/offlineUser/'+query)
            .then(response => {
                this.id_response = response.data;
                this.id_query = '';
                console.log(id_response);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('\nbackend url:' + backendUrl);
                this.error = e;
            })
        },

        gotoOfflineUserView: function() {
            Router.push({
                path: "/offlineuser",
                name: "OfflineUser",
            });
        },
        signUpLibrarian: function (firstName, lastName, address, username, password, email) {
            console.log('first name: ' + firstName)
            console.log('last name: ' + lastName)
            console.log('address: ' + address)
            console.log('username: ' + username)
            console.log('password: ' + password)
            console.log('email: ' + email)
            AXIOS.post('/librarian/create/head?firstname='+firstName+'&lastname='+lastName+'&address='+address+'&email='+email+'&password='+password+'&username='+username)
            .then(response => {
                this.response = response.data;
		        this.librarian = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },
        checkLoggedIn: function() {
            console.log(localStorage.getItem("username"));
            if (localStorage.getItem("username") === null) {
              this.logout();
            }
        },
        logout: function() {
            localStorage.clear();
            Router.push({
              path: "/login",
              name: "Login"
            });
        },
        
    },
    beforeMount() {
        this.checkLoggedIn();
      }
};