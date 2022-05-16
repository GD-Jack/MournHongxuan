<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="save.data.Orderform" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD>
    <%@ include file="user_head.txt" %>
</HEAD>
<title>查看订单</title>
<style>
    #tom {
        font-family:楷体;font-size:26;color:black;
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
</style>
<HTML>
<body background=image/back.jpg id=tom>
<div align="center">
    <table border=1>
        <tr>
            <th id=tom width=110>序号
            <th id=tom width=300>内容
        </tr>
        <%
        ArrayList<Orderform> orderforms = (ArrayList) request.getAttribute("orderforms");
        for (Orderform orderform : orderforms) {
            out.print("<tr>");
            out.print("<th id=tom>" + orderform.getId());
            out.print("<th id=tom>" + orderform.getTitle());
            out.print("<th id=tom><a href=\"lookOrder.jsp?order_id=" + orderform.getId() + "\">订单详细</a>");
            out.print("<th id=tom><a href=\"deleteOrderServlet?flag=user&order_id=" + orderform.getId() + "\">删除订单</a>");
            out.print("</tr>");
        }
        out.print("</table>");
    %>
</div>
</body>
</HTML>
