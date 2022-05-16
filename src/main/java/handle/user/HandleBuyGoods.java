package handle.user;

import save.filter.LoginValidate;
import save.util.JdbcUtil;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HandleBuyGoods extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String logname = request.getParameter("logname");
        LoginValidate.userValidate(request, response);
        JdbcUtil util = new JdbcUtil();
        ResultSet rs = null;
        try {
            String querySQL = "select * from shoppingform where logname = ?";
            String insertSQL = "insert into orderform values(?,?,?,?)";
            String deleteSQL = "delete from shoppingform where logname = ?";
            String updatebookSQL = "update book set book_num = ? where book_id = ?";
            String querybookSQL = "select book_num from book where book_id = ?";
            PreparedStatement ps = util.createStatement(querySQL);
            ps.setString(1, logname);
            rs = ps.executeQuery();
            StringBuffer buffer = new StringBuffer();
            float sum = 0;
            boolean canCreateForm = false;
            buffer.append("<table id=tom border=1><tr><th>书籍编号<th>名称<th>单价<th>数量");
            String title = "";
            int flag = 0;
            while (rs.next()) {
                canCreateForm = true;
                logname = rs.getString("logname");
                int book_id = rs.getInt("book_id");
                String goods_name = rs.getString("goods_name");
                float goods_price = rs.getFloat("goods_price");
                int goods_amount = rs.getInt("goods_amount");
                if (goods_amount == 0) {
                    continue;
                }
                if (++flag < 4) {
                    title += rs.getString("goods_name") + ",";
                }
                sum += goods_price * goods_amount;
                buffer.append("<tr><th>" + book_id + "<th>" + goods_name +
                        "<th>" + goods_price + "<th>" + goods_amount + "</tr>");
                ps = util.createStatement(querybookSQL);
                ps.setInt(1, book_id);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    int book_num = rs2.getInt("book_num");
                    //库存小于下单数量
                    if (book_num < goods_amount) {
                        response.setContentType("text/html;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.println("<html><body background=image/back.jpg; style=\"background-repeat:no-repeat;" +
                                "background-size:100% 100%;\"><div align=\"center\">");
                        out.println("<h2>下单失败，" + goods_name +  "等书籍库存不足");
                        out.println("<br><a href = \"UserLoginValidateServlet?flag=shoppingCar\">返回</a></h2>");
                        out.println("</div></body></html>");
                        return;
                    }
                    ps = util.createStatement(updatebookSQL);
                    ps.setInt(1, book_num - goods_amount);
                    ps.setInt(2, book_id);
                    ps.executeUpdate();
                }
            }
            if (canCreateForm == false || sum == 0) {
                //购物车没有物品，或者有物品数量未零
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<html><body background=image/back.jpg; style=\"background-repeat:no-repeat;" +
                        "background-size:100% 100%;\"><div align=\"center\">");
                out.println("<h2>请先选择商品添加到购物车");
                out.println("<br><a href = \"index.jsp\">主页</a></h2>");
                out.println("<br><a href = \"bookServlet?flag=user\">查看书籍</a></h2>");
                out.println("</div></body></html>");
                return;
            }
            title = title.substring(0, title.length() - 1);
            title += "等";
            buffer.append("<tr><table>");
            String strSum = String.format("%10.2f", sum);
            buffer.append("商品总价:" + strSum);
            ps = util.createStatement(insertSQL);
            ps.setInt(1, 0);
            ps.setString(2, logname);
            ps.setString(3, title);
            ps.setString(4, new String(buffer));
            ps.executeUpdate();
            ps = util.createStatement(deleteSQL);
            ps.setString(1, logname);
            ps.executeUpdate();
            //下单成功，付款
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body><div align=\"center\">");
            out.println("<h2>请付款");
            out.println("<br><img src = \"image/收款码.jpg\" width=\"500px\">");
            out.println("<br><a href = \"UserLoginValidateServlet?flag=order\">完成付款</a></h2>");
            out.println("</div></body></html>");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
    }
}
