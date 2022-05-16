package handle.user;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "DeleteOrderServlet", value = "/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int orderform_id = Integer.valueOf(request.getParameter("order_id"));
        String sql = "delete from orderform where order_id = ?";
        JdbcUtil util = new JdbcUtil();
        LoginValidate.userValidate(request, response);
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setInt(1, orderform_id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        //用户删除订单
        if (request.getParameter("flag").equals("administrators"))
            response.sendRedirect("LoginValidateServlet?flag=lookorder");
        //管理员删除订单
        else if (request.getParameter("flag").equals("user"))
            response.sendRedirect("UserLoginValidateServlet?flag=order");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
