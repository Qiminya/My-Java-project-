<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>起点密码找回页面</title>

    <%--静态包含base和引入css、jquery--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {
            //返回首页绑定事件
            $("#loginToIndex").click(function (){
                location="index.jsp";
            });
            //给验证吗图片添加单击事件
            $("#code_img").click(function (){
                this.src="${basePath}Kaptcha.jpg?d="+new Date();
            });
            // 给注册绑定单击事件
            $("#sub_btn").click(function () {

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }
                // 去掉错误信息
                $("span.errorMsg").text("");
            });

        });

    </script>
    <style type="text/css">
        .login_form{
            height:310px;
            margin-top: 50px;
        }
    </style>

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
                    <h1>找回密码</h1>
                    <span class="errorMsg"><%=request.getAttribute("mes")==null?"":request.getAttribute("mes")%></span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="resetPassword">
                        <input type="hidden" name="user_id" value=${requestScope.user_id}>
                        <label>新的密码：</label>
                        <input class="itxt" type="text" placeholder="请输入新密码"
                               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"
                               autocomplete="off" tabindex="1" name="password" id="password" />
                        <br />
                        <br />
                        <label>确认密码：</label>
                        <input class="itxt" type="text" placeholder="确认密码"
                               value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd" />
                        <br />
                        <br />
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 100px;" id="code" value="<%=request.getAttribute("code")==null?"":request.getAttribute("code")%>"/>
                        <img alt="" id="code_img" src="Kaptcha.jpg" style=" height: 38px; width: 120px; float: right; margin-right: 50px">
                        <br />
                        <br />
                        <input type="submit" value="密码更改" id="sub_btn" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>