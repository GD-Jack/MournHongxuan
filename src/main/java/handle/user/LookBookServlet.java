package handle.user;

import save.data.Book;
import save.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LookBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<Book> books = new ArrayList();
        String sql = "select * from book";
        JdbcUtil util = new JdbcUtil();
        ResultSet rs = null;
        PreparedStatement ps = util.createStatement(sql);
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getString("book_name"));
                book.setAuthor(rs.getString("book_author"));
                book.setTranslator(rs.getString("book_translator"));
                book.setPress(rs.getString("book_press"));
                book.setPrice(rs.getFloat("book_price"));
                book.setNum(rs.getInt("book_num"));
                book.setIntroduction(rs.getString("book_introduction"));
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        request.setAttribute("books", books);
        //当用户浏览书籍
        if (request.getParameter("flag").equals("user"))
            request.getRequestDispatcher("lookBook.jsp").forward(request, response);
        //当管理员浏览书籍
        else if(request.getParameter("flag").equals("administrators"))
            request.getRequestDispatcher("administrators_lookBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
