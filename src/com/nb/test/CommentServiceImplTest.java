package com.nb.test;

import com.nb.sevice.CommentService;
import com.nb.sevice.impl.CommentServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentServiceImplTest {
    CommentService commentService=new CommentServiceImpl();
    @Test
    public void deleteCommentById() {
        commentService.deleteCommentById(2);
    }

    @Test
    public void pageAllComments() {
        System.out.println(commentService.pageAllComments(1,1,""));
    }
}