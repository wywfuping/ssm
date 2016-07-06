package com.yawei.mapper;


import com.yawei.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    void saveBook(Book book);
    Book findById(Integer id);
    List<Book> findAll();
    void delete(Integer id);
    void update(Book book);

    Long count();
    Long countByParam(Map<String,Object> param);

    List<Book> findByPage(@Param("start") Integer start,@Param("size") Integer size);
    List<Book> findByParam(Map<String,Object> param);
}
