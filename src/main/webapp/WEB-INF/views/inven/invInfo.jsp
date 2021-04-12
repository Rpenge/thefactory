<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

	<section class="d-flex justify-content-center">

		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents ">

				<form ng-submit="formSave()" ng-if="search.stInvSeq != null">
					<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
						<span class="mr-auto p-2" style="font-size: 22px;color:gray;">
							<i class="xi-caret-down-circle-o"></i> 재고실사작업
							<span  style="color:black;font-size:15px;">{{list[0].invStoreCd | code:store}} {{list[0].stInvDate}}</span>
						</span>
<%--						<button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>--%>
						<button class="p-2 btn btn-outline-secondary" type='submit' style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
					</div>

					<table class="table-bordered" style="width:100%;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
						<tr>
							<th style="width:10%;height:40px">실사일자</th>
							<td style="width:15%;padding: 0;"><input type="text" class="form-control" ng-model="form.stInvDate" disabled></td>
							<th style="width:10%">매장</th>
							<td style="width:15%">
								<select class="form-control" ng-model="form.invStoreCd" disabled>
									<option value="">매장</option>
									<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
								</select>
							</td>
							<th style="width:10%">바코드</th>
							<td style="width:15%"><input type="text" class="form-control" ng-model="form.stInvDate" disabled></td>
							<th style="width:10%">태그ID</th>
							<td style="width:15%"><input type="text" class="form-control" ng-model="form.tfPrdTagid" disabled></td>
						</tr>
						<tr>
							<th>사이즈</th>
							<td><input type="text" class="form-control" ng-model="form.prdSize" disabled></td>
							<th>자체상품코드</th>
							<td><input type="text" class="form-control" ng-model="form.tfPrdCd" disabled></td>
							<th>등록자</th>
							<td><input type="text" class="form-control" ng-model="form.regId" disabled></td>
							<th style="height:40px">등록일시</th>
							<td><input type="text" class="form-control" uib-datepicker-popup="{{'yyyy-MM-dd HH:mm'}}" ng-model="form.regDate" disabled></td>
						</tr>
						<tr>
							<th>자체상품명</th>
							<td colspan="3"><input type="text" class="form-control" ng-model="form.tfPrdNm" disabled></td>
							<th style="height:40px">처리작업</th>
							<td>
								<select class="form-control" ng-model="form.misWork" ng-disabled="form.invYn=='Y'">
									<option value="">실사확정</option>
									<option ng-repeat="value in commCode" ng-if="value.codeLevel=='S'&&(value.commCd.indexOf('0602')==0 || value.commCd.indexOf('0603')==0)" value="{{value.commCd}}">{{value.commCdNm}}</option>
								</select>
							</td>
							<th>처리내용</th>
							<td><input type="text" class="form-control" ng-model="form.invComment" ng-disabled="form.invYn=='Y'"></td>
						</tr>
					</table>
				</form>

				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 재고실사 상세목록</span>
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

					<div class="d-flex" ng-if="search.stInvSeq != null" style="margin-right: 10px;border:1px solid gray;padding:0 0 0 3px;border-radius: 5px">
						<select class="form-control input-group-append" ng-model="select.misWork" style="border:0px;">
							<option value="">실사확정</option>
							<option ng-repeat="value in commCode" ng-if="value.codeLevel=='S'&&(value.commCd.indexOf('0602')==0 || value.commCd.indexOf('0603')==0)" value="{{value.commCd}}">{{value.commCdNm}}</option>
						</select>
						<button class="btn btn-outline-secondary" ng-click="tableBtn('confirm')" style="border-radius: 0 5px 5px 0;border:0;border-left: 1px solid gray;">일괄확정</button>
					</div>

<%--					<button class="btn btn-secondary p-2 table-top-btn" ng-click="assetUpdate('DEL')">삭제</button>--%>
					<div class="btn-group btn-group-toggle" data-toggle="buttons">
						<label class="btn btn-secondary" ng-class="{'active': search.stInvSeq==null}" ng-click="stkDif()" style="width:80px;">
							<input type="radio" name="options"  autocomplete="off" > Total
						</label>
						<label class="btn btn-secondary" ng-class="{'active': search.stInvSeq!=null}" ng-click="stkDif('dis')" style="width:110px;">
							<input type="radio" name="options"  autocomplete="off"> Discordance
						</label>
					</div>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
					<table id="deviceList" class="table custom-table-1 table-hover text-center custom-align-middle table table-striped-odd" style="min-width:1100px;">
						<thead>
						<tr class="pointer">
							<th style="width:30px;"><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'stInvenSeq')"></th>
							<th style="width:100px;">실사일자</th>
							<th style="width:90px;">매장</th>
							<th  style="width:110px;">바코드</th>
							<th style="width:90px;">사이즈</th>
							<th style="width:270px;">자체상품명</th>
							<th style="width:110px;">자체상품코드</th>
							<th style="width:130px;">태그ID</th>
							<th style="width:110px;">등록일시</th>
							<th style="width:100px;">처리작업</th>
							<th style="width:100px;">처리내용</th>
							<th style="width:90px;">확정여부</th>
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="(key, value) in list" class="pointer" ng-init="value.isSelected = false;">
							<td style="padding:13px;"><input type="checkbox" ng-model="value.isSelected" ng-class="{'check-disabled' : value.invYn=='Y'}" ng-change="checkBox(!{{value.isSelected}}, {{value.stInvenSeq}})" ></td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.stInvDate}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.invStoreCd | code:store}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.btPrdBarcode}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.prdSize}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.tfPrdNm}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.tfPrdCd}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.tfPrdTagid}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regDate | date:'yyyy-MM-dd HH:mm'}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.misWork | code: commCode}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.invComment}}</td>
							<td>
								<i class="xi-check" ng-if="value.invYn=='Y'" style="color:limegreen;font-weight: bolder;"></i>
								<i class="xi-close" ng-if="value.invYn=='N'" style="color:red;font-weight: bolder;"></i>
							</td>
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


