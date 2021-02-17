package com.systemk.ams.Repository.my;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.Main.MenuTable;


public interface MenuRepository extends JpaRepository<MenuTable, Long>{

	public List<MenuTable> findByRole(String role);
}
