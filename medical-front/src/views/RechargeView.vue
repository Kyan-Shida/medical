<template>
  <frame-work>
    <div class="recharge-page">
      <div class="recharge-form">
        <h3>充值信息</h3>
        <div class="row form-group">
          <label for="30000">
            <input type="radio" name="coin" id="30000" value="100" v-model="amount">
            <span class="radio">1000次<br>100元</span>
          </label>
          <label for="10000">
            <input type="radio" name="coin" id="10000" value="50" v-model="amount">
            <span class="radio">500次<br>50元</span>
          </label>
          <label for="5000">
            <input type="radio" name="coin" id="5000" value="28" v-model="amount">
            <span class="radio">200次<br>20元</span>
          </label>
          <label for="2000">
            <input type="radio" name="coin" id="2000" value="13" v-model="amount">
            <span class="radio">130次<br>13元</span>
          </label>
          <label for="1000">
            <input type="radio" name="coin" id="1000" value="7" v-model="amount">
            <span class="radio">70次<br>7元</span>
          </label>
          <label for="500">
            <input type="radio" name="coin" id="500" value="4" v-model="amount">
            <span class="radio">40次<br>4元</span>
          </label>
          <label for="220">
            <input type="radio" name="coin" id="220" value="2" v-model="amount">
            <span class="radio">20次<br>2元</span>
          </label>
          <label for="100">
            <input type="radio" name="coin" id="100" value="1" v-model="amount">
            <span class="radio">10次<br>1元</span>
          </label>
        </div>

        <h3>目前次数余额：{{ times }}</h3>
        <p class="info">——数据刷新有延迟——</p>
        <!-- 充值表单 -->
        <form @submit.prevent="processRecharge">
          <button type="submit" :disabled="isSubmitting">确认充值</button>
        </form>
      </div>
    </div>
  </frame-work>
</template>
    
  <script>
import FrameWork from '@/components/FrameWork.vue'

export default {
  components: { FrameWork },
  data() {
    return {
      amount: '1', // 默认值设为100元
      isSubmitting: false,
      times: Number(localStorage.getItem('times')), // 初始化时从 localStorage 获取余额
      username: localStorage.getItem('username'),
    }
  },
  mounted() {
    this.show_times() // 初始时获取一次余额
    this.startRefreshingTimes() // 开始定时刷新余额
  },
  beforeDestroy() {
    clearInterval(this.refreshInterval) // 清除定时器
  },
  methods: {
    async processRecharge() {
      if (!this.amount || this.amount <= 0) {
        alert('请输入有效的充值金额')
        return
      }

      if (!this.username) {
        alert('用户ID不存在')
        return
      }

      this.isSubmitting = true

      try {
        const data = {
          username: this.username,
          times: this.times + this.amount * 10, // 充值后的次数
        }

        const response = await this.$axios.post('/api/times', data)
        console.log('processRecharge', response.data)
        if (response.data.id === 1) {
          alert('充值成功！')
        } else {
          alert('充值失败，请稍后重试')
        }
      } catch (error) {
        console.error('充值失败:', error)
        alert('提交失败，请稍后重试')
      } finally {
        this.isSubmitting = false
      }
    },
    // 获取次数
    show_times() {
      this.$axios
        .get('/api/times', {
          params: { username: localStorage.getItem('username') },
        })
        .then((res) => {
          console.log('show_times', res.data)
          localStorage.setItem('times', res.data.data) // 更新 localStorage
          this.times = res.data.data // 更新组件中的 times
        })
        .catch((err) => {
          console.error(err)
        })
    },
    // 定时刷新次数余额
    startRefreshingTimes() {
      this.refreshInterval = setInterval(() => {
        this.show_times() // 每隔一段时间调用 show_times 更新余额
      }, 10000) // 每 30 秒刷新一次
    },
  },
}
</script>
  
  <style scoped>
@import url('../style/views/RechargeView.css');
</style>
  