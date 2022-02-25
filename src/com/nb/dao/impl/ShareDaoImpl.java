package com.nb.dao.impl;

import com.nb.dao.ShareDao;
import com.nb.pojo.Book;
import com.nb.pojo.Share;

import java.util.List;

public class ShareDaoImpl extends BaseDao implements ShareDao {
    @Override
    public Share queryShareBookById(int id) {
        String sql="select * from shares where `share_id`=?";
        return queryForOne(Share.class,sql,id);
    }

    @Override
    public int deleteShareById(int id) {
        String sql="delete from shares where share_id = ?";
        return update(sql,id);
    }

    @Override
    public int addBookToBooks(Share share) {
        String sql="INSERT INTO books(`book_name` , `book_author` , `book_publisher` ,`book_type`,`book_lenduser_id`, `book_imgpath`)VALUE " +
                "(?,?,?,?,?,?)";
        return update(sql, share.getBook_name(), share.getBook_author(), share.getBook_publisher(), share.getBook_type(), share.getBook_lenduser_id(), share.getBook_imgpath());
    }

    @Override
    public int updateSharesBook(Share share) {
        String sql="update shares set `book_name`=? , `book_author`=?, `book_publisher`=? ,`book_type`=?,`book_imgpath`=? where `share_id`=?";
        return update(sql,share.getBook_name(),share.getBook_author(),share.getBook_publisher(),share.getBook_type(),share.getBook_imgpath(),share.getShare_id());
    }

    @Override
    public List<Share> queryPageItems(int begin, int pageSize,String book_name) {
        String sql="SELECT `share_id`,`book_imgpath`,`book_name`,`book_author`,`book_publisher`,`book_type`,u.`name` AS 'book_lenduser_name',`book_lenduser_id` " +
                "FROM shares s JOIN users u ON s.book_lenduser_id=u.user_id " +
                "where `book_name` like ? LIMIT ?,?";
        return queryForList(Share.class,sql,"%"+book_name+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCount(String book_name) {
        String sql="select count(*) from shares where `book_name` like ?";
        Number number= (Number) queryForSingleValue(sql,"%"+book_name+"%");
        return number.intValue();
    }

    @Override
    public int createShare(Share share) {
        String sql="insert into shares (`book_name`,`book_author`,`book_publisher`,`book_type`,`book_lenduser_id`,`book_imgpath`)value "+
                "(?,?,?,?,?,?)";
        return update(sql,share.getBook_name(),share.getBook_author(),share.getBook_publisher(),share.getBook_type(),share.getBook_lenduser_id(),share.getBook_imgpath());
    }

    @Override
    public List<Share> queryShareByUser_id(int user_id) {
        String sql="select * from shares where `book_lenduser_id`=?";
        return queryForList(Share.class,sql,user_id);
    }
}
