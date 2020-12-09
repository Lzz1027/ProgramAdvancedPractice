package com.team.PCStore.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.PCStore.Entity.Order;

public interface OrderDao extends JpaRepository<Order,Integer> {
	public Order deleteByOrderId(Integer id);
	public Order findByOrderId(Integer id);
	public List<Order> findByUserId(Integer id);
}
