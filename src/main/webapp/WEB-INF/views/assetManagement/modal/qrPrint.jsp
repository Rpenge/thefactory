<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자산 등록</title>
</head>
<body>
	<div id="myframe" style="height:42px;background-color:whitesmoke;padding: 5px;">
		<i class='xi-print' style='font-size:1.5em;'></i>
		<button class='btn btn-danger button-custom1' style='float: right;' ng-click="close()">X</button>
		<button class='btn btn-primary button-custom1' style='float:right; margin: 0 5px' ng-click="onPrint()">Print</button>
		
	</div>
	<div style="overflow:auto; background-color: lightgray;width:100%;height:800px;">
		<div class="modal-body no-drag"  style="border:1px solid black;width:1100px; min-height:1500px;padding:50px;margin:15px auto;background-color: white; ">
			<h2>QR코드 리스트</h2>
			<h6>자산 목록 조회 / 출력일시 : {{today | date:'yyyy-MM-dd HH:mm:ss'}} / 사용자 : {{user.name}}</h6>
			
			<div>
				<div class="QRImg" ng-repeat="(key, value) in list" style="display:inline-block;width:420px;border:1px solid gray;margin:8px;padding: 5px;">
					<table class="table table-div-10 table-bordered text-center col-12" style="margin:0 auto;">
					<thead>
						<tr style="visibility: collapse;">
							<th ng-repeat="x in [].constructor(10) track by $index"></th>
						</tr>
					</thead>
					<tbody style='font-size:11px;padding:0;'>
						<tr >
							<td class="table-content" colspan="3" style="vertical-align: middle;">자산명</td>
							<td colspan="5">{{value.assetName}}</td>
							<td colspan="2" rowspan='2' style="padding:0;"><img src="{{value.qr}}" style="width:70px;height:70px;margin: auto;" /></td>
						</tr>
						
						<tr>
							<td class="table-content" colspan="2" style="vertical-align: middle;">자산코드</td>
							<td colspan="2">{{value.assetControlCode}}</td>
							<td class="table-content" colspan="2" style="vertical-align: middle;">분류</td>
							<td colspan="2">{{value.assetDivision | code: commonCode}}</td>
						</tr>
						
						<tr>
							<td class="table-content" colspan="2">사용부서</td>
							<td colspan="2">{{value.assetDept | code: commonCode}}</td>
							<td class="table-content" colspan="2">등록일자</td>
							<td colspan="4">{{value.assetRegDate | date:'yyyy-MM-dd'}}</td>
						</tr>
						
						<tr>
							<td class="table-content" colspan="2">수량</td>
							<td colspan="2">{{value.assetQuantity}}</td>
							<td class="table-content" colspan="2">출력일자</td>
							<td colspan="4">{{today | date:'yyyy-MM-dd HH:mm'}}</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div>
				<span class="print-add">(주)시스템케이 (SYSTEMK Co., Ltd.) / 주소 : 경기도 구리시 갈매순환로 204번길 65, 구리스마트벤처타워 407호 408호 / 전화 : 070-8830-5252팩스 : 031-571-5254</span>
				<span class="print-add">Copyrightⓒ2011 SYSTEMK. All rights Reserved.</span>
			</div>
		</div>
	</div>
</body>
</html>