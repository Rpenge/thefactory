<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>기초 코드 추가</title>
</head>
<body>
	<div style="padding:35px; background-color: #f1f1f1; border-radius:5px;">
	<h2 style="border-bottom:1px solid black; padding:10px;margin-bottom:20px;font-weight:bold;">코드 업데이트</h2>
		
		<form class="list">
			<div class="form-group row">
				<label class="col-sm-4 col-form-label" >Parent Code</label>
				<div class="col-7">
					<select id="select" class="custom-select col-7" ng-model="list.parentCode" ng-if="!list.cmmnSeq" >
			      		<option ng-repeat="list in selectCode" value="{{list.code}}">{{list.codeName}}({{list.code}})</option>
			    	</select>
			    	<select id="select" class="custom-select col-7" ng-model="list.parentCode" ng-if="list.cmmnSeq" disabled>
			      		<option ng-repeat="list in selectCode" value="{{list.code}}">{{list.codeName}}({{list.code}})</option>
			    	</select>
				</div>
			</div>
		
			<div class="form-group row">
				<label class="col-sm-4 col-form-label" >Code</label>
				<div class="col-7">
					<input class="form-control" ng-model="list.code" ng-if="list.cmmnSeq" readOnly>
					<input class="form-control" ng-model="list.code" ng-if="!list.cmmnSeq">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-4 col-form-label" >Code Name</label>
				<div class="col-7">
					<input class="form-control" ng-model="list.codeName" required>
				</div>
			</div>
			
			<button class="btn btn-primary col-4" ng-click="submit()" ng-if="!list.cmmnSeq" style="margin:0 30px;">추가 </button>
			<button class="btn btn-primary col-4" ng-click="submit()" ng-if="list.cmmnSeq" style="margin:0 30px;">수정 </button>
			<button class="btn btn-danger col-4" ng-click="close()" style="margin:0 30px;">닫기 </button>
		</form>
	</div>
</body>
</html>