package com.cos.security1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cos.security1.domain.CustomerCenter;


public interface CustomerCenterRepository extends JpaRepository<CustomerCenter, Integer>{

	public CustomerCenter findById(Long id);
	
	public CustomerCenter findByUsername(String username);
	
	
	@Transactional
	public void deleteById(Long id);
}
