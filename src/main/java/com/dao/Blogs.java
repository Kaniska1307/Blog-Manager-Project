package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import com.beans.*;
import com.dao.DBConnection;

/**
 * This class is used to retrieve data about blogs from the Database by the
 * procedure call
 * 
 *
 * @author Abinaya R
 * @since 20-12-2022 - second draft
 * @version 1.1
 */

public class Blogs {

	// This method add a new record to the blog table

	public String addBlog(String blogName) {
		Blogs object = new Blogs();
		BlogView viewObj = new BlogView();
		viewObj.setBlogId(null);
		viewObj.setBlogIndex(null);
		viewObj.setBlogName(null);
		viewObj.setPageNo(1);
		viewObj.setStatus(null);
		ArrayList<BlogList> blogList = object.getBlogList();
		String error = null;
		if (!(blogList.isEmpty())) {
			for (int i = 0; i < blogList.size(); i++) {
				BlogList blogObj = blogList.get(i);
				System.out.println(blogName + " name = " + blogObj.getBlogName());
				if ((blogObj.getBlogName()).equalsIgnoreCase(blogName)) {
					System.out.println("blog matched");
					return "notAdded";
				}
			}
		}
		DBConnection DBobject = new DBConnection();
		Connection connection = null;
		CallableStatement stmt = null;
		try {
			connection = DBobject.connect();
			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.ADD_BLOGS_PRC(?, ?, ?)");
			stmt.setString(1, blogName);
			System.out.println("blog setted");
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.execute();
			int errorCode = ((OracleCallableStatement) stmt).getInt(2);

			error = ((OracleCallableStatement) stmt).getString(3);
			System.out.println("error = " + errorCode + "   " + error);
			stmt.close();
			connection.close();
		}catch (Exception e) {
			System.out.println("update done wrong " + e.getMessage());
		}
		return error;
	}

	// This method retrieves the list of all blogs in the database

	public ArrayList<BlogList> getBlogList() {
		DBConnection object = new DBConnection();
		Connection connection = null;
		ArrayList<BlogList> blogList = new ArrayList<BlogList>();
		CallableStatement stmt = null;
		try 
		{
			connection = object.connect();
			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_BLOG_LIST_PRC(?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);

			while (rs.next()) {
				BlogList blogObj = new BlogList();
				blogObj.setBlogId(rs.getInt(1));
				blogObj.setBlogName(rs.getString(2));
				blogList.add(blogObj);
			}

			stmt.close();
			connection.close();
		}catch (Exception e) {
			System.out.println("update done wrong " + e.getMessage());
		}
		return blogList;
	}

	// This method retrieves the List of Authors in the database

