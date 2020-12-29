package nancy.servlet;


import nancy.dao.BookDao;
import nancy.pojo.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName BookQueryAsDictServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:01
 * @Version 1.0
 **/

@WebServlet("/book/queryAsDict2")
public class BookQueryAsDictServlet extends AbstractServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> list = BookDao.queryAsDict();
        return list;
    }
}
