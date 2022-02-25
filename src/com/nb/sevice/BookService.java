package com.nb.sevice;

import com.nb.pojo.Book;
import com.nb.pojo.Page;

import java.util.List;

public interface BookService {
    /**
     * 根据id查图书
     * @param id
     * @return
     */
    public Book queryBookById(int id);

    /**
     * 根据id查图书详细
     * @param book_id
     * @return
     */
    public Book getBookById(int book_id);

    /**
     * 根据图书名查图书
     * @param name
     * @return
     */
    public List<Book> queryBooksByName(String name);

    /**
     * 查询所有图书
     * @return
     */
    public List< Book> queryBooks();
    /**
     * 根据id删除图书
     * @param id
     */
    public void deleteBookById(int id);

    /**
     * 修改图书信息
     * @param book 所修改的图书对象
     */
    public void updateBook(Book book);

    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);
    /**
     * 根据当前页数，和每页记录数查询图书
     * @param pageNo 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize,String state,String name);

    /**
     * 根据当前页数，和每页记录数查询图书
     * @param pageNo 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    public Page<Book> page_user(int pageNo, int pageSize,String state,String name);

    /**
     * 根据当前页数，和每页记录数查询用户往期借入图书
     * @param pageNo 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    public Page<Book> page_userBorrow(int pageNo, int pageSize,int user_id);

    /**
     * 根据当前页数，和每页记录数以及图书名查询图书
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    public Page<Book> pageByName(int pageNo, int pageSize,String name);

    /**
     * 根据图书状态 当前页数，和每页记录数查询图书
     * @param pageNo
     * @param pageSize
     * @param state
     * @return
     */
    Page<Book> pageByState(int pageNo, int pageSize, String state);

    public List<Book> getNowBorrowBooks(int user_id);

    public List<Book> queryHotBooks();

    public Page<Book> pageMyLendBooks(int pageNo, int pageSize,int user_id);
}
