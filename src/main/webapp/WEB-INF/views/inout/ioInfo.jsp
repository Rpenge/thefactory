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
					<button class="btn btn-outline-secondary add-tabs-btn" ng-class="{'active-btn-2' : tab.stk}" ng-click="tabChange('stk')">판매/배송</button>
					<div class="div-fade-in" ng-show="divFadeIn" style="width: 100%;height:100%;margin-top:7px;padding:30px 10px 10px 10px;border:1px solid lightgray;overflow: hidden;">

						<span style="font-size:20px;font-weight: bold;">2021.04.01 논현본점 입고내역</span>
						<table class="table-bordered table-hover text-center custom-align-middle" style="width:100%;margin-top:20px;">
							<thead>
							<tr style="height: 35px;">
								<th style="width:10%">일자</th>
								<th style="width:10%">매장</th>
								<th style="width:10%">구분</th>
								<th style="width:35%">상품명</th>
								<th style="width:15%">수량</th>
								<th style="width:20%">등록일시</th>
							</tr>
							</thead>
							<tbody>
							<tr ng-repeat="(key, value) in [1,2,3,4,5,6,7,8,9,10]" class="pointer">
								<td style="height: 45px;">2021.04.01</td>
								<td ng-click="btnTabOn()">논현본점</td>
								<td>신규입고</td>
								<td>400</td>
								<td>1000</td>
								<td>2021.04.01 11:12:11</td>
							</tr>
							</tbody>
						</table>

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
									<%--                            <li ng-repeat="pageNum in [begin, end] | makeRange" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>--%>
									<li ng-repeat="pageNum in [1,2,3,4,5]" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
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

				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 입출고 내역 조회</span>
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
					<table id="listTable" class="table-bordered table-hover text-center custom-align-middle" style="min-width:1100px;width:100%;">
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
						<tr ng-repeat="(key, value) in list" class="pointer">
							<td >{{value.inOutDate}}</td>
							<td ng-click="addTabsOn()" style="height: 45px;">{{value.storeCd | code : store}}</td>
							<td>{{value.inNewcnt}}</td>
							<td>{{value.inMovcnt}}</td>
							<td>{{value.inIncnt}}</td>
							<td>{{value.inRetcnt}}</td>
							<td>{{value.inTotcnt}}</td>
							<td>{{value.outOutcnt}}</td>
							<td>{{value.outMovcnt}}</td>
							<td>{{value.outTotcnt}}</td>
							<td>{{value.sellStcnt}}</td>
							<td>{{value.sellOnlcnt}}</td>
							<td>{{value.sellTotcnt}}</td>
							<td>{{value.stockTotcnt}}</td>
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
							<%--                            <li ng-repeat="pageNum in [begin, end] | makeRange" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>--%>
							<li ng-repeat="pageNum in [1,2,3,4,5]" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
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


<script>
	$(document).ready(function() {
		$('#listTable').rowspan (0);
	});

</script>
