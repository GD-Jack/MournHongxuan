<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="userBean" class="save.data.Register" scope="request"/>
<HEAD><%@ include file="user_head.txt" %></HEAD>
<title>注册页面</title>
<style>
    .ok {
        font-family:楷体;
        font-size:26;
        color:black;
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

    .yes {
        font-family: 黑体;
        font-size: 18;
        color: black;
    }
</style>

<HTML>
<body class="ok" background=image/back.jpg>
<div align="center">
    <form action="registerServlet" id="userForm" method="get">
        <table class="ok">
            <tr>
                <td>用户名称:</td>
                <td><input type=text id=username class="ok" name="logname"/></td>
                <td><span id="usernameError" class="no"></span></td>
            </tr>
            <tr>
                <td>用户密码:</td>
                <td><input type=password id=password class="ok" name="password"></td>
                <td><span id="passwordError" class="no"></span></td>
            </tr>
            <tr>
                <td>重复密码:</td>
                <td><input type=password id=again_password class="ok" name="again_password"></td>
                <td><span id="again_passwordError" class="no"></span></td>
            </tr>
            <tr>
                <td>联系电话或邮箱:</td>
                <td><input type=text id=telephone class="ok" name="phone"/></td>
                <td><span id="telephoneError" class="no"></span></td>
            </tr>
            <tr>
                <td>邮寄地址:</td>
                <td><input type=text class="ok" name="address"/></td>
                <td><span id="emailError"></span></td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td><input type=text class="ok" name="realname"/></td>
                <td><span id="nameError"></span></td>
            </tr>
            <tr>
                <th><input type="button" id="submitBtn" class="ok" value="注册"></th>
                <th><input type="reset" class="ok" value="清空"/></th>
            </tr>
        </table>
    </form>
</div>
<div align="center">
    注册反馈：
    <jsp:getProperty name="userBean" property="backNews"/>
    <table class="yes" border=3>
        <tr>
            <td>会员名称:</td>
            <td>
                <jsp:getProperty name="userBean" property="logname"/>
            </td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td>
                <jsp:getProperty name="userBean" property="realname"/>
            </td>
        </tr>
        <tr>
            <td>地址:</td>
            <td>
                <jsp:getProperty name="userBean" property="address"/>
            </td>
        </tr>
        <tr>
            <td>电话:</td>
            <td>
                <jsp:getProperty name="userBean" property="phone"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    window.onload = function () {
        //用户注册规范
        var usernameElt = document.getElementById("username");
        var usernameErrorspan = document.getElementById("usernameError");
        //鼠标提出文本框
        usernameElt.onblur = function () {
            var username = usernameElt.value;
            username = username.trim();
            if (username == "") {
                usernameErrorspan.innerText = "用户名不能为空";
            } else {
                if (username.length < 6 || username.length > 14) {
                    usernameErrorspan.innerText = "用户名长度不合法，用户名必须在6-14位之间";
                } else {
                    var usernameRegExp = /^[A-Za-z0-9_]{1,}$/;
                    var ok = usernameRegExp.test(username);
                    if (!ok) {
                        usernameErrorspan.innerText = "用户名只能含有数字、字母或者下划线组成";
                    }
                }
            }
        }
        //鼠标选中文本框
        usernameElt.onfocus = function () {
            usernameErrorspan.innerText = "";
        }
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
            usernameElt.focus();
            usernameElt.blur();
            passwordElt2.focus();
            passwordElt2.blur();
            telephoneElt.focus();
            telephoneElt.blur();

            // 当所有表单项都是合法的时候,提交表单
            if (usernameErrorspan.innerText == "" && passwordErrorspan2.innerText == ""
                && telephoneErrorspan.innerText == "") {
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
