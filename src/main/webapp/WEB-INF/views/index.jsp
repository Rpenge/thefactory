<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp" ng-controller="indexController">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>THEFACTOR2</title>

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

<%--<script src="${pageContext.request.contextPath}/resources/js/controller/indexController.js?v=${version}"></script>--%>
<script src="${pageContext.request.contextPath}/resources/js/controller/userController.js?v=${version}"></script>
<script src="${pageContext.request.contextPath}/resources/js/controller/assetMgController.js?v=${version}"></script>


	<!--thefacotry controller-->
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/indexController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/homeController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/codeController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/salesController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/ioInfoController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/inputController.js?v=${version}"></script>

<!--icon-->
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
		<nav class="navbar navbar-dark bg-inverse navbar-fixed-top d-flex justify-content-between" style="color: white;width:100%;height:35px;z-index:3;background-color:#484848;padding:0 60px;position: fixed;">
			<i class="xi-box" style="color: white;font-size: small"> THEFACTOR2 재고관리</i>
            <!-- Top Menu Items -->
           	<ul class="navbar-nav flex-row ml-md-auto d-md-flex " ng-if="authenticated">
				<li class="nav-item" >
					<a class="nav-link" href='' ng-click="goUserInfo()" style="margin-right:20px;"><i class="xi-profile" aria-hidden="true"></i> 사용자정보 </a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" ng-click="logout()"><i class="xi-log-out" aria-hidden="true"></i> 로그아웃</a>
				</li>
			</ul>
        </nav>


		<nav class="navbar navbar-fixed-top d-flex dropdown" ng-if="authenticated" style="width:100%;height:95px;background-color:white; border-bottom:5px solid #DCDCDC;z-index:3;position: fixed;top:30px;" >

			<div class="p-2" >
				<a href="" ng-click="goMain()"><img src="/resources/img/ci/top-logo.png" style="height: 60px;"></a>
			</div>

			<!--top메뉴 -->
			<div class="p-2 d-flex">
				<div class="navbar-header">
					<a href="">시스템관리</a>
					<div class="tri" ng-if="currentMenu.GROUP_CD == 'SYSTEM'"></div>
				</div>
				<div class="navbar-header">
					<a href="" ng-click="goMenu('sample/commList')">기초정보관리</a>
					<div class="tri" ng-if="currentMenu.GROUP_CD == 'BASE'"></div>
				</div>
				<div class="navbar-header">
					<a href="">입출고관리</a>
					<div class="tri" ng-if="currentMenu.GROUP_CD == 'INOUT'"></div>
				</div>
				<div class="navbar-header">
					<a href="" >재고실사</a>
					<div class="tri" ng-if="currentMenu.GROUP_CD == 'INVEN'"></div>
				</div>
				<div class="navbar-header">
					<a href="" ng-click="goAssetManagementList()">재고현황</a>
					<div class="tri" ng-if="currentMenu.GROUP_CD == 'STOCK'"></div>
				</div>


				<div class="dropdown-content" ng-style="dropDown">
					<div  class="dropdown-board" >
						<span>시스템 관리</span>
						<a href="" ng-repeat="value in topMenu" ng-click="goMenu(value)" ng-if="value.GROUP_CD == 'SYSTEM'"> &#176; {{value.PGM_NM}}</a>
					</div>

					<div class="dropdown-board" >
						<span>기초정보 관리</span>
						<a href="" ng-repeat="value in topMenu" ng-click="goMenu(value)" ng-if="value.GROUP_CD == 'BASE'"> &#176; {{value.PGM_NM}}</a>
					</div>

					<div class="dropdown-board" >
						<span>입출고 관리</span>
						<a href="" ng-repeat="value in topMenu" ng-click="goMenu(value)" ng-if="value.GROUP_CD == 'INOUT'"> &#176; {{value.PGM_NM}}</a>
					</div>

					<div class="dropdown-board" >
						<span>재고실사</span>
						<a href="" ng-repeat="value in topMenu" ng-click="goMenu(value)" ng-if="value.GROUP_CD == 'INVEN'"> &#176; {{value.PGM_NM}}</a>
					</div>

					<div class="dropdown-board">
						<span>재고현황</span>
						<a href="" ng-repeat="value in topMenu" ng-click="goMenu(value)" ng-if="value.GROUP_CD == 'STOCK'"> &#176; {{value.PGM_NM}}</a>
					</div>
				</div>
			</div>

