package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtil util = new JdbcUtil();
        LoginValidate.administratorsValidate(request, response);
        int result = 0;
        try {
            String sql = "delete from shoppingform where logname = ?";
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1, request.getParameter("logname"));
            result = ps.executeUpdate();
            sql = "delete from orderform where logname = ?";
            ps = util.createStatement(sql);
            ps.setString(1, request.getParameter("logname"));
            result = ps.executeUpdate();
            sql = "delete from user where logname = ?";
            ps = util.createStatement(sql);
            ps.setString(1, request.getParameter("logname"));
            result = ps.executeUpdate();
            //如果更新失败
            if (result != 0) {

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        request.getRequestDispatcher("LookUserServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
