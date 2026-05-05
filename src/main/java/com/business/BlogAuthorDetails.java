package com.business;

import java.util.ArrayList;
import java.util.List;

import com.dao.BlogAuthorDAO;
import com.google.gson.Gson;

public class BlogAuthorDetails {
	
	public String getJSONBlogNames() {
	List<String> blogAuthor = new ArrayList<String>();
	
	Gson gson = new Gson();
	BlogAuthorDAO daoObj = new BlogAuthorDAO();
	try {
	blogAuthor = daoObj.getBlogAuthorList();
	String JSONBlog = gson.toJson(blogAuthor);
	return JSONBlog;
	} catch (Exception e) {
	e.printStackTrace();
	}
	return null;
	}


}
