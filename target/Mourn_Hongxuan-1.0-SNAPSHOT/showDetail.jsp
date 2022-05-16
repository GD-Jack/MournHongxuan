<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="save.data.Book" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD>
    <%@ include file="user_head.txt" %>
</HEAD>
<title>商品详情</title>
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
<center>
    <table id=tom border=1>
        <tr>
            <th>序号</th>
            <th>书名</th>
            <th>作者</th>
            <th>译者</th>
            <th>出版社</th>
            <th>售价</th>
            <th>剩余数量</th>
        </tr>
    <%
        Book book = (Book) request.getAttribute("book");
        out.print("<tr>");
        out.print("<th>" + book.getId() + "</th>");
        out.print("<th>" + book.getName() + "</th>");
        out.print("<th>" + book.getAuthor() + "</th>");
        out.print("<th>" + book.getTranslator() + "</th>");
        out.print("<th>" + book.getPress() + "</th>");
        out.print("<th>" + book.getPrice() + "</th>");
        out.print("<th>" + book.getNum() + "</th>");
        String shopping ="<a href ='putGoodsServlet?flag=showDetail&book_id=" + book.getId() + "&logname=" +
                loginBean.getLogname() + "'>添加到购物车</a>";
        out.print("<th>" + shopping + "</th>");
        out.print("</tr>");
        out.print("</table>");
        out.print("产品详情:<br>");
        out.print("介绍<br>" + book.getIntroduction());
        String pic = "<br><img src='image/" + book.getId() + ".jpg'></img>";
        out.print(pic);

    %>
</center>
</body>
</HTML>
