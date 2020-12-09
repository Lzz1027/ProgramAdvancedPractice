package com.team.PCStore.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired 
	private AccountService accountS;

	//充值账户
	@PutMapping("/recharge")
	@ResponseBody
	public JSONObject recharge(@RequestBody Map<String, Object> map) {
		Integer userId = (Integer) map.get("userId");
		String code =(String) map.get("verifyCode");
		String key = (String)map.get("rechargeKey");
		
		System.out.println("userID : "+userId.toString() + "--------");
		System.out.println("code : "+code+ "--------");
		System.out.println("key : "+key+ "--------");
		
		
		return accountS.recharge(userId,code,key);
	}

	//更新支付密码
	@PutMapping("/paykey")
	@ResponseBody
	public JSONObject paykey(@RequestBody Map<String, Object> map){
		String email = (String) map.get("email");
		String paykey = (String)map.get("paykey");
		String verifyCode = (String)map.get("verifyCode");
		return accountS.paykey(email, paykey, verifyCode);	
	}
	
	//获取充值列表
	@GetMapping(value="/recharge-list")
	public JSONObject getMethodName() {
		JSONObject res = new JSONObject(true);
		JSONObject data = new JSONObject(true);
		JSONObject rechargeList = new JSONObject(true);
		rechargeList.put("littler", 500);
		rechargeList.put("little", 1880);
		rechargeList.put("custom", 2880);
		rechargeList.put("more", 5880);
		rechargeList.put("evenMore", 8880);
		rechargeList.put("most", 10000);
		data.put("rechargeList", rechargeList);
		res.put("data", data);
		res.put("code", AppConfi.getSuccessCode());
		res.put("msg", "获取成功");
		
		
		
		return res;
	}
	
	//获取账户余额
	@GetMapping("/balance")
	@ResponseBody
	public JSONObject getAccountBalance() {
		return accountS.getAccountBalance();
	}
	
	//检查是否有paykey
	@GetMapping("/check-has-paykey")
	@ResponseBody
	public JSONObject checkHasPaykey() {
		
		return accountS.checkPaykey();
	}
	
	
}
