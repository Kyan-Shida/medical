<template>
  <frame-work>
    <div class="education">
      <h1>健康知识普及</h1>
      <p>在这里，您可以找到关于健康的各种知识和资源，帮助您了解和维护自己的健康。</p>

      <!-- 搜索框和分类选择 -->
      <div class="search-category">
        <el-input v-model="searchQuery" placeholder="请输入搜索关键词" @input="fetchArticles" style="width: 300px; margin-right: 10px;"></el-input>
        <el-select v-model="selectedCategory" placeholder="选择分类" @change="fetchArticles" style="width: 200px;">
          <el-option label="全部" value=""></el-option>
          <el-option label="运动" value="运动"></el-option>
          <el-option label="心理健康" value="心理健康"></el-option>
          <el-option label="饮食" value="饮食"></el-option>
          <!-- 你可以根据实际需要继续添加更多分类 -->
        </el-select>
      </div>

      <div class="article-display">
        <h2>相关文章</h2>
        <div v-for="article in articles" :key="article.id" class="article-card">
          <div class="article-content">
            <!-- 显示文章图片 -->
            <img v-if="article.imageUrl" :src="article.imageUrl" alt="文章图片" class="article-image" />
            <div class="article-text">
              <h3>{{ article.title }}</h3>
              <p>{{ article.content }}</p>
            </div>
          </div>
        </div>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="now_page" :page-sizes="[5, 10, 15, 20]"
                       :page-size="5" layout="total, sizes, prev, pager, next" :total="article_count">
        </el-pagination>
      </div>
    </div>
  </frame-work>
</template>

<script>
import FrameWork from '@/components/FrameWork.vue'

export default {
  components: { FrameWork },
  name: 'EducationView',
  data() {
    return {
      articles: [],
      article_count: 0, // 总文章数量
      now_page: 1, // 当前页
      page_size: 5, // 每页条数
      searchQuery: '', // 搜索关键词
      selectedCategory: '', // 选择的分类
    }
  },
  mounted() {
    this.fetchArticles()
  },
  methods: {
    async fetchArticles() {
      try {
        const response = await this.$axios.get('/api/articles', {
          params: {
            now_page: this.now_page, // 当前页
            page_size: this.page_size, // 每页条数
            search_query: this.searchQuery, // 搜索关键词
            category: this.selectedCategory, // 分类
          },
        })
        console.log(this.searchQuery, this.selectedCategory)
        console.log('EducationView:', response.data.data)
        if (response.data.id === 1) {
          this.articles = response.data.data.articles // 返回的数据结构与articles 数组相同
          this.article_count = response.data.data.total
        } else {
          console.log('EducationView:登录失效，请重新登录,才能重新向后端请求')
          alert('登录失效，请重新登录,才能重新向后端请求')
          this.router.push({ name: 'login' })
        }
      } catch (error) {
        console.error('EducationView:获取文章失败:', error)
      }
    },
    handleSizeChange(val) {
      this.page_size = val
      this.fetchArticles()
    },
    handleCurrentChange(val) {
      this.now_page = val
      this.fetchArticles()
    },
  },
}
</script>

<style scoped>
@import url('../style/views/EducationView.css');
</style>
