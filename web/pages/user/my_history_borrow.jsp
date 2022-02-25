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
  <div class="table_mark" style="background-color: #e3e3e3">我借过的图书</div>
  <c:forEach items="${requestScope.page.items}" var="book">
    <div class="book_div1">
      <div class="book_div2"><img class="img" src="${book.book_imgpath}"></div>
      <div style="margin: 0px; background-color: #e3e3e3">
        <span><a href="bookServlet?action=getBookById&id=${book.book_id}">${book.book_name}</a></span>
      </div>
    </div>
  </c:forEach>
  <%--分页开始--%>
  <div id="page" style="float: left; margin-left: 10px;">
    <div>
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
        <a href="${requestScope.page.url}&pageNo=1&user_id=${sessionScope.user.user_id}">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}&user_id=${sessionScope.user.user_id}">上一页</a>
      </c:if>


      <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
          【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
          <a href="${requestScope.page.url}&pageNo=${i}&user_id=${sessionScope.user.user_id}">${i}</a>
        </c:if>
      </c:forEach>


      <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}&user_id=${sessionScope.user.user_id}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}&user_id=${sessionScope.user.user_id}">末页</a>
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
