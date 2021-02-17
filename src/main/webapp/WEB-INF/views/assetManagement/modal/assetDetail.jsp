<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>자산 상세</title>
</head>

<body>
	<div style="padding:15px 35px 30px; background-color: #f1f1f1; border-radius:5px;">
	<img src="{{qr}}" style="width:70px;height:70px;float:right;"/>
	<h2 style="border-bottom:1px solid black; padding:10px;margin:30px 0 20px;font-weight:bold;">자산 상세정보</h2>

	<div class="d-flex justify-content-center" style="position:absolute;right:35px;width:355px;height:202px;overflow:hidden;background-color: #FAFAFA;border:1px solid #D8D8D8;">
		<img class="pointer" src="{{imgPath}}" id="assetImg" ng-click = "upload()" ng-if="imgIf" style="max-width:353px;height:200px;"  >
		<span class="pointer" ng-click = "upload()" ng-if="!imgIf" style="margin:auto;font-size:1.3em;color:#A4A4A4;font-weight:bold;">이미지를 등록해 주세요</span>
		<form method="post" id="imgFrm" action="upload" enctype="multipart/form-data" onsubmit="return false" >
	         <input type="file" id="fileImg" name="uploadImg" hidden>
	         <input type="text" ng-model="list.assetControlCode" name="name" hidden/>
	   </form>
	</div>

		<form class="assetReg">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" >관리번호</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetControlCode" readonly>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label" >자산명</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetName">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">모델번호</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.modelNumber">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">시리얼번호</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.serialNumber" >
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">자산 분류</label>
				<div class="col-4">
			    	<select class="custom-select col-6" ng-model="list.assetDivision" disabled>
			    		<option value="">분류</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
			    	</select>
		    	</div>

				<label class="col-sm-2 col-form-label">사용부서</label>
				<div class="col-4">
			    	<select class="custom-select col-6" ng-model="list.assetDept">
			    		<option value="">부서</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
			    	</select>
		    	</div>
		    </div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">제조사</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.manufacturer">
				</div>

				<label class="col-sm-2 col-form-label">바코드</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetBarcode" >
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">등록자</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetRegPerson" readonly>
				</div>

				<label for="" class="col-sm-2 col-form-label" >등록일</label>
				<div class="col-4 row input-group" style="left:15px;">
					<input type="text" class="form-control " uib-datepicker-popup="{{'yyyy-MM-dd HH:mm:ss'}}" ng-model="list.assetRegDate"  is-open="regDate.open"  readonly/>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">구입처</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetPurchase" >
				</div>

				<label class="col-sm-2 col-form-label">구입 가격</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetPrice"  >
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">상태</label>
				<div class="col-4">
			    	<select class="custom-select col-6" ng-model="list.assetStatus"  >
			    		<option value="">상태</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='AST'">{{list.codeName}}</option>
			    	</select>
		    	</div>

				<label class="col-sm-2 col-form-label">위치</label>
				<div class="col-4" id="locBox">
			    	<select class="custom-select col-6"  ng-model="list.assetLocation"  >
			    		<option value="">Location</option>
			      		<!-- <option  ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='LOC'">{{list.codeName}}</option> -->
			      		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
			    	</select>
		    	</div>
		    </div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">보증기간</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.warranty" >
				</div>

				<label  class="col-sm-2 col-form-label">업체 연락처</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetContact"  >
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">수량</label>
				<div class="col-4">
					<input class="form-control" ng-model="list.assetQuantity" >
				</div>

				<label class="col-sm-2 col-form-label" >상세정보</label>
				<div class="col-4">
					<textarea class="form-control"  ng-model="list.assetDetail" ></textarea>
				</div>
			</div>

			<div class="form-group" style="display:flex;justify-content:center;">
				<button class="btn btn-primary col-2" ng-click="submit()" style="margin:0 30px;">수정 </button>
				<button class="btn btn-danger col-2" ng-click="close()" style="margin:0 30px;">닫기 </button>
			</div>



			<div ng-show="list.assetChange.length > 0">
				<h5 style="font-weight:bold">자산 변동 내역</h5>
				<table class="table table-hover text-center custom-align-middle" >
					<thead>
						<tr style="background-color: lightgray">
							<th>관리번호</th>
							<th>변동내역</th>
							<th>작업위치</th>
							<th>변동일자</th>
							<th>사용자</th>
							<th>작업환경</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="value in list.assetChange" style="background-color: white">
							<td>{{value.astCtrlCd}}</td>
							<td>{{value.astWk | code: commonCode}}</td>
							<td>{{value.wkLoc | code: commonCode}}</td>
							<td>{{value.chgDt | date:'yyyy-MM-dd HH:mm'}}</td>
							<td>{{value.wkPrsn}}</td>
							<td>{{value.wkEnv}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
