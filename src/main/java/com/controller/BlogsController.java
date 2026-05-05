package com.controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;

import com.business.*;
import com.business.impl.*;
import com.beans.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Blog;
import com.business.BlogDetails;
import com.dao.BlogPostViewDao;
import com.dao.Blogs;
import com.validation.*;

@Controller
public class BlogsController {

	CommonBusinessImpl businessObj = new CommonBusinessImpl();

	// This method retrieves the records of the blogs table based on the page
	// number and blog index.

	/**
	 * @ method viewBlogs 
	 * @ description This method retrieves the blog records
	 * data 
	 * @ version 2.0 
	 * @ author Abinaya R 
	 * @ since 22/12/2022
	 **/

	@RequestMapping(value = "blog-view-table", method = RequestMethod.GET)
	public ModelAndView viewBlogs(HttpServletRequest request) {
		int pageNo = 1;
		String blogIndex = null;
		int total = 0;
		ModelAndView mav = new ModelAndView("blogs");
		HttpSession session = request.getSession();
		BlogDetails utilityObj = new BlogDetails();
		if (request.getParameter("page-no") != null) {
			pageNo = Integer.parseInt(request.getParameter("page-no"));
		}

		if (request.getParameter("blog-index") != null) {
			blogIndex = request.getParameter("blog-index");

			mav.addObject("blogListFiltered", true);
		}
		Blogs object = new Blogs();
		BlogView viewObj = new BlogView();
		viewObj.setBlogId(null);
		viewObj.setBlogIndex(blogIndex);
		viewObj.setBlogName(null);
		viewObj.setPageNo(pageNo);
		viewObj.setStatus(null);
		ArrayList<BlogList> blog = object.getBlogList();
		ArrayList<Blog> blogList = object.viewTable(viewObj);
		ArrayList<Character> indexList = object.getBlogIndex(viewObj);
		// counts the number of blogs with same blog index if blog index value is not
		// null
		if (request.getParameter("blog-index") != null) {
            total = utilityObj.getIndexCount(blog, blogIndex);
            mav.addObject("Total", (total - 1) / 10 + 1);
        } else
            mav.addObject("Total", (blog.size() - 1) / 10 + 1);
		mav.addObject("paginationURL", request.getRequestURI());
		mav.addObject("Submit", true);
		mav.addObject("blogList", blogList);
		session.setAttribute("blogList", blogList);
		mav.addObject("indexList", indexList);
		mav.addObject("blogIndex", blogIndex);
		mav.addObject("pageNo", pageNo);
		// all the objects are added to the model and view object and object is returned
		return mav;
	}

	/**
	 * @ method viewBlogFilter 
	 * @ description This method retrieves the blog records
	 * data based on the filter applied 
	 * @ version 2.0 
	 * @ author Abinaya R 
	 * @ since 20/12/2022
	 **/

	@RequestMapping(value = "blog-view-filter", method = RequestMethod.GET)
	public ModelAndView viewBlogFilter(HttpServletRequest request) throws NullPointerException {
		String blogName = request.getParameter("blogName");
		String status = request.getParameter("status");
		int pageNo = 1;
		if (request.getParameter("page-no") != null) {
			pageNo = Integer.parseInt(request.getParameter("page-no"));
		}
		ModelAndView mav = new ModelAndView("blogs");
		Blogs object = new Blogs();
		BlogView viewObj = new BlogView();
		viewObj.setBlogId(null);
		viewObj.setBlogIndex(null);
		viewObj.setBlogName(blogName);
		viewObj.setPageNo(pageNo);
		viewObj.setStatus(status);
		ArrayList<Blog> blogListFilter = object.viewTable(viewObj);
		if (status.equals("deleted")) {
			mav.addObject("statusDelete", true);
		} else {
			mav.addObject("statusDelete", false);
			if (!(blogListFilter.isEmpty())) {
				mav.addObject("blogList", blogListFilter);

				mav.addObject("Total", (blogListFilter.size()) / 10 + 1);

				mav.addObject("noBlog", false);
			} else
				mav.addObject("noBlog", true);
		}
		return mav;
	}

	/**
	 * @ method addBlogs 
	 * @ description This method adds a record to the blog table 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since 03/11/2022
	 **/

	@RequestMapping(value = "blog-add", method = RequestMethod.GET)
	public ModelAndView addBlogs(HttpServletRequest request) throws NullPointerException {
		ModelAndView mav = new ModelAndView("blogsAdd");

		// businessObj

		String blogName = request.getParameter("blogName");
		Validation validation = new Validation();
		if (validation.textValidation(blogName)) {
			mav.addObject("GreaterText", false);
			Blogs object = new Blogs();
			String result = object.addBlog(blogName);
			if (result.equals("notAdded"))
				mav.addObject("Added", false);
			else
				mav.addObject("Added", true);
		} else {
			mav.addObject("GreaterText", true);
		}

		return mav;
	}

