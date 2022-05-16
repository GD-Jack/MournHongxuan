package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddBookServlet", value = "/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sql = "insert into book value(null, ?, ?, ?, ?, ?, ?, ?)";
        JdbcUtil util = new JdbcUtil();
        LoginValidate.administratorsValidate(request, response);
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1, request.getParameter("name"));
            ps.setString(2, request.getParameter("author"));
            ps.setString(3, request.getParameter("translator"));
            ps.setString(4, request.getParameter("press"));
            ps.setFloat(5, Float.valueOf(request.getParameter("price")));
            ps.setInt(6, Integer.valueOf(request.getParameter("num")));
            ps.setString(7, request.getParameter("introduction"));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        //如果添加失败，后续更新添加
        if (result != 0) {

        }
        request.getRequestDispatcher("bookServlet?flag=administrators").forward(request, response);
    }

}
