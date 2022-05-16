package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdateBookServlet", value = "/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        int result = 0;
        LoginValidate.administratorsValidate(request, response);
        String updatesql = "update book set book_name = ?, book_author = ?, book_translator = ?" +
                ", book_press = ?, book_price = ?, book_num = ?, book_introduction = ? where book_id = ?";
        PreparedStatement ps = util.createStatement(updatesql);
        try {
            ps.setString(1, request.getParameter("book_name"));
            ps.setString(2, request.getParameter("book_author"));
            ps.setString(3, request.getParameter("book_translator"));
            ps.setString(4, request.getParameter("book_press"));
            ps.setFloat(5, Float.valueOf(request.getParameter("book_price")));
            ps.setInt(6, Integer.valueOf(request.getParameter("book_num")));
            ps.setString(7, request.getParameter("book_introduction").trim());
            ps.setInt(8, Integer.valueOf(request.getParameter("book_id")));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        //如果更新失败
        if (result != 0) {

        }
        request.getRequestDispatcher("bookServlet?flag=administrators").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
