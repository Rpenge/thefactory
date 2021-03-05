<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

<%--	<section class="container-fluid d-flex justify-content-center" style="padding:0!important;">--%>
<%--		<div class="d-flex align-items-end" style="width:20%;border-bottom:1px solid lightgray;">--%>
<%--			<h4><a class="menuName" href="" ng-click="reload()">STOCK</a></h4>--%>
<%--			<h6 style="color:gray;">재고현황조회</h6>--%>
<%--		</div>--%>
<%--		<ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:80%;background-color:transparent;border-bottom:1px solid lightgray;">--%>
<%--			<li><a href="#">재고현황</a></li> &nbsp;>&nbsp;--%>
<%--			<li><a href="#">재고현황조회</a></li>--%>
<%--		</ol>--%>
<%--	</section>--%>


	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents ">



				<div class="d-flex">
					<select class="custom-select p-2" ng-model="sizeValue" ng-change="pageSize(sizeValue)">
						<option value="">10개씩 보기</option>
						<option value="20">20개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="total" style="color: red;">전체보기</option>
					</select>

					<h6 class="total-text p-2">TOTAL ( {{total}} )</h6>

					<div class="ml-auto p-2 d-flex">

<%--						<form id="excelUpload" enctype="multipart/form-data" >--%>
<%--							<input type="file" id="excelUpload1" name="excel" onchange="angular.element(this).scope().excelUpload()" accept=".xlsx" style="border-bottom: 1px solid black;">--%>
<%--						</form>--%>
<%--						<button class="btn btn-outline-secondary btn-sm" ng-click="excelClick()"><i class="xi-file-upload-o"></i> EXCEL업로드</button>--%>
						<div class="btn btn-sm" style="padding:0;">
							<input ng-model="file_path" style="border:0px;border-bottom: 1px solid gray;width: 100px;" disabled=disabled">
							<label class="btn btn-outline-secondary btn-sm" style="margin:0;">업로드
							<input class="btn btn-outline-secondary" type="file" id="ex_filename" onchange="angular.element(this).scope().fileUpload(this.value)" hidden>
							</label>
						</div>



						<button class="btn btn-outline-secondary btn-sm" ng-click="formDown()"><i class="xi-file-download-o"></i> 양식</button>
						<button class="btn btn-primary btn-sm" ng-click="modalOpen('assetReg')">자산 등록</button>
						<button class="btn btn-secondary btn-sm" ng-click="assetUpdate('DEL')">삭제</button>
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
							<td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})" ></td>
							<td ng-click="modalOpen('assetDetail', value.assetControlCode)">{{value.assetControlCode}}</td>
							<td ng-click="modalOpen('assetDetail', value.assetControlCode)" style="text-align: left;">{{value.assetName}}</td>
							<td>{{value.assetDivision }}</td>
							<td>{{value.assetStatus}}</td>
							<td>{{value.mappingYn}}</td>
							<td>{{value.assetDept}}</td>
							<td>{{value.assetRegDate | date:'yyyy-MM-dd HH:mm'}}</td>
							<td>{{value.assetRegPerson}}</td>
							<td>{{value.assetLocation}}</td>
							<td>{{value.assetQuantity}} </td>
						</tr>
					</tbody>
				</table>
				</div>

				<div class="row d-flex justify-content-center">
				<!-- 네비게이션 바 -->
					<nav class=" text-center" >
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

				</div>
			</div>
		</div>
	</section>
</div>
</html>


