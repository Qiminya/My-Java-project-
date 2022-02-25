<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            //删除图书确认事件
            $(".delete_book").click(function (){
                return confirm("你确定下架id为" +$(this).parent().parent().children('td').eq(1).text()+
                    "的【"+$(this).parent().parent().children('td').eq(2).text()+"】吗？");
            });
            //给状态栏添加事件
            $("#0_state").click(function (){
                location.href="bookServlet?action=pageAllBooks";
            });
            //空闲
            $("#1_state").click(function (){
                location.href="bookServlet?action=pageAllBooks&state=free";
            });
            //已被预约
            $("#2_state").click(function (){
                location.href="bookServlet?action=pageAllBooks&state=booking";
            });
            //已被借阅
            $("#3_state").click(function (){
                location.href="bookServlet?action=pageAllBooks&state=borrowing";
            });
            //已下架
            $("#4_state").click(function (){
                location.href="bookServlet?action=pageAllBooks&state=out";
            });
            //给所有a标签添加鼠标移入事件,移入变色
            $("a").mouseenter(function (){
                this.style.color="red";
            });
            //给所有a标签添加鼠标移出事件,移出变色
            $("a").mouseout(function (){
                this.style.color="blue";
            });
            //给所有数据行添加鼠标移入移出改变颜色事件
            $(".han").mouseenter(function (){
                color=$(this).css('background-color');
                this.style.backgroundColor="#f6cf99";
            });
            $(".han").mouseout(function (){
                this.style.backgroundColor=color;
            });
            //给输入图书名关键字输入框添加选中去除文本内容事件
            $("#name").focus(function (){
                $("#name").val("");
            });
            //给查找图书按钮添加点击事件
            $("#findbooks").click(function (){
                var name=$("#name").val();
                location.href="bookServlet?action=pageAllBooks&name="+name;
            });
            //给所有图书按钮添加点击事件
            $("#all").click(function (){
                location.href="bookServlet?action=pageAllBooks";
            });
            //隔行变色
            /*var i=1;
            var a=$(".han")
            var n=a.length;
            for(i=0;i<=n-1;i++){
                if(i%2==0){
                    a[i].style.backgroundColor="khaki";
                }
            }*/
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
        .liename{
            background-color: #2cffa0;
        }
        select{
            background-color: #2cffa0;
        }


        * {
            margin: 0px;
            padding: 0px;
        }
        .book_div1{
            width: 130px;
            height: 200px;
            float: left;
            margin: 10px 20px 10px 30px;
            text-align: center;
        }
        .book_div2{
            overflow: hidden;
            text-align: center;
            width: 130px;
            height: 160px;
            margin: 0px;
        }
        .img{

            width: 130px;
            height: 160px;
        }
        .book_div1 div span a{
            font-size: 12px;
        }
    </style>
</head>
<body>
<%--静态包含用户菜单jsp--%>
<%@include file="../common/user_menubar.jsp"%>
<div id="table">
    <div class="table_mark" style="background-color: #e3e3e3">已提交的分享图书</div>
    <c:forEach items="${requestScope.shares}" var="share">
        <div class="book_div1">
            <div class="book_div2"><img class="img" src="${share.book_imgpath}"></div>
            <div style="margin: 0px; background-color: #e3e3e3">
                <span><a href="bookServlet?action=getBookById&id=">${share.book_name}</a></span>
            </div>
        </div>
    </c:forEach>
</div>
<%--静态包含页脚--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
