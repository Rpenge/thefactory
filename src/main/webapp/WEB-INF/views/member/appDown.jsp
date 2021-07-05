<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">

<head>
<meta charset="UTF-8">
</head>
	<div style="width:720px;">
		<div>
			<h5 style="font-weight: bold">APPLICATION</h5>
		</div>
		<div style="width: 95%;margin:10px auto;">
			<table class="table-div-10 table-bordered text-center col-12" style="text-align: center;">
				<thead >
					<tr style="visibility:collapse;">
						<th ng-repeat="x in [].constructor(10) track by $index"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th colspan="3" style="height:40px">최신업로드</th>
						<td colspan="7" style="padding: 0;"><input type="text" class="form-control" ng-model="view.appNm" disabled></td>
					</tr>
					<tr>
						<th colspan="2" style="height:40px">구분</th>
						<td colspan="3"><input type="text" class="form-control" value="{{view.appGub}}" disabled></td>
						<th colspan="2">업로더명</th>
						<td colspan="3"><input type="text" class="form-control" ng-model="view.regId" disabled></td>
					</tr>
					<tr>
						<th colspan="2" >등록일자</th>
						<td colspan="4"><input type="text" class="form-control" value="{{view.regDate | date:'yyyy-MM-dd HH:mm'}}" disabled></td>
						<th colspan="2">version</th>
						<td colspan="2"><input type="text" class="form-control" ng-model="view.version" disabled></td>
					</tr>
					<tr>
						<th colspan="2">설명</th>
						<td colspan="8"><input type="text" class="form-control" ng-model="view.comment" disabled></td>
					</tr>
					<tr>
						<td colspan="10" style="height: 60px;">
						<button class="btn btn-outline-secondary" ng-click="appDown(view.appDownUrl)" style="width:100%;height: 60px;">다운</button></td>
					</tr>

				</tbody>
			</table>
		</div>

	</div>
</html>
