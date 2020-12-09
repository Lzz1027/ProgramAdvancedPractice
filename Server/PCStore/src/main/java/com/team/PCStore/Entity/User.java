package com.team.PCStore.Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "userId")
	private Integer userId;
	
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Column(name = "userToken")
	private String userToken;
	
	@Column(name = "userName")
	private String userName;
	
	public String getUserPayKey() {
		return userPayKey;
	}

	public void setUserPayKey(String userPayKey) {
		this.userPayKey = userPayKey;
	}

	@Column(name = "userPayKey")
	private String userPayKey;
	
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserGender() {
		return userGender;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	public String getUserHearPic() {
		return userHearPic;
	}

	public void setUserHearPic(String userHearPic) {
		this.userHearPic = userHearPic;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String email) {
		this.userEmail = email;
	}

	@Column(name = "userPhone")
	private String userPhone;
	
	@Column(name = "userPassword")
	private String userPassword;
	
	@Column(name = "userGender")
	private Integer userGender;
	
	@Column(name = "userHeadPic")
	private String userHearPic;
	
	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "userMoney")
	private Integer userMoney;
	
	
	public Integer getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(Integer userMoney) {
		this.userMoney = userMoney;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public User(Integer userId, String userName, String userPayKey, String userPhone, String userPassword,
			Integer userGender, String userHearPic, String userEmail, Integer userMoney) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPayKey = userPayKey;
		this.userPhone = userPhone;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userHearPic = userHearPic;
		this.userEmail = userEmail;
		this.userMoney = userMoney;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

		public User() {
		super();
	}
	
	public User(Integer userId, String userName) {
		super();
		this.userName = userName;
	}
	
	
}
