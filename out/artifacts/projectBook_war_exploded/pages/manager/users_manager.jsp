<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员首页</title>
    <%@include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //给重置密码a标签添加缺人按钮
            $(".init_password").click(function (){
                return confirm("您确定重置id为"+$(this).parent().parent().find("td:first").text()+"的用户密码吗？");
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
            //给输入学籍号输入框添加选中去除文本内容事件
            $("#studentcode").focus(function (){
                $("#studentcode").val("");
            });
            //给查找用户按钮添加点击事件
            $("#findusers").click(function (){
                var studentcode=$("#studentcode").val();
                location.href="userServlet?action=pageAllUsers&studentcode="+studentcode;
            });
            //给所有用户按钮添加点击事件
            $("#all").click(function (){
                location.href="userServlet?action=pageAllUsers";
            });
            if("<%=request.getParameter("flag")%>"=="1"){
                alert("密码重置成功")
            }else if("<%=request.getParameter("flag")%>"=="-1"){
                alert("密码重置失败")
            }
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
        <c:if test="${empty requestScope.studentcode}">
            <div class="table_mark">用户信息表${requestScope.studentcode}</div>
        </c:if>
        <c:if test="${! empty requestScope.studentcode}">
            <div class="table_mark">用户信息表【查询学籍号关键词(${requestScope.studentcode})】</div>
        </c:if>
        <table id="books_table" >
            <tr>
                <th class="liename" style="width: 120px;">用户id</th>
                <th class="liename" style="width: 140px;">姓名</th>
                <th class="liename">学籍号</th>
                <th class="liename">权限等级</th>
                <th class="liename">当前借书</th>
                <th class="liename">借书次数</th>
                <th class="liename">分享次数</th>
                <th class="liename">逾期次数</th>
                <th  class="liename" colspan="2">操作</th>
            </tr>
            <c:forEach items="${requestScope.page.items}" var="user">
                <tr class="han">
                    <td>${user.user_id}</td>
                    <td><a href="userServlet?action=queryOneUser&id=${user.user_id}">${user.name}</a></td>
                    <td>${user.studentcode}</td>
                    <td>${user.power_grade}</td>
                    <td>${user.now_count_borrow}</td>
                    <td>${user.count_borrow}</td>
                    <td>${user.count_lend}</td>
                    <td>${user.count_late}</td>
                    <td><a class="init_password" href="userServlet?action=initPassword&id=${user.user_id}&pageNo=${requestScope.page.pageNo}">重置密码</a></td>
                    <td><a class="delete_book" href="bookServlet?action=deleteBook&id=${book.book_id}&pageNo=${requestScope.page.pageNo}">封禁</a></td>
                </tr>
            </c:forEach>
        </table>
        <%--分页开始--%>
        <div id="page">
            <div>
                <div style="float: left; margin:auto; margin-left: 10px;">
                    <input id="studentcode" type="text" value="请输入学籍号关键词" >
                    <input id="findusers" type="button" value="查找">
                    <input id="all" type="button" value="所有用户" style="margin-left: 20px;">
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
                    <a href="${requestScope.page.url}&pageNo=1&studentcode=${requestScope.studentcode}">首页</a>
                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&studentcode=${requestScope.studentcode}">上一页</a>
                </c:if>


                <c:forEach begin="${begin}" end="${end}" var="i">
                    <c:if test="${i == requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}&pageNo=${i}&studentcode=${requestScope.studentcode}">${i}</a>
                    </c:if>
                </c:forEach>


                <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&studentcode=${requestScope.studentcode}">下一页</a>
                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&studentcode=${requestScope.studentcode}">末页</a>
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
