package save.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * JDBC工具类
 *
 */
public class JdbcUtil {

    final String URL = "jdbc:mysql://localhost:3306/booksdatabase";
    final String USERNAME = "root";
    final String PASSWORD = "Jack";
    PreparedStatement ps = null;
    Connection con = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon(HttpServletRequest request) {


        ServletContext application = request.getServletContext();

        Map map = (Map) application.getAttribute("key1");

        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            con = (Connection) it.next();
            boolean flag = (boolean) map.get(con);
            if (flag == true) {
                map.put(con, false);
                break;
            }
        }
        return con;
    }

    public PreparedStatement createStatement(String sql, HttpServletRequest request) {

        try {
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(HttpServletRequest request) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ServletContext application = request.getServletContext();
        Map map = (Map) application.getAttribute("key1");
        map.put(con, true);

    }

    public Connection getCon() {
        try {
            Context context = new InitialContext();
            Context contextNeeded = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource)contextNeeded.lookup("bookConn");
            con = getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return con;
    }

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public PreparedStatement createStatement(String sql) {

        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close() {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
