package com.nb.servlet;
import com.nb.pojo.Page;
import com.nb.pojo.Share;
import com.nb.sevice.ShareService;
import com.nb.sevice.impl.ShareServiceImpl;
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

@MultipartConfig
public class ShareServlet extends BaseServlet{
    private ShareService shareService=new ShareServiceImpl();
    public void queryOneShareBook(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取要得到的图书的id
        int share_id=Integer.parseInt(req.getParameter("share_id"));
        //根据id获取分享图书
        Share share = shareService.queryShareBookById(share_id);
        //将得到的图书对象存到
        req.setAttribute("share",share);
        req.getRequestDispatcher("/pages/manager/share_book_update.jsp").forward(req,resp);
    }
    public void queryShareByUser_id(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取用户id
        int user_id=Integer.parseInt(req.getParameter("user_id"));
        //根据id获取分享图书
        List<Share> shares = shareService.queryShareByUser_id(user_id);
        //将得到的分享图书对象存到
        req.setAttribute("shares",shares);
        req.getRequestDispatcher("/pages/user/noup_sharebooks.jsp").forward(req,resp);
    }
    public void pageAllShares(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = Page.PAGE_SIZE;
        //获得图书名关键字
        String book_name= (String) req.getAttribute("book_name");
        if(book_name==null)
            book_name="";
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<Share> page=shareService.pageAllShares(pageNo,pageSize,book_name);
        page.setUrl("shareServlet?action=pageAllShares");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("book_name",book_name);
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/shares_manager.jsp").forward(req,resp);
    }
    public void updateShareBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将图书属性参数封装成javaBean
        Share share = WebUtils.copyParamToBean(req.getParameterMap(), new Share());
        String oldPath = req.getParameter("oldPath");//原文件虚拟路径
        String s=req.getParameter("selectFlag");
        //未选中文件
        if(s.equals("false")){
            share.setBook_imgpath(oldPath);
        }
        //选中了文件
        if(s.equals("true")){
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
            String imgpath="/share_books/"+fileName;//存入数据库的图片虚拟路径
            String absolutepath="D:/uploadFiles/share_books/"+fileName;//文件存储的绝对路径
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
            share.setBook_imgpath(imgpath);
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
        //设置标识，对所有数据分页如果该参数为0，则将book_name赋值为空字符，查询所有
        req.removeAttribute("book_name");
        shareService.updateSharesBook(share);
        int pageNo=Integer.parseInt(req.getParameter("pageNo"));
        req.getRequestDispatcher("/shareServlet?action=pageAllShares&pageNo="+pageNo).forward(req,resp);
    }
    public void createShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将图书属性参数封装成javaBean
        Share share = WebUtils.copyParamToBean(req.getParameterMap(), new Share());
            String fileName = "";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss秒SSS毫秒");
            fileName += sdf.format(date) + ".jpg";
            //修改其信息
            String imgpath="/share_books/"+fileName;//存入数据库的图片虚拟路径
            share.setBook_imgpath(imgpath);
            String absolutepath="D:/uploadFiles/share_books/"+fileName;//文件存储的绝对路径
            //获得文件part对象
            Part part=req.getPart("bookImg");
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
            share.setBook_imgpath(imgpath);
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        int flag = shareService.createShare(share);
            if(flag==1){
                req.setAttribute("flag_share","提交分享信息成功！！！");
            }else{
                req.setAttribute("flag_share","提交分享信息失败！！！");
            }
        req.getRequestDispatcher("/pages/user/user_sharebook.jsp").forward(req,resp);
    }
    public void addToBooks(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Share share= WebUtils.copyParamToBean(req.getParameterMap(),new Share());
        String oldAbsolutepath="D:/uploadFiles"+share.getBook_imgpath();
        //从分享图书封面传至图书库封面
        //输入流
        InputStream inputStream=new FileInputStream(oldAbsolutepath);
        Date date = new Date();
        String fileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日HH时mm分ss秒SSS毫秒");
        fileName += sdf.format(date) + ".jpg";
        //修改其信息
        String imgpath="/books/"+fileName;
        String absolutepath="D:/uploadFiles/books/"+fileName;
        //输出流
        OutputStream outputStream=new FileOutputStream(absolutepath);
        int len=0;
        byte[] bytes=new byte[1024];
        while((len=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        inputStream.close();
        outputStream.close();
        //将分享图书封面图片删除
        File file=new File(oldAbsolutepath);
        System.out.println(oldAbsolutepath);
        if(file.exists())
            file.delete();
        //修改book的img_path属性
        share.setBook_imgpath(imgpath);
        shareService.addToBooks(share);
        resp.sendRedirect(req.getContextPath()+"/shareServlet?action=pageAllShares&pageNo="+req.getParameter("pageNo"));
    }
}
