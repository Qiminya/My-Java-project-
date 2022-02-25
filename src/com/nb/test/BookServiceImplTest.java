package com.nb.test;

import com.nb.pojo.Book;
import com.nb.sevice.BookService;
import com.nb.sevice.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    BookService bookService=new BookServiceImpl();
    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooksByName() {
    }

    @Test
    public void queryBooks() {
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(2);
    }

    @Test
    public void addBook() {
        bookService.addBook(new Book("钢铁是怎样炼成的","尼古拉·奥斯特洛夫斯基","中国致公出版社","文学",1,"/img/1.jpg"));
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1,4,"%free%","%java%"));
    }

    @Test
    public void pageByName() {
        System.out.println(bookService.pageByName(1,4,"三国演义"));
    }
    @Test
    public void update(){
        bookService.updateBook(new Book(31,"三演义","罗贯中","中国出版社",1,"sad",999,"borrowing"));
    }
}