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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.team.PCStore.Service.AddressService;

@CrossOrigin
@RestController
@RequestMapping("user")
public class AddressController {
	@Autowired
	private AddressService addS;
	
	//创建一个新的地址
	@PostMapping("/address")
	@ResponseBody
	public JSONObject addNewAdd(@RequestBody Map<String, Object> m) {
		String userId = (String) m.get("userId");
		String name = (String)m.get("name");
		String phone = (String)m.get("phone");
		String detAddress = (String)m.get("detailedAddress");
		String comAddress = (String)m.get("completedAddress");
		Map<String, Object> addTable = (Map<String, Object>)m.get("addressTable");
		
		Map<String, Object> province = (Map<String, Object>)addTable.get("province");
		Map<String, Object> city = (Map<String, Object>)addTable.get("city");
		Map<String, Object> area = (Map<String, Object>)addTable.get("area");
		
		String provinceStr = (String) province.get("value");
		String provinceLabel = (String) province.get("label");
		
		String cityStr = (String) city.get("value");
		String cityLabel = (String) city.get("label");
		
		String areaStr = (String) area.get("value");
		String areaLabel = (String) area.get("label");
		
		JSONObject res = addS.addNewAddress(userId,detAddress,comAddress,provinceLabel,cityLabel,areaLabel,phone,name);
		
		return res;
	}
	
	//删除地址
	@DeleteMapping("/address")
	@ResponseBody
	public JSONObject deleteAddress(@RequestBody Map<String, Object> map) {
		String addressId = (String) map.get("addressId");
		
		return addS.deleteAddress(addressId);
	}
	
	//修改地址
	@PutMapping("/address")
	@ResponseBody
	public JSONObject changeAddress(@RequestBody Map<String, Object> m) {
		Integer addressId = (Integer) m.get("addressId");
		String userId = (String) m.get("userId");
		String name = (String)m.get("name");
		String phone = (String)m.get("phone");
		String detAddress = (String)m.get("detailedAddress");
		String comAddress = (String)m.get("completedAddress");
		Map<String, Object> addTable = (Map<String, Object>)m.get("addressTable");
		
		Map<String, Object> province = (Map<String, Object>)addTable.get("province");
		Map<String, Object> city = (Map<String, Object>)addTable.get("city");
		Map<String, Object> area = (Map<String, Object>)addTable.get("area");
		
		String provinceStr = (String) province.get("value");
		String provinceLabel = (String) province.get("label");
		
		String cityStr = (String) city.get("value");
		String cityLabel = (String) city.get("label");
		
		String areaStr = (String) area.get("value");
		String areaLabel = (String) area.get("label");
	
		
		return addS.changeAddress(addressId,userId,detAddress,comAddress,provinceLabel,cityLabel,areaLabel,phone,name);
		
	}
	
	//获取地址
	@GetMapping("/address/{userId}")
	@ResponseBody
	public JSONObject getAddress(@PathVariable String userId) {
		return addS.getAddress(userId);
	}
}
