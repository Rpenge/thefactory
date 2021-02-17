<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div class="starter-template" ng-show="authenticated">
	<div class="container-fluid body-custom">
		<div class="body-contents ">
			<h2><a class="menuName" href="" ng-click="reload()">자산 수리</a></h2>
			<h6 class="total-text">TOTAL ( {{total}} )</h6>
	
        	
        	<form class="form-inline search-form" ng-submit="clickSearch()">
        	<table class="table-bordered table-div-10 text-center col-12">
				<thead>
					<tr style="visibility: collapse;">
						<th ng-repeat="x in [].constructor(10) track by $index"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2" class="table-content" >날짜 설정</td>
						<td colspan="2"><div class="form-check">
							<input id='radio1' class="form-check-input"  type="radio" name="dateSearch" ng-click="dateUseChange(true)" checked>
							<label  class="form-check-label" for='radio1' > 전체 </label>&nbsp;&nbsp;
							<input id='radio2' type="radio" name="dateSearch" ng-click="dateUseChange(false)">  
							<label  class="form-check-label" for='radio2' > 선택 </label>
						</div></td>
						<td class="table-content">시작일</td>
						<td colspan="2">
							<div class="row input-group" style="margin: 0;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="startDate" is-open="search.startDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
				        		<span class="input-group-append" >
				            		<button type="button" class="btn btn-secondary" ng-click="startDateOpen()"> <i class="xi-calendar"></i></button>
				        		</span>
				        	</div>
			        	</td>
			        	<td class="table-content">종료일</td>
			        	<td colspan="2">
			        		<div class="row input-group" style="margin: 0;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="endDate" is-open="search.endDateOpened" datepicker-options="dateOptions" close-text="Close" ng-readonly="dateUse"/>
				        		<span class="input-group-append">
				            		<button type="button" class="btn btn-secondary" ng-click="endDateOpen()"> <i class="xi-calendar"></i></button>
				        		</span>
				        	</div>
			        	</td>
					</tr>
					
					<tr>
						<td class="table-content reg-form-td" colspan="1">조건 분류</td>
						<td class="table-content" colspan="1">자산</td>
						<td class="reg-form-td" colspan="2" style="padding:0;">
							<select class="form-control fit" ng-model="search.division">
					    		<option value="">자산 분류</option>
					    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
					    	</select>
						</td>
						<td class="table-content" colspan="1">위치</td>
						<td class="reg-form-td" colspan="2">
							<select class="form-control fit" ng-model="search.location"  >
					    		<option value="">위치 분류</option>
					    		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
					    	</select>
					    </td>
					    <td class="table-content" colspan="1">부서</td>
					    <td class="reg-form-td" colspan="2" style="margin:0;padding:0;">
					    	<select class="form-control fit" ng-model="search.dept" >
					    		<option value="">부서</option>
					    		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
					    	</select>
						</td>
					</tr>
					
					<tr>
						<td class="table-content" colspan="2">검색옵션</td>
						<td colspan="8"> 
							<div class="search-submit row" style="margin: 0;padding :0;">
								<select class="form-control col-2" style="margin: 0; padding :0;" ng-model="search.option" ng-init="search.option='assetName'" style="margin:5px;">
									<option value="assetName">자산명</option>
									<option value="assetControlCode" >관리번호</option>
									<option value="assetRegPerson" >등록자</option>
								</select>
								<input type="text" class="form-control col-3" style="margin:0 3px;padding :0 15px;" placeholder="검색" autocomplete="off" ng-model="search.text" style="width:300px;" id="inputSearch">
								<button type="submit" class="btn btn-primary" style="margin:0;padding:0 13px;">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
        	</form>
        	
        	<div class="btnBox d-flex justify-content-between">
        		<select class="custom-select" ng-model="sizeValue" ng-change="pageSize(sizeValue)">
	        		<option value="">10개씩 보기</option>
	        		<option value="20">20개씩 보기</option>
	        		<option value="50">50개씩 보기</option>
	        		<option value="total" style="color: red;">전체보기</option>
	        	</select>
	        	
	        	<div>
				</div>
        	</div>
			<!-- 테이블 생성 -->
			<div class="table-box">
			<table class="table table-striped-odd table-hover text-center custom-align-middle" style="min-width:1100px;">
				<thead>
					<tr class="pointer">
						<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
						<th ng-click="sort('')" id="">관리번호</th>
						<th ng-click="sort('')" class="astNmTable"  id="">자산명</th>
						<th ng-click="sort('')" id="">분류</th>
						<th ng-click="sort('')" id="">상태</th>
						<th ng-click="sort('')" id="">태그발행</th>
						<th ng-click="sort('')" id="">사용부서</th>
						<th ng-click="sort('')" id="">등록일</th>
						<th ng-click="sort('')" id="">등록자</th>
						<th ng-click="sort('')" id="">위치</th>
						<th ng-click="sort('')" id="">수량</th>
					</tr>
				</thead> 
				<tbody>
					<tr ng-repeat="(key, value) in list" class="pointer" ng-init="value.isSelected = false;">
						<td><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})"></td>
						<td ng-click="assetDetail(value.assetControlCode)">{{value.assetControlCode}}</td>
						<td ng-click="assetDetail(value.assetControlCode)">{{value.assetName}}</td>
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
			
			<div class="d-flex justify-content-center">
			<!-- 네비게이션 바 -->
				<nav class="text-center">
					<ul class="pagination">
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
</div>
</html>


