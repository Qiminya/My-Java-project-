package com.nb.dao.impl;

import com.nb.dao.BookingDao;
import com.nb.pojo.Booking;

import java.util.List;

public class BookingDaoImpl extends BaseDao implements BookingDao {
    @Override
    public int deleteBookingByUserIdAndBookId(int user_id, int book_id) {
        String sql="delete from bookings where `user_id`=? and `book_id`=?";
        return update(sql,user_id,book_id);
    }

    @Override
    public List<Booking> queryAllBookingpageItems(int begin,int pageSize, String user_name) {
        String sql="SELECT u.`name` AS 'user_name',b.`user_id`,books.`book_name`,b.`book_id`,`booking_time` " +
                "FROM bookings b JOIN users u ON b.`user_id`=u.`user_id` " +
                "JOIN books ON books.`book_id`=b.`book_id` " +
                "WHERE u.`name` like ? ORDER BY `booking_time` DESC "+
                "limit ?,?";
        return queryForList(Booking.class,sql,"%"+user_name+"%",begin,pageSize);
    }

    @Override
    public Integer queryBookingpageTotalCount(String user_name) {
        String sql="SELECT count(*) " +
                "FROM bookings b JOIN users u ON b.`user_id`=u.`user_id` " +
                "WHERE u.`name` like ? ";
        Number number= (Number) queryForSingleValue(sql,"%"+user_name+"%");
        return number.intValue();
    }
    @Override
    public int updateBookStateById(int id){
        String sql="update books set `book_state`='booking' where `book_id`=? and `book_state`='free'";
        return update(sql,id);
    }

    @Override
    public int deleteBooking(int book_id) {
        String sql="delete from bookings where `book_id`=?";
        return update(sql,book_id);
    }

    @Override
    public int createBooking(int user_id,int book_id) {
        String sql="insert into bookings (`user_id`,`book_id`,`booking_time`)value(?,?,now())";
        return update(sql,user_id,book_id);
    }

    @Override
    public List<Booking> queryMyBooking(int user_id) {
        String sql="select bookings.`book_id`,`book_imgpath`,`book_name`,`booking_time` from bookings "
                +"join books on books.`book_id`=bookings.`book_id` where bookings.`user_id`=?";
        return queryForList(Booking.class,sql,user_id);
    }
}
