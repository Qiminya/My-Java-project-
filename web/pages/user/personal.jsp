<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            //返回首页
            $("#toindex").click(function (){
                location="index.jsp";
            });

            //修改表单提交点击事件
            $("#submit_update").click(function (){
                if($("#upload_update").val()==""){
                    $("#flag").val("false");
                }else{
                    $("#flag").val("true");
                }
                return confirm("您确定要更改用户分享图书的信息吗的信息吗？");
            });
            $("a").mouseenter(function (){
                this.style.color="red";
            });
            //给所有a标签添加鼠标移出事件,移出变色
            $("a").mouseout(function (){
                this.style.color="blue";
            });
        });
    </script>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }
        img{
            transition: all 1s;
        }
        img:hover{
            transform: scale(1.4);
        }
        .img{
            margin-top: 0px;
            overflow: hidden;
            width: 140px;
            height: 160px;
        }
        div{
            border: black solid 1px;
        }
    </style>
</head>
<body>
<%--静态包含用户菜单jsp--%>
<%@include file="../common/user_menubar.jsp"%>

<div id="table">
    <div class="table_mark" style="background-color: #e3e3e3">热门图书</div>
    <c:forEach items="${requestScope.books}" var="book">
        <div class="hotbooks">
            <div class="img"><img class="hotimg" src="${book.book_imgpath}"></div>
            <ul style="text-align: center">
                <li><a href="bookServlet?action=getBookById&id=${book.book_id}">${book.book_name}</a></li>
                <li>借阅次数：${book.book_count_borrow}</li>
            </ul>
        </div>
    </c:forEach>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
