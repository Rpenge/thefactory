package com.systemk.ams.Repository.Main;



import org.springframework.data.jpa.repository.JpaRepository;

import com.systemk.ams.Entity.Main.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{


	
	public Company findByCpnCd(String cpnCd);
}
