package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.beans.BlogAuthorNameBean;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

/**
 * @ class BlogAuthorNameDropDownDao @ description To get Authordetails for
 * dropdown. @ version 1.0 @ author HARISH KUMAAR S @ since 23/12/2022
 **/
public class BlogAuthorNameDropDownDao {

	/**
	 * @param nill
	 * @return ArrayList<BlogAuthorNameBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public ArrayList<BlogAuthorNameBean> getAuthorNameDetails() throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogAuthorNameBean> al = new ArrayList<BlogAuthorNameBean>();
		Connection con = conobj.connect();
		CallableStatement stmt = null;
		try {
			stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_AUTHOR_NMAE_DETAILS_PRC (?)");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
			while (rs.next()) {
				BlogAuthorNameBean obj = new BlogAuthorNameBean();
				Integer idNo = rs.getInt(1);
				obj.setAuthorId(idNo.toString());
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				obj.setAuthorName(firstName + " " + lastName);
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
