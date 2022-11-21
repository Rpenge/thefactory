<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%-- 		 pageEncoding="utf-8"%> --%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html ng-app="myApp"> -->


<!-- <div ng-show="authenticated"> -->

<!-- 	<section class="d-flex justify-content-center"> -->

<!-- 		<!--contents--> -->
<!-- 		<div class="container-fluid body-custom" style="width:100%;"> -->
<!-- 			<div class="body-contents "> -->

<!-- 				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;"> -->
<!-- 					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 재고실사 목록 <span style="color:red;font-size:15px;"> * 재고실사 데이터 수정시 삭제가 불가능합니다</span></span> -->
<!-- 				</div> -->

<!-- 				테이블 상단 구성 -->
<!-- 				<div class="d-flex" style="margin:10px 0;"> -->
<!-- 					<div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;"> -->
<!-- 						<select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;"> -->
<!-- 							<option value="10">10개씩 보기</option> -->
<!-- 							<option value="20">20개씩 보기</option> -->
<!-- 							<option value="50">50개씩 보기</option> -->
<!-- 						</select> -->
<!-- 						<h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6> -->
<!-- 					</div> -->

<!-- 					<button class="btn btn-danger p-2 table-top-btn" ng-click="tableBtn('delete')">삭제</button> -->
<!-- 				</div> -->
<!-- 				테이블 생성 -->
<!-- 				<div class="table-box"> -->
<!-- 					<table class="table custom-table-1 text-center custom-align-middle table-striped-odd" style="min-width:1100px;"> -->
<!-- 						<thead> -->
<!-- 							<tr> -->
<!-- 								<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'stInvSeq')"></th> -->
<!-- 								<th>실사일자</th> -->
<!-- 								<th>매장</th> -->
<!-- 								<th>대상수량</th> -->
<!-- 								<th>실사수량</th> -->
<!-- 								<th>미실사수량</th> -->
<!-- 								<th>완료여부</th> -->
<!-- 								<th>최종수정일</th> -->
<!-- 								<th>상세내역이동</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<tr ng-repeat="(key, value) in list" ng-class="{'table-disabled' : value.stInvYn=='Y'}" ng-init="value.isSelected = false;"> -->
<!-- 								<td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-class="{'check-disabled' : value.stInvYn=='Y'}" ng-change="checkBox(!{{value.isSelected}}, {{value.stInvSeq}})" ></td> -->
<!-- 								<td>{{value.stInvDate}}</td> -->
<!-- 								<td>{{value.stInvStore | code:store}}</td> -->
<!-- 								<td>{{value.stTarCnt}}</td> -->
<!-- 								<td>{{value.stInvCnt}}</td> -->
<!-- 								<td>{{value.stTarCnt - value.stInvCnt}}</td> -->
<!-- 								<td> -->
<!-- 									<i class="xi-check" ng-if="value.stInvYn=='Y'" style="color:limegreen;font-weight: bolder;"></i> -->
<!-- 									<i class="xi-close" ng-if="value.stInvYn=='N'" style="color:red;font-weight: bolder;"></i> -->
<!-- 								</td> -->
<!-- 								<td>{{value.modDate | date : 'yyyy-MM-dd HH:mm:ss'}}</td> -->
<!-- 								<td><button class="btn btn-outline-secondary" ng-click="goInvInfo(value.stInvSeq)"><i class="xi-share"></i></button></td> -->
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 					</table> -->
<!-- 				</div> -->

<!-- 				<div class="row d-flex justify-content-center"> -->
<!-- 					네비게이션 바 -->
<!-- 					<nav class=" text-center" > -->
<!-- 						<ul class="pagination"> -->
<!-- 							<li class="page-item" ng-if="paging.current > 10"> -->
<!-- 								<a href="" aria-label="Previous" ng-click="goPage(1)" class="page-link"><span aria-hidden="true">&laquo;</span></a> -->
<!-- 							</li> -->
<!-- 							<li class="page-item" ng-if="paging.current > 10"> -->
<!-- 								<a href="" aria-label="Previous" ng-click="goPage(paging.begin - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a> -->
<!-- 							</li> -->
<!-- 							<li ng-repeat="pageNum in [paging.begin, paging.end] | makeRange" class="page-item" ng-class="{'active-page' : paging.current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li> -->
<!-- 							<li class="page-item" ng-if="paging.end != paging.last"> -->
<!-- 								<a href="" aria-label="Next" ng-click="goPage(paging.end + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a> -->
<!-- 							</li> -->
<!-- 							<li class="page-item" ng-if="paging.end != paging.last"> -->
<!-- 								<a href="" aria-label="Next" ng-click="goPage(paging.last)" class="page-link"><span aria-hidden="true">&raquo;</span></a> -->
<!-- 							</li> -->
<!-- 						</ul> -->
<!-- 					</nav> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->
<!-- </div> -->
<!-- </html> -->


