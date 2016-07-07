package com.yawei.service;

import com.yawei.mapper.BookMapper;
import com.yawei.mapper.BookTypeMapper;
import com.yawei.mapper.PublisherMapper;
import com.yawei.pojo.Book;
import com.yawei.pojo.BookType;
import com.yawei.pojo.Publisher;
import com.yawei.util.Page;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class BookService {
    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookTypeMapper bookTypeMapper;
    @Inject
    private PublisherMapper publisherMapper;

    public List<BookType> findAllBookType(){
        return bookTypeMapper.findAll();
    }

    public List<Publisher> findAllPublisher(){
        return publisherMapper.findAll();
    }

    public List<Book> findAllBook(){
        return bookMapper.findAll();
    }

    public Book findBookById(Integer id){
        return bookMapper.findById(id);
    }
    public void saveBook(Book book){
        bookMapper.saveBook(book);
    }

    public void deleteBook(Integer id){
        bookMapper.delete(id);
    }

    public void updateBook(Book book){
        bookMapper.update(book);
    }

    public Page<Book> findBookPage(Integer p, Map<String,Object> param){
        int totalSize=bookMapper.countByParam(param).intValue();
        Page<Book> page = new Page<>(p,5,totalSize);
        param.put("start",page.getStart());
        param.put("size",5);

        List<Book> bookList = bookMapper.findByParam(param);
        page.setItems(bookList);
        return page;
    }

    public List<Book> findDataTables(Map<String, Object> param) {
        return bookMapper.findByDataTable(param);
    }

    public Long count() {
        return bookMapper.count();
    }

    public Long countByKeyWord(String keyword) {
        return bookMapper.countByKeyWord(keyword);
    }
}
