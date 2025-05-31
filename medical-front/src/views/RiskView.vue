<template>
  <frame-work>
    <div class="chat-container">
      <h1 class="chat-title">问答</h1>

      <div class="conversation-box">
        <div v-for="(message, index) in messages" :key="index" :class="['message', message.role == '用户' ? 'user' : 'bot']">
          <div class="message-content" v-html="message.content"></div>
          <div class="avatar" :class="message.role == '用户' ? 'user-avatar' : 'bot-avatar'">
            <img :src="message.avatar" alt="头像" />
          </div>
        </div>
      </div>

      <!-- 显示“正在思考...” -->
      <div v-if="isProcessing_talk" class="loading-state">
        正在思考...
      </div>

      <!-- 显示加载进度条 -->
      <div v-if="progress > 0 && progress < 100" class="progress-container">
        <div class="progress-bar" :style="{ width: progress + '%' }"></div>
      </div>

      <div class="input-container">
        <input type="text" v-model="userInput" placeholder="输入你的问题" @keyup.enter="sendMessage" :disabled="times === 0">
        <button @click="toggleRecognition" :class="{ 'recognizing': isRecognizing }">
          {{ isRecognizing ? "语音停止识别" : "语音开始识别" }}
        </button>
        <button @click="sendMessage" v-if="times != 0" class="send-btn">发送</button>
        <button v-if="times == 0" class="warning-btn">你的次数已用完，请充值。</button>
      </div>

      <p class="speech-recognition-info">——语音识别推荐使用Edge浏览器（Chrome暂不支持！）</p>
    </div>
  </frame-work>
</template>

<script>
import { marked } from 'marked' // 确保正确导入
import FrameWork from '@/components/FrameWork.vue'
import axios from 'axios'

export default {
  components: { FrameWork },
  name: 'RiskView',
  data() {
    return {
      userInput: '',
      messages: [],
      recognition: null,
      isRecognizing: false,
      fullTranscript: '',
      output: '',
      times: 10, //问答可用次数
      isProcessing_talk: false, // 控制“正在思考”的显示
      progress: 0, // 进度条的初始值
    }
  },
  mounted() {
    // 初始化语音识别
    this.recognition = new window.webkitSpeechRecognition()
    this.recognition.continuous = true
    this.recognition.interimResults = true
    this.recognition.lang = 'zh-CN' // 设置为中文

    this.recognition.onresult = (event) => {
      const transcript = event.results[event.resultIndex][0].transcript
      this.fullTranscript = transcript // 更新完整文本
      // 添加用户消息
      if (this.fullTranscript != '') {
        this.userInput = this.fullTranscript
      }
    }

    this.recognition.onerror = (event) => {
      console.error('识别错误:', event.error)
    }

    this.recognition.onend = () => {
      console.log('识别已结束')
      console.log('完整识别文本:', this.userInput)
    }

    this.times = localStorage.getItem('times') // 获取问答次数
    this.send_times() // 将回答可用次数发送服务器
  },
  methods: {
    sendMessage() {
      if (!this.userInput || this.times === 0) return
      this.times--
      localStorage.setItem('times', this.times)
      this.isProcessing_talk = true
      this.progress = 0 // 初始化进度

      // 添加用户消息
      this.messages.push({
        role: '用户',
        content: this.userInput,
        avatar:
          'https://raw.githubusercontent.com/Chanzhaoyu/chatgpt-web/main/src/assets/avatar.jpg',
      })

      const params = {
        prompt: this.userInput,
        userId: '#/chat/1729685907501',
        network: true,
        system: '',
        withoutContext: false,
        stream: false,
      }

      // 清空输入框
      this.userInput = ''
      axios
        .post(
          'https://api.binjie.fun/api/generateStream?refer__1360=eqIxRiwx9D0DgiDlcmq0%3DIy4Gu4Wu5xHqD%3DF4D',
          params,
          {
            headers: {
              accept: 'application/json, text/plain, */*',
              'accept-language': 'en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7',
              'content-type': 'application/json',
            },
          }
        )
        .then((res) => {
          const botResponse = res.data // 假设返回的内容是 Markdown 格式的字符串
          console.log(res)
          // 将 Markdown 转换为 HTML
          const htmlContent = marked(botResponse) // 使用 GitHub Flavored Markdown

          // 逐字打印效果
          this.printMessage(htmlContent)

          // 清空输入框
          this.isProcessing_talk = false
        })
        .catch((err) => {
          console.error(err)
          this.messages.push({
            role: '错误',
            content: 'Error: ' + err.message,
            avatar:
              'https://tse3-mm.cn.bing.net/th/id/OIP-C.zr3fan0AC1Sako9iryOQBAHaKd?rs=1&pid=ImgDetMain',
          })
          this.isProcessing_talk = false
        })
    },

    // 逐字打印函数
    printMessage(htmlContent) {
      let index = 0
      const messageLength = htmlContent.length
      let tempContent = ''

      // 替换掉 <br> 为特殊字符或者直接不处理
      htmlContent = htmlContent.replace(/<br\s*\/?>/gi, ' [BR] ') // 将 <br> 替换为一个占位符，避免 DOM 重排

      // 添加一个新的消息框用于显示逐字打印
      this.messages.push({
        role: '管家',
        content: tempContent, // 初始为空
        avatar:
          'https://tse3-mm.cn.bing.net/th/id/OIP-C.zr3fan0AC1Sako9iryOQBAHaKd?rs=1&pid=ImgDetMain',
      })

      const interval = setInterval(() => {
        // 更新临时内容
        tempContent = htmlContent.slice(0, index + 1)

        // 如果遇到占位符 [BR]，则添加换行符
        tempContent = tempContent.replace(/\[BR\]/g, '<br>')

        // 更新消息框中的内容
        this.messages[this.messages.length - 1].content = tempContent

        // 更新进度条
        this.progress = Math.round(((index + 1) / messageLength) * 100)

        index++

        // 当所有字符都已显示，停止定时器
        if (index >= messageLength) {
          clearInterval(interval)
        }
      }, 70) // 每50毫秒更新一次
    },
    //语音识别
    toggleRecognition() {
      if (this.isRecognizing) {
        this.recognition.stop()
      } else {
        this.fullTranscript = '' // 清空之前的文本
        this.recognition.start()
      }
      this.isRecognizing = !this.isRecognizing // 切换状态
    },

    // 将回答可用次数发送服务器
    send_times() {
      this.$axios
        .post('/api/times', {
          times: this.times,
          username: localStorage.getItem('username'),
        })
        .then((res) => {
          console.log(res.data)
        })
        .catch((err) => {
          console.error(err)
        })
    },
  },
}
</script>

<style scoped>
@import url('../style/views/RiskView.css');

/* 进度条样式 */
.progress-container {
  width: 100%;
  height: 10px;
  background-color: #f3f3f3;
  border-radius: 5px;
  margin-top: 10px;
}

.progress-bar {
  height: 100%;
  background-color: #4caf50;
  width: 0;
  transition: width 0.5s ease;
}
</style>
