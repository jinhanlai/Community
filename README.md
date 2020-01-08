#学习社区

##文档

[Spring 文档](https://spring.io/guides/gs/serving-web-content/)

[BootStrap文档](https://v3.bootcss.com/components/#navbar)

[Github OAuth 文档](https://developer.github.com/apps/building-oauth-apps/)

[OKHttp 做服务器请求](https://square.github.io/okhttp/)

[Maven仓库下载fastjson](https://mvnrepository.com/artifact/com.alibaba/fastjson)

[Springboot 数据库连接](https://docs.spring.io/spring-boot/docs/2.2.0.RC1/reference/htmlsingle/)

[mybatis的快速链接](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)


##项目说明
controller      #处理页面信息
dto             #数据传输对象，相当于实体类，存储对象的类
mapper          #数据库操作
provider        #处理get，put请求
model           #数据库中的表对象

##脚本
```sql
CREATE TABLE user
(
  account_id   VARCHAR(100) NOT NULL,
  name         VARCHAR(50)  NULL,
  token        CHAR(36)     NULL,
  gmt_create   BIGINT       NULL,
  gmt_modified BIGINT       NULL,
  id           INT(255) AUTO_INCREMENT
    PRIMARY KEY
);
```

