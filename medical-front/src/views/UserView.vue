<template>
  <frame-work>
    <div class="user-profile">
      <h2>个人信息</h2>
      <div class="profile">
        <div class="avatar-section">
          <img :src="avatar" alt="用户头像" class="avatar" />
        </div>
        <div class="info-section">
          <h3>用户</h3>
          <form @submit.prevent="updateUserInfo">

            <p><strong>姓名:</strong> <input v-model="user.username" type="text" /></p>

            <div>
              <h4>修改密码</h4>
              <label for="new-password">新密码:</label>
              <input v-model="password.new" id="new-password" type="password" />

              <label for="confirm-password">确认密码:</label>
              <input v-model="password.confirm" id="confirm-password" type="password" />
            </div>

            <button type="submit" :disabled="isSubmitting">保存修改</button>
          </form>
        </div>
      </div>
    </div>
  </frame-work>
</template>

<script>
import FrameWork from '@/components/FrameWork.vue'
// import axios from 'axios'

export default {
  components: { FrameWork },
  data() {
    return {
      avatar:
        'https://raw.githubusercontent.com/Chanzhaoyu/chatgpt-web/main/src/assets/avatar.jpg', // 替换为真实头像 URL
      user: {
        username: localStorage.getItem('username'), // 用户名
      },
      password: {
        new: '',
        confirm: '',
      },
      userId: localStorage.getItem('id'), // 假设用户的 ID 存储在 localStorage 中
      message: '',
      isSubmitting: false,
    }
  },
  methods: {
    async updateUserInfo() {
      if (this.password.new !== this.password.confirm) {
        alert('密码不一致，请重新输入')
        return
      }

      if (!this.userId) {
        alert('用户ID不存在')
        return
      }

      this.isSubmitting = true

      try {
        const data = {
          id: this.userId, // 传递用户ID
          username: this.user.username,
          password: this.password.new,
        }

        const response = await this.$axios.post('/api/update', data)
        // console.log('response:', response)
        // 根据 API 返回的数据处理
        if (response.data.id === 1) {
          alert('修改成功')
        } else {
          alert('修改失败')
        }
      } catch (error) {
        console.error('修改资料失败:', error)
        alert('提交失败，请稍后重试')
      } finally {
        this.isSubmitting = false
      }
    },
  },
}
</script>

<style scoped>
@import url('../style/views/UserView.css');
</style>
