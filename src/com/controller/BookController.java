package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BaseController;
import com.CtxUtil;
import com.mybatis.entities.Book;
import com.mybatis.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("book")
public class BookController extends BaseController {
    private static Logger logger = Logger.getLogger(BookController.class);
    private static final long serialVersionUID = 1L;

    @Resource
    BookService bookservice;

    @Override
    public void init() throws ServletException {
        bookservice = CtxUtil.getBean(BookService.class);
    }

    // 图书列表Action
    @RequestMapping("/ListBook.do")
    public String ListBook(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("books", bookservice.getAllBooks());
        return "books/ListBook";
    }

    // 删除图书Action
    @RequestMapping("/Delete.do")
    public String Delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("message", bookservice.delete(id) > 0 ? "删除成功！" : "删除失败！");
        request.setAttribute("books", bookservice.getAllBooks());
        return "books/ListBook";
    }

    // 多删除图书Action
    @RequestMapping("/Deletes.do")
    public String Deletes(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("ids");
        if (ids!=null&&ids.length > 0) {
            request.setAttribute("message", bookservice.delete(ids) > 0 ? "删除成功！" : "删除失败！");
        } else {
            request.setAttribute("message", "请选择删除项！");
        }
        request.setAttribute("books", bookservice.getAllBooks());
        return "books/ListBook";
    }

    // 添加图书Action
    @RequestMapping("/AddBook.do")
    public String AddBook(HttpServletRequest request, HttpServletResponse response) {
        return "books/AddBook";
    }

    // 保存添加图书Action
    @RequestMapping("/AddBookPost.do")
    public String AddBookPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String title = request.getParameter("title");
            double price = Double.parseDouble(request.getParameter("price"));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));

            Book entity = new Book(0, title, price, publishDate);
            if (bookservice.add(entity) > 0) {
                request.setAttribute("model", new Book());
                request.setAttribute("message", "添加成功！");
            } else {
                request.setAttribute("model", entity);
                request.setAttribute("message", "添加失败！");
            }
        } catch (Exception exp) {
            request.setAttribute("message", exp.getMessage());
            exp.printStackTrace();
        }
        return "books/AddBook";
    }
    
        //编辑图书Action
        @RequestMapping("/EditBook.do")
        public String EditBook(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            Book model=bookservice.getBookById(id);
            request.setAttribute("model", model);
            return "books/EditBook";
        }

        // 保存编辑图书Action
        @RequestMapping("/EditBookPost.do")
        public String EditBookPost(HttpServletRequest request, HttpServletResponse response) {
            try {
                int id=Integer.parseInt(request.getParameter("id"));
                
                String title = request.getParameter("title");
                double price = Double.parseDouble(request.getParameter("price"));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));

                Book entity = new Book(id, title, price, publishDate);
                request.setAttribute("message", bookservice.update(entity) > 0 ? "更新成功！" : "更新失败！");
                request.setAttribute("model", entity);
            } catch (Exception exp) {
                request.setAttribute("message", exp.getMessage());
                exp.printStackTrace();
            }
            return "books/EditBook";
        }

}