	public ArrayList<BlogAuthors> getAuthorList() 
	{
		DBConnection object = new DBConnection();
		Connection connection = null;
		ArrayList<BlogAuthors> authorList = new ArrayList<BlogAuthors>();
		CallableStatement stmt = null;
		try {
			connection = object.connect();
			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_AUTHOR_NMAE_DETAILS_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();

			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogAuthors author = new BlogAuthors();
				author.setAuthorId(rs.getInt(1) + "");
				author.setAuthorName(rs.getString(2));
				authorList.add(author);
			}
			stmt.close();
			connection.close();
		}catch (Exception e) {
			System.out.println("update done wrong " + e.getMessage());
		}
		return authorList;
	}

	// This method retrieves the records of the blogs table based on the
	// BlogView bean.

	public ArrayList<Blog> viewTable(BlogView viewObj) {
		DBConnection object = new DBConnection();
		Connection connection = null;
		ArrayList<Blog> blogList = new ArrayList<Blog>(5);
		CallableStatement stmt = null;
		try {
			connection = object.connect();

			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOGS_PRC(?,?,?,?,?,?,?,?,?)");
			System.out.println("Name got from the controller " + viewObj.getBlogName());
			stmt.setInt(1, viewObj.getPageNo());
			stmt.setString(2, viewObj.getBlogIndex());
			stmt.setString(3, viewObj.getBlogName());
			stmt.setString(4, viewObj.getStatus());
			stmt.setString(5, viewObj.getBlogId());
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.NUMBER);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();

			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(7);
			while (rs.next()) {
				Blog blogObj = new Blog();
				blogObj.setBlogId(Integer.parseInt(rs.getString(1)));
				blogObj.setBlogName(rs.getString("BLOG_NAME"));
				blogObj.setStatus(rs.getString("STATUS"));
				blogObj.setCreatedBy(rs.getString("CREATED_BY"));
				blogObj.setCreatedDate(rs.getString("CREATED_DATE"));
				blogObj.setUpdatedBy(rs.getString("UPDATED_BY"));
				blogObj.setUpdatedDate(rs.getString("UPDATED_DATE"));
				blogList.add(blogObj);
			}
			return blogList;
		}catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {

				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}

		}
		return blogList;
	}

	// This method updates a particular Blog record based on the BlogUpdate
	// bean.

	public String updateBlog(BlogUpdate updateObj) {
		DBConnection object = new DBConnection();
		Connection connection = null;
		String error = null;
		CallableStatement stmt = null;
		try {
			connection = object.connect();
			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.UPDATE_BLOGS_PRC(?,?,?,?,?)");
			stmt.setString(1, updateObj.getBlogId());
			// stmt.setLong(2, Long.parseLong(updateObj.getBlogId()));
			stmt.setString(2, updateObj.getBlogName());
			stmt.setString(3, updateObj.getStatus());
			System.out.println("status got = " + updateObj.getStatus());
			stmt.registerOutParameter(4, OracleTypes.NUMBER);
			stmt.registerOutParameter(5, OracleTypes.VARCHAR);
			stmt.execute();
			int errorCode = ((OracleCallableStatement) stmt).getInt(4);

			error = ((OracleCallableStatement) stmt).getString(5);
			stmt.close();
			connection.close();
			if (errorCode == 0)
				return "Success";
		}catch (Exception e) {
			System.out.println("update done wrong " + e.getMessage());
		}
		return error;
	}

	// This method retrieves the record of a particular blog

	public List<String> updateAutofill(String blogName) {
		DBConnection object = new DBConnection();
		Connection connection = null;
		List<String> dataList = new ArrayList<String>();
		CallableStatement stmt = null;
		try {
			connection = object.connect();

			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOGS_PRC(?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, 1);
			stmt.setString(2, null);
			stmt.setString(3, blogName);
			stmt.setString(4, null);
			stmt.setString(5, null);
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.NUMBER);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();

			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(7);
			while (rs.next()) {
				dataList.add((rs.getString(1)));
				dataList.add(rs.getString("BLOG_NAME"));
				dataList.add(rs.getString("STATUS"));

			}
			return dataList;

		}catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {

				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}

		}
		return dataList;
	}

	// This method gets the index of all blogs in the table

	public ArrayList<Character> getBlogIndex(BlogView viewObj) {
		DBConnection object = new DBConnection();
		Connection connection = null;
		ArrayList<Character> indexList = new ArrayList<Character>(5);
		CallableStatement stmt = null;
		try {
			connection = object.connect();

			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOGS_PRC(?,?,?,?,?,?,?,?,?)");

			stmt.setInt(1, viewObj.getPageNo());
			stmt.setString(2, viewObj.getBlogIndex());
			stmt.setString(3, viewObj.getBlogName());
			stmt.setString(4, viewObj.getStatus());
			stmt.setString(5, null);
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.NUMBER);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();

			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(6);
			while (rs.next()) {
				System.out.println("index  = " + rs.getString(1));
				indexList.add(rs.getString(1).charAt(0));
			}
			return indexList;
		}catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {

				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}

		}
		return indexList;
	}

	// This method gets the list of all the blog names in the table

	public ArrayList<String> getBlogNameList() {
		DBConnection object = new DBConnection();
		Connection connection = null;
		ArrayList<String> blogList = new ArrayList<String>();
		CallableStatement stmt = null;
		try {
			connection = object.connect();
			stmt = connection.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_BLOG_LIST_PRC(?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);

			while (rs.next()) {
				blogList.add(rs.getString(2));
			}

			stmt.close();
			connection.close();
		}catch (Exception e) {
			System.out.println("update done wrong " + e.getMessage());
		}
		return blogList;
	}
}
