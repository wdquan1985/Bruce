package com.bruce.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;





import com.bruce.authority.AuthorityRequired;
import com.bruce.authority.AuthorityType;
import com.bruce.authority.ResultTypeEnum;
import com.bruce.model.BaseModel;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.model.Product;
import com.bruce.serviceInterface.ProductServiceInter;
import com.bruce.upload.UploadUtil;

@Controller
@RequestMapping("/productAction")
public class ProductAction<T extends BaseModel> extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ProductServiceInter productServiceInter;
	
//	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_FIND, resultType=ResultTypeEnum.json)
	@RequestMapping(value = "/getall")
	public @ResponseBody
	Pagination All(PageBean pageBean, HttpSession session,String brand) {
		System.out.println("2222222222222222222:热加载");
		if ("".equals(brand)) {
			brand = null;
		}
		return productServiceInter.retrieve(pageBean,brand);
	}
	
	@AuthorityRequired(authorityTypes=AuthorityType.SALES_ORDER_FIND, resultType=ResultTypeEnum.json)
	@RequestMapping(value = "/save")
	public @ResponseBody
	void save(Product product) {
		System.out.println("1111111111111111: add one product" + product.getBrand());
		productServiceInter.addUser(product);
	}
	
	
	@RequestMapping(value = "/update")
	public @ResponseBody
	void update(Product product) {
		productServiceInter.updateUser(product);
		
	}
	
	@RequestMapping(value = "/search")
	public @ResponseBody
	Product search(String id,String firstName,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("11111111111111111: search is involked!");
		long ida = Long.parseLong(id);
		return productServiceInter.searchOne(ida, firstName);
	}

	
	@RequestMapping(value = "/dele")
	public @ResponseBody
	void delete(String ids,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("ids is :" + ids);
		String[] ss=ids.split(",");
		System.out.println("111111:" + ss.length);
		for(int i=0;i<ss.length;i++){
			long id = Long.parseLong(ss[i]);
			System.out.println("11111:" + id);
			Product cUser = productServiceInter.getByID(id);		
			productServiceInter.deleteUser(cUser);			
		}
	}

}