package com.nb.sevice;

import com.nb.pojo.Page;
import com.nb.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);

    public User adminlogin(User user);

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);

    /**
     * 根据用户名，邮箱查找用户信息
     * @param username
     * @param email
     * @return
     */
    public User findPassword(String username,String email);

    /**
     * 对所有（学籍模糊）用户进行分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<User> page(int pageNo,int pageSize,String studentcode);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    public User queryUserById(int id);


    /**
     * 重置用户密码为123456789
     * @param user
     * @return返回-1失败其他为影响行数
     */
    public int initPassword(User user);

    /**
     * 用户修改个人资料
     * @param user
     * @return
     */
    public int updateUser_info(User user);

    /**
     * 忘记密码重置密码
     * @param user_id
     * @param password
     * @return
     */
    public int resetPassword(int user_id,String password);

    /**
     * 用户修改密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    public int updatePassword(String username,String password,String newPassword);
}
