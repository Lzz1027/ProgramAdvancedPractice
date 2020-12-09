package com.team.PCStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.UserDao;
import com.team.PCStore.Entity.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao ud;
	
	public JSONObject check(String f,String v) {
		JSONObject data = new JSONObject(true);
		data.put("canUse", true);
		data.put("filed", f);
		data.put("value", v);
		return conJ(data,true);
	}
	//用户注册
	public JSONObject register(String email, String password, String verfyCode) {
		JSONObject res = new JSONObject(true);
		
		List<User> allUser = ud.findAll();
		
		if(ifUserRegister(email)) {
			res.put("detail", "此邮箱已经有人注册过了");
			return res;
		}
		
		User user = new User();
		user.setUserId(allUser.size()+1);
		
		int userId = allUser.size() + 1;
		
		user.setUserEmail(email);
		user.setUserPassword(password);
		ud.save(user);
		
		JSONObject data = new JSONObject(true);
		data.put("userId", userId);
		res.put("code", AppConfi.getSuccessCode());
		res.put("data", data);
		res.put("msg", "注册成功");
		
		return res;
	}

	//用户重置密码
	public JSONObject resetPassword(String email, String password, String verfyCode) {
		JSONObject res = new JSONObject(true);
		
		if(!ifUserRegister(email)) {
			//说明邮箱不存在,
			res.put("detail", "用户不存在");
			return res;
		}
		
		User user = ud.findByUserEmail(email);
		user.setUserPassword(password);
		ud.save(user);
		res.put("detail", "更改用户密码成功");
		return res;
	}

	//更新用户信息
	public JSONObject updateUserInfo(String email, String nickName,Integer gender,String avatarUrl){
		JSONObject res = new JSONObject(true);
		
		if(!ifUserRegister(email)) {
			res.put("detail", "用户未注册");
			return res;
		}
		User user = ud.findByUserEmail(email);
		user.setUserGender(gender);
		user.setUserHearPic(avatarUrl);
		user.setUserName(nickName);
		ud.save(user);
		res.put("detail", "更新用户信息成功");

		return res;
	}
	
	//登录
	public JSONObject login(String email, String password) {
		JSONObject res = new JSONObject(true);
		if(!ifUserRegister(email)) {
			//还没注册
			res.put("detail", "邮箱或者密码错误");
			return res;
		}
		User user = ud.findByUserEmail(email);
		
		res.put("code", 10000);
		
		JSONObject data = new JSONObject(true);
		JSONObject userInfo = new JSONObject(true);
		
		userInfo.put("nickName", user.getUserName());
		userInfo.put("gender", user.getUserGender());
		userInfo.put("avatalUrl",AppConfi.getAddress()+ user.getUserHearPic());
		userInfo.put("balance", user.getUserMoney());
		userInfo.put("email", user.getUserEmail());
		
		data.put("userInfo", userInfo);
		data.put("token", user.getUserId().toString());
		data.put("userId", user.getUserId());
		
		res.put("data", data);
		res.put("msg", "登录成功");
		return res;
	}
	
	//判断用户是否注册
	//true -> 已经注册了；false -> 没有注册
	private boolean ifUserRegister(String email) {
		//得到所有的用户
		List<User> allUser = ud.findAll();
		//默认没有注册
		boolean flag = false;
		for(User user : allUser) {
			if(user.getUserEmail().equals(email))
				//改成已经注册了
				flag = true;
		}
		
		return flag;
	}

	//构造返回数据
		private JSONObject conJ(JSONObject data, boolean success) {
			JSONObject res = new JSONObject(true);
			if(success) {
				res.put("code", AppConfi.getSuccessCode());
				res.put("msg", "操作成功");
				res.put("data", data);
			}
			else {
				res.put("code", 49909);
				res.put("msg", "操作失败");
				res.put("data", data);
			}
			return res;
		}
}
