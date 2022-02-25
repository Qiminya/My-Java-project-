package com.nb.pojo;

public class Share {
    private Integer share_id;
    private String book_name;
    private String book_author;
    private String book_publisher;
    private String book_type;
    private Integer book_lenduser_id;
    private String book_lenduser_name;
    private String book_imgpath;

    public Share() {
    }

    public Share(Integer share_id, String book_name, String book_author, String book_publisher, String book_type, Integer book_lenduser_id, String book_lenduser_name, String book_imgpath) {
        this.share_id = share_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_type = book_type;
        this.book_lenduser_id = book_lenduser_id;
        this.book_lenduser_name = book_lenduser_name;
        this.book_imgpath = book_imgpath;
    }

    public Integer getShare_id() {
        return share_id;
    }

    public void setShare_id(Integer share_id) {
        this.share_id = share_id;
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

    public String getBook_lenduser_name() {
        return book_lenduser_name;
    }

    public void setBook_lenduser_name(String book_lenduser_name) {
        this.book_lenduser_name = book_lenduser_name;
    }

    public String getBook_imgpath() {
        return book_imgpath;
    }

    public void setBook_imgpath(String book_imgpath) {
        this.book_imgpath = book_imgpath;
    }

    @Override
    public String toString() {
        return "Share{" +
                "share_id=" + share_id +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", book_type='" + book_type + '\'' +
                ", book_lenduser_id=" + book_lenduser_id +
                ", book_lenduser_name='" + book_lenduser_name + '\'' +
                ", book_imgpath='" + book_imgpath + '\'' +
                '}';
    }
}
