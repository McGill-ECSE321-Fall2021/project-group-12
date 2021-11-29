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
      username: "",
      error: ""
    };
  },

  methods: {
    deleteUserConfirm: function() {
      this.username = localStorage.getItem("username");

      if (!this.username) {
        this.error = "Please log in first to delete account";
        return;
      }

      AXIOS.delete("/onlineuser/delete/username/" + this.username)
        .then(response => {
          alert("Account deleted successfully");
          localStorage.setItem("username", "");
          Router.push({
            path: "/login"
          });
        })
        .catch(error => {
          this.error = error.response.data.message;
        });
    },
    deleteUserReject: function() {
      Router.push({
        path: "/onlineuser"
      });
    },
    checkLoggedIn: function() {
      console.log(localStorage.getItem("username"));
      if (localStorage.getItem("username") === null) {
        this.logout();
      }
    }
  },
  beforeMount() {
    this.checkLoggedIn();
  }
};
