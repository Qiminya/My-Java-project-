package com.nb.servlet;

import com.nb.pojo.Down;
import com.nb.pojo.Page;
import com.nb.pojo.User;
import com.nb.sevice.DownService;
import com.nb.sevice.impl.DownServiceImpl;
import com.nb.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownServlet extends BaseServlet{
    DownService downService=new DownServiceImpl();
    public void BookOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求参数注入down对象
        Down down = WebUtils.copyParamToBean(req.getParameterMap(), new Down());
        //执行下架操作
        int flag=downService.downBook(down);
        String str="";
        if(flag==1){
            str="id为["+down.getBook_id()+"]的《"+down.getBook_name()+"》下架成功！！！";
        }else if(flag==0){
            str="id为["+down.getBook_id()+"]的《"+down.getBook_name()+"》图书状态修改成功但是没生成下架记录！！！";
        }else if(flag==-1){
            str="id为["+down.getBook_id()+"]的《"+down.getBook_name()+"》状态修改失败！！！";
        }
        //跳到之前页面
        String lastUrl=req.getHeader("referer");
        resp.sendRedirect(lastUrl+"&flag_down="+flag);
    }
    public void pageAllDowns(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Page<Down> page = downService.pageByBook_id(pageNo, pageSize, book_id);
        page.setUrl("downServlet?action=pageAllDowns");
        //将page保存到request域
        req.setAttribute("page",page);
        req.setAttribute("book_id",book_id);
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/pages/manager/downbooks_manager.jsp").forward(req, resp);
    }
}
