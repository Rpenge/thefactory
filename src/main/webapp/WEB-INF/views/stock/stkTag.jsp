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
						<select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;">
							<option value="10">10개씩 보기</option>
							<option value="20">20개씩 보기</option>
							<option value="50">50개씩 보기</option>
						</select>

						<h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6>
					</div>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box" style="overflow: auto;">
					<table class="table-bordered table-striped-odd" id="listTable" style="min-width:1450px;text-align: center;font-size:13px;" >
						<thead style="background: whitesmoke;">
							<tr>
								<th rowspan="2" style="width:65px;height:42px;">NO</th>
								<th rowspan="2" style="width:85px;">매장명</th>
								<th rowspan="2" style="width:420px;">품목명</th>
								<th rowspan="2" style="width:80px;">입고유형</th>
								<th rowspan="2" style="width:180px;">제품코드</th>
								<th rowspan="2" style="width:110px;">태그ID</th>
								<th rowspan="2" style="width:85px;">size</th>
								<th rowspan="2" style="width:110px;">바코드</th>
								<th rowspan="2" style="width:85px;">EC수량</th>
								<th rowspan="2" style="width:95px;">rfid수량</th>
								<th rowspan="2">입고일</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in list">
								<td>{{value.AC_STOCK_SEQ}}</td>		<!--NO-->
								<td>{{value.STORE_CD | code:store}}</td>		<!--매장명-->
								<td style="text-overflow:ellipsis;padding:5px;font-size: 12px">{{value.TF_PRD_NM}}</td>		<!--품목명-->
								<td style="height: 60px;">{{value.ST_IN_TYPE | code:commCode}}</td><!--입고유형-->
								<td>{{value.TF_PRD_CD}}</td>					<!--제품코드-->
								<td>{{value.TF_PRD_TAGID }}</td>				<!--태그ID-->
								<td>{{value.PRD_SIZE}}</td>						<!--size-->
								<td>{{value.TF_PRD_BARCODE}}</td>				<!--바코드-->
								<td>{{value.REAL_STOCK_CNT}}</td>				<!--EC수량-->
								<td style="background: lavenderblush">{{value.RFID_STOCK_CNT}}</td>	<!--rfid수량-->
								<td>{{value.REG_DATE | date:'yyyy-MM-dd HH:mm'}}</td><!--입고일-->
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



