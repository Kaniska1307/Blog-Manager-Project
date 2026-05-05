package com.controller;


import java.util.ArrayList;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.Blogs;
import com.beans.*;

@Controller
public class HomeController {

	// Home Mapping
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String callOne() {
		return "homelayout";
	}

	

	@RequestMapping(value = "blogs", method = RequestMethod.GET)
	public String callBlog() {
		return "blogs";
	}

	@RequestMapping(value = "blog-add-page", method = RequestMethod.GET)
	public ModelAndView callBlogAdd() {
		ModelAndView mav = new ModelAndView("blogsAdd");
		Blogs object = new Blogs();
		ArrayList<BlogAuthors> authorList = object.getAuthorList();
		mav.addObject("authorList", authorList);
		System.out.println("Hello I'm in call add blog");
		return mav;
	}

}
