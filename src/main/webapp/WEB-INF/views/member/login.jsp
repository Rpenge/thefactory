<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
	<div class="login-background" style="background-image:url('/resources/img/img_1 (1).png');background-size:100vw;height:97vh;background-color: #484848;background-repeat:no-repeat;-ms-overflow-style: none;">
		<div class="container-fluid" >
<%--			<div style="background:white;width: 98%;height:80vh;opacity: 50%;position:fixed;"></div>--%>
			<div class="form-signin" style="opacity: 95%;margin-top: 100px;">
				<img src="/resources/img/ci/top-logo.png" style="width:100%; auto;">
				<form role="form" ng-submit="login()" style="margin-bottom: 5px;">
					<label for="j_username" class="sr-only">아이디</label>
					<input type="text" class="form-control" ng-model="credentials.userId" placeholder="아이디" name="j_username" id="j_username" required autofocus>
					<label for="j_password" class="sr-only">비밀번호</label>
					<input type="password" class="form-control" ng-model="credentials.userPw" placeholder="비밀번호" name="j_password" id="j_password" required>
					<button type="submit" class="btn btn-lg btn-secondary btn-block">로그인</button>
				</form>
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
		<div class="footer d-flex justify-content-center" style="width:100%;padding: 10px;height: auto;position: fixed; bottom: 0px;">
			<div class="footer-text">
				<span>더팩토리 서울특별시 강남구 봉은사로7길 40 201호</span>
				<span>대표 :이예림사업자등록번호 :214-13-55201 사업자번호조회 >통신판매업신고번호 :제 2017-서울강남-00071호개인정보관리자 :이예림</span>
				<span>대표번호 :02-6407-7879팩스번호 :메일 :thefactor2@naver.com</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
	</div>

</html>
