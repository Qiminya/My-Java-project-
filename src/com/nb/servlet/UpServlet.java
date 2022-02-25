package com.nb.servlet;

import com.nb.pojo.Page;
import com.nb.pojo.Up;
import com.nb.sevice.UpService;
import com.nb.sevice.impl.UpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpServlet extends BaseServlet{
    private UpService upService=new UpServiceImpl();
    public void pageAllUps(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前页数
        int pageNo=1;
        if(req.getParameter("pageNo")!=null){
            pageNo=Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize=18;
        //将book_id取出
        String book_id=req.getParameter("book_id");
        if (book_id==null)
            book_id="";
        //分页
        Page<Up> page = upService.pageAllUps(pageNo,pageSize,book_id);
        page.setUrl("upServlet?action=pageAllUps");
        //将page保存到request域
        req.setAttribute("page",page);
        req.setAttribute("book_id",book_id);
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/pages/manager/upbooks_manager.jsp").forward(req, resp);
    }
}
