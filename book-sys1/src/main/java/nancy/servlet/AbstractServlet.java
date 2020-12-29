package nancy.servlet;

import nancy.exception.BaseException;
import nancy.pojo.ResponseResult;
import nancy.util.JsonUtil;
import nancy.util.countHolder;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName AbstractServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 16:50
 * @Version 1.0
 **/
public abstract class AbstractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//针对请求题设置编码，对url中的请求数据没有效果
        resp.setCharacterEncoding("utf-8");//针对响应体设置编码
        resp.setContentType("application/json");//浏览器解析返回的响应体

        ResponseResult r = new ResponseResult();
        try {
            Object data = process(req, resp);
            r.setSuccess(true);
            r.setCode("200");
            r.setMessage("操作成功");
            r.setData(data);
            r.setTotal(countHolder.get());//
        } catch (Exception e) {
            if (e instanceof BaseException) {
                BaseException be = (BaseException) e;
                r.setCode(be.getCode());
                r.setMessage(be.getMessage());

            }else {
                r.setCode("500");
                r.setMessage("未知错误");
            }

            //设置堆栈信息
            StringWriter stringWriter = new StringWriter();
            PrintWriter pw = new PrintWriter(stringWriter);
            e.printStackTrace(pw);
            r.setStackTrace(stringWriter.toString());
        }finally {
            countHolder.remove();//线程结束前要remove删除，否则会引起内存泄漏
        }

        PrintWriter pw = resp.getWriter();
        pw.println(JsonUtil.write(r));
        pw.flush();
    }


    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
