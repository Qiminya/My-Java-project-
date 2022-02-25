package com.nb.servlet;

import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.pojo.User;
import com.nb.sevice.BookService;
import com.nb.sevice.impl.BookServiceImpl;
import com.nb.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    public void queryOneBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        //获取要得到的图书的id
        String str=req.getParameter("id");
        boolean b = WebUtils.isNum(str);
        int id;
        if(b){
            id=Integer.parseInt(req.getParameter("id"));
            //根据id获取图书
            Book book = bookService.queryBookById(id);
            //将得到的图书对象存到
            if(book!=null) {
                req.setAttribute("book", book);
                req.setAttribute("oldPath", book.getBook_imgpath());
                req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
            }else{
                req.setAttribute("mes","输入的id不存在！！！");
                req.getRequestDispatcher("/pages/manager/personal.jsp").forward(req,resp);
            }
        }else{
            req.setAttribute("mes","输入的id非法！！！(应为数字)");
            req.getRequestDispatcher("/pages/manager/personal.jsp").forward(req,resp);
        }
    }

    public void getBookById(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        //获取要得到的图书的id
        String str=req.getParameter("id");
        boolean b = WebUtils.isNum(str);
        int id;
        if(b){
            id=Integer.parseInt(req.getParameter("id"));
            //根据id获取图书
            Book book = bookService.getBookById(id);
            //将得到的图书对象存到
            req.setAttribute("book", book);
            req.getRequestDispatcher("/pages/user/book_information.jsp").forward(req, resp);
        }
    }

    public void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将图书属性参数封装成javaBean
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        String oldPath = req.getParameter("oldPath");//原文件虚拟路径
        String s=req.getParameter("selectFlag");
        //未选中文件
        if(s.equals("false")){
            book.setBook_imgpath(oldPath);
        }
        //选中了文件
        if(s.equals("true")){
            System.out.println("有");
            //文件上传
            File oldFile=new File("D:/uploadFiles"+oldPath);
            if(oldFile.exists()) {//原已有图片，将其删除再上传
                oldFile.delete();
            }
            String fileName = "";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss秒SSS毫秒");
            fileName += sdf.format(date) + ".jpg";
            //修改其信息
            String imgpath="/books/"+fileName;//存入数据库的图片虚拟路径
            String absolutepath="D:/uploadFiles/books/"+fileName;//文件存储的绝对路径
            //获得文件part对象
            Part part=req.getPart("img_path");
            //得到文件输入流
            InputStream in = part.getInputStream();
            FileOutputStream out=new FileOutputStream(absolutepath);
            //输出流
            int len=0;
            byte[] bytes=new byte[1024];
            while((len=in.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            //修改book的img_path属性
            book.setBook_imgpath(imgpath);
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
        bookService.updateBook(book);
        int pageNo=Integer.parseInt(req.getParameter("pageNo"));
        req.getRequestDispatcher("/bookServlet?action=pageAllBooks&pageNo="+pageNo).forward(req,resp);
    }
    public void queryAllBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/books_manager.jsp").forward(req,resp);
    }
    public void pageAllBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = Page.PAGE_SIZE;
        //获得图书名
        String name=req.getParameter("name");
        if (name==null){
            name="";
        }
        //获得图书状态
        String state=req.getParameter("state");
        if(state==null){
            state="";
        }
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Book> page=bookService.page(pageNo,pageSize,state,name);
        page.setUrl("bookServlet?action=pageAllBooks");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("name",name);
        req.setAttribute("state",state);
        req.setAttribute("flag_down",req.getParameter("flag_down"));
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/books_manager.jsp").forward(req,resp);
    }
    public void pageAllMyLendBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        int pageSize = 14;
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Book> page=bookService.pageMyLendBooks(pageNo,pageSize,user_id);
        page.setUrl("bookServlet?action=pageAllBooks");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/user/my_share_books.jsp").forward(req,resp);
    }
    public void pageAllBooks_user(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 14;
        //获得图书名
        String name=req.getParameter("name");
        if (name==null){
            name="";
        }
        //获得图书状态
        String state=req.getParameter("state");
        if(state==null){
            state="";
        }
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Book> page=bookService.page_user(pageNo,pageSize,state,name);
        page.setUrl("bookServlet?action=pageAllBooks_user");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("name",name);
        req.setAttribute("state",state);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/user/select_books.jsp").forward(req,resp);
    }
    public void page_userBorrow(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        String id=req.getParameter("user_id");
        int user_id = 0;
        if(id.equals("")){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            user_id = Integer.parseInt(id);
            if (req.getParameter("pageNo") == null) {
                pageNo = Integer.parseInt("1");
            } else {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
            }
            int pageSize = 14;
            //2.调用BookService.page(pageNo,pageSize):得到page对象
            Page<Book> page = bookService.page_userBorrow(pageNo, pageSize,user_id);
            page.setUrl("bookServlet?action=page_userBorrow");
            //3.保存Page对象到Request域中
            req.setAttribute("page", page);
            //4.请求转发到pages/manager/book_manager.jsp页面
            req.getRequestDispatcher("/pages/user/my_history_borrow.jsp").forward(req, resp);
        }
    }
    public void getNowBorrowBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id=req.getParameter("user_id");
        int user_id = 0;
        if(id.equals("")){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            user_id=Integer.parseInt(id);
        }
        List<Book> books=bookService. getNowBorrowBooks(user_id);
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/user/my_now_borrow.jsp").forward(req,resp);
    }
    public void pageAllBooksByState(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = Page.PAGE_SIZE;
        //获得图书状态
        String state=req.getParameter("state");
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Book> page=bookService.pageByState(pageNo,pageSize,state);
        page.setUrl("bookServlet?action=pageAllBooksByState");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("state",state);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/books_manager.jsp").forward(req,resp);
    }
    public void pageAllBooksByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = Page.PAGE_SIZE;
        //获得图书名
        String name=req.getParameter("name");
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Book> page=bookService.pageByName(pageNo,pageSize,name);
        page.setUrl("bookServlet?action=pageAllBooksByName");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("name",name);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/books_manager.jsp").forward(req,resp);
    }
    public void addBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        String fileName = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss秒SSS毫秒");
        fileName += sdf.format(date) + ".jpg";
        //修改其信息
        String imgpath="/books/"+fileName;
        String absolutepath="D:/uploadFiles/books/"+fileName;
        //获得文件part对象
        Part part=req.getPart("img_path");
        //得到文件输入流
        InputStream in = part.getInputStream();
        //输出流
        FileOutputStream out=new FileOutputStream(absolutepath);
        int len=0;
        byte[] bytes=new byte[1024];
        while((len=in.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
        //修改book的img_path属性
        book.setBook_imgpath(imgpath);
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath()+"/bookServlet?action=pageAllBooks&pageNo="+req.getParameter("pageNo"));
    }
    public void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取要删除的图书id
        int id=Integer.parseInt(req.getParameter("id"));
        //通过图书id删除图书
        bookService.deleteBookById(id);

        int pageNo=Integer.parseInt(req.getParameter("pageNo"));
        //返回至原删除图书的当前页
        req.getRequestDispatcher("/bookServlet?action=pageAllBooks&pageNo="+pageNo).forward(req,resp);
    }
    public void queryHotBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Book> books = bookService.queryHotBooks();
        req.setAttribute("books",books);
        //返回至原删除图书的当前页
        req.getRequestDispatcher("/pages/user/personal.jsp").forward(req,resp);
    }
}

