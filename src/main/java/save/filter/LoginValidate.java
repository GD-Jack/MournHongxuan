package save.filter;
/**
 * 拦截恶意登录
 */

import save.data.Administrators;
import save.data.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginValidate {
    public static void userValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        try {
            Login loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                response.sendRedirect("login.jsp");//重定向到登录页面。
                return;
            } else {
                boolean b = loginBean.getLogname() == null ||
                        loginBean.getLogname().length() == 0;
                if (b) {
                    response.sendRedirect("login.jsp");//重定向到登录页面。
                    return;
                }
            }
        } catch (Exception exp) {
            response.sendRedirect("login.jsp");//重定向到登录页面。
            return;
        }
    }

    public static void administratorsValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    }
}
