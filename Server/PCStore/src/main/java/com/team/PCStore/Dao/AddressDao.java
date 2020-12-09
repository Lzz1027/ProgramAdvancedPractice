package com.team.PCStore.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.PCStore.Entity.Address;

public interface AddressDao extends JpaRepository<Address,Integer>{
	
	public Address findByAddressId(Integer id);
	
	public List<Address> findByUserId(Integer id);
}
