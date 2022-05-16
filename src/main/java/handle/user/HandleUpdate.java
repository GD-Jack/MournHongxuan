package handle.user;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleUpdate extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String amount = request.getParameter("amount");
        int goods_id = Integer.valueOf(request.getParameter("goods_id"));
        if (amount == null)
            amount = "1";
        int newAmount = 0;
        try {
            newAmount = Integer.parseInt(amount);
            if (newAmount < 0) {
                newAmount = 1;
            }
            //如果用户提交的不是整数
        } catch (NumberFormatException exp) {
            newAmount = 1;
        }
        String flag = request.getParameter("flag");
        //如果用户按了加按钮
        if (flag != null && flag.equals("+"))
            newAmount ++;
        //如果按了减按钮
        else if (flag != null && flag.equals("-"))
            newAmount --;
        LoginValidate.userValidate(request, response);
        JdbcUtil util = new JdbcUtil();
        String sql = "update shoppingform set goods_amount = ? where goods_id = ?";
        PreparedStatement ps = util.createStatement(sql);
        try {
            ps.setInt(1, newAmount);
            ps.setInt(2, goods_id);
            ps.executeUpdate();
            response.sendRedirect("UserLoginValidateServlet?flag=shoppingCar");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
    }
}
