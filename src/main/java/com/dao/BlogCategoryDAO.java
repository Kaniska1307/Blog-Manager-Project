package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.beans.BlogCategoryBean;
import com.beans.ErrorBean;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class BlogCategoryDAO {

	/**
	   *@ method blogCategory-add
	   *@ description This method adds data into database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	public ErrorBean addIntoDB(String categoryName) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ErrorBean errorObj = new ErrorBean();
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.ADD_BLOG_CATEGORY_PRC (?,?,?)");
			stmt.setString(1, categoryName);
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.execute();
			errorObj.setErrorMessage(stmt.getString(3));
			errorObj.setErrorCode(stmt.getInt(2));
		} catch (Exception e) {
			System.out.println(e + " category IN add into db time  :" + java.time.LocalDate.now());
			return errorObj;
		}

		return errorObj;
	}

	/**
	   *@ method blogCategory-edit
	   *@ description This method retrieve data from database for specific Category ID
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 30-10-2022
	   **/
	public BlogCategoryBean getCategoryFromDB(int categoryId) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		BlogCategoryBean beanObj = new BlogCategoryBean();
		try (Connection con = conobj.connect()) {
			try (CallableStatement stmt = con
					.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_CATEGORY_DETAILS_PRC (?,?)")) {
				stmt.setLong(1, categoryId);
				stmt.registerOutParameter(2, OracleTypes.CURSOR);
				stmt.execute();
				try (ResultSet rs = ((OracleCallableStatement) stmt).getCursor(2);) {
					while (rs.next()) {
						beanObj.setCategoryId(rs.getString(1));
						beanObj.setCategoryName(rs.getString(2));
						beanObj.setCategoryStatus(rs.getString(3));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e + "IN edit for category into db time  :" + java.time.LocalDate.now());
			return beanObj;
		}

		return beanObj;
	}

	/**
	   *@ method blogCategory-Edit
	   *@ description This method retrieve Category name as a list for dropdown from database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	public ArrayList<BlogCategoryBean> getCategoryName() throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogCategoryBean> al = new ArrayList<BlogCategoryBean>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_CATEGORY_NAME_LIST_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogCategoryBean beanObj = new BlogCategoryBean();
				Integer idNo = rs.getInt(1);
				beanObj.setCategoryId(idNo.toString());
				beanObj.setCategoryName(rs.getString(2));
				al.add(beanObj);
			}
			stmt.close();
			con.close();
		}catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				// finally block used to close resources
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e + " here in Blog categoryDao arraylist catch");
			}

		}
		return al;
	}
	/**
	   *@ method blogCategory-update
	   *@ description This method update data to the database for specific Category ID
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 31-10-2022
	   **/
	public ErrorBean updateCategoryOnDB(BlogCategoryBean beanObj) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ErrorBean errorObj = new ErrorBean();
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.UPDATE_BLOG_CATEGORY_PRC (?,?,?,?,?)");
			System.out.println();
			stmt.setLong(1, Long.parseLong(beanObj.getCategoryId()));
			stmt.setString(2, beanObj.getCategoryName());
			stmt.setString(3, beanObj.getCategoryStatus());
			stmt.registerOutParameter(4, OracleTypes.NUMBER);
			stmt.registerOutParameter(5, OracleTypes.VARCHAR);
			stmt.execute();
			// Setting Error Code and Number
			errorObj.setErrorCode(stmt.getInt(4));
			errorObj.setErrorMessage(stmt.getString(5));
		} catch (Exception e) {
			System.out.println(e + "IN UPDATE into db time  :" + java.time.LocalDate.now());
			return errorObj;
		}
		return errorObj;
	}

	/**
	   *@ method blogCategory-view
	   *@ description This method retrieve data from database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	public ArrayList<BlogCategoryBean> searchFromDB(BlogCategoryBean beanObj)
			throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogCategoryBean> al = new ArrayList<>();
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOG_CATEGORIES_PRC (?,?,?,?,?,?,?,?)");
			stmt.setString(1, beanObj.getCategoryName());
			stmt.setString(2, beanObj.getCategoryIndex());
			stmt.setString(3, beanObj.getCategoryId());
			stmt.setString(4, beanObj.getCategoryPage());
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.NUMBER);
			stmt.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt.execute();
			beanObj.setCategoryErrorCode(stmt.getInt(7));
			ResultSet rsCatDetails = ((OracleCallableStatement) stmt).getCursor(6);
			while (rsCatDetails.next()) {
				BlogCategoryBean beanObjForAl = new BlogCategoryBean();
				beanObjForAl.setCategoryId(rsCatDetails.getString(1));
				beanObjForAl.setCategoryName(rsCatDetails.getString(2));
				beanObjForAl.setCategoryStatus(rsCatDetails.getString(3));
				beanObjForAl.setCategoryCreatedDate(rsCatDetails.getString(4));
				al.add(beanObjForAl);
			}
		} catch (Exception e) {
			System.out.println(e + " inside view category field");
		}
		return al;
	}
	
	/**
	   *@ method blogCategory-view
	   *@ description This method retrieve index as a list from database 
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 22-12-2022
	   **/
	public ArrayList<Character> getIndexList(BlogCategoryBean beanObj){
		DBConnection conobj = new DBConnection();
		ArrayList<Character> al = new ArrayList<>();
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOG_CATEGORIES_PRC (?,?,?,?,?,?,?,?)");
			stmt.setString(1, beanObj.getCategoryName());
			stmt.setString(2, beanObj.getCategoryIndex());
			stmt.setString(3, beanObj.getCategoryId());
			stmt.setString(4, beanObj.getCategoryPage());
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.NUMBER);
			stmt.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt.execute();
			beanObj.setCategoryErrorCode(stmt.getInt(7));
			ResultSet rsCatDetails = ((OracleCallableStatement) stmt).getCursor(5);
			while (rsCatDetails.next()) {
				al.add(rsCatDetails.getString(1).charAt(0));
				}
			}catch (Exception e) {
				System.out.println(e + " inside view category field");
			}
		return al;
		}
}
