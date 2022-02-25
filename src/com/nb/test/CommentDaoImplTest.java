package com.nb.test;

import com.nb.dao.CommentDao;
import com.nb.dao.impl.CommentDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentDaoImplTest {
    CommentDao commentDao=new CommentDaoImpl();
    @Test
    public void deleteCommentById() {
        commentDao.deleteCommentById(1);
    }

    @Test
    public void queryItemsByContent() {
        System.out.println(commentDao.queryItemsByContent(0,1,""));
    }

    @Test
    public void queryPageTotalCountByContent() {
        System.out.println(commentDao.queryPageTotalCountByContent(""));
    }
}