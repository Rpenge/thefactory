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
	<script src="${pageContext.request.contextPath}/resources/js/controller/base/brandController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/salesController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/ioInfoController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/inputController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/inout/outputController.js?v=${version}"></script>
	<script src="${pageContext.request.contextPath}/resources/js/controller/stock/stockController.js?v=${version}"></script>
<!--icon-->
<link rel="shortcut icon" type="image/x-icon" href="/resources/img/ci/sysk.png">

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
	<body class="ng-cloak " style="min-width:1280px;">
		<nav class="navbar navbar-dark bg-inverse navbar-fixed-top d-flex justify-content-between" style="color: white;width:100%;height:35px;z-index:3;background-color:#484848;padding:0 60px;position: fixed;">
			<i class="xi-box" style="color: white;font-size: small"> THEFACTOR2 재고관리</i>
            <!-- Top Menu Items -->
           	<ul class="navbar-nav flex-row ml-md-auto d-md-flex " ng-if="authenticated">
				<li class="nav-item" >
<%--					<a class="nav-link" href='/#/system/user' ng-click="goUserInfo()" style="margin-right:20px;"><i class="xi-profile" aria-hidden="true"></i>사용자정보 </a>--%>
					<a class="nav-link" style="margin-right:20px;"> {{userId}}님 반갑습니다</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" ng-click="logout()"><i class="xi-log-out" aria-hidden="true"></i> 로그아웃</a>
				</li>
			</ul>
        </nav>


		<nav class="navbar navbar-fixed-top d-flex dropdown" ng-if="authenticated" style="width:100%;min-width:1280px;height:95px;background-color:white; border-bottom:5px solid #DCDCDC;z-index:3;position: fixed;top:30px;" >

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


			<div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;padding: 10px;" ng-if="!regPage">

				<!-- left body -->
				<div style="width:20%;margin:3px;" ng-if="!mainPage">
					<div class="left-body" >
					<!--메뉴-->

						<div class="d-flex justify-content-between left-body-menu">
							<p style="margin: 8px 0 5px 0">매장</p>
							<select class="form-control" ng-model="quickSearch.storeCd" style="width: 180px;">
								<option value="">전체</option>
								<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
							</select>
						</div>

						<div class="d-flex justify-content-between left-body-menu" ng-click="st1 = st1==true ? false : true">
							<p style="margin: 8px 50px 5px 0;">브랜드 선택</p>
							<i style="margin: 8px;" class="xi-angle-down" ng-show="!st1"></i>
							<i style="margin: 8px;" class="xi-angle-up" ng-show="st1"></i>
						</div>
						<div style="box-shadow: 1px 1px 5px 1px lightgray;padding:15px;" ng-show="st1">

							<table id="lBrandTb" style="width:100%;">
