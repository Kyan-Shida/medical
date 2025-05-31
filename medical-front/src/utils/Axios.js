import axios from "axios";

const axiosinstance=axios.create({
    baseURL: 'http://localhost:8080', // 你的 API 基本路径
    timeout: 600000, // 请求超时时间10分钟
})

axiosinstance.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
        //   config.headers.Authorization = `Bearer ${token}`;//可替换
        config.headers.token = `Bearer ${token}`;
        }
        return config;
      },
      error => {
        return Promise.reject(error);
      }
)
// 响应拦截器（可选）
axiosinstance.interceptors.response.use(
    response => {
      return response;
    },
    error => {
      return Promise.reject(error);
    }
  );
  
  export default axiosinstance;