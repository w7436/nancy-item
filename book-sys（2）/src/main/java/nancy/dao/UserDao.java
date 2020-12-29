package nancy.dao;

import nancy.exception.SysException;
import nancy.pojo.Classes;
import nancy.pojo.User;
import nancy.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @ClassName UserDao
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/28 19:07
 * @Version 1.0
 **/
public class UserDao {

    public static User query(User user) {
        User u = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DButil.getConnetion();
            String sql = "select id,username, password,nickname from user where username = ? and password = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2,user.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                u = user;
                u.setId(rs.getInt("id"));
                u.setNickname(rs.getString("nickname"));
            }
        } catch (SQLException e) {
            throw new SysException("00002",e,"查询用户失败");
        } finally {
            DButil.close(c,ps,rs);
        }
        return u;
    }
}