<%--								<tr ng-repeat="value in brandList"><td onclick="selectTd($(this))" style="padding:5px;">{{value.brandNm}}</td></tr>--%>
								<tr ng-repeat="(key, value) in brandList">
									<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key])">{{brandList[key].brandNm}} </td>
								</tr>
							</table>


						</div>

						<div  class="d-flex justify-content-between left-body-menu">
							<p style="margin: 8px 0 5px 0">성별</p>
							<select class="form-control" ng-model="quickSearch.gender" ng-change="genderSelect(quickSearch.gender)" style="width: 180px;">
								<option value="">전체</option>
								<option ng-repeat="value in subBrand" ng-if="value.codeLevel=='M'" value="{{value.brandKindCd}}">{{value.brandNm}}</option>
							</select>
						</div>

						<div  class="d-flex justify-content-between left-body-menu" ng-click="st3 = st3==true ? false : true">

							<p style="margin: 8px 50px 5px 0;">상품분류</p>
							<i style="margin: 8px;" class="xi-angle-down" ng-show="!st5"></i>
							<i style="margin: 8px;" class="xi-angle-up" ng-show="st5"></i>
						</div>
						<div class="form-group" style="box-shadow: 1px 1px 5px 1px lightgray;;padding:15px;" ng-show="st3">
							<table style="width:100%;">
								<tr ng-repeat="(key, value) in subBrandCls">
									<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="quickSearch.cls=value.brandKindCd">{{value.brandNm}} </td>
								</tr>
							</table>
						</div>


						<div  class="d-flex justify-content-between" style="height:55px;padding:10px;border-bottom:3px solid gray;background:whitesmoke;margin-top:100px;">
							<p style="margin: 8px 50px 5px 0;">입출고현황</p>
						</div>

						<div class="form-group">
							<table style="width:100%;text-align: center;height: 50px;">

								<tr style="margin:10px;">
									<td style="width:33.3%;padding:5px;">입고</td>
									<td style="width:33.3%;">출고</td>
									<td style="width:33.3%;">판매/배송</td>
								</tr>
								<tr style="margin:10px;">
									<td style="width:33.3%;padding:15px;">{{todayData.inTotcnt}}</td>
									<td style="width:33.3%;border-left: 1px solid lightgray;border-right: 1px solid lightgray;">{{todayData.outTotcnt}}</td>
									<td style="width:33.3%;">{{todayData.sellTotcnt}}</td>
								</tr>
							</table>
						</div>

					</div>
				</div>

				<!--right body-->
				<div style="width:80%;padding: 10px;">

					<!--현재 메뉴명 : 메인페이지에서 숨김-->
					<div class="container-fluid d-flex justify-content-center" ng-if="!mainPage" style="padding:0!important;" >
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

						<div class="d-flex" style="border-bottom: 1px solid #f1f1f1;margin-bottom: 4px;">
							<span style="font-size:20px;margin: 12px 13px 0 0;">Quick Search</span>

							<select class="form-control" ng-model="quickCommand" style="width:180px;margin-top: 10px;margin-bottom:5px;height: 40px;" ng-change="addQuick(quickCommand)">
								<option value="IO1">입고관리</option>
								<option value="IO2">출고관리</option>
								<option value="IO3">판매/배송관리</option>
								<option value="IO4">입출고내역조회</option>
								<option value="ST1">재고현황관리</option>
							</select>

						</div>
