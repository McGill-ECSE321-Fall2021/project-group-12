import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Login from '@/components/Login.vue'
import SignUp from '@/components/SignUp.vue'
import OnlineUser from '@/components/OnlineUser.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/login',
      name: 'Login',
      component: require('../components/Login.vue').default
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: require('../components/SignUp.vue').default
    },
    {
      path: '/onlineuser',
      name: 'OnlineUser',
      component: require('../components/OnlineUser.vue').default
    },
    {
      path: '/librarian',
      name: 'Librarian',
      component: require('../components/Librarian.vue').default
    }
  ]
})
