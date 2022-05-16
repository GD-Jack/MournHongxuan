package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        LoginValidate.administratorsValidate(request, response);
        int result = 0;
        String updatesql = "update user set password = ?, phone = ?, address = ?, realname = ? where logname = ?";
        PreparedStatement ps = util.createStatement(updatesql);
        try {
            ps.setString(1, request.getParameter("password"));
            ps.setString(2, request.getParameter("phone"));
            ps.setString(3, request.getParameter("address"));
            ps.setString(4, request.getParameter("realname"));
            ps.setString(5, request.getParameter("logname"));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        //如果更新失败
        if (result != 0) {

        }
        request.getRequestDispatcher("LookUserServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
