package nancy.servlet;

import nancy.dao.UserDao;
import nancy.exception.BusinessException;
import nancy.pojo.User;
import nancy.util.JsonUtil;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName UserLoginServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 14:59
 * @Version 1.0
 **/

@WebServlet("/user/login")
public class UserLoginServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JsonUtil.read(req.getInputStream(),User.class);
        User queryUser = UserDao.query(user);
        if (queryUser == null) {
            throw new BusinessException("0001","用户名密码不存在");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",queryUser);
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
//        resp.setContentType("text/html");//浏览器解析返回的响应体
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        System.out.println("密码：");
//
//        PrintWriter pw = resp.getWriter();
//        pw.println("登录成功");
//        pw.write("登录成功");
//        pw.flush();
//    }
}
