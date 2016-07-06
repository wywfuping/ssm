package com.yawei.service;

import com.yawei.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceTestCase {

    private Logger logger = LoggerFactory.getLogger(BookServiceTestCase.class);
    @Inject
    private BookService bookService;

    @Test
    public void testFindBookById() {
        Book book = bookService.findBookById(1);
        Assert.assertNotNull(book);
    }

    @Test
    public void testFindAllBook() {
        List<Book> bookList = bookService.findAllBook();
        Assert.assertEquals(bookList.size(), 29);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setBookname("把时间当作朋友");
        book.setBookauthor("李笑来");
        book.setBooknum(100);
        book.setBookprice(56.5F);
        book.setPubid(1);
        book.setTypeid(2);

        bookService.saveBook(book);
    }

    @Test
    public void testDeleteBook() {
        bookService.deleteBook(30);
    }
    @Test
    public void testUpdateBook() {
        Book book=bookService.findBookById(1);
        book.setBookprice(120F);
        bookService.updateBook(book);
    }
}