	/**
	 * @ method callBlogEdit 
	 * @ description This method gets the list of blog Id and blog name 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since 03/11/2022
	 **/

	@RequestMapping(value = "blog-edit", method = RequestMethod.GET)
	public ModelAndView callBlogEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("blogsEdit");
		Blogs object = new Blogs();
		ArrayList<BlogList> blogList = object.getBlogList();
		mav.addObject("blogList", blogList);
		return mav;
	}

	/**
	 * @ method callBlogUpdate 
	 * @ description This method updates the records in the blog table 
	 * @ version 2.0 
	 * @ author Abinaya R 
	 * @ since 20/12/2022
	 **/

	@RequestMapping(value = "blog-update", method = RequestMethod.GET)
	public ModelAndView callBlogUpdate(HttpServletRequest request) throws NullPointerException {
		ModelAndView mav = new ModelAndView("blogsEdit");
		String blogName = request.getParameter("blogNameOne");
		String status = request.getParameter("status");
		String blogId = request.getParameter("blogId");
		Validation validation = new Validation();
		if (validation.textValidation(blogName)) {
			System.out.println("update blog valid");
			mav.addObject("GreaterText", false);
			BlogUpdate updateObj = new BlogUpdate();
			updateObj.setBlogId(blogId);
			updateObj.setBlogName(blogName);
			updateObj.setStatus(status);
			Blogs object = new Blogs();
			String result = object.updateBlog(updateObj);
			if (result == "Success")
				mav.addObject("result", true);
			else
				mav.addObject("result", false);
		} else {
			System.out.println("update blog invalid");
			mav.addObject("GreaterText", true);
		}

		return mav;
	}

	/**
	 * @ method editUpdateAutocomplete 
	 * @ description This method process the blog
	 * name and gives blog details 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since
	 * 15/12/2022
	 **/

	@RequestMapping(value = "blog-edit-update", method = RequestMethod.GET)
	@ResponseBody
	public String editUpdateAutocomplete(String blogName) throws NullPointerException {

		BlogDetails detailsObj = new BlogDetails();
		String JSONBlog = detailsObj.getJSONBlog(blogName);
		return JSONBlog;
	}

	/**
	 * @ method callBlogEditIndiv 
	 * @ description This method sets the blog name and
	 * blog Id to the Model and view object based on the blog bean 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since 19/12/2022
	 **/

	@RequestMapping(value = "blog-edit-indiv", method = RequestMethod.GET)
	public ModelAndView callBlogEditIndiv(BlogNameBean bean) throws NullPointerException {

		ModelAndView mav = new ModelAndView("blogsEdit");
		mav.addObject("blogSetted", true);
		mav.addObject("blogIdIndiv", bean.getBlogId());
		mav.addObject("blogNameIndiv", bean.getBlogName());
		return mav;
	}

	/**
	 * @ method callBlogPostsView 
	 * @ description This method process the age and give
	 * the result 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since 21/12/2022
	 **/

	@RequestMapping(value = "blog-viewposts-filter", method = RequestMethod.GET)
	public ModelAndView callBlogPostsView(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		System.out.println("Hello I'm in call blog-postview");
		String pageNo = "1";
		String blogId = request.getParameter("blogId");
		String blogName = request.getParameter("blogName");
		ModelAndView mv = new ModelAndView("blogs");
		BlogPostViewDao daoObject = new BlogPostViewDao();
		BlogPostBean postBean = new BlogPostBean();
		postBean.setBlogId(blogId);
		postBean.setBlogName(blogName);
		ArrayList<BlogPostBean> totalDetails = daoObject.viewDetails(postBean, pageNo,null);
		mv.addObject("blogPostList", totalDetails);
		mv.addObject("postDetailsAvailable", true);
		return mv;
	}

	/**
	 * @ method blogNameAutocomplete 
	 * @ description This method gets the string of
	 * all blog names 
	 * @ version 1.0 
	 * @ author Abinaya R 
	 * @ since 14/12/2022
	 **/

	@RequestMapping(value = "blogname-autocomplete", method = RequestMethod.GET)
	@ResponseBody
	public String blogNameAutocomplete() throws NullPointerException {

		BlogDetails detailsObj = new BlogDetails();
		String JSONBlog = detailsObj.getJSONBlogNames();
		return JSONBlog;
	}

}
