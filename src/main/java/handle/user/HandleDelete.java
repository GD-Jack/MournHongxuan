package handle.user;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleDelete extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        LoginValidate.userValidate(request, response);
        JdbcUtil util = new JdbcUtil();
        String sql = "";
        int goods_id;
        String logname;
        try {
            if (request.getParameter("flag").equals("all")) {
                logname = request.getParameter("logname");
                sql = "delete from shoppingform where logname = ?";
                PreparedStatement ps = util.createStatement(sql);
                ps.setString(1, logname);
                ps.executeUpdate();
            } else {
                goods_id = Integer.valueOf(request.getParameter("goods_id"));
                sql = "delete from shoppingform where goods_id = ?";
                PreparedStatement ps = util.createStatement(sql);
                ps.setInt(1, goods_id);
                ps.executeUpdate();
            }
            response.sendRedirect("UserLoginValidateServlet?flag=shoppingCar");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
    }
}
