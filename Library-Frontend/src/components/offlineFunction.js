import axios from "axios";
import Router from "../router/index";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "onlineuser",
  data() {
    return {
      username: localStorage.getItem("username"),
      userId: localStorage.getItem("userId"),
      item_query: "",
      item_response: [],
      reserved_response: [],
      current_item: null,
      error: "",
      response: ""
    };
  },

  methods: {
        gotoOfflineCreateReservation: function() {
        Router.push({
            path: "/createofflinereservation",
            name: "CreateOfflineReservation"
        });
        },

        gotoOfflineCreateEvent: function() {
            Router.push({
                path: "/createofflineevent",
                name: "CreateOfflineEvent",
            });
        },

        logout: function() {
            localStorage.clear();
            Router.push({
                path: "/login",
                name: "Login",
            });
        },

        logout: function() {
        localStorage.clear();
        Router.push({
            path: "/offlineuser",
            name: "OfflineUser"
        });
        },

        getReservedItems: function() {
        var username = localStorage.getItem("username");
        if (username === null || username === "") {
            this.logout();
        }
        AXIOS.get(
            "/onlineuser/reservations/username/" + localStorage.getItem("username")
        ).then(response => {
            this.reserved_response = response.data;
            console.log(this.reserved_response);
        });
        }
    },

};
