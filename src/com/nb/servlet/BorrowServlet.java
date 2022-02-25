package com.nb.servlet;

import com.nb.pojo.Book;
import com.nb.pojo.Borrow;
import com.nb.pojo.Page;
import com.nb.sevice.BorrowService;
import com.nb.sevice.impl.BorrowServiceImpl;
import com.nb.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BorrowServlet extends BaseServlet{
    BorrowService borrowService=new BorrowServiceImpl();
    public void pageAllBorrows(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取当前页数
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo=1;
        }else {
            pageNo=Integer.parseInt(req.getParameter("pageNo"));
        }
        //获取输入的名字关键字
        String name=req.getParameter("name");
        //如果name为空赋值为空串查询所有记录
        if(name==null){
            name="";
        }
        //设置每页数据数
        int pageSize=18;
        Page<Borrow> page= borrowService.pageAllBorrows(pageNo,pageSize,name);
        page.setUrl("borrowServlet?action=pageAllBorrows");
        //将分页与姓名保存到域中
        req.setAttribute("name",name);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/borrows_manager.jsp").forward(req,resp);
    }
    public void userBorrow(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取借书者id，图书id
        String u_id=req.getParameter("user_id");
        String b_id=req.getParameter("book_id");
        int flag;
        if(WebUtils.isNum(u_id)&&WebUtils.isNum(b_id)){
            int user_id=Integer.parseInt(u_id);
            int book_id=Integer.parseInt(b_id);
            flag=borrowService.userBorrow(user_id,book_id);
            req.removeAttribute("state");
//            if(flag==-1){
//                req.setAttribute("mes","该用户借书数量达最大值！！！");
//            }else if(flag==0){
//                req.setAttribute("mes","该书已被借阅！！！");
//            }else if(flag==1){
//                req.setAttribute("mes","图书借阅操作完成！！！");
//            }
        }else{
            flag=-2;//输入id非法，(应该为存数字)
        }
        req.removeAttribute("flag_back");
        String lastUrl=req.getHeader("referer");
        resp.sendRedirect(lastUrl+"&flag_borrow="+flag);
    }
}
