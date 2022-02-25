package com.nb.servlet;

import com.nb.dao.BookingDao;
import com.nb.pojo.*;
import com.nb.sevice.BookingService;
import com.nb.sevice.UserService;
import com.nb.sevice.impl.BookingServiceImpl;
import com.nb.sevice.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BookingServlet extends BaseServlet{
    private BookingService bookingService=new BookingServiceImpl();
    private UserService userService=new UserServiceImpl();
    public void pageAllBookings(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
        String user_name=req.getParameter("user_name");
        if(user_name==null)
            user_name="";
        Page<Booking> page= bookingService.pageAllBooking(pageNo,pageSize,user_name);
        page.setUrl("bookingServlet?action=pageAllBookings");
        //将分页与姓名保存到域中
        req.setAttribute("flag_agree_borrow",req.getParameter("flag_agree_borrow"));
        System.out.println(req.getAttribute("flag_agree_borrow")+"------");
        req.setAttribute("name",name);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/bookings_manager.jsp").forward(req,resp);
    }
    public void pageAllBookings_user(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
        String user_name=req.getParameter("user_name");
        if(user_name==null)
            user_name="";
        Page<Booking> page= bookingService.pageAllBooking(pageNo,pageSize,user_name);
        page.setUrl("bookingServlet?action=pageAllBookings");
        //将分页与姓名保存到域中
        req.setAttribute("name",name);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/bookings_manager.jsp").forward(req,resp);
    }
    public void queryMyBooking(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户id
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        List<Booking> bookings = bookingService.queryMyBooking(user_id);
        req.setAttribute("bookings",bookings);
        req.getRequestDispatcher("/pages/user/my_now_booking.jsp").forward(req,resp);
    }
    public void agreeBorrow(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户id和图书id
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        int book_id=Integer.parseInt(req.getParameter("book_id"));
        int flag = bookingService.agreeBorrow(user_id, book_id);
        if(flag==4
        ){
            req.setAttribute("mes","用户预约申请已通过");
        }else{
            req.setAttribute("mes","用户预约申请处理出错！！！");
        }
        String lastUrl=req.getHeader("referer");
        resp.sendRedirect(lastUrl+"&flag_agree_borrow="+flag);
    }
    public void deleteBooking(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户id和图书id
        int book_id=Integer.parseInt(req.getParameter("book_id"));
        int flag = bookingService.deleteBooking(book_id);
        if (flag==1){
            req.setAttribute("mes","删除成功！！！");
        }else{
            req.setAttribute("mes","删除失败！！！");
        }
        req.getRequestDispatcher("/bookingServlet?action=pageAllBookings&pageNo="+req.getParameter("pageNo")).forward(req,resp);
    }
    public void createBooking(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户id和图书id
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        int book_id=Integer.parseInt(req.getParameter("book_id"));
        int flag = bookingService.createBooking(user_id,book_id);
        if (flag==1){
            req.setAttribute("mes_booking","预约成功！！！");
            User user=userService.queryUserById(Integer.parseInt(req.getParameter("user_id")));
            req.getSession().removeAttribute("user");
            req.getSession().setAttribute("user",user);
        }
        if (flag==0){
            req.setAttribute("mes_booking","该书已被预约或借阅！！！");
        }
        if (flag==-1){
            req.setAttribute("mes_booking","您无法再预约或借书！！！");
        }
        String lastUrl=req.getHeader("referer");
        String url=lastUrl.substring(lastUrl.indexOf("book")+4);
        req.getRequestDispatcher(url).forward(req,resp);
    }
}
