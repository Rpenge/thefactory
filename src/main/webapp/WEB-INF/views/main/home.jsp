<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="d-flex flex-column" ng-show="authenticated">

	<div class="d-flex justify-content-center absolute-center" style="top:520px;width:800px;height: 100px;color:black;text-align: center;">

		<div class="d-flex justify-content-around main-cnt-box">
			<i class="xi-home-o" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font">총 재고</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.stockTotcnt | zeroCheck }}</span>
			</div>
		</div>
		<div class="d-flex justify-content-around main-cnt-box">
			<i class="xi-box" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font">입고</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.inTotcnt | zeroCheck}}</span>
			</div>
		</div>

		<div class="d-flex justify-content-around main-cnt-box">
			<i class="xi-truck" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font">출고</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.outTotcnt | zeroCheck}}</span>
			</div>
		</div>

		<div class="d-flex justify-content-around main-cnt-box" style="padding:20px 0 20px 20px;border-right: 0px;">
			<i class="xi-dropbox" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font" >판매/배송</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.sellTotcnt | zeroCheck}}</span>
			</div>
		</div>
		<div style="background: white;width:820px;height: 120px; opacity: 75%;position:absolute;z-index: 1;top:-10px;box-shadow: 1px 1px 5px lightgray;border-radius: 3px;"></div>
	</div>

		<div class="body-box" style="background: white;width:100%;margin:0 auto;top:30px;position:relative;padding:20px 0 45px 0;">
			<p style="text-align:center;font-size:25px;font-weight: bold;border-bottom: 1px solid lightgrey;margin:0 80px;padding-bottom: 6px;">주요업무 <span style="font-size:13px;font-weight: normal;">바로가기</span></p>

			<div class="d-flex justify-content-center" style="min-width: 350px;height: 180px;">
				<div class="main-work-btn" ng-click="goMainMenu('IO1')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/main_menu_1.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left: 30px;top: 20px;">입고</p>
				</div>

				<div class="main-work-btn" ng-click="goMainMenu('IO2')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/main_menu_2.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left: 30px;top: 20px;">출고</p>
				</div>

				<div class="main-work-btn" ng-click="goMainMenu('IO3')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/main_menu_3.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left: 30px;top: 20px;">판매/배송</p>
				</div>

				<div class="main-work-btn" ng-click="goMainMenu('ST1')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/main_menu_4.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold; color: whitesmoke; position: absolute;left: 30px;top: 20px;">재고현황</p>
				</div>
			</div>
		</div>

	<div class="body-box"style="position:relative;top:40px;background: white;width:100%;margin:15px auto;height: 400px;padding:10px 0 45px 0;">
		<p style="text-align:center;font-size:25px;font-weight: bold;margin:8px 80px;padding-bottom: 6px;">입출고 현황</p>
		<div id="chart1" style="width: 100%;padding: 20px;background: white;">
			<div style="width:100%;height: 350px;">
				<canvas id="myChart" style="max-height: 330px;overflow: hidden;"></canvas>
			</div>
		</div>
	</div>

	<div style="margin-top:100px;" ng-if="role=='010101'">
		<button class="btn btn-outline-secondary" ng-click="uploaderOpen('fixed')">고정형 리더기</button>
		<button class="btn btn-outline-secondary" ng-click="uploaderOpen('pda')">PDA APP</button>
	</div>
</div>
</html>


<script>
	function chart(n){
		$('#chart1 >').css("display","none");
		$('#chart1 > :nth-child('+n+')').css("display","block");
		$('#select-chart >').css("background","whitesmoke");
		$('#select-chart >').css("color","gray");
		$('#select-chart > :nth-child('+n+')').css("background","white");
		$('#select-chart > :nth-child('+n+')').css("color","gray");
	}
</script>
