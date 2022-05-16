<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD><%@ include file="administrators_head.txt" %></HEAD>
<title>管理员登录页面</title>
<style>
    .no {
        font-family: 宋体;
        font-size: 16;
        color: red;
        display: block;
        width: 150px;
    }
    .tom {
        font-family:楷体;
        font-size:26;
        color:black;
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
</style>
<HTML>
<body class=tom background=image/back.jpg>
<div align="center">
    <table class=tom>
        <tr align="center">
            <td colspan = "2">登录</td>
        </tr>
        <form action="LoginServlet" id=logform method="post">
            <tr>
                <th>登录名称:</th>
                <th><input type=text id=logname class=tom name="logname"></th>
                <th><span id="lognameError" class="no"></span></th>
            </tr>
            <tr>
                <th>输入密码:</th>
                <th><input type=password id=password class=tom name="password"></th>
                <th><span id="passwordError" class="no"></span></th>
            </tr>
            <tr>
                <th><input type=button id=submitBtn class=tom value="提交">
                <th><input type="reset" class=tom value="清空"/></th>
            </tr>
        </form>
    </table>
</div>
<div align="center">
    登录反馈信息:<br>
    <jsp:getProperty name="administratorsloginBean" property="backNews"/>
    <br>登录名称:<br>
    <jsp:getProperty name="administratorsloginBean" property="logname"/>
</div>
<script type="text/javascript">
    window.onload = function () {
        var lognameElt = document.getElementById("logname");
        var lognameErrorspan = document.getElementById("lognameError");
        lognameElt.onblur = function () {
            var logname = lognameElt.value.trim();
            if (logname == "") {
                lognameErrorspan.innerText = "登录账号不能为空";
            }
        }
        lognameElt.onfocus = function () {
            lognameErrorspan.innerText = "";
        }

        var passwordElt = document.getElementById("password");
        var passwordErrorspan = document.getElementById("passwordError");
        passwordElt.onblur = function () {
            var password= passwordElt.value.trim();
            if (password == "") {
                passwordErrorspan.innerText = "密码不能为空";
            }
        }
        passwordElt.onfocus = function () {
            passwordErrorspan.innerText = "";
        }

        var submitBtnElt = document.getElementById("submitBtn");
        submitBtnElt.onclick = function () {
            lognameElt.focus();
            lognameElt.blur();
            passwordElt.focus();
            passwordElt.blur();

            if (lognameErrorspan.innerText == "" && passwordErrorspan.innerText == "") {
                var logformElt = document.getElementById("logform");
                logformElt.submit();
            }
        }
    }
</script>
</body>
</HTML>