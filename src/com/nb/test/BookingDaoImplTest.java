package com.nb.test;

import com.nb.dao.BookingDao;
import com.nb.dao.impl.BookingDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingDaoImplTest {
    BookingDao bookingDao=new BookingDaoImpl();
    @Test
    public void deleteBookingByUserIdAndBookId() {
        System.out.println(bookingDao.deleteBookingByUserIdAndBookId(1,1));
    }

    @Test
    public void queryAllBookingpageItems() {
        System.out.println(bookingDao.queryAllBookingpageItems(0,1,"郑"));

    }

    @Test
    public void queryBookingpageTotalCount() {
        System.out.println(bookingDao.queryBookingpageTotalCount("郑"));
    }
}