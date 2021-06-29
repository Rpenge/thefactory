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
<%--					<button class="btn btn-primary mobile-none" ng-click="formDown()" ng-if="hiddenFunction==true"><i class="xi-file-download-o"></i> 양식</button>--%>
<%--					<form method="post" id="excelForm" action="upload" enctype="multipart/form-data" onsubmit="return false" ng-if="role=='010101' || role=='010102'">--%>
<%--						<div class="input-group btn p-2" style="width:330px;margin:0px;padding:0!important;height:40px;">--%>
<%--							<input ng-model="file_path" style=";border: 1px solid lightgray;width: 180px;background: white;border-radius: 5px 0 0 5px;" disabled=disabled">--%>
<%--							<div class="input-group-append">--%>
<%--								<label class="btn btn-outline-secondary" style="margin:0;border:1px solid lightgray;border-left:0px">찾기--%>
<%--									<input class="btn-outline-secondary" type="file" name="excelFile" onchange="angular.element(this).scope().fileUpload(this.value)" accept=".xlsx" hidden>--%>
<%--								</label>--%>
<%--							</div>--%>
<%--							<button class="btn btn-outline-secondary" ng-click="upload()" style="border: 1px solid lightgray;border-radius: 0 5px 5px 0;border-left:0px">업로드</button>--%>
<%--						</div>--%>
<%--					</form>--%>

<%--					<div class="btn-group btn-group-toggle" data-toggle="buttons">--%>
<%--						<label class="btn btn-secondary active" ng-click="stkDif()" style="width:80px;">--%>
<%--							<input type="radio" name="options"  autocomplete="off" checked> 전체--%>
<%--						</label>--%>
<%--						<label class="btn btn-secondary" ng-click="stkDif('rfid')" ng-if='hiddenFunction == true' style="width:110px;">--%>
<%--							<input type="radio" name="options"  autocomplete="off"> 실재고--%>
<%--						</label>--%>
<%--						<label class="btn btn-secondary" ng-click="stkDif('dis')" style="width:110px;">--%>
<%--							<input type="radio" name="options"  autocomplete="off"> 재고차이--%>
<%--						</label>--%>
<%--					</div>--%>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box" style="overflow: auto;">
					<table class="table-bordered table-striped-odd" id="listTable" style="min-width:1450px;text-align: center;font-size:13px;" >
						<thead style="background: whitesmoke;">
							<tr>
								<th rowspan="2" style="width:65px;height:42px;">No</th>
								<th rowspan="2" style="width:400px;">품목명</th>
								<th rowspan="2" style="width:75px;">매장</th>
								<th rowspan="2" style="width:150px;">브랜드</th>
								<th rowspan="2" style="width:75px;">성별</th>
								<th rowspan="2" style="width:80px;">상품구분</th>
								<th rowspan="2" style="width:75px;">사이즈</th>
								<th rowspan="2" style="width:110px;">바코드</th>
								<th rowspan="2">EC재고</th>
								<th rowspan="2">실재고</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in list">
								<td style="height: 60px;">{{value.STOCK_REG_SEQ}}</td><!--No-->
								<td style="text-overflow:ellipsis;padding:5px;font-size: 12px">{{value.TF_PRD_NM}}</td>	<!--품목명-->
								<td>{{value.STOCK_STORE_NM}}</td>			<!--매장명-->
								<td>{{value.brandNm}}</td>					<!--브랜드-->
								<td>{{value.genderNm}}</td>					<!--성별-->
								<td>{{value.clsNm}}</td>					<!--분류명-->
								<td>{{value.PRD_SIZE}}</td>					<!--size-->
								<td>{{value.TF_PRD_BARCODE}}</td>			<!--size-->
								<td>{{value.REAL_STOCK_CNT}}</td>		<!--EC수량-->
								<td style="background: lavenderblush">{{value.RFID_STOCK_CNT}}</td>		<!--rfid시스템수량-->
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



