package com.nb.sevice.impl;

import com.nb.dao.BookingDao;
import com.nb.dao.BorrowDao;
import com.nb.dao.UserDao;
import com.nb.dao.impl.BookingDaoImpl;
import com.nb.dao.impl.BorrowDaoImpl;
import com.nb.dao.impl.UserDaoIml;
import com.nb.pojo.Booking;

import com.nb.pojo.Page;
import com.nb.sevice.BookingService;


import java.util.List;

public class BookingServiceImpl implements BookingService {
    private BookingDao bookingDao=new BookingDaoImpl();
    private BorrowDao borrowDao=new BorrowDaoImpl();
    private UserDao userDao=new UserDaoIml();
    @Override
    public int agreeBorrow(int user_id, int book_id) {
        int flag=0;
        //1.修改图书状态从预约到借出
        flag += borrowDao.updateBookStateById(book_id);
        //2.删除该预约记录
        flag+=bookingDao.deleteBookingByUserIdAndBookId(user_id,book_id);
        //借阅数加一
        flag+=userDao.addBorrowCount(user_id);//2.总借书+1,
        //3.生成一条借阅记录
        flag+=borrowDao.addOneBorrow(user_id,book_id);
        return flag;//返回3正常成功
    }
    @Override
    public Page<Booking> pageAllBooking(int pageNo,int pageSize,String user_name) {
        //新建分页对象
        Page<Booking> page=new Page<Booking>();
        //查找总记录数
        Integer pageTotalCount=bookingDao.queryBookingpageTotalCount(user_name);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //计算页数
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize!=0)
            pageTotal++;
        //设置总页数
        page.setPageTotal(pageTotal);
        //设置每页数量
        page.setPageSize(pageSize);
        //设置当前页数
        page.setPageNo(pageNo);
        //计算当前页数匹配索引
        int begin=(pageNo-1)*pageSize;
        //求当前页数据
        List<Booking> items=bookingDao.queryAllBookingpageItems(begin,pageSize,user_name);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public int deleteBooking(int book_id) {
        return bookingDao.deleteBooking(book_id);
    }

    @Override
    public int createBooking(int user_id, int book_id) {
        //1.检测用户是否有权预约(当前借书已经为最大值)
        if(userDao.isfull(user_id)==0){
            //2.修改预约图书状态
            if(bookingDao.updateBookStateById(book_id)==1){
                //当前借阅数加一
                userDao.addNowBorrowCount(user_id);//用户当前借书+1，防止多预约
                //3.生成预约申请
                bookingDao.createBooking(user_id,book_id);
                return 1;//预约成功
            }else {
                return 0;//已被预约或借阅
            }
        }else {
            return -1;//无权预约
        }
    }
    @Override
    public List<Booking> queryMyBooking(int user_id) {
        return bookingDao.queryMyBooking(user_id);
    }
}
