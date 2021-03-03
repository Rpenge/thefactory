<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<div class="starter-template">
	<div class="container-fluid body-custom">
		<div class="body-contents" style="width: 600px; margin: 0 auto;">
			<h2><a class="menuName" href="" ng-click="reload()">가입신청</a></h2><br>
			
			<div class="custom-shadow-box">
			<form class="was-validated">
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">아이디</span>
					<input type="text" ng-model="regData.userId" class="form-control col-4" required>
				</div>
				
				<div class="input-group mb-1">
					<span class="input-group-text input-group-prepend col-3">비밀번호</span>
					<input type="password" ng-model="regData.password" class="form-control col-4" required>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">비밀번호 확인</span>
					<input type="password" ng-model="regData.passwordC" class="form-control col-4" required>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">사원번호</span>
					<input type="text" ng-model="regData.empNo" class="form-control col-4">
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">이름</span>
					<input type="text" ng-model="regData.empName" class="form-control col-4" required>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">이메일</span>
					<input type="text" ng-model="regData.email" class="form-control col-6">
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
					<select class="custom-select col-4" ng-model="regData.company">
						<option value=''>회사선택</option>
				    	<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='CPN'">{{list.codeName}}</option>
				    </select>
				</div>
				
				<div class="input-group mb-4">
					<span class="input-group-text input-group-prepend col-3">부서</span>
					<select class="custom-select col-4" ng-model="regData.dept"  >
						<option value=''>부서선택</option>
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
   				<div>
					<button ng-click="submit()" class="btn btn-success col-3" style="margin-right:15px;">가입</button>
					<button ng-click="goLogin()" class="btn btn-danger col-3">돌아가기</button>
			    </div>
		    </form>
		    </div>
	    </div>
	</div>
</div>
</html>