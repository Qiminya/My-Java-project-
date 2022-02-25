package com.nb.servlet;

import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.pojo.User;
import com.nb.sevice.BookService;
import com.nb.sevice.UserService;
import com.nb.sevice.impl.BookServiceImpl;
import com.nb.sevice.impl.UserServiceImpl;
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

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
@MultipartConfig
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private BookService bookService=new BookServiceImpl();
    protected void queryOneUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));//获取请求参数用户名
        User user=userService.queryUserById(id);
        //将用户的JavaBean保存到域中
        req.setAttribute("user",user);
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/pages/manager/user_information.jsp").forward(req, resp);
    }
    protected void queryOneUser_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));//获取请求参数用户名
        User user=userService.queryUserById(id);
        //将用户的JavaBean保存到域中
        req.setAttribute("user",user);
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/pages/user/user_information.jsp").forward(req, resp);
    }
    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数用户名，旧密码，新密码
        String username=req.getParameter("username");
        String oldPassword=req.getParameter("oldPassword");
        String newPassword=req.getParameter("newPassword");
        int flag = userService.updatePassword(username, oldPassword, newPassword);
        if(flag==1){//修改成功
            //销毁session对象
            req.getSession().invalidate();
            //转发至密码修改成功页面
            resp.sendRedirect(req.getContextPath()+"/pages/user/update_password_success.jsp");
        }else if(flag==0){//旧密码错误
            req.setAttribute("mes","旧密码错误！！！");
            req.getRequestDispatcher("/pages/user/update_password.jsp").forward(req,resp);
        }
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/pages/user/user_information.jsp").forward(req, resp);
    }
    protected void resetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id=Integer.parseInt(req.getParameter("user_id"));//获取请求参数用户名
        String password=req.getParameter("password");//获取输入的新密码
        int flag=userService.resetPassword(user_id,password);
        if(flag==1){//成功重置密码
            //跳到登录页面用户信息界面.jsp
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
    protected void updateUser_info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将图书属性参数封装成javaBean
        User user1 = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String oldPath = req.getParameter("oldPath");//原文件虚拟路径
        String s=req.getParameter("selectFlag");
        //未选中文件
        if(s.equals("false")){
            user1.setUser_imgpath(oldPath);
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
            String imgpath="/users/"+fileName;//存入数据库的图片虚拟路径
            String absolutepath="D:/uploadFiles/users/"+fileName;//文件存储的绝对路径
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
            user1.setUser_imgpath(imgpath);
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
        userService.updateUser_info(user1);
        User user = userService.queryUserById(user1.getUser_id());
        req.getSession().removeAttribute("user");
        req.getSession().setAttribute("user",user);
        req.getRequestDispatcher("pages/user/personal.jsp").forward(req,resp);
    }
    protected void initPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户id
        int id=Integer.parseInt(req.getParameter("id"));
        User user=userService.queryUserById(id);
        System.out.println(user);
        //设置密码为123456789
        user.setPassword("123456789");
        System.out.println(user);
        int flag=userService.initPassword(user);//重置成功
        req.setAttribute("flag",flag+"");
        //将用户的JavaBean保存到域中
        //req.setAttribute("user",user);
        //获得当前页数
        String pageNo = req.getParameter("pageNo");
        //跳到成功页面用户信息界面.jsp
        req.getRequestDispatcher("/userServlet?action=pageAllUsers&pageNo="+pageNo+"&flag="+flag).forward(req,resp);
    }
    protected void pageAllUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数pageNo和pageSize
        int pageNo;
        if(req.getParameter("pageNo")==null){
            pageNo = Integer.parseInt("1");
        }else {
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize = 15;
        String studentcode=req.getParameter("studentcode");
        if(studentcode==null){
            studentcode="";
        }
        //2.调用BookService.page(pageNo,pageSize):得到page对象
        Page<User> page=userService.page(pageNo,pageSize,studentcode);
        page.setUrl("userServlet?action=pageAllUsers");
        //3.保存Page对象到Request域中
        req.setAttribute("page",page);
        req.setAttribute("studentcode",studentcode);
        req.setAttribute("flag",req.getParameter("flag"));//重置密码成功标识-1失败
        //4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/users_manager.jsp").forward(req,resp);
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username=req.getParameter("username");//获取请求参数用户名
        String password=req.getParameter("password");//获取请求参数用户密码
        User loginUser=userService.login(new User(username,password,null));
        if(loginUser==null){//登录失败
            //保存用户名，提示错误信息
            req.setAttribute("mes","用户名或密码错误");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{//登录成功
            //将登录成功的用户JavaBean保存到Ssesion域中
            req.getSession().setAttribute("user",loginUser);
            List<Book> books=bookService.queryHotBooks();
            req.setAttribute("books",books);
            //跳到成功页面login_success.jsp
            req.getRequestDispatcher("/pages/user/personal.jsp").forward(req, resp);
        }
    }
    protected void adminlogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username=req.getParameter("username");//获取请求参数用户名
        String password=req.getParameter("password");//获取请求参数用户密码
        User loginUser=userService.adminlogin(new User(username,password,null));
        if(loginUser==null){//登录失败
            //保存用户名，提示错误信息
            req.setAttribute("mes","管理员账号或密码错误");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/manager/login.jsp").forward(req,resp);
        }else{//登录成功
            //将登录成功的用户JavaBean保存到Ssesion域中
            req.getSession().setAttribute("user",loginUser);
            //跳到成功页面login_success.jsp
            resp.sendRedirect(req.getContextPath()+"/pages/manager/personal.jsp");
            //req.getRequestDispatcher("/pages/manager/personal.jsp").forward(req, resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取请求参数
        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());
        String code=req.getParameter("code");
        //服务器生成的验证码
        String token=(String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //2.判断验证码
        if (token.equals(code)){//验证码正确
            //检测用户名是否可用
            if(userService.existsUsername(user.getUsername())){//存在
                //保存用户名，邮箱，提示用户名已存在
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                req.setAttribute("mes","用户名已存在");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{//用户名可用保存用户信息到数据库
                userService.registUser(user);
                //跳到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{//验证码错误
            //回传用户名，邮箱，提示验证码错误
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            req.setAttribute("mes","验证码错误");
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
    protected void findpassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.获取请求参数
        String username=req.getParameter("username");
        String email=req.getParameter("email");
        String code=req.getParameter("code");
        String token=(String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if(code.equals(token)) {//验证码正确
            //判断用户名是否合法
            if(userService.existsUsername(username)){//用户名存在可找回
                //匹配用户名和邮箱
                User user=userService.findPassword(username,email);
                if(user==null){//用户名和邮箱不匹配
                    //回传用户名、提示用户名和邮箱不匹配
                    req.setAttribute("username",username);
                    req.setAttribute("mes","用户名和密码不匹配");
                }else{//校验成功
                    req.setAttribute("user_id",user.getUser_id());
                    req.getRequestDispatcher("/pages/user/reset_password.jsp").forward(req,resp);
                }
            }else{//用户名不存在
                //回传用户名、提示用户名不存在
                req.setAttribute("username",username);
                req.setAttribute("mes","用户名不存在");
                //跳转至找回密码页面
                req.getRequestDispatcher("/pages/user/findpassword.jsp").forward(req,resp);
            }
        }else{//验证码错误
            //回传用户名，邮箱，提示验证码错误
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.setAttribute("mes","验证码错误");
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/findpassword.jsp").forward(req,resp);
        }
    }
}
