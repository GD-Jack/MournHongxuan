package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;
import save.data.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LookUserServlet", value = "/LookUserServlet")
public class LookUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtil util = new JdbcUtil();
        String sql = "select * from user";
        LoginValidate.administratorsValidate(request, response);
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        List<User> users = new ArrayList();
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setLogname(rs.getString("logname"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRealname(rs.getString("realname"));
                users.add(user);
            }
            request.setAttribute("users", users);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        request.getRequestDispatcher("administrators_showUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
