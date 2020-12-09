package com.team.PCStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.AppConfi;
import com.team.PCStore.Dao.AddressDao;
import com.team.PCStore.Entity.Address;

@Service
@Transactional
public class AddressService {
	@Autowired
	private AddressDao addD;
	
	public JSONObject addNewAddress(String userId, String detAddress,
									String comAddress,String provinceStr,
									String cityStr,String areaStr,
									String phone, String name) {
		JSONObject data = new JSONObject();
		Address add = new Address();
		List<Address> allAddress = addD.findAll();
		int addressId = allAddress.size()+1;
		
		add.setAddressId(addressId);
		add.setUserId(Integer.parseInt(userId));
		add.setAddressDetailed(detAddress);
		add.setAddressCompleted(comAddress);
		add.setAddressProvince(provinceStr);
		add.setAddressCity(cityStr);
		add.setAddressArea(areaStr);
		add.setPhone(phone);
		add.setName(name);
		addD.save(add);
		
		return conJ(data, true);
	}
	
	public JSONObject deleteAddress(String addressId) {
		addD.deleteById(Integer.parseInt(addressId));
		
		JSONObject data = new JSONObject(true);
		
		return conJ(data, true);
	}
	
	public JSONObject changeAddress(Integer addressId, String userId,
									String detAddress,String comAddress,
									String provinceLabel, String cityLabel, String areaLabel,
									String phone, String name) {
		//找到对应的地址
		Address add = addD.findByAddressId(addressId);
		add.setUserId(Integer.parseInt(userId));
		add.setAddressDetailed(detAddress);
		add.setAddressCompleted(comAddress);
		add.setAddressProvince(provinceLabel);
		add.setAddressCity(cityLabel);
		add.setAddressArea(areaLabel);
		add.setPhone(phone);
		add.setName(name);
		addD.save(add);
		
		JSONObject data = new JSONObject(true);
		
		return conJ(data,true);
	}
	
	public JSONObject getAddress(String userId) {
		List<Address> addressList = addD.findByUserId(Integer.parseInt(userId));
		JSONObject data = new JSONObject(true);
		JSONArray addListArray = new JSONArray();
		for(int i = 0 ;i < addressList.size(); i++) {
			JSONObject temp = new JSONObject(true);
			Address a = addressList.get(i);
			String id = a.getAddressId().toString();
			JSONObject addTable = new JSONObject(true);
			addTable.put("completedAddress", a.getAddressCompleted());
			addTable.put("detailedAddress", a.getAddressDetailed());
			addTable.put("name", a.getName());
			addTable.put("phone", a.getPhone());
			temp.put("addressId", id);
			temp.put("addressTable", addTable);
			addListArray.add(temp);
		}
		data.put("addressList", addListArray);
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
