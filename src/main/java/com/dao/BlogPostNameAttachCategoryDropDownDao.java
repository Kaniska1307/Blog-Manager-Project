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
 *@ class BlogPostNameAttachCategoryDropDownDao
 *@ description To get post for dropdown for particular category to attach to edit.
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/
public class BlogPostNameAttachCategoryDropDownDao {
	
	/**
	 * @param nill
	 * @return ArrayList<BlogPostNameBean> 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<BlogPostNameBean> getPostDetails() throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogPostNameBean> al = new ArrayList<BlogPostNameBean>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call  HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_POST_LIST_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogPostNameBean obj=new BlogPostNameBean();
				obj.setPostId(rs.getString(1));		//PostId	
				obj.setHeadlines(rs.getString(2));	//PostName
				al.add(obj);
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
			}

		}
		return al;
	}

}
