package com.nb.servlet;

import com.nb.pojo.Book;
import com.nb.pojo.Comment;
import com.nb.pojo.Page;
import com.nb.sevice.CommentService;
import com.nb.sevice.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CommentServlet extends  BaseServlet{
    private CommentService commentService=new CommentServiceImpl();
    public void pageAllComments(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 4;
        String nickname=req.getParameter("nickname");
        if (nickname==null)
            nickname="";
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Comment> page=commentService.pageAllComments(pageNo,pageSize,nickname);
        page.setUrl("commentServlet?action=pageAllComments");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("nickname",nickname);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/comments_manager.jsp").forward(req,resp);
    }
    public void pageAllComments_in(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 4;
        String nickname=req.getParameter("nickname");
        if (nickname==null)
            nickname="";
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Comment> page=commentService.pageAllComments_in(pageNo,pageSize,nickname,user_id);
        page.setUrl("commentServlet?action=pageAllComments_in");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("nickname",nickname);
        //4.请求转发到页面
        req.getRequestDispatcher("/pages/user/my_comment.jsp").forward(req,resp);
    }
    public void pageAllComments_out(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 4;
        String nickname=req.getParameter("nickname");
        if (nickname==null)
            nickname="";
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Comment> page=commentService.pageAllComments_out(pageNo,pageSize,nickname,user_id);
        page.setUrl("commentServlet?action=pageAllComments_out");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("nickname",nickname);
        //4.请求转发到页面
        req.getRequestDispatcher("/pages/user/my_comment_ing.jsp").forward(req,resp);
    }
    public void deleteCommentById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取要删除的id
        int id=Integer.parseInt(req.getParameter("id"));
        int flag=commentService.deleteCommentById(id);
        req.setAttribute("flag",flag);
        String pageNo=req.getParameter("pageNo");
        req.getRequestDispatcher("/commentServlet?action=pageAllComments&pageNo="+pageNo).forward(req,resp);
    }
    public void createComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取发送者id，被留言者id，留言内容
        int user_ing_id=Integer.parseInt(req.getParameter("user_ing_id"));
        int user_ed_id=Integer.parseInt(req.getParameter("user_ed_id"));
        String comment_content=req.getParameter("comment_content");
        int flag=commentService.createComment(user_ing_id,user_ed_id,comment_content);
        if(flag==1){
            req.setAttribute("flag_comment","留言成功！！！");
        }else{
            req.setAttribute("flag_comment","留言失败！！！");
        }
        String lastUrl=req.getHeader("referer");
        String url=lastUrl.substring(lastUrl.indexOf("book")+4);
        req.getRequestDispatcher(url).forward(req,resp);
    }

}
