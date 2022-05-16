package handle.user;

import save.util.JdbcUtil;
import save.data.Register;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleRegister extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        JdbcUtil util = new JdbcUtil();
        Register userBean = new Register();
        request.setAttribute("userBean", userBean);

        String logname = request.getParameter("logname").trim();
        String password = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String address = request.getParameter("address").trim();
        String realname = request.getParameter("realname").trim();
        String backNews = "";
        try {
            String sql = "insert into user(logname, password, phone, address, realname) value (?,?,?,?,?)";
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1, logname);
            ps.setString(2, password);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, realname);
            int m = ps.executeUpdate();
            if (m != 0) {
                backNews = "注册成功";
                userBean.setBackNews(backNews);
                userBean.setLogname(logname);
                userBean.setPhone(phone);
                userBean.setAddress(address);
                userBean.setRealname(realname);
            }
        } catch (SQLException e) {
            backNews = "该会员名已被使用，请您更换名字";
            userBean.setBackNews(backNews);
        } finally {
            util.close();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");
        dispatcher.forward(request, response);
    }
}
