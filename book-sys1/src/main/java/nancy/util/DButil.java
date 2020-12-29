package nancy.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import nancy.exception.SysException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName DButil
 * @Description TODO
 * @Author nancy
 * @Date 2020/12/27 15:48
 * @Version 1.0
 **/
public class DButil {
    private static final String URL = "jdbc:mysql://localhost:3306/book";
    private static final String name = "root";
    private static final String password = "root";
    private static volatile DataSource DATA_SOURCE;

    public DButil() {
    }

    private static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            synchronized (DButil.class) {
                if (DATA_SOURCE == null) {
                    DATA_SOURCE = new MysqlDataSource();
                    ((MysqlDataSource) DATA_SOURCE).setUrl(URL);
                    ((MysqlDataSource) DATA_SOURCE).setUser(name);
                    ((MysqlDataSource) DATA_SOURCE).setPassword(password);
                }
            }
        }
        return DATA_SOURCE;
    }

    public static Connection getConnetion()  {
        try {
            return getDataSource().getConnection();
        } catch (SQLException throwables) {
            throw new SysException("001",throwables,"数据库连接失败");
        }
    }
    public static void close(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null ) r.close();
            if (s != null ) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            throw new SysException("002",e, "释放数据库资源出错");
        }
    }
    public static void close(Connection c, Statement s) {
        close(c, s,null);
    }
}
