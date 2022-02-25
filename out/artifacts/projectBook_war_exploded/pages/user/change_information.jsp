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
                }else{
                    $("#flag").val("true");
                }
                return confirm("您确定要修改信息吗？");
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
        .lie{
            width: 150px;
            height: 30px;
        }
    </style>
</head>
<body>
<%--静态包含用户菜单jsp--%>
<%@include file="../common/user_menubar.jsp"%>
<input id="flag_comment" type="hidden" name="flag_comment" value="${requestScope.flag_comment}" >
<div id="table">
    <div class="table_mark" style="background-color: #e3e3e3">资料修改
        <input class="back" type="button" onclick="back()" value="返回上一步">
    </div>
    <div id="t1">
        <form action="userServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="updateUser_info">
            <input type="hidden" name="user_id" value="${sessionScope.user.user_id}">
            <input type="hidden" name="oldPath" value="${sessionScope.user.user_imgpath}">
            <input id="flag" type="hidden" name="selectFlag">
            <table style="width: 500px;height: 500px;">
                <tr>
                    <td class="lie">头像：</td>
                    <td ><input id="headimg" name="img_path" type="file"></td>
                </tr>
                <tr>
                    <td class="lie">昵称：</td>
                    <td><input name="nickname" type="text" value="${sessionScope.user.nickname}"></td>
                </tr>
                <tr>
                    <td class="lie">姓名：</td>
                    <td><input name="name" type="text" value="${sessionScope.user.name}"></td>
                </tr>
                <tr>
                    <td class="lie">学籍号：</td>
                    <td><input name="studentcode" type="text" value="${sessionScope.user.studentcode}"></td>
                </tr>
                <tr>
                    <td class="lie">性别：</td>
                    <td>
                        <select name="sex">
                            <option>${sessionScope.user.sex}</option>
                            <c:if test="${sessionScope.user.sex eq '男'}">
                                <option value="女">女</option>
                            </c:if>
                            <c:if test="${sessionScope.user.sex eq '女'}">
                                <option value="男">男</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="lie" style="height: 150px;">个性签名：</td>
                    <td><textarea name="signature" rows="4" cols="40" >${sessionScope.user.signature}</textarea></td>
                </tr>
                    <tr>
                        <td class="lie"><input type="reset" value="重置"></td>
                        <td><input id="submit_user_info" type="submit" value="更改"></td>
                    </tr>
            </table>
        </form>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
