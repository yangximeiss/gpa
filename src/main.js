import Vue from 'vue'
import router from "./router";
import App from './App.vue'
import axios from 'axios';

import VueRouter from 'vue-router'
Vue.use(VueRouter)


import ElementUI from 'element-ui'; // 2.1引入结构
import 'element-ui/lib/theme-chalk/index.css'; // 2.2引入样式
Vue.use(ElementUI);

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8080';

Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    router: router
}).$mount('#app')