package com.nb.dao;

import com.nb.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 当前页数据
     * @param begin
     * @param pageSize
     * @param studentcode
     * @return
     */
    public List<User> queryForPageItems(int begin, int pageSize,String studentcode);

    /**
     * 总记录数
     * @param studentcode
     * @return
     */
    public Integer queryForPageTotalCount(String studentcode);
    /**
     * 根据用户名查用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误,反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 根据 用户名和邮箱查询用户信息
     * @param username
     * @param email
     * @return
     */
    public User queryUserByUsernameAndEmail(String username,String email);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查用户
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword_admin(String username, String password);

    /**
     * 根据用户名修改其密码
     * @param username
     * @param newPassword
     * @return
     */
    public int updatePassword(String username,String newPassword);

    /**
     * 根据id查用户
     * @param id
     * @return
     */
    User queryUserById(int id);


    /**
     * 重置密码
     * @param user
     * @return
     */
    public int initPassword(User user);

    public int addNowBorrowCount(int user_id);

    public int reduceNowBorrowCount(int user_id);

    public int addBorrowCount(int user_id);

    public int addLendCount(int user_id);

    public int addCountLate(int user_id);

    public int isfull(int user_id);

    public int updateUser_info(Integer user_id, String user_imgpath, String nickname, String name, String sex, String studentcode, String signature);

    public int resetUserPassword(int user_id,String password);
}
