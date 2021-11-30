import axios from 'axios';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function AlbumDto (title, isArchive, isReservable, releaseDate, numSongs, available, genre, creator) {
    this.title = title
    this.isArchive = isArchive
    this.isReservable = isReservable
    this.releaseDate = releaseDate
    this.numSongs = numSongs
    this.available = available
    this.genre = genre
    this.creator = creator
}

function BookDto (title, isArchive, isReservable, releaseDate, numPages, available, genre, creator) {
    this.title = title
    this.isArchive = isArchive
    this.isReservable = isReservable
    this.releaseDate = releaseDate
    this.numPages = numPages
    this.available = available
    this.genre = genre
    this.creator = creator
}

function BookDto (title, isArchive, isReservable, isAvailable, releaseDate, duration, genre, creator) {
    this.title = title
    this.isArchive = isArchive
    this.isReservable = isReservable
    this.isAvailable = isAvailable
    this.releaseDate = releaseDate
    this.duration = duration
    this.genre = genre
    this.creator = creator
}

export default {
    name: "librarian",
    data(){
        return {
            albumTitle: '',
            bookTitle: '',
            movieTitle: '',
            newspaperCreateTitle: '',
            newspaperUpdateTitle: '',
            albumCreateIsReservable: false,
            bookCreateIsReservable: false,
            movieCreateIsReservable: false,
            newspaperCreateIsReservable: false,
            albumCreateIsArchive: false,
            bookCreateIsArchive: false,
            movieCreateIsArchive: false,
            newspaperCreateIsArchive: false,
            albumCreateAvailable: false,
            bookCreateAvailable: false,
            movieCreateAvailable: false,
            newspaperCreateAvailable: false,
            albumCreateIsAvailable: false,
            bookCreateIsAvailable: false,
            movieCreateIsAvailable: false,
            newspaperCreateIsAvailable: false,
            albumUpdateIsReservable: false,
            bookUpdateIsReservable: false,
            movieUpdateIsReservable: false,
            newspaperUpdateIsReservable: false,
            albumUpdateIsArchive: false,
            bookUpdateIsArchive: false,
            movieUpdateIsArchive: false,
            newspaperUpdateIsArchive: false,
            albumUpdateAvailable: false,
            bookUpdateAvailable: false,
            movieUpdateAvailable: false,
            newspaperUpdateAvailable: false,
            albumUpdateIsAvailable: false,
            bookUpdateIsAvailable: false,
            movieUpdateIsAvailable: false,
            newspaperUpdateIsAvailable: false,
            albumReleaseDate: '',
            bookReleaseDate: '',
            movieReleaseDate: '',
            newspaperCreateReleaseDate: '',
            newspaperUpdateReleaseDate: '',
            numPages: '',
            numSongs: '',
            duration: '',
            albumGenre: '',
            bookGenre: '',
            movieGenre: '',
            albumCreatorId: '',
            bookCreatorId: '',
            movieCreatorId: '',
            newspaperCreateCreatorId: '',
            newspaperUpdateCreatorId: '',
            id_query: '',
            id_response: null,
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
            isHead: false,
            albumUpdateId: '',
            bookUpdateId: '',
            movieUpdateId: '',
            newspaperUpdateId: '',
            albumDeleteId: '',
            bookDeleteId: '',
            movieDeleteId: '',
            newspaperDeleteId: '',
            allOfflineUsers: [],
            creatorFirst: '',
            creatorLast: '',
            creatorType: '',
            allCreators: []
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
            AXIOS.post('librarian/album/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&releaseDate='+releaseDate+'&numSongs='+numSongs+'&isAvailable='+available+'&genre='+genre+'&creatorId='+creatorId)
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
            AXIOS.delete('/librarian/ablum/delete?itemId='+id)
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
            AXIOS.put('librarian/album/update?itemId='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&isAvailable='+available)
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
            AXIOS.post('librarian/book/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&releaseDate='+releaseDate+'&numPages='+numPages+'&isAvailable='+available+'&genre='+genre+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteBook: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/book/delete?itemId='+id)
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
            AXIOS.put('librarian/book/update?itemId='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&isAvailable='+available)
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
            AXIOS.post('librarian/movie/create?title='+title+'&isArchive='+isArchive+'&isReservable='+isReservable+'&isAvailable='+isAvailable+'&releaseDate='+releaseDate+'&duration='+duration+'&genre='+genre+'&creatorId='+creatorId)
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
            AXIOS.delete('/librarian/movie/delete?itemId='+id)
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
            AXIOS.put('librarian/movie/update?itemId='+id+'&isArchive='+isArchive+'&isReservable='+isReservable+'&isAvailable='+isAvailable)
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
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        deleteNewspaper: function (id) {
            console.log('id: ' + id)
            AXIOS.delete('/librarian/newspaper/delete?itemId='+id)
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
            console.log('isArchive: ' + isArchive)
            console.log('release date: ' + releaseDate)
            console.log('creator id: ' + creatorId)
            AXIOS.put('librarian/newspaper/update?itemId='+id+'&title='+title+'&isArchive='+isArchive+'&releaseDate='+releaseDate+'&creatorId='+creatorId)
            .then(response => {
                this.response = response.data;
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl)
                console.log('\nbackend url:' + backendUrl)
                this.error = e;
            })
        },

        createCreator: function (creatorFirst, creatorLast, creatorType){
            console.log('firstName: ' + creatorFirst)
            console.log('lastName: ' + creatorLast)
            console.log('creatorType: ' + creatorType)
            AXIOS.post('creator/create?firstName='+creatorFirst+'&lastName='+creatorLast+'&creatorType='+creatorType)
            .then(response => {
                this.response = response.data;
                this.creatorFirst = ''
                this.creatorLast = ''
                this.creatorType = ''
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
                console.log(this.id_response);
            })
            .catch(e => {
                console.log('frontend url: ' + frontendUrl);
                console.log('\nbackend url:' + backendUrl);
                this.error = e;
            })
        },

        getOfflineUsers: function() {
            AXIOS.get('offlineusers')
            .then(response => {
                this.allOfflineUsers = response.data
            })
            .catch(e => {
                this.error = e
            })
        },

        getCreators: function()
        {
            AXIOS.get('creators')
            .then(response => {
                this.allCreators = response.data
            })
            .catch(e => {
                this.error = e
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