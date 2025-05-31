<template>
  <div class="container">
    <div class="register-container">
      <h1>注册</h1>
      <form @submit.prevent="SubmitEvent">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" v-model="username" id="username" required placeholder="请输入用户名" />
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input type="password" v-model="password" id="password" required placeholder="请输入密码" />
        </div>
        <button type="submit">注册</button>
        <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      </form>
    </div>
  </div>

</template>
  
  <script>
import router from '@/router'

export default {
  name: 'RegisterView',
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
    }
  },
  methods: {
    SubmitEvent() {
      this.errorMessage = ''
      if (this.username && this.password) {
        // 添加注册逻辑
        this.$axios
          .post('/api/register', {
            username: this.username,
            password: this.password,
          })
          .then((res) => {
            console.log('RegisterView:', res.data)
            if (res.data.id == 1) {
              alert('注册成功！')
              router.push({ name: 'login' })
            } else {
              alert('注册失败！')
            }

            // 清空输入
            this.username = ''
            this.password = ''
          })
          .catch((err) => {
            console.error(err)
            this.errorMessage = '注册失败，请重试。' // 处理错误时的提示
          })
      } else {
        this.errorMessage = '请填写完整。'
      }
    },
  },
}
</script>
  
  <style scoped>
@import url('../style/views/RegisterView.css');
</style>
  