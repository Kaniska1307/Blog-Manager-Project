package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.BlogCategoryNameBean;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

/**
 *@ class BlogCategoryNameDropDownDao
 *@ description To get Categorydetails for dropdown.
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/
public class BlogCategoryNameDropDownDao {

	/**
	 * @param nill
	 * @return ArrayList<BlogCategoryNameBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public ArrayList<BlogCategoryNameBean> getCategoryDetails() throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogCategoryNameBean> al = new ArrayList<BlogCategoryNameBean>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_CATEGORY_NAME_LIST_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogCategoryNameBean obj = new BlogCategoryNameBean();
				obj.setCategoryId(rs.getString(1));
				obj.setCategoryName(rs.getString(2));
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
