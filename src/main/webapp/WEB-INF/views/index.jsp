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

<%--<script src="${pageContext.request.contextPath}/resources/js/controller/mainController.js?v=${version}"></script>--%>
<script src="${pageContext.request.contextPath}/resources/js/controller/userController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/assetMgController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/assetChange.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/settingController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/printController.js?v=${version}"></script>

	<!--thefacotry controller-->
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/mainController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/homeController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/codeController.js?v=${version}"></script>


<link rel="shortcut icon" type="image/x-ic on" href="#">
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
		<nav class="navbar navbar-dark bg-inverse navbar-fixed-top d-flex justify-content-between" style="color: white;width:100%;height:35px;z-index:3;background-color:#484848;padding:0 60px;position: fixed;">
			<i class="xi-box" style="color: white;font-size: small"> THEFACTOR2 재고관리</i>
            <!-- Top Menu Items -->
           	<ul class="navbar-nav flex-row ml-md-auto d-md-flex " ng-show="authenticated">
				<li class="nav-item" >
					<a class="nav-link" href='' ng-click="goUserInfo()" style="margin-right:20px;"><i class="xi-profile" aria-hidden="true"></i> 사용자정보 </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" ng-click="logout()"><i class="xi-log-out" aria-hidden="true"></i> 로그아웃</a>
				</li>
			</ul>
        </nav>



		<nav class="navbar navbar-fixed-top d-flex dropdown" style="width:100%;background-color:white; border-bottom:5px solid #DCDCDC;z-index:3;position: fixed;top:30px;" ng-show="authenticated">
			<div class="p-2">
				<a href="" ng-click="goMain()"><img src="/resources/img/ci/top-logo.png" style="height: 60px;"></a>
			</div>

			<!--top메뉴 -->
			<div class="p-2 d-flex">
				<div class="navbar-header">
					<a href="">시스템관리</a>
				</div>
				<div class="navbar-header">
					<a href="" ng-click="goMenu('sample/codeList')">기초정보관리</a>
				</div>
				<div class="navbar-header">
					<a href="">입출고관리</a>
				</div>
				<div class="navbar-header">
					<a href="" >재고실사</a>
				</div>
				<div class="navbar-header">
					<a href="" ng-click="goAssetManagementList()">재고현황</a>
				</div>

				<div class="dropdown-content">

				</div>

<%--				<div class="navbar-header" ng-repeat="value in topMenu.parent">--%>
<%--					<a href=""> {{value.menu_name}} </a>--%>
<%--				</div>--%>

