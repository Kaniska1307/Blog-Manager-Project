package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.BlogNameBean;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

/**
 * @ class BlogNameDropDownDao @ description To get Blogdetails for dropdown. @
 * version 1.0 @ author HARISH KUMAAR S @ since 23/12/2022
 **/
public class BlogNameDropDownDao {

	/**
	 * @param bab
	 * @return ArrayList<BlogNameBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<BlogNameBean> getBlogDetails() throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogNameBean> al = new ArrayList<BlogNameBean>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_BLOG_LIST_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogNameBean obj = new BlogNameBean();
				obj.setBlogId(rs.getString(1));
				obj.setBlogName(rs.getString(2));
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
