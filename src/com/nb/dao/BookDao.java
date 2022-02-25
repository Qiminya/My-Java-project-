package com.nb.dao;

import com.nb.pojo.Book;
import com.nb.pojo.Page;

import java.util.List;

public interface BookDao {
    /**
     * 根据图书id查找图书信息
     * @param id
     * @return
     */
    public Book queryBooktById(int id);

    public Book getBookById(int book_id);

    /**
     * 根据图书名查找图书
     * @param name
     * @return
     */
    public List<Book> queryBooksByName(String name);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 根据id修改图书信息
     * @param book
     * @return
     */
    public  int updateBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    public int deleteBookById(int id);

    /**
     * 根据图书对象添加图书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 总记录数
     * @return
     */
    public Integer queryForPageTotalCount(String state,String name);

    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems(int begin, int pageSize,String state,String name);

    /**
     * 总记录数
     * @return
     */
    public Integer queryForPageTotalCount_user(String state,String name);

    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems_user(int begin, int pageSize,String state,String name);

    /**
     * 根据书名查书
     * @param begin
     * @param pageSize
     * @param name
     * @return
     */
    public List<Book> queryForPageItemsByName(int begin, int pageSize,String name);

    /**
     * 根据书名查记录数
     * @return
     */
    public Integer queryForPageTotalCountByName(String name);

    /**
     * 根据图书状态查图书信息以及开始索引和记录数
     * @param begin
     * @param pageSize
     * @param state
     * @return
     */
    List<Book> queryForPageItemsByState(int begin, int pageSize, String state);

    /**
     * 根据图书状态查总记录数
     * @param state
     * @return
     */
    Integer queryForPageTotalCountByState(String state);

    /**
     * 增加图书的借阅次数
     * @param book_id
     * @return
     */
    public int addBookBorrowCount(int book_id);

    /**
     * 根据用户id查出用户当前借入图书
     * @param user_id
     * @return
     */
    public List<Book> queryNowBorrowBooks(int user_id);

    /**
     * 查该用户所有借过的书
     * @param user_id
     * @return
     */
    public Integer queryPageUserBorrowBooksTotalCount(int user_id);

    /**
     * 当前页数据
     * @param user_id
     * @return
     */
    public List<Book> queryPageUserBorrowBooksItems(int begin,int pageSize,int user_id);

    public List<Book> queryHotBooks();

    public List<Book> queryPageMyLendItems(int begin, int pageSize,int user_id);

    public Integer queryPageMyLendBooksTotalCount(int user_id);
}
