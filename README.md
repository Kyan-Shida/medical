# 🏥 健康管家平台 Medical

[![Vue](https://img.shields.io/badge/Vue-2.6+-4FC08D?logo=vue.js&logoColor=white)](https://vuejs.org/)
[![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![LangChain](https://img.shields.io/badge/LangChain-0.2.x-1C3C3C?logo=langchain&logoColor=white)](https://www.langchain.com/)
[![GLM-4.7](https://img.shields.io/badge/GLM-4.7-0077FF?logo=zhipuai&logoColor=white)](https://open.bigmodel.cn/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![状态](https://img.shields.io/badge/%E7%8A%B6%E6%80%81-%E8%BF%AD%E4%BB%A3%E5%BC%80%E5%8F%91%E4%B8%AD-orange.svg)

> 
> 📌 **项目定位**：基于大语言模型的全栈智能医疗健康服务平台，融合 RAG 知识增强、病症辅助预测、老年健康监护三大核心能力，打造一站式个人健康管家。

## 📖 项目介绍
![image](logo)

健康管家平台是一款面向个人与社区的医疗健康服务系统，以「科技促进健康」为核心理念，在传统病历管理、病症预测的基础上，全面接入**智谱 GLM-4.7 大模型**，基于 **LangChain 框架**构建 RAG 检索增强引擎，实现智能问诊、风险评估、健康科普、老年监护等全场景健康服务。

平台采用前后端分离架构，前端提供可视化交互与健康数据展示，后端承载业务逻辑与数据管理，独立 AI 引擎模块提供大模型推理与知识库检索能力；支持病历 OCR 识别、多轮健康对话、患病风险评估报告生成等核心功能，同时面向老年群体打造健康手环联动、电子病历二维码、家属/社区医院联网监护等特色服务。

## ✨ 核心特性

### ✅ 已落地能力

- 🤖 **智能医疗对话**
基于 GLM-4.7 大模型 + LangChain 编排，支持多轮健康问诊、症状咨询、医学知识科普，回答专业且贴合日常场景
- 📚 **RAG 知识增强**
内置权威医学知识库，通过向量检索实现精准知识召回，约束大模型输出边界，降低幻觉，提升回答专业度与合规性
- 📋 **病症风险评估**
支持手动输入症状 / 上传病历图片 OCR 识别，结合知识库与匹配算法生成结构化患病风险评估报告
- 👴 **老年健康监护**
对接健康手环数据，生成电子病历二维码，支持家属、社区医院实时查看健康状态，应对突发健康状况
- 🎨 **可视化健康面板**
基于 ECharts 实现健康数据趋势、风险等级可视化，报告清晰直观
- 🛡️ **工业级系统架构**
前后端分离设计，JWT 身份认证，数据脱敏存储，完善的异常处理与日志体系

### 🚧 迭代规划

- 🔄 基于 LangChain 重构 AI 全链路，优化 RAG 检索与多轮对话体验
- 📊 新增个人健康档案持久化，支持历史健康趋势分析
- 🔗 对接更多智能穿戴设备，扩展老年监护预警场景
- 📱 移动端 H5 适配，规划微信小程序端开发
- 🏥 接入医院预约挂号、医生在线咨询业务通道
- 🧪 完善单元测试与集成测试，提升系统稳定性

## 🏗️ 系统架构

平台采用「前端展示层 - 业务服务层 - AI 引擎层 - 数据存储层」四层分离架构，各模块职责清晰，便于独立迭代与维护。

```
┌─────────────────────────────────────────────────────────────────┐
│                        前端展示层 (medical-front)                │
│  首页 | 病历上传 | 风险报告 | 智能问诊 | 健康科普 | 老年监护        │
└───────────────────────────────┬─────────────────────────────────┘
                                │ HTTP/RESTful
┌───────────────────────────────▼─────────────────────────────────┐
│                        业务服务层 (medical-back)                 │
│  用户管理 | 病历管理 | 医院服务 | 预约管理 | 健康数据 | 鉴权        │
└───────────────────────────────┬─────────────────────────────────┘
                                │ 内部 API 调用
┌───────────────────────────────▼─────────────────────────────────┐
│                         AI 引擎层 (medical-ai)                   │
│  LangChain 编排 | GLM-4.7 推理 | RAG 检索 | 意图识别 | OCR        │
└───────────────────────────────┬─────────────────────────────────┘
                                │
┌───────────────────────────────▼─────────────────────────────────┐
│                        数据存储层                                │
│  MySQL(业务数据) | FAISS(向量库) | 文件存储(病历/知识库文档)       │
└─────────────────────────────────────────────────────────────────┘
```

## 🛠️ 技术栈

### 前端技术栈

| 分类 | 技术选型 | 说明 |
| --- | --- | --- |
| 核心框架 | Vue 2.6 + Vue Router | 单页应用路由与组件化开发 |
| UI 组件 | Element UI | 通用业务组件库 |
| 网络请求 | Axios | 后端接口对接 |
| 数据可视化 | ECharts 5.x | 健康报告、风险趋势图表渲染 |
| 特色能力 | tesseract.js | 前端病历图片 OCR 文字识别 |
| 内容渲染 | marked | 医学科普文章、AI 回答 Markdown 渲染 |

### 后端技术栈

| 分类 | 技术选型 | 说明 |
| --- | --- | --- |
| 核心框架 | Spring Boot 3.x + Java 17 | 业务服务核心框架 |
| 持久层 | MyBatis + MySQL | 关系型数据存储与操作 |
| 认证鉴权 | JJWT | Token 式身份认证 |
| 工具组件 | Lombok + FastJSON + Jackson | 实体简化、JSON 序列化 |

### AI 引擎技术栈（LangChain + RAG + GLM-4.7）

| 分类 | 技术选型 | 说明 |
| --- | --- | --- |
| 编排框架 | LangChain 0.2.x | 大模型应用编排、链路管理、工具调用 |
| 基座大模型 | 智谱 GLM-4.7 | 核心对话推理、报告生成能力 |
| 向量嵌入 | 智谱 Embedding-2 | 文本向量化表征 |
| 向量数据库 | FAISS | 本地向量检索，知识库精准召回 |
| 文档处理 | PyPDF2 + python-docx | PDF/Word 格式知识库解析与切片 |
| 服务框架 | FastAPI | AI 能力对外提供标准化 HTTP 接口 |

## 📁 项目结构

```
medical/
├── medical-front/              # 前端工程（Vue）
│   ├── src/
│   │   ├── views/              # 业务页面
│   │   ├── components/         # 公共复用组件
│   │   ├── api/                # 后端接口请求封装
│   │   ├── router/             # 路由配置与权限控制
│   │   └── assets/             # 静态资源与全局样式
│   └── package.json
│
├── medical-back/               # 后端工程（Java Spring Boot）
│   ├── src/main/java/
│   │   ├── controller/         # 接口控制层
│   │   ├── service/            # 核心业务逻辑层
│   │   ├── mapper/             # 数据持久层
│   │   ├── pojo/               # 实体、DTO、VO 数据模型
│   │   ├── utils/              # 通用工具类
│   │   └── config/             # 系统配置类
│   └── pom.xml
│
├── medical-ai/                 # AI 引擎工程（Python，持续完善中）
│   ├── app/
│   │   ├── chain/              # LangChain 链定义（对话链、检索增强链）
│   │   ├── rag/                # RAG 模块：文档加载、切片、向量库、检索器
│   │   ├── llm/                # GLM-4.7 模型封装与调用
│   │   ├── prompt/             # 领域 Prompt 模板
│   │   └── api.py              # FastAPI 对外接口
│   ├── knowledge_base/         # 医学知识库源文件
│   ├── requirements.txt
│   └── .env.example
│
├── assets/                     # 文档配图
├── README.md                   # 项目说明文档
├── README.en.md                # 英文说明文档
└── .gitignore
```

## 🚀 快速开始

### 环境要求

- **前端**：Node.js 16+、npm 8+
- **后端**：JDK 17+、Maven 3.8+、MySQL 8.0+
- **AI 引擎**：Python 3.10+、智谱 AI API Key

### 1. 克隆项目

```
git clone https://github.com/Kyan-Shida/medical.git
cd medical
```

### 2. 启动后端服务

1. 创建 MySQL 数据库，执行 `medical-back` 中的初始化 SQL 脚本
2. 修改 `application.yml` 中的数据库连接信息
3. 启动 Spring Boot 服务

```
cd medical-back
mvn clean install
mvn spring-boot:run
```

后端服务默认启动在 `http://localhost:8080`

### 3. 启动前端服务

```
cd medical-front
npm install
npm run serve
```

前端页面默认访问地址：`http://localhost:8081`

### 4. 启动 AI 引擎服务（可选）

> 
> 启用智能问诊、RAG 增强回答需要启动此服务

```
cd medical-ai
pip install -r requirements.txt
cp .env.example .env
# 编辑 .env，填入智谱 AI API_KEY
uvicorn app.api:app --reload --port 8000
```

AI 接口文档地址：`http://localhost:8000/docs`

## 🧩 核心功能说明

### 1. 智能问诊对话

基于 LangChain 构建对话链，接入 GLM-4.7 大模型，结合 RAG 检索医学知识库，支持多轮健康咨询、症状主动追问；回答附带医学依据来源，同时内置安全过滤规则，违规与超范围问题自动拦截。

### 2. 病症风险评估

- 支持手动勾选症状、描述病情，或上传病历/检验单图片通过 OCR 自动提取关键信息
- 后端结合知识库与症状匹配规则计算患病概率，AI 引擎生成结构化评估报告与就医建议
- 报告支持导出、保存至个人健康档案

### 3. 老年健康监护

- 对接健康手环数据，同步心率、步数、睡眠等基础健康指标
- 生成个人电子病历二维码，突发情况扫码即可查看关键健康信息与既往病史
- 支持家属账号、社区医院账号绑定，异常健康数据自动预警

### 4. 医学知识科普

平台内置医学科普文章库，结合 AI 能力支持按病症、人群分类检索，普及健康常识与疾病预防知识。

## ⚙️ 核心配置说明

### AI 引擎环境变量（.env）

```
# 智谱 AI 配置
ZHIPU_API_KEY=your_api_key_here
GLM_MODEL=glm-4.7
EMBEDDING_MODEL=embedding-2

# 向量库配置
VECTOR_STORE_PATH=./vector_store
FAISS_INDEX_NAME=medical_knowledge

# 服务配置
AI_SERVER_PORT=8000
LOG_LEVEL=INFO
```

### 后端核心配置（application.yml）

```
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/medical?useUnicode=true&characterEncoding=utf8
    username: root
    password: your_password
mybatis:
  mapper-locations: classpath:mapper/*.xml
jwt:
  secret: your_jwt_secret
```

## 🗺️ 版本路线图

### v1.1.0（开发中）

- 完成 LangChain + GLM-4.7 AI 引擎接入
- 实现 RAG 医学知识库检索问答
- 前端新增智能问诊对话页面
- 优化病历 OCR 识别准确率

### v1.2.0（规划中）

- 个人健康档案体系落地
- 健康手环数据对接与可视化
- 电子病历二维码生成与分享
- 多角色权限体系（用户/家属/医生/管理员）

### v2.0.0（远期规划）

- 社区医院联动管理后台
- 在线问诊与预约挂号打通
- 移动端小程序版本
- 更多 AI 健康管理能力

## 🤝 参与贡献

欢迎提交 Issue 和 Pull Request 参与项目共建：

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 **MIT 许可证**，详见 [LICENSE](LICENSE) 文件。

> 
> ⚠️ **免责声明**：本平台所有 AI 生成内容、病症评估结果仅作健康参考，不替代专业医生诊断与治疗方案，身体不适请及时就医。

## 📬 联系方式

- 项目地址：[Kyan-Shida/medical](https://github.com/Kyan-Shida/medical)
- 问题反馈：[Issues](https://github.com/Kyan-Shida/medical/issues)
- 开发者：Kyan-Shida

---

**🏥 健康管家 - 用科技守护每一份健康**

项目持续迭代中，欢迎 Star ⭐ 关注更新！

[⬆️ 返回顶部](#-%E5%81%A5%E5%BA%B7%E7%AE%A1%E5%AE%B6%E5%B9%B3%E5%8F%B0-medical)
