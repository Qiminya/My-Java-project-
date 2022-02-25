//用户借阅管理
$("#borrows_table_btn").click(function (){
    location.href="borrowServlet?action=pageAllBorrows&pageNo=1";
});
//用户管理
$("#users_table_btn").click(function (){
    location.href="userServlet?action=pageAllUsers&pageNo=1";
});
//图书库管理
$("#books_table_btn").click(function (){
    location.href="bookServlet?action=pageAllBooks&pageNo=1";
});