<%@ page contentType="text/html" %>
<%@ page pageEncoding="utf-8" %>
<HEAD>
    <%@ include file="user_head.txt" %>
</HEAD>
<title>查询页面</title>
<style>
    .on {
        font-family: 宋体;
        font-size: 16;
        color: red;
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
<body bgcolor=pink class=tom background=image/back.jpg>
<div align="center">
    <p class=tom>书籍信息支持模糊查询。
        <br>输入价格是在2个值之间的价格，格式是：价格1-价格2<br>
    </p>
    <form action="searchByConditionServlet?flag=user" id="searchForm" class=tom method="post">
        <br>输入查询信息:<input type=text class=tom id="searchMess" name="searchMess">
        <span id="searchMessError" class="on"></span><br>
        <input type=radio name="radio" class=tom value="book_id">
        书籍编号
        <input type=radio name="radio" class=tom value="book_information" checked="ok">
        书籍信息
        <input type=radio name="radio" class=tom value="book_price">
        价格
        <br><input type="button" id="submitBtn" class="tom" value="提交">
        <input type="reset" class=tom value="清空"/>
    </form>
</div>
<script type="text/javascript">
    window.onload = function () {
        //搜索书籍规范
        var searchMessElt = document.getElementById("searchMess");
        var searchMessErrorspan = document.getElementById("searchMessError");
        searchMessElt.onblur = function () {
            var searchMess = searchMessElt.value.trim();
            if (searchMess == "") {
                searchMessErrorspan.innerText = "搜索内容不能为空";
            }
        }
        searchMessElt.onfocus = function () {
            searchMessErrorspan.innerText = "";
        }
        var submitBtnElt = document.getElementById("submitBtn");
        submitBtnElt.onclick = function () {
            searchMessElt.focus();
            searchMessElt.blur();

            if (searchMessErrorspan.innerText == "") {
                var searchFormElt = document.getElementById("searchForm");
                searchFormElt.submit();
            }
        }
    }
</script>
</body>
</HTML>
