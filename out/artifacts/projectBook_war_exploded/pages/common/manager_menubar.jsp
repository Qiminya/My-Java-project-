<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    /*菜单*/
    #div1{
        float: left;
        width: 760px;
        height: 30px;
        margin: 0px 0px 0px 0px;
        text-align: right;
    }
    #mes{
        line-height: 30px;
        margin-right: 20px;
        color: red;
    }
    #navigator {
        height:30px;
        width: 1534px;
        font-size: 14px;
        margin-top: 0px;
        background-color: #d2bfa5;
    }
    #navigator ul {
        list-style-type: none;
    }
    #navigator li {
        float: left;
        position: relative;
    }
    #navigator li a {
        color: rgba(0, 60, 255, 0.99);
        text-decoration: none;
        padding-top: 3px;
        display: block;
        width: 120px;
        height: 25px;
        text-align: center;
        background-color: rgba(255, 255, 255, 0.99);
        margin-left: 4px;
    }
    #navigator li a:hover {
        background-color: rgb(11, 196, 174);
        color: #FFFFFF;
    }
    #navigator ul li ul {
        visibility: hidden;
        position: absolute;
    }

    #navigator ul li:hover ul,
    #navigator ul a:hover ul{
        visibility: visible;
    }
</style>
<div id="personal_header" style="position: relative;">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span style="font-family: 华文琥珀;line-height:70px;font-size: 40px;color: rgba(8,166,187,0.94)">欢迎管理员进入后台管理系统</span>
</div>
<div id="div1">
    <p id="mes">${requestScope.mes}</p>
</div>
<div id="navigator">
    <ul>
        <li><a>图书管理</a>
            <ul>
                <li><a href="pages/manager/book_edit.jsp?pageNo=1000000">添加图书</a></li>
                <li><a href="javascript:prom()">修改图书</a></li>
                <li><a href="bookServlet?action=pageAllBooks&pageNo=1">图书资源库</a></li>
            </ul>
        </li>
        <li><a>请求审批</a>
            <ul>
                <li><a href="shareServlet?action=pageAllShares&pageNo=1" >分享图书审核</a></li>
                <li><a href="bookingServlet?action=pageAllBookings&user_name=">预约请求处理</a></li>
            </ul>
        </li>
        <li><a>用户操作</a>
            <ul>
                <li><a href="javascript:userBack()">用户图书归还</a></li>
                <li><a href="javascript:userBorrow()">用户图书借阅</a></li>
            </ul>
        </li>
        <li><a>记录查询</a>
            <ul>
                <li><a href="borrowServlet?action=pageAllBorrows&pageNo=1" >用户借阅记录</a></li>
                <li><a href="backServlet?action=pageAllBacks&pageNo=1">用户还书记录</a></li>
                <li><a href="upServlet?action=pageAllUps&pageNo=1&book_id=">图书上架记录</a></li>
                <li><a href="downServlet?action=pageAllDowns&pageNo=1&book_id=">图书下架记录</a></li>
            </ul>
        </li>
        <li><a>用户管理</a>
            <ul>
                <li><a href="userServlet?action=pageAllUsers&pageNo=1">用户信息管理</a></li>
                <li><a href="commentServlet?action=pageAllComments&pageNo=1">用户留言管理</a></li>
            </ul>
        </li>
        <li><a>用户管理</a>
            <ul>
                <li><a href="index.jsp">注销登录</a></li>
                <li><a href="pages/manager/personal.jsp">管理员首页</a></li>
            </ul>
        </li>
    </ul>
</div>
<div id=personal_select style="float: left;height: 530px;">
</div>
<input type="hidden" id="flag_back" value="${requestScope.flag_back}">
<input type="hidden" id="flag_borrow" value="${requestScope.flag_borrow}">