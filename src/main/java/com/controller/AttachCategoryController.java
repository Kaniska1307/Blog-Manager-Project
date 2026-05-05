package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beans.BlogCategoryNameBean;
import com.beans.BlogPostNameBean;
import com.dao.AttachCategoryDao;
import com.dao.BlogCategoryNameDropDownDao;
import com.dao.BlogPostNameAttachCategoryDropDownDao;
import com.validation.AttachCategoryValidation;

/**
 *@ class AttachCategoryController
 *@ description To handle all the request related to Attach-Category module
 *@ version 1.0
 *@ author HARISH KUMAAR S
 *@ since 23/12/2022
 **/

@Controller
public class AttachCategoryController {

	/*
	 * @ description: To handle the request for redirecting to attachCategory page .
	 */
	@RequestMapping(value = "attach-category", method = RequestMethod.GET)
	public ModelAndView callAttachCategory() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		BlogCategoryNameDropDownDao categName = new BlogCategoryNameDropDownDao();
		ArrayList<BlogCategoryNameBean> blogNameDetails = categName.getCategoryDetails();
		mv.setViewName("attach-category");
		mv.addObject("categoryList", blogNameDetails);
		return mv;
	}

	/*
	 * @ description: To handle the request for performing insert or delete action
	 * for post to category .
	 */
	@RequestMapping(value = "attach-category-action", method = RequestMethod.POST)
	public ModelAndView deleteOrAttachCategory(String postId, String flag, String categoryId,String categoryName)
			throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		AttachCategoryDao callDao1 = new AttachCategoryDao();
		AttachCategoryValidation validate = new AttachCategoryValidation();
		String postIdVal = validate.removeComma(postId);
		BlogCategoryNameDropDownDao categName = new BlogCategoryNameDropDownDao();
		BlogPostNameAttachCategoryDropDownDao callDao2 = new BlogPostNameAttachCategoryDropDownDao();
		ArrayList<BlogPostNameBean> postDetails = callDao1.attachCategoryProcess(flag, categoryId, postIdVal);
		ArrayList<BlogCategoryNameBean> blogNameDetails = categName.getCategoryDetails();
		ArrayList<BlogPostNameBean> postDropDown = callDao2.getPostDetails();
		mv.setViewName("attach-category-view");
		// String status=postDetails.get(0).getErrorStatus();
		// mv.addObject("status", status);
		System.out.println("error msg: " + postIdVal);
		mv.addObject("categoryList", blogNameDetails);
		mv.addObject("categoryPostList", postDetails);
		mv.addObject("postDropDownList", postDropDown);
		mv.addObject("categoryId", categoryId);
		mv.addObject("categoryName", categoryName);
		return mv;
	}

	/*
	 * @ description: To handle the ajax request for getting attached post details
	 * and to attach other post to particular category.
	 */
	@ResponseBody
	@RequestMapping(value = "show-attachcategory-details", method = RequestMethod.POST)
	public ModelAndView showAttachcategory(String categoryId, String categoryName)
			throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		String flag = null;
		String postId = null;
		mv.setViewName("attach-category-view");
		AttachCategoryDao callDao1 = new AttachCategoryDao();
		BlogPostNameAttachCategoryDropDownDao callDao2 = new BlogPostNameAttachCategoryDropDownDao();
		ArrayList<BlogPostNameBean> postDetails = callDao1.attachCategoryProcess(flag, categoryId, postId);
		ArrayList<BlogPostNameBean> postDropDown = callDao2.getPostDetails();
		mv.addObject("categoryPostList", postDetails);
		mv.addObject("postDropDownList", postDropDown);
		mv.addObject("categoryId", categoryId);
		mv.addObject("categoryName", categoryName);
		return mv;
	}

}
