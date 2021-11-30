import axios, { Axios } from 'axios';
import { use } from 'chai';
import { data } from 'jquery';
import Router from "../router/index";
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

function EventDto (name, isPrivate, isAccepted,timeSlotDto, user, eventId ){
this.name = name
this.isPrivate = isPrivate
this.isAccepted = isAccepted
this.timeSlotDto = timeSlotDto
this.user = user
this.eventId = eventId
}

export default {
    name: "createEvent",
    data(){
        return {
        username: localStorage.getItem('username'),
        userId: localStorage.getItem('userId'),
        current_timeslot: '',
        current_event: '',
        isPrivate: '',
        isAccepted: '',
        startDate: '',
        endDate: '',
        startTime: '',
        endTime: '',
        error: '',
        response: ''
     }
    },


    methods: {

        createEvent: function(eventName, isPrivate, isAccepted) {
            if (this.current_timeslot === null){
                this.error = 'Please chose your timeslot before creating the event.'
                return
            }
            var eventN = eventName
            var isPriv = isPrivate
            var isAcc = isAccepted

            AXIOS.post('event/create?name='+eventN+'&timeSlotId='+this.current_timeslot.timeSlotId+'&isPrivate='+isPriv+'&isAccepted='+isAcc+'&userId='+this.userId)
            .then(response => {
                this.current_event = response.data
                console.log(this.current_event)
                localStorage.setItem('event', this.current_event.eventId)

            })
            .catch(e => {
                this.error = e;
            })

        },

        createTimeSlot: function(sDate, eDate, sTime, eTime) {
            var startDate = sDate
            var endDate = eDate
            var startTime = sTime
            var endTime = eTime 

            AXIOS.post('timeSlot/create?startTime='+startTime+'&endTime='+endTime+'&startDate='+startDate+'&endDate='+endDate)
            .then(response => {
                console.log(response)
                if (response != null)
                    this.current_timeslot = response.data;
                    this.startDate = '';
                    this.endDate = '';
                    this.startTime = '';
                    this.endTime = '',
                    this.alert = "Event Time Selected"
                    localStorage.setItem('timeslot', this.current_timeslot.timeSlotId.toString())
            }).catch(e => {
                console.log(e)
                this.error = e;
            })
        },

        gotoOnlineUserView: function() {
            Router.push({
                path: "/onlineuser",
                name: "OnlineUser",
            });
        },

        logout: function() {
            localStorage.clear();
            Router.push({
                path: "/login",
                name: "Login",
            });
        },

        beforeMount() {
            this.checkLoggedIn();
        },

    }
};