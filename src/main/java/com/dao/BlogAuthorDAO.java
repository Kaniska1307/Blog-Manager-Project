package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.BlogAuthorBean;
import com.beans.ErrorBean;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class BlogAuthorDAO {

	
	
	
	
	
	 /**
	   *@ method blogAuthors-add
	   *@ description This method adds data into database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	public ErrorBean addIntoDB(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ErrorBean errorObj = new ErrorBean();
			try(Connection con = conobj.connect()){
				CallableStatement stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.ADD_AUTHORS_PRC (?,?,?,?,?,?,?,?)") ;
				stmt.setString(1, beanObj.getAuthorFirstName());
				stmt.setString(2, beanObj.getAuthorLastName());
				stmt.setString(3, beanObj.getAuthorQualification());
				stmt.setString(4, beanObj.getAuthorEmail());
				stmt.setString(5, beanObj.getAuthorHomeTown());
				stmt.setString(6, beanObj.getAuthorPhoneNo());
				stmt.registerOutParameter(7, OracleTypes.NUMBER);
				stmt.registerOutParameter(8, OracleTypes.VARCHAR);
				stmt.execute();
				errorObj.setErrorCode(stmt.getInt(7));
				errorObj.setErrorMessage(stmt.getString(8));
				
			}catch (Exception e) {
				System.out.println(e+"IN add into db time  :"+java.time.LocalDate.now());
				return errorObj;
			}
		
		return errorObj;
	}
	
	
	
	/**
	   *@ method blogAuthors-Edit
	   *@ description This method retrieve author name as a list for dropdown from database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	public ArrayList<BlogAuthorBean> getAuthorNameDetails() throws ClassNotFoundException, SQLException {
        DBConnection conobj = new DBConnection();
        ArrayList<BlogAuthorBean> al = new ArrayList<BlogAuthorBean>();
        Connection con = conobj.connect();
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_AUTHOR_NMAE_DETAILS_PRC (?)");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
            while (rs.next()) {
                BlogAuthorBean obj=new BlogAuthorBean();
                Integer idNo=rs.getInt(1);
                obj.setAuthorId(idNo.toString());
                obj.setAuthorFullName(rs.getString(2)+" "+rs.getString(3));
                obj.setAuthorFirstName(rs.getString(2));
                obj.setAuthorLastName(rs.getString(3));
                obj.setAuthorQualification(rs.getString(4));
                obj.setAuthorEmail(rs.getString(5));
                obj.setAuthorHomeTown(rs.getString(6));
                obj.setAuthorHomeTown(rs.getString(7));
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
	
	/**
	   *@ method blogAuthors-edit
	   *@ description This method retrieve data from database for specific author ID
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 30-10-2022
	   **/
	public BlogAuthorBean getAuthorFromDB(int authorId) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		BlogAuthorBean authorBeanObj = new BlogAuthorBean();
			try(Connection con = conobj.connect()){
				try(CallableStatement stmt = con.prepareCall("Call\r\n" + 
						"    HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_AUTHOR_DETAILS_PRC (?,?)")){
				stmt.setLong(1, authorId);
				stmt.registerOutParameter(2, OracleTypes.CURSOR);
				stmt.execute();
				try(ResultSet rs = ((OracleCallableStatement) stmt).getCursor(2);){
					while(rs.next()) {
						authorBeanObj.setAuthorId(rs.getString(1));
						authorBeanObj.setAuthorFirstName(rs.getString(2));
						authorBeanObj.setAuthorLastName(rs.getString(3));
						authorBeanObj.setAuthorQualification(rs.getString(4));
						authorBeanObj.setAuthorEmail(rs.getString(5));
						authorBeanObj.setAuthorHomeTown(rs.getString(6));
						authorBeanObj.setAuthorPhoneNo(rs.getString(7));
					}
				}
				}
			}catch (Exception e) {
				System.out.println(e+"IN eDIT into db time  :"+java.time.LocalDate.now());
				return authorBeanObj;
			}
		
		return authorBeanObj;
	}
	
	/**
	   *@ method blogAuthors-update
	   *@ description This method update data to the database for specific author ID
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 31-10-2022
	   **/
	public ErrorBean updateAuthorOnDB(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ErrorBean errorObj = new ErrorBean();
			try(Connection con = conobj.connect()){
				CallableStatement stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.UPDATE_AUTHORS_PRC (?,?,?,?,?,?,?,?,?)") ;
				stmt.setLong(1, Long.parseLong(beanObj.getAuthorId()));
				stmt.setString(2, beanObj.getAuthorFirstName());
				stmt.setString(3, beanObj.getAuthorLastName());
				stmt.setString(4, beanObj.getAuthorQualification());
				stmt.setString(5, beanObj.getAuthorEmail());
				stmt.setString(6, beanObj.getAuthorHomeTown());
				stmt.setString(7, beanObj.getAuthorPhoneNo());
				stmt.registerOutParameter(8, OracleTypes.NUMBER);
				stmt.registerOutParameter(9, OracleTypes.VARCHAR);
				stmt.execute();
				errorObj.setErrorMessage(stmt.getString(9));
				errorObj.setErrorCode(stmt.getInt(8));
			}catch (Exception e) {
				System.out.println(e+"IN UPDATE into db time  :"+java.time.LocalDate.now());
				return errorObj;
			}
		
		return errorObj;
	}
	
	/**
	   *@ method blogAuthors-view
	   *@ description This method retrieve index as a list from database 
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 22-12-2022
	   **/
	public ArrayList<Character> getIndexList(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<Character> al = new ArrayList<>();
		try(Connection con = conobj.connect()){
			CallableStatement stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_AUTHORS_PRC (?,?,?,?,?,?,?,?,?)") ;
			stmt.setString(1, beanObj.getAuthorFirstName());
			stmt.setString(2, beanObj.getAuthorEmail());
			stmt.setString(3, beanObj.getAuthorQualification());
			stmt.setString(4, beanObj.getAuthorIndex());
			stmt.setString(5, "1");
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.NUMBER);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();
			ResultSet rsAuthorIndex = ((OracleCallableStatement)stmt).getCursor(7);
			while(rsAuthorIndex.next())
            {
				al.add(rsAuthorIndex.getString(1).charAt(0));
			}
		}
		return al;
	}
	
	/**
	   *@ method blogAuthors-view
	   *@ description This method retrieve data from database
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	public ArrayList<BlogAuthorBean> searchFromDB(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		DBConnection conobj = new DBConnection();
		ArrayList<BlogAuthorBean> al = new ArrayList<>();
		try(Connection con = conobj.connect()){
			CallableStatement stmt = con.prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.VIEW_AUTHORS_PRC (?,?,?,?,?,?,?,?,?)") ;
			stmt.setString(1, beanObj.getAuthorFirstName());
			stmt.setString(2, beanObj.getAuthorEmail());
			stmt.setString(3, beanObj.getAuthorQualification());
			stmt.setString(4, beanObj.getAuthorIndex());
			stmt.setString(5, beanObj.getAuthorPage());
			stmt.registerOutParameter(6, OracleTypes.CURSOR);
			stmt.registerOutParameter(7, OracleTypes.CURSOR);
			stmt.registerOutParameter(8, OracleTypes.NUMBER);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();
			ResultSet rsAuthorDetails = ((OracleCallableStatement)stmt).getCursor(6);
			while(rsAuthorDetails.next())
          {
				BlogAuthorBean beanObjForAl = new BlogAuthorBean();
				beanObjForAl.setAuthorId(rsAuthorDetails.getString(1));
				beanObjForAl.setAuthorFirstName(rsAuthorDetails.getString(2));
				beanObjForAl.setAuthorLastName(rsAuthorDetails.getString(3));
				beanObjForAl.setAuthorQualification(rsAuthorDetails.getString(4));
				beanObjForAl.setAuthorEmail(rsAuthorDetails.getString(5));
				beanObjForAl.setAuthorHomeTown(rsAuthorDetails.getString(6));
				al.add(beanObjForAl);
			}
		}
		return al;
	}
	
	
	public ArrayList<String> getBlogAuthorList() {
        DBConnection object = new DBConnection();
        Connection connection = null;
        ArrayList<String> blogAuthorList = new ArrayList<String>();
        CallableStatement stmt = null;
        try {
            connection = object.connect();
            stmt = connection
                    .prepareCall("Call HOT_ACADEMY.BLOG_POST_MODULE_PKG.GET_AUTHOR_NMAE_DETAILS_PRC (?)");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = ((OracleCallableStatement) stmt).getCursor(1);
            while (rs.next()) {
                blogAuthorList.add(rs.getString(2));
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(" sql error" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("update done wrong " + e.getMessage());
        }
        return blogAuthorList;
    }
}
