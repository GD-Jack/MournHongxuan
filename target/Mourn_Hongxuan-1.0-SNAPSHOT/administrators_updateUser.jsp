<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/22
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>修改用户信息</title>
<style>
    .ok {
        font-family: 宋体;
        font-size: 26;
        color: black
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
    .no {
        font-family: 宋体;
        font-size: 16;
        color: red;
        display: block;
        width: 150px;
    }
</style>

<HTML>
<body class="ok" background=image/back.jpg>
<%
    if (administratorsloginBean == null) {
        response.sendRedirect("administrators_login.jsp");//重定向到登录页面。
        return;
    } else {
        boolean b = administratorsloginBean.getLogname() == null ||
                administratorsloginBean.getLogname().length() == 0;
        if (b) {
            response.sendRedirect("administrators_login.jsp");//重定向到登录页面。
            return;
        }
    }%>
<div align="center">
    <form action="UpdateUserServlet" id="userForm" method="get">
        <table class="ok">
            <tr>
                <td>用户名:</td>
                <td><%=request.getParameter("logname")%></td>
                <td><input type="hidden" name="logname" value=<%=request.getParameter("logname")%>></td>
            </tr>
            <tr>
                <td>用户密码:</td>
                <td><input type=password id=password class="ok" name="password" value="<%=request.getParameter("password")%>"></td>
                <td><span id="passwordError" class="no"></span></td>
            </tr>
            <tr>
                <td>重复密码:</td>
                <td><input type=password id=again_password class="ok" name="again_password" value="<%=request.getParameter("password")%>"></td>
                <td><span id="again_passwordError" class="no"></span></td>
            </tr>
            <tr>
                <td>联系电话或邮箱:</td>
                <td><input type=text id=telephone class="ok" name="phone" value="<%=request.getParameter("phone")%>"></td>
                <td><span id="telephoneError" class="no"></span></td>
            </tr>
            <tr>
                <td>邮寄地址:</td>
                <td><input type=text class="ok" name="address" value="<%=request.getParameter("address")%>"></td>
                <td><span id="emailError"></span></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input type=text class="ok" name="realname" value="<%=request.getParameter("realname")%>"></td>
                <td><span id="nameError"></span></td>
            </tr>
            <tr>
                <th><input type="button" id="submitBtn" class="ok" value="更新"></th>
                <th><input type="reset" class="ok" value="清空"/></th>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    window.onload = function () {
        //更新用户信息规范
        var passwordElt = document.getElementById("password");
        var passwordErrorspan = document.getElementById("passwordError");
        passwordElt.onblur = function () {
            password = passwordElt.value.trim();
            if (password == "") {
                passwordErrorspan.innerText = "密码不能为空";
            } else {
                if (password.length < 6) {
                    passwordErrorspan.innerText = "密码长度不能低于六位";
                }
                if (password.length > 16) {
                    passwordErrorspan.innerText = "密码长度不能高于十六位";
                } else {
                    var passwordRegExp = /^[^\s]*$/;
                    var ok = passwordRegExp.test(password);
                    if (!ok) {
                        passwordErrorspan.innerText = "密码不能出现空格";
                    }
                }
            }
        }
        passwordElt.onfocus = function () {
            passwordErrorspan.innerText = "";
        }

        var passwordElt2 = document.getElementById("again_password");
        var passwordErrorspan2 = document.getElementById("again_passwordError");

        passwordElt2.onblur = function () {
            password2 = passwordElt2.value;
            password2 = password2.trim();
            if (document.getElementById("password").value != password2) {
                passwordErrorspan2.innerText = "前后密码不一致";
            }
        }
        passwordElt2.onfocus = function () {
            passwordErrorspan2.innerText = "";
        }

        var telephoneElt = document.getElementById("telephone");
        var telephoneErrorspan = document.getElementById("telephoneError");
        telephoneElt.onblur = function () {
            var telephone = telephoneElt.value;
            telephone = telephone.trim();
            if (telephone == "") {
                telephoneErrorspan.innerText = "电话或邮箱不能为空";
            } else {
                var telephoneRegExp = /^1[3456789]\d{9}$/;
                var emailRegExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                var ok = telephoneRegExp.test(telephone) || emailRegExp.test(telephone);
                if (!ok) {
                    telephoneErrorspan.innerText = "电话或邮箱格式不正确";
                }
            }
        }
        telephoneElt.onfocus = function () {
            telephoneErrorspan.innerText = "";
        }

        var submitBtnElt = document.getElementById("submitBtn");
        submitBtnElt.onclick = function () {
            passwordElt2.focus();
            passwordElt2.blur();
            telephoneElt.focus();
            telephoneElt.blur();

            // 当所有表单项都是合法的时候,提交表单
            if (passwordErrorspan2.innerText == "" && telephoneErrorspan.innerText == "") {
                // 获取表单对象
                var userFormElt = document.getElementById("userForm");
                // 提交表单
                userFormElt.submit();
            }
        }
    }
</script>
</body>
</HTML>
