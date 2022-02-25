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
            $("a").mouseenter(function (){
                this.style.color="red";
            });
            //给所有a标签添加鼠标移出事件,移出变色
            $("a").mouseout(function (){
                this.style.color="blue";
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
        .comment{
            width: 1200px;
            height: 100px;
            background-color: #2cffa0;
        }
    </style>
</head>
<body>

</body>
</html>
