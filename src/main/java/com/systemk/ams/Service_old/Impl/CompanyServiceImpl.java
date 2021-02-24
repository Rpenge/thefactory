package com.systemk.ams.Service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.systemk.ams.Entity.Main.Company;
import com.systemk.ams.Repository.Main.CompanyRepository;
import com.systemk.ams.Service.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Page<Company> findCompany(Pageable pageable) {
		return companyRepository.findAll(pageable);
	}

}
