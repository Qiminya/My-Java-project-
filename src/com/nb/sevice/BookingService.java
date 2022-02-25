package com.nb.sevice;

import com.nb.pojo.Booking;
import com.nb.pojo.Page;

import java.util.List;

public interface BookingService {
    /**
     * 根据预约用户id，图书id完成用户预约到借出
     * @param user_id
     * @param book_id
     * @return
     */
    public int agreeBorrow(int user_id,int book_id);

    /**
     * 根据用户姓名模糊查询name为空查所有
     * @param user_name
     * @return
     */
    public Page<Booking> pageAllBooking(int begin,int pageSize,String user_name);

    /**
     * 删除预约
     * @param book_id
     * @return
     */
    public int deleteBooking(int book_id);

    public int createBooking(int user_id,int book_id);

    public List<Booking> queryMyBooking(int user_id);
}
