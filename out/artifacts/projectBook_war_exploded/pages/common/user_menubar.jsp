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
    td{
        border: black 1px solid;
    }
</style>
<div id="personal_header" style="position: relative;">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span style="font-family: 华文琥珀;line-height:70px;font-size: 40px;color: rgba(8,166,187,0.94)">${sessionScope.user.signature}</span>
</div>
<div id="div1">
    <p id="mes">${requestScope.mes}</p>
</div>
<div id="navigator">
    <ul>
        <li><a>起点书库</a>
            <ul>
                <li><a href="bookServlet?action=pageAllBooks_user&pageNo=1">图书资源库</a></li>
            </ul>
        </li>
        <li><a>借阅与分享</a>
            <ul>
                <li><a href="bookServlet?action=pageAllMyLendBooks&pageNo=1&user_id=${sessionScope.user.user_id}" >我的分享</a></li>
                <li><a href="bookingServlet?action=queryMyBooking&user_id=${sessionScope.user.user_id}">我的预约</a></li>
                <li><a href="bookServlet?action=getNowBorrowBooks&user_id=${sessionScope.user.user_id}">当前借入</a></li>
                <li><a href="bookServlet?action=page_userBorrow&user_id=${sessionScope.user.user_id}">往期借入</a></li>
            </ul>
        </li>
        <li><a>图书分享</a>
            <ul>
                <li><a href="shareServlet?action=queryShareByUser_id&user_id=${sessionScope.user.user_id}">待审核图书</a></li>
                <li><a href="pages/user/user_sharebook.jsp">分享图书</a></li>
            </ul>
        </li>
        <li><a>交流社区</a>
            <ul>
                <li><a href="commentServlet?action=pageAllComments_in&pageNo=1&user_id=${sessionScope.user.user_id}" >我的留言板</a></li>
                <li><a href="commentServlet?action=pageAllComments_out&pageNo=1&user_id=${sessionScope.user.user_id}">我留的言</a></li>
            </ul>
        </li>
        <li><a>用户管理</a>
            <ul>
                <li><a >用户信息管理</a></li>
                <li><a >用户留言管理</a></li>
            </ul>
        </li>
        <li><a>账号设置</a>
            <ul>
                <li><a href="index.jsp">注销登录</a></li>
                <li><a href="bookServlet?action=queryHotBooks">个人首页</a></li>
            </ul>
        </li>
    </ul>
</div>
<div id=personal_select style="float: left;height: 550px;">
    <table id="personal_table">
        <tr>
            <td>头像：</td>
            <td><img src="${sessionScope.user.user_imgpath}"></td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>${sessionScope.user.nickname}</td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td>${sessionScope.user.name}</td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>${sessionScope.user.sex}</td>
        </tr>
        <tr>
            <td>学籍号：</td>
            <td>${sessionScope.user.studentcode}</td>
        </tr>
        <tr>
            <td>当前借阅数：</td>
            <td>${sessionScope.user.now_count_borrow}</td>
        </tr>
        <tr>
            <td>可借阅数：</td>
            <td></td>
        </tr>
        <tr>
            <td>分享图书数：</td>
            <td>${sessionScope.user.count_lend}</td>
        </tr>
        <tr>
            <td>总借书数：</td>
            <td>${sessionScope.user.count_borrow}</td>
        </tr>
        <tr>
            <td>超期次数：</td>
            <td>${sessionScope.user.count_late}</td>
        </tr>
        <tr>
            <td><input id="set_info" type="button" onclick="change_info()" value="资料设置"></td>
            <td><input id="update_password" onclick="change_paswd()" type="button" value="修改密码"></td>
        </tr>
    </table>
</div>