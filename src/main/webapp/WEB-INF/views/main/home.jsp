<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<html ng-app="myApp">
<div class="starter-template" ng-show="authenticated" >
	<div class="container-fluid body-custom" >
		<div class="body-contents" >
			<div class="row">
				<div style="margin: auto;">
					<h1 class="homeIntro" >SYSTEMK ASSET MANAGEMENT</h1>
				</div>
			</div>

			<div class="row d-flex justify-content-center">
				<div class="col-3 boardBox-1" >
					<!-- small box -->
					<div class="small-box bg-info">
						<div class="inner" >
							<h3>{{chart.status['사용']+chart.status['이동']++chart.status['수리']+chart.status['대여']+chart.status['폐기']}}</h3>
							<p>총 자산</p>
						</div>
						<a href="" ng-click="goAssetManagementList()">More info</a>
						<i class="xi-home-o bg-icon-custom"></i>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-3 boardBox-1">
					<!-- small box -->
					<div class="small-box bg-success">
						<div class="inner">
							<h3>{{chart.count.dayAsset}} </h3>
							<p>오늘 등록 자산</p>
						</div>
						<a href="" ng-click="goAssetManagementList()">More info</a>
						<i class="xi-time-o bg-icon-custom"></i>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-3 boardBox-1">
					<!-- small box -->
					<div class="small-box bg-warning">
						<div class="inner">
							<h3>{{chart.count.monthAsset}}</h3>
							<p>이번 달 등록 자산</p>
						</div>
						<a href="" ng-click="goAssetManagementList()">More info</a>
						<i class="xi-document bg-icon-custom"></i>
					</div>
				</div>
				<!-- ./col -->
				<div class="col-3 boardBox-1">
					<!-- small box -->
					<div class="small-box bg-danger">
						<div class="inner">
							<h3>{{chart.count.dayRfid}}<sup style="font-size:15px">건</sup></h3>
							<p>오늘 RFID 사용 건수</p>
						</div>
						<a href="" ng-click="goRfidChange()" >More info</a>
						<i class="xi-tags bg-icon-custom"></i>
					</div>
				</div>
				<!-- ./col -->
			</div>

			
			<div class="row d-flex justify-content-center">
				<div class="col-6 graphBox">
					<div class="custom-shadow-box graph" id="barchart_material"></div>
				</div>
				<div class="col-6 graphBox">
					<div class="custom-shadow-box graph" id="piechart"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</html>