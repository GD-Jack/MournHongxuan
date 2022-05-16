package handle.user;

import save.data.Book;
import save.util.JdbcUtil;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class SearchByCondition extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String searchMess = request.getParameter("searchMess");
        String radioMess = request.getParameter("radio");
        JdbcUtil util = new JdbcUtil();
        String sql = "";
        ResultSet rs = null;
        float max = 0, min = 0;
        //当选则用编号查询
        if (radioMess.contains("book_id")) {
            sql = "select * from book where book_id = " + searchMess;
            //当选择用书籍信息模糊查询
        } else if (radioMess.contains("book_information")) {
            sql = "select * from book where book_name like '%" + searchMess + "%' or " +
                    "book_author like '%" + searchMess + "%' or " +
                    "book_translator like '%" + searchMess + "%' or " +
                    "book_press like '%" + searchMess + "%' or " +
                    "book_introduction like '%" + searchMess + "%'";
            //当选择用价格区间查询
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
            List<Book> books = new ArrayList();
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
            request.setAttribute("books", books);
        } catch (Exception e) {
            response.getWriter().print("" + e);
        } finally {
            util.close(rs);
        }
        if (request.getParameter("flag").equals("user"))
            request.getRequestDispatcher("byPageShow.jsp").forward(request, response);
        else if (request.getParameter("flag").equals("administrators"))
            request.getRequestDispatcher("administrators_byPageShow.jsp").forward(request, response);
    }
}  
