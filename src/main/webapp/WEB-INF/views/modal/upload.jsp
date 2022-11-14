<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">

<head>
<meta charset="UTF-8">
</head>
	<div class="modal-content" style="z-index: 3">
		<div class="modal-header">
			<h5 class="modal-title" style="font-weight: bold">APPLICATION</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" ng-click="close()">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="modal-body" style="width: 100%;height: 380px;">
			<table class="table-div-10 table-bordered text-center col-12" style="text-align: center;">
				<thead >
					<tr style="visibility:collapse;">
						<th ng-repeat="x in [].constructor(10) track by $index"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th colspan="3" style="height:40px">최신업로드</th>
						<td colspan="6" style="padding: 0;"><input type="text" class="form-control" ng-model="view.appNm" disabled></td>
						<td colspan="1" style="padding: 0;"><button class="btn" ng-click="appDown(view.appDownUrl)">다운</button></td>
					</tr>
					<tr>
						<th colspan="2" style="height:40px">구분</th>
						<td colspan="3"><input type="text" class="form-control" value="{{view.appGub | code: device}}" disabled></td>
						<th colspan="2">업로더명</th>
						<td colspan="3"><input type="text" class="form-control" ng-model="view.regId" disabled></td>
					</tr>
					<tr>
						<th colspan="2">등록일자</th>
						<td colspan="4"><input type="text" class="form-control" value="{{view.regDate | date:'yyyy-MM-dd HH:mm'}}" disabled></td>
						<th colspan="2">version</th>
						<td colspan="2"><input type="text" class="form-control" ng-model="view.version" disabled></td>
					</tr>
					<tr>
						<th colspan="2">설명</th>
						<td colspan="8"><input type="text" class="form-control" ng-model="view.comment" disabled></td>
					</tr>

				</tbody>
			</table>
			<div class="d-flex flex-column" style="margin-top:20px;">

				<form method="post" id="appForm" action="upload" enctype="multipart/form-data" onsubmit="return false" >
					<span style="font-weight: bold">어플리케이션 업로드</span>

					<div class="input-group btn" style="width:330px;margin:5px 0 0 0;padding:0!important;height:40px;cursor:auto;">
						<span style="margin:7px 8px 3px 3px;"> 파일 : </span>
						<input ng-model="file_path" style=";border: 1px solid lightgray;width: 210px;background: white;border-radius: 5px 0 0 5px;" disabled=disabled">
						<div class="input-group-append">
							<label class="btn btn-outline-secondary" style="margin:0;border:1px solid lightgray;">찾기
								<input class="btn-outline-secondary" type="file" name="uploadApp" id="ex_filename" onchange="angular.element(this).scope().fileUpload(this.value)" hidden>
							</label>
						</div>
					</div>
				</form>

				<div class="d-flex" style="margin-top: 8px;">
					<span style="margin:7px 8px 3px 3px;"> 버전 : </span>
					<input type="text" class="form-control" ng-model="input.version" placeholder="version" style="width:150px;">
					<span style="margin:7px 8px 3px 15px;"> 구분 : </span>
					<select class="form-control" ng-model="input.appGub" style="width:150px;">
						<option value="">App구분</option>
						<option ng-repeat="value in device" value="{{value.commCd}}">{{value.commCdNm}}</option>
					</select>
				</div>

				<div class="d-flex" style="margin-top: 8px;">
					<span style="margin:7px 8px 3px 3px;"> 설명 : </span>
					<input type="text" class="form-control" maxlength="200" ng-model="input.comment" style="width:363px;">
				</div>


			</div>
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-primary" ng-click="upload()">upload</button>
			<button type="button" class="btn btn-secondary" ng-click="close()" >cancel</button>
		</div>
	</div>
</html>
