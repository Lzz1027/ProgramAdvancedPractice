package com.team.PCStore.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.PCStore.Entity.User;


public interface UserDao extends JpaRepository<User,Integer>{
	
	public User findByUserId(Integer id);
	
	public User findByUserEmail(String email);
	
	public User findByUserName(String name);	
}
