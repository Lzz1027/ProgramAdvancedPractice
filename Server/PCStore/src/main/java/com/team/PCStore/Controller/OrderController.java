package com.team.PCStore.Controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.Service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderS;
	
	//创建订单
	@PostMapping("/create-order")
	@ResponseBody
	public JSONObject createOrder(@RequestBody Map<String,Object> map) {
		
		String userId = (String)map.get("userId");
		String addressId = (String)map.get("addressId");
		ArrayList orderList = (ArrayList)map.get("orderList");
		Integer[] goodsId = new Integer[orderList.size()];
		Integer[] purchaseNum = new Integer[orderList.size()];
		
		for(int i = 0; i < orderList.size(); i++) {	
			System.out.println(orderList.get(i));
			Map<String, Object> m = (Map)orderList.get(i);
			goodsId[i] = (Integer)m.get("goodsId");
			purchaseNum[i] = (Integer)m.get("purchaseNum");
		}
		
		return orderS.createOrder(userId, addressId, goodsId, purchaseNum);
	}
	
	//支付订单
	@PostMapping("/pay-for-order")
	@ResponseBody
	public JSONObject payForOrder(@RequestBody Map<String, Object> map) {
		Integer userId = (Integer)map.get("userId");
		Integer orderId = (Integer)map.get("orderId");
		String paykey = (String) map.get("paykey");
		return orderS.payForOrder(userId, orderId, paykey);
	}
	
	//删除订单
	@DeleteMapping("/order")
	@ResponseBody
	public JSONObject deleteOrder(@RequestParam("orderId") String orderId) {
		return orderS.deleteOrder(orderId);
	}
	
	//查找订单列表
	@GetMapping("/order-list")
	@ResponseBody
	public JSONObject orderList(@RequestParam(value = "userId") Integer userId,
								@RequestParam(value = "pageSize", required = false)Integer pageSize, 
			                    @RequestParam(value = "currentPage", required = false)Integer currentPage) {
		
		return orderS.getOrderList(userId,pageSize,currentPage);
	}
}
