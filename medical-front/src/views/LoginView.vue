<template>
  <div class="container">
    <div class="box_out">
      <div class="box_left">
        <img src="../assets/img/1.jpg" alt="" class="box_left_img">
      </div>
      <div class="box_right">
        <table class="box_right_table">
          <tr>
            <th>登录</th>
          </tr>
          <tr>
            <td><label for="username">用户：</label><input type="text" name="username" id="username" v-model="user.username"></td>
          </tr>
          <tr>
            <td><label for="password">密码：</label><input type="password" name="password" id="password" v-model="user.password" @keyup.enter="login">
            </td>
          </tr>
          <tr>
            <td>
              <button type="button" @click="login">登录</button>
              <!-- ————
              <button type="button" @click="logout">退出</button> -->
            </td>
          </tr>
          <tr>
            <td><a href="/register">注册</a></td>
          </tr>
        </table>
      </div>
    </div>

  </div>
</template>
  
  <script>
export default {
  name: 'LoginView',
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      expire_time: 600000, //设置 token 过期时间,600000毫秒 = 10 分钟
    }
  },
  methods: {
    login() {
      // console.log('LoginView', this.user)
      this.$axios
        .post('/api/login', this.user)
        .then((res) => {
          console.log('LoginView:', res.data.data)
          if (res.data.msg == '成功') {
            const expire_sum = Date.now() + this.expire_time //定义 token过期的‘目标’闹钟时间
            localStorage.setItem('token', res.data.data.token)
            localStorage.setItem('expire_sum', expire_sum) //在localStorage设置 token过期‘目标’闹钟时间
            localStorage.setItem('username', this.user.username)
            localStorage.setItem('times', res.data.data.times)
            localStorage.setItem('id', res.data.data.id)
            console.log('LoginView:登录成功')
            this.$router.push({
              name: 'home', //登陆成功，跳转home view页面
            })
          } else {
            console.log('LoginView:登录失败')
            alert('登录失败')
          }
        })
        .catch((err) => {
          console.error(err)
        })

      setTimeout(() => {
        this.logout()
      }, this.expire_time)
    },
    logout() {
      console.log('logout:删除token')
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      localStorage.removeItem('times')
      localStorage.removeItem('expire_sum')
      localStorage.removeItem('id')
    },
  },
}
</script>
  
<style scoped>
@import url('../style/views/LoginView.css');
</style>