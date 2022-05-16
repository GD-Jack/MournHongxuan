<%--
  Created by IntelliJ IDEA.
  User: Jack
  Date: 2021/11/22
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="administratorsloginBean" class="save.data.Administrators" scope="session"/>
<HEAD>
    <%@ include file="administrators_head.txt" %>
</HEAD>
<title>添加书籍</title>
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
</style>

<HTML>
<body class="ok" background=image/back.jpg>
<div align="center">
    <form action="AddBookServlet" id="bookForm" method="get">
        <table class="ok">
            <tr>
                <td>书籍名称:</td>
                <td><input type=text id=name class="ok" name="name"/></td>
                <td><span id="nameError" class="no"></span></td>
            <tr>
                <td>作者:</td>
                <td><input type=text id=author class="ok" name="author"></td>
                <td><span id="authorError" class="no"></span></td>
            </tr>
            <tr>
                <td>译者:</td>
                <td><input type=text id=translator class="ok" name="translator"></td>
                <td><span id="translatorError" class="no"></span></td>
            </tr>
            <tr>
                <td>出版社:</td>
                <td><input type=text id=press class="ok" name="press"/></td>
                <td><span id="pressError" class="no"></span></td>
            </tr>
            <tr>
                <td>单价:</td>
                <td><input type=text id=price class="ok" name="price"/></td>
                <td><span id="priceError" class="no"></span></td>
            </tr>
            <tr>
                <td>数量:</td>
                <td><input type=text id=num class="ok" name="num"/></td>
                <td><span id="numError" class="no"></span></td>
            </tr>
            <tr>
                <td>介绍:</td>
                <td><textarea id=introduction class="ok" name="introduction" rows="10"></textarea></td>
                <td><span id="introductionError" class="no"></span></td>
            </tr>
            <tr>
                <td>书籍封面:</td>
                <td><input type="file" name="image"></td>
                <td></td>
            </tr>
            <tr>
                <th><input type="button" id="submitBtn" class="ok" value="上架"></th>
                <th><input type="reset" class="ok" value="清空"/></th>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    window.onload = function () {
        //添加书籍信息规范
        var nameElt = document.getElementById("name");
        var nameErrorspan = document.getElementById("nameError");
        nameElt.onblur = function () {
            var name = nameElt.value.trim();
            if (name == "") {
                nameErrorspan.innerText = "名称不能为空";
            }
        }
        nameElt.onfocus = function () {
            nameErrorspan.innerText = "";
        }

        var authorElt = document.getElementById("author");
        var authorErrorspan = document.getElementById("authorError");
        authorElt.onblur = function () {
            author = authorElt.value.trim();
            if (author == "") {
                authorErrorspan.innerText = "作者不能为空";
            }
        }
        authorElt.onfocus = function () {
            authorErrorspan.innerText = "";
        }

        var pressElt = document.getElementById("press");
        var pressErrorspan = document.getElementById("pressError");
        pressElt.onblur = function () {
            var press = pressElt.value;
            press = press.trim();
            if (press == "") {
                pressErrorspan.innerText = "出版社不能为空";
            }
        }
        pressElt.onfocus = function () {
            pressErrorspan.innerText = "";
        }

        var priceElt = document.getElementById("price");
        var priceErrorspan = document.getElementById("priceError")
        priceElt.onblur = function () {
            var price = priceElt.value.trim();
            if (price == "") {
                priceErrorspan.innerText = "单价不能为空";
            }
        }
        priceElt.onfocus = function () {
            priceErrorspan.innerText = "";
        }

        var numElt = document.getElementById("num");
        var numErrorspan = document.getElementById("numError")
        numElt.onblur = function () {
            var num = numElt.value.trim();
            if (num == "") {
                numErrorspan.innerText = "单价不能为空";
            }
        }
        numElt.onfocus = function () {
            numErrorspan.innerText = "";
        }

        var submitBtnElt = document.getElementById("submitBtn");
        submitBtnElt.onclick = function () {
            nameElt.focus();
            nameElt.blur();
            authorElt.focus();
            authorElt.blur();
            pressElt.focus();
            pressElt.blur();
            priceElt.focus();
            priceElt.blur();
            numElt.focus();
            numElt.blur();

            // 当所有表单项都是合法的时候,提交表单
            if (nameErrorspan.innerText == "" && authorErrorspan.innerText == ""
                && pressErrorspan.innerText == "" && priceErrorspan.innerText == ""
                && numErrorspan.innerText == "") {

                var bookFormElt = document.getElementById("bookForm");

                bookFormElt.submit();
            }
        }
    }
</script>
</body>
</HTML>
