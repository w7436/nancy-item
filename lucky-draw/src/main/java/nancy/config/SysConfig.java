package nancy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nancy.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import nancy.config.web.RequestResponseBodyMethodProcessorWrapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 配置统一的后台访问路径的前缀，统一格式返回数据的方式，实现InitializingBean
 */
@Configuration
public class SysConfig implements WebMvcConfigurer, InitializingBean{

    @Resource
    private RequestMappingHandlerAdapter adapter;

    //SpringMVC初始化操作时，就会注册的对象，ObjectMapper类是Jackson库的主要类，提供一些功能将转换成Java对象匹配JSON结构
    @Autowired
    private ObjectMapper objectMapper;



    /**之前以@ControllerAdvice+实现ResponseBodyAdvice接口，完成统一处理返回数据包装：无法解决返回值为null需要包装
     * 1、@RestControllerAdvice或@ControllerAdvice注解可用于扩展Controller层次结构，若想返回json格式数据，直接使用@RestControllerAdvice注解就不需要额外添加@ResponseBody注解。官方API: 1.3.7. Controller Advice.
     * 2、SpringMVC的ResponseBodyAdvice接口可实现自定义返回数据的功能。官方API: 1.1.6. Interception.
     */

    //改用现在这种方式，可以解决返回null包装为自定义类型
    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        for(int i=0; i<handlers.size(); i++){
            HandlerMethodReturnValueHandler handler = handlers.get(i);
            if(handler instanceof RequestResponseBodyMethodProcessor){
                handlers.set(i, new RequestResponseBodyMethodProcessorWrapper(handler));
            }
        }
        adapter.setReturnValueHandlers(handlers);
    }

    //springmvc在初始化的时候会调用这个方法，添加的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(objectMapper))
                .addPathPatterns("/api/**")//**代表多级任意匹配，*代表一级路径匹配
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/register")
                .excludePathPatterns("/api/user/logout");
        //再添加一个前端静态资源请求的拦截器，非登录页面（/*.html 排除/index.html），如果没有登录，重定向到/index.html
    }




    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //Controller路径，统一添加请求的路径前缀，第二个参数，表示是否添加
        //所有Controller请求路径，都要带/api的前缀
        configurer.addPathPrefix("api", c->true);
    }
}


