<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="starter-template d-flex flex-column" ng-show="authenticated">

	<div class="d-flex justify-content-between" style="background: white;width:75%;margin:0 auto;max-height: 650px;">

		<div style="padding: 1px;overflow: hidden;"><img src="/resources/img/다운로드 (1).png" style="width:100%;min-width:1150px;opacity: 85%;" draggable="false"></div>

		<div style="background: white;min-width: 350px;">
			<div style="background: #555657;height:8%;padding: 7px;">
				<p style="font-size: 25px;color: whitesmoke;"> 주요업무</p>
			</div>

			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">
				<img src="/resources/img/1.jpg" style="width:100%;">
				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">
				</div>
				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">재고상품등록</p>
			</div>

			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">
				<img src="/resources/img/2.jpg" style="width:100%;">
				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">
				</div>
				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">출고조회</p>
			</div>

			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">
				<img src="/resources/img/3.jpg" style="width:100%;">
				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">
				</div>
				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">입고조회</p>
			</div>

			<div style="background: black;height:23%;border:1px solid white;opacity: 80%;cursor:pointer;position: relative;overflow: hidden;">
				<img src="/resources/img/4.jpg" style="width:100%;">
				<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 60%;">
				</div>
				<p style="font-size: 25px;color: black;position:absolute;top:25px;left: 30px;">재고실사</p>
			</div>

		</div>
	</div>

	<div class="d-flex justify-content-between" style="width:75%;margin:15px auto;height: 150px;position: relative;">
		<div style="position:absolute;top:0px;width:100%;height:100%;background: white;opacity: 20%;">
		</div>
		<div style="width: 250px;background: black;color: white;opacity: 65%;padding: 15px;">
			<p style="font-size: larger;font-weight: bold;">금일 작업 현황</p>
			<p style="">날짜 : 2021-02-23 (화요일)</p>
		</div>

		<div style="width: 150px;background: white;border-radius: 100px;">
			<p style="font-size: larger;font-weight: bold;">입고수량</p>
			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">10</p>
		</div>

		<div style="width: 150px;background: white;border-radius: 100px;">
			<p style="font-size: larger;font-weight: bold;">출고수량</p>
			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">15</p>
		</div>

		<div style="width: 150px;background: white;border-radius: 100px;">
			<p style="font-size: larger;font-weight: bold;">실사수량</p>
			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">50</p>
		</div>

		<div style="width: 150px;background: white;border-radius: 100px;">
			<p style="font-size: larger;font-weight: bold;">반품수량</p>
			<p style="font-size: 40px;margin-left: 50px;color:#80bdff;">3</p>
		</div>

		<div style="width: 150px;background: white;border-radius: 100px;">
			<p style="font-size: larger;font-weight: bold;">전체재고</p>
			<p style="font-size: 40px;margin-left: 50px;">98</p>
		</div>

		<div></div>

	</div>

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

	<div class="d-flex"style="background: white;width:75%;margin:15px auto;height: 400px;">
		<div id="select-chart"class="d-flex flex-column" style="width: 20%;">

			<div style="height: 33.3%;background: #555657;padding:20px;color: white;" onclick="chart(1)">
				<p style="font-size: 20px;">재고현황(매장별)</p>
			</div>
			<div style="height: 33.3%;background: whitesmoke;padding:20px;color: gray;border-top:1px solid white;border-bottom:1px solid white;" onclick="chart(2)">
				<p style="font-size: 20px;">재고현황(상품별)</p>
			</div>
			<div style="height: 33.3%;background: whitesmoke;padding:20px;color: gray;" onclick="chart(3)">
				<p style="font-size: 20px;">재고현황(전체)</p>
			</div>
		</div>

		<div id="chart1" style="width: 80%;padding: 20px;">
			<div style="display: block;">
				<div class="d-flex">
					<div style="width:50%;">
						<p style="font-size: larger;font-weight: bold;">재고현황(매장별)</p>
						<canvas id="myChart" style="max-width:600px;"></canvas>
					</div>
					<div style="width:50%;">
						<p style="font-size: larger;font-weight: bold;">재고현황(매장별) 테이블</p>
						<table class="table table-striped">
							<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">First</th>
								<th scope="col">Last</th>
								<th scope="col">Handle</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th scope="row">1</th>
								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							</tbody>
						</table>
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
		$('#select-chart > :nth-child('+n+')').css("background","#555657");
		$('#select-chart > :nth-child('+n+')').css("color","white");
	}


	var ctx = document.getElementById('myChart').getContext('2d');

	var myChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: ['Red', 'Blue', 'Yellow', 'Green'],
			datasets: [{
				label: '1 of Votes',
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
			},{
				label: '2 Votes',
				data: [11, 18, 2, 4],
				backgroundColor: [
					'rgba(25, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)'
				],
				borderColor: [
					'rgba(25, 99, 132, 1)',
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

	var ctx2 = document.getElementById('myChart2').getContext('2d');

	var myChart = new Chart(ctx2, {
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
