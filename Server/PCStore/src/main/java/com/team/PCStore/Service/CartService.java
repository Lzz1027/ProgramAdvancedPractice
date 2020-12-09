package com.team.PCStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.CartDao;
import com.team.PCStore.Entity.Cart;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartDao cartD;
	
	public JSONObject deleteGoodsFromCart(Integer userId, Integer goodsId) {
		
		JSONObject data = new JSONObject(true);
		
		Cart cart = cartD.findByUserIdAndProductId(userId, goodsId);
		
		System.out.println(cart.getCartId());
		
		cartD.deleteById(cart.getCartId());
		//cartD.deleteByCartId(cart.getCartId());
		
		return conJ(data, true);
	
	}
	
	//往购物车中添加商品
	public JSONObject addCart(Integer userId, Integer goodId, Integer goodsNum) {
		JSONObject data = new JSONObject(true);
		data.put("data", "");
		
		List<Cart> allCart = cartD.findAll();
		boolean flag = false;
		for(Cart cart : allCart) {
			if(cart.getProductId() == goodId) {
				flag = true;
			}
		}
		if(flag) {
			Cart c = cartD.findByProductId(goodId);
			int num = goodsNum + c.getProductNumber();
			c.setProductNumber(num);
			cartD.save(c);
			return conJ(data, true);
		}
		
		int num = cartD.findAll().size() + 1;
		Cart cart = new Cart();
		cart.setCartId(num);
		cart.setProductId(goodId);
		cart.setUserId(userId);
		cart.setProductNumber(goodsNum);
		cartD.save(cart);
		
		
		return conJ(data, true);
	}
	
	
	public JSONObject refreshCart(Integer userId, Integer goodsId, Integer goodsNum) {
		
		JSONObject data = new JSONObject(true);
		
		Cart cart = cartD.findByUserIdAndProductId(userId, goodsId);
		cart.setProductNumber(goodsNum);
		
		cartD.save(cart);
		
		return conJ(data, true);
	}
	
	public JSONObject getCartList(String userId, Integer pageSize, Integer curPage) {
		JSONObject data = new JSONObject(true);
		
		//得到查询用户的所有 购物信息
		List<Cart> cart = cartD.findByUserId(Integer.parseInt(userId));
		
		data.put("listLength", cart.size());
		data.put("curPage", curPage);
		
		JSONArray cartList = new JSONArray();
		
		if(cart.size() == 0) {
			data.put("cartList", cartList);
			return conJ(data, true);
		}
		
		int begin = curPage*pageSize;//0 6
		int end = begin + pageSize;  //6 12
		if(begin >cart.size()-1) {
			data.put("cartList", cartList);
			return conJ(data, true);
		}
		for(int i = begin; i < end; i++) {
			if(i > cart.size()-1)
				break;
			JSONObject tempObj = new JSONObject(true);
			Cart tempCart = cart.get(i);
			tempObj.put("goodsId", tempCart.getProductId());
			tempObj.put("goodsNum", tempCart.getProductNumber());
			cartList.add(tempObj);
		}
		data.put("cartList", cartList);
		return conJ(data, true);
	}
	
	
	
	public JSONObject getCartLength() {	
		List<Cart> cart = cartD.findByUserId(1);
		JSONObject data = new JSONObject(true);
		data.put("cartLength", cart.size());
	
		return conJ(data, true);
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
