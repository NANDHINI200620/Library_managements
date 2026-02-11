package com.wipro.book.service;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;
public class Administrator {
	 private BookDAO bookDAO = new BookDAO();
	  public String addBook(BookBean bookBean) {
	    if (bookBean == null ||
		        bookBean.getBookName() == null || bookBean.getBookName().isEmpty() ||
		        bookBean.getIsbn() == null || bookBean.getIsbn().isEmpty() ||
		        (bookBean.getBookType() != 'G'&& bookBean.getBookType() != 'T') ||
		        bookBean.getCost() <= 0 ||
		        bookBean.getAuthor() == null ||
		        bookBean.getAuthor().getAuthorName() == null ||
		        bookBean.getAuthor().getAuthorName().isEmpty()) {
		        return "InvalidData";
		    }
	    int res = BookDAO.createBook(bookBean);
	    if (res == 1) {
		        return "Success";
		    }
		    return "Failure";
		}
	public BookBean ViewBook(String isbn) {
		BookBean bookBean=new BookDAO().fetchBook(isbn);
		return bookBean;		
}
}
