package com.team.PCStore.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.PCStore.Entity.Cart;

public interface CartDao extends JpaRepository<Cart,Integer>{
	
	public List<Cart> findByUserId(Integer userId	); 
	
	public Cart findByUserIdAndProductId(Integer userId, Integer productId);
	
	public Cart deleteByCartId(Integer productId);

	public Cart findByProductId(Integer productId);
	
	
}
