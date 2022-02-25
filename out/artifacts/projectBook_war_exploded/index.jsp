<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>起点书库首页</title>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            $("#login_user").click(function (){
                location="pages/user/login.jsp";
            });

            $("#regist_user").click(function (){
                location="pages/user/regist.jsp";
            });

            $("#login_admin").click(function (){
                location="pages/manager/login.jsp";
            });
            //right
            $("#right").mousemove(function (){
                $("#right").css("background","pink");
            });
            $("#right").mouseout(function (){
                $("#right").css("background","darkgrey");
            });
        });
    </script>
</head>
<body id="index">

<div id="header" style="position: relative">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <input id="login_user" type="button" value="登录" href="pages/user/login.jsp" style="width: 80px; right: 220px;"/>
    <input id="regist_user" type="button" value="注册" href="pages/user/regist.jsp" style="width: 80px;right: 130px;;"/>
    <input id="login_admin" type="button" value="管理员登录" href="pages/manager/login.jsp" style="width: 120px;right: 0px;"/>
</div>

<div style="font-size: 45px;width: 1200px;height: 120px; margin:0 auto;text-align: center;color: white;">
    <br/>
    <span style="font-family: 华文琥珀; color: white; font-size: 40px;">遨游知识的星空，开启属于知识的浪漫之旅</span>
</div>

<div style="height: 440px;"></div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>