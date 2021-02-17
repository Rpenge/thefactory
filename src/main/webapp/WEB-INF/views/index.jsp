<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp" ng-controller="indexController">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>RFID</title>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.slim.min.js"></script> --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-animate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-modal-service.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-cookies.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-sanitize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-file-saver.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-file-saver.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ngprogress.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/checklist-model.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular-tree-view.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ui-bootstrap-tpls-3.0.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/config/appInitConfig.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/config/routeProvider.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/filter/appFilter.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/directive/appDirective.js?v=${version}"></script>

<script src="${pageContext.request.contextPath}/resources/js/controller/mainController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/userController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/assetMgController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/assetChange.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/settingController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/printController.js?v=${version}"></script>

<link rel="shortcut icon" type="image/x-ic on" href="/resources/img/ci/sysk.png">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sb-admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/xeicon.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css?v=${version}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/metisMenu.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/angular-tree.css">

<style type="text/css">
[ng\:cloak], [ng-cloak], .ng-cloak {
	display: none !important;
}
</style>
</head>
<body class="ng-cloak ">

		<nav class="navbar navbar-dark bg-inverse navbar-fixed-top" style="width:100%;height:35px;z-index:1;background-color:white;border-bottom:1px solid #dcdcdc;padding:0 60px;">
            <!-- Top Menu Items -->
           	<ul class="navbar-nav flex-row ml-md-auto d-md-flex navbar-right" ng-show="authenticated">
				<li class="nav-item" >
					<a class="nav-link" href='' ng-click="goUserInfo()" style="margin-right:20px;"><i class="xi-profile" aria-hidden="true"></i> 사용자정보 </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#index" ng-click="logout()"><i class="xi-log-out" aria-hidden="true"></i> 로그아웃</a>
				</li>
			</ul>
        </nav>


		<nav class="navbar navbar-fixed-top d-flex dropdown" style="width:100%;background-color:white; border-bottom:5px solid #DCDCDC;" ng-show="authenticated">
			<div class="p-2">
				<img src="/resources/img/ci/top-logo.png" style="height: 60px;">
			</div>

			<!--top메뉴 -->
			<div class="p-2 d-flex">
				<div class="navbar-header" ng-repeat="value in topMenu.parent">
					<a href=""> {{value.menu_name}} </a>
				</div>

				<div class="dropdown-content">
					<div  class="dropdown-board" ng-repeat="value in topMenu.parent">
						<span>{{value.menu_name}}</span>
						<a href="" ng-repeat="subValue in value.child.parent" ng-click="goMenu(subValue)">{{subValue.menu_name}}</a>
					</div>
				</div>
			</div>

			<div class="ml-auto p-2" id="menuToggle" ng-show="authenticated"><button class="btn" ng-click="menuToggle()" ><i class="xi-list"></i></button></div>
		</nav>


		<!--사이드바-->
		<div id="page-wrapper" class="d-flex justify-content-left" ng-class="{'login-not-authenticated' : !authenticated}">
			<div>
				<nav class="text-light bg-dark sidebar" ng-init="menu" ng-show="menu && authenticated" > <!-- 첫화면 사이드 메뉴 안보이게 -->
					<div class="sidebar-sticky">
						<ul class="nav flex-column">
							<li class="nav-item" ng-show="authenticated && user.principal.role=='MT'"><a class="nav-link" href="" ng-click="sideMenuDock(0)"><i class="xi-user"></i> 사용자관리
								<i class='xi-angle-down' ng-show="dock[0]"></i><i class='xi-angle-up' ng-show="!dock[0]"></i></a>
								<ul class="nav sideBarDock" ng-show="dock[0]" >
									<li><a class="nav-link" href="" ng-click="goUserList()">사용자 관리</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="" ng-click="sideMenuDock(1)"><i class="xi-book"></i> 자산관리
								<i class='xi-angle-down' ng-show="dock[1]"></i><i class='xi-angle-up' ng-show="!dock[1]"></i></a>
								<ul class="nav sideBarDock" ng-show="dock[1]" >
									<li><a class="nav-link" href="" ng-click="goAssetManagementList()">자산 관리</a></li>
									<li><a class="nav-link" href="" ng-click="goMenu('assetManagement/assetPrintList')">자산 프린트</a></li>
									<li><a class="nav-link" href="" ng-click="goAssetStatusChange()">자산 상태 변경</a></li>
									<li><a class="nav-link" href="" ng-click="goMenu('assetManagement/assetRepair')">수리 목록</a></li>
									<li><a class="nav-link" href="" ng-click="goAssetDisList()">폐기자산 목록</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="" ng-click="sideMenuDock(2)"><i class="xi-library-books"></i> 자산정보조회
								<i class='xi-angle-down' ng-show="dock[2]"></i><i class='xi-angle-up' ng-show="!dock[2]"></i></a>
								<ul class="nav sideBarDock" ng-show="dock[2]" >
									<li><a class="nav-link" href="" ng-click="goRfidChange()">자산 내역 조회</a></li>
									<li><a class="nav-link" href="" ng-click="goRfidRegList()">등록 조회</a></li>
									<li><a class="nav-link" href="" ng-click="goRfidMoveList()">이동 조회</a></li>
									<li><a class="nav-link" href="" ng-click="goRfidWiList()">실사 조회</a></li>
									<li><a class="nav-link" href="" ng-click="goRfidDisList()">폐기 조회</a></li>
									<li><a class="nav-link" href="" ng-click="goRfidFailList()">전송 오류 조회</a></li>
								</ul>
							</li>
							<li class="nav-item"><a class="nav-link" href="" ng-click="sideMenuDock(3)"><i class="xi-cog"></i> 기초정보 관리
								<i class='xi-angle-down' ng-show="dock[3]"></i><i class='xi-angle-up' ng-show="!dock[3]"></i></a>
								<ul class="nav sideBarDock" ng-show="dock[3]" >
									<li><a class="nav-link" href="" ng-click="goCommonCode()">기초정보 관리</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</nav>
			</div>
			<!--본문 -->
			<div style="width:100%;background-color:#ecf0f5;" ng-view></div>

			<!--로딩바-->
			<div class="loading-spiner-holder" data-loading >
				<div class="loading-spiner">
					<img src="${pageContext.request.contextPath}/resources/img/icon/loading.gif" class="loading"/>
				</div>
			</div>
		</div>

		<!--footer-->
		<div class="footer d-flex justify-content-center" ng-show="authenticated" style="width:100%;padding: 15px;">
			<div style="padding: 5px 70px 0 0;">
				<img src="/resources/img/ci/s_logo.png" style="width:140px;height:35px;">
			</div>
			<div class="footer-text">
				<span>(주)시스템케이 (SYSTEMK Co., Ltd.)</span>
				<span>주소 : 경기도 구리시 갈매순환로 204번길 65, 구리스마트벤처타워 407호 408호</span>
				<span>전화 : 070-8830-5252팩스 : 031-571-5254</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
</body>
</html>
