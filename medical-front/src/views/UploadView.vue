<template>
  <frame-work>
    <div class="ocr">
      <h1>病历分析</h1>

      <input type="file" @change="handleImageUpload" accept="image/*" />
      <p>——请上传清晰的病历照片进行文字识别——</p>

      <div v-if="isProcessing">正在识别病历图片...</div>

      <div v-if="recognizedText">
        <h2>识别结果：</h2>
        <pre>{{ recognizedText }}</pre>

        <button v-if="recognizedText && this.times != 0" @click="sendToButler" class="send-button">发送管家</button>

        <div v-if="isProcessing_talk">正在思考（预计需要一分钟）...</div>
      </div>
      <button v-if="this.times == 0" class="warning">你的次数已用完，请充值。</button>

      <div v-if="errorMessage" class="error">
        <pre>{{ errorMessage }}</pre>
      </div>

      <div v-if="htmlContent" class="butler-response">
        <span v-html="displayedContent"></span>
      </div>
    </div>
  </frame-work>
</template>


<script>
import { marked } from 'marked' // 确保正确导入
import FrameWork from '@/components/FrameWork.vue'
import Tesseract from 'tesseract.js'
import axios from 'axios'

export default {
  components: { FrameWork },
  data() {
    return {
      recognizedText: null, // 存储识别结果
      isProcessing: false, // 是否正在识别
      isProcessing_talk: false, // 是否正在进行思考
      errorMessage: null, // 错误信息
      htmlContent: null, // 存储管家返回的HTML响应
      displayedContent: '', // 用来存储逐个字符显示的内容
      times: localStorage.getItem('times'), // 剩余的次数
    }
  },
  mounted() {
    this.send_times() // 将剩余次数发送到服务器
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0]
      if (file) {
        this.recognizedText = null // 清空之前的识别结果
        this.errorMessage = null // 清空之前的错误信息
        this.isProcessing = true // 开始识别

        // 使用 Tesseract 进行 OCR 识别
        this.recognizeText(file)
      }
    },

    // 使用 Tesseract 识别图片中的文本
    recognizeText(image) {
      Tesseract.recognize(
        image,
        'chi_sim', // 使用简体中文语言包
        {
          langPath: '/langs/', // 本地语言包路径
        }
      )
        .then(({ data: { text } }) => {
          this.isProcessing = false
          this.htmlContent = null
          this.recognizedText = text // 保存识别结果
        })
        .catch((error) => {
          this.isProcessing = false
          this.errorMessage = '识别失败，请检查图片质量或重试。' // 显示错误信息
          console.error(error)
        })
    },

    sendToButler() {
      if (this.times == 0) {
        this.errorMessage = '您的次数已用完，请充值。'
        return
      }
      this.times-- // 剩余次数减 1
      localStorage.setItem('times', this.times) // 更新 localStorage
      this.isProcessing_talk = true

      const params = {
        prompt:
          this.recognizedText.replace(/\n/g, '') +
          '。你帮我分析一下，谈谈你的看法，基于大数据进行评估!',
        userId: '#/chat/1729685907501',
        network: true,
        system: '',
        withoutContext: false,
        stream: false,
      }

      // 发送请求给管家API
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
        .then((response) => {
          const botResponse = response.data // 获取管家的响应（假设是 Markdown 格式）
          this.htmlContent = marked(botResponse) // 将 Markdown 转换为 HTML
          this.printMessage(this.htmlContent) // 开始逐个字符显示
          this.isProcessing_talk = false
        })
        .catch((error) => {
          console.error('发送失败:', error)
          this.errorMessage = '发送失败，请稍后重试。' // 显示错误信息
          this.isProcessing_talk = false
        })
    },

    // 逐字显示内容
    printMessage(htmlContent) {
      let index = 0
      const messageLength = htmlContent.length
      this.displayedContent = '' // 初始化时清空显示内容

      const interval = setInterval(() => {
        this.displayedContent = htmlContent.slice(0, index + 1) // 每次显示一个字符
        index++

        // 当所有内容都显示完时，停止定时器
        if (index >= messageLength) {
          clearInterval(interval)
        }
      }, 50) // 每 50 毫秒更新一次（可以调整速度）
    },

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
@import url('../style/views/UploadView.css');
</style>

