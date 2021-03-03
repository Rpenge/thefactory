<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<div class="starter-template" ng-show="authenticated">
	<div class="container-fluid body-custom">
		<div class="body-contents" style="width: 800px;margin: 0 auto;">
			<h2><a class="menuName" href="" ng-click="reload()">사용자 정보 변경</a></h2><br>
			<div class="custom-shadow-box">
			<form>
				<div class="input-group mb-4">
					<span class="input-group-text align-middle input-group-prepend col-3">아이디</span>
					<input type="text" ng-model="regData.userId" class="form-control col-md-4" readOnly>
				</div>
				
				<div class="input-group mb-1">
					<span class="input-group-text input-group-prepend col-3">비밀번호 변경</span>
					<input type="password" ng-model="regData.password" class="form-control col-md-4" >
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">비밀번호 확인</span>
					<input type="password" ng-model="regData.passwordC" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">사원번호</span>
					<input type="text" ng-model="regData.empNo" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">이름</span>
					<input type="text" ng-model="regData.empName" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">이메일</span>
					<input type="text" ng-model="regData.email" class="form-control col-5">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">휴대폰 번호</span>
					<input type="text" ng-model="regData.empPhone" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">전화번호</span>
					<input type="text" ng-model="regData.empContact" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">회사</span>
					<select class="custom-select col-4" ng-model="regData.company"  >
				    	<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='CPN'">{{list.codeName}}</option>
				    </select>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">부서</span>
					<select class="custom-select col-4" ng-model="regData.dept"  >
				    	<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
				    </select>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">직책</span>
					<input type="text" ng-model="regData.position" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">근무지</span>
					<input type="text" ng-model="regData.location" class="form-control col-4">
				</div>
   				<hr>
			    <button ng-click="submit()" class="btn btn-primary col-3">변경</button>
		    </form>
		    </div>
	    </div>
	</div>
</div>
</html>