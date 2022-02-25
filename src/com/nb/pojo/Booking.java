package com.nb.pojo;

import java.util.Date;

public class Booking {
    private String user_name;
    private Integer user_id;
    private String book_name;
    private String book_imgpath;
    private Integer book_id;
    private String booking_time;

    public Booking() {
    }

    public Booking(String user_name, Integer user_id, String book_name, Integer book_id, String booking_time) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.book_name = book_name;
        this.book_id = book_id;
        this.booking_time = booking_time;
    }

    public String getBook_imgpath() {
        return book_imgpath;
    }

    public void setBook_imgpath(String book_imgpath) {
        this.book_imgpath = book_imgpath;
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

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time.replace(".0","");
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "user_name='" + user_name + '\'' +
                ", user_id=" + user_id +
                ", book_name='" + book_name + '\'' +
                ", book_id=" + book_id +
                ", booking_time=" + booking_time +
                '}';
    }
}
