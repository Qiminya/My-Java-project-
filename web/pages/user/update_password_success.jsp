<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册成功</title>
    <%--静态包含base和引入css、jquery--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //登录跳转页面
            $("#login_user").click(function (){
                location="pages/user/login.jsp";
            });
            //返回首页跳转页面
            $("#button").click(function (){
                location="pages/user/login.jsp";
            });
        });
    </script>
</head>
<body id="regist_success">
<div id="header" style="position: relative">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <input id="login_user" type="button" value="登录"  style="width: 80px; right: 130px;"/>
    <input id="toindex" type="button" value="返回首页" style="width: 120px;right: 0px;"/>
</div>

<div style="text-align: center; height: 550px;">

    <br/><br/><br/><br/>
    <span style="font-family: 华文琥珀; font-size: 45px;color: aqua;">密码修改成功!请登录！</span>
</div>


<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>