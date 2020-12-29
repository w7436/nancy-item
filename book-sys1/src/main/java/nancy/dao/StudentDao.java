package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Student;
import nancy.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 13:38
 * @Version 1.0
 **/
public class StudentDao {
    public static List<Student> queryAsDict(int key) {
        List<Student> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select s.id,s.student_name,s.id_card,s.student_no" +
                    " from student s join classes c on s.classes_id = c.id" +
                    " where s.classes_id = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setDictionaryTagKey(rs.getString("id"));
                student.setDictionaryTagValue(rs.getString("student_name"));
                student.setIdCard(rs.getString("id_card"));
                student.setStudentNo(rs.getString("student_no"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new SysException("00001",e,"查询数据字段出错");
        } finally {
            DButil.close(c,ps,rs);
        }
        return list;
    }
}
