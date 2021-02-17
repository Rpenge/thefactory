package com.systemk.ams.Repository.Main.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.systemk.ams.Entity.Main.AssetChange;



public class assetChangeSpecification {

	public static Specification<AssetChange> isNotEmpty(){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.notEqual(root.get("astCtrlCd"), "");
			}
		};
	}
	
	public static Specification<AssetChange> changeDateBetween(final Date startDate, final Date endDate){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.between(root.get("chgDt"), startDate, endDate);
			}
		};
	}
	
	public static Specification<AssetChange> assetStatusEqual(final String status){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("astSt"), status);
			}
		};
	}
	
	public static Specification<AssetChange> assetWorkEqual(final String work){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("astWk"), work);
			}
		};
	}
	
	public static Specification<AssetChange> workLocationEqual(final String location){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("wkLoc"), location);
			}
		};
	}
	
	public static Specification<AssetChange> assetDiv(final String div){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("astDiv"), div);
			}
		};
	}
	
	public static Specification<AssetChange> assetDept(final String dept){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("astDp"), dept);
			}
		};
	}
	
	public static Specification<AssetChange> wealthYn(final String wealth){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("wiYn"), wealth);
			}
		};
	}
	
	public static Specification<AssetChange> workEnv(final String env){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("wkEnv"), env);
			}
		};
	}
	
	public static Specification<AssetChange> search(final String option, final String dic){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get(option), "%"+dic+"%");
			}
		};
	}
	
	public static Specification<AssetChange> updateYn(final String update){
		return new Specification<AssetChange>() {
			@Override
			public Predicate toPredicate(Root<AssetChange> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("updateYn"), update);
			}
		};
	}


}
