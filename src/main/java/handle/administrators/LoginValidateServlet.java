package handle.administrators;
/**
 *防止未登录浏览或修改管理员权限隐私信息
 */

import save.data.Administrators;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginValidateServlet", value = "/LoginValidateServlet")
public class LoginValidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            Administrators administrators = (Administrators) session.getAttribute("administratorsloginBean");
            if (administrators == null) {
                response.sendRedirect("administrators_login.jsp");
                return;
            } else {
                boolean b = administrators.getLogname() == null ||
                        administrators.getLogname().length() == 0;
                if (b) {
                    response.sendRedirect("administrators_login.jsp");
                    return;
                }
            }
        } catch (Exception exp) {
            response.sendRedirect("administrators_login.jsp");
            return;
        }
        if (request.getParameter("flag").equals("addbook")) {
            response.sendRedirect("administrators_addBook.jsp");
        } else if (request.getParameter("flag").equals("deletebook")) {
            response.sendRedirect("DeleteBookServlet?flag=look&book_id=" + request.getParameter("book_id"));
        } else if (request.getParameter("flag").equals("lookuser")) {
            response.sendRedirect("LookUserServlet");
        } else if (request.getParameter("flag").equals("lookorder")) {
            response.sendRedirect("LookOrderServlet?flag=administrators");
        } else {
            response.sendRedirect("administrators_login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
