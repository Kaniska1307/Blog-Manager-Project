package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.beans.BlogPostNameBean;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class BlogPostNameDropDownDao {

	/**
	 * @param bab
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<BlogPostNameBean> getPostDetails(String blogId) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogPostNameBean> al = new ArrayList<BlogPostNameBean>();
		Connection con = conobj.connect();
		String postName=null;
		System.out.println("Inside Get blog Post details");
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.POST_LIST_PRC (?,?,?)");
			stmt.setString(1, postName);
			stmt.setString(2, blogId);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(3);
			while (rs.next()) {
				System.out.println("resultset");
				BlogPostNameBean obj=new BlogPostNameBean();
				System.out.println("POST ID : " + rs.getString(1));
				obj.setPostId(rs.getString(1));		//PostId	
				System.out.println("POST NAME : " + rs.getString(2));
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
