<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div class="starter-template" ng-show="authenticated">

	<section class="container-fluid d-flex justify-content-between" style="width:80%;min-width:900px;border-bottom:1px solid lightgray;padding:0!important;">
		<div class="d-flex align-items-end">
			<h4><a class="menuName" href="" ng-click="reload()">STOCK</a></h4>
			<h6 style="color:gray;">재고현황조회</h6>
		</div>
		<ol class="breadcrumb" style="height:10px;background-color:transparent;">
			<li><a href="#">재고현황</a></li> &nbsp;>&nbsp;
			<li><a href="#">재고현황조회</a></li>
		</ol>
	</section>

	<section class="d-flex justify-content-center">
	<div class="container-fluid body-custom " style="width:300px;margin:3px;">


	</div>

	<div class="container-fluid body-custom" style="width:60%;margin:3px;">
		<div class="body-contents ">
			<h6 class="total-text">TOTAL ( {{total}} )</h6>
			<!-- 검색 -->
			<form class="form-inline search-form" ng-submit="clickSearch()">
				<div class="form-group search-box">
        			<div class="form-check">
						<input class="form-check-input" type="radio" name="dateSearch" ng-click="dateUseChange(true)" checked>전체 기간
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="dateSearch" ng-click="dateUseChange(false)">기간 설정
					</div>

					<div class="row">
						<input type="text" class="form-control custom-date" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="search.startDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
		        		<span class="input-group-btn" >
		            		<button type="button" class="btn btn-secondary" ng-click="startDateOpen()">달력 <i class="xi-calendar"></i></button>
		        		</span>
		        		<i class="xi-minus" style="margin:12px 26px 12px 5px;"></i>
		        	</div>

		        	<div class="row">
						<input type="text" class="form-control custom-date" uib-datepicker-popup="{{format}}" ng-model="endDate" is-open="search.endDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse" />
		        		<span class="input-group-btn" style="margin-right:30px;">
		            		<button type="button" class="btn btn-secondary" ng-click="endDateOpen()">달력 <i class="xi-calendar"></i></button>
		        		</span>
		        	</div>

	        		<select class="custom-select " ng-model="search.assetStatus"  >
			    		<option value="">상태</option>
			    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='AST'">{{list.codeName}}</option>
			    	</select>

			    	<select class="custom-select " ng-model="search.assetDivision"  >
			    		<option value="">자산 분류</option>
			    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
			    	</select>

			    	<select class="custom-select " ng-model="search.assetLocation"  >
			    		<option value="">위치 분류</option>
			    		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
			    	</select>

			    	<select class="custom-select " ng-model="search.assetDept" >
			    		<option value="">부서</option>
			    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
			    	</select>
			    	<div class="search-submit row">
						<select class="custom-select col-3" ng-model="search.option" ng-init="search.option='assetName'" style="margin:5px;">
							<option value="assetName">자산명</option>
							<option value="assetControlCode" >관리번호</option>
							<option value="assetRegPerson" >등록자</option>
						</select>
						<input type="text" class="form-control col-5" placeholder="검색" autocomplete="off" ng-model="search.text" style="width:300px;" id="inputSearch">&nbsp;
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
				</div>
        	</form>

        	<div class="btnBox d-flex justify-content-between">
        		<select class="custom-select" ng-model="sizeValue" ng-change="pageSize(sizeValue)">
	        		<option value="">10개씩 보기</option>
	        		<option value="20">20개씩 보기</option>
	        		<option value="50">50개씩 보기</option>
	        		<option value="total" style="color: red;">전체보기</option>
	        	</select>

	        	<div>
					<button class="btn btn-outline-secondary btn-sm" ng-click="modalOpen('assetReg')">자산 등록</button>
					<button class="btn btn-outline-secondary btn-sm" ng-click="modalOpen('repair')">수리 등록</button>
					<button class="btn btn-outline-secondary btn-sm" ng-click="assetUpdate('DEL')">삭제</button>
				</div>
        	</div>
			<!-- 테이블 생성 -->
			<div class="table-box">
			<table class="table custom-table-1 table-hover text-center custom-align-middle" style="min-width:1100px;">
				<thead>
					<tr class="pointer">
						<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
						<th ng-click="sort('assetControlCode')" id="assetControlCode">관리번호</th>
						<th class="astNmTable" ng-click="sort('assetName')" id="assetName">자산명</th>
						<th ng-click="sort('assetDivision')" id="assetDivision">분류</th>
						<th ng-click="sort('assetStatus')" id="assetStatus">상태</th>
						<th ng-click="sort('mappingYn')" id="mappingYn">태그발행</th>
						<th ng-click="sort('assetDept')" id="assetDept">사용부서</th>
						<th ng-click="sort('assetRegDate')" id="assetRegDate">등록일</th>
						<th ng-click="sort('assetRegPerson')" id="assetRegPerson">등록자</th>
						<th ng-click="sort('assetLocation')" id="assetLocation">위치</th>
						<th ng-click="sort('assetQuantity')" id="assetQuantity">수량</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="(key, value) in list" class="pointer" ng-init="value.isSelected = false;">
						<td><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})"></td>
						<td ng-click="modalOpen('assetDetail', value.assetControlCode)">{{value.assetControlCode}}</td>
						<td ng-click="modalOpen('assetDetail', value.assetControlCode)">{{value.assetName}}</td>
						<td>{{value.assetDivision | code: commonCode}}</td>
						<td>{{value.assetStatus | code: commonCode}}</td>
						<td>{{value.mappingYn}}</td>
						<td>{{value.assetDept | code: commonCode}}</td>
						<td>{{value.assetRegDate | date:'yyyy-MM-dd HH:mm'}}</td>
						<td>{{value.assetRegPerson}}</td>
						<td>{{value.assetLocation | code: commonCode}}</td>
						<td>{{value.assetQuantity}} </td>
					</tr>
				</tbody>
			</table>
			</div>

			<div class="row">
			<div class="col"></div>
			<!-- 네비게이션 바 -->
				<nav class="col-6 text-center d-flex justify-content-center">
					<ul class="pagination">
						<li class="page-item">
							<a href="" aria-label="Previous" ng-click="goPage(begin)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
				    	</li>
				    	<li class="page-item">
							<a href="" aria-label="Previous" ng-click="goPage(current - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
				    	</li>
				    	<li ng-repeat="pageNum in [begin, end] | makeRange" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
				    	<li class="page-item">
				    		<a href="" aria-label="Next" ng-click="goPage(current + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
				    	</li>
				    	<li class="page-item">
				    		<a href="" aria-label="Next" ng-click="goPage(end)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
				    	</li>
				  	</ul>
				</nav>

				<div class="page-area col d-flex justify-content-end">
					<button class="btn btn-outline-secondary btn-sm" ng-click="formDown()"><i class="xi-file-download-o"></i> 양식</button>
					<button class="btn btn-outline-secondary btn-sm" ng-click="excelClick()"><i class="xi-file-upload-o"></i> EXCEL업로드</button>
					<form id="excelUpload" enctype="multipart/form-data" >
						<input  type="file" id="excelUpload1" name="excel" onchange="angular.element(this).scope().excelUpload()" accept=".xlsx" hidden>
					</form>
				</div>

			</div>
		</div>
	</div>
	</section>
</div>
</html>


