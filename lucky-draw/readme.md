#### springBoot 配置文件：application.properties

#### SysConfig
这个类进行mvc配置,之前以@ControllerAdvice+实现ResponseBodyAdvice接口，
完成统一处理返回数据包装：无法解决返回值为null需要包装
利用MVC配置类中处理，可以解决返回null的情况

在后台机接口路径加上统一的前缀为了方便后面的会话管理，Controller路径

