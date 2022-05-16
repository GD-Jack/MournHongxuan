package handle.user;

import save.data.Orderform;
import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LookOrderServlet", value = "/LookOrderServlet")
public class LookOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        List<Orderform> orderforms = new ArrayList();
        if (request.getParameter("flag").equals("user"))
            LoginValidate.userValidate(request, response);
        else if (request.getParameter("flag").equals("administrators"))
            LoginValidate.administratorsValidate(request, response);
        String sql = "select * from orderform where logname = ?";
        //管理员可以查看所有订单
        if (request.getParameter("flag").equals("administrators")) {
            sql = "select * from orderform";
        }
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        try {
            if (request.getParameter("flag").equals("user"))
                ps.setString(1, request.getParameter("logname"));
            rs = ps.executeQuery();
            while(rs.next()) {
                Orderform orderform = new Orderform();
                orderform.setId(rs.getInt("order_id"));
                orderform.setLogname(rs.getString("logname"));
                orderform.setTitle(rs.getString("order_title"));
                orderform.setMessage(rs.getString("order_message"));
                orderforms.add(orderform);
            }
            request.setAttribute("orderforms", orderforms);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        if (request.getParameter("flag").equals("user"))
            request.getRequestDispatcher("lookOrderForm.jsp").forward(request, response);
        else if (request.getParameter("flag").equals("administrators"))
            request.getRequestDispatcher("administrators_lookOrderForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
