package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beans.BlogAuthorBean;
import com.beans.BlogCategoryBean;
import com.beans.ErrorBean;
import com.business.BlogAuthorDetails;
import com.dao.BlogAuthorDAO;

@Controller
public class BlogAuthorController {
	/**
	* This class is used to show Blog Author module
	*
	* @author Mohamed Aslam A
	* @since 22-10-2022 - initial draft
	* @version 1.1
	*/

	
	/**
	   *@ method blogAuthor-view
	   *@ description This method redirect to Blog author view page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 01/11/2022
	   **/
	@GetMapping(value = "blog-author-view")
	public ModelAndView blogAuthorSearch(BlogAuthorBean beanObj,HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String pageNo = "1";
		String authorIndex = null;
		BlogAuthorDAO daoObj = new BlogAuthorDAO();
		ArrayList<BlogAuthorBean> authorList = daoObj.getAuthorNameDetails();//Arraylist for authorList
		ModelAndView mv = new ModelAndView("blogAuthors");
		//For pagination
		if (request.getParameter("page-no") != null) {	
			pageNo = request.getParameter("page-no");
			beanObj.setAuthorPage(pageNo);
		}
		if (request.getParameter("author-index") != null) {
			authorIndex = request.getParameter("author-index");
			mv.addObject("Total",1);
			beanObj.setAuthorIndex(authorIndex);
		}
		
		if((beanObj.getAuthorQualification()!=null))
		{
			mv.addObject("Total",1);
		}
        if(!(beanObj.getAuthorQualification()!=null) && !(request.getParameter("author-index") != null))
        {
        	mv.addObject("Total", (authorList.size()) / 10 + 1);
        }
		ArrayList<BlogAuthorBean> al = daoObj.searchFromDB(beanObj);//Arraylist for table data 
		mv.addObject("qualificationList", beanObj.getListForQualification());//retrieving qualification list for select box
		mv.addObject("indexList", daoObj.getIndexList(beanObj));
		mv.addObject("authorBean", al);
		mv.addObject("pageNo", pageNo);
		return mv;
	} 
	 /**
	   *@ method blogAuthors-add
	   *@ description This method redirect to blog author add page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	@RequestMapping(value = "blog-authors-add", method = RequestMethod.GET)
	public ModelAndView callAuthorAdd() {
		ModelAndView mv = new ModelAndView("blogAuthorsAdd");
		BlogAuthorBean beanObj = new BlogAuthorBean();
		mv.addObject("qualificationList", beanObj.getListForQualification());//retrieving qualification list for select box
		return mv;
	}

	/**
	   *@ method blogAuthors-Edit
	   *@ description This method redirect to blog author edit page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	@RequestMapping(value = "blog-authors-edit", method = RequestMethod.GET)
	public ModelAndView callAuthorEdit(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView("blogAuthorsEdit");
		BlogAuthorBean beanObj = new BlogAuthorBean();
		String authorId = req.getParameter("author-id");
		mv.addObject("qualificationList", beanObj.getListForQualification());
		BlogAuthorDAO daoObj = new BlogAuthorDAO();
		mv.addObject("authorDetails", daoObj.getAuthorNameDetails());
		mv.addObject("aid", authorId);
		return mv;
	}

	/**
	   *@ method blogAuthors-add
	   *@ description This method calls dao class' method to insert data into db.
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	@RequestMapping(value = "add-authors-into-db", method = RequestMethod.GET)
	public ModelAndView addAuthorIntoDB(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView("blogAuthorsAdd");
		BlogAuthorDAO daoObj = new BlogAuthorDAO();
		ErrorBean error = daoObj.addIntoDB(beanObj);
		mv.addObject("qualificationList", beanObj.getListForQualification());
		mv.addObject("error", error);
		return mv;
	}

	/**
	   *@ method blogAuthors-Edit
	   *@ description This method calls dao class' method to get specific author details for author Id
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 30-10-2022
	   **/
	@RequestMapping(value = "updateauthor-db", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView editAuthorOnDB(String text) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView("blogAuthorsUpdate");
		BlogAuthorDAO daoObj = new BlogAuthorDAO();
		BlogAuthorBean beanObj = new BlogAuthorBean();
		mv.addObject("qualificationList", beanObj.getListForQualification());
		mv.addObject("authorObj", daoObj.getAuthorFromDB(Integer.parseInt(text)));
		return mv;
	}

	/**
	   *@ method blogAuthors-update
	   *@ description This method calls dao class' method to update data to db.
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 1-11-2022
	   **/
	@RequestMapping(value = "update-authour-into-db", method = RequestMethod.GET)
	public ModelAndView updateAuthorIntoODB(BlogAuthorBean beanObj) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView("blogAuthorsEdit");
		BlogAuthorDAO daoObj = new BlogAuthorDAO();
		ErrorBean error = daoObj.updateAuthorOnDB(beanObj);
		mv.addObject("qualificationList", beanObj.getListForQualification());
		mv.addObject("authorDetails", daoObj.getAuthorNameDetails());
		mv.addObject("error", error);
		return mv;
	}
	
	@RequestMapping(value = "blog-author-autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public String blogAuthorAutocomplete() throws NullPointerException {
        BlogAuthorDetails detailsObj = new BlogAuthorDetails();
        String JSONBlog = detailsObj.getJSONBlogNames();
        return JSONBlog;
    }
}
