<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <%@include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //给重置密码a标签添加缺人按钮
            $(".backBook").click(function (){
                return confirm("图书【"+$(this).parent().parent().children('td').eq(2).text()+"】归还吗？");
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
            //给查找用户按钮添加点击事件
            $("#finddowns").click(function (){
                var book_id=$("#book_id").val();
                location.href="downServlet?action=pageAllDowns&book_id="+book_id;
            });
            //给所有下架记录按钮添加点击事件
            $("#all").click(function (){
                location.href="downServlet?action=pageAllDowns&book_id=";
            });

            /*//隔行变色
            var i=1;
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
    <c:if test="${empty requestScope.book_id}">
        <div class="table_mark">图书下架记录${requestScope.name}</div>
    </c:if>
    <c:if test="${! empty requestScope.book_id}">
        <div class="table_mark">图书下架记录【查询id关键词(${requestScope.book_id})】</div>
    </c:if>
    <table id="books_table" >
        <tr>
            <th class="liename" style="width: 180px;">下架图书</th>
            <th class="liename" style="width: 120px;">下架图书id</th>
            <th class="liename" style="width: 200px;">下架时间</th>
            <th class="liename" style="width: 300px;">下架原因</th>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="down">
            <tr class="han">
                <td>${down.book_name}</td>
                <td>${down.book_id}</td>
                <td>${down.down_time}</td>
                <td>${down.down_cause}</td>
            </tr>
        </c:forEach>
    </table>
    <%--分页开始--%>
    <div id="page">
        <div>
            <div style="float: left; margin:auto; margin-left: 10px;">
                <input id="book_id" type="text" placeholder="请输入图书id" >
                <input id="finddowns" type="button" value="查找">
                <input id="all" type="button" value="所有记录" style="margin-left: 20px;">
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
                <a href="${requestScope.page.url}&pageNo=1&book_id=${requestScope.book_id}">首页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&book_id=${requestScope.book_id}">上一页</a>
            </c:if>


            <c:forEach begin="${begin}" end="${end}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}&book_id=${requestScope.book_id}">${i}</a>
                </c:if>
            </c:forEach>


            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&book_id=${requestScope.book_id}">下一页</a>
                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&book_id=${requestScope.book_id}">末页</a>
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
