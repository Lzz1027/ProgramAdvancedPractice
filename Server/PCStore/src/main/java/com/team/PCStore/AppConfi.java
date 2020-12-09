package com.team.PCStore;

public class AppConfi {
	private static String token = "yanqiandeheibushihei,nishuodebaishishenmbai";
	private static int ID = 1;
	private static int successCode = 10000;
	private static String address = "http://172.26.202.195:8081/";
	public static int getId() {
		int oldID = ID;
		ID ++;
		return oldID;
	}
	public static String getToken() {
		return token;
	}
	
	public static int getSuccessCode() {
		return successCode;
	}
	public static String getAddress() {
		return address;
	}
	
}
