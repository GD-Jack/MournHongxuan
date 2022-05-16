package handle.user;

import save.data.Book;
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

@WebServlet(name = "FindBookServlet", value = "/FindBookServlet")
public class FindBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        String searchMess = request.getParameter("searchMess");
        String radioMess = request.getParameter("radio");
        List<Book> books = new ArrayList();
        String sql = "";
        ResultSet rs = null;
        float max = 0, min = 0;
        if (radioMess.contains("book_id")) {
            sql = "select * from book where book_id = " + searchMess;
        } else if (radioMess.contains("book_information")) {
            sql = "select * from book where book_name like '%" + searchMess + "%' or " +
                    "book_author like '%" + searchMess + "%' or " +
                    "book_translator like '%" + searchMess + "%' or " +
                    "book_press like '%" + searchMess + "%' or " +
                    "book_introduction like '%" + searchMess + "%'";
        } else if (radioMess.contains("book_price")) {
            String priceMess[] = searchMess.split("[-]+");
            try {
                min = Float.parseFloat(priceMess[0]);
                max = Float.parseFloat(priceMess[1]);
            } catch (NumberFormatException exp) {
                min = 0;
                max = 0;
            }
            sql = "select * from book where book_price<=" + max + " and book_price>=" + min;
        }
        try {
            PreparedStatement ps = util.createStatement(sql);
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
        Book book = books.get(0);
        request.setAttribute("book", book);
        if (request.getParameter("flag").equals("user"))
            request.getRequestDispatcher("showDetail.jsp").forward(request, response);
        else if(request.getParameter("flag").equals("administrators"))
            request.getRequestDispatcher("administrators_showDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
