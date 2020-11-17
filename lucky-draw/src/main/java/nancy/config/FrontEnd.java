package nancy.config;

import nancy.config.interceptor.FrontInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName FrontEnd
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/15 19:51
 * @Version 1.0
 **/
@Configuration
public class FrontEnd  implements WebMvcConfigurer, InitializingBean {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FrontInterceptor())
                .addPathPatterns("/*.html")
                .excludePathPatterns("/index.html");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
