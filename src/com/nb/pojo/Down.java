package com.nb.pojo;

public class Down {
    private Integer book_id;
    private String book_name;
    private String down_time;
    private String down_cause;

    public Down() {
    }

    public Down(Integer book_id, String book_name, String down_time, String down_cause) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.down_time = down_time;
        this.down_cause = down_cause;
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

    public String getDown_time() {
        return down_time;
    }

    public void setDown_time(String down_time) {
        this.down_time = down_time.replace(".0","");
    }

    public String getDown_cause() {
        return down_cause;
    }

    public void setDown_cause(String down_cause) {
        this.down_cause = down_cause;
    }

    @Override
    public String toString() {
        return "Down{ "+
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", down_time='" + down_time + '\'' +
                ", down_cause='" + down_cause + '\'' +
                '}';
    }
}
