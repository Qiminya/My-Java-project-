package com.nb.pojo;

import com.nb.utils.TimeUtils;
import com.nb.utils.WebUtils;

import java.text.ParseException;
import java.util.Date;

public class Comment {
    private Integer comment_id;
    private Integer user_ing_id;
    private String user_ing_nickname;
    private Integer user_ed_id;
    private String user_ed_nickname;
    private String comment_content;
    private String comment_time;

    public Comment() {
    }

    public Comment(Integer comment_id, Integer user_ing_id, String user_ing_nickname, Integer user_ed_id, String user_ed_nickname, String comment_content, String comment_time) {
        this.comment_id = comment_id;
        this.user_ing_id = user_ing_id;
        this.user_ing_nickname = user_ing_nickname;
        this.user_ed_id = user_ed_id;
        this.user_ed_nickname = user_ed_nickname;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_ing_id() {
        return user_ing_id;
    }

    public void setUser_ing_id(Integer user_ing_id) {
        this.user_ing_id = user_ing_id;
    }

    public String getUser_ing_nickname() {
        return user_ing_nickname;
    }

    public void setUser_ing_nickname(String user_ing_nickname) {
        this.user_ing_nickname = user_ing_nickname;
    }

    public Integer getUser_ed_id() {
        return user_ed_id;
    }

    public void setUser_ed_id(Integer user_ed_id) {
        this.user_ed_id = user_ed_id;
    }

    public String getUser_ed_nickname() {
        return user_ed_nickname;
    }

    public void setUser_ed_nickname(String user_ed_nickname) {
        this.user_ed_nickname = user_ed_nickname;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) throws ParseException {
        this.comment_time = comment_time.replace(".0","");
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", user_ing_id=" + user_ing_id +
                ", user_ing_nickname='" + user_ing_nickname + '\'' +
                ", user_ed_id=" + user_ed_id +
                ", user_ed_nickname='" + user_ed_nickname + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_time=" + comment_time +
                '}';
    }
}
