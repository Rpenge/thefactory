<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">

<div ng-show="authenticated" style="width:90%;margin:0 auto;">
	<div class="container-fluid d-flex justify-content-center" style="padding:0!important;" ng-show="!mainPage">
		<div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;">
			<h4><a class="menuName" href="" ng-click="reload()">{{currentMenu.PGM_NM}}</a></h4>
		</div>
		<ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;">
			<li><a href="">{{currentMenu | menuGroup}}</a></li> &nbsp;>&nbsp;
			<li><a href="" >{{currentMenu.PGM_NM}}</a></li>
		</ol>
	</div>

	<!--상단 검색 보드-->
	<div class="d-flex container-fluid body-custom flex-column" style="width:100%;min-height: 80px;padding:5px 3%;">
		<!-- 검색 -->
		<div class="d-flex justify-content-center" style="width: 100%;">
			<p style="margin:20px 10px;">소속매장</p>
			<select class="form-control" ng-model="searchGroup.storeCd" style="width:200px;margin: 10px;height: 40px;">
				<option value="">매장</option>
				<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
			</select>

			<p style="margin:20px 10px;">회원등급</p>
			<select class="form-control" ng-model="searchGroup.grade" style="width:200px;margin: 10px;height: 40px;">
				<option value="">회원등급</option>
				<option ng-repeat="value in grade" ng-if="(value.commCd!='010101')||(role=='010101')" value="{{value.commCd}}">{{value.commCdNm}}</option>
			</select>

			<p style="margin:20px 10px;">회원상태</p>
			<td>
				<select class="form-control" ng-model="searchGroup.userStat" style="width:200px;margin: 10px;height: 40px;">
					<option value="">상태</option>
					<option value="Y">사용</option>
					<option value="N">탈퇴</option>
				</select>
			</td>

			<button class="btn btn-outline-secondary" ng-click="searchBtn('group')" style="width:70px;margin:10px 30px;">검색</button>

			<div class="d-flex" style="width:300px;margin: 10px;">
				<input type="text" class="form-control" ng-model="searchWord.word" my-enter="searchBtn('word')" placeholder="ID 또는 이름을 입력하세요" style="height: 40px;border:0;border-bottom: 1px solid gray;">
				<button class="btn" ng-click="searchBtn('word')" style="position:relative;left:-40px;background: transparent;">
					<i class="xi-search" style="font-size: 20px;"></i>
				</button>
			</div>
		</div>
	</div>

	<section class="d-flex justify-content-center">
		<!--contents-->
		<div class="container-fluid body-custom" style="width:100%;">
			<div class="body-contents">

				<form ng-submit="formSave()">
					<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;height:50px;">
						<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 사용자 추가/수정 <span style="color:red;font-size:15px;"> * 표시는 필수 입력 항목입니다.</span></span>

						<button class="p-2 btn btn-outline-secondary top-rad-btn" ng-class="{'active-btn' : es.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('userList')" style="width:60px;">신규</button>
						<button class="p-2 btn btn-outline-secondary top-rad-btn" ng-class="{'active-btn' : es.pwForm}" ng-click="formChange('pw')" style="width:125px;">비밀번호변경</button>
						<button class="p-2 btn btn-outline-secondary top-rad-btn" type='submit' style="width:60px;">저장</button>
					</div>

					<table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
						<tr>
							<th style="width:10%;height:40px"><span style="color:red;" ng-if="es.newForm || es.modForm">*</span> 매장</th>
							<td style="width:15%;">
								<select class="form-control" ng-model="form.storeCd" ng-readonly="es.pwForm" ng-required="true">
									<option value="">매장</option>
									<option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
								</select>
							</td>
							<th style="width:10%;"><span style="color:red;" ng-if="es.newForm">*</span> ID</th>
							<td style="width:15%;padding: 0;"><input type="text" class="form-control" maxlength="12" ng-model="form.userId" ng-readonly="es.pwForm || es.modForm" placeholder="ID" ng-required="true"></td>
							<th style="width:10%"><span style="color:red;" ng-if="es.newForm || es.pwForm">*</span> Password</th>
							<td style="width:15%"><input type="password" class="form-control"  ng-model="form.userPwd" ng-readonly="es.modForm" placeholder="*****" ng-required="es.newForm || es.pwForm"></td>
							<th style="width:10%"><span style="color:red;" ng-if="es.newForm || es.pwForm">*</span> Password 확인</th>
							<td style="width:15%"><input type="password" class="form-control"  ng-model="pwCheck" ng-readonly="es.modForm" placeholder="*****" ng-required="es.newForm || es.pwForm"></td>
						</tr>
						<tr>
							<th style="width:10%"><span style="color:red;" ng-if="es.newForm || es.modForm">*</span> 이름</th>
							<td style="width:15%"><input type="text" class="form-control" maxlength="10" ng-readonly="es.pwForm" ng-model="form.userNm" ng-required='true'></td>
							<th>직급</th>
							<td><input type="text" class="form-control" maxlength="20" ng-readonly="es.pwForm" ng-model="form.userRankCd"></td>
							<th>전화번호</th>
							<td><input type="text" class="form-control" maxlength="20" ng-readonly="es.pwForm" ng-model="form.userPhone"></td>
							<th>E-MAIL</th>
							<td><input type="text" class="form-control" maxlength="50" ng-readonly="es.pwForm" ng-model="form.userEmail"></td>
						</tr>
						<tr>

							<th><span style="color:red;" ng-if="es.newForm || es.modForm">*</span> 권한</th>
							<td>
								<select class="form-control" ng-model="form.grade" ng-readonly="es.pwForm" ng-required='true'>
									<option value="">권한</option>
									<option ng-repeat="value in grade" ng-if="(value.commCd!='010101')||(role=='010101')" value="{{value.commCd}}">{{value.commCdNm}}</option>
								</select>
							</td>
							<th>PDA사용</th>
							<td><select class="form-control"  ng-model="form.pdaUseYn" ng-readonly="es.pwForm">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
							</select></td>

							<th>상태</th>
							<td><select class="form-control"  ng-model="form.userStat" ng-init="form.userStat='Y'" ng-readonly="es.pwForm">
								<option value="Y" >사용</option>
								<option value="N">탈퇴</option>
							</select></td>
							<th>등록자</th>
							<td><input type="text" class="form-control" ng-readonly="true" ng-model="form.regUserId"></td>
						</tr>
					</table>
				</form>

				<div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
					<span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 사용자 리스트</span>
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

					<button class="btn btn-danger p-2 table-top-btn" ng-click="tableBtn('Withdrawal')">탈퇴</button>
				</div>
				<!-- 테이블 생성 -->
				<div class="table-box">
				<table id="userList" class="table custom-table-1 table-hover text-center table table-striped-odd custom-align-middle" style="min-width:1100px;">
					<thead>
						<tr class="pointer">
