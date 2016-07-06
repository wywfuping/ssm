package com.yawei.controller;

import com.google.common.collect.Maps;
import com.yawei.exception.NotFindException;
import com.yawei.pojo.Book;
import com.yawei.pojo.BookType;
import com.yawei.pojo.Publisher;
import com.yawei.service.BookService;
import com.yawei.util.Page;
import com.yawei.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {
    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1") Integer p,
                       @RequestParam(required = false) String bookname,
                       @RequestParam(required = false) Integer type,
                       @RequestParam(required = false) Integer pub,
            Model model) {

        bookname = Strings.toUtf8(bookname);

        Map<String,Object> map = Maps.newHashMap();
        map.put("bookname",bookname);
        map.put("type",type);
        map.put("pub",pub);

        Page<Book> page = bookService.findBookPage(p,map);

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        model.addAttribute("page",page);

        model.addAttribute("bookname",bookname);
        model.addAttribute("typeid",type);
        model.addAttribute("pubid",pub);

        return "books/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String saveBook(Model model) {
        List<BookType> bookTypeList = bookService.findAllBookType();
        List<Publisher> publisherList = bookService.findAllPublisher();

        model.addAttribute("pubs", publisherList);
        model.addAttribute("types", bookTypeList);

        return "books/new";
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id, Model model) {
        Book book = bookService.findBookById(id);
        if(book == null) {
            throw new NotFindException();
        }

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        model.addAttribute("book",book);
        return "books/edit";
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.POST)
    public String editBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.updateBook(book);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }
    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }


}
