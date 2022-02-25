<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/9/26
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临起点图书库</span>
    <a href="pages/order/order.jsp">个人中心</a>
    <a href="userServlet?action=logout">注销</a>
    <a href="index.jsp">返回</a>
</div>

