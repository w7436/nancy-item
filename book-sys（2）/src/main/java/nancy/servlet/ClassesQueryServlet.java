package nancy.servlet;

import nancy.dao.ClassesDao;
import nancy.pojo.Classes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassesQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 11:02
 * @Version 1.0
 **/

@WebServlet("/classes/queryAsDict")
public class ClassesQueryServlet extends AbstractServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Classes> list = ClassesDao.queryAsDict();
        return list;
    }
}
