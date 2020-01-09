#学习社区

##文档

[Spring 文档](https://spring.io/guides/gs/serving-web-content/)

[BootStrap文档](https://v3.bootcss.com/components/#navbar)

[Github OAuth 文档](https://developer.github.com/apps/building-oauth-apps/)

[lombok自动生成get，set等方法](https://projectlombok.org)
[Spring Developer Tools](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)
[thymeleaf官方文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

##工具

[OKHttp 做服务器请求](https://square.github.io/okhttp/)

[Maven仓库下载fastjson](https://mvnrepository.com/artifact/com.alibaba/fastjson)

[Springboot 数据库连接](https://docs.spring.io/spring-boot/docs/2.2.0.RC1/reference/htmlsingle/)

[mybatis的快速链接](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

[flyway做数据库迁移](https://flywaydb.org/getstarted/firststeps/maven)

##项目说明

controller      #处理页面信息
dto             #数据传输对象，相当于实体类，存储对象的类
mapper          #对数据库进行操作
provider        #处理get，put请求
model           #数据库中的表对象,跟数据库关联
service         #组装层，可以管理mapper层多个对象，当需要组装user，question时就要用到service层

##脚本
```sql
CREATE TABLE user(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  account_id VARCHAR(100) not NULL ,
  nsame VARCHAR(50),
  tokon char(36),
  gmt_create BIGINT,
  gmt_modified BIGINT
)
```
```
mvn flyway:migrate
```

