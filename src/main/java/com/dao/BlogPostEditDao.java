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
 *@ class BlogPostEditDao
 *@ description To get Details of particular blog and post to edit.
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/
public class BlogPostEditDao {

	/**
	 * @param blogId,postId
	 * @return ArrayList<String> (Details of particular blog and post)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> getEditSubmitDetails(String blogId,String postId) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<String> al = new ArrayList<>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_POST_DETAILS_PRC (?,?,?)");
			stmt.setString(1, postId);
			stmt.setString(2, blogId);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(3);
			while (rs.next()) {
				al.add(rs.getString(1)); 		//AuthorId
				al.add(rs.getString(2));		//BlogId
				al.add(rs.getString(3));		//PostId
				al.add(rs.getString(4));		//BlogName
				al.add(rs.getString(5));		//AuthorName
				al.add(rs.getString(6)); 		//PostName
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
