package com.bruce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.authority.AuthorityType;
import com.bruce.authority.AuthorityRequired;
import com.bruce.authority.LoginRequired;
import com.bruce.authority.ResultTypeEnum;
import com.bruce.authority.util.AjaxResult;

//验证annotation 权限控制。
@Controller("memoController")
@RequestMapping(value = "/memo")
public class MemoController {

	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired(ResultTypeEnum.page)
	@RequestMapping(value = "/doSomeThing_a.spr", method = RequestMethod.GET)
	public ModelAndView doSomeThing_a() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login.jsp");
		logger.info("doSomeThing_a");
		return mav;
	}
	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired(ResultTypeEnum.json)
	@RequestMapping(value = "/doSomeThing_b.spr", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult doSomeThing_b() throws Exception {
		AjaxResult ar = new AjaxResult();
		ar.setResult(false);
		ar.setErrorMessage("操作成功!");
		logger.info(" doSomeThing_b ");
		return ar;
	}

	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired()
	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_CREATE)
	@RequestMapping(value = "/doSomeThing_c.spr", method = RequestMethod.GET)
	public ModelAndView doSomeThing_c() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mg/userIndex");
		logger.info(" doSomeThing_c ");
		mav.addObject("opmsg", "调用成功!");
		return mav;
	}
	
	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired()
	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_FIND)
	@RequestMapping(value = "/doSomeThing_d.spr", method = RequestMethod.GET)
	public ModelAndView doSomeThing_d() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mg/userIndex");
		logger.info(" doSomeThing_d ");
		mav.addObject("opmsg", "调用成功!");
		return mav;
	}
	
	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired(ResultTypeEnum.json)
	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_CREATE, resultType=ResultTypeEnum.json)
	@RequestMapping(value = "/doSomeThing_e.spr", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult doSomeThing_e() throws Exception {
		AjaxResult ar = new AjaxResult();
		ar.setResult(false);
		ar.setErrorMessage("操作成功!");
		logger.info(" doSomeThing_e ");
		return ar;
	}
	
	/**
	 * @param sentence
	 * @return
	 * @throws Exception
	 */
	@LoginRequired(ResultTypeEnum.json)
	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_FIND, resultType=ResultTypeEnum.json)
	@RequestMapping(value = "/doSomeThing_f.spr", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult doSomeThing_f() {
		AjaxResult ar = new AjaxResult();
		ar.setResult(false);
		ar.setErrorMessage("操作成功!");
		logger.info(" doSomeThing_f ");
		return ar;
	}

}
