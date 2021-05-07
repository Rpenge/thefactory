<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents" style="padding:0;">

				<!--탭 메뉴-->
				<div class="add-tabs" ng-show="addTabs">
					<button class="btn btn-outline-secondary add-tabs-btn" ng-click="tabChange('list')" style="border-right: 0;">리스트</button>
					<button class="btn btn-outline-secondary add-tabs-btn" ng-class="{'active-btn-2' : tab.in}" ng-click="tabChange('in')">입고</button>
					<button class="btn btn-outline-secondary add-tabs-btn" ng-class="{'active-btn-2' : tab.out}" ng-click="tabChange('out')">출고</button>
					<button class="btn btn-outline-secondary add-tabs-btn" ng-class="{'active-btn-2' : tab.sell}" ng-click="tabChange('sell')">판매/배송</button>
					<div class="div-fade-in" ng-show="divFadeIn" style="width: 100%;height:100%;margin-top:7px;padding:30px 10px 10px 10px;border:1px solid lightgray;overflow: hidden;">

						<span style="font-size:20px;font-weight: bold;">{{inView.ST_DATE | dateFormCustom:'.'}} {{inView.STORE_CD | code:store}}  {{inView.ST_TYPE | code:workM}}내역</span>

						<div ng-if="subList.length == 0">
							<h3 style="color: lightgrey;margin-top:20px;"> < 해당 데이터가 존재하지 않습니다 ></h3>
						</div>
						<table class="table-bordered table-hover text-center custom-align-middle table-striped-odd" style="width:100%;margin-top:20px;" ng-if="subList.length > 0">
							<thead>
							<tr style="height: 35px;">
								<th style="width:10%">일자</th>
								<th style="width:10%">매장</th>
								<th style="width:10%">구분</th>
								<th style="width:35%">상품명</th>
								<th style="width:15%">수량</th>
							</tr>
							</thead>
							<tbody>
							<tr ng-repeat="(key, value) in subList">
								<td style="height: 45px;">{{value.ST_DATE}}</td>
								<td>{{value.STORE_CD | code:store}}</td>
								<td>{{value.ST_TYPE | code:workS}}</td>
								<td>{{value.TF_PRD_NM}}</td>
								<td>{{value.cnt}}</td>
							</tr>
							</tbody>
						</table>

						<div class="row d-flex justify-content-center" ng-if="subList.length > 0">
							<!-- 네비게이션 바 -->
							<nav class=" text-center" >
								<ul class="pagination">
									<li class="page-item" ng-if="subPaging.current > 10">
										<a href="" aria-label="Previous" ng-click="goSubPage(1)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
									</li>
									<li class="page-item" ng-if="subPaging.current > 10">
										<a href="" aria-label="Previous" ng-click="goSubPage(subPaging.begin - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
									</li>
									<li ng-repeat="pageNum in [subPaging.begin, subPaging.end] | makeRange" class="page-item" ng-class="{'active-page' : subPaging.current == pageNum}" ><a href="" ng-click="goSubPage(pageNum)" class="page-link">{{pageNum}}</a></li>
									<li class="page-item" ng-if="subPaging.end != subPaging.last">
										<a href="" aria-label="Next" ng-click="goSubPage(subPaging.end + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
									</li>
									<li class="page-item" ng-if="subPaging.end != subPaging.last">
										<a href="" aria-label="Next" ng-click="goSubPage(subPaging.last)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
									</li>
								</ul>
							</nav>
						</div>

					</div>
				</div>
				<!--탭 메뉴 종류-->

				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 입출고내역 목록</span>
				</div>

				<!--테이블 상단 구성-->
				<div class="d-flex" style="margin:10px 0;">
					<div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;">
						<select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;">
							<option value="10">10개씩 보기</option>
							<option value="20">20개씩 보기</option>
							<option value="50">50개씩 보기</option>
						</select>

						<h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6>
					</div>

				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
					<table id="listTable" class="table-bordered table-td-hover text-center custom-align-middle table-striped-odd" style="min-width:1100px;width:100%;" my-repeat-directive>
						<thead>
						<tr style="height: 35px;">
							<th rowspan="2" style="width:13%;">일자</th>
							<th rowspan="2" style="width:13%;">매장</th>
							<th colspan="5">입고</th>
							<th colspan="3">출고</th>
							<th colspan="3">판매/배송</th>
							<th rowspan="2" style="width:8%;">총재고</th>
						</tr>
						<tr style="height: 40px;">
							<th style="width:6%;">신규</th>
							<th style="width:6%;">점간</th>
							<th style="width:6%;">입고</th>
							<th style="width:6%;">반품</th>
							<th style="width:6%;">Total</th>
							<th style="width:6%;">출고</th>
							<th style="width:6%;">점간</th>
							<th style="width:6%;">Total</th>
							<th style="width:6%;">매장</th>
							<th style="width:6%;">온라인</th>
							<th style="width:6%;">Total</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="(key, value) in list"  class="pointer">
							<td>{{value.inOutDate}}</td>
							<td ng-click="addTabsOn(value)" style="height: 45px;">{{value.storeCd | code : store}}</td>
							<td ng-click="addTabsOn(value)">{{value.inNewcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.inMovcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.inIncnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.inRetcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.inTotcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.outOutcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.outMovcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.outTotcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.sellStcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.sellOnlcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.sellTotcnt}}</td>
							<td ng-click="addTabsOn(value)">{{value.stockTotcnt}}</td>
						</tr>
						</tbody>
					</table>
				</div>

				<div class="row d-flex justify-content-center">
					<!-- 네비게이션 바 -->
					<nav class=" text-center" >
						<ul class="pagination">
							<li class="page-item" ng-if="paging.current > 10">
								<a href="" aria-label="Previous" ng-click="goPage(1)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
							</li>
							<li class="page-item" ng-if="paging.current > 10">
								<a href="" aria-label="Previous" ng-click="goPage(paging.begin - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
							</li>
							<li ng-repeat="pageNum in [paging.begin, paging.end] | makeRange" class="page-item" ng-class="{'active-page' : paging.current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
							<li class="page-item" ng-if="paging.end != paging.last">
								<a href="" aria-label="Next" ng-click="goPage(paging.end + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
							</li>
							<li class="page-item" ng-if="paging.end != paging.last">
								<a href="" aria-label="Next" ng-click="goPage(paging.last)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</section>
</div>
</html>