<%--			<div class="ml-auto p-2" id="menuToggle" ng-show="authenticated"><button class="btn" ng-click="menuToggle()" style="background: white;"><i class="xi-list"></i></button></div>--%>
			<div class="ml-auto p-2" id="menuToggle" ng-show="authenticated"><button class="btn" ng-click="dropDown.display = dropDown.display == 'block' ? '' : 'block'" style="background: white;"><i class="xi-list"></i></button></div>
		</nav>

		<div style="height:120px;" ng-if="authenticated" ></div>

		<!--메인TOP-->
		<div style="height:500px;" ng-if="authenticated && mainPage">
			<div style="overflow: hidden;"><img src="/resources/img/다운로드 (1).png" style="width:100%;min-width:1150px;" draggable="false"></div>
		</div>



		<div class="d-flex justify-content-left" ng-if="authenticated" >

			<!--사이드바-->
			<div>
				<nav class="text-light bg-dark sidebar" ng-init="menu" ng-if="menu && authenticated" > <!-- 첫화면 사이드 메뉴 안보이게 -->
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


			<div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;padding: 10px;" ng-if="!regPage">


				<!-- left body -->
				<div style="width:20%;margin:3px;" ng-if="!mainPage">
					<div class="left-body" >
					<!--메뉴-->

						<div class="d-flex justify-content-between left-body-menu">
							<p style="margin: 8px 0 5px 0">매장</p>
							<select class="form-control" style="width: 180px;">
								<option>전체</option>
								<option>논현점</option>
								<option>압구정점</option>
							</select>
						</div>

						<div class="d-flex justify-content-between left-body-menu" ng-click="st1 = st1==true ? false : true">
							<p style="margin: 8px 50px 5px 0;">브랜드 선택</p>
							<i style="margin: 8px;" class="xi-angle-down" ng-show="!st1"></i>
							<i style="margin: 8px;" class="xi-angle-up" ng-show="st1"></i>
						</div>
						<div style="box-shadow: 1px 1px 5px 1px lightgray;padding:15px;" ng-show="st1">

							<table style="width:100%;">

								<tr><td onclick="selectTd($(this))">CHANEL</td></tr>
								<tr><td onclick="selectTd($(this))">ACNE STUDIOS</td></tr>
								<tr><td onclick="selectTd($(this))">ALEXANDER MCQUEEN</td></tr>
								<tr><td onclick="selectTd($(this))">BALENCIAGA</td></tr>
								<tr><td onclick="selectTd($(this))">CHRISTIAN LOUBOUTIN</td></tr>
								<tr><td onclick="selectTd($(this))">COMME DES GARCONS</td></tr>
								<tr><td onclick="selectTd($(this))">BOTTEGA VENETA</td></tr>
								<tr><td onclick="selectTd($(this))">ISABEL MARANT</td></tr>
								<tr><td onclick="selectTd($(this))">ISABEL MARANT</td></tr>
								<tr><td onclick="selectTd($(this))">ISABEL MARANT</td></tr>
								<tr><td onclick="selectTd($(this))">ISABEL MARANT</td></tr>
								<tr><td onclick="selectTd($(this))">ISABEL MARANT</td></tr>

							</table>
						</div>

<%--					<div class="d-flex justify-content-between left-body-menu"  ng-click="st2 = st2==true ? false : true">--%>
<%--						<p style="margin: 8px 50px 5px 0;">조회 날짜</p>--%>
<%--						<i style="margin: 8px;" class="xi-angle-down" ng-show="!st2"></i>--%>
<%--						<i style="margin: 8px;" class="xi-angle-up" ng-show="st2"></i>--%>
<%--					</div>--%>
<%--					<div style="box-shadow: 1px 1px 5px 1px lightgray;;padding:15px;" ng-show="st2">--%>

<%--						<div class="row input-group" style="width:250px;margin:5px;"> 시작일--%>
<%--							<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="st2_sdt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>--%>
<%--							<span class="input-group-append" >--%>
<%--								<button type="button" class="btn btn-secondary" ng-click="st2_sdt = st2_sdt==true ? false : true"> <i class="xi-calendar"></i></button>--%>
<%--							</span>--%>
<%--						</div>--%>

