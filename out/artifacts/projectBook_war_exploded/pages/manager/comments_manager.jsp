<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
    <%@include file="../common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            //删除图书确认事件
            $(".delete_comment").click(function (){
                return confirm("你确定删除[ " +$(this).parent().parent().children('td').eq(1).text()+
                    " ]发给[ "+$(this).parent().parent().children('td').eq(2).text()+" ]的留言吗？");
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
            var color;
            //给所有数据行添加鼠标移入移出改变颜色事件
            $(".han").mouseenter(function (){
                color=$(this).css('background-color');
                this.style.backgroundColor="#ffc1a1";
            });
            $(".han").mouseout(function (){
                this.style.backgroundColor=color;
            });
            //给输入图书名关键字输入框添加选中去除文本内容事件
            $("#comment_content").focus(function (){
                $("#comment_content").val("");
            });
            //给查找留言按钮添加点击事件
            $("#findcomments").click(function (){
                var content=$("#comment_content").val();
                location.href="commentServlet?action=pageAllComments&content="+content;
            });
            //给所有留言按钮添加点击事件
            $("#all").click(function (){
                location.href="commentServlet?action=pageAllComments";
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
            mes_borrow();
            mes_back();
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
    </style>
</head>
<body>
    <%--静态包含管理员菜单jsp--%>
    <%@include file="../common/manager_menubar.jsp"%>
<div id="table">
    <c:if test="${empty requestScope.content}">
        <div class="table_mark">用户留言表</div>
    </c:if>
    <c:if test="${! empty requestScope.content}">
        <div class="table_mark">用户留言表【内容关键词(${requestScope.content})】</div>
    </c:if>
    <table id="books_table" >
        <tr>
            <th class="liename" style="width: 100px;">留言id</th>
            <th class="liename" style="width: 200px;">留言者昵称（id）</th>
            <th class="liename" style="width: 200px;">被留言者昵称（id）</th>
            <th class="liename" style="width: 400px;">留言内容</th>
            <th class="liename" style="width: 200px;">留言时间</th>
            <th class="liename" colspan="2">操作</th>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="comment">
            <tr class="han">
                <td>${comment.comment_id}</td>
                <td>${comment.user_ing_nickname}(${comment.user_ing_id})</td>
                <td>${comment.user_ed_nickname}(${comment.user_ed_id})</td>
                <td style="height: 20px;">${comment.comment_content}</td>
                <td>${comment.comment_time}</td>
                <td><a class="update_book" href="bookServlet?action=queryOneBook&id=${book.book_id}&pageNo=${requestScope.page.pageNo}&oldPath=${book.book_imgpath}">禁言</a></td>
                <td><a class="delete_comment" href="commentServlet?action=deleteCommentById&id=${comment.comment_id}&pageNo=${requestScope.page.pageNo}">删除留言</a></td>
            </tr>
        </c:forEach>
    </table>
    <%--分页开始--%>
    <div id="page">
        <div>
            <div style="float: left; margin:auto; margin-left: 10px;">
                <input id="comment_content" type="text" value="请输入留言关键词" >
                <input id="findcomments" type="button" value="查找">
                <input id="all" type="button" value="所有留言" style="margin-left: 20px;">
            </div>
            <c:choose>
                <%--情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
                <c:when test="${requestScope.page.pageTotal <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--情况 2：总页码大于 5 的情--%>
                <c:when test="${requestScope.page.pageTotal > 5}">
                    <c:choose>
                        <%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
                        <c:when test="${requestScope.page.pageNo <= 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <%--小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
                        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                            <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                            <c:set var="end" value="${requestScope.page.pageTotal}"/>
                        </c:when>
                        <%--小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
                        <c:otherwise>
                            <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                            <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>

            <c:if test="${requestScope.page.pageNo>1}">
                <a href="${requestScope.page.url}&pageNo=1&content=${requestScope.content}">首页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&content=${requestScope.content}">上一页</a>
            </c:if>


            <c:forEach begin="${begin}" end="${end}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}&content=${requestScope.content}">${i}</a>
                </c:if>
            </c:forEach>


            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&content=${requestScope.content}">下一页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&content=${requestScope.content}">末页</a>
            </c:if>

            共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
            到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input" style="width: 100px;"/>页
            <input id="searchPageBtn" type="button" value="确定">
            <script type="text/javascript">

                $(function () {
// 跳到指定的页码
                    $("#searchPageBtn").click(function () {
                        var pageNo = $("#pn_input").val();
                        <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                        <%--alert(pageTotal);--%>
                        // javaScript 语言中提供了一个 location 地址栏对象
                        // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
                        // href 属性可读，可写
                        location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                    });
                });
            </script>
        </div>

    </div>
    <%--分页结束--%>
</div>
    <%--静态包含页脚--%>
    <%@include file="/pages/common/footer.jsp"%>
</body>
</html>
