package com.team.PCStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.PcDao;
import com.team.PCStore.Dao.UserDao;
import com.team.PCStore.Entity.Pc;
import com.team.PCStore.Entity.User;

@Service
@Transactional
public class PcService {
	
	@Autowired
	private PcDao pcD;
	@Autowired
	private UserDao userD;
	
	public JSONObject getGoodsList(String range, Integer page, Integer pagingSize, String type) {
		int begin = 0+page*6+1;
		int end = begin + pagingSize+1;
		
		
		JSONObject data = new JSONObject(true);

		JSONArray goodsArray = new JSONArray();
		
		if(end > 47) {
			data.put("goodsList", goodsArray);
			return conJ(data, true);
		}
		
		for(int i = begin; i < end;i++) {
			
			Pc pc = pcD.findByPcId(i);
			
			if(pc == null)
				System.out.println("第"+i+"轮获取失败");
			
			JSONObject tempObj = new JSONObject(true);
			tempObj.put("saleVolume", 5);
			tempObj.put("goodsId", pc.getPcId());
			tempObj.put("goodsName", pc.getPcName());
			tempObj.put("originalPrice", pc.getPcPrice().intValue());
			tempObj.put("discountPrice", (int)((pc.getPcPrice().intValue())*0.95));
			tempObj.put("memberPrice", (int)((pc.getPcPrice().intValue())*0.90));
			
			tempObj.put("inventory", 100);
			tempObj.put("masterImg",AppConfi.getAddress() + pc.getPcPicture().split(",")[0]);
			//tempObj.put("masterImg", AppConfi.getAddress()+"/img/ThinkBook/picture1/4.PNG");
			tempObj.put("likeNum", 10);
			goodsArray.add(tempObj);
		}
		
		data.put("goodsList", goodsArray);
		
		return conJ(data,true);
	}
	
	public JSONObject getMoreGoodsList() {
		
		JSONObject data = new JSONObject(true);
		JSONArray moreGoodsList = new JSONArray();
		for(int i = 1; i < 8; i++) {
			Pc pc = pcD.findByPcId(i);
			JSONObject temp = new JSONObject(true);
			temp.put("goodsId", pc.getPcId());
			temp.put("goodsName", pc.getPcName());
			temp.put("originalPrice", pc.getPcPrice().intValue());
			temp.put("masterImg", AppConfi.getAddress() + pc.getPcPicture().split(",")[0]);
			moreGoodsList.add(temp);
		}
		data.put("moreGoodsList", moreGoodsList);
		return conJ(data, true);
	}
	
	public JSONObject getGoodDetail(Integer goodId) {
		Pc pc = pcD.findByPcId(goodId);
		JSONObject obj = new JSONObject(true);
		obj.put("goodsName", pc.getPcName());
		obj.put("inventory", 100);
		obj.put("originalPrice", pc.getPcPrice().intValue());
		obj.put("discountPrice", (int)((pc.getPcPrice().intValue())*0.95));
		obj.put("memberPrice", (int)((pc.getPcPrice().intValue())*0.90));
		obj.put("inventory", 100);
		
		JSONObject goodsInfo = new JSONObject(true);
		String[] imgArray = pc.getPcPicture().split(",");
		JSONArray imgList = new JSONArray();
		for(int i =0 ;i < imgArray.length; i++) {
			imgList.add(AppConfi.getAddress() + "/"+imgArray[i]);
		}
		goodsInfo.put("imgList", imgList);
		goodsInfo.put("intro", pc.getPcDetail());
		goodsInfo.put("address", pc.getPcPlaceOfOrigin());
		goodsInfo.put("postage", 50);
		goodsInfo.put("videoUrl", "暂无");
		
		obj.put("goodsInfo", goodsInfo);
		
		
		return conJ(obj, true);
	}
	
	//得到所要的商品的基本信息
	public JSONObject getGoodsInfoList(Integer [] goodsIdList) {
		
		JSONObject data = new JSONObject(true);
		
		JSONArray goodsArray = new JSONArray();
		for(int i = 0 ; i < goodsIdList.length; i++) {
			//String tempId = goodsIdList[i];
			//Integer id = Integer.parseInt(tempId);
			int id = goodsIdList[i];
			Pc pc = pcD.findByPcId(id);
			JSONObject tempObj = new JSONObject(true);
			tempObj.put("goodsId", pc.getPcId());
			tempObj.put("goodsName", pc.getPcName());
			tempObj.put("originalPrice", pc.getPcPrice().intValue());
			tempObj.put("discountPrice", (int)((pc.getPcPrice().intValue())*0.95));
			tempObj.put("memberPrice", (int)((pc.getPcPrice().intValue())*0.90));
			tempObj.put("inventory", 100);
			tempObj.put("masterImg",AppConfi.getAddress()+pc.getPcPicture().split(",")[0]);
			goodsArray.add(tempObj);
		}
			
		data.put("goodsInfoList", goodsArray);
		
		return conJ(data,true);
	}	
	
	//通过data和成功与否构造一个返回的json对象
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
	
	private boolean isTokenValid(String token) {
		List<User> allUser = userD.findAll();
		boolean flag = false;
		for(User user : allUser) {
			if(user.getUserToken().equals(token))
				flag = true;
		}
		return flag;
	}
	
}
