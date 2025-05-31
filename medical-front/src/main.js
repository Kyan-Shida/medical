import Vue from 'vue';
import App from './App.vue';
import router from './router';
import axiosinstance from './utils/Axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 设置生产提示
Vue.config.productionTip = false;

// 将 Axios 注册为全局属性
Vue.prototype.$axios = axiosinstance;

//使用vue
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
