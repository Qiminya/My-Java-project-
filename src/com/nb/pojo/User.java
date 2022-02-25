package com.nb.pojo;

/**
 * 用户对象
 */
public class User {
    private Integer user_id;                     //用户id
    private String username;                //用户名
    private String password;                //用户密码
    private String nickname;                //用户昵称
    private String user_imgpath;            //用户个人照片路径
    private String name;                    //用户真实姓名
    private String sex;                     //用户性别
    private String email;                   //用户注册邮箱
    private String studentcode;             //用户学籍号
    private Integer power_grade;            //用户权限等级
    private Integer now_count_borrow;       //当前借书数量
    private String signature;               //个性签名
    private Integer count_borrow;           //用户总借入书数量，初始为0
    private Integer count_lend;             //用户总借出书数量，初始为0
    private Integer count_late;             //用户总逾期次数，初始为0

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String username, String password, String nickname, String user_imgpath, String name, String sex, String email, String studentcode, Integer power_grade, String signature, Integer count_borrow, Integer count_lend, Integer count_late) {
        this.user_id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.user_imgpath = user_imgpath;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.studentcode = studentcode;
        this.power_grade = power_grade;
        this.signature = signature;
        this.count_borrow = count_borrow;
        this.count_lend = count_lend;
        this.count_late = count_late;
    }

    public Integer getNow_count_borrow() {
        return now_count_borrow;
    }

    public void setNow_count_borrow(Integer now_count_borrow) {
        this.now_count_borrow = now_count_borrow;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_imgpath() {
        return user_imgpath;
    }

    public void setUser_imgpath(String user_imgpath) {
        this.user_imgpath = user_imgpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode;
    }

    public Integer getPower_grade() {
        return power_grade;
    }

    public void setPower_grade(Integer power_grade) {
        this.power_grade = power_grade;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getCount_borrow() {
        return count_borrow;
    }

    public void setCount_borrow(Integer count_borrow) {
        this.count_borrow = count_borrow;
    }

    public Integer getCount_lend() {
        return count_lend;
    }

    public void setCount_lend(Integer count_lend) {
        this.count_lend = count_lend;
    }

    public Integer getCount_late() {
        return count_late;
    }

    public void setCount_late(Integer count_late) {
        this.count_late = count_late;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", user_imgpath='" + user_imgpath + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", studentcode='" + studentcode + '\'' +
                ", power_grade=" + power_grade +
                ", now_count_borrow=" + now_count_borrow +
                ", signature='" + signature + '\'' +
                ", count_borrow=" + count_borrow +
                ", count_lend=" + count_lend +
                ", count_late=" + count_late +
                '}';
    }
}
