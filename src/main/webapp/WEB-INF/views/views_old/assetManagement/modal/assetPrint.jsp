<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자산 프린트</title>
</head>
<body>
	<div id="myframe" style="height:42px;background-color:whitesmoke;padding: 5px;">
		<i class='xi-print' style='font-size:1.5em;'></i>
		<button class='btn btn-danger button-custom1' style='float: right;' ng-click="close()">X</button>
		<button class='btn btn-primary button-custom1' style='float:right; margin: 0 5px' ng-click="onPrint()">Print</button>
		<button class='btn btn-dark button-custom1' style='float: right;' ng-click="fontSize('base');lineWeight('base');sizeDefault;lineColor('lightgray');blank('base');">Reset</button>
		
		<button class='btn btn-info button-custom1' ng-click="sizeUp()">+</button>
		<button class='btn btn-info button-custom1' ng-click="sizeDown()"> - </button>
		<button class='btn btn-info button-custom1' ng-click="sizeDefault()">100%</button>
		
		<button class='btn btn-success button-custom1' ng-click="blank('up')"> -> <- </button>
		<button class='btn btn-success button-custom1' ng-click="blank('down')"> <- -> </button>
		
		<button class='btn btn-success button-custom1' ng-click="lineWeight('thick')">Line +</button>
		<button class='btn btn-success button-custom1' ng-click="lineWeight('thin')">Line -</button>
		
		<button class='btn btn-success button-custom1' ng-click="fontSize('big')">Font +</button>
		<button class='btn btn-success button-custom1' ng-click="fontSize('small')">Font -</button>
		
		
		<input class="text" ng-model="inputColor" placeholder='Input Color'>
		<button class='btn btn-secondary button-custom1' ng-click="lineColor(inputColor)">Line Color</button>
		<button class='btn btn-secondary button-custom1' ng-click="fontColor(inputColor)">Font Color</button>
	</div>
	<div style="overflow:auto; background-color: lightgray;width:100%;height:800px;">
		<div class="modal-body no-drag"  style="border:1px solid black;width:1100px; min-height:1500px;padding:50px;margin:15px auto;background-color: white; ">
			<h2 ng-if="command == null">자산 목록 조회</h2>
			<h2 ng-if="command == 'QR'">QR코드 리스트</h2>
			<h6>자산 목록 조회 / 출력일시 : {{today | date:'yyyy-MM-dd HH:mm:ss'}} / 사용자 : {{user.name}}</h6>
			
			<div ng-if="command == null">
				<table class="table table-div-8 table-bordered text-center col-12" style="margin:0 auto 40px;">
					<thead>
						<tr style="visibility: collapse;">
							<th ng-repeat="x in [].constructor(8) track by $index"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="table-content" colspan="2" >사용자ID</td>
							<td colspan="2">{{user.username}}</td>
							<td class="table-content" colspan="2">이름</td>
							<td colspan="2">{{user.name}} 님</td>
						</tr>
						<tr>
							<td class="table-content" colspan="2">조회 기간</td>
							<td colspan="3">{{startDate | date:'yyyy-MM-dd'}} ~ {{endDate | date:'yyyy-MM-dd'}}</td>
							<td colspan="3" rowspan="2"></td>
						</tr>
						<tr>
							<td class="table-content" colspan="2">출력일자</td>
							<td colspan="3">{{today | date:'yyyy-MM-dd HH:mm:ss'}}</td>
						</tr>
						<tr>
							<td class="table-content" colspan="2" rowspan="2" style="vertical-align: middle;">자료 구분</td>
							<td class="table-content" colspan="1">자산상태</td>
							<td colspan="1">{{search.status | code: commonCode :'전체'}}</td>
							<td class="table-content" colspan="1">자산종류</td>
							<td colspan="1">{{search.division | code: commonCode :'전체'}}</td>
							<td class="table-content" colspan="1">자산위치</td>
							<td colspan="1">{{search.location | code: commonCode :'전체'}}</td>
						</tr>
						<tr>
							<td class="table-content" colspan="1">부서</td>
							<td colspan="1">{{search.dept | code: commonCode :'전체'}}</td>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td class="table-content" colspan="2">인쇄 데이터 수량</td>
							<td colspan="2">{{list.length}} 건</td>
							<td class="table-content" colspan="2">조회 데이터 수량</td>
							<td colspan="2">{{total}} 건</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div ng-if="command == null">
			<table class="table text-center col-12" style="border:1px solid lightgray; margin: 0 auto;">
				<thead>
					<tr >
						<th style="border-right:1px solid lightgray;">No</th>
						<th>관리번호</th>
						<th>자산명</th>
						<th>분류</th>
						<th>상태</th>
						<th>태그발행</th>
						<th>사용부서</th>
						<th>등록일</th>
						<th>등록자</th>
						<th>위치</th>
						<th>수량</th>
					</tr>
				</thead> 
				<tbody>
					<tr ng-repeat="(key, value) in list" >
						<td style="border-right:1px solid lightgray;">{{$index+1}}</td>
						<td>{{value.assetControlCode}}</td>
						<td>{{value.assetName}}</td>
						<td>{{value.assetDivision | code: commonCode}}</td>
						<td>{{value.assetStatus | code: commonCode}}</td>
						<td>{{value.mappingYn}}</td>
						<td>{{value.assetDept | code: commonCode}}</td>
						<td>{{value.assetRegDate | date:'yyyy-MM-dd HH:mm'}}</td>
						<td>{{value.assetRegPerson}}</td>
						<td>{{value.assetLocation | code: commonCode}}</td>
						<td>{{value.assetQuantity}} </td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<div ng-if="command=='QR'">
				<div class="QRImg" ng-repeat="(key, value) in list" style="display: inline-block;width: 420px;border:1px solid gray;margin:8px;padding: 5px;">
					<img src="{{value.qr}}" style="width:80px;height:80px;float:right;"/>
					자산코드 : {{value.assetControlCode}}<br>
					자산명 : {{value.assetName}}<br>
					자산분류 : {{value.assetDivision | code: commonCode}}
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