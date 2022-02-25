package com.nb.pojo;

import java.util.Date;

public class Back {
    private Integer back_id;
    private String book_name;
    private Integer book_id;
    private String back_time;
    private String max_back_time;
    private Integer flag;
    public Back() {
    }

    public Back(Integer back_id, String book_name, Integer book_id, String back_time, String max_back_time, Integer flag) {
        this.back_id = back_id;
        this.book_name = book_name;
        this.book_id = book_id;
        this.back_time = back_time;
        this.max_back_time = max_back_time;
        this.flag = flag;
    }

    public Integer getBack_id() {
        return back_id;
    }

    public void setBack_id(Integer back_id) {
        this.back_id = back_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBack_time() {
        return back_time;
    }

    public void setBack_time(String back_time) {
        this.back_time = back_time.replace(".0","");
    }

    public String getMax_back_time() {
        return max_back_time;
    }

    public void setMax_back_time(String max_back_time) {
        this.max_back_time = max_back_time.replace(".0","");
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Back{" +
                "back_id=" + back_id +
                ", book_name='" + book_name + '\'' +
                ", book_id=" + book_id +
                ", back_time=" + back_time +
                ", max_back_time=" + max_back_time +
                ", flag=" + flag +
                '}';
    }
}
