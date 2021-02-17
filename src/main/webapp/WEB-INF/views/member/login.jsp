<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<div>
		<div class="login-background">
			<div class="container-fluid">
				<div class="form-signin">
					<!-- <img src="${pageContext.request.contextPath}/resources/img/ci/icon_nobland_logo.png" width="150" height="50" alt=""> -->

					<h2 class="form-signin-heading">로그인</h2>
					<form role="form" ng-submit="login()" style="margin-bottom: 5px;">
					    <label for="j_username" class="sr-only">아이디</label>
					    <input type="text" class="form-control" ng-model="credentials.userId" placeholder="아이디" name="j_username" id="j_username" required autofocus>
					    <label for="j_password" class="sr-only">비밀번호</label>
					    <input type="password" class="form-control" ng-model="credentials.userPw" placeholder="비밀번호" name="j_password" id="j_password" required>
					    <button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
				    </form>
				    <button class="btn btn-lg btn-block" ng-click="clickUserReg()">회원가입</button>
				    <div class="text-center alert alert-danger" ng-show="error == 'Bad credentials'">
						패스워드를 확인하여 다시 시도해주세요.
					</div>
					<div class="text-center alert alert-danger" ng-show="error == '3000'">
						등록된 사용자가 아닙니다.
					</div>
					<div class="text-center alert alert-danger" ng-show="error == '3001'">
						사용 중지된 사용자입니다.
					</div>
					<div class="text-center alert alert-danger" ng-show="error == '3002'">
						관리자 승인 이후에 사용하실 수 있습니다.
					</div>
			    </div>
			</div>
		</div>
	</div>
</html>
