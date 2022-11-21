<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--     pageEncoding="utf-8"%> --%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html ng-app="myApp"> -->


<!-- <div ng-show="authenticated"> -->

<!-- 	<section class="d-flex justify-content-center" ng-if="currentMenu.AUTH_EXCEL_YN=='Y'"> -->
<!-- 		<div class="container-fluid body-custom" style="width:100%;"> -->
<!-- 			<div style="padding:20px 5px;"> -->

<!-- 				<div class="d-flex"> -->
<!-- 					<span style="font-size: 20px;color:gray;margin:4px 25px 0 0;"><i class="xi-file-download-o"></i> 보유재고 내역 다운로드</span> -->

<!-- 					<select class="custom-select" ng-model="excelForm.storeCd" ng-init="excelForm.storeCd=''" style="width:150px;margin: 0 10px;"> -->
<!-- 						<option value="">매장</option> -->
<!-- 						<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option> -->
<!-- 					</select> -->

<!-- 					<input class="form-control" id="brandSearch" style="width:220px;margin: 0 10px;height: 40px;"  placeholder="브랜드" ng-model="eView.brand" ng-click="xs = xs==true ? false : true"  readonly> -->
<!-- 					<label class="d-flex justify-content-between" style="position:relative;left:-40px;top:5px;width:0px;" > -->
<!-- 						<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-down" ng-show="!xs"></i> -->
<!-- 						<i style="margin: 10px;font-size: 11px;font-weight: bolder;" class="xi-angle-up" ng-show="xs"></i> -->
<!-- 					</label> -->
<!-- 					<div style="width:100%;min-height:150px;background:ghostwhite;position:absolute;margin-top:65px;left:0px;z-index: 3;padding: 20px;border-radius:5px;box-shadow: 1px 1px 5px 1px lightgray;" ng-show="xs"> -->
<!-- 						<div onclick="tableTdDel('eBrandTb')" ng-click="xs=false;excelBrand()" style="position:absolute;right:10px;top:5px;width:25px;color:gray;border:1px solid lightgray;border-radius:4px;text-align: center;cursor: pointer;">X</div> -->
<!-- 						<table id="eBrandTb" ng-click="xs=false" style="width:98%;vertical-align: top;"> -->
<!-- 							<tr ng-repeat="(key, value) in brandList" ng-if="key % 7 == 0"> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key])">{{brandList[key].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+1])" ng-if="brandList[key+1]">{{brandList[key+1].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+2])" ng-if="brandList[key+2]">{{brandList[key+2].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+3])" ng-if="brandList[key+3]">{{brandList[key+3].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+4])" ng-if="brandList[key+4]">{{brandList[key+4].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+5])" ng-if="brandList[key+5]">{{brandList[key+5].brandNm}} </td> -->
<!-- 								<td class="select-table-pointer" onclick="selectTd($(this))" ng-click="excelBrand(brandList[key+6])" ng-if="brandList[key+6]">{{brandList[key+6].brandNm}} </td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</div> -->

<!-- 					<button class="btn btn-success btn-arr" ng-click="excelDown()"><i class="xi-file-download-o"></i> EXCEL </button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->

<!-- 	<section class="d-flex justify-content-center"> -->

<!-- 		<!--contents--> -->
<!-- 		<div class="container-fluid body-custom" style="width:100%;"> -->
<!-- 			<div class="body-contents"> -->
<!-- 				<form ng-submit="formSave()"> -->
<!-- 					<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;"> -->
<!-- 						<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 재고상품관리 </span> -->
<%-- 												<button class="p-2 btn btn-outline-secondary top-rad-btn" ng-class="{'active-btn' : es.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('inputTable')" style="width:60px;">신규</button> --%>
<!-- 						<button class="p-2 btn btn-outline-secondary top-rad-btn" type='submit' ng-disabled="es.newForm" style="width:60px;">저장</button> -->
<!-- 					</div> -->

