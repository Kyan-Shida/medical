<template>
  <frame-work>
    <img src="../assets/img/1.jpg" alt="健康生活图像">
    <div class="text">
      <h2>我们的使命</h2>
      <p>
        我们的使命是通过大数据技术和互联网平台，推广健康知识，提升公众健康水平。
      </p>

      <h2>健康功能介绍</h2>
      <p>
        我们的平台提供多种健康管理功能，帮助用户更好地了解和管理他们的健康：
      </p>
      <ul>
        <li><strong>知识普及：</strong> 提供最新的健康知识和信息，帮助用户做出明智的健康决策。</li>
        <li><strong>病历分析：</strong> 用户可以在线上传病历图片，方便健康管家进行诊断。</li>
        <li><strong>风险预测：</strong> 通过用户的健康数据，分析潜在的健康风险，并提供预防建议。</li>
      </ul>

      <h2>用户故事</h2>
      <p>我们的用户在使用平台后的反馈：</p>
      <blockquote>
        “通过健康管家平台，我了解了许多以前不知道的健康知识。”<br> — 小邵同学
      </blockquote>
      <blockquote>
        “病历问卷的设计非常简单，医生的反馈也很及时，让我在家就能得到专业的建议。” <br>— 小沙同学
      </blockquote>
    </div>
  </frame-work>
</template>

<script>
import FrameWork from '@/components/FrameWork.vue'
export default {
  components: { FrameWork },
  data() {
    return {}
  },
}
</script>

<style>
@import url('../style/views/HomeView.css');
</style>
