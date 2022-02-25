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
            //留言按钮
            $("#use_comment").click(function (){
                var content=prompt("请输入要留言的信息","");
                if(content){
                    location.href="commentServlet?action=createComment&user_ing_id=${sessionScope.user.user_id}&user_ed_id=${requestScope.user.user_id}&comment_content="+content;
                }
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

            $("#submit_user_info").click(function (){

                if($("#headimg").val()==""){
                    $("#flag").val("false");
                    alert("false");
                }else{
                    $("#flag").val("true");
                    alert("true");
                }
                return confirm("您确定要更资料吗？");
            });

            // 给注册绑定单击事件
            $("#update_password_sbt").click(function () {

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var oldPasswordText = $("#oldPassword").val();
                var newPasswordText = $("#newPassword").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(oldPasswordText)) {
                    //4 提示用户结果
                    $("#mes_mark").text("旧的密码不合法！");
                    return false;
                }
                if (!passwordPatt.test(newPasswordText)) {
                    //4 提示用户结果
                    $("#mes_mark").text("新的密码不合法！");
                    return false;
                }
                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var rePwdText = $("#rePassword").val();
                //2 和密码相比较
                if (rePwdText != newPasswordText) {
                    //3 提示用户
                    $("#mes_mark").text("确认密码和新密码不一致！");
                    return false;
                }
                // 去掉错误信息
                $("#mes_mark").text("");
            });
            if($("#flag_share").val()!=""){
                alert($("#flag_share").val());
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
            height: 400px;
            text-align: center;
            background-color: #f6cf99;
        }
        #t1 table tr td{
            background-color: #e3e3e3;
        }
        #center div{
            text-align: left;
        }
        .han_div{
            margin-left: 70px;
        }
    </style>
</head>
<body>
<%--静态包含用户菜单jsp--%>
<%@include file="../common/user_menubar.jsp"%>
<input id="flag_comment" type="hidden" name="flag_comment" value="${requestScope.flag_comment}" >
<div id="table">
    <div class="table_mark" style="background-color: #e3e3e3">
        <input class="back_left" type="button" onclick="back()" value="返回上一步">
        资料修改
        <input class="back" type="button" onclick="back()" value="返回上一步">
    </div>
    <div id="t1">
        <div style="width: 400px;height: 300px; margin: 50px 50px 50px 50px;">

            <input type="hidden" id="flag_share" name="flag_share" value="${requestScope.flag_share}">
            <form action="shareServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="createShare" >
                <input type="hidden" name="book_lenduser_id" value="${sessionScope.user.user_id}">

                <div id="center" style="height: 300px;width: 400px;background-color: #ffffff; float: left; margin-top: 50px;">
                    <div id="mes_mark" style="width: 400px;height: 30px; margin: 0px; background-color: #fff52c;font-family: 楷体; font-size: 25px;">
                        <%=request.getAttribute("mes")==null?"请输入图书信息":request.getAttribute("mes")%>
                    </div>
                    <div class="han_div">
                        <label>图书封面：</label>
                        <input type="file"  name="bookImg"/>
                    </div>
                    <div class="han_div">
                        <label>书籍名称：</label>
                        <input type="text"  name="book_name" placeholder="请输入图书名" />
                    </div>
                    <div class="han_div">
                        <label>图书作者：</label>
                        <input type="text"  name="book_author" placeholder="请输入图书作者" />
                    </div>
                    <div class="han_div">
                        <label>书出版社：</label>
                        <input type="text"  name="book_publisher" placeholder="请输入图书出版社" />
                    </div>
                    <div class="han_div">
                        <label>图书类型：</label>
                        <select name="book_type">
                            <option value="计算机科学">计算机科学</option>
                            <option value="文学">文学</option>
                            <option value="自然科学">自然科学</option>
                            <option value="工程技术">工程技术</option>
                            <option value="思想政治">思想政治</option>
                            <option value="艺术">艺术</option>
                        </select>
                    </div>
                    <div class="han_div">
                        <input type="reset" value="重置" style="margin-right: 30px;">
                        <input id="shareBook_sbt" type="submit" value="提交" style="margin-left: 30px;">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
