<template>
  <frame-work>
    <div class="hospital-info">
      <h1>地区医院信息</h1>
      <p>在这里，您可以根据医院名称、医院类型和地区筛选医院。</p>

      <div class="search-category">
        <el-select v-model="selectedRegion" placeholder="选择地区" @change="fetchHospitals" style="width: 200px;">
          <el-option label="全部" value=""></el-option>
          <el-option label="北京" value="北京"></el-option>
          <el-option label="上海" value="上海"></el-option>
          <el-option label="江苏" value="江苏"></el-option>
          <el-option label="黑龙江" value="黑龙江"></el-option>
          <el-option label="甘肃" value="甘肃"></el-option>

        </el-select>
      </div>

      <div class="hospital-display">
        <h2>医院列表</h2>
        <div v-for="(hospital) in hospitals" :key="hospital.id" class="hospital-card">
          <div class="hospital-content">
            <h3>{{ hospital.name }}</h3>
            <p><strong>医院类型：</strong>{{ hospital.type }}</p>
            <p><strong>医院优势：</strong>{{ hospital.advantages }}</p>
            <p><strong>医院地址：</strong>{{ hospital.address }}</p>
            <p><strong>医院链接：</strong><a :href="hospital.link" target="_blank">{{ hospital.link }}</a></p>
          </div>
        </div>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="now_page" :page-sizes="[5, 10, 15, 20]"
                       :page-size="5" layout="total, sizes, prev, pager, next" :total="hospital_count">
        </el-pagination>
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
      selectedRegion: '', // 选择的地区
      now_page: 1, // 当前页码
      page_size: 5, // 每页显示的医院数量
      hospitals: [], // 存储医院数据
      hospital_count: 0, // 总医院数量
    }
  },
  methods: {
    async fetchHospitals() {
      try {
        const params = {
          region: this.selectedRegion, // 地区
          page: this.now_page, // 当前页
          size: this.page_size, // 每页医院数量
        }

        const response = await this.$axios.get('/api/hospital', { params })
        console.log('结果：', response.data.data)

        this.hospitals = response.data.data.hospitals
        this.hospital_count = response.data.data.totalCount
      } catch (error) {
        console.error('获取医院信息失败:', error)
        alert('登录失效，请重新登录,才能重新向后端请求')
        this.router.push({ name: 'login' })
      }
    },
    // all() {
    //   this.$axios
    //     .get('/api/all')
    //     .then((res) => {
    //       console.log(res)
    //     })
    //     .catch((err) => {
    //       console.error(err)
    //     })
    // },

    // 分页处理函数
    handleSizeChange(size) {
      this.page_size = size // 更新每页显示数量
      this.fetchHospitals() // 重新请求数据
    },

    handleCurrentChange(page) {
      this.now_page = page // 更新当前页码
      this.fetchHospitals() // 重新请求数据
    },
  },

  mounted() {
    this.fetchHospitals()
    // this.all()
  },
}
</script>


<style scoped>
@import url('../style/views/HosptialView.css');
</style>
