package handle.user;

import save.util.JdbcUtil;
import save.data.*;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleLogin extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        JdbcUtil util = new JdbcUtil();
        String logname = request.getParameter("logname").trim();
        String password = request.getParameter("password").trim();
        String sql = "select * from user where logname = ? and password = ?";

        try {
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1, logname);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                success(request,response,logname,password);
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            else {
                String backNews="您输入的用户名不存在，或密码不般配";
                fail(request,response,logname,backNews);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
    }

    public void success(HttpServletRequest request,
                        HttpServletResponse response,
                        String logname, String password) {
        Login loginBean = null;
        HttpSession session = request.getSession(true);
        try {
            loginBean = (Login) session.getAttribute("loginBean");
            if (loginBean == null) {
                loginBean = new Login();
                session.setAttribute("loginBean", loginBean);
                loginBean = (Login) session.getAttribute("loginBean");
            }
            String name = loginBean.getLogname();
            if (name.equals(logname)) {
                loginBean.setBackNews(logname + "已经登录了");
                loginBean.setLogname(logname);
            } else {
                loginBean.setBackNews(logname + "登录成功");
                loginBean.setLogname(logname);
            }
        } catch (Exception ee) {
            loginBean = new Login();
            session.setAttribute("loginBean", loginBean);
            loginBean.setBackNews(ee.toString());
            loginBean.setLogname(logname);
        }
    }

    public void fail(HttpServletRequest request,
                     HttpServletResponse response,
                     String logname, String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<html><body background=image/back.jpg; style=\"background-repeat:no-repeat;" +
                    "background-size:100% 100%;\"><div align=\"center\">");
            out.println("<h2>" + logname + "登录反馈结果<br>" + backNews + "</h2>");
            out.println("返回登录页面或主页<br>");
            out.println("<a href = login.jsp>登录页面</a>");
            out.println("<br><a href = index.jsp>主页</a>");
            out.println("</div></body></html>");
        } catch (IOException exp) {
        }
    }
}
