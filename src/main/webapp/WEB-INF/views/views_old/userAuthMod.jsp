<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<div>
		<div class="container-fluid">
			<div style="margin: 15px;">
				<h2 class="form-signin-heading" style="border-bottom:1px solid black; padding:10px;margin-bottom:20px;font-weight:bold;">회원정보 수정</h2>
				<form>
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">아이디</span>
						<input type="text" ng-model="regData.userId" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">사원번호</span>
						<input type="text" ng-model="regData.empNo" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">이름</span>
						<input type="text" ng-model="regData.empName" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">가입일</span>
						<input type="text" ng-model="regData.userRegDate" uib-datepicker-popup="{{format}}" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">이메일</span>
						<input type="text" ng-model="regData.email" class="form-control col-md-8" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">휴대폰 번호</span>
						<input type="text" ng-model="regData.empPhone" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">전화번호</span>
						<input type="text" ng-model="regData.empContact" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">회사</span>
						<input type="text" ng-model="regData.company" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">직책</span>
						<input type="text" ng-model="regData.position" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">근무지</span>
						<input type="text" ng-model="regData.location" class="form-control col-md-5" readOnly>
					</div>
					
					<div class="input-group mb-2">
						<span class="input-group-text input-group-prepend col-md-3">권한수정</span>
						<select class="custom-select col-md-5"  ng-model="regData.role" required>
				    		<option value="">권한 선택</option>
				      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='ROLE'" >{{list.codeName}}</option>
				    	</select>
					</div>
    				
				    <button ng-click="submit()" class="btn btn-primary col-4">수정</button>
				    <button ng-click="close()" class="btn btn-danger col-4">취소</button>
			    </form>
		    </div>
		</div>
	</div>
</html>