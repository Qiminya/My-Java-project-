package com.nb.dao.impl;

import com.nb.dao.UpDao;
import com.nb.pojo.Up;

import java.util.List;

public class UpDaoImpl extends BaseDao implements UpDao {
    @Override
    public int createUp(Up up) {
        String sql="insert into upbooks (`book_id`,`book_name`,`user_id`,`nickname`,`up_time`)value(?,?,?,?,now())";
        return update(sql,up.getBook_id(),up.getBook_name(),up.getUser_id(),up.getNickname());
    }

    @Override
    public List<Up> queryPageAllItemsByBookId(int begin, int pageSize, String book_id) {
        String sql="select upbooks.`book_id`,`book_name`,upbooks.`user_id`,`nickname`,`up_time` from upbooks "
                +"join books on (books.`book_id`=upbooks.`book_id` and upbooks.`book_id` like ?) "
                +"join users on users.`user_id`=upbooks.`user_id` "
                +"order by `up_time` desc limit ?,?";
        return queryForList(Up.class,sql,"%"+book_id+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCount( String  book_id) {
        String sql="select count(*) from upbooks where `book_id` like ?";
        Number number= (Number) queryForSingleValue(sql,"%"+book_id+"%");
        return number.intValue();
    }
}
