package com.team.PCStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.OrderDao;
import com.team.PCStore.Dao.PcDao;
import com.team.PCStore.Dao.UserDao;
import com.team.PCStore.Entity.Order;
import com.team.PCStore.Entity.Pc;
import com.team.PCStore.Entity.User;


@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderDao orderD;
	@Autowired
	private UserDao userD;
	@Autowired
	private PcDao pcD;
	//创建订单
	public JSONObject createOrder(String userId, String addressId, Integer[] goodsId, Integer[] purchaseNum) {
		
		Order order = new Order();
		List<Order> allOrder = orderD.findAll();
		int orderId = allOrder.size() + 1;
		
		order.setOrderId(orderId);
		order.setUserId(Integer.parseInt(userId));
		order.setAddressId(Integer.parseInt(addressId));
		
		String goodsIdStr = "";
		String goodsNumStr = "";
		Integer totalNum = 0;
		for(int i = 0; i < goodsId.length; i++) {
			if(i == goodsId.length - 1) {
				goodsIdStr += goodsId[i].toString();
				goodsNumStr += purchaseNum[i].toString();
			}else {	
				goodsIdStr += goodsId[i].toString() + ",";
				goodsNumStr += purchaseNum[i].toString() + ",";
			}
			
			Pc pc = pcD.findByPcId(goodsId[i]);
			int m = pc.getPcPrice().intValue();
			totalNum += m*purchaseNum[i];
			
		}
		order.setProductId(goodsIdStr);
		order.setProductNumber(goodsNumStr);
		
		orderD.save(order);
		
		JSONObject data = new JSONObject(true);
		data.put("orderId", order.getOrderId());
		data.put("totalAmount", totalNum);
		return conJ(data, true);
	}

	//删除订单
	public JSONObject deleteOrder(String orderId) {
		JSONObject res = new JSONObject(true);
	    orderD.deleteByOrderId(Integer.parseInt(orderId));
		
		return conJ(res,true);
	}
	
	//支付订单
	public JSONObject payForOrder(Integer userId,Integer orderId, String paykey) {
		JSONObject data = new JSONObject(true);
		if(userD.findByUserId(userId).getUserPayKey().equals(paykey)) {
			System.out.println("密码正确，开始计算");
			Order order = orderD.findByOrderId(orderId);
			String [] productNum = order.getProductNumber().split(",");
			String [] productId = order.getProductNumber().split(",");
			Integer [] id = new Integer[productNum.length];
			Integer [] num = new Integer[productNum.length];
			
			for(int i = 0; i < productNum.length;i++) {
				id[i] = Integer.parseInt(productId[i]);
				num[i] = Integer.parseInt(productNum[i]);
			}
			int totalNum = getTotalMoney(id, num);
			
			User u = userD.findByUserId(userId);
			data.put("balance", u.getUserMoney() - totalNum);
			u.setUserMoney(u.getUserMoney() - totalNum);
			userD.save(u);
		}
		return conJ(data,true);
	}
	
	public int getTotalMoney(Integer[] goodsId, Integer [] num) {
		int totalNum = 0;
		for(int i = 0; i < goodsId.length; i++) {
			Pc pc = pcD.findByPcId(goodsId[i]);
			int m = pc.getPcPrice().intValue();
			totalNum += m*num[i];
		}
		return totalNum;
	}
	
	public JSONObject getOrderList(Integer userId,Integer pageSize, Integer currentPage) {
		//得到这个人所有的订单
		List<Order> list = orderD.findByUserId(userId);
		JSONObject data = new JSONObject();
		JSONArray orderList = new JSONArray();
		
		if(list.size() == 0) {
			data.put("orderList", orderList);
			return conJ(data, true);
		}
		int begin = currentPage * pageSize;
		int end = begin + pageSize;
		if(begin > list.size() -1) {
			data.put("orderList", orderList);
			return conJ(data, true);
		}
		
		
		for(int i = begin; i < end; i ++) {
			//得到相应的订单
			if(i > list.size() - 1)
				break;
			JSONObject o = new JSONObject(true);
			
			Order order = list.get(i);
			String [] idStrArray = order.getProductId().split(",");
			String [] numStrArray = order.getProductNumber().split(",");
			int l = idStrArray.length;
			Integer [] idInt = new Integer[idStrArray.length];
			Integer [] numInt = new Integer[numStrArray.length];
			for(int k = 0 ; k < l;k++) {
				idInt[k] = Integer.parseInt(idStrArray[k]);
				numInt[k] = Integer.parseInt(numStrArray[k]);
				
			}
			
			int totalAmount = getTotalMoney(idInt,numInt);
			
			int totalNum = 0;
			for(int j = 0; j < numStrArray.length;j++) {
				totalNum += numInt[j];
			}
			
			JSONArray goodsListArray = new JSONArray();
			for(int j = 0; j < l;j++) {
				JSONObject obj = new JSONObject();
				Pc tempPc = pcD.findByPcId(idInt[j]);
				obj.put("goodsid", tempPc.getPcId());
				obj.put("goodsName", tempPc.getPcName());
				obj.put("purchaseNum", numInt[j]);
				obj.put("unitPrice", tempPc.getPcPrice().intValue());
				obj.put("tsotalPrice", tempPc.getPcPrice().intValue() * numInt[j]);
				obj.put("discountPrice", 0);
				obj.put("masterImg",AppConfi.getAddress()+ tempPc.getPcPicture().split(",")[0]);
				goodsListArray.add(obj);
			}
			o.put("orderId", order.getOrderId());
			o.put("totalAmount", totalAmount);
			o.put("totalNum", totalNum);
			o.put("goodsList", goodsListArray);
			o.put("paymentState", "SUCCESSED");
			orderList.add(o);
		}
		data.put("orderList", orderList);
		
		return conJ(data,true);
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