<%--				<div class="dropdown-content">--%>
<%--					<div  class="dropdown-board" ng-repeat="value in topMenu.parent">--%>
<%--						<span>{{value.menu_name}}</span>--%>
<%--						<a href="" ng-repeat="subValue in value.child.parent" ng-click="goPage(subValue)">{{subValue.menu_name}}</a>--%>
<%--					</div>--%>
<%--				</div>--%>
			</div>

			<div class="ml-auto p-2" id="menuToggle" ng-show="authenticated"><button class="btn" ng-click="menuToggle()" style="background: white;"><i class="xi-list"></i></button></div>
		</nav>

		<div style="height:120px;" ng-if="authenticated" ></div>

		<!--메인TOP-->
		<div style="height:500px;" ng-if="authenticated && mainPage">
			<div style="overflow: hidden;"><img src="/resources/img/다운로드 (1).png" style="width:100%;min-width:1150px;" draggable="false"></div>
		</div>



		<div id="page-wrapper" class="d-flex justify-content-left" ng-show="authenticated" >

			<!--사이드바-->
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




			<div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;padding: 10px;">
				<!-- left body -->
				<div class="container-fluid body-custom" style="width:20%;margin:3px;padding:10px;" ng-show="!mainPage">
					<!--메뉴-->
					<br>
					<div class="d-flex justify-content-between" style="height:65px;padding:10px;border-bottom:1px solid gray;">
						<p style="margin: 8px 0 5px 0">매장</p>
						<select class="form-control" style="width: 180px;">
							<option>논현점</option>
							<option>압구정점</option>
						</select>
					</div>


					<br>
					<div class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:1px solid gray;" ng-click="st2 = st2==true ? false : true">
						<p style="margin: 8px 50px 5px 0;">브랜드 선택</p>
						<i style="margin: 8px;" class="xi-angle-down" ng-show="!st2"></i>
						<i style="margin: 8px;" class="xi-angle-up" ng-show="st2"></i>
					</div>
					<div style="box-shadow: 1px 1px 3px gray;padding:15px;overflow: auto;height:200px;" ng-show="st2">


						<select multiple class="form-control" style="height: 100%;">
							<option>CHANEL</option>
							<option>ACNE STUDIOS</option>
							<option>ALEXANDER MCQUEEN</option>
							<option>BALENCIAGA</option>
							<option>LOUIS</option>
							<option>CHRISTIAN LOUBOUTIN</option>
							<option>COMME DES GARCONS</option>
							<option>BOTTEGA VENETA</option>
							<option>ISABEL MARANT</option>
							<option>ISSEY MIYAKE</option>
							<option>ISABEL MARANT</option>
							<option>ISSEY MIYAKE</option>

						</select>
					</div>

					<br>
					<div class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:1px solid gray;" ng-click="st3 = st3==true ? false : true">
						<p style="margin: 8px 50px 5px 0;">조회 날짜</p>
						<i style="margin: 8px;" class="xi-angle-down" ng-show="!st3"></i>
						<i style="margin: 8px;" class="xi-angle-up" ng-show="st3"></i>
					</div>
					<div style="box-shadow: 1px 1px 3px gray;padding:15px;" ng-show="st3">

						<div class="row input-group" style="width:250px;margin:5px;"> 시작일
							<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="st3_sdt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
							<span class="input-group-append" >
								<button type="button" class="btn btn-secondary" ng-click="st3_sdt = st3_sdt==true ? false : true"> <i class="xi-calendar"></i></button>
							</span>
						</div>

						<div class="row input-group" style="width:250px;margin:5px;"> 종료일
							<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="st3_edt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
							<span class="input-group-append" >
								<button type="button" class="btn btn-secondary" ng-click="st3_edt = st3_edt==true ? false : true"> <i class="xi-calendar"></i></button>
							</span>
						</div>
					</div>


					<br>
					<div  class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:1px solid gray;" ng-click="st4 = st4==true ? false : true">
						<p style="margin: 8px 50px 5px 0;">성별</p>
						<i style="margin: 8px;" class="xi-angle-down" ng-show="!st4"></i>
						<i style="margin: 8px;" class="xi-angle-up" ng-show="st4"></i>
					</div>
					<div class="form-group" style="box-shadow: 1px 1px 3px gray;padding:15px;" ng-show="st4">

						<table class="table-bordered" style="width:100%;text-align: center;height: 50px;">

							<tr style="margin:10px;">
								<td style="width:33.3%;padding:10px;">전체</td>
								<td style="width:33.3%;">남성</td>
								<td style="width:33.3%;">여성</td>
							</tr>
						</table>
					</div>


					<br>
					<div  class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:1px solid gray;" ng-click="st5 = st5==true ? false : true">

						<p style="margin: 8px 50px 5px 0;">상품분류</p>
						<i style="margin: 8px;" class="xi-angle-down" ng-show="!st5"></i>
						<i style="margin: 8px;" class="xi-angle-up" ng-show="st5"></i>
					</div>
					<div class="form-group" style="box-shadow: 1px 1px 3px gray;padding:15px;" ng-show="st5">
						<table class="table-bordered" style="width:100%;text-align: center;height: 50px;">

							<tr style="margin:10px;">
								<td style="width:33.3%;padding:10px;">아우터</td>
								<td style="width:33.3%;">상의</td>
								<td style="width:33.3%;">하의</td>
							</tr>
							<tr style="margin:10px;">
								<td style="width:33.3%;padding:10px;">슈즈</td>
								<td style="width:33.3%;">가방</td>
								<td style="width:33.3%;">지갑</td>
							</tr>
						</table>
					</div>


					<br><br>
					<div  class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:3px solid gray;background: whitesmoke;">

						<p style="margin: 8px 50px 5px 0;">재고현황</p>

					</div>
					<div class="form-group">
						<table style="width:100%;text-align: center;height: 50px;">

							<tr style="margin:10px;">
								<td style="width:33.3%;padding:5px;">입고</td>
								<td style="width:33.3%;">출고</td>
								<td style="width:33.3%;">재고</td>
							</tr>
							<tr style="margin:10px;">
								<td style="width:33.3%;padding:15px;">15</td>
								<td style="width:33.3%;border-left: 1px solid lightgray;border-right: 1px solid gray;">3</td>
								<td style="width:33.3%;">3809</td>
							</tr>
						</table>
					</div>

				</div>

				<!--right body-->
				<div style="width:80%;padding: 10px;">

					<!--현재 메뉴명 : 메인페이지에서 숨김-->
					<div class="container-fluid d-flex justify-content-center" style="padding:0!important;" ng-show="!mainPage">
						<div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;">
							<h4><a class="menuName" href="" ng-click="reload()">매장재고현황</a></h4>
							<h6 style="color:gray;">재고현황조회</h6>
						</div>
						<ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;">
							<li><a href="#">재고현황</a></li> &nbsp;>&nbsp;
							<li><a href="#">재고현황조회</a></li>
						</ol>
					</div>

					<!--상단 검색 보드-->
					<div class="d-flex container-fluid body-custom flex-column" style="width:100%;min-height: 150px;padding:5px 5%;">
						<div style="font-size:20px;margin: 5px;">Quick Search</div>
						<div class=" d-flex" style="width: 100%;border:1px solid lightgray;border-radius: 10px;padding:10px;">
							<select class="form-control" style="width:200px;margin: 10px;height: 45px;">
								<option>매장</option>
							</select>
							<select class="form-control" style="width:200px;margin: 10px;height: 45px;">
								<option>브랜드</option>
							</select>
							<select class="form-control" style="width:150px;margin: 10px;height: 45px;">
								<option>성별</option>
							</select>
							<select class="form-control" style="width:200px;margin: 10px;height: 45px;">
								<option>상품분류</option>
							</select>
							<button class="btn btn-outline-secondary" style="width:70px;margin:10px 30px;">검색</button>
							<div class="d-flex" style="width:230px;margin: 10px;">
								<input type="text" class="form-control" style="height: 45px;border:0;border-bottom: 1px solid gray;">
								<button class="btn" style="position:relative;left:-40px;background: transparent;">
									<i class="xi-search" style="font-size: 20px;"></i>
								</button>
							</div>

						</div>

					</div>


					<!--contents-->
					<div ng-view ></div>
				</div>
			</div>
		</div>


		<!--login 페이지-->
		<div ng-show="!authenticated" ng-view></div>

		<!--footer-->
		<div class="footer d-flex justify-content-center" ng-show="authenticated" style="width:100%;padding: 15px;">
			<div style="padding: 5px 70px 0 0;">
				<img src="/resources/img/ci/top-logo.png" style="width:140px;height:35px;">
			</div>
			<div class="footer-text">
				<span>더팩토리 서울특별시 강남구 봉은사로7길 40 201호</span>
				<span>대표 :이예림사업자등록번호 :214-13-55201 사업자번호조회 >통신판매업신고번호 :제 2017-서울강남-00071호개인정보관리자 :이예림</span>
				<span>대표번호 :02-6407-7879팩스번호 :메일 :thefactor2@naver.com</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
	</body>
</html>
