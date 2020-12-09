package com.team.PCStore.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.Service.CartService;

@CrossOrigin
@RestController
@RequestMapping("cart")

public class CartController {
	
	@Autowired
	private CartService cartS;
	
	//从购物车删除商品
	@DeleteMapping("/cart")
	@ResponseBody
	public JSONObject deleteCart(@RequestBody Map<String, Object> map) {
		
		Integer userId = (Integer) map.get("userId");
		Integer goodsId = (Integer) map.get("goodsId");
		
		System.out.println(userId);
		System.out.println(goodsId);
		
		
		return cartS.deleteGoodsFromCart(userId, goodsId);
	}
	
	//加入购物车
	@PostMapping("/cart")
	@ResponseBody
	public JSONObject addCart(@RequestBody Map<String, Object> map) {
		Integer userId =(Integer) map.get("userId");
		Integer goodsId =(Integer) map.get("goodsId");
		Integer goodsNum = (Integer) map.get("goodsNum");
		return cartS.addCart(userId, goodsId, goodsNum);
	}
	
	//更新购物车
	@PutMapping("/cart")
	@ResponseBody
	public JSONObject refreshCart(@RequestBody Map<String, Object> map) {
		Integer userId = (Integer)map.get("userId");
		Integer goodsId = (Integer)map.get("goodsId");
		Integer goodsNum = (Integer)map.get("goodsNum");
		
		return cartS.refreshCart(userId, goodsId, goodsNum);
	}
	
	@GetMapping("/cart-length")
	@ResponseBody
	public JSONObject getCartLength() {
		return cartS.getCartLength();
	}
	
	//获取购物车列表
	@GetMapping("/cart")
	@ResponseBody
	public JSONObject getCartList(@RequestParam("userId") String userId,
								  @RequestParam(value = "pageSize", required = false, defaultValue = "6")Integer pageSize,
								  @RequestParam(value = "curPage", required = false, defaultValue = "0")Integer curPage){
		
		JSONObject res = cartS.getCartList(userId, pageSize, curPage);
		System.out.println(res);
		
		return cartS.getCartList(userId, pageSize, curPage);
	}
}
