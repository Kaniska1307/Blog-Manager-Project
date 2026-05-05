package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.BlogNameBean;
import com.beans.BlogPostBean;
import com.google.gson.Gson;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class BlogPostViewDao {

	/**
	 * @param bab
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public 	ArrayList<BlogPostBean> viewDetails(BlogPostBean bpb,String pgNo, String postIndex) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogPostBean> totalDetails=new ArrayList<BlogPostBean>();
		String error="Error in DB";
		String status="LIVE";
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call   HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOG_POST_PRC  (?,?,?,?,?,?,?,?,?,?)");
	        stmt.setString(1,pgNo);
			stmt.setString(2,postIndex);
			stmt.setString(3, bpb.getBlogName());
			stmt.setString(4, bpb.getHeadlines());
			stmt.setString(5, bpb.getBlogId());
			stmt.setString(6, bpb.getPostId());
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.CURSOR);
			stmt.registerOutParameter(9, OracleTypes.NUMBER);
			stmt.registerOutParameter(10, OracleTypes.VARCHAR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(7);
			while (rs.next()) {
				BlogPostBean indivDetails=new BlogPostBean();
				indivDetails.setAuthorId(rs.getString(1));
				indivDetails.setAuthorName(rs.getString(2));
				indivDetails.setBlogId(rs.getString(3));
				indivDetails.setBlogName(rs.getString(4));
				indivDetails.setPostId(rs.getString(5));
				indivDetails.setHeadlines(rs.getString(6));		
				indivDetails.setPostText(rs.getString(7));		
				indivDetails.setPostURL(rs.getString(8));
				indivDetails.setStatus(rs.getString(9));
				indivDetails.setTag(rs.getString(10));
				indivDetails.setStartDate(rs.getString(11));
				totalDetails.add(indivDetails);
			}
			error = stmt.getString(10);
		} catch (Exception e) {
			System.out.println(e + "IN add into db time  :" + java.time.LocalDate.now());
			return totalDetails;
		}

		return totalDetails;
	}
	
	
	public 	ArrayList<Character> viewIndexDetails(BlogPostBean bpb,String pgNo) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<Character> indexDetails=new ArrayList<Character>();
		String error="Error in DB";
		String status="LIVE";
		String index=null;
		try (Connection con = conobj.connect()) {
			CallableStatement stmt = con
					.prepareCall("Call   HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_BLOG_POST_PRC  (?,?,?,?,?,?,?,?,?,?)");
	        stmt.setString(1,pgNo);
			stmt.setString(2,index);
			stmt.setString(3, bpb.getBlogName());
			stmt.setString(4, bpb.getHeadlines());
			stmt.setString(5, bpb.getBlogId());
			stmt.setString(6, bpb.getPostId());
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.CURSOR);
			stmt.registerOutParameter(9, OracleTypes.NUMBER);
			stmt.registerOutParameter(10, OracleTypes.VARCHAR);
			stmt.execute();
			ResultSet rs = ((OracleCallableStatement) stmt).getCursor(8);
			while (rs.next()) {
				
				indexDetails.add(rs.getString(1).charAt(0));
			}
			error = stmt.getString(10);
		} catch (Exception e) {
			System.out.println(e + "IN add into db time  :" + java.time.LocalDate.now());
			return indexDetails;
		}

		return indexDetails;
	}

	public String getJSONBlogNames() {
		ArrayList<BlogNameBean> blogBean;
        Gson gson = new Gson();
        List<String> blogNames = new ArrayList<String>();
        BlogNameDropDownDao blogObject= new BlogNameDropDownDao();

        try {
            blogBean = blogObject.getBlogDetails();
            for(int i=0; i<blogBean.size(); i++)
            {
            	blogNames.add(blogBean.get(i).getBlogName());
            }
  
            String JSONBlog = gson.toJson(blogNames);
            return JSONBlog;
        } catch (Exception e) {
            e.printStackTrace();
        }

 

        return null;
}
}
