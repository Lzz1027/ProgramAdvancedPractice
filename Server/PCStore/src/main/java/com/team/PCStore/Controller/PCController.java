package com.team.PCStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.PcDao;
import com.team.PCStore.Dao.UserDao;
import com.team.PCStore.Entity.Pc;
import com.team.PCStore.Entity.User;
import com.team.PCStore.Service.PcService;

import net.minidev.json.JSONArray;

@CrossOrigin("http://172.26.94.91:8080")
@RestController
@RequestMapping("goods")
public class PCController {
	
	@Autowired
	private PcService pcs;
	
	@Autowired
	private PcDao pcD;
	
	@Autowired
	private UserDao userD;
	
	@GetMapping("/testController/{id}")
	@ResponseBody
	public JSONObject test(@PathVariable Integer id) {
		
		User user = userD.findByUserId(id);
		System.out.print("userEmail:" + user.getUserEmail());
		
		System.out.println("要查找的id是："+id);
		Pc pc = pcD.findByPcId(id);
		if(pc == null)
			System.out.print("没有找到任何信息");
		
		JSONObject res = new JSONObject(true);
		res.put("id", pc.getPcId());
		res.put("brand", pc.getPcBrand());
	
		return res;
	}
	
	
	//获取商品基本信息
	@GetMapping("/goods-info-list")
	@ResponseBody
	public JSONObject getInfoList(@RequestParam("goodsIdList[]") Integer[] goodsIdList) {
		for(int i = 0; i < goodsIdList.length; i++) {
			System.out.println(goodsIdList[i]);
		}
		
		
		JSONObject res = pcs.getGoodsInfoList(goodsIdList); 
		return res;
	}
	
	
	@RequestMapping(value = "/goods-list",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getGoodsList(
							   @RequestParam("priceRange")String range, 
							   @RequestParam("currentPage")Integer page, 
							   @RequestParam("pagingSize")Integer pagingSize,
							   @RequestParam("sortType") String type) {
		System.out.print("调用了正确的函数");
		//range:价格范围 all
		//page: 0 0-5, 1 6-11
		return pcs.getGoodsList(range, page, pagingSize, type);
	}


	
	@GetMapping("/goods-detail")
	@ResponseBody
	public JSONObject getGoodsDetail(@RequestParam("goodsId") String goodId) {
		return pcs.getGoodDetail(Integer.parseInt(goodId));
	}
	
	@GetMapping("/get-more-goods-list")
	@ResponseBody
	public JSONObject getMoreGoodsList(@RequestParam(value ="maxLength",required = false) String length) {
		return pcs.getMoreGoodsList();
	}
	
}
