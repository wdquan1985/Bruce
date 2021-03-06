package com.bruce.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.authority.AuthorityRequired;
import com.bruce.authority.AuthorityType;

@Controller
@RequestMapping("auth")
public class LoginLogoutController{

	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * 指向登录页面
	 */
//	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_FIND)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {

		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		System.out.println("1111111111111111");
		return "loginpage";

	}

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		logger.debug("Received request to show denied page");

		return "deniedpage";

	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String ChangePassPage() {

		logger.debug("Received request to change Password !");

		return "changePassword";

	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String GotoMianPage() {

		logger.debug("Received request to change Password !");

		return "mainPage";

	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String GotoAddPage() {

		logger.debug("Received request to change Password !");

		return "jsonAdd";

	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String GotoSearchPage() {

		logger.debug("Received request to change Password !");

		return "jsonSearch";

	}
	
	
}
