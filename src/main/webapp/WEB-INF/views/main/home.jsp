<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="d-flex flex-column" ng-show="authenticated">

	<div class="d-flex justify-content-center" style="top:-310px;width:800px;height: 100px;position:relative;color:black;text-align: center;margin:0 auto;">

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
				<span style="font-size: 25px;font-weight: bold;">{{todayData.stockTotcnt | zeroCheck}}</span>
			</div>
		</div>

		<div class="d-flex justify-content-around main-cnt-box">
			<i class="xi-truck" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font">출고</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.stockTotcnt | zeroCheck}}</span>
			</div>
		</div>

		<div class="d-flex justify-content-around main-cnt-box" style="padding:20px 0 20px 20px;border-right: 0px;">
			<i class="xi-dropbox" style="font-size: 70px;"></i>
			<div>
				<p class="main-cnt-font" >판매/배송</p>
				<span style="font-size: 25px;font-weight: bold;">{{todayData.stockTotcnt | zeroCheck}}</span>
			</div>
		</div>
		<div style="background: white;width:820px;height: 120px; opacity: 50%;position:absolute;z-index: 1;top:-10px;"></div>
	</div>

		<div style="background: transparent;width:100%;margin:0 auto;top:-70px;position:relative;">
			<p style="text-align: center;font-size:25px;font-weight: bold;">주요업무 <span style="font-size:13px;font-weight: normal;">바로가기</span></p>

			<div class="d-flex justify-content-center" style="min-width: 350px;height: 180px;">

				<div class="main-work-btn" ng-click="modalOpen('assetReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 60%;"></div>
<%--					<div style="width:100%;height: 100%;position: absolute;background: black;opacity: 50%;"></div>--%>
					<img src="/resources/img/1.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left:10px;top:8px;">입고</p>
				</div>

				<div class="main-work-btn" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 50%;"></div>
					<img src="/resources/img/1.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">출고</p>
				</div>

				<div class="main-work-btn" ng-click="modalOpen('userReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/2.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">판매/배송</p>
				</div>

				<div class="main-work-btn" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/3.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 22px;color: whitesmoke;position:absolute;left:10px;top:10px;">재고현황</p>
				</div>


			</div>
		</div>



<%--	<div class="d-flex justify-content-between" style="width:100%;margin:15px auto;height: 150px;position: relative;">--%>
<%--		<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 20%;">--%>
<%--		</div>--%>
<%--		<div style="width: 250px;background: black;color: white;opacity: 65%;padding: 15px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">금일 작업 현황</p>--%>
<%--			<p style="">날짜 : 2021-02-23 (화요일)</p>--%>
<%--		</div>--%>

<%--		<div style="width: 150px;background: white;border-radius: 100px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">입고수량</p>--%>
<%--			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">10</p>--%>
<%--		</div>--%>

<%--		<div style="width: 150px;background: white;border-radius: 100px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">출고수량</p>--%>
<%--			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">15</p>--%>
<%--		</div>--%>

<%--		<div style="width: 150px;background: white;border-radius: 100px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">실사수량</p>--%>
<%--			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">50</p>--%>
<%--		</div>--%>

<%--		<div style="width: 150px;background: white;border-radius: 100px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">반품수량</p>--%>
<%--			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">3</p>--%>
<%--		</div>--%>

<%--		<div style="width: 150px;background: white;border-radius: 100px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">전체재고</p>--%>
<%--			<p style="font-size: 40px;margin-left: 50px;">98</p>--%>
<%--		</div>--%>

<%--		<div></div>--%>

<%--	</div>--%>

<%--	<div class="d-flex"style="background: white;width:75%;margin:15px auto;height: 400px;">--%>
<%--		<div style="background: whitesmoke;width: 50%;padding: 20px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">재고현황(매장별)</p>--%>
<%--			<canvas id="myChart" style="max-width:600px;"></canvas>--%>
<%--		</div>--%>

<%--		<div style="background: white;width: 50%;padding: 50px;">--%>
<%--			<p style="font-size: larger;font-weight: bold;">재고현황(상품별)</p>--%>
<%--			<canvas id="myChart2" style="max-width:600px;"></canvas>--%>
<%--		</div>--%>
<%--	</div>--%>

	<div class="d-flex"style="position:relative;top:-70px;background: transparent;width:100%;margin:15px auto;height: 400px;border:1px solid lightgray;">


		<div id="chart1" style="width: 100%;padding: 20px;background: white;">
			<div style="width:100%;height: 350px;">
<%--				<p style="font-size: larger;font-weight: bold;">월별 작업현황</p>--%>
				<canvas id="myChart" style="max-height: 330px;overflow: hidden;"></canvas>
			</div>
		</div>

	</div>

	<div>
		<button class="btn btn-outline-secondary">고정형 리더기</button>
		<button class="btn btn-outline-secondary">PDA APP</button>
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
