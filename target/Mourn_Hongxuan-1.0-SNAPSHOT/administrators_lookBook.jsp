<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/22
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="save.data.Book" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>浏览书籍页面</title>
<style>
    .ok {
        font-family: 宋体;
        font-size: 26;
        color: black;
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
</style>
<HTML>
<body class="ok" background=image/back.jpg>
<div align="center">
    <table class="ok" border=1>
        <tr>
            <th>书籍编号
            <th>书名
            <th>作者
            <th>售价
            <th>剩余数量
        </tr>
        <%

            ArrayList<Book> books = (ArrayList) request.getAttribute("books");
            for (Book book: books) {
                out.print("<tr><th>" + book.getId());
                out.print("<th>" + book.getName());
                out.print("<th>" + book.getAuthor());
                out.print("<th>" + book.getPrice());
                out.print("<th>" + book.getNum());
                String detail = "<a href ='FindBookServlet?flag=administrators&" +
                        "radio=book_id&searchMess=" + book.getId() + "'>书籍详情</a>";
                out.print("<th>" + detail + "</th>");
                String update = "<th><a href=\"administrators_updateBook.jsp?book_id=" + book.getId() +
                        "&book_name=" + book.getName() + "&book_author=" + book.getAuthor() +
                        "&book_translator=" + book.getTranslator() + "&book_press=" + book.getPress() +
                        "&book_price=" + book.getPrice() + "&book_num=" + book.getNum() +
                        "&book_introduction=" + book.getIntroduction() + "\">更新信息</a></th>";
                out.print(update);
                String delete ="<a href ='LoginValidateServlet?flag=deletebook&book_id=" + book.getId() + "'>下架书籍</a>";
                out.print("<th>" + delete + "</tr>");
            }
        %>
    </table>
</div>
</body>
</HTML>

