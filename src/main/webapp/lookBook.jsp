<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="save.data.Book" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD>
    <%@ include file="user_head.txt" %>
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
                String detail = "<a href ='FindBookServlet?flag=user&radio=book_id&searchMess=" + book.getId() + "'>书籍详情</a>";
                out.print("<th>" + detail + "</th>");
                String shopping ="<a href ='putGoodsServlet?flag=showDetail&book_id=" + book.getId() + "&logname=" +
                        loginBean.getLogname() + "'>添加到购物车</a>";
                out.print("<th>" + shopping + "</tr>");
            }
        %>
    </table>
</div>
</body>
</HTML>
