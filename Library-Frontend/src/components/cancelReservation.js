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
  name: 'cancelreservation',
  data() {
    return {
      reservationId: '',
      userId: '',
      reservation: {},
      error: '',
    };
  },

  methods: {
    getReservation: function () {
      this.reservationId = this.$route.params.reservationId;

      if (!this.reservationId) {
        this.error = 'Reservation not found';
        Router.push({ path: '/reservations' });
      }

      Axios.get('/reservation/' + this.reservationId)
        .then((res) => {
          this.reservation = res.data;
        })
        .catch((error) => {
          this.error = 'Reservation not found';
          // Router.push({ path: "/reservations" });
        });
    },
    cancelReservation: function (requestURL) {
      AXIOS.post(requestURL)
        .then((res) => {
          alert('Reservation has been successfully cancelled');
        })
        .catch((error) => {
          this.error = this.error + '\n' + error.response.data.message;
        });
    },
    cancelReservationConfirm: function (id) {
      this.reservationId = this.$route.params.reservationId;
      this.userId = this.$route.params.userId;

      // check if user exists as offline or online
      AXIOS.get('/offlineuser/userId/' + this.userId)
        .then((offRes) => {
          const requestURL =
            '/offlineuser/cancelreservation/userId/' +
            this.userId +
            '?reservationId=' +
            this.reservationId;

          this.cancelReservation(requestURL);
        })
        .catch((offError) => {
          AXIOS.get('/onlineuser/userId/' + this.userId)
            .then((onRes) => {
              const requestURL =
                '/onlineuser/cancelreservation/userId/' +
                this.userId +
                '?reservationId=' +
                this.reservationId;

              this.cancelReservation(requestURL);
            })
            .catch((onError) => {
              this.error = 'User does not exist';
            });
        });
    },

    cancelReservationReject: function () {
      alert('Reservation not cancelled');
    },
  },
};