<%--							<th><input type="checkbox" ></th>--%>
							<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'perMemberNo')"></th>
							<th ng-click="sort('')" >번호</th>
							<th ng-click="sort('')" >아이디</th>
							<th ng-click="sort('')" >이름</th>
							<th ng-click="sort('')" >소속매장</th>
							<th ng-click="sort('')" >직급</th>
							<th ng-click="sort('')" >회원등급</th>
							<th ng-click="sort('')" >PDA사용여부</th>
							<th ng-click="sort('')" >전화번호</th>
							<th ng-click="sort('')" >이메일</th>
							<th ng-click="sort('')" >상태</th>
							<th ng-click="sort('')" >등록자</th>
							<th ng-click="sort('')" >등록일자</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="value in list" class="pointer" ng-init="value.isSelected = false;">
							<td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox(!{{value.isSelected}}, {{value.perMemberNo}})" ></td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.perMemberNo}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.userId}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.userNm}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.storeCd | code: store}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.userRankCd}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.grade | code: grade}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.pdaUseYn | YnWord: 2}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.userPhone}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.userEmail}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))" ng-class="{'text-danger' : value.userStat=='N'}">{{value.userStat | YnWord: 1}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regUserId}}</td>
							<td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.reqJoinDate | date:'yyyy-MM-dd HH:mm'}}</td>
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


