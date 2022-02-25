package com.nb.sevice.impl;

import com.nb.dao.BookDao;
import com.nb.dao.BookingDao;
import com.nb.dao.BorrowDao;
import com.nb.dao.UserDao;
import com.nb.dao.impl.BookDaoImpl;
import com.nb.dao.impl.BookingDaoImpl;
import com.nb.dao.impl.BorrowDaoImpl;
import com.nb.dao.impl.UserDaoIml;
import com.nb.pojo.Book;
import com.nb.pojo.Borrow;
import com.nb.pojo.Page;
import com.nb.pojo.User;
import com.nb.sevice.BorrowService;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    private BorrowDao borrowDao=new BorrowDaoImpl();
    private UserDao userDao=new UserDaoIml();
    private BookingDao bookingDao=new BookingDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public Page<Borrow> pageAllBorrows(int pageNo, int pageSize ,String name) {
        //新建分页对象
        Page<Borrow> page=new Page<Borrow>();
        //查找总记录数
        Integer pageTotalCount=borrowDao.queryPageTotalCount("%"+name+"%");
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
        List<Borrow> items=borrowDao.queryPageItems(begin,pageSize,"%"+name+"%");
        //设置当前页数据
        page.setItems(items);
        return page;
    }
    @Override
    public int userBorrow(int user_id, int book_id) {
        //判断用户是否达到最大借阅数量1为是0为否
        User user = userDao.queryUserById(user_id);
        if(user==null){
            return -3;//用户不存在
        }
        Book book = bookDao.queryBooktById(book_id);
        if (book==null){
            return -4;//书不存在
        }
        int flag=userDao.isfull(user_id);
        if(flag==0){//该用户可借
            //修改图书状态为借阅
            flag=borrowDao.updateBookStateById(book_id);
            //如果有人预约了把其预约请求删除,到店优先

            if(flag==1){//修改成功该书在库且空闲
                userDao.addNowBorrowCount(user_id);//借了本书，1.用户当前借书+1，
                userDao.addBorrowCount(user_id);//2.总借书+1,
                borrowDao.addOneBorrow(user_id,book_id);//3.生成一条借阅记录
                bookingDao.deleteBooking(book_id);//4.删除该书的预约
                bookDao.addBookBorrowCount(book_id);
                return 1;//借书成功
            }else{
                return 0;//该书不是空闲状态
            }
        }else{
            return -1;//用户不可借
        }
    }
}
