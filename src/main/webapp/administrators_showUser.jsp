<%@ page import="save.data.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/23
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>用户信息浏览</title>
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
            <th>用户名
            <th>密码
            <th>联系方式
            <th>地址
            <th>真实姓名
        </tr>
        <%

            ArrayList<User> users = (ArrayList) request.getAttribute("users");
            for (User user : users) {
                out.print("<tr><th>" + user.getLogname());
                out.print("<th>" + user.getPassword());
                out.print("<th>" + user.getPhone());
                out.print("<th>" + user.getAddress());
                out.print("<th>" + user.getRealname());
                String update = "<th><a href=\"administrators_updateUser.jsp?logname=" + user.getLogname() +
                        "&password=" + user.getPassword() + "&phone=" + user.getPhone() +
                        "&address=" + user.getAddress() + "&realname=" +
                        user.getRealname() + "\">更新信息</a></th>";
                out.print(update);
                String delete ="<a href ='DeleteUserServlet?logname=" + user.getLogname() + "'>删除用户</a>";
                out.print("<th>" + delete + "</tr>");
            }
        %>
    </table>
</div>
</body>
</HTML>
