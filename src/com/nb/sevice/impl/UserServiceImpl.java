package com.nb.sevice.impl;

import com.nb.dao.UserDao;
import com.nb.dao.impl.UserDaoIml;
import com.nb.pojo.Book;
import com.nb.pojo.Page;
import com.nb.pojo.User;
import com.nb.sevice.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoIml();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public User adminlogin(User user) {
        return userDao.queryUserByUsernameAndPassword_admin(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null)
            return false;//为空未查到，用户名可用
        else
            return true;
    }

    @Override
    public User findPassword(String username, String email) {
        return userDao.queryUserByUsernameAndEmail(username,email);
    }

    @Override
    public Page<User> page(int pageNo, int pageSize,String studentcode) {
        //新建个用户分页对象
        Page<User> page = new Page<User>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = userDao.queryForPageTotalCount("%"+studentcode+"%");
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
        List<User> items = userDao.queryForPageItems(begin,pageSize,"%"+studentcode+"%");
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public int initPassword(User user) {
        return userDao.initPassword(user);
    }

    @Override
    public int updateUser_info(User user) {
        return userDao.updateUser_info(user.getUser_id(),user.getUser_imgpath(),user.getNickname(),user.getName(),user.getSex(),user.getStudentcode(),user.getSignature());
    }

    @Override
    public int resetPassword(int user_id,String password) {
        return userDao.resetUserPassword(user_id,password);
    }

    @Override
    public int updatePassword(String username, String password, String newPassword) {
        User user = userDao.queryUserByUsername(username);
        if(user.getPassword().equals(password)){//旧密码输入正确
            userDao.updatePassword(username,newPassword);
            return 1;//修改成功
        }else {//旧密码输入错误
            return 0;//旧密码错误
        }
    }
}
