package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {
	public AuthorBean getAuthor(int authorCode) {
	Connection connection = DBUtil.getDBConnection();
	String query = "select * from Author_Tbl where authorCode=?";
    try (
         PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, authorCode);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
        	AuthorBean authorBean=new AuthorBean();
        	authorBean.setAuthorcode(rs.getInt(1));
        	authorBean.setAuthorName(rs.getString(2));
        	authorBean.setContactNo(rs.getLong(3));
        	return authorBean;
        }
        else {
    return null;
    }
    }
catch (SQLException e) {
    e.printStackTrace();
}
	return null;
}
	public static AuthorBean getAuthor(String authorName) {
		Connection connection = DBUtil.getDBConnection();
		String query = "select * from Author_Tbl where author_Name=?";
	    try (
	         PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, authorName);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	        	AuthorBean authorBean=new AuthorBean();
	        	authorBean.setAuthorcode(rs.getInt(1));
	        	authorBean.setAuthorName(rs.getString(2));
	        	authorBean.setContactNo(rs.getLong(3));
	        	return authorBean;
	        }
	        else {
	    return null;
	    }
	    }
	catch (SQLException e) {
	    e.printStackTrace();
	}
		return null;
	}
}
