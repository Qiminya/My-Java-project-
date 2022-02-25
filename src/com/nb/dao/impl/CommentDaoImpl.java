package com.nb.dao.impl;

import com.nb.dao.CommentDao;
import com.nb.pojo.Comment;

import java.util.List;

public class CommentDaoImpl extends BaseDao implements CommentDao {
    @Override
    public int deleteCommentById(int id) {
        String sql="delete from comments where `comment_id`=?";
        return update(sql,id);
    }

    @Override
    public int createComment(int user_ing_id, int user_ed_id,String comment_content) {
        String sql="insert into comments (`user_ed_id`,`user_ing_id`,`comment_content`,`comment_time`)value "
                +" (?,?,?,now())";
        return update(sql,user_ed_id,user_ing_id,comment_content);
    }

    @Override
    public List<Comment> queryItemsByContent(int begin, int pageSize, String content) {
        String sql="SELECT `comment_id`,u1.`nickname` AS user_ing_nickname,`user_ing_id`,u2.`nickname` AS user_ed_nickname,`user_ed_id`,`comment_content`,`comment_time` " +
                "FROM comments c " +
                "JOIN users u1 ON c.`user_ing_id`=u1.`user_id` " +
                "JOIN users u2 ON c.`user_ed_id`=u2.`user_id` "+
                "where `comment_content` like ? limit ?,?";
        return queryForList(Comment.class,sql,"%"+content+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCountByContent(String content) {
        String sql="select count(*) from comments where `comment_content` like ?";
        Number number= (Number) queryForSingleValue(sql,"%"+content+"%");
        return number.intValue();
    }

    @Override
    public List<Comment> queryItemsBynickname_in(int begin, int pageSize, String nickname,int user_id) {
        String sql="SELECT `comment_id`,u1.`nickname` AS user_ing_nickname,`user_ing_id`,u2.`nickname` AS user_ed_nickname,`user_ed_id`,`comment_content`,`comment_time` " +
                "FROM comments c " +
                "JOIN users u1 ON c.`user_ing_id`=u1.`user_id` " +
                "JOIN users u2 ON c.`user_ed_id`=u2.`user_id` " +
                "WHERE `user_ed_id`=? and u1.`nickname` like ? order by `comment_time` desc LIMIT ?,?";
        return queryForList(Comment.class,sql,user_id,"%"+nickname+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCountByContent_in(String nickname,int user_id) {
        String sql="select count(*) from comments c join users u on u.`user_id`=c.`user_ing_id` "+
                "where u.`nickname` like ? and `user_ed_id`=?";
        Number number= (Number) queryForSingleValue(sql,"%"+nickname+"%",user_id);
        return number.intValue();
    }

    @Override
    public List<Comment> queryItemsBynickname_out(int begin, int pageSize, String nickname,int user_id) {
        String sql="SELECT `comment_id`,u1.`nickname` AS user_ing_nickname,`user_ing_id`,u2.`nickname` AS user_ed_nickname,`user_ed_id`,`comment_content`,`comment_time` " +
                "FROM comments c " +
                "JOIN users u1 ON c.`user_ing_id`=u1.`user_id` " +
                "JOIN users u2 ON c.`user_ed_id`=u2.`user_id` " +
                "WHERE `user_ing_id`=? and u2.`nickname` like ? order by `comment_time` desc LIMIT ?,?";
        return queryForList(Comment.class,sql,user_id,"%"+nickname+"%",begin,pageSize);
    }

    @Override
    public Integer queryPageTotalCountByContent_out(String nickname,int user_id) {
        String sql="select count(*) from comments c join users u on u.`user_id`=c.`user_ed_id` "+
                "where u.`nickname` like ? and `user_ing_id`=?";
        Number number= (Number) queryForSingleValue(sql,"%"+nickname+"%",user_id);
        return number.intValue();
    }
}
