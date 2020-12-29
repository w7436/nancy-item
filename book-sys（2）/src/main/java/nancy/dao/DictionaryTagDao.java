package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.DictionaryTag;
import nancy.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DictionaryTagDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 10:32
 * @Version 1.0
 **/
public class DictionaryTagDao {

    public static List<DictionaryTag> queryById(String key) {
        List<DictionaryTag> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select concat(d.dictionary_key, dt.dictionary_tag_key) dictionary_tag_key, dt.dictionary_tag_value " +
                    "from dictionary d " +
                    "         join dictionary_tag dt on d.id = dt.dictionary_id " +
                    "where d.dictionary_key = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                DictionaryTag tag = new DictionaryTag();
                tag.setDictionaryTagKey(rs.getString("dictionary_tag_key"));
                tag.setDictionaryTagValue(rs.getString("dictionary_tag_value"));
                list.add(tag);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }
}
