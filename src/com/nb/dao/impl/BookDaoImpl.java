package com.nb.dao.impl;

import com.nb.dao.BookDao;
import com.nb.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public Book queryBooktById(int id) {
        String sql="select * from books where `book_id`=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public Book getBookById(int book_id) {
        String sql="SELECT borrows.`borrow_flag` ,u1.`user_id` as 'book_borrowuser_id',books.`book_id`,`book_name`,`book_author`,`book_publisher`,`book_type`,u1.`nickname` AS 'borrow_nickname',`book_imgpath`,`book_count_borrow`,`book_state`,u2.`nickname` AS 'lend_nickname',u2.`user_id` AS 'book_lenduser_id',borrows.`borrow_time` FROM books " +
                "JOIN borrows ON books.`book_id`=borrows.`book_id` " +
                "JOIN users u1 ON u1.`user_id`=borrows.`user_id` " +
                "JOIN users u2 ON u2.`user_id`=books.`book_lenduser_id` WHERE  books.`book_id`=? ORDER BY `borrow_time` DESC LIMIT 0,1;";
        Book book = queryForOne(Book.class, sql, book_id);
        if(book==null){
            sql="select `book_lenduser_id`,`book_id`,`book_name`,`book_author`,`book_publisher`,`book_type`,`nickname` AS 'lend_nickname',`book_imgpath`,`book_count_borrow`,`book_state` from books join users on `book_lenduser_id`=`user_id` where `book_id`=?";
            book=queryForOne(Book.class,sql,book_id);
        }
        return book;
    }

    @Override
    public List<Book> queryBooksByName(String name) {
        String sql="select * from books where `book_name`=?";
        return queryForList(Book.class,sql,name);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from books";
        return queryForList(Book.class,sql);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update books set `book_name`=? , `book_author`=?, `book_publisher`=? ,`book_type`=?, `book_lenduser_id`=?,`book_imgpath`=?  where `book_id`=?";
        return update(sql,book.getBook_name(),book.getBook_author(),book.getBook_publisher(),book.getBook_type(),book.getBook_lenduser_id(),book.getBook_imgpath(),book.getBook_id());
    }

    @Override
    public int deleteBookById(int id) {
        String sql="delete from books where book_id = ?";
        return update(sql,id);
    }

    @Override
    public int addBook(Book book) {
        String sql="INSERT INTO books(`book_name` , `book_author` , `book_publisher` ,`book_type`,`book_lenduser_id`, `book_imgpath`)VALUE " +
                "(?,?,?,?,?,?);";
        return update(sql,book.getBook_name(),book.getBook_author(),book.getBook_publisher(),book.getBook_type(),book.getBook_lenduser_id(),book.getBook_imgpath());
    }

    @Override
    public Integer queryForPageTotalCount(String state,String name) {
        String sql="select count(*) from books where `book_state` like ? and `book_name` like ?";
        Number number=(Number)queryForSingleValue(sql,state,name);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize,String state,String name) {
        String sql="select * from books where `book_state` like ? and `book_name` like ? limit ?,?";
        return queryForList(Book.class,sql,state,name,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount_user(String state, String name) {
        String sql="select count(*) from books where `book_state` like ? and `book_name` like ? and `book_state`!='out'";
        Number number=(Number)queryForSingleValue(sql,state,name);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItems_user(int begin, int pageSize, String state, String name) {
        String sql="select * from books where `book_state` like ? and `book_name` like ? and `book_state`!='out' order by `book_count_borrow` desc limit ?,?";
        return queryForList(Book.class,sql,state,name,begin,pageSize);
    }

    @Override
    public List<Book> queryForPageItemsByName(int begin, int pageSize, String name) {
        String sql="select * from books where `book_name` like ?  limit ?,?";
        return queryForList(Book.class,sql,"%"+name+"%",begin,pageSize);
    }

    @Override

    public Integer queryForPageTotalCountByName(String name) {
        String sql="select count(*) from books where `book_name` like ?";
        Number number=(Number)queryForSingleValue(sql,"%"+name+"%");
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByState(int begin, int pageSize, String state) {
        String sql="select * from books where `book_state` = ?  limit ?,?";
        return queryForList(Book.class,sql,state,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByState(String state) {
        String sql="select count(*) from books where `book_state`=?";
        Number number=(Number)queryForSingleValue(sql,state);
        return number.intValue();
    }

    @Override
    public int addBookBorrowCount(int book_id) {
        String sql="update books set `book_count_borrow`=`book_count_borrow`+1 where `book_id`=?";
        return update(sql,book_id);
    }

    @Override
    public List<Book> queryNowBorrowBooks(int user_id) {
        String sql="SELECT `book_name`,`book_imgpath`,books.`book_id`,`book_state`,DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY) AS 'back_time'" +
                "FROM borrows JOIN books " +
                "ON borrows.`user_id`=? AND `borrow_flag`='未还' AND books.`book_id`=borrows.`book_id` "+
                "join users on users.`user_id`=borrows.`user_id` "+
                "join grades  on grades.`power_grade`=users.`power_grade`";
        return queryForList(Book.class,sql,user_id);
    }

    @Override
    public Integer queryPageUserBorrowBooksTotalCount(int user_id) {
        String sql="select count(*) from borrows where `user_id`=?";
        Number number= (Number) queryForSingleValue(sql,user_id);
        return number.intValue();
    }

    @Override
    public List<Book> queryPageUserBorrowBooksItems(int begin, int pageSize, int user_id) {
        String sql="SELECT `book_name`,`book_imgpath`,books.`book_id`,`book_state` FROM borrows JOIN books " +
                "ON borrows.`user_id`=? AND books.`book_id`=borrows.`book_id` "+
                "limit ?,?";
        return queryForList(Book.class,sql,user_id,begin,pageSize);
    }

    @Override
    public List<Book> queryHotBooks() {
        String sql="select * from books order by `book_count_borrow` desc limit 0,8";
        return queryForList(Book.class,sql);
    }

    @Override
    public List<Book> queryPageMyLendItems(int begin, int pageSize,int user_id) {
        String sql="select * from books where `book_lenduser_id`=? limit ?,?";
        return queryForList(Book.class,sql,user_id,begin,pageSize);
    }

    @Override
    public Integer queryPageMyLendBooksTotalCount(int user_id) {
        String sql="select count(*) from books where `book_lenduser_id`=?";
        Number number= (Number) queryForSingleValue(sql,user_id);
        return number.intValue();
    }
}
