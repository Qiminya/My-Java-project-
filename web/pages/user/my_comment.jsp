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
            if($("#flag_comment").val()!=""){
                alert($("#flag_comment").val());
            }
        });
        function user_comment(user_ing_id,user_ed_id){
            var comment_content = prompt("请输入回复的内容", "");
            if(comment_content){
                location.href="commentServlet?action=createComment&user_ing_id="+user_ing_id+"&user_ed_id="+user_ed_id+"&comment_content="+comment_content;
            }
        }
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
            height: 90px;
            background-color: #2cffa0;
        }
    </style>
</head>
<body>
<%--静态包含用户菜单jsp--%>
<%@include file="../common/user_menubar.jsp"%>
<input id="flag_comment" type="hidden" name="flag_comment" value="${requestScope.flag_comment}" >
<div id="table">
    <div class="table_mark" style="background-color: #e3e3e3">我的留言板</div>
    <c:forEach items="${requestScope.page.items}" var="comment">
        <div class="comment">
            <a href="userServlet?action=queryOneUser_user&id=${comment.user_ing_id}">${comment.user_ing_nickname}</a>
            <span>对你说：${comment.comment_content}</span>
            <span style="float: right; margin-top: 70px;">
                <a href="javascript:user_comment(${sessionScope.user.user_id}${comment.user_ing_id})">回复</a>
                留言时间：${comment.comment_time}</span>
        </div>
        <hr style="color: #ff2ced; width: 1260px; text-align: center; size: A5;">
    </c:forEach>


    <%--分页开始--%>
    <div id="page" style="float: left; margin-left: 0px;">
        <div>
            <div style="float: left; margin:auto; margin-left: 10px;">
                <input id="name" type="text" value="请输入图书名关键词" >
                <input id="findbooks" type="button" value="查找">
                <input id="all" type="button" value="所有图书" style="margin-left: 20px;">
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
                <a href="${requestScope.page.url}&pageNo=1&user_id=${sessionScope.user.user_id}&nickname=${requestScope.nickname}">首页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&user_id=${sessionScope.user.user_id}&nickname=${requestScope.nickname}">上一页</a>
            </c:if>


            <c:forEach begin="${begin}" end="${end}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}&user_id=${sessionScope.user.user_id}&nickname=${requestScope.nickname}">${i}</a>
                </c:if>
            </c:forEach>


            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&user_id=${sessionScope.user.user_id}&nickname=${requestScope.nickname}">下一页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&user_id=${sessionScope.user.user_id}&nickname=${requestScope.nickname}">末页</a>
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
