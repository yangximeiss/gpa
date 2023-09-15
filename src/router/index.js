import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/LoginInterface.vue";
import Main from "../components/MainInterface.vue";
import Register from "../components/registerInterface.vue";
import Course from "../components/CourseInterface.vue"

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter);

const routes = [{
        path: '',
        component: Login
    },
    {
        path: "/Login",
        name: "Login",
        component: Login
    },
    {
        path: "/Main",
        name: "Main",
        component: Main
    },
    {
        path: "/Register",
        name: "Register",
        component: Register
    },
    {
        path: "/Course",
        name: "Course",
        component: Course
    }
];

const router = new VueRouter({
    routes
});


export default router;