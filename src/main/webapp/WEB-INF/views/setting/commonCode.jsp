<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>기초 코드 변경</title>
</head>
<body>
<div class="starter-template" ng-show="authenticated">
	<div class="container-fluid body-custom">
		<div class="body-contents">
			<h2><a class="menuName" href="" ng-click="reload()">기초 정보 관리</a></h2><br>
			<div id='shadow-body' class="custom-shadow-box">
			<form class="assetReg">
				<div class="form-group" style="margin-top:50px;">
					<h4 style="font-weight:bold">* 코드선택</h4>
					<div class="col-12">
				    	<select class="custom-select col-2" ng-model="list.assetDepth" ng-change="codeChange(list.assetDepth, 0)">
				    		<option value="">코드</option>
				      		<option ng-repeat="list in commonCode" value="{{list.code}}" ng-if="list.parentCode==null || list.parentCode==''">{{list.codeName}}({{list.code}})</option>
				    	</select>
				    	
				    	<select class="custom-select col-2" ng-model="list.assetDepth1" ng-change="codeChange(list.assetDepth1, 1)" ng-show="list.depth[0]">
				    		<option value="">코드2</option>
				      		<option ng-repeat="list in commonArray[0]" value="{{list.code}}">{{list.codeName}}({{list.code}})</option>
				    	</select>
				    	
				    	<select class="custom-select col-2" ng-model="list.assetDepth2" ng-change="codeChange(list.assetDepth2, 2)" ng-show="list.depth[1]">
				    		<option value="">코드3</option>
				      		<option ng-repeat="list in commonArray[1]" value="{{list.code}}">{{list.codeName}}({{list.code}})</option>
				    	</select>
				    	
				    	<button class="btn btn-primary" ng-click="addCode()">추가</button>
			    	</div>
			    </div>
			   
				<div style="margin-top:30px;">
					
					
					<div class="d-flex justify-content-between">
						<h4 style="font-weight:bold; ">* 코드리스트</h4>
		        		<button class="btn btn-danger " ng-click="codeDelete()" style="margin:5px;">삭제</button>
		        	</div>
					
					<div class="table-box">
						<table class="table table-hover table-striped-odd text-center custom-align-middle" style="width:900px;" >
							<thead>
								<tr>
									<th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
									<th>No</th>
									<th>코드</th>
									<th>코드명</th>
									<th>부모코드</th>
								</tr>
							</thead> 
							<tbody>
								<tr ng-repeat="value in list.codeList" ng-init="value.isSelected = false;" style="background-color: white" >
									<td><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.cmmnSeq}}, !{{value.isSelected}})"></td>
									<td ng-click="addCode(value)" class="pointer">{{value.cmmnSeq}}</td>
									<td ng-click="addCode(value)" class="pointer">{{value.code}}</td>
									<td ng-click="addCode(value)" class="pointer">{{value.codeName}}</td>
									<td ng-click="addCode(value)">{{value.parentCode}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>