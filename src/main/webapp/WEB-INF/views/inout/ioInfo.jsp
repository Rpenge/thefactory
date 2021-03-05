<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents ">


				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 입출고 내역 조회</span>
				</div>

				<!--테이블 상단 구성-->
				<div class="d-flex" style="margin:10px 0;">
					<div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;">
						<select class="custom-select" ng-model="sizeValue" ng-change="pageSize(sizeValue)" style="width:150px;margin-right: 10px;">
							<option value="">10개씩 보기</option>
							<option value="20">20개씩 보기</option>
							<option value="50">50개씩 보기</option>
							<option value="total" style="color: red;">전체보기</option>
						</select>

						<h6 class="align-self-center">TOTAL ( 10 )</h6>
					</div>

					<button class="btn btn-secondary p-2 table-top-btn" ng-click="assetUpdate('DEL')">삭제</button>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
					<table class="table custom-table-1 table-hover text-center custom-align-middle" style="min-width:1100px;">
						<thead>
						<tr class="pointer">
							<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
							<th>No</th>
							<th>일자</th>
							<th>매장</th>
							<th>총입고수량</th>
							<th>총출고수량</th>
							<th>총재고수량</th>
							<th>등록일</th>
							<th>등록자</th>
							<th>수정일</th>
							<th>수정자</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="(key, value) in [1,2,3,4,5,6,7,8,9,10]" class="pointer" ng-init="value.isSelected = false;">
							<td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})" ></td>
							<td>{{value}}</td>
							<td>2021.04.01</td>
							<td>논현본점</td>
							<td>500</td>
							<td>400</td>
							<td>1000</td>
							<td>2021.04.22</td>
							<td>systemk</td>
							<td></td>
							<td></td>
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


