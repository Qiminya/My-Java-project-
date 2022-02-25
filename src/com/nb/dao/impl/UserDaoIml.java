package com.nb.dao.impl;

import com.nb.dao.UserDao;
import com.nb.pojo.User;

import java.util.List;

public class UserDaoIml extends BaseDao implements UserDao {
    @Override
    public List<User> queryForPageItems(int begin, int pageSize,String studentcode) {
        String sql="select * from users where `studentcode` like ? limit ?,?";
        return queryForList(User.class,sql,studentcode,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount(String studentcode) {
        String sql="select count(*) from users where `studentcode` like ?";
        Number number= (Number) queryForSingleValue(sql,studentcode);
        return number.intValue();
    }

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from users where `username` = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from users where `username` = ? and `password`=?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public User queryUserByUsernameAndPassword_admin(String username, String password) {
        String sql = "select * from admin where `username` = ? and `password`=?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int updatePassword(String username, String newPassword) {
        String sql="update users set `password`=? where `username`=?";
        return update(sql,newPassword,username);
    }

    @Override
    public User queryUserById(int id) {
        String sql="select * from users where `user_id`=?";
        return queryForOne(User.class,sql,id);
    }

    @Override
    public int initPassword(User user) {
        String sql="update users set `password`=? where `user_id`=?";
        return update(sql,user.getPassword(),user.getUser_id());
    }

    @Override
    public int addNowBorrowCount(int user_id) {
        String sql="update users set `now_count_borrow`=`now_count_borrow`+1 where `user_id`=?";
        return update(sql,user_id);
    }

    @Override
    public int reduceNowBorrowCount(int user_id) {
        String sql="update users set `now_count_borrow`=`now_count_borrow`-1 where `user_id`=?";
        return update(sql,user_id);
    }

    @Override
    public int addBorrowCount(int user_id) {
        String sql="update users set `count_borrow`=`count_borrow`+1 where `user_id`=?";
        return update(sql,user_id);
    }

    @Override
    public int addLendCount(int user_id) {
        String sql="update users set `count_lend`=`count_lend`+1 where `user_id`=?";
        return update(sql,user_id);
    }

    @Override
    public int addCountLate(int user_id) {
        String sql="update users set `count_late`=`count_late`+1 where `user_id`=?";
        return update(sql,user_id);
    }

    @Override
    public int isfull(int user_id) {
        String sql="SELECT `now_count_borrow`=`borrow_count` " +
                "FROM users u JOIN grades g ON (SELECT `power_grade` FROM users WHERE `user_id`=?)=g.`power_grade` " +
                "WHERE `user_id`=?";
        Number number= (Number) queryForSingleValue(sql,user_id,user_id);
        return number.intValue();
    }

    @Override
    public int updateUser_info(Integer user_id, String user_imgpath, String nickname, String name, String sex, String studentcode, String signature) {
        String sql="update users set `user_imgpath`=?,`nickname`=?,`name`=?,`sex`=?,`studentcode`=?,`signature`=? where `user_id`=?";
        return update(sql,user_imgpath,nickname,name,sex,studentcode,signature,user_id);
    }

    @Override
    public int resetUserPassword(int user_id,String password) {
        String sql="update users set `password`=? where `user_id`=?";
        return update(sql,password,user_id);
    }

    @Override
    public User queryUserByUsernameAndEmail(String username, String email) {
        String sql = "select * from users where `username`=? and `email`=?";
        return queryForOne(User.class, sql, username, email);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into users (`username`,`password`,`email`)value(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
