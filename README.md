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
生成token
```html
客户端请求 ==> 验证appId、appKey ==> 进入JwtAuthenticationFilter拦截  ==>  进入UserDetailsServiceImpl查询DB ==> 验证appId、appKey ==>  
存在：生成token返回
不存在：错误返回
```
请求天气
```html
客户端请求 ==> 进入JwtAuthorizationFilter拦截 ==> 验证IP ==> 执行AOP ==> 
存在：生成数据返回
不存在：错误返回
```
请求用户
```html
客户端请求 ==> 进入JwtAuthorizationFilter拦截 ==> 进入MyRbacPermission验证token ==> 
存在：生成数据返回
不存在：错误返回
```

AOP:
```html
项目设置AOP，可通过 @CustomVerify 注解方式使用，在方法上填写，默认值为：true
```
Redis:
```html
缓存作用在service层，自定义key，统一定义数据dto，通过键值对map的缓存存储，进行存取转化
```

日志：
```html
日志通过logback管理，将日志写入DB数据库(由官方提供SQL语句执行)
```

sql语句：
```html
语句通过P6Spy管理，将日志写入DB数据库内的自定义表 system_logging_sql，自行执行语句
```

swagger:
```html
项目集成swagger简易测试，访问：http://localhost:8011/spring-boot-api/swagger
配置需要在SwaggerController.java中配置，也可以自行通过数据库去加载
```
