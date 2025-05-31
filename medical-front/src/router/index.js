// import { meta } from '@babel/eslint-parser'
// import { meta } from '@babel/eslint-parser'
import Vue from 'vue'
import VueRouter from 'vue-router'
// import { component } from 'vue/types/umd'

Vue.use(VueRouter)

const routes = [
  // {
  //   path:'/',
  //   name:'login',
  //   redirect:'/login'
  // },
  {
    path:'/',
    name:'home',
    redirect:'/home'
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/HomeView.vue'),
    // meta:{requiresAuth:true}

  },
  {
    path: '/education',
    name: 'education',
    component: () => import('../views/EducationView.vue'),
    meta:{requiresAuth:true}

  },
  {
    path: '/upload',
    name: 'upload',
    component: () => import('../views/UploadView.vue'),
    meta:{requiresAuth:true}

  },
  {
    path: '/risk',
    name: 'risk',
    component: () => import('../views/RiskView.vue'),
    meta:{requiresAuth:true}

  },
  {
    path:'/login',
    name:'login',
    component:()=>import('../views/LoginView.vue')
  },
  {
    path:'/register',
    name:'register',
    component:()=>import('../views/RegisterView.vue')
  },
  {
    path:'/user',
    name:'user',
    component:()=>import('../views/UserView.vue'),
    meta:{requiresAuth:true}
  },
  {
    path:'/hosptial',
    name:'hosptial',
    component:()=>import('../views/HosptialView.vue'),
    meta:{requiresAuth:true}
  },
  {
    path:'/recharge',
    name:'recharge',
    component:()=>import('../views/RechargeView.vue'),
    meta:{requiresAuth:true}
  }
]

const router = new VueRouter({
  routes,
 mode:'history'
})

router.beforeEach((to, from, next) => {
  
  const token = localStorage.getItem('token');
  const expire_sum = localStorage.getItem('expire_sum');

  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (token && Date.now() < expire_sum) {
      next(); // token 有效，继续导航
    } else {
      console.log('IndexView:token无效，重定向到登录页')
           localStorage.removeItem('token')
      localStorage.removeItem('username')
      localStorage.removeItem('times')
      localStorage.removeItem('expire_sum')
      localStorage.removeItem('id')
      next({ name:'login' }); // token 无效，重定向到登录页
    }
  } else {
    next(); // 不需要身份验证的路由
  }
});

export default router
