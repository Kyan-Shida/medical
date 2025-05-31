<template>
  <div class="container">
    <header class="header">
      <h1>健康管家平台</h1>
      <nav>
        <ul>
          <li @click="user_dist">
            <div class="center">
              <img src="../assets/icons/user.svg" alt="">
              <span id="username-display" class="username">欢迎:（{{ username }}）</span>
              <div class="login"><a href="/login" v-if="this.username=='游客'">登录</a></div>
            </div>
            <div class="user_dist_down" v-if="(user_dist_down)">
              <tr>
                <td>
                  <div><a href="/user">我的信息</a></div>
                </td>
              </tr>
              <tr>
                <td>
                  <div><a href="/recharge">我的钱包</a></div>
                </td>
              </tr>
              <tr>
                <td>
                  <div @click="logout"><a href="/login">退出登录</a></div>
                </td>
              </tr>

            </div>
          </li>
        </ul>

      </nav>
    </header>
    <main class="main">
      <nav class="nav">
        <ul>
          <li>
            <div class="center"><strong>健康功能</strong><img src="../assets/icons/align-center.svg" alt=""></div>
          </li>
          <li>
            <div class="center"><a href="/home">健康首页<img src="../assets/icons/down.svg" alt=""></a></div>
          </li>
          <li>
            <div class="center"><a href="/education">知识普及<img src="../assets/icons/down.svg" alt=""></a></div>
          </li>
          <li>
            <div class="center"><a href="/upload">病历分析<img src="../assets/icons/down.svg" alt=""></a></div>
          </li>
          <li>
            <div class="center"><a href="/risk">风险预测<img src="../assets/icons/down.svg" alt=""></a></div>
          </li>
          <li>
            <div class="center"><a href="/hosptial">查找医院<img src="../assets/icons/down.svg" alt=""></a></div>
          </li>
        </ul>
      </nav>
      <div class="content">
        <slot></slot>
      </div>
    </main>
    <footer class="footer">
      <!-- <h3>联系我</h3> -->
      <p>作者: Ryan</p>
      <p>
        邮箱: <a href="mailto:asd123a@outlook.com">asd123a@outlook.com</a>
      </p>
    </footer>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      username: '游客', // 初始化用户名
      user_dist_down: false,
    }
  },
  mounted() {
    // 从 localStorage 中读取用户名
    const storedUsername = localStorage.getItem('username')
    if (storedUsername) {
      this.username = storedUsername // 设置用户名
    }
  },
  methods: {
    user_dist() {
      if (this.username != '游客') {
        this.user_dist_down = ~this.user_dist_down
      }

      // console.log('this.user_dist_down' + this.user_dist_down)
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
  
  <style>
@import url('../style/component/FrameWork.css');
</style>
  