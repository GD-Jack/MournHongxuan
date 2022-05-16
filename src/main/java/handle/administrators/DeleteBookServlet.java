package handle.administrators;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "DeleteBookServlet", value = "/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        int id = Integer.valueOf(request.getParameter("book_id"));
        String sql = "delete from shoppingform where book_id = ?";
        LoginValidate.administratorsValidate(request, response);
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            ps.setInt(1, id);
            result = ps.executeUpdate();
            sql = "delete from book where book_id = ?";
            ps = util.createStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        //如果删除失败，后续更新添加
        if (result != 0) {

        }
        if (request.getParameter("flag").equals("search"))
            request.getRequestDispatcher("searchBook.jsp").forward(request, response);
        else if(request.getParameter("flag").equals("look"))
            request.getRequestDispatcher("bookServlet?flag=administrators").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
