package com.nb.dao.impl;

import com.nb.dao.BorrowDao;
import com.nb.pojo.Borrow;

import java.util.List;

public class BorrowDaoImpl extends BaseDao implements BorrowDao {
    @Override
    public Integer queryPageTotalCount(String name) {
        String sql="select count(*) from borrows b join users u on b.user_id=u.user_id where `name` like ?";
        Number number= (Number) queryForSingleValue(sql,name);
        return number.intValue();
    }

    @Override
    public List<Borrow> queryPageItems(int begin, int pageSize,String name) {
        String sql="SELECT `borrow_id`,`name`,b.`user_id`,`book_name`,b.`book_id`,`borrow_time`,DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY) AS 'back_time',`borrow_flag`" +
                "FROM (borrows b JOIN users u ON b.user_id=u.user_id)" +
                "JOIN books bk ON b.book_id=bk.book_id " +
                "JOIN grades g ON u.power_grade=g.power_grade where `name` like ?" +
                " ORDER BY `borrow_time` DESC limit ?,?";
        return queryForList(Borrow.class,sql,name,begin,pageSize);
    }

    @Override
    public int updateBookStateById(int id){
        String sql="update books set `book_state`='borrowing' where `book_id`=? and (`book_state`='booking' or `book_state`='free')";
        return update(sql,id);
    }

    @Override
    public int addOneBorrow(int user_id,int book_id) {
        String sql="insert INTO borrows(`user_id`,`book_id`,`borrow_time`)value (?,?,now())";
        return update(sql,user_id,book_id);
    }

    @Override
    public Integer getUserIdByBookId(int book_id) {
        String sql="SELECT `user_id` FROM borrows WHERE `book_id`=? ORDER BY `borrow_time` DESC LIMIT 0,1";
        Number number= (Number) queryForSingleValue(sql,book_id);
        return number.intValue();
    }
}
