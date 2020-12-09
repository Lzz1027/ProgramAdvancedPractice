package com.team.PCStore.Controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.Service.UserService;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired 
	private UserService us;
	
	@GetMapping("/goods-like-list")
	@ResponseBody
	public JSONObject getLikeList() {
		JSONObject res = new JSONObject(true);
		JSONObject data = new JSONObject(true);
		
		Integer [] a = new Integer[1];
		a[0] = 1;
		
		data.put("goodsLikeList", a);
		res.put("data", data);
		res.put("code", 10000);
		res.put("msg", "成功");
		return res;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public JSONObject register(@RequestBody Map<String,Object> userInfo) {
		
		String email = (String) userInfo.get("email");
		String password = (String) userInfo.get("password");
		String verfyCode = "123456";
		
		JSONObject res = us.register(email, password, verfyCode);
		return res;
	}
	
	@PostMapping("/reset-password")
	@ResponseBody
	public JSONObject resetPassword(@RequestBody Map<String,Object> userInfo){
		String email = (String) userInfo.get("email");;
		String password = (String)userInfo.get("password");
		String verfycode = "123456";
		
		JSONObject res = us.resetPassword(email, password, verfycode);
		
		return res;
	}
	
	@PostMapping("/update-user-info")
	@ResponseBody
	public JSONObject updateUserInfo(@RequestBody Map<String,Object> userInfo){
		String email = (String) userInfo.get("email");
		String nickName = (String) userInfo.get("nickName");
		Integer gender = (Integer) userInfo.get("gender");
		String avatarUrl = (String) userInfo.get("avatarUrl");
		
		System.out.println(gender);
		
		JSONObject res = us.updateUserInfo( email, nickName, gender, avatarUrl);
		
		return res;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public JSONObject login(@RequestBody Map<String,Object> userInfo) {
		for(String key : userInfo.keySet()) {
			System.out.println(key + "    " + userInfo.get(key));
		}
		
		String email = (String)userInfo.get("email");
		String password = (String)userInfo.get("password");
		
		JSONObject res = us.login(email, password);
		
		return res;
	}
	
	@PostMapping("/check-field-can-use")
	@ResponseBody
	public JSONObject check(@RequestBody Map<String, Object> map) {
		return us.check((String)map.get("field"),(String)map.get("value"));
	}
}
