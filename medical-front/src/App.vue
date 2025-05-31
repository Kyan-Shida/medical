<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  created() {
    this.checkToken()
  },
  destroyed() {
    // 确保在组件销毁时也移除事件监听
    window.removeEventListener('beforeunload', this.clearLocalStorage)
  },
  methods: {
    checkToken() {
      if (localStorage.getItem('token') && localStorage.getItem('expire_sum')) {
        return
      }
      const token = localStorage.getItem('token')
      const expire_sum = localStorage.getItem('expire_sum')

      if (this.$route.meta.requiresAuth === true) {
        if (token && Date.now() < expire_sum) {
          // token 有效
          console.log('AppView: Token有效, 页面跳转')
        } else {
          // token 无效，删除
          this.clearLocalStorage()
          console.log('AppView: Token无效/不存在')
          alert('请重新登录！')
          // 重定向到登录页
          this.$router.push({ name: 'login' })
        }
      }
    },

    clearLocalStorage() {
      localStorage.removeItem('token')
      localStorage.removeItem('expire_sum')
      localStorage.removeItem('username')
      localStorage.removeItem('times')
      localStorage.removeItem('id')
      console.log('AppView: localStorage已清除')
    },
  },
}
</script>

<style scoped>
#app {
  padding: 0;
  margin: 0;
}
a {
  text-decoration: none;
}
</style>
