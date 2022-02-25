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
            mes_borrow();
            mes_back();
        });
    </script>
    <style>
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
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
