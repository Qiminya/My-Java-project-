package com.nb.dao.impl;

import com.nb.dao.DownDao;
import com.nb.pojo.Down;

import java.util.List;

public class DownDaoImpl extends BaseDao implements DownDao {
    @Override
    public int updateBookState(int book_id) {
        String sql="update books set `book_state`='out' where `book_id`=?";
        return update(sql,book_id);
    }

    @Override
    public int createDown(Down down) {
        String sql="insert into downbooks (`book_Id`,`down_time`,`down_cause`) value(?,now(),?)";
        return update(sql,down.getBook_id(),down.getDown_cause());
    }

    @Override
    public List<Down> queryPageAllItemsByBookId(int begin, int pageSize, String book_id) {
        String sql="select downbooks.`book_id`,`book_name`,`down_time`,`down_cause` from downbooks "
                +"join books on (books.`book_id`=downbooks.`book_id` and downbooks.`book_id` like ?) "
                +"order by `down_time` desc limit ?,? ";
        return queryForList(Down.class,sql,"%"+book_id+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCount(String book_id) {
        String sql="select count(*) from downbooks where `book_id` like ?";
        Number number= (Number) queryForSingleValue(sql,"%"+book_id+"%");
        return number.intValue();
    }
}
