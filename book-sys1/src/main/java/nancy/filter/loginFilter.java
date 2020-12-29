package nancy.filter;

import nancy.pojo.ResponseResult;
import nancy.pojo.User;
import nancy.util.JsonUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName loginFilter
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 19:37
 * @Version 1.0
 **/

@WebFilter("/*")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getServletPath();
        HttpSession session = req.getSession(false);
        if(session == null) {//，没有登录
            //访问首页 /public/page/main.html，没有登录重定向到登陆页面
            if ("/public/page/main.html".equals(uri)) {
                String schema = req.getScheme();//http
                String host = req.getServerName();//服务器ip或域名
                int port = req.getServerPort();//服务器端口号
                String contextPath = req.getContextPath();
                String basePath = schema + "://" + host + ":" + port + contextPath;
                res.sendRedirect(basePath + "/public/index.html");
                return;
                //访问后台的敏感资源，servlet的敏感服务，返回json数据
            } else if (!"/login.html".equals(uri) && !uri.startsWith("/public/") && !uri.startsWith("/static/")
                    && !"/user/login".equals(uri)) {//访问敏感资源
                req.setCharacterEncoding("UTF-8");//针对请求体设置编码，注意对url中的请求数据无效
                res.setCharacterEncoding("UTF-8");//针对响应体设置编码
                res.setContentType("application/json");//设置响应的数据格式：响应头Content-Type告诉浏览器怎么解析
                ResponseResult r = new ResponseResult();
                r.setCode("301");
                r.setMessage("未被授权的访问");
                PrintWriter pw = res.getWriter();
                pw.println(JsonUtil.write(r));
                pw.flush();
                return;
            }
        }
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
