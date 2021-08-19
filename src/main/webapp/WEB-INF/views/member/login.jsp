<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<%--	<div class="login-background" style="background-image:url('{{imgPath}}/img/loginImg.png');background-size:100vw;height:100vh;background-color: #484848;background-repeat:no-repeat;-ms-overflow-style: none;">--%>
<div class="login-background" style=" background-size:100vw;height:100vh;background-color: #484848;-ms-overflow-style: none;">
	<img ng-src="{{imgPath}}/img/loginImg.png" style="position: fixed;width:100vw;min-width:1920px;">
		<div class="container-fluid" >
			<div class="form-signin" style="opacity: 95%;margin-top: 150px;width:380px;">
				<img ng-src="{{imgPath}}/img/ci/s_logo.png" style="width:100%;margin-bottom: 30px;">
				<form role="form" ng-submit="login()" style="margin-bottom: 5px;">
					<input type="text" class="form-control" ng-model="credentials.userId" placeholder="아이디" required autofocus>
					<input type="password" class="form-control" ng-model="credentials.userPw" placeholder="비밀번호" required>
					<button type="submit" class="btn btn-lg btn-secondary btn-block">로그인</button>
				</form>
				<div class="text-center alert alert-danger" ng-show="error == 'Bad credentials'" style="padding:10px 0;">
					패스워드를 확인하여 다시 시도해주세요.
				</div>
				<div class="text-center alert alert-danger" ng-show="error == '3000'" style="padding:10px 0;">
					등록된 사용자가 아닙니다.
				</div>
				<div class="text-center alert alert-danger" ng-show="error == '3001'" style="padding:10px 0;">
					사용 중지된 사용자입니다.
				</div>

				<div style="position:relative;left:3px;top:5px;font-size:14px;">
					<input type="checkbox" ng-model="autoLogin" style="transform: scale(1.2);"> 자동로그인
					<input type="checkbox" ng-model="idSaveCheck" style="margin-left:120px;transform: scale(1.2);"> 아이디 저장
				</div>
			</div>
		</div>
		<div class="footer d-flex justify-content-center" style="width:100%;padding: 10px;height: auto;position: fixed; bottom: 0px;">
			<div class="footer-text" ng-if="systemk != true">
				<span>더팩토리 서울특별시 강남구 봉은사로7길 40 201호</span>
				<span>대표 :이예림사업자등록번호 :214-13-55201 사업자번호조회 >통신판매업신고번호 :제 2017-서울강남-00071호개인정보관리자 :이예림</span>
				<span>대표번호 :02-6407-7879팩스번호 :메일 :thefactor2@naver.com</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
			<div class="footer-text" ng-if="systemk == true">
				<span>(주)시스템케이 (SYSTEMK Co., Ltd.)</span>
				<span>주소 : 경기도 구리시 갈매순환로 204번길 65, 구리스마트벤처타워 407호 408호</span>
				<span>전화 : 070-8830-5252팩스 : 031-571-5254</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
	</div>

</html>
