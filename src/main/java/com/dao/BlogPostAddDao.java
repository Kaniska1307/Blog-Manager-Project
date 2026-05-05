package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.beans.BlogPostBean;

import oracle.jdbc.driver.OracleTypes;

/**
 *@ class BlogPostAddDao
 *@ description To get Authordetails for dropdown.
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/
public class BlogPostAddDao {

	/**
	 * @param bab
	 * @return String(errorStatus)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String addIntoDB(BlogPostBean bpb) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		String error="Error in DB";
		String status="LIVE";
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call  HOT_ACADEMY.BLOG_POST_MODULE_PKG.ADD_UPDATE_BLOG_POST_PRC  (?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, bpb.getPostId());
			stmt.setString(2, bpb.getBlogId());
			stmt.setString(3, bpb.getAuthorId());
			stmt.setString(4, bpb.getHeadlines());
			stmt.setString(5, bpb.getPostText());
			stmt.setString(6, bpb.getPostURL());
			stmt.setString(7, bpb.getTag());
			stmt.setString(8, bpb.getStartDate());
			stmt.setString(9, status);
			stmt.registerOutParameter(10, OracleTypes.NUMBER);
			stmt.registerOutParameter(11, OracleTypes.VARCHAR);
			stmt.execute();
			error = stmt.getString(11);
		} catch (Exception e) {
			System.out.println(e + "IN add into db time  :" + java.time.LocalDate.now());
			return error;
		}

		return error;
	}

}
