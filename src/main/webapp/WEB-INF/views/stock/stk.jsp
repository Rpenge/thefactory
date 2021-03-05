<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">
	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents">


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

					<div class="input-group btn p-2" style="width:250px;margin:0px;padding:0!important;height:40px;">
						<input ng-model="file_path" style=";border: 1px solid lightgray;width: 175px;background: white;border-radius: 5px 0 0 5px;" disabled=disabled">
						<div class="input-group-append">
							<label class="btn btn-outline-secondary" style="margin:0;border:1px solid lightgray;">업로드
								<input class="btn-outline-secondary" type="file" id="ex_filename" onchange="angular.element(this).scope().fileUpload(this.value)" hidden>
							</label>
						</div>
					</div>
					<button class="btn btn-primary p-2 table-top-btn" ng-click="modalOpen('assetReg')">자산 등록</button>
					<button class="btn btn-secondary p-2 table-top-btn" ng-click="assetUpdate('DEL')">삭제</button>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
					<table class="table-bordered" id="listTable" style="width:100%;text-align: center;" >
						<thead>
							<tr>
								<th rowspan="2">No</th>
								<th rowspan="2">품목명</th>
								<th rowspan="2">매장</th>
								<th>EC재고</th>
								<th colspan="6">RFID 재고관리 시스템</th>
								<th rowspan="2">재고차이</th>
							</tr>
							<tr>
								<th>현재고</th>
								<th>입고</th>
								<th>츨고</th>
								<th>판매(온라인)</th>
								<th>판매(매장)</th>
								<th>실사수량</th>
								<th>실재고</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="key in [1,2,3,4,5,6,7,8,9,10]">
								<td style="height: 50px;">{{key}}</td>
								<td ng-if="key%3 == 0" style="text-align: left;padding-left: 10px;"> 21SS 이자벨마랑 에뚜왈 MANSEL 로고 오버핏…</td>
								<td ng-if="key%3 != 0" style="text-align: left;padding-left: 10px;"> 21SS 골든구스 여성 하이스타 화이트 실버</td>
								<td>압구정점</td>
								<td>5</td>
								<td>10</td>
								<td>5</td>
								<td>2</td>
								<td>3</td>
								<td>5</td>
								<td>5</td>
								<td>0</td>
							</tr>
							<tr >
								<td style="height: 50px;">-</td>
								<td>합계</td>
								<td>-</td>
								<td>5</td>
								<td>10</td>
								<td>5</td>
								<td>2</td>
								<td>3</td>
								<td>5</td>
								<td>5</td>
								<td>0</td>
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
<%--							<li ng-repeat="pageNum in [begin, end] | makeRange" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>--%>
							<li ng-repeat="pageNum in [1,2,3]" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
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
		$('#listTable').rowspan (1);
	});

</script>


