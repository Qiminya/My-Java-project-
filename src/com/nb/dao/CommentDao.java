package com.nb.dao;

import com.nb.pojo.Comment;

import java.util.List;

public interface CommentDao {
    /**
     * 通过id删除留言
     * @param id
     * @return
     */
    public int deleteCommentById(int id);

    /**
     * 新建一个留言
     * @param user_ing_id
     * @param user_ed_id
     * @return
     */
    public int createComment(int user_ing_id,int user_ed_id,String comment_content);

    /**
     * 根据留言内容模糊查询当前页数据
     * @param content
     * @return
     */
    public List<Comment> queryItemsByContent(int begin ,int pageSize,String content);

    /**
     * 通过留言内容模糊查询总记录数
     * @param content
     * @return
     */
    public Integer queryPageTotalCountByContent(String content);

    /**
     *
     * @param begin
     * @param pageSize
     * @param nickname
     * @return
     */
    public List<Comment> queryItemsBynickname_in(int begin ,int pageSize,String nickname,int user_id);

    public Integer queryPageTotalCountByContent_in(String nickname,int user_id);
    /**
     *
     * @param begin
     * @param pageSize
     * @param nickname
     * @return
     */
    public List<Comment> queryItemsBynickname_out(int begin ,int pageSize,String nickname,int user_id);

    public Integer queryPageTotalCountByContent_out(String nickname,int user_id);
}
