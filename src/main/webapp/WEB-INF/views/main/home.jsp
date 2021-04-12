<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="d-flex flex-column" ng-show="authenticated">

	<div class="d-flex justify-content-center absolute-center" style="top:550px;width:800px;height: 100px;color:black;text-align: center;">

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
		<div style="background: white;width:820px;height: 120px; opacity: 50%;position:absolute;z-index: 1;top:-10px;"></div>
	</div>

		<div style="background: transparent;width:100%;margin:0 auto;top:40px;position:relative;">
			<p style="text-align: center;font-size:25px;font-weight: bold;">주요업무 <span style="font-size:13px;font-weight: normal;">바로가기</span></p>

			<div class="d-flex justify-content-center" style="min-width: 350px;height: 180px;">
				<a href="/#/inout/input">
					<div class="main-work-btn" >
						<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 60%;"></div>
						<img src="/resources/img/1.jpg" style="width:100%;height:100%;" draggable="false">
						<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left: 90px;top: 50px;">입고</p>
					</div>
				</a>

				<a href="/#/inout/output">
				<div class="main-work-btn" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 50%;"></div>
					<img src="/resources/img/1.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left: 90px;top: 50px;">출고</p>
				</div>
				</a>

				<a href="/#/inout/sales">
				<div class="main-work-btn" ng-click="modalOpen('userReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/2.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left: 70px;top: 50px;">판매/배송</p>
				</div>
				</a>

				<a href="/#/stock/stk">
				<div class="main-work-btn" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/3.jpg" style="width:100%;height:100%;" draggable="false">
					<p style="font-size: 25px; color: whitesmoke; position: absolute;left: 70px;top: 50px;">재고현황</p>
				</div>
				</a>

			</div>
		</div>

	<div class="d-flex"style="position:relative;top:40px;background: transparent;width:100%;margin:15px auto;height: 400px;border:1px solid lightgray;">
		<div id="chart1" style="width: 100%;padding: 20px;background: white;">
			<div style="width:100%;height: 350px;">
				<canvas id="myChart" style="max-height: 330px;overflow: hidden;"></canvas>
			</div>
		</div>
	</div>

	<div style="margin-top:50px;" ng-if="role=='010101'">
<%--		<button class="btn btn-outline-secondary" ng-click="uploaderOpen()">고정형 리더기</button>--%>
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
