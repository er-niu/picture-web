# picture-web

图片服务器：Nginx + FastDFS
持久化：mysql
爬虫：python 每天爬取最新的壁纸，上传到图片服务器，保存url到mysql
后台：java 提供http接口访问图片，开发框架spring boot + thymeleaf
前端：H5 bootstrap风格