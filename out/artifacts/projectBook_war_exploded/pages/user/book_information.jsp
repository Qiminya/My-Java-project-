<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            $(".booking").click(function (){
                var book_name=$(this).parent().parent().parent().find("tr").eq(1).find("td:last").text();
                if(confirm("您确定预约【"+book_name+"】吗？")){
                    location.href="bookingServlet?action=createBooking&user_id=${sessionScope.user.user_id}&book_id=${requestScope.book.book_id}";
                }
            });
            $("a").mouseenter(function (){
                this.style.color="red";
            });
            //给所有a标签添加鼠标移出事件,移出变色
            $("a").mouseout(function (){
                this.style.color="blue";
            });
            if(${!empty requestScope.mes_booking}){
                alert("${requestScope.mes_booking}");
            }
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
            width: 160px;
            height: 200px;
        }
        #t1{
            width: 500px;
            height: 500px;
            text-align: center;
        }
        #t1 table tr td{
            background-color: #e3e3e3;
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
    <div class="table_mark" style="background-color: #e3e3e3">图书信息
        <input class="back" type="button" onclick="back()" value="返回上一步">
    </div>
    <div id="t1">
        <table>
            <tr>
                <td>封面：</td>
                <td><img src="${requestScope.book.book_imgpath}"></td>
            </tr>
            <tr>
                <td>书名：</td>
                <td>${requestScope.book.book_name}</td>
            </tr>
            <tr>
                <td>作者：</td>
                <td>${requestScope.book.book_author}</td>
            </tr>
            <tr>
                <td>出版社：</td>
                <td>${requestScope.book.book_publisher}</td>
            </tr>
            <tr>
                <td>书主：</td>
                <td><a href="userServlet?action=queryOneUser_user&id=${requestScope.book.book_lenduser_id}">${requestScope.book.lend_nickname}</a></td>
            </tr>
            <tr>
                <td>图书类型：</td>
                <td>${requestScope.book.book_type}</td>
            </tr>
            <tr>
                <td>图书状态：</td>
                <td>${requestScope.book.book_state}</td>
            </tr>
            <tr>
                <td>最新借阅用户：</td>
                <td><a href="userServlet?action=queryOneUser_user&id=${requestScope.book.book_borrowuser_id}">${requestScope.book.borrow_nickname}</a></td>
            </tr>
            <tr>
                <td>借阅时间：</td>
                <td>${requestScope.book.borrow_time}</td>
            </tr>
            <tr>
                <td>归还情况：</td>
                <td>${requestScope.book.borrow_flag}</td>
            </tr>
            <tr>
                <td>被借阅次数：</td>
                <td>${requestScope.book.book_count_borrow}</td>
            </tr>
            <c:if test="${requestScope.book.book_state eq 'free'}">
                <tr>
                    <td>操作：</td>
                    <td><input class="booking" type="button" value="预约"></td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
