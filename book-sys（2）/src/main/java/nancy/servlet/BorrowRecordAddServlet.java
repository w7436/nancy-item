package nancy.servlet;

import nancy.dao.BorrowRecordDao;
import nancy.exception.BusinessException;
import nancy.pojo.BorrowRecord;
import nancy.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BorrowRecordAddServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 20:09
 * @Version 1.0
 **/
@WebServlet("/borrowRecord/add")
public class BorrowRecordAddServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BorrowRecord borrowRecord = JsonUtil.read(req.getInputStream(), BorrowRecord.class);
        int num = BorrowRecordDao.insert(borrowRecord);
        if (num != 1) {
            throw new BusinessException("0001","插入图书借阅信息异常");
        }
        return null;
    }
}
