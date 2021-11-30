import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";
import OnlineUser from "@/components/OnlineUser.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/hello",
      name: "Hello",
      component: Hello
    },
    {
      path: "/login",
      name: "Login",
      component: require("../components/Login.vue").default
    },
    {
      path: "/",
      name: "LandingPage",
      component: require("../components/Login.vue").default
    },
    {
      path: "/signup",
      name: "SignUp",
      component: require("../components/SignUp.vue").default
    },
    {
      path: "/onlineuser",
      name: "OnlineUser",
      component: require("../components/OnlineUser.vue").default
    },
    {
      path: "/createreservation",
      name: "CreateReservation",
      component: require("../components/CreateReservation.vue").default
    },
    {
      path: "/libraryhour",
      name: "LibraryHour",
      component: require("../components/LibraryHour.vue").default
    },
    {
      path: "/librarian",
      name: "Librarian",
      component: require("../components/Librarian.vue").default
    },
    {
      path: "/offlineuser",
      name: "OfflineUser",
      component: require("../components/OfflineUser.vue").default
    },
    {
      path: "/users/:userId/reservations/:reservationId/cancel",
      name: "CancelReservation",
      component: require("../components/CancelReservation.vue").default
    },
    {
      path: "/users/:userId/events/:eventId/cancel",
      name: "CancelEvent",
      component: require("../components/CancelEvent.vue").default
    },
    {
      path: "/onlineuser/delete",
      name: "DeleteOnlineUser",
      component: require("../components/DeleteOnlineUser.vue").default
    },
    {
      path: '/updateonlineaccount',
      name: 'UpdateOnlineAccount',
      component: require('../components/UpdateOnlineAccount.vue').default
    },
    {
      path: '/headlibrarian',
      name: 'HeadLibrarian',
      component: require('../components/HeadLibrarian.vue').default
    },
    {
      path: '/createevent',
      name: 'CreateEvent',
      component: require('../components/CreateEvent.vue').default
    }
  ]
});
