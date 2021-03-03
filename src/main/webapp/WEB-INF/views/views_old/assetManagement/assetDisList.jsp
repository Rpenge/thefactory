<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div class="starter-template" ng-show="authenticated">
	<div class="container-fluid body-custom">
		<div class="body-contents ">
			<h2><a class="menuName" href="" ng-click="reload()">폐기 자산 목록</a></h2>
			<h6 class="total-text">TOTAL ( {{total}} )</h6>
			<!-- 검색 -->
			<form class="form-inline" ng-submit="clickSearch()">
				<div class="form-group search-box">
					<div class="search-text">검색옵션</div>
					
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
			    	
			    	<select class="custom-select " ng-model="search.division"  >
			    		<option value="">자산 분류</option>
			    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
			    	</select>
			    	
			    	<select class="custom-select " ng-model="search.location"  >
			    		<option value="">위치 분류</option>
			    		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
			    	</select>
			    	
			    	<select class="custom-select " ng-model="search.dept" >
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
        	
        	<div class="btnBox">
        		<select class="custom-select col-1 left" ng-model="sizeValue" ng-change="pageSize(sizeValue)">
	        		<option value="">10개씩 보기</option>
	        		<option value="20">20개씩 보기</option>
	        		<option value="50">50개씩 보기</option>
	        	</select>
        	</div>
        	
			<!-- 테이블 생성 -->
			<div class="table-box">
			<table class="table table-striped-odd table-hover text-center custom-align-middle" style="min-width:1100px;">
				<thead>
					<tr class="pointer">
						<th ng-click="sort('assetControlCode')">관리번호</th>
						<th ng-click="sort('assetName')">자산명</th>
						<th ng-click="sort('mappingYn')">태그사용</th>
						<th ng-click="sort('assetStatus')">상태</th>
						<th ng-click="sort('assetDept')">사용부서</th>
						<th ng-click="sort('assetRegDate')">등록일</th>
						<th ng-click="sort('assetRegPerson')">등록자</th>
						<th ng-click="sort('assetLocation')">위치</th>
						<th ng-click="sort('assetQuantity')">수량</th>
						<th ng-click="sort('assetDivision')">분류</th>
					</tr>
				</thead> 
				<tbody>
					<tr ng-repeat="(key, value) in list" class="pointer" ng-init="value.isSelected = false;">
						<td ng-click="assetDetail(value.assetControlCode)">{{value.assetControlCode}}</td>
						<td ng-click="assetDetail(value.assetControlCode)">{{value.assetName}}</td>
						<td>{{value.mappingYn}}</td>
						<td>{{value.assetStatus | code: commonCode}}</td>
						<td>{{value.assetDept | code: commonCode}}</td>
						<td>{{value.assetRegDate | date:'yyyy-MM-dd HH:mm'}}</td>
						<td>{{value.assetRegPerson}}</td>
						<td>{{value.assetLocation | code: commonCode}}</td>
						<td>{{value.assetQuantity}} </td>
						<td>{{value.assetDivision | code: commonCode}}</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<!-- 네비게이션 바 -->
			<nav class="text-center">
				<ul class="pagination justify-content-center">
					<li class="page-item">
						<a href="" aria-label="Previous" ng-click="goPage(begin)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
			    	</li>
			    	<li class="page-item">
						<a href="" aria-label="Previous" ng-click="goPage(current - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
			    	</li>
			    	<li ng-repeat="pageNum in [begin, end] | makeRange" ng-class="{'active' : current == pageNum}" class="page-item"><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
			    	<li class="page-item">
			    		<a href="" aria-label="Next" ng-click="goPage(current + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
			    	</li>
			    	<li class="page-item">
			    		<a href="" aria-label="Next" ng-click="goPage(end)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
			    	</li>
			  	</ul>
			</nav>
		</div>
	</div>
</div>
</html>


