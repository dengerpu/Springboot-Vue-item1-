import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/register.vue'
import FindPassword from '../components/findPassword.vue'
import Home from '../components/Home.vue'
import Users from '../components/user/Users.vue'

Vue.use(VueRouter)
Vue.use(Login)
Vue.use(Register)
Vue.use(FindPassword)
Vue.use(Home)
Vue.use(Users)

const routes = [
    {path:'/',redirect:'/login'},
     // 登录页面：
    {path:'/login',component:Login},
      // 注册页面：
    {path:'/register',component:Register},
    // 找回密码页面：
    {path:'/findPassword',component:FindPassword},
    //主页
    {path:'/home',component:Home},
    {path:'/users',component:Users}   
]


const router = new VueRouter({
  routes
})

//挂载路由导航守卫
router.beforeEach((to,from,next)=>{
  // to将要访问的路径
   //from代表从哪个路径跳转而来
   //next是一个函数表示放行  next()放行  next('/login') 强制跳转
   if(to.path === '/login') return next();
   if(to.path === '/register') return next();
   if(to.path === '/findPassword') return next();
   //获取token
   const tokenStr = window.sessionStorage.getItem('token');
   if(!tokenStr) return next('/login');
   next();
 })

export default router
