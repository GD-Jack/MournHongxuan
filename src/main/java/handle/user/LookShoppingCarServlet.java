package handle.user;

import save.data.Shoppingform;
import save.filter.LoginValidate;
import save.util.JdbcUtil;
import save.data.Login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LookShoppingCarServlet", value = "/LookShoppingCarServlet")
public class LookShoppingCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        LoginValidate.userValidate(request, response);
        List<Shoppingform> shoppingforms = new ArrayList();
        String sql = "select * from shoppingform where logname = ?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        try {
            HttpSession session = request.getSession();
            Login loginBean = (Login) session.getAttribute("loginBean");
            ps.setString(1, loginBean.getLogname());
            rs = ps.executeQuery();
            while(rs.next()) {
                Shoppingform shoppingform = new Shoppingform();
                shoppingform.setId(rs.getInt("goods_id"));
                shoppingform.setLogname(rs.getString("logname"));
                shoppingform.setName(rs.getString("goods_name"));
                shoppingform.setPrice(rs.getFloat("goods_price"));
                shoppingform.setAmount(rs.getInt("goods_amount"));
                shoppingform.setBook_id(rs.getInt("book_id"));
                shoppingforms.add(shoppingform);
            }
            request.setAttribute("shoppingforms", shoppingforms);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("lookShoppingCar.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
