<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">

<div class="modal-body" ng-show="authenticated">
		<h4 style="font-weight: bold;">자산 수리 등록</h4>
		<div>
			<form class="form-inline search-form" ng-submit="clickSearch()">
        	<table class="table-div-10 table-bordered text-center col-12">
				<thead >
					<tr style="visibility:collapse;">
						<th ng-repeat="x in [].constructor(10) track by $index"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2" class="table-content" >수리 목록</td>
						<td colspan="8" >
							<ul style="max-height:160px;overflow:auto;text-align:left;margin:0;">
								<li style="list-style:none;">※선택 수량 : {{list.length}}</li>
								<li ng-repeat="value in list.item">
									관리번호 : {{value.assetControlCode}} / 자산명 : {{value.assetName}} / {{value.assetDivision | code: commonCode}}
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="table-content" >날짜 설정</td>
						<td class="table-content">시작일</td>
						<td colspan="3">
							<div class="row input-group" style="margin: 0;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="stDt" is-open="startDateOpened" ng-init="today()" datepicker-options="dateOptions"/>
				        		<span class="input-group-append" >
				            		<button type="button" class="btn btn-secondary" ng-click="startDateOpened=true"> <i class="xi-calendar"></i></button>
				        		</span>
				        	</div>
			        	</td>
			        	<td class="table-content">종료일</td>
			        	<td colspan="3">
			        		<div class="row input-group" style="margin: 0;">
								<input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="edDt" is-open="endDateOpened" ng-init="today()" datepicker-options="dateOptions"/>
				        		<span class="input-group-append">
				            		<button type="button" class="btn btn-secondary" ng-click="endDateOpened=true"> <i class="xi-calendar"></i></button>
				        		</span>
				        	</div>
			        	</td>
					</tr>
					<tr>
						<td class="table-content" colspan="2">수리업체</td>
						<td class="reg-form-td" colspan="3">
						<!-- <input class="form-control fit" ng-model="list.rpCpn"> -->
							<select class="form-control fit" ng-model="list.rpCpn">
								<option value="">업체</option>
								<option ng-repeat="value in company" value="{{value.cpnCd}}">{{value.cpnNm}}</option>
							</select>
						</td>
						<td class="table-content" colspan="2">견적</td>
						<td class="reg-form-td" colspan="3"><input class="form-control fit" ng-model="list.rpEm"></td>
					</tr>
					
					<tr>
 						<td class="table-content reg-form-div" colspan="2">수리 사유</td>
						<td class="reg-form-td" colspan="3">
							<select class="form-control fit" ng-model="list.rpCtt">
								<option value="">분류</option>
								<option value="">작동 이상</option>
								<option value="">교체</option>
								<option value="">노후화</option>
								<option value="">불량</option>
							</select>
						</td>
						<td class="table-content" colspan="2">수량</td>
						<td class="reg-form-td" colspan="3"><input class="form-control fit" ng-model="list.rpQtt"></td>
					</tr>
					<tr>
 						<td class="table-content reg-form-div" colspan="2">진행 상태</td>
						<td class="reg-form-td" colspan="3">
							<select class="form-control fit" ng-model="list.rpSt">
								<option value="">상태</option>
								<option value="">수리예정</option>
								<option value="">수리진행중</option>
								<option value="">수리완료</option>
							</select>
						</td>
						<td class="table-content" colspan="2">등록자</td>
						<td class="reg-form-td" colspan="3"><input class="form-control fit" ng-model="list.rpRegPrsn"></td>
					</tr>
					
					<tr>
						<td class="table-content" colspan="2">상세 내역</td>
						<td colspan="8"><textarea class="form-control fit" ng-model="list.rpDt" style="min-height:100px;" ></textarea></td>
					</tr>
					
				</tbody>
			</table>
        	</form>
			<div class="d-flex justify-content-end">
				<button class="btn btn-primary right-bottom-btn" ng-click="upload()">저장</button>
				<button class="btn btn-danger right-bottom-btn" ng-click="close()">닫기</button>
			</div>
		</div>
</div>
</html>


