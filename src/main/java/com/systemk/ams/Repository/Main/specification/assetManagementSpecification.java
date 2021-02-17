package com.systemk.ams.Repository.Main.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.systemk.ams.Entity.Main.AssetManagement;



public class assetManagementSpecification {

	public static Specification<AssetManagement> assetOptionContain(final String option, final String text){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get(option), "%"+text+"%");
			}
		};
	}
	
	public static Specification<AssetManagement> assetStatusEqual(final String assetStatus){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("assetStatus"), assetStatus);
			}
		};
	}
	
	public static Specification<AssetManagement> assetRegDateBetween(final Date startDate, final Date endDate){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.between(root.get("assetRegDate"), startDate, endDate);
			}
		};
	}
	
	public static Specification<AssetManagement> assetDivisionEqual(final String assetDivision){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("assetDivision"), assetDivision);
			}
		};
	}
	
	public static Specification<AssetManagement> assetLocationEqual(final String assetLocation){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("assetLocation"), assetLocation);
			}
		};
	}
	
	public static Specification<AssetManagement> assetDeptEqual(final String assetDept){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("assetDept"), assetDept);
			}
		};
	}
	
	public static Specification<AssetManagement> TagMappingYn(final String mappingYn){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("mappingYn"), mappingYn);
			}
		};
	}
	
	public static Specification<AssetManagement> assetIsNotEmpty(){
		return new Specification<AssetManagement>() {
			@Override
			public Predicate toPredicate(Root<AssetManagement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.notEqual(root.get("assetControlCode"), "");
			}
		};
	}
}
