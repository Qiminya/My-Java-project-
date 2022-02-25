package com.nb.pojo;

public class Up {
    private Integer book_id;
    private String book_name;
    private Integer user_id;
    private String nickname;
    private String up_time;

    public Up() {
    }

    public Up( Integer book_id, Integer user_id, String up_time) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.up_time = up_time;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time.replace(".0","");
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Up{" +
                ", book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", up_time='" + up_time + '\'' +
                '}';
    }
}
