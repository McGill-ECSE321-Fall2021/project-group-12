import axios, { Axios } from 'axios';
import Router from '../router/index';
var config = require('../../config');

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port;
var backendUrl =
  'http://' + config.dev.backendHost + ':' + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl },
});

export default {
  name: 'cancelevent',
  data() {
    return {
      eventId: '',
      userId: '',
      event: {},
      error: '',
    };
  },

  methods: {
    getEvent: function () {
      this.eventId = this.$route.params.eventId;

      if (!this.eventId) {
        this.error = 'Event not found';
        Router.push({ path: '/events' });
      }

      AXIOS.get('/event/' + this.reservationId)
        .then((res) => {
          this.error = '';
          this.event = res.data;
        })
        .catch((error) => {
          this.error = 'Event not found';
          Router.push({ path: '/events' });
        });
    },
    cancelEvent: function (requestURL) {
      AXIOS.post(requestURL)
        .then((res) => {
          alert('Event has been successfully cancelled');
        })
        .catch((error) => {
          this.error = this.error + '\n' + error.response.data.message;
        });
    },
    cancelEventConfirm: function (id) {
      // users/:userId/events/:eventId/cancel
      this.eventId = this.$route.params.eventId;
      this.userId = this.$route.params.userId;

      this.error = '';

      // check if user exists as offline or online
      AXIOS.get('/offlineuser/userId/' + this.userId)
        .then((offRes) => {
          const requestURL =
            '/offlineuser/cancelevent/userId/' +
            this.userId +
            '?eventId=' +
            this.eventId;

          this.cancelEvent(requestURL);
        })
        .catch((offError) => {
          AXIOS.get('/onlineuser/userId/' + this.userId)
            .then((onRes) => {
              const requestURL =
                '/onlineuser/cancelevent/userId/' +
                this.userId +
                '?eventId=' +
                this.eventId;

              this.cancelEvent(requestURL);
            })
            .catch((onError) => {
              this.error = 'User does not exist';
            });
        });
    },

    cancelEventReject: function () {
      alert('Event not cancelled');
    },
  },
};
