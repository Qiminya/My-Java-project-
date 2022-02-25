package com.nb.sevice.impl;

import com.nb.dao.BackDao;
import com.nb.dao.BookDao;
import com.nb.dao.BorrowDao;
import com.nb.dao.UserDao;
import com.nb.dao.impl.BackDaoImpl;
import com.nb.dao.impl.BookDaoImpl;
import com.nb.dao.impl.BorrowDaoImpl;
import com.nb.dao.impl.UserDaoIml;
import com.nb.pojo.Back;
import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.sevice.BackService;

import java.util.List;

public class BackServiceImpl implements BackService {
    private BackDao backDao=new BackDaoImpl();
    private UserDao userDao=new UserDaoIml();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public Page<Back> pageAllBacks(int pageNo, int pageSize) {
        Page<Back> page = new Page<Back>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = backDao.queryPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Back> items = backDao.queryPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Back> pageAllBacksByBook_Id(int pageNo, int pageSize, int id) {
        Page<Back> page = new Page<Back>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = backDao.queryPageTotalCountByBook_Id(id);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Back> items = backDao.queryPageItemsByBook_Id(begin,pageSize,id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public int userBackBook(int book_id) {
        int flag=0;
        //判断该id是否存在
        Book book = bookDao.queryBooktById(book_id);
        if(book==null){
            return -2;
        }
        //1.把图书状态改为空闲
        flag = backDao.updateBookStateById(book_id);
        if(flag!=0){
            //把图书借阅记录归还状态改为已归还
            flag+=backDao.updateBorrowFlagByBookId(book_id);
            //当前借书-1
            flag+=userDao.reduceNowBorrowCount(book_id);
            //新增一条还书记录
            backDao.addOneBack(book_id,backDao.getNewBorrowIdByBookId(book_id));
            return 1;
        }else{
            return 0;//为1成功,为0则该书没有被借阅
        }
    }
}
