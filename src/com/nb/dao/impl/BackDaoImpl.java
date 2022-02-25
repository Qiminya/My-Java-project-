package com.nb.dao.impl;

import com.nb.dao.BackDao;
import com.nb.pojo.Back;

import java.util.List;

public class BackDaoImpl extends BaseDao implements BackDao {

    @Override
    public Integer queryPageTotalCount() {
        String sql="select count(*) from backs";
        Number number= (Number) queryForSingleValue(sql);
        return number.intValue();
    }
    @Override
    public List<Back> queryPageItems(int begin, int pageSize) {
        String sql="SELECT `back_id`,bk.`book_name`,b.`book_id`,`back_time`,DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY) AS 'max_back_time',DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY)>`back_time` AS 'flag' " +
                "FROM backs b " +
                "JOIN borrows bo ON bo.borrow_id=b.borrow_id " +
                "JOIN users u ON bo.user_id=u.user_id " +
                "JOIN grades g ON u.power_grade=g.power_grade " +
                "JOIN books bk ON b.book_id=bk.book_id " +
                "ORDER BY back_time DESC LIMIT ?,?";
        return queryForList(Back.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCountByBook_Id(int id) {
        String sql="select count(*) from backs where `book_id`=?";
        Number number= (Number) queryForSingleValue(sql,id);
        return number.intValue();
    }

    @Override
    public List<Back> queryPageItemsByBook_Id(int begin, int pageSize, int id) {
        String sql="SELECT `back_id`,bk.`book_name`,b.`book_id`,`back_time`,DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY) AS 'max_back_time',DATE_ADD(`borrow_time`,INTERVAL `back_day` DAY)>`back_time` AS 'flag' " +
                "FROM backs b " +
                "JOIN borrows bo ON bo.borrow_id=b.borrow_id " +
                "JOIN users u ON bo.user_id=u.user_id " +
                "JOIN grades g ON u.power_grade=g.power_grade " +
                "JOIN books bk ON b.book_id=bk.book_id " +
                "where b.`book_id`=? "+
                "ORDER BY back_time DESC LIMIT ?,?";
        return queryForList(Back.class,sql,id,begin,pageSize);
    }

    @Override
    public int updateBorrowFlagByBookId(int book_id) {
        String sql="update borrows set `borrow_flag`= '已归还' where `borrow_flag`='未还' and `book_id`=?";
        return update(sql,book_id);
    }

    @Override
    public int addOneBack(int book_id,int borrow_id) {
        String sql="insert into backs (`borrow_id`,`book_id`,`back_time`)value "+
                "(?,?,now())";
        return update(sql,borrow_id,book_id);
    }

    @Override
    public int getNewBorrowIdByBookId(int book_id) {
        String sql="select `borrow_id` from borrows where `book_id`=? limit 0,1";
        Number number= (Number) queryForSingleValue(sql,book_id);
        return number.intValue();
    }

    @Override
    public int updateBookStateById(int book_id) {
        String sql="update books set `book_state`='free' where `book_id`=? and `book_state`='borrowing'";
        return update(sql,book_id);
    }
}