<!-- 					태그ID 검색, 입고예정 매장 선택 , 출고유형선택 -->
<!-- 					<table class="table-bordered" style="width:100%;height:80px;text-align: center;background: whitesmoke;margin:10px 0 10px 0"> -->
<!-- 						<tr> -->
<!-- 							<th style="width:10%;height:40px"> 태그ID</th> -->
<!-- 							<td class="d-flex" style="padding: 0;"><input type="text" class="form-control" ng-change="es.newForm=true;" ng-model="form.tfPrdTagid" ng-required="true" disabled> -->
<%-- <%--								<button class="btn btn-secondary" type="button" ng-click="inputAdd()" style="width:25%;padding:0px;opacity: 85%"><i class="xi-search" style="font-size: 20px;"></i></button>--%> --%>
<!-- 							</td> -->
<!-- 							<th style="width:10%">매장</th> -->
<!-- 							<td style="width:15%"> -->
<!-- 								<select class="form-control" ng-model="form.outStoreCd" ng-required="true" disabled> -->
<!-- 									<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 							<th style="width:10%"> 입고유형</th> -->
<!-- 							<td style="width:15%"> -->
<!-- 								<select class="form-control" ng-model="inView.ST_IN_TYPE" ng-required="true" disabled> -->
<!-- 									<option ng-repeat="value in workS" ng-if="value.commCd.substr(0,4)=='0601'" value="{{value.commCd}}">{{value.commCdNm}}</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 							<th style="width:10%">바코드</th> -->
<!-- 							<td style="width:15%"><input type="text" class="form-control" ng-model="form.barcode" disabled></td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th>사이즈</th> -->
<!-- 							<td><input type="text" class="form-control" ng-model="inView.PRD_SIZE" disabled></td> -->
<!-- 							<th>상품코드</th> -->
<!-- 							<td><input type="text" class="form-control" ng-model="inView.TF_PRD_CD" disabled></td> -->
<!-- 							<th>품목명</th> -->
<!-- 							<td colspan="3"><input type="text" class="form-control" ng-model="inView.TF_PRD_NM" disabled></td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th>입고일</th> -->
<!-- 							<td><input type="text" class="form-control" ng-model="inView.REG_DATE" disabled></td> -->
<!-- 							<th>등록자</th> -->
<!-- 							<td><input type="text" class="form-control" ng-model="inView.REG_ID" disabled></td> -->
<!-- 							<th style="background: lightslategray;color: white;border-radius:3px;">출고/판매유형</th> -->
<!-- 							<td> -->
<!-- 								<select class="form-control" ng-model="form.stOutType" ng-required="true"> -->
<!-- 									<option value="">출고/판매유형</option> -->
<!-- 									<option ng-repeat="value in workS" ng-if="value.commCd.substr(0,4)=='0602' || value.commCd.substr(0,4)=='0603'" value="{{value.commCd}}">{{value.commCdNm}}</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 							<th style="background: lightslategray;color: white;border-radius:3px;">출고사유</th> -->
<!-- 							<td><input type="text" class="form-control" ng-model="form.comment" ></td> -->
<!-- 						</tr> -->
<!-- 					</table> -->
<!-- 				</form> -->

<!-- 				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;"> -->
<!-- 					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 재고상품목록</span> -->
<!-- 				</div> -->


<!-- 				테이블 상단 구성 -->
<!-- 				<div class="d-flex" style="margin:10px 0 10px 0;"> -->
<!-- 					<div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;"> -->
<!-- 						<select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;"> -->
<!-- 							<option value="10">10개씩 보기</option> -->
<!-- 							<option value="20">20개씩 보기</option> -->
<!-- 							<option value="50">50개씩 보기</option> -->
<!-- 							<option value="100">100개씩 보기</option> -->
<!-- 							<option value="500">500개씩 보기</option> -->
<%-- <%--							<option value="{{paging.total}}">전체 보기</option>--%> --%>
<!-- 						</select> -->

<!-- 						<h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				테이블 생성 -->
<!-- 				<div class="table-box" style="overflow: auto;"> -->
<!-- 					<table class="table custom-table-1 table-hover text-center table table-striped-odd custom-align-middle" id="listTable" style="min-width:1450px;text-align: center;font-size:13px;" > -->
<!-- 						<thead style="background: whitesmoke;"> -->
<!-- 							<tr> -->
<!-- 								<th rowspan="2" style="width:65px;height:42px;">NO</th> -->
<!-- 								<th rowspan="2" style="width:85px;">매장명</th> -->
<!-- 								<th rowspan="2" style="width:120px;">태그ID</th> -->
<!-- 								<th rowspan="2" style="width:400px;">품목명</th> -->
<!-- 								<th rowspan="2" style="width:80px;">입고유형</th> -->
<!-- 								<th rowspan="2" style="width:180px;">자체상품코드</th> -->
<!-- 								<th rowspan="2" style="width:85px;">size</th> -->
<!-- 								<th rowspan="2" style="width:110px;">바코드</th> -->
<!-- 								<th rowspan="2" style="width:85px;">EC수량</th> -->
<!-- 								<th rowspan="2" style="width:110px;">동일재고수량</th> -->
<!-- 								<th rowspan="2">등록일시</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<!-- 							<tr ng-repeat="value in list" style="cursor:pointer"> -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))" style="height: 60px;">{{value.AC_STOCK_SEQ}}</td>								NO -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.STORE_CD | code:store}}</td>						매장명 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.TF_PRD_TAGID }}</td>								태그ID -->
<!-- 								<td class='table-text-left' ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.TF_PRD_NM}}</td>			품목명 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.ST_IN_TYPE | code:commCode}}</td>				입고유형 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.TF_PRD_CD}}</td>									자체상품코드 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.PRD_SIZE}}</td>									size -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.TF_PRD_BARCODE}}</td>							바코드 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.REAL_STOCK_CNT}}</td>							EC수량 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.RFID_STOCK_CNT}}</td>							rfid수량 -->
<!-- 								<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.REG_DATE | date:'yyyy-MM-dd HH:mm'}}</td>		입고일 -->
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



