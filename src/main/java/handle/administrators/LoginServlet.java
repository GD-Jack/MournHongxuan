package handle.administrators;

import save.util.JdbcUtil;
import save.data.Administrators;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logname = request.getParameter("logname");
        String password = request.getParameter("password");

        JdbcUtil util = new JdbcUtil();
        ResultSet rs = null;
        try {
            String sql = "select * from administrator where logname = ? and password = ?";
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1, logname);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                success(request,response,logname,password);
                request.getRequestDispatcher("administrators_index.jsp").forward(request,response);
            } else {
                String backNews="��������û��������ڣ������벻����";
                fail(request,response,logname,backNews);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
    }
    public void success(HttpServletRequest request,
                        HttpServletResponse response,
                        String logname, String password) {
        Administrators administratorsloginBean = null;
        HttpSession session = request.getSession(true);
        try {
            administratorsloginBean = (Administrators) session.getAttribute("administratorsloginBean");
            if (administratorsloginBean == null) {
                administratorsloginBean = new Administrators();  //�����µ�����ģ�� ��
                session.setAttribute("administratorsloginBean", administratorsloginBean);
                administratorsloginBean = (Administrators) session.getAttribute("administratorsloginBean");
            }
            String name = administratorsloginBean.getLogname();
            if (name.equals(logname)) {
                administratorsloginBean.setBackNews(logname + "�Ѿ���¼��");
                administratorsloginBean.setLogname(logname);
            } else {  //����ģ�ʹ洢�µĵ�¼�û�:
                administratorsloginBean.setBackNews(logname + "��¼�ɹ�");
                administratorsloginBean.setLogname(logname);
            }
        } catch (Exception ee) {
            administratorsloginBean = new Administrators();
            session.setAttribute("administratorsloginBean", administratorsloginBean);
            administratorsloginBean.setBackNews(ee.toString());
            administratorsloginBean.setLogname(logname);
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
            out.println("<h2>" + logname + "��¼�������<br>" + backNews + "</h2>");
            out.println("���ص�¼ҳ�����ҳ<br>");
            out.println("<a href = administrators_index.jsp>��¼ҳ��</a>");
            out.println("<br><a href = administrators_index.jsp>��ҳ</a>");
            out.println("</div></body></html>");
        } catch (IOException exp) {
        }
    }
}
