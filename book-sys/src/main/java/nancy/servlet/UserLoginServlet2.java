package nancy.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import nancy.exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @ClassName UserLoginServlet2
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 15:31
 * @Version 1.0
 **/
@WebServlet("/user/login2")
public class UserLoginServlet2 extends AbstractServlet {


    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");//针对请求题设置编码，对url中的请求数据没有效果
//        resp.setCharacterEncoding("utf-8");//针对响应体设置编码
//        resp.setContentType("application/json");//浏览器解析返回的响应体
//
//        HashMap map = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);
//        System.out.println(map);
//        HashMap<String, Object> r = new HashMap<>();
//        r.put("ress",true);
//        r.put("code",200);
//        PrintWriter pw = resp.getWriter();
//        pw.println(new ObjectMapper().writeValueAsString(r));
//
//
//        pw.flush();
//    }
}
