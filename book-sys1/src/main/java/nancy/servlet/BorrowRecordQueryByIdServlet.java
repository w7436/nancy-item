package nancy.servlet;

import nancy.dao.BorrowRecordDao;
import nancy.pojo.BorrowRecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName BorrowRecordQueryByIdServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 18:42
 * @Version 1.0
 **/
@WebServlet("/borrowRecord/queryById")
public class BorrowRecordQueryByIdServlet extends AbstractServlet{

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        BorrowRecord borrowRecord = BorrowRecordDao.queryById(id);
        return borrowRecord;
    }
}
