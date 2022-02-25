package com.nb.pojo;

import java.util.Date;

//#图书id自增（主键）
//        `book_name` VARCHAR(50) NOT NULL,			#书名
//        `book_author` VARCHAR(50) NOT NULL,			#作者
//        `book_publisher` VARCHAR(50) NOT NULL,			#图书出版社
//        `book_lenduser_id` INT,					#书主id
//        `book_imgpath` VARCHAR(50), 				#图书的封面图片路径
//        `book_count_borrow` INT NOT NULL DEFAULT 0,		#图书的总借阅次数
//        `book_state` VARCHAR(20) DEFAULT 'free'
//        CHECK(`book_state`='free' OR `book_state`='borrowing')	#图书状态free表示未被借阅 borrowing表示借阅中
public class Book {
    private Integer book_id;
    private String book_name;
    private String book_author;
    private String book_publisher;
    private String book_type;
    private Integer book_lenduser_id;
    private String book_imgpath;
    private Integer book_count_borrow;
    private String book_state;
    private String borrow_time;//借阅时间
    private String borrow_nickname;//借书人昵称
    private Integer book_borrowuser_id;//最新借书人id
    private String lend_nickname;//分享图书用户昵称
    private String back_time;//最迟归还时间
    private String borrow_flag;
    private String lend_time;//分享时间

    public Book() {
    }

    public Book(String book_name, String book_author, String book_publisher, String book_type, String book_imgpath) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_type = book_type;
        this.book_imgpath = book_imgpath;
    }

    public Book(String book_name, String book_author, String book_publisher, String book_type, int book_lenduser_id, String book_imgpath) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_type=book_type;
        this.book_lenduser_id = book_lenduser_id;
        this.book_imgpath = book_imgpath;
    }

    public Book(Integer book_id, String book_name, String book_author, String book_publisher, Integer book_lenduser_id, String book_imgpath, Integer book_count_borrow, String book_state) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_lenduser_id = book_lenduser_id;
        this.book_imgpath = book_imgpath;
        this.book_count_borrow = book_count_borrow;
        this.book_state = book_state;
    }

    public String getLend_time() {
        return lend_time;
    }

    public void setLend_time(String lend_time) {
        this.lend_time = lend_time;
    }

    public String getBack_time() {
        return back_time;
    }

    public void setBack_time(String back_time) {
        this.back_time = back_time.replace(".0","");
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public Integer getBook_borrowuser_id() {
        return book_borrowuser_id;
    }

    public void setBook_borrowuser_id(Integer book_borrowuser_id) {
        this.book_borrowuser_id = book_borrowuser_id;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time.replace(".0","");
    }

    public String getBorrow_nickname() {
        return borrow_nickname;
    }

    public void setBorrow_nickname(String borrow_nickname) {
        this.borrow_nickname = borrow_nickname;
    }

    public String getLend_nickname() {
        return lend_nickname;
    }

    public void setLend_nickname(String lend_nickname) {
        this.lend_nickname = lend_nickname;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public Integer getBook_lenduser_id() {
        return book_lenduser_id;
    }

    public void setBook_lenduser_id(Integer book_lenduser_id) {
        this.book_lenduser_id = book_lenduser_id;
    }
    public String getBook_imgpath() {
        return book_imgpath;
    }

    public void setBook_imgpath(String book_imgpath) {
        this.book_imgpath = book_imgpath;
    }

    public Integer getBook_count_borrow() {
        return book_count_borrow;
    }

    public void setBook_count_borrow(Integer book_count_borrow) {
        this.book_count_borrow = book_count_borrow;
    }

    public String getBook_state() {
        return book_state;
    }

    public void setBook_state(String book_state) {
        this.book_state = book_state;
    }

    public String getBorrow_flag() {
        return borrow_flag;
    }

    public void setBorrow_flag(String borrow_flag) {
        this.borrow_flag = borrow_flag;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", book_type='" + book_type + '\'' +
                ", book_lenduser_id=" + book_lenduser_id +
                ", book_imgpath='" + book_imgpath + '\'' +
                ", book_count_borrow=" + book_count_borrow +
                ", book_state='" + book_state + '\'' +
                ", borrow_time=" + borrow_time +
                ", borrow_nickname='" + borrow_nickname + '\'' +
                ", book_borrowuser_id=" + book_borrowuser_id +
                ", lend_nickname='" + lend_nickname + '\'' +
                ", borrow_flag='" + borrow_flag + '\'' +
                '}';
    }
}
