package nancy.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName FrontInterceptor
 * @Description TODO
 * @Author nancy
 * @Date 2020/11/15 20:15
 * @Version 1.0
 **/
public class FrontInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI() == "/lucky-draw/index.html") {
            System.out.println("放行成功");
            return true;
        }
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            return true;//登录，继续执行Controller中的方法
        }
        response.sendRedirect("index.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
