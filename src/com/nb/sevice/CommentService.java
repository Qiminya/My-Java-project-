package com.nb.sevice;

import com.nb.pojo.Comment;
import com.nb.pojo.Page;

public interface CommentService {
    /**
     * 根据id删除留言
     * @param id
     * @return
     */
    public int deleteCommentById(int id);

    /**
     * 根据内容模糊查询得到留言分页模型
     * @param pageNo
     * @param pageSize
     * @param content
     * @return
     */
    public Page<Comment> pageAllComments(int pageNo,int pageSize,String content);

    public Page<Comment> pageAllComments_in(int pageNo,int pageSize,String nickname,int user_id);

    public Page<Comment> pageAllComments_out(int pageNo,int pageSize,String nickname,int user_id);

    public int createComment(int user_ing_id,int user_ed_id,String comment_content);
}
