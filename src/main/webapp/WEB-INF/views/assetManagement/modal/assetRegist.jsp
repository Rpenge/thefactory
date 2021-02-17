<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>자산 등록</title>
</head>
<body>
	<div style="padding:35px; background-color: #f1f1f1; border-radius:5px;">
	<h2 style="border-bottom:1px solid black; padding:10px;margin-bottom:20px;">자산 등록</h2>
	
		<div class="d-flex justify-content-center" style="position:absolute;right:35px;width:250px;height:202px;overflow:hidden;background-color: #FAFAFA;border:1px solid #D8D8D8;">
			<img class="pointer" src="" id="assetImg" ng-click = "upload()" ng-if="imgIf" style="max-width:353px;height:200px;"  >
			<span class="pointer" ng-click = "upload()" ng-if="!imgIf" style="margin:auto;font-size:1.3em;color:#A4A4A4;font-weight:bold;">이미지를 등록해 주세요</span>
			<form method="post" id="imgFrm" enctype="multipart/form-data"  >
				<input type="file" id="fileImg" name="uploadImg" hidden>
				<input type="text" ng-model="list.assetControlCode" name="name" hidden/>
		   </form>
		</div>
		
		<form class="assetReg">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" >자산명</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetName" required>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">모델번호</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.modelNumber">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">시리얼번호</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.serialNumber" >
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">제조사</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.manufacturer">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">자산분류</label>
				<div class="col-4">
			    	<select class="custom-select" ng-model="list.assetDivision" required>
			    		<option value="">분류</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DIV'">{{list.codeName}}</option>
			    	</select>
		    	</div>
		    	
		    	<label class="col-sm-2 col-form-label">사용부서</label>
				<div class="col-4">
			    	<select class="custom-select" ng-model="list.assetDept" required>
			    		<option value="">부서</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='DEPT'">{{list.codeName}}</option>
			    	</select>
		    	</div>
		    </div>
		    
		    
		    <div class="form-group row">
				<label class="col-sm-2 col-form-label">상태</label>
				<div class="col-4">
			    	<select class="custom-select" ng-model="list.assetStatus" required>
			    		<option value="">상태</option>
			      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode=='AST'">{{list.codeName}}</option>
			    	</select>
		    	</div>
		
				<label class="col-sm-2 col-form-label">위치</label>
				<div class="col-4" id="locBox">
			    	<select class="custom-select"  ng-model="list.assetLocation" required>
			    		<option value="">Location</option>
			      		<option ng-repeat="list in locList" value="{{list.code}}" >{{list.addNm}}</option>
			    	</select>
		    	</div>
		    </div>
		   

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">등록자</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetRegPerson" required>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="" class="col-sm-2 col-form-label" >등록일</label>
				<div class="col-5 row input-group" style="left:15px;">
					<input type="text" class="form-control " uib-datepicker-popup="{{format}}" ng-model="list.assetRegDate"  is-open="regDate.open"  />
					<button type="button" class="btn btn-secondary" ng-click="regDateOpen()" style="margin:0 0 0 -5px;"><i class="xi-calendar"></i></button>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">바코드</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetBarcode" >
				</div>
			</div>
	
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">구입처</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetPurchase" >
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">구입 가격</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetPrice"  >
				</div>
			</div>
			
			

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">보증기간</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.warranty" >
				</div>
			</div>
			
			<div class="form-group row">
				<label  class="col-sm-2 col-form-label">업체 연락처</label>
				<div class="col-5">
					<input class="form-control" ng-model="list.assetContact"  >
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">수량</label>
				<div class="col-5">
					<input type="number" class="form-control" ng-model="list.assetQuantity" min="0">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" >상세정보</label>
				<div class="col-9">
					<textarea class="form-control"  ng-model="list.assetDetail" ></textarea>
				</div>
			</div>
			
			<div class="form-group" style="display:flex;justify-content:center;">
				<button class="btn btn-primary col-2" ng-click="submit()" style="margin:0 30px;">등록 </button>
				<button class="btn btn-danger col-2" ng-click="close()" style="margin:0 30px;">닫기 </button>
			</div>
		</form>
	</div>
</body>
</html>