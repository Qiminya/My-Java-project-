<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <%@include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //返回首页
            $("#toindex").click(function (){
                location.href="index.jsp";
            });
            //用户管理
            $("#users_table_btn").click(function (){
                location.href="userServlet?action=pageAllUsers&pageNo=1";
            });
            //图书库管理
            $("#books_table_btn").click(function (){
                location.href="bookServlet?action=pageAllBooks&pageNo=1";
            });
            //给所有a标签添加鼠标移入事件,移入变色
            $("a").mouseenter(function (){
                this.style.color="red";
            });
            //给所有a标签添加鼠标移出事件,移出变色
            $("a").mouseout(function (){
                this.style.color="blue";
            });
            //给输入学籍号输入框添加选中去除文本内容事件
            $("#studentcode").focus(function (){
                $("#studentcode").val("");
            });
            //给查找用户按钮添加点击事件
            $("#findusers").click(function (){
                var studentcode=$("#studentcode").val();
                location.href="bookServlet?action=pageAllBooksByName&name="+name;
            });
            //给所有用户按钮添加点击事件
            $("#all").click(function (){
                location.href="userServlet?action=pageAllUsers";
            });
            mes_borrow();
            mes_back();
        });
    </script>
    <style>
        img{
            transition: all 0.6s;
        }
        img:hover{
            transform: scale(1.6);
        }
        #imge{
            overflow: hidden;
        }
        select{
            background-color: #2cffa0;
        }
        .left{
            text-align: right;
            width: 100px;
        }
        * {
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
<%--静态包含管理员菜单jsp--%>
<%@include file="../common/manager_menubar.jsp"%>
<div id="table">
        <div class="table_mark">【${requestScope.user.nickname}】的信息
        <input class="back" type="button" onclick="back()" value="返回上一步">
        </div>
    <table id="user_information_table">
        <tr>
            <td class="left">头像：</td>
            <td style="overflow: hidden"><img src="${requestScope.user.user_imgpath}"></td>
        </tr>
        <tr>
            <td class="left">id：</td>
            <td>${requestScope.user.user_id}</td>
        </tr>
        <tr>
            <td class="left">昵称：</td>
            <td>${requestScope.user.nickname}</td>
        </tr>
        <tr>
            <td class="left">账号：</td>
            <td>${requestScope.user.username}</td>
        </tr>
        <tr>
            <td class="left">密码：</td>
            <td><input type="password" value="${requestScope.user.password}"></td>
        </tr>
        <tr>
            <td class="left">姓名：</td>
            <td>${requestScope.user.name}</td>
        </tr>
        <tr>
            <td class="left">学籍号：</td>
            <td>${requestScope.user.studentcode}</td>
        </tr>
        <tr>
            <td class="left">性别：</td>
            <td>${requestScope.user.sex}</td>
        </tr>
        <tr>
            <td class="left">个性签名：</td>
            <td>${requestScope.user.signature}</td>
        </tr>
    </table>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
