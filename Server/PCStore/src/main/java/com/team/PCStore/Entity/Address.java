package com.team.PCStore.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "addressId")
	private Integer addressId;
	
	@Column(name = "addressName")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "addressPhone")
	private String phone;
	
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "addressDetailed")
	private String addressDetailed;
	
	@Column(name = "addressCompleted")
	private String addressCompleted;
	
	@Column(name = "addressProvince")
	private String addressProvince;
	
	@Column(name = "address_city")
	private String addressCity;
	
	@Column(name = "addressArea")
	private String addressArea;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddressDetailed() {
		return addressDetailed;
	}

	public void setAddressDetailed(String addressDetailed) {
		this.addressDetailed = addressDetailed;
	}

	public String getAddressCompleted() {
		return addressCompleted;
	}

	public void setAddressCompleted(String addressCompleted) {
		this.addressCompleted = addressCompleted;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressArea() {
		return addressArea;
	}

	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	
}