<%--						<div class="row input-group" style="width:250px;margin:5px;"> 종료일--%>
<%--							<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="st2_edt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>--%>
<%--							<span class="input-group-append" >--%>
<%--								<button type="button" class="btn btn-secondary" ng-click="st2_edt = st2_edt==true ? false : true"> <i class="xi-calendar"></i></button>--%>
<%--							</span>--%>
<%--						</div>--%>
<%--					</div>--%>

						<div  class="d-flex justify-content-between left-body-menu">
							<p style="margin: 8px 0 5px 0">성별</p>
							<select class="form-control" style="width: 180px;">
								<option>전체</option>
								<option>남성</option>
								<option>여성</option>
							</select>
						</div>

						<div  class="d-flex justify-content-between left-body-menu" ng-click="st3 = st3==true ? false : true">

							<p style="margin: 8px 50px 5px 0;">상품분류</p>
							<i style="margin: 8px;" class="xi-angle-down" ng-show="!st5"></i>
							<i style="margin: 8px;" class="xi-angle-up" ng-show="st5"></i>
						</div>
						<div class="form-group" style="box-shadow: 1px 1px 5px 1px lightgray;;padding:15px;" ng-show="st3">
							<table class="table-bordered" style="width:100%;text-align: center;height: 50px;">

								<tr style="margin:10px;">
									<td onclick="selectTd($(this))" style="width:33.3%;padding:10px;">아우터</td>
									<td onclick="selectTd($(this))" style="width:33.3%;">상의</td>
									<td onclick="selectTd($(this))" style="width:33.3%;">하의</td>
								</tr>
								<tr style="margin:10px;">
									<td onclick="selectTd($(this))" style="width:33.3%;padding:10px;">슈즈</td>
									<td onclick="selectTd($(this))" style="width:33.3%;">가방</td>
									<td onclick="selectTd($(this))" style="width:33.3%;">지갑</td>
								</tr>
							</table>
						</div>


						<div  class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:3px solid gray;background:whitesmoke;margin-top:100px;">
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
									<td style="width:33.3%;border-left: 1px solid lightgray;border-right: 1px solid lightgray;">3</td>
									<td style="width:33.3%;">3809</td>
								</tr>
							</table>
						</div>

					</div>
				</div>

				<!--right body-->
				<div style="width:80%;padding: 10px;">

					<!--현재 메뉴명 : 메인페이지에서 숨김-->
					<div class="container-fluid d-flex justify-content-center" style="padding:0!important;" ng-show="!mainPage">
						<div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;">
							<h4><a class="menuName" href="" ng-click="reload()">{{currentMenu.PGM_NM}}</a></h4>
<%--							<h6 style="color:gray;">재고현황조회</h6>--%>
						</div>
						<ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;">
							<li><a href="">{{currentMenu | menuGroup}}</a></li> &nbsp;>&nbsp;
							<li><a href="" >{{currentMenu.PGM_NM}}</a></li>
						</ol>
					</div>

					<!--상단 검색 보드-->
					<div class="d-flex container-fluid body-custom flex-column" style="width:100%;min-height: 150px;padding:5px 3%;">

						<div style="font-size:20px;margin: 5px;">Quick Search</div>
<%--						<div class=" d-flex" style="width: 100%;border:1px solid lightgray;border-radius: 10px;padding:10px;">--%>

						<!-- 검색 추가열 -->
