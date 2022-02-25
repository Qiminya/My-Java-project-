package com.nb.servlet;

import com.nb.pojo.Back;
import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.sevice.BackService;
import com.nb.sevice.impl.BackServiceImpl;
import com.nb.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BackServlet extends BaseServlet{
    private BackService backService=new BackServiceImpl();
    public void pageAllBacks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 18;
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Back> page=backService.pageAllBacks(pageNo,pageSize);
        page.setUrl("backServlet?action=pageAllBacks");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/backs_manager.jsp").forward(req,resp);
    }
    public void pageAllBacksByBook_Id(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 18;
        int id=Integer.parseInt(req.getParameter("id"));
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Back> page=backService.pageAllBacksByBook_Id(pageNo,pageSize,id);
        page.setUrl("backServlet?action=pageAllBacksByBook_Id");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("id",id);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/backs_manager.jsp").forward(req,resp);
    }
    public void userBackBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获得要归还的图书的id
        int book_id=0;
        int flag=0;
        String b_id=req.getParameter("book_id");
        String lastUrl=req.getHeader("referer");
        if(WebUtils.isNum(b_id)){
            book_id=Integer.parseInt(b_id);
            flag = backService.userBackBook(book_id);

        }else{
            flag=-1;
        }
        req.removeAttribute("flag_borrow");
        resp.sendRedirect(lastUrl+"&flag_back="+flag);
    }
}
