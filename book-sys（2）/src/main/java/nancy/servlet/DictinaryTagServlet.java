package nancy.servlet;

import nancy.dao.DictionaryTagDao;
import nancy.pojo.DictionaryTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName DictinaryTagServlet
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 10:30
 * @Version 1.0
 **/
@WebServlet("/dict/tag/query")
public class DictinaryTagServlet extends AbstractServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String key = req.getParameter("dictionaryKey");
        List<DictionaryTag> tags = DictionaryTagDao.queryById(key);
        return tags;
    }
}