<%--						{{currentMenu}}--%>
						<div class="d-flex" style="width: 100%;" ng-if="addQuick1">

							<p style="margin:12px 10px;">구분</p>
							<select class="form-control" style="width:150px;margin:5px;">
								<option>전체</option>
							</select>

							<select class="form-control" style="width:150px;margin:5px;">
								<option>전체</option>
							</select>

							<p style="margin:12px 10px;">일자검색</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="st2_sdt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_sdt = st2_sdt==true ? false : true"> <i class="xi-calendar"></i></button>
								</span>
							</div>
							<p style="margin:12px 10px;">~</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="endDate" is-open="st2_edt" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_edt = st2_edt==true ? false : true"> <i class="xi-calendar"></i></button>
								</span>
							</div>
						</div>


						<!-- 검색 공통 -->
						<div class="d-flex" style="width: 100%;">

							<select class="form-control" ng-model="quickSearch.gub" style="width:150px;margin: 10px;height: 40px;">
								<option option="">검색구분</option>
							</select>

							<input class="form-control" id="brandSearch" style="width:150px;margin: 10px;height: 40px;"  placeholder="브랜드" ng-model="quickSearch.brand" ng-click="qs1 = qs1==true ? false : true" readonly>
							<label class="d-flex justify-content-between" for="brandSearch" style="position:relative;left:-35px;top:15px;width:0px;" >
								<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-down" ng-show="!qs1"></i>
								<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-up" ng-show="qs1"></i>
							</label>
							<div style="width:100%;height:150px;background: white;position:absolute;top:150px;left:0px;z-index: 1;padding: 20px;border-radius:5px;box-shadow: 1px 1px 5px 1px lightgray;" ng-show="qs1">
								<div style="position:absolute;right:10px;top:5px;width:25px;color:gray;border:1px solid lightgray;border-radius:4px;text-align: center;cursor: pointer;" ng-click="qs1= false">X</div>
								<table style="width:100%;">
									<tr>
										<td onclick="selectTd($(this))">CHANEL</td>
										<td onclick="selectTd($(this))">ACNE STUDIOS</td>
										<td onclick="selectTd($(this))">ALEXANDER MCQUEEN</td>
										<td onclick="selectTd($(this))">BALENCIAGA</td>
										<td onclick="selectTd($(this))">CHRISTIAN LOUBOUTIN</td>
										<td onclick="selectTd($(this))">COMME DES GARCONS</td>
									</tr>
									<tr>
										<td onclick="selectTd($(this))">BOTTEGA VENETA</td>
										<td onclick="selectTd($(this))">ISABEL MARANT</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>

							</div>


							<select class="form-control" style="width:150px;margin: 10px;height: 40px;">
								<option>성별</option>
							</select>
							<select class="form-control" style="width:150px;margin: 10px;height: 40px;">
								<option>사이즈</option>
							</select>
							<select class="form-control" style="width:150px;margin: 10px;height: 40px;">
								<option>상품분류</option>
							</select>
							<button class="btn btn-outline-secondary" style="width:70px;margin:10px 30px;">검색</button>
							<div class="d-flex" style="width:280px;margin: 10px;">
								<input type="text" class="form-control" style="height: 40px;border:0;border-bottom: 1px solid gray;">
								<button class="btn" style="position:relative;left:-40px;background: transparent;">
									<i class="xi-search" style="font-size: 20px;"></i>
								</button>
							</div>
						</div>
					</div>


					<!--contents-->
					<div ng-view ng-show="authenticated && !regPage" ng-if="authenticated && !regPage" style="width:100%;"></div>
				</div>
			</div>
		</div>



		<!--login 페이지-->
		<div ng-if="!authenticated">
			<div ng-view ng-show="!authenticated" ></div>
		</div>
		<!-- 등록 페이지 -->
		<div ng-if="authenticated && regPage">
			<div ng-view ng-show="authenticated && regPage"  style="background-color:whitesmoke;width:100%;padding: 15px;"></div>
		</div>



		<!--footer-->
		<div class=" d-flex justify-content-center" ng-if="authenticated" style="width:100%;padding: 15px;">
			<div style="padding: 5px 70px 0 0;">
				<img src="/resources/img/ci/top-logo.png" style="width:140px;height:35px;">
			</div>
			<div class="footer-text">
				<span>더팩토리 서울특별시 강남구 봉은사로7길 40 201호</span>
				<span>대표 :이예림 사업자등록번호 :214-13-55201 사업자번호조회 >통신판매업신고번호 :제 2017-서울강남-00071호개인정보관리자 :이예림</span>
				<span>대표번호 :02-6407-7879팩스번호 :메일 :thefactor2@naver.com</span>
				<span>Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
	</body>
</html>

<script>
	function selectTd(td){
		var table = td.parent().parent();
		table.children().children().css('background', 'white');
		table.children().children().css('color', 'black');

		td.css('background', 'gray');
		td.css('color', 'white');
	}


</script>
