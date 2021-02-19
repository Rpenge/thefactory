<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="starter-template d-flex justify-content-center" ng-show="authenticated">


	<div class="container-fluid body-custom" style="width: 20%;height:600px;margin:5px;">
		ssss
	</div>

	<div class="container-fluid body-custom" style="width: 60%;height:600px;margin:5px;">

		<div class="body-contents" >

<%--			<div class="row d-flex justify-content-center">--%>
<%--				<div class="col-3 boardBox-1" >--%>
<%--					<!-- small box -->--%>
<%--					<div class="small-box bg-info">--%>
<%--						<div class="inner" >--%>
<%--							<h3>{{chart.status['사용']+chart.status['이동']++chart.status['수리']+chart.status['대여']+chart.status['폐기']}}</h3>--%>
<%--							<p>총 자산</p>--%>
<%--						</div>--%>
<%--						<a href="" ng-click="goAssetManagementList()">More info</a>--%>
<%--						<i class="xi-home-o bg-icon-custom"></i>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<!-- ./col -->--%>
<%--				<div class="col-3 boardBox-1">--%>
<%--					<!-- small box -->--%>
<%--					<div class="small-box bg-success">--%>
<%--						<div class="inner">--%>
<%--							<h3>{{chart.count.dayAsset}} </h3>--%>
<%--							<p>오늘 등록 자산</p>--%>
<%--						</div>--%>
<%--						<a href="" ng-click="goAssetManagementList()">More info</a>--%>
<%--						<i class="xi-time-o bg-icon-custom"></i>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<!-- ./col -->--%>
<%--				<div class="col-3 boardBox-1">--%>
<%--					<!-- small box -->--%>
<%--					<div class="small-box bg-warning">--%>
<%--						<div class="inner">--%>
<%--							<h3>{{chart.count.monthAsset}}</h3>--%>
<%--							<p>이번 달 등록 자산</p>--%>
<%--						</div>--%>
<%--						<a href="" ng-click="goAssetManagementList()">More info</a>--%>
<%--						<i class="xi-document bg-icon-custom"></i>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<!-- ./col -->--%>
<%--				<div class="col-3 boardBox-1">--%>
<%--					<!-- small box -->--%>
<%--					<div class="small-box bg-danger">--%>
<%--						<div class="inner">--%>
<%--							<h3>{{chart.count.dayRfid}}<sup style="font-size:15px">건</sup></h3>--%>
<%--							<p>오늘 RFID 사용 건수</p>--%>
<%--						</div>--%>
<%--						<a href="" ng-click="goRfidChange()" >More info</a>--%>
<%--						<i class="xi-tags bg-icon-custom"></i>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>


			<canvas id="myChart" style="width: 75%;"></canvas>
			<script>
				// var ctx = document.getElementById('myChart').getContext('2d');
				// var myChart = new Chart(ctx, {
				// 	type: 'bar',
				// 	data: {
				// 		labels: ['Red', 'Blue', 'Yellow', 'Green'],
				// 		datasets: [{
				// 			label: '1 of Votes',
				// 			data: [12, 19, 3, 5],
				// 			backgroundColor: [
				// 				'rgba(255, 99, 132, 0.2)',
				// 				'rgba(54, 162, 235, 0.2)',
				// 				'rgba(255, 206, 86, 0.2)',
				// 				'rgba(75, 192, 192, 0.2)'
				// 			],
				// 			borderColor: [
				// 				'rgba(255, 99, 132, 1)',
				// 				'rgba(54, 162, 235, 1)',
				// 				'rgba(255, 206, 86, 1)',
				// 				'rgba(75, 192, 192, 1)'
				// 			],
				// 			borderWidth: 1
				// 		},{
				// 			label: '2 Votes',
				// 			data: [11, 18, 2, 4],
				// 			backgroundColor: [
				// 				'rgba(25, 99, 132, 0.2)',
				// 				'rgba(54, 162, 235, 0.2)',
				// 				'rgba(255, 206, 86, 0.2)',
				// 				'rgba(75, 192, 192, 0.2)'
				// 			],
				// 			borderColor: [
				// 				'rgba(25, 99, 132, 1)',
				// 				'rgba(54, 162, 235, 1)',
				// 				'rgba(255, 206, 86, 1)',
				// 				'rgba(75, 192, 192, 1)'
				// 			],
				// 			borderWidth: 1
				// 		}]
				// 	},
				// 	options: {
				// 		scales: {
				// 			yAxes: [{
				// 				ticks: {
				// 					beginAtZero: true
				// 				}
				// 			}]
				// 		}
				// 	}
				// });

				var ctx = document.getElementById('myChart').getContext('2d');
				var myChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: ['입고', '출고', '반품', '총 재고'],
						datasets: [{
							label: '논현',
							data: [12, 19, 3, 5],
							backgroundColor: [
								'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)'
							],
							borderColor: [
								'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)'
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
		</div>
	</div>
</div>
</div>
</html>
