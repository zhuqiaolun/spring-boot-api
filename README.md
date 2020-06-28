# spring-boot-api
spring-boot集成 - 后台管理

# 应用技术
```html
spring-boot-starter-parent（2.3.0.RELEASE）
spring-boot-starter-security（安全权限）
spring-boot-starter-data-redis（redis缓存）
logback（日志管理）
mybatis-plus-boot-starter（3.3.2）（数据库连接）
mysql-connector-java（5.1.34）（数据库）
p6spy（3.9.0）（执行SQL分析打印）
jjwt（0.9.1）（token生成）
fastjson（1.2.62）（json解析）
httpclient（4.5.12）（https配置）
UserAgentUtils（1.21）（请求解析）
```


# 应用说明
项目有4个请求;
```html
../auth/getToken ：请求获取token，时效7200秒
../test : 测试，此请求过滤权限验证
../weather/** : 应用请求，获取天气数值，权限设置为指定IP以及IP范围，请求时不需要token，可进行or,and添加更多
../user/** : 应用请求，获取用户数值，权限设置为token有效验证，但不限制IP，可进行or,and添加指定IP
```
权限设置需要在WebSecurityConfig.java中设置

# 项目流程
```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
```
