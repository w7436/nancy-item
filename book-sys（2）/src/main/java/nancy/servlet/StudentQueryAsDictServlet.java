package nancy.servlet;

import nancy.dao.StudentDao;
import nancy.pojo.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName StudentQueryAsDictServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:37
 * @Version 1.0
 **/

@WebServlet("/student/queryAsDict")
public class StudentQueryAsDictServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("dictionaryKey"));
        List<Student> list = StudentDao.queryAsDict(id);
        return  list;
    }
}
