# 抽奖系统

### 项目功能

为学校活动进行抽奖活动，提供奖项设置、抽奖人员管理以及抽奖设置

- 用户注册
- 用户登录
- 抽奖设置：抽奖人员、抽奖奖项
- 用户管理

### 项目环境

- windows
- Maven
- jdk1.8
- spring、springBoot、springMVC
- MySQL、MyBatis、Druid、MyBatis-generator

***



#### 1.数据库设计

- award(奖项):  <u>id</u>，name，count，award，setting_id，create_time
- member(抽奖人员)：<u>id</u>，name，no，user_id，create_time
- record(记录)：<u>id</u> ，member_id，award_id，create_time
- setting(抽奖设置)：<u>id</u>，user_id，batch_number(每次抽奖人数)，create_time
- user(用户表)：<u>id</u>，username，password，nickname，email，age，head(头像)，create_time

#### 2.自定义异常类

根据不同的情况，需要进行异常处理的时候，抛出不同的异常

- 客户端请求错误异常：自定义错误码以及错误信息（用户不存在）
- 业务出现异常：自定义错误码，给定错误信息，方便后台错误的定位
- 系统异常：自定义错误码和错误消息

#### 3.统一数据响应

- 进行后端接口的访问统一加上‘/api’前缀，方便管理
- 统一的数据响应格式封装(InitializingBean)

#### 4.拦截器

如果访问后台敏感资源，则重定向到登录界面，同样，对于静态资源路径也是重定向到登录界面



#### springBoot 配置文件：application.properties

#### SysConfig
这个类进行mvc配置,之前以@ControllerAdvice+实现ResponseBodyAdvice接口，
完成统一处理返回数据包装：无法解决返回值为null需要包装
利用MVC配置类中处理，可以解决返回null的情况

在后台机接口路径加上统一的前缀为了方便后面的会话管理，Controller路径

