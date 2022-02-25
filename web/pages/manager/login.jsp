<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录页面</title>
    <%--静态包含base和引入css、jquery--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //返回首页绑定事件
            $("#loginToIndex").click(function (){
                location="index.jsp";
            });
        });
    </script>
</head>
<body>
<div id="login_header" style="position: relative">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <input id="loginToIndex" type="button" value="返回首页" href="pages/user/regist.jsp"
           style="width: 120px;height: 30px;color: blue;font-size: 20px;position: absolute;right: 0px;bottom: 0px;"/>
</div>

<div class="login_banner">
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员登录</h1>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg"><%=request.getAttribute("mes")==null?"请输入管理员账号和密码":request.getAttribute("mes")%></span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="adminlogin">
                        <label>账号：</label>
                        <input class="itxt" type="text" placeholder="请输入管理员账号"
                               autocomplete="off" tabindex="1" name="username"
                               value="<%=request.getAttribute("username")==null?"":
											request.getAttribute("username")%>" />
                        <br />
                        <br />
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" />
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="height: 85px;"></div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>