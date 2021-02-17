package com.systemk.ams.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.systemk.ams.Entity.Main.Company;

public interface CompanyService {

	Page<Company> findCompany(Pageable pageable);

	

}
