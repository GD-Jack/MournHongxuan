package handle.user;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PutGoodsToCar extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        ResultSet rs = null;
        String logname = request.getParameter("logname");
        int book_id = Integer.valueOf(request.getParameter("book_id"));
        LoginValidate.userValidate(request, response);
        try {
            String selectbook = "select book_name, book_price from book where book_id = ?";

            String selectshopping = "select goods_amount from shoppingform where logname = ? and " +
                            "goods_name = (select book_name from book where book_id = ?)";
            String update = "update shoppingform set goods_amount = ? where logname = ? and " +
                            "goods_name = (select book_name from book where book_id = ?)";
            String insert = "insert into shoppingform values(?,?,?,?,?,?)";
            PreparedStatement ps = util.createStatement(selectshopping);
            ps.setString(1, logname);
            ps.setInt(2, book_id);
            rs = ps.executeQuery();
            int result = 0;
            if (rs.next()) {
                int amount = rs.getInt("goods_amount");
                amount ++;
                ps = util.createStatement(update);
                ps.setInt(1, amount);
                ps.setString(2, logname);
                ps.setInt(3, book_id);
                result = ps.executeUpdate();
            } else {
                ps = util.createStatement(selectbook);
                ps.setInt(1, book_id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ps = util.createStatement(insert);
                    ps.setString(1, null);
                    ps.setString(2, logname);
                    ps.setInt(3, book_id);
                    ps.setString(4, rs.getString("book_name"));
                    ps.setFloat(5, rs.getFloat("book_price"));
                    ps.setInt(6, 1);
                    result = ps.executeUpdate();
                }
            }
            if (result == 0) {
                PrintWriter out = response.getWriter();
                out.print("添加购物车失败");
            } else{
                PrintWriter out = response.getWriter();
                out.print("添加购物车成功");
            }
            response.sendRedirect("UserLoginValidateServlet?flag=shoppingCar");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
    }
}
