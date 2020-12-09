package com.team.PCStore.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;

@Service
@Transactional
public class PublicService {
	
	public JSONObject getVerifyCode() {
		JSONObject data = new JSONObject(true);
		return conJ(data, true);
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
