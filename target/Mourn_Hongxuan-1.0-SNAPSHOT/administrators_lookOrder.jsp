<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/22
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="save.util.JdbcUtil" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>查看订单</title>
<style>
    #tom {
        font-family: 宋体;
        font-size: 26;
        color: black
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
</style>
<HTML>
<body background=image/back.jpg id=tom>
<div align="center">
    <%
        request.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(request.getParameter("order_id"));
        JdbcUtil util = new JdbcUtil();
        ResultSet rs = null;
        String sql = "select * from orderform where order_id = ?";
        PreparedStatement ps = util.createStatement(sql);
        ps.setInt(1, id);
        try {
            rs = ps.executeQuery();
            if (rs.next()) {
                out.print(rs.getString("order_message"));
                out.print("<br><a href=\"deleteOrderServlet?flag=administrators&order_id=" + rs.getInt("order_id") + "\">删除订单</a>");
                out.print("<br><a href=\"LookOrderServlet?flag=administrators\">返回</a>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
    %>
</div>
</body>
</HTML>
