package com.yawei.controller;

import com.google.common.collect.Maps;
import com.yawei.pojo.Book;
import com.yawei.service.BookService;
import com.yawei.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/datatables")
public class DataTableController {
    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "datatables/table";
    }

    @RequestMapping(value = "/data.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = Strings.toUtf8(request.getParameter("search[value]"));
        String sortColumnIndex=request.getParameter("order[0][column]");
        String sortColumnName=request.getParameter("order["+sortColumnIndex+"][name]");
        String sortType=request.getParameter("order[0][dir]");

        Map<String,Object> map= Maps.newHashMap();
        map.put("start",start);
        map.put("length",length);
        map.put("keyword",keyword);
        map.put("sortColumnName",sortColumnName);
        map.put("sortType",sortType);

        List<Book> bookList = bookService.findDataTables(map);

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordsFiltered",bookService.countByKeyWord(keyword));
        result.put("data",bookList);
        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String saveBook(Book book){
        bookService.saveBook(book);
        return "success";
    }
}
