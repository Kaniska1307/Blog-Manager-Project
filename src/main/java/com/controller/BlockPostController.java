package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beans.BlogAuthorNameBean;
import com.beans.BlogNameBean;
import com.beans.BlogPostBean;
import com.beans.BlogPostNameBean;
import com.dao.BlogAuthorNameDropDownDao;
import com.dao.BlogNameDropDownDao;
import com.dao.BlogPostAddDao;
import com.dao.BlogPostEditDao;
import com.dao.BlogPostNameAttachCategoryDropDownDao;
import com.dao.BlogPostNameDropDownDao;
import com.dao.BlogPostViewDao;
import com.google.gson.Gson;
import com.validation.BlogPostValidation;

/**
 * @ class BlogPostController @ description To handle all the request related to
 * blogPost module @ version 1.0 @ author HARISH KUMAAR S @ since 23/12/2022
 **/

@Controller
public class BlockPostController {

	BlogPostValidation check = new BlogPostValidation(); // Object of validation class to perform validation

	/*
	 * @ description: To handle the request to load blogPost-add page
	 */
	@RequestMapping(value = "blog-post-add", method = RequestMethod.GET)
	public ModelAndView callBlogPostAdd() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog-post-add");
		BlogNameDropDownDao blogName = new BlogNameDropDownDao();
		BlogAuthorNameDropDownDao blogAuthorName = new BlogAuthorNameDropDownDao();
		ArrayList<BlogNameBean> blogNameDetails = blogName.getBlogDetails();
		ArrayList<BlogAuthorNameBean> blogAuthorNameDetails = blogAuthorName.getAuthorNameDetails();
		mv.addObject("blogList", blogNameDetails);
		mv.addObject("authorList", blogAuthorNameDetails);
		return mv;
	}

	/*
	 * @ description: To handle the request to submit the details in DB received in
	 * blogPost-add page
	 */
	@RequestMapping(value = "add-post-form", method = RequestMethod.POST)
	public ModelAndView callBlogPostAddSubmit(BlogPostBean postDetails) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		String error = "success";
		error = check.checkDate(postDetails.getStartDate());
		error = check.checkURL(postDetails.getPostURL());
		error = check.countLength(postDetails.getPostText());
		BlogPostAddDao obj = new BlogPostAddDao();
		obj.addIntoDB(postDetails);
		mv.addObject("error", error);
		mv.setViewName("blog-post-add");
		return mv;
	}

	/*
	 * @ description: To handle the request to load blogPost-edit page
	 */
	@RequestMapping(value = "blog-post-edit", method = RequestMethod.GET)
	public ModelAndView callBlogPostEdit() throws ClassNotFoundException, SQLException {
		BlogPostBean editPostDetails = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog-post-edit");
		BlogNameDropDownDao blogName = new BlogNameDropDownDao();
		ArrayList<BlogNameBean> blogNameDetails = blogName.getBlogDetails();
		mv.addObject("blogList", blogNameDetails);
		mv.addObject("blogEditAuto", editPostDetails);
		return mv;
	}

	/*
	 * @ description: To handle the request to load blog-post-add-page
	 */
	@RequestMapping(value = "blog-post-edit-indiv", method = RequestMethod.GET)
	public ModelAndView callBlogPostEditIndiv(BlogPostBean editPostDetails)
			throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog-post-edit");
		mv.addObject("blogEditAuto", editPostDetails);
		return mv;
	}

	/*
	 * @ description: To handle the request to load blog-post-view-page initially
	 */
	@RequestMapping(value = "blog-post-view", method = RequestMethod.GET)
	public ModelAndView callBlogPostView(HttpServletRequest request, BlogPostBean postDetails)
			throws ClassNotFoundException, SQLException {
		String pageNo = "1";
		String postIndex = null;
		if (request.getParameter("page-no") != null) {
			pageNo = request.getParameter("page-no");
		}
		if (request.getParameter("post-index") != null) {
			postIndex = request.getParameter("post-index");
		}
		String error = "success";
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog-post-view");
		BlogPostViewDao daoObj = new BlogPostViewDao();
		BlogPostNameAttachCategoryDropDownDao listObj = new BlogPostNameAttachCategoryDropDownDao();
		ArrayList<BlogPostBean> totalDetails = daoObj.viewDetails(postDetails, pageNo, postIndex);
		ArrayList<Character> indexList = daoObj.viewIndexDetails(postDetails, pageNo);
		if (totalDetails.isEmpty()) {
			error = "no values";
		}
		if(!(postDetails.getPostId() != null) && !(postDetails.getBlogName()!=null) && !(request.getParameter("post-index")!=null))
		{
			ArrayList<BlogPostNameBean> listDetails = listObj.getPostDetails();
			mv.addObject("Total", (listDetails.size() - 1) / 10 + 1);
		}
		else {
			mv.addObject("Total", 1);
		}
		mv.addObject("indexList", indexList);
		mv.addObject("error", error);
		mv.addObject("pageNo", pageNo);
		mv.addObject("blogPostList", totalDetails);
		return mv;
	}

	/*
	 * @ description: To handle the request to submit the updated details in DB
	 * received from load blogPost-edit page
	 */
	@RequestMapping(value = "update-post-form", method = RequestMethod.GET)
	public ModelAndView callBlogPostUpdateSubmit(BlogPostBean postDetails) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("blog-post-edit");
		String error = "success";
		error = check.checkDate(postDetails.getStartDate());
		error = check.checkURL(postDetails.getPostURL());
		error = check.countLength(postDetails.getPostText());
		BlogPostAddDao obj = new BlogPostAddDao();
		obj.addIntoDB(postDetails);
		mv.addObject("error", error);
		return mv;
	}

	/*
	 * @ description: To handle the ajax request to perform date-validation
	 */
	@ResponseBody
	@RequestMapping(value = "date-validation", method = RequestMethod.POST)
	public String dateValidationAjax(String date) {
		BlogPostValidation val = new BlogPostValidation();
		String error = val.checkDate(date);
		return error;
	}

	/*
	 * @ description: To handle the ajax request to perform URL-validation
	 */
	@ResponseBody
	@RequestMapping(value = "url-validation", method = RequestMethod.POST)
	public String urlValidationAjax(String url) {
		BlogPostValidation val = new BlogPostValidation();
		String error = val.checkURL(url);
		return error;
	}

	/*
	 * @ description: To handle the ajax request to perform date-validation
	 */
	@ResponseBody
	@RequestMapping(value = "count-validation", method = RequestMethod.POST)
	public String countValidationAjax(String text) {
		BlogPostValidation val = new BlogPostValidation();
		String error = val.countLength(text);
		return error;
	}

	/*
	 * @ description: To handle the ajax request to load post in particular Blog in
	 * dropdown
	 */
	@ResponseBody
	@RequestMapping(value = "load-post", method = RequestMethod.POST)
	public ModelAndView loadPost(String blogId) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		BlogPostNameDropDownDao postName = new BlogPostNameDropDownDao();
		ArrayList<BlogPostNameBean> postNameDetails = postName.getPostDetails(blogId);
		System.out.println(postNameDetails.size());
		mv.setViewName("blog-post-loadpostdetails");
		mv.addObject("postList", postNameDetails);
		return mv;
	}

	/*
	 * @ description: To handle the ajax request for getting details of particular
	 * blogpost to autucomplete .
	 */
	@ResponseBody
	@RequestMapping(value = "edit-update-autocomplete", method = RequestMethod.GET)
	public String loadUpdate(String postId, String blogId) throws ClassNotFoundException, SQLException {
		BlogPostEditDao daoObj = new BlogPostEditDao();
		ArrayList<String> updateDetails = daoObj.getEditSubmitDetails(blogId, postId);
		Gson gson = new Gson();
		String data = gson.toJson(updateDetails);
		return data;
	}

	/*
	 * @ description: To handle the ajax request for loading blog-name details in
	 * view page for autocomplete .
	 */
	/*@RequestMapping(value = "blogname-autocomplete", method = RequestMethod.GET)
	@ResponseBody
	public String blogNameAutocomplete() throws NullPointerException {
		BlogPostViewDao detailsObj = new BlogPostViewDao();
		String JSONBlog = detailsObj.getJSONBlogNames();
		return JSONBlog;
	}
*/
}
