package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.beans.BlogCategoryBean;
import com.beans.ErrorBean;
import com.dao.BlogCategoryDAO;

@Controller
public class BlogCategoryController {
	
	/**
	* This class is used to show Blog Category module
	*
	* @author Mohamed Aslam A
	* @since 22-10-2022 - initial draft
	* @version 1.1
	*/

	/**
	   *@ method blogCategory-view
	   *@ description This method redirect to Blog Category view page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 01/11/2022
	   **/
	@RequestMapping(value = "blog-category-view",method = RequestMethod.GET)
	public ModelAndView blogCategorySearch(BlogCategoryBean beanObj,HttpServletRequest request) throws ClassNotFoundException, SQLException{
		ModelAndView mv = new ModelAndView("blogCategory");
		String pageNo = "1";
		String categoryIndex = null;
		BlogCategoryDAO daoObj = new BlogCategoryDAO();
		String catId =  request.getParameter("category-id");
		beanObj.setCategoryId(catId);
		//For pagination
		if (request.getParameter("page-no") != null) {
			pageNo = request.getParameter("page-no");
			beanObj.setCategoryPage(pageNo);
		}
		ArrayList<BlogCategoryBean> al = daoObj.searchFromDB(beanObj);//Arraylist for table data
		
		
		if(!(request.getParameter("category-id")!=null) && !(beanObj.getCategoryIndex()!=null)) {
			ArrayList<BlogCategoryBean> categoryList = daoObj.getCategoryName();//Arraylist for authorList
			mv.addObject("Total", (categoryList.size()) / 10 + 1);
		}
		else
		{
			mv.addObject("Total",1);
		}
		mv.addObject("indexList", daoObj.getIndexList(beanObj));
		mv.addObject("categoryBean", al);
		
		mv.addObject("pageNo", pageNo);
		return mv;
	}

	/**
	   *@ method blogCategory-add
	   *@ description This method redirect to blog author add page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	@RequestMapping(value = "blog-category-add",method = RequestMethod.GET)
	public ModelAndView blogCategoryAdd(){
		ModelAndView mv = new ModelAndView("blogCategoryAdd");
		return mv;
	}

	/**
	   *@ method blogCategory-Edit
	   *@ description This method redirect to blog Category edit page
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 29-10-2022
	   **/
	@RequestMapping(value = "blog-category-edit",method = RequestMethod.GET)
	public ModelAndView blogCategoryEdit(HttpServletRequest req) throws ClassNotFoundException, SQLException{
		ModelAndView mv = new ModelAndView("blogCategoryEdit");
		BlogCategoryDAO daoObj = new BlogCategoryDAO();
		String catId =  req.getParameter("category-id");
		mv.addObject("categoryDetails", daoObj.getCategoryName());
		mv.addObject("catId", catId);
		return mv;
	}

	/**
	   *@ method blogCategory-add
	   *@ description This method calls dao class' method to insert data into db.
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 23-10-2022
	   **/
	@RequestMapping(value = "add-category-into-db",method = RequestMethod.GET)
	public ModelAndView addCategoryIntoDB(String categoryName) throws ClassNotFoundException, SQLException{
		ModelAndView mv = new ModelAndView("blogCategoryAdd");
		BlogCategoryDAO bcd = new BlogCategoryDAO();
		ErrorBean error = bcd.addIntoDB(categoryName);
		mv.addObject("error", error);
		return mv;
	}

	/**
	   *@ method blogCategory-Edit
	   *@ description This method calls dao class' method to get specific Category details for Category Id
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 30-10-2022
	   **/
	@RequestMapping(value = "updatecategory-db",method = RequestMethod.GET)
	@ResponseBody
	public  ModelAndView editCategoryOnDB(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		System.out.println("controller called");
		String categoryId = request.getParameter("category-name");
		ModelAndView mv = new ModelAndView("blogCategoryEdit");
		BlogCategoryDAO daoObj = new BlogCategoryDAO();
		mv.addObject("categoryObj", daoObj.getCategoryFromDB(Integer.parseInt(categoryId)));
		mv.addObject("updateClick", true);
		System.out.println("obj setted");
		return mv;
	}
	
	/**
	   *@ method blogCategory-update
	   *@ description This method calls dao class' method to update data to db.
	   *@ version 1.0
	   *@ author Mohamed Aslam A
	   *@ since 1-11-2022
	   **/
	@RequestMapping(value = "update-cat-into-db",method = RequestMethod.GET)
	public  ModelAndView updateCategoryIntoODB(BlogCategoryBean beanObj) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView("blogCategoryEdit");
		BlogCategoryDAO daoObj = new BlogCategoryDAO();
		mv.addObject("categoryDetails", daoObj.getCategoryName());
		ErrorBean error = daoObj.updateCategoryOnDB(beanObj);
		mv.addObject("error", error);
		return mv;
	}
}
