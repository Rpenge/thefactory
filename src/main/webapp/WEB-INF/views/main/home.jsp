<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="d-flex flex-column" ng-show="authenticated">

	<div class="d-flex justify-content-center" style="top:-310px;width:100%;height: 120px;position:relative;color:black;text-align: center;">

		<div style="width: 200px;z-index: 2;">
			<p style="font-size: larger;font-weight: bold;">총 재고</p>
			<p style="font-size: 40px;">10</p>
		</div>

		<div style="width: 200px;z-index: 2;border-left:1px solid gray;border-right:1px solid gray;">
			<p style="font-size: larger;font-weight: bold;">입고</p>
			<p style="font-size: 40px;">15</p>
		</div>

		<div style="width: 200px;z-index: 2;">
			<p style="font-size: larger;font-weight: bold;">출고</p>
			<p style="font-size: 40px;">50</p>
		</div>
		<div style="background: white;width:40%;height: 100%; opacity: 50%;position:absolute;z-index: 1;"></div>
	</div>

<%--	<div class="d-flex justify-content-between" style="background: white;width:100%;margin:0 auto;max-height: 650px;">--%>

<%--		<div style="padding: 1px;overflow: hidden;"><img src="/resources/img/다운로드 (1).png" style="width:100%;min-width:1150px;opacity: 85%;" draggable="false"></div>--%>
<%--		<div style="background: white;min-width: 350px;">--%>
<%--			<div style="background: #555657;height:8%;padding: 7px;">--%>
<%--				<p style="font-size: 25px;color: whitesmoke;"> 주요업무</p>--%>
<%--			</div>--%>

<%--			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">--%>
<%--				<img src="/resources/img/1.jpg" style="width:100%;">--%>
<%--				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">--%>
<%--				</div>--%>
<%--				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">재고상품등록</p>--%>
<%--			</div>--%>

<%--			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">--%>
<%--				<img src="/resources/img/2.jpg" style="width:100%;">--%>
<%--				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">--%>
<%--				</div>--%>
<%--				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">출고조회</p>--%>
<%--			</div>--%>

<%--			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">--%>
<%--				<img src="/resources/img/3.jpg" style="width:100%;">--%>
<%--				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">--%>
<%--				</div>--%>
<%--				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">입고조회</p>--%>
<%--			</div>--%>

<%--			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">--%>
<%--				<img src="/resources/img/4.jpg" style="width:100%;">--%>
<%--				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">--%>
<%--				</div>--%>
<%--				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">재고실사</p>--%>
<%--			</div>--%>

<%--		</div>--%>
<%--	</div>--%>

		<div style="background: transparent;width:100%;margin:0 auto;top:-70px;position:relative;">
			<p style="text-align: center;font-size:25px;font-weight: bold;">주요업무 <span style="font-size:13px;font-weight: normal;">바로가기</span></p>
			<div class="d-flex justify-content-around" style="min-width: 350px;height: 180px;">

				<div style="width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('assetReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 60%;"></div>
<%--					<div style="width:100%;height: 100%;position: absolute;background: black;opacity: 50%;"></div>--%>
					<img src="/resources/img/1.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 22px;font-weight: bold;color: whitesmoke;position:absolute;left:10px;top:8px;">브랜드</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 50%;"></div>
					<img src="/resources/img/1.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">상품</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('userReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/2.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">입고</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('proReg')">
					<div class="btn btn-dark" style="width:100%;height: 100%;position: absolute;opacity: 40%;"></div>
					<img src="/resources/img/3.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 22px;color: whitesmoke;position:absolute;left:10px;top:10px;">출고</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('proReg')">
					<img src="/resources/img/2.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">반품</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('proReg')">
					<img src="/resources/img/3.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">재고실사</p>
				</div>

				<div style="background: white;width:130px;border:1px solid gray;cursor:pointer;overflow: hidden;border-radius: 5px;position:relative;" ng-click="modalOpen('proReg')">
					<img src="/resources/img/4.jpg" style="height:100%;" draggable="false">
					<p style="font-size: 25px;color: whitesmoke;position:absolute;left:10px;top:10px;">재고현황</p>
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

	<div class="d-flex"style="background: transparent;width:100%;margin:15px auto;height: 400px;border-radius: 10px;border:1px solid lightgray;">
		<div id="select-chart"class="d-flex flex-column" style="width: 18%;">

			<div style="height: 20%;background: white;padding:20px;color: gray;" onclick="chart(1)">
				<p style="font-size: 20px;">월별 작업현황</p>
			</div>
			<div style="height: 20%;background: whitesmoke;padding:20px;color: gray;border-top:1px solid lightgray;border-bottom:1px solid lightgray;" onclick="chart(2)">
				<p style="font-size: 20px;">graph_1</p>
			</div>
			<div style="height: 20%;background: whitesmoke;padding:20px;color: gray;border-bottom:1px solid lightgray;" onclick="chart(3)">
				<p style="font-size: 20px;">graph_2</p>
			</div>
		</div>

		<div id="chart1" style="width: 82%;padding: 20px;background: white;">
			<div style="display: block;">
				<div class="d-flex">
					<div style="width:100%;overflow: hidden;">
						<p style="font-size: larger;font-weight: bold;">월별 작업현황</p>
						<canvas id="myChart" style="max-height: 350px;overflow: hidden;"></canvas>
					</div>
				</div>
			</div>
			<div style="display: none">
				<p style="font-size: larger;font-weight: bold;">재고현황(상품별)</p>
				<canvas id="myChart2" style="max-width:600px;"></canvas>
			</div>
			<div style="display: none">
				<p style="font-size: larger;font-weight: bold;">재고현황(TOTAL)</p>
			</div>
		</div>
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




	var ctx2 = document.getElementById('myChart2').getContext('2d');

	var myChart2 = new Chart(ctx2, {
		type: 'bar',
		data: {
			labels: ['Red', 'Blue', 'Yellow', 'Green'],
			datasets: [{
				data: [12, 19, 3, 5],
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)'
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			}
		}
	});

</script>
