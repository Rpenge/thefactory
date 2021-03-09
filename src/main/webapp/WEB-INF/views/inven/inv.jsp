<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents ">

				<div>
					<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
						<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 재고실사작업</span>
						<button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
						<button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
					</div>

					<table class="table-bordered" style="width:100%;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
						<tr>
							<th style="width:10%;height:40px">실사일자</th>
							<td style="width:15%;padding: 0;"><input type="text" class="form-control" placeholder="2021.04.01" ></td>
							<th style="width:10%">시작일</th>
							<td style="width:15%"><input type="text" class="form-control" placeholder="논현본점"></td>
							<th style="width:10%">종료일</th>
							<td style="width:15%"><input type="text" class="form-control" placeholder=""></td>
							<th style="width:10%">완료여부</th>
							<td style="width:15%"><input type="text" class="form-control" placeholder="수입입고"></td>
						</tr>
						<tr>
							<th style="height:40px">실사수량</th>
							<td><input type="text" class="form-control" placeholder="BALENCIAGA"></td>
							<th>RFID수량</th>
							<td><input type="text" class="form-control" placeholder="여자"></td>
							<th>EC수량</th>
							<td><input type="text" class="form-control" placeholder="아우터"></td>
							<th>등록자</th>
							<td><input type="text" class="form-control" placeholder="M"></td>
						</tr>

					</table>

				</div>



				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 재고실사 목록</span>
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

					<button class="btn btn-outline-secondary p-2 table-top-btn" >확정</button>
					<button class="btn btn-secondary p-2 table-top-btn" ng-click="assetUpdate('DEL')">삭제</button>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
					<table class="table custom-table-1 table-hover text-center custom-align-middle" style="min-width:1100px;">
						<thead>
							<tr class="pointer">
								<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
								<th>실사일자</th>
								<th>실사내역</th>
								<th>시작일</th>
								<th>종료일</th>
								<th>매장</th>
								<th>실사수량</th>
								<th>등록자</th>
								<th>수정자</th>
								<th>완료여부</th>
								<th>상세내역이동</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="(key, value) in [1,2,3,4,5,6,7,8,9,10]" class="pointer" ng-init="value.isSelected = false;">
								<td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})" ></td>
								<td>2021.04.01</td>
								<td>재고전체조사</td>
								<td>2021.04.01</td>
								<td>2021.04.01</td>
								<td>논현본점</td>
								<td>1212</td>
								<td>systemk</td>
								<td></td>
								<td><i class="xi-check" style="color:lightgreen;font-weight: bolder;" ></i></td>
								<td><button class="btn btn-outline-secondary"><i class="xi-share"></i></button></td>

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


