package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Classes;
import nancy.pojo.DictionaryTag;
import nancy.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClassesDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 11:04
 * @Version 1.0
 **/
public class ClassesDao {
    public static List<Classes> queryAsDict() {
        List<Classes> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select id, classes_name, classes_graduate_year, classes_major from classes";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Classes classes = new Classes();
                classes.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                classes.setDictionaryTagValue(rs.getString("classes_name"));
                classes.setClassesGraduateYear(rs.getString("classes_graduate_year"));
                classes.setClassesMajor(rs.getString("classes_major"));

                list.add(classes);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询班级数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }
}
