package com.nb.sevice.impl;

import com.nb.dao.BookDao;
import com.nb.dao.impl.BookDaoImpl;
import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.sevice.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBooktById(id);
    }

    @Override
    public Book getBookById(int book_id) {
        return bookDao.getBookById(book_id);
    }

    @Override
    public List<Book> queryBooksByName(String name) {
        return bookDao.queryBooksByName(name);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize,String state,String name) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount("%"+state+"%","%"+name+"%");
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
        List<Book> items = bookDao.queryForPageItems(begin,pageSize,"%"+state+"%","%"+name+"%");
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> page_user(int pageNo, int pageSize, String state, String name) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount_user("%"+state+"%","%"+name+"%");
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
        List<Book> items = bookDao.queryForPageItems_user(begin,pageSize,"%"+state+"%","%"+name+"%");
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> page_userBorrow(int pageNo, int pageSize, int user_id) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryPageUserBorrowBooksTotalCount(user_id);
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
        List<Book> items = bookDao.queryPageUserBorrowBooksItems(begin,pageSize,user_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByName(int pageNo, int pageSize, String name) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByName(name);
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
        List<Book> items = bookDao.queryForPageItemsByName(begin,pageSize,name);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByState(int pageNo, int pageSize, String state) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByState(state);
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
        List<Book> items = bookDao.queryForPageItemsByState(begin,pageSize,state);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public List<Book> getNowBorrowBooks(int user_id) {
        return bookDao.queryNowBorrowBooks(user_id);
    }

    @Override
    public List<Book> queryHotBooks() {
        return bookDao.queryHotBooks();
    }

    @Override
    public Page<Book> pageMyLendBooks(int pageNo, int pageSize,int user_id) {
        Page<Book> page = new Page<Book>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryPageMyLendBooksTotalCount(user_id);
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
        List<Book> items = bookDao.queryPageMyLendItems(begin,pageSize,user_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }
}
