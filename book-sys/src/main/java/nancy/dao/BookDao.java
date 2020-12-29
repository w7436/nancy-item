package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Book;
import nancy.pojo.Classes;
import nancy.util.DButil;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookAsDictDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:03
 * @Version 1.0
 **/

public class BookDao {
    public static List<Book> queryAsDict() {
        List<Book> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select  id, book_name,author,price from book";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                book.setDictionaryTagValue(rs.getString("book_name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getBigDecimal("price"));
                list.add(book);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询图书数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }
}
