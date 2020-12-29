package nancy.servlet;

import javafx.util.Pair;
import nancy.dao.BorrowRecordDao;
import nancy.pojo.BorrowRecord;
import nancy.pojo.Page;
import nancy.util.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName BorrowRecordQueryServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 20:08
 * @Version 1.0
 **/
@WebServlet("/borrowRecord/query")
public class BorrowRecordQueryServlet extends AbstractServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page p = Util.parse(req);

        List<BorrowRecord> records = BorrowRecordDao.query(p);
        return records;
    }
}
