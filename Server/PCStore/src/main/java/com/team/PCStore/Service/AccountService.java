package com.team.PCStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.UserDao;
import com.team.PCStore.Entity.User;


@Service
@Transactional
public class AccountService {

	@Autowired
	private UserDao ud;
	
	public JSONObject recharge(Integer userId,String code, String key) {
		JSONObject data = new JSONObject(true);
		User user = ud.findByUserId(userId);
		int m = user.getUserMoney();
		int n = 0;
		
		if(code.equals("123456")) {
			if(key.equals("littler"))
				n += 500;
			else if(key.equals("little"))
				n += 1880;
			else if(key.equals("custom"))
				n += 2880;
			else if(key.equals("more"))
				n += 5880;
			else if(key.equals("evenMore"))
				n += 8880;
			else
				n += 10000;
		user.setUserMoney(m+n);
		ud.save(user);
		data.put("balance", m+n);
		return conJ(data,true);
		}else {
			data.put("balance", m);
			return conJ(data, true);
		}
	}
	
	public JSONObject getAccountBalance() {
		JSONObject data = new JSONObject(true);
		
		User user = ud.findByUserId(1);
		data.put("balance", user.getUserMoney());
		return conJ(data,true);
	}

	public JSONObject checkPaykey() {
		User user = ud.findByUserId(1);
		boolean flag = false;
		if(user.getUserPayKey().length() != 0)
			flag = true;
		
		JSONObject data = new JSONObject(true);
		data.put("hasPaykey", flag);
		return conJ(data,true);
	}
	
	public JSONObject paykey(String email, String paykey, String code) {
		User user = ud.findByUserEmail(email);
		user.setUserPayKey(paykey);
		ud.save(user);
		return conJ(new JSONObject(true), true);
	}
	
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
