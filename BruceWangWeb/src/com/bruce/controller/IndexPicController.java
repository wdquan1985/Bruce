package com.bruce.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.model.IndexPic;
import com.bruce.serviceInterface.IIndexPicService;


@Controller
@RequestMapping("/admin/pic")
public class IndexPicController {
	
	@Autowired
	private IIndexPicService indexPicService;

	public final static String FILE_PATH="/resources/indexPic";
	public final static int T_W = 120;

	@RequestMapping("/indexPics")
	public String listIndexPic(Model model) {
//		Map<String,Integer> mm = indexPicService.getMinAdnMaxPos();
//		model.addAttribute("min", mm.get("min"));
//		model.addAttribute("max", mm.get("max"));
		model.addAttribute("datas",indexPicService.findIndexPic());
		return "listIndexPic";
	}
	
	@RequestMapping(value="/addIndexPic",method=RequestMethod.GET)
	public String addIndexPic(Model model) {
		IndexPic ip = new IndexPic();
		ip.setStatus(1);
		model.addAttribute("indexPic", ip);
		return "addIndexPic";
	}
	

	
	@RequestMapping(value="/updateIndexPic/{id}",method=RequestMethod.GET)
	public String updateIndexPic(@PathVariable int id,Model model) {
		IndexPic ip = indexPicService.load(id);
		model.addAttribute("indexPic", ip);
		return "updateIndexPic";
	}
	

	
	@RequestMapping(value="/indexPic/{id}")
	public String showIndexPic(@PathVariable int id,Model model) {
		model.addAttribute("indexPic",indexPicService.load(id));
		return "showIndexPic";
	}
}
