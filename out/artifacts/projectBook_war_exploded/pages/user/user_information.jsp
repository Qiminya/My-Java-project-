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
            if($("#flag_comment").val()!=""){
                alert($("#flag_comment").val());
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
    <div class="table_mark" style="background-color: #e3e3e3">用户资料
        <input class="back" type="button" onclick="back()" value="返回上一步">
    </div>
    <div id="t1">
        <table style="width: 500px;height: 500px;">
            <tr>
                <td class="lie">头像：</td>
                <td ><img src="${requestScope.user.user_imgpath}"></td>
            </tr>
            <tr>
                <td class="lie">昵称：</td>
                <td>${requestScope.user.nickname}</td>
            </tr>
            <tr>
                <td class="lie">姓名：</td>
                <td>${requestScope.user.name}</td>
            </tr>
            <tr>
                <td class="lie">性别：</td>
                <td><a href="userServlet?action=queryOneUser_user&id=${requestScope.book.book_lenduser_id}">${requestScope.book.lend_nickname}</a></td>
            </tr>
            <tr>
                <td class="lie">权限等级：</td>
                <td>${requestScope.user.power_grade}</td>
            </tr>
            <tr>
                <td class="lie">当前借书数量：</td>
                <td>${requestScope.user.now_count_borrow}</td>
            </tr>
            <tr>
                <td class="lie">总借入数量：</td>
                <td><a href="userServlet?action=queryOneUser_user&id=${requestScope.book.book_borrowuser_id}">${requestScope.user.count_borrow}</a></td>
            </tr>
            <tr>
                <td class="lie">分享图书数量：</td>
                <td>${requestScope.user.count_lend}</td>
            </tr>
            <tr>
                <td class="lie" style="height: 150px;">个性签名：</td>
                <td>${requestScope.user.signature}</td>
            </tr>
            <c:if test="${sessionScope.user.user_id !=requestScope.user.user_id}">
                <tr>
                    <td class="lie"><input id="use_comment" type="button" value="留言"></td>
                    <td><input type="button" value="举报"></td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