<%--						<div class=" d-flex" style="width: 100%;border:1px solid lightgray;border-radius: 10px;padding:10px;">--%>

						<!-- 검색열1 -->
						<div class="d-flex" style="width: 100%;" ng-if="quick1">

							<p style="margin:12px 10px;">구분</p>
							<select class="form-control" ng-model="quickSearch.workGub" style="width:150px;margin:5px;" ng-disabled="gubDisabled">
								<option value="">전체</option>workB
								<option ng-repeat="value in workM" value="{{value.commCd}}">{{value.commCdNm}}</option>
							</select>

							<select class="form-control" ng-model="quickSearch.detatilGub" style="width:150px;margin:5px;">
								<option value="">전체</option>
								<option ng-repeat="value in workS" ng-if="detatilGub(value.commCd)" value="{{value.commCd}}">{{value.commCdNm}}</option>
							</select>

							<p style="margin:12px 10px;">일자검색</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="quickSearch.startDate" is-open="st2_sdt" datepicker-options="startDateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_sdt = st2_sdt==true ? false : true"> <i class="xi-calendar"></i></button>
								</span>
							</div>
							<p style="margin:12px 10px;">~</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="quickSearch.endDate" is-open="st2_edt" datepicker-options="endDateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_edt = st2_edt==true ? false : true"> <i class="xi-calendar"></i></button>
								</span>
							</div>
						</div>

						<!-- 검색 추가2 -->
						<div class="d-flex" style="width: 100%;" ng-if="quick2">
							<p style="margin:12px 10px;">일자검색</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="quickSearch.startDate" is-open="st2_sdt" datepicker-options="startDateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_sdt = st2_sdt==true ? false : true" style="height: 38px;"> <i class="xi-calendar"></i></button>
								</span>
							</div>
							<p style="margin:12px 10px;">~</p>
							<div class="row input-group" style="width:200px;margin:5px;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="quickSearch.endDate" is-open="st2_edt" datepicker-options="endDateOptions" close-text="Close" ng-readonly="dateUse"/>
								<span class="input-group-append" >
									<button type="button" class="btn btn-secondary" ng-click="st2_edt = st2_edt==true ? false : true" style="height: 38px;"> <i class="xi-calendar"></i></button>
								</span>
							</div>
							<button class="btn btn-outline-secondary" ng-click="goSearch(quickCommand)" style="width:70px;margin:5px 30px;">검색</button>
						</div>


						<!-- 검색 3 -->
						<div class="d-flex" style="width: 100%;" ng-if="quick3">
							<input class="form-control" id="brandSearch" style="width:220px;margin: 10px;height: 40px;"  placeholder="브랜드" ng-model="view.brand" ng-click="qs1 = qs1==true ? false : true"  readonly>
							<label class="d-flex justify-content-between" for="brandSearch" style="position:relative;left:-35px;top:15px;width:0px;" >
								<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-down" ng-show="!qs1"></i>
								<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-up" ng-show="qs1"></i>
							</label>
							<div style="width:100%;min-height:150px;background: white;position:absolute;top:180px;left:0px;z-index: 1;padding: 20px;border-radius:5px;box-shadow: 1px 1px 5px 1px lightgray;" ng-show="qs1">
								<div onclick="tableTdDel('qBrandTb');tableTdDel('lBrandTb')" ng-click="qs1=false;brandSelect()" style="position:absolute;right:10px;top:5px;width:25px;color:gray;border:1px solid lightgray;border-radius:4px;text-align: center;cursor: pointer;">X</div>
								<table id="qBrandTb" style="width:98%;vertical-align: top;" ng-click="qs1= false">
									<tr ng-repeat="(key, value) in brandList" ng-if="key % 6 == 0">
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key])">{{brandList[key].brandNm}} </td>
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key+1])" ng-if="brandList[key+1]">{{brandList[key+1].brandNm}} </td>
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key+2])" ng-if="brandList[key+2]">{{brandList[key+2].brandNm}} </td>
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key+3])" ng-if="brandList[key+3]">{{brandList[key+3].brandNm}} </td>
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key+4])" ng-if="brandList[key+4]">{{brandList[key+4].brandNm}} </td>
										<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="brandSelect(brandList[key+5])" ng-if="brandList[key+5]">{{brandList[key+5].brandNm}} </td>
									</tr>
								</table>
							</div>

							<select class="form-control" ng-model="quickSearch.gender" ng-change="genderSelect(quickSearch.gender)" style="width:150px;margin: 10px;height: 40px;">
								<option value="">성별</option>
								<option ng-repeat="value in subBrand" ng-if="value.codeLevel=='M'" value="{{value.brandKindCd}}">{{value.brandNm}}</option>
							</select>
							<select class="form-control" ng-model="quickSearch.cls" style="width:150px;margin: 10px;height: 40px;">
								<option value="">상품분류</option>
								<option ng-repeat="value in subBrandCls" value="{{value.brandKindCd}}">{{value.brandNm}}</option>
							</select>

							<input class="form-control" ng-model="quickSearch.prdSize" placeholder="사이즈" style="width:150px;margin: 10px;height: 40px;"></input>

							<button class="btn btn-outline-secondary" ng-click="goSearch(quickCommand)" style="width:70px;margin:10px 30px;">검색</button>
							<div class="d-flex" style="width:280px;margin: 10px;">
								<input type="text" class="form-control" ng-model="quickSearchWord.word" style="height: 40px;border:0;border-bottom: 1px solid gray;">
								<button class="btn" ng-click="goSearch(quickCommand, 'word')" style="position:relative;left:-40px;background: transparent;">
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
