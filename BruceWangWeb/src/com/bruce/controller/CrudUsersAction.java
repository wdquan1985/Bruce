package com.bruce.controller;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
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
import com.bruce.model.CrudUsers;
import com.bruce.model.PageBean;
import com.bruce.model.Pagination;
import com.bruce.serviceInterface.CrudUsersServiceInter;
import com.bruce.upload.UploadUtil;

@Controller
@RequestMapping("/crudUsers")
public class CrudUsersAction<T extends BaseModel> extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	CrudUsersServiceInter crudUsersServiceInter;
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	//@ResponseBody,表示该方法的返回结果直接写入HTTP response body中
	//一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，
	//而是直接写入HTTP response body中。比如异步获取json数据，加上@responsebody后，会直接返回json数据。
	@ResponseBody
	public Pagination All(PageBean pageBean, HttpSession session) {
		System.out.println("2222222222222222222:热加载");
		return crudUsersServiceInter.retrieve(pageBean);
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody
	void save(CrudUsers crudUsers) {
		System.out.println("1111111111111111: add the cruduser" + crudUsers.getFirstname());
		crudUsersServiceInter.addUser(crudUsers);
	}
	
	
	@RequestMapping(value = "/update")
	public @ResponseBody
	void update(CrudUsers crudUsers) {
//		Long id = crudUsers.getId();
		crudUsersServiceInter.updateUser(crudUsers);
		
	}
	
	@RequestMapping(value = "/search")
	public @ResponseBody
	CrudUsers search(String id,String firstName,HttpServletRequest request,HttpServletResponse response) {
//		Long id = crudUsers.getId();
		System.out.println("11111111111111111: search is involked!");
		long ida = Long.parseLong(id);
		return crudUsersServiceInter.searchOne(ida, firstName);
		
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getAddress(String idnum,HttpServletRequest request,HttpServletResponse response) {
//		Long id = user.getId();
		System.out.println("get the address of the picture!" + idnum);
		Long id = Long.parseLong(idnum);
		CrudUsers users = crudUsersServiceInter.getByID(id);
		int condition = 123;
		System.out.println(condition);
		String path= users.getPicPath();
		if(path == null){
			path="noPic";
		}
//      得到小图片相对路径
        String proName = request.getContextPath().replace("/",""); //获取web工程的名称,并且去掉‘/’字符。
        System.out.println(proName);
        if(path.equals("noPic") == false){
        	path = path.split(proName)[1];
            path = path.replace('\\','/');
            path = path.substring(1,path.length()); 
        }
        System.out.println(path);
        
//      得到大大图片相对路径
        String bigPath= users.getPicPathBig();
		if(bigPath == null){
			bigPath="noPic";
		}
		if(bigPath.equals("noPic") == false){
			bigPath = bigPath.split(proName)[1];
	        bigPath = bigPath.replace('\\','/');
	        bigPath = bigPath.substring(1,bigPath.length()); 
		}

        System.out.println(bigPath);
        
        List<String> list = new ArrayList<String>();
        list.add(path);
        list.add(bigPath);
        return list;
 
        
	}
	
	@RequestMapping(value = "/dele", method = RequestMethod.GET)
	public @ResponseBody
	void delete(String ids,HttpServletRequest request,HttpServletResponse response) {
		// ��session�л�ȡmeetingId
		System.out.println("ids is :" + ids);
//		String idString = "58,";

		String[] ss=ids.split(",");
		System.out.println("111111:" + ss.length);
		for(int i=0;i<ss.length;i++){
			long id = Long.parseLong(ss[i]);
			System.out.println("11111:" + id);
			
			CrudUsers cUser = crudUsersServiceInter.getByID(id);		
			crudUsersServiceInter.deleteUser(cUser);			
		}
	}
	
    @RequestMapping ("upload01")  //***********************上传大图片和小图片。
    public String upload01(Model model,HttpServletRequest request)  
    {
    	
        //转型为MultipartHttpRequest(重点的所在)     
                MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;     
                //  获得第1张图片（根据前台的name名称得到上传的文件）      
                MultipartFile imgFile1  =  multipartRequest.getFile("imgFile");
                
                //然后获取前台发送来的idinfo。String str=request.getParameter("userid");
                String idInfo = request.getParameter("idinfo");
                System.out.println("0000000000000000000: upload01 get the idinfo" + imgFile1.getContentType());
                
                UploadUtil uploadutil = new UploadUtil();
                String fileName = imgFile1.getOriginalFilename();
                
                String path = null;
                try {  
                	
                	path = uploadutil.uploadImage1(request, imgFile1, imgFile1.getContentType(), fileName);  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();
                }  
                
                
             //保存大图片
                String bigPath = request.getSession().getServletContext().getRealPath("picturePath");
                String picPathBig = bigPath + '\\' + fileName;
                String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
                //对扩展名进行小写转换  
                ext = ext.toLowerCase();  
                System.out.println("1234567890");
                List<String> fileTypes = new ArrayList<String>();  
                fileTypes.add("jpg");  
                fileTypes.add("jpeg");  
                fileTypes.add("bmp");  
                fileTypes.add("gif");
                
                //判断是否是合法文件。
                if(fileTypes.contains(ext)) { 
                	
                	 System.out.println(bigPath);  
                     File targetFile = new File(bigPath, fileName);  
                     if(!targetFile.exists()){  
                         targetFile.mkdirs();  
                     }  
                     
                     //保存  
                     try {  
                    	 imgFile1.transferTo(targetFile);  
                     } catch (Exception e) {
        			 
                		e.printStackTrace();
                     }  
                    // model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);        
                     
                }else {
                	System.out.println("The error image format");
        		}
                
//              update the picture path in the database.
                Long id = Long.parseLong(idInfo);
                CrudUsers crudUser = crudUsersServiceInter.getByID(id);
                crudUser.setPicPath(path);
                crudUser.setPicPathBig(picPathBig);
                crudUsersServiceInter.updateUser(crudUser);

        return "query";
    } 
    
    
	//simple file upload
	@RequestMapping(value = "/upload")
	@ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, 
    		HttpServletResponse response,ModelMap model) throws IOException  {  
  
        System.out.println("开始"); 
        
     // 设置响应给前台的数据格式
     	response.setContentType("text/plain;charset=UTF-8");
     // 设置响应给前台内容的PrintWriter对象
       // PrintWriter out = response.getWriter();
        
        String path = request.getSession().getServletContext().getRealPath("picture"); 
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
        String fileName = file.getOriginalFilename();  
        

        String picPath = path + '\\' + fileName;
        
        //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
        String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
        //对扩展名进行小写转换  
        ext = ext.toLowerCase();  
        System.out.println("1234567890");
        List<String> fileTypes = new ArrayList<String>();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif");  
        
       //判断是否是合法文件。
        if(fileTypes.contains(ext)) { 
        	
        	 System.out.println(path);  
             File targetFile = new File(path, fileName);  
             if(!targetFile.exists()){  
                 targetFile.mkdirs();  
             }  
             
             //保存  
             try {  
                 file.transferTo(targetFile);  
             } catch (Exception e) {
         //		out.print("1`文件上传失败，请重试！！");
        	//	out.flush();
        		e.printStackTrace();
             }  
            // model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);        
             
        }else {
        	System.out.println("The error image format");
        //	out.print("1`The error image format，请重试！！");
    	//	out.flush();
    		//e.printStackTrace();
			//return "The error image format";
		}
        
       // return "Save picture successfully!"; 
        System.out.println("Save picture successfully!");
        
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("picPath",picPath);				
//		mv.setViewName("Crud_Users");	
//		return mv;
        //如果直接返回图片的硬盘地址，在前台是不会显示的，所以在这里返回工程名字后面的一部分，工程的网络地址在前台得到，
        System.out.println(picPath);
        
//      update the picture path in the database.
        CrudUsers crudUser = crudUsersServiceInter.getByID(1L);
        crudUser.setPicPath(path + '\\' + fileName);
        crudUsersServiceInter.updateUser(crudUser);
        
//      返回相对路径
        String proName = request.getContextPath().replace("/",""); //获取web工程的名称,并且去掉‘/’字符。
        System.out.println(proName);
        picPath = picPath.split(proName)[1];
        picPath = picPath.replace('\\','/');
        return picPath;
       
    }  
}