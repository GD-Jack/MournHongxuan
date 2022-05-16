<%@ page import="java.util.ArrayList" %>
<%@ page import="save.data.Book" %>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD>
    <%@ include file="user_head.txt" %>
</HEAD>
<title>分页浏览页面</title>
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
<body background=image/back.jpg id="tom">
<center>
    </p>
    <table id=tom border=1>
        <%
            ArrayList<Book> books = (ArrayList) request.getAttribute("books");
            if (books.size() == 0) {
                out.print("没有记录");
                return;
            }
        %>
        <tr>
            <th>书籍编号</th>
            <th>书名</th>
            <th>作者</th>
            <th>单价</th>
        </tr>
        <%
            for (Book book : books) {
                out.print("<tr><th>" + book.getId() + "</th>");
                out.print("<th>" + book.getName() + "</th>");
                out.print("<th>" + book.getAuthor() + "</th>");
                out.print("<th>" + book.getPrice() + "</th>");
                String detail = "<a href ='FindBookServlet?flag=user&radio=book_id&searchMess=" + book.getId() + "'>书籍详情</a>";
                out.print("<th>" + detail + "</th>");
                String shopping = "<a href ='putGoodsServlet?flag=byPageShow&book_id=" + book.getId() + "&logname=" +
                        loginBean.getLogname() +  "'>添加到购物车</a>";
                out.print("<th>" + shopping + "</th></tr>");
            }
        %>
    </table>
    <p id=tom>全部记录数:<%out.print(books.size());%>
</center>
</body>
</HTML>
