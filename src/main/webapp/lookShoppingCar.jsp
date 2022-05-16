<%@ page import="java.util.ArrayList" %>
<%@ page import="save.data.Shoppingform" %>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD>
    <%@ include file="user_head.txt" %>
</HEAD>
<title>查看购物车</title>
<style>
    .tom {
        font-family: 楷体;
        font-size: 26;
        color: black;
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
</style>
<HTML>
<body background=image/back.jpg class="tom">

<div align="center">
    <table border=1 align="center">
        <tr>
            <th class=tom width=120>书籍编号
            <th class=tom width=120>书名
            <th class=tom width=120>单价
            <th class=tom width=120>数量
            <th class=tom width=120>总价
        </tr>
            <%
        ArrayList<Shoppingform> shoppingforms = (ArrayList) request.getAttribute("shoppingforms");
        float num = 0;
        for (Shoppingform shoppingform : shoppingforms) {
            out.print("<tr>");
            out.print("<th class=tom>" + shoppingform.getBook_id());
            //给商品名称一个超链接，方便用户查看书籍详细信息
            out.print("<th class=tom><a  href='FindBookServlet?flag=user&radio=book_id&searchMess=" + shoppingform.getBook_id() + "'>" +
            shoppingform.getName() + "</a>");
            out.print("<th class=tom>" + shoppingform.getPrice());
            //交给updateServletch处理商品数量信息
            out.print("<th class=tom>" + "<form  action='updateServlet' id=\"update\" method = 'post'>" +
                    "<input type ='submit' name=\"flag\" size=5 value='-'>" +          //减按钮
                    "<input type ='text' id=\"amount\" name='amount' size =3 value= " +
                    shoppingform.getAmount() + " onblur=\"submit();\"/>" +            //当鼠标离开文本框，提交表单更新
                    "<input type ='hidden' name='goods_id' value= " + shoppingform.getId() + " />" +//隐秘提交id
                    "<input type ='submit' name=\"flag\" size=5 value='+'></form>");   //加按钮
            out.print("<th class=tom>" + (shoppingform.getPrice() * shoppingform.getAmount()));
            num += shoppingform.getPrice() * shoppingform.getAmount();
            out.print("<th class=tom><a  href='deleteServlet?flag=one&goods_id=" + shoppingform.getId() + "'>删除商品</a>");
            out.print("</tr>");
        }
        String delete = "<a  href='deleteServlet?flag=all&logname=" + loginBean.getLogname() + "'>清空购物车</a>";
        out.print("<tr><th class=tom>总价<th><th><th><th class=tom>" + num + "<th class=tom>" + delete + "</tr>");
        out.print("</table>");
        out.print("<form action='buyServlet' method='post'>" +
                "<input type ='hidden' name='logname' value= '" + loginBean.getLogname() + "'/>" +
                "<input type ='submit' class=tom value='生成订单(同时清空购物车)'></form></div>");
    %>
</body>
</HTML>
