package com.nb.dao;

import com.nb.pojo.Booking;

import java.util.List;

public interface BookingDao {
    /**
     * 根据用户id和图书id删除预约记录
     * @param user_id
     * @param book_id
     * @return
     */
    public int deleteBookingByUserIdAndBookId(int user_id,int book_id);

    /**
     * 根据用户姓名查当前页数据
     * @param user_name
     * @return
     */
    public List<Booking> queryAllBookingpageItems(int begin,int pageSize,String user_name);

    /**
     * 根据用户姓名查总记录数
     * @param user_name
     * @return
     */
    public Integer queryBookingpageTotalCount(String user_name);

    public int updateBookStateById(int id);

    /**
     * 删除图书对应的预约
     * @param book_id
     * @return
     */
    public int deleteBooking(int book_id);

    /**
     * 生产新的预约记录
     * @return
     */
    public int createBooking(int user_id,int book_id);

    public List<Booking> queryMyBooking(int user_id);
}
