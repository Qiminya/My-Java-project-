function prom(){
    var book_id = prompt("请输入您要修改的图书id","");
    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值, 所以我在哪里传了个空
    if(book_id)//如果返回的有内容
    {
        location.href="bookServlet?action=queryOneBook&id="+book_id;
    }
}

function back(){
    history.back();
}

function userBorrow(){
    var user_id = prompt("借书用户id","");
    if(user_id==""||user_id==null)
        return;
    var book_id = prompt("所借图书id","");
    if (book_id==""||user_id==null)
        return;
    if(book_id&&book_id)//如果返回的有内容
    {
        location.href="borrowServlet?action=userBorrow&user_id="+user_id+"&book_id="+book_id;
    }else{
        alert("请将信息填写完整");
    }
}

function userBack(){
    var book_id = prompt("归还图书id","")
    if(book_id==""||book_id==null){
        return;
    }
    if(book_id){
        location.href="backServlet?action=userBackBook&book_id="+book_id;
    }
}
function change_info(){
    location.href="pages/user/change_information.jsp";
}

function change_paswd(){
    location.href="pages/user/update_password.jsp";
}

/**
 * 用户借书操作结果提醒
 */
function mes_borrow(){
    var flag=$("#flag_borrow").val();
    var str;
    if (flag!=""){
        switch (parseInt(flag)) {
            case -4:
                str="无该id的图书！！！";
                break;
            case -3:
                str="无此id用户！！！";
                break;
            case -2:
                str="输入id非法为存数字）！！！";
                break;
            case -1:
                str="该用户借书数量达最大值！！！";
                break;
            case 0:
                str="该书已被借阅！！！";
                break;
            case 1:
                str="图书借阅操作完成！！！";
                break;
        }
        alert(str);
    }
}

/**
 * 用户还书操作结果提醒
 */
function mes_back(){
    var flag=$("#flag_back").val();
    var str;
    if (flag!=""){
        switch (parseInt(flag)) {
            case -2:
                str="该id的图书不存在！！！";
                break;
            case -1:
                str="输入id非法（应为纯数字）！！！";
                break;
            case 0:
                str="该图书未被借阅！！！";
                break;
            case 1:
                str="图书归还成功！！！";
                break;
        }
        alert(str);
    }
}


