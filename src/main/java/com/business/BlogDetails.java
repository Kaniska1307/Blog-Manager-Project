package com.business;

import java.util.ArrayList;

import java.util.List;

import com.beans.BlogList;
import com.dao.Blogs;
import com.google.gson.Gson;

public class BlogDetails {
	private Blogs blogObject = new Blogs();

	// This method is used to get the blog details as a string

	public String getJSONBlog(String blogName) {
		List<String> blogDetail = new ArrayList<String>();
		Gson gson = new Gson();

		try {
			blogDetail = blogObject.updateAutofill(blogName);
			String JSONBlog = gson.toJson(blogDetail);
			return JSONBlog;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// This method is used to get all the blog names as a string

	public String getJSONBlogNames() {
		List<String> blogNames = new ArrayList<String>();
		Gson gson = new Gson();

		try {
			blogNames = blogObject.getBlogNameList();
			String JSONBlog = gson.toJson(blogNames);
			return JSONBlog;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	//This method will get the count of blogs based on the index
	public int getIndexCount(ArrayList<BlogList> blog, String blogIndex)
    {
        int total=0;
        for (int i = 0; i < blog.size(); i++) {
            String name = blog.get(i).getBlogName();
            if (name.startsWith(blogIndex))
                total++;
        }
        return total;
    }
}
