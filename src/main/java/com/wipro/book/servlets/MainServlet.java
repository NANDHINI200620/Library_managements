package com.wipro.book.servlets;

import java.io.IOException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String res = addBook(request);

            if ("Success".equals(res)) {
                response.sendRedirect("Menu.html");
            } else if ("Failure".equals(res)) {
            	response.sendRedirect("Failure.html");
             
            } else if("Invalid".equals(res)){
            	response.sendRedirect("InvalidData.html");
            }

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean bookBean = ViewBook(isbn);

            if (bookBean == null) {
                response.sendRedirect("InvalidData.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", bookBean);
                RequestDispatcher rd = request.getRequestDispatcher("viewServlet");
                rd.forward(request, response);
            }
        }
    }

    public String addBook(HttpServletRequest request) {

        String isbn = request.getParameter("isbn");
        String bookName = request.getParameter("bookName");
        String bookType = request.getParameter("bookType");
        String authorName = request.getParameter("authorName");
        String cost = request.getParameter("cost");

        BookBean bookBean = new BookBean();
        bookBean.setIsbn(isbn);
        bookBean.setBookName(bookName);
        bookBean.setBookType(bookType.charAt(0));
        bookBean.setAuthor(AuthorDAO.getAuthor(authorName));
        bookBean.setCost(Float.parseFloat(cost));

        return new Administrator().addBook(bookBean);
    }

    public BookBean ViewBook(String isbn) {
        return new Administrator().ViewBook(isbn);
    }
}
