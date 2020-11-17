package nancy.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import nancy.base.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {


    private ObjectMapper objectMapper;

    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * if(session != null && session.getAttribute("user") != null) return true;===>继续执行
     * else 返回一个带错误码和错误信息的json
     */

    //return true；放行，执行下一个拦截器
    //return false：不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            return true;//登录，继续执行Controller中的方法
        }


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);//浏览器用来解析的数据格式和编码
        response.setCharacterEncoding("UTF-8");//设置响应体编码，和java文件的编码一致

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //前端通过状态码已经处理了，可以不返回响应体内容
        ResponseResult r = ResponseResult.error("SES000", "用户没有登录");
        PrintWriter pw = response.getWriter();
        pw.println(objectMapper.writeValueAsString(r));
        pw.flush();

        return false;
    }


    //处理后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //清理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
