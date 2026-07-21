# 个人求职助手

个人求职助手是一个本地运行的求职进度管理系统，用于管理岗位投递、招聘流程、简历版本、面试记录、笔记资料和提醒事项。

项目适用于秋招、春招、实习、提前批、社招、国企、银行、研究所等投递场景。

## 技术栈

- 后端：Java 17、Spring Boot 3、MyBatis Plus、MySQL 8
- 前端：Vue 3、TypeScript、Vite、Element Plus、ECharts
- 文件：简历、面试笔记、普通笔记保存到本地文件系统，数据库保存元信息

## 目录结构

```text
backend/       Spring Boot 后端
frontend/      Vue 3 前端
database/      MySQL 建表和迁移脚本
data/          默认本地文件目录
```

## 核心功能

- Dashboard：统计投递数量、面试数量、Offer 数量、状态分布、趋势图、最近投递和今日提醒。
- 投递管理：岗位 CRUD、绑定简历、岗位类别/简历类别多选和自定义、投递批次/状态自定义、查询、排序、按公司合并。
- 投递详情：Markdown JD 预览、自定义招聘流程、当前进度/成功失败/操作时间、面试记录、Markdown 面试笔记。
- 简历管理：上传、下载、编辑版本信息、PDF 预览、删除。
- 笔记管理：独立普通笔记区，支持文件夹、拖拽移动、移出根目录、上传 Markdown/TXT/PDF/Word、预览、编辑可编辑文本、下载。
- 提醒管理：新增、编辑、完成、删除提醒，完成/未完成高亮，全局未完成预警。
- 系统设置：配置简历、面试笔记、普通笔记的本地保存目录。

## 数据库

首次运行执行：

```sql
source database/schema.sql;
```

## 本地配置

复制 Spring Boot 配置模板，并填写本机 MySQL 密码：

```powershell
Copy-Item backend\src\main\resources\application.example.yml backend\src\main\resources\application.yml
```

`application.yml` 包含本机数据库密码，不要提交到 GitHub；项目已在 `.gitignore` 中忽略该文件。提交到仓库的只有 `application.example.yml` 模板。

## 启动后端

先确认 MySQL 已启动，并且 `application.yml` 中的 MySQL 账号密码正确。

```powershell
cd <PROJECT_ROOT>\backend
mvn spring-boot:run
```

后端默认地址：

```text
http://localhost:8080
```

## 启动前端

```powershell
cd <PROJECT_ROOT>\frontend
npm.cmd install
npm.cmd run dev
```

前端默认地址：

```text
http://localhost:5173
```

## Docker 部署

项目已提供 Docker 部署文件：

```text
backend/Dockerfile
frontend/Dockerfile
frontend/nginx.conf
docker-compose.yml
```

首次启动前在项目根目录手动创建本地 `.env` 文件，至少填写 MySQL root 密码：

```env
MYSQL_ROOT_PASSWORD=your-local-mysql-password
```

`.env` 是本机配置文件，不要提交到 GitHub。

启动服务：

```bash
docker compose up -d --build
```

访问地址：

```text
http://localhost:5173
```

停止服务：

```bash
docker compose down
```

注意事项：

- 默认前端端口为 `5173`，可通过 `FRONTEND_PORT` 修改。
- 如需暴露 MySQL 端口，可在 `docker-compose.yml` 中开启 `MYSQL_PORT` 端口映射。
- `database/schema.sql` 只会在 MySQL 数据卷首次初始化时执行；已有数据卷需要手动执行 `database/` 下的迁移脚本。
- 简历、面试笔记、普通笔记保存到 Docker volume `app_data`，MySQL 数据保存到 `mysql_data`。

## 说明

本项目是前后端分离的 Web 版本，默认使用 MySQL。桌面客户端版本位于独立复制项目 `JobTrackerDesktop`，其打包逻辑、Electron 配置、H2 桌面配置没有同步到本项目。

文档中的 `<PROJECT_ROOT>` 表示本项目根目录。请确保 Maven 已加入系统 PATH，或在本机终端中使用自己的 Maven 绝对路径。
