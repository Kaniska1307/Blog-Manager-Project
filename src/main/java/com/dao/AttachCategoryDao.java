package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.BlogPostNameBean;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

/**
 *@ class AttachCategoryController
 *@ description To perform dao actions(insert/remove/view) in DB related to Attach-Category-post module
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/
public class AttachCategoryDao {
	
	/**
	 * @param flag(INSERT/DELETE),categoryId,postId
	 * @return ArrayList<BlogPostNameBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public ArrayList<BlogPostNameBean> attachCategoryProcess(String flag, String categoryId, String postId)
			throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		String status = "";
		ArrayList<BlogPostNameBean> al = new ArrayList<>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.INS_DEL_POST_CATEGORY_PRC  (?,?,?,?,?,?)");
			stmt.setString(1, flag);
			stmt.setString(2, categoryId);
			stmt.setString(3, postId);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);
			stmt.registerOutParameter(5, OracleTypes.NUMBER);
			stmt.registerOutParameter(6, OracleTypes.VARCHAR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(4);
			status = stmt.getString(6);

			if (rs != null) {
				while (rs.next()) {
					BlogPostNameBean obj = new BlogPostNameBean();
					obj.setPostId(rs.getString(1));
					obj.setHeadlines(rs.getString(3));
					obj.setErrorStatus(status);
					al.add(obj);
				}
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
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
			}

		}

		return al;
	}

}

