<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div class="starter-template" ng-show="authenticated">

	<div class="container-fluid body-custom">
		<div class="body-contents">
			<h2><a class="menuName" href="" ng-click="reload()">RFID 사용 내역</a></h2>
			<h6 class="total-text">TOTAL ( {{total}} )</h6>
			<!-- 검색 -->
			<form ng-submit="clickSearch()">
				<div class="form-group search-box">
					<div class="search-text">검색옵션</div>
					<div class="form-inline">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="dateSearch" ng-click="dateUseChange(true)" checked>전체 기간 
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="dateSearch" ng-click="dateUseChange(false)">기간 설정
						</div>
						
						<div class="row input-group calBtn">
							<input type="text" class="form-control custom-date" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="search.startDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
			        		<span class="input-group-append" >
			            		<button type="button" class="btn btn-secondary" ng-click="startDateOpen()"> <i class="xi-calendar"></i></button>
			        		</span>
			        	</div>
			        	<i class="xi-minus" ></i>
			        	<div class="row input-group calBtn">
							<input type="text" class="form-control custom-date" uib-datepicker-popup="{{format}}" ng-model="endDate" is-open="search.endDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
			        		<span class="input-group-append" style="margin-right:30px;">
			            		<button type="button" class="btn btn-secondary" ng-click="endDateOpen()"> <i class="xi-calendar"></i></button>
			        		</span>
			        	</div>
						
		        		<select class="custom-select" ng-model="search.astSt"  >
				    		<option value="">상태</option>
				    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='AST'">{{list.codeName}}</option>
				    	</select>
				    	
				    	<select class="custom-select" ng-model="search.astWk"  >
				    		<option value="">작업 분류</option>
				    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='AJB'">{{list.codeName}}</option>
				    	</select>
				    	
				    	<select class="custom-select" ng-model="search.wkLoc"  >
				    		<option value="">위치 분류</option>
				    		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
				    	</select>
				    	
				    	<select class="custom-select" ng-model="search.astDiv"  >
				    		<option value="">자산 분류</option>
				    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
				    	</select>
				    	
				    	<select class="custom-select" ng-model="search.astDp" >
				    		<option value="">부서</option>
				    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
				    	</select>
				    	
				    	<select class="custom-select" ng-model="search.updateYn" >
				    		<option value="">업데이트</option>
				    		<option value="Y">Y</option>
				    		<option value="N">N</option>
				    	</select>
			    	</div>
			    	
			    	<div class="search-submit row d-flex justify-content-center">
						<select class="custom-select col-1" ng-model="search.option" ng-init="search.option='astNm'" style="margin:5px;">
							<option value="astNm">자산명</option>
							<option value="astCtrlCd">관리번호</option>
							<option value="wkPrsn">등록자</option>
						</select>
						<input type="text" class="form-control col-2" placeholder="검색" autocomplete="off" ng-model="search.text" style="width:300px;" id="inputSearch">&nbsp;
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
				</div>
        	</form>
        	
        	<div class="btnBox d-flex justify-content-between">
        		<select class="custom-select left" ng-model="sizeValue" ng-change="pageSize(sizeValue)">
	        		<option value="">10개씩 보기</option>
	        		<option value="20">20개씩 보기</option>
	        		<option value="50">50개씩 보기</option>
	        	</select>
	        	<button class="btn btn-success" ng-click="excelDown()">excel</button>
        	</div>
        	
			<!-- 테이블 생성 -->
			<div class="table-box">
			<table class="table table-striped-odd table-hover text-center custom-align-middle" style="min-width:1100px;">
				<thead>
					<tr class="pointer">
						<th ng-click="sort('astChgSeq')" id="astChgSeq">No</th>
						<th ng-click="sort('astCtrlCd')" id="astCtrlCd">관리번호</th>
						<th class="astNmTable" ng-click="sort('astNm')" id="astNm">자산명</th>
						<th ng-click="sort('astSt')" id="astSt">상태</th>
						<th ng-click="sort('astDiv')" id="astDiv">분류</th>
						<th ng-click="sort('astDp')" id="astDp">부서</th>
						<th ng-click="sort('wkLoc')" id="wkLoc">위치</th>
						<th ng-click="sort('astWk')" id="astWk">변동내용</th>
						<th ng-click="sort('chgDt')" id="chgDt">변경일자</th>
						<th ng-click="sort('wkPrsn')" id="wkPrsn">등록자</th>
						<th ng-click="sort('updateYn')" id="updateYn">업데이트</th>
					</tr>
				</thead> 
				<tbody>
					<!-- <tr ng-repeat="(key, value) in list" ng-click="tableSearch(value.astCtrlCd)" class="pointer" ng-style="divLine(list[key-1],value)"> -->
					<tr ng-repeat="(key, value) in list" ng-click="tableSearch(value.astCtrlCd)" class="pointer">
						<td>{{value.astChgSeq}}</td>
						<td>{{value.astCtrlCd}}</td>
						<td>{{value.astNm}}</td>
						<td>{{value.astSt | code: commonCode}}</td>
						<td>{{value.astDiv | code: commonCode}}</td>
						<td>{{value.astDp | code: commonCode}}</td>
						<td>{{value.wkLoc | code: commonCode}}</td>
						<td>{{value.astWk | code: commonCode }}</td>
						<td>{{value.chgDt | date:'yyyy-MM-dd HH:mm'}}</td>
						<td>{{value.wkPrsn}}</td>
						<td>{{value.updateYn}} </td>
					</tr>
				</tbody>
			</table>
			</div>
			<!-- 네비게이션 바 -->
			<nav class="text-center">
				<ul class="pagination justify-content-center">
					<li class="page-item">
						<a href="" aria-label="Previous" ng-click="goPage(prev)" ng-show="prev>0" class="page-link"><span aria-hidden="true">&laquo;</span></a>
			    	</li>
			    	<li class="page-item">
						<a href="" aria-label="Previous" ng-click="goPage(current - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
			    	</li>
			    	<li ng-repeat="pageNum in ( current | makeRange2: total : last)" ng-class="{'active' : current == pageNum}" class="page-item"><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
			    	<li class="page-item">
			    		<a href="" aria-label="Next" ng-click="goPage(current + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
			    	</li>
			    	<li class="page-item">
			    		<a href="" aria-label="Next" ng-click="goPage(next)" ng-show="next < last" class="page-link"><span aria-hidden="true">&raquo;</span></a>
			    	</li>
			  	</ul>
			</nav>
		</div>
	</div>
</div>
</html>