<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/22
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="save.data.Book" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>商品详情</title>
<style>
    #tom {
        font-family: 宋体;
        font-size: 26;
        color: black;
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
        String update = "<th><a href=\"administrators_updateBook.jsp?book_id=" + book.getId() +
        "&book_name=" + book.getName() + "&book_author=" + book.getAuthor() +
        "&book_translator=" + book.getTranslator() + "&book_press=" + book.getPress() +
        "&book_price=" + book.getPrice() + "&book_num=" + book.getNum() +
        "&book_introduction=" + book.getIntroduction() + "\">更新信息</a></th>";
        out.print(update);
        String delete ="<a href ='LoginValidateServlet?flag=deletebook&book_id=" + book.getId() + "'>下架书籍</a>";
                out.print("<th>" + delete + "</tr>");
        out.print("</table>");
        out.print("产品详情:<br>");
        out.print("介绍<br>" + book.getIntroduction());
        String pic = "<br><img src='image/" + book.getId() + ".jpg'></img>";
        out.print(pic);
    %>
</center>
</body>
</HTML>

