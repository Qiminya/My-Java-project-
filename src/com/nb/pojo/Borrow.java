package com.nb.pojo;

import java.util.Date;

public class Borrow {
    private Integer borrow_id;
    private String name;
    private Integer user_id;
    private String book_name;
    private Integer book_id;
    private String borrow_time;
    private String back_time;
    private String borrow_flag;

    public Borrow() {
    }

    public Borrow(Integer user_id, Integer book_id, String borrow_time) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrow_time = borrow_time;
    }

    public Borrow(Integer borrow_id, Integer user_id, Integer book_id, String borrow_time, String back_time) {
        this.borrow_id = borrow_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrow_time = borrow_time;
        this.back_time = back_time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(Integer borrow_id) {
        this.borrow_id = borrow_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time.replace(".0","");
    }

    public String getBack_time() {
        return back_time;
    }

    public void setBack_time(String back_date) {
        this.back_time = back_date.replace(".0","");
    }

    public String getBorrow_flag() {
        return borrow_flag;
    }

    public void setBorrow_flag(String borrow_flag) {
        this.borrow_flag = borrow_flag;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrow_id=" + borrow_id +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", book_name='" + book_name + '\'' +
                ", book_id=" + book_id +
                ", borrow_time=" + borrow_time +
                ", back_time=" + back_time +
                ", borrow_flag='" + borrow_flag + '\'' +
                '}';
    }
}
