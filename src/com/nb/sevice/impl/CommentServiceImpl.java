package com.nb.sevice.impl;

import com.nb.dao.CommentDao;
import com.nb.dao.impl.CommentDaoImpl;
import com.nb.pojo.Book;
import com.nb.pojo.Comment;
import com.nb.pojo.Page;
import com.nb.sevice.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao=new CommentDaoImpl();
    @Override
    public int deleteCommentById(int id) {
        return commentDao.deleteCommentById(id);
    }

    @Override
    public Page<Comment> pageAllComments(int pageNo, int pageSize, String content) {
        //新建分页
        Page<Comment> page=new Page<Comment>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = commentDao.queryPageTotalCountByContent(content);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Comment> items = commentDao.queryItemsByContent(begin,pageSize,content);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Comment> pageAllComments_in(int pageNo, int pageSize, String nickname, int user_id) {
        //新建分页
        Page<Comment> page=new Page<Comment>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = commentDao.queryPageTotalCountByContent_in(nickname,user_id);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Comment> items = commentDao.queryItemsBynickname_in(begin,pageSize,nickname,user_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Comment> pageAllComments_out(int pageNo, int pageSize, String nickname, int user_id) {
        //新建分页
        Page<Comment> page=new Page<Comment>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = commentDao.queryPageTotalCountByContent_out(nickname,user_id);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Comment> items = commentDao.queryItemsBynickname_out(begin,pageSize,nickname,user_id);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public int createComment(int user_ing_id, int user_ed_id, String comment_content) {
        return commentDao.createComment(user_ing_id,user_ed_id,comment_content);
    }
}
