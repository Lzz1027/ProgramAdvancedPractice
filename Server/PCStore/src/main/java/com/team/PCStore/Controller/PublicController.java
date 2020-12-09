package com.team.PCStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.Service.PublicService;

@CrossOrigin()
@RestController
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	private PublicService publicS;
	
	@PostMapping("/get-verify-code")
	@ResponseBody
	public JSONObject getVerifyCode() {
		return publicS.getVerifyCode();
	}
	
}
