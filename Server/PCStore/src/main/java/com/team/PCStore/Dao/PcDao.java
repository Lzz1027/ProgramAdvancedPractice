package com.team.PCStore.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.PCStore.Entity.Pc;

public interface PcDao extends JpaRepository<Pc,Integer>{
	
	public Pc findByPcId(Integer id);

}
