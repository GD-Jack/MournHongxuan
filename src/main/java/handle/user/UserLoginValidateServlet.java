package handle.user;
/**
 *��ֹδ��¼������޸��û�Ȩ����˽��Ϣ
 */
import save.data.Login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginValidateServlet", value = "/UserLoginValidateServlet")
public class UserLoginValidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Login loginBean = null;
        try {
            loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ�档
                return;
            } else {
                boolean b = loginBean.getLogname() == null ||
                        loginBean.getLogname().length() == 0;
                if (b) {
                    response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ�档
                    return;
                }
            }
        } catch (Exception exp) {
            response.sendRedirect("login.jsp");//�ض��򵽵�¼ҳ�档
            return;
        }
        if (request.getParameter("flag").equals("shoppingCar")) {
            String logname = loginBean.getLogname();
            response.sendRedirect("LookShoppingCarServlet?logname=" + logname);
        } else if (request.getParameter("flag").equals("order")) {
            String logname = loginBean.getLogname();
            response.sendRedirect("LookOrderServlet?flag=user&logname=" + logname);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
