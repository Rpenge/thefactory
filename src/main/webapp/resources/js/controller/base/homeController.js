app.controller('homeController', ['$scope', '$http', '$location','$rootScope', '$interval', '$timeout', '$window','$uibModal',
	function ($scope, $http, $location,$rootScope, $interval, $timeout, $window, $uibModal) {


		$rootScope.quickSearch = {}; //검색조건
		$rootScope.quickSearchWord = {}; // 검색어
		$scope.view = {};
		$rootScope.quickSearch.startDate = new Date( new Date().setMonth( new Date().getMonth() - 1));
		$rootScope.quickSearch.endDate = new Date();

		$rootScope.quickCmd['cmd'] = 'IO1';	//quick검색 초기값
		//현재페이지 정보
		pageInfo($rootScope, $location);
		$rootScope.quick1 = true;
		$rootScope.quick2 = false;
		$rootScope.quick3 = true;
		$rootScope.quick4 = false;
		var monthList = [];
		$http.get('/home/homeInfo').success(function (data) {
			$rootScope.todayData = data.todayData;
			monthList = data.monthData;
			if(sessionStorage.getItem('id')) {
				chartDraw();
			}
		});

		$scope.goMainMenu = function(cmd){
			for(const value of $rootScope.topMenu){
				if(value.PGM_CD == cmd){
					$location.url(value.PGM_URL);
					return;
				}
			}
			modalAlert($uibModal, "메뉴이동", "접근권한이 없습니다");

		}



		//그래프
		function chartDraw() {
			var ctx = document.getElementById('myChart').getContext('2d');
			var myChart = new Chart(ctx, {
				type: 'bar',
				data: {
					labels: [
						customStr(monthList[5].searchMonth),
						customStr(monthList[4].searchMonth),
						customStr(monthList[3].searchMonth),
						customStr(monthList[2].searchMonth),
						customStr(monthList[1].searchMonth),
						customStr(monthList[0].searchMonth)
					],

					datasets: [{
						label: '입고',
						data: [
							monthList[5].inTotcnt,
							monthList[4].inTotcnt,
							monthList[3].inTotcnt,
							monthList[2].inTotcnt,
							monthList[1].inTotcnt,
							monthList[0].inTotcnt,
						],
						backgroundColor: 'rgba(255, 99, 132, 0.2)',
						borderColor: 'rgba(255, 99, 132, 1)',
						borderWidth: 1
					},{
						label: '출고',
						data: [
							monthList[5].outTotcnt,
							monthList[4].outTotcnt,
							monthList[3].outTotcnt,
							monthList[2].outTotcnt,
							monthList[1].outTotcnt,
							monthList[0].outTotcnt,
						],
						backgroundColor: 'rgba(54, 162, 235, 0.2)',
						borderColor: 'rgba(25, 99, 132, 1)',
						borderWidth: 1
					}, {
						label: '판매/배송',
						data: [
							monthList[5].sellTotcnt,
							monthList[4].sellTotcnt,
							monthList[3].sellTotcnt,
							monthList[2].sellTotcnt,
							monthList[1].sellTotcnt,
							monthList[0].sellTotcnt,
						],
						backgroundColor: 'rgba(75, 192, 192, 0.2)',
						borderColor: 'rgba(25, 99, 132, 1)',
						borderWidth: 1
					}]
				},
				options: {
					legend: {
						position: 'right',
						align: 'right',
						labels: {
							padding: 10
						}
					},
					maintainAspectRatio: false,
					scales: {
						yAxes: [{
							scaleLabel: {
								display: true,
								labelString: '수량'
							},
							ticks: {
								beginAtZero: true
							}
						}]
					}
					// , title: {
					// 	display: true,
					// 	text: '입출고 현황',
					// 	fontSize: 20,
					// 	padding: 20
					// }
				}
			});
		}
		function customStr(str){
			return (str.substr(4,2)*1) + '월';
		}



		//어플 업로드 열기
		$scope.uploaderOpen = function(command){
			if(command=='pda'){
				$ctrl = {'cmd':'020102'};
				$ctrl['commonCode'] = $scope.commonCode;
				var modalInstance = $uibModal.open({
					backdrop: 'static',
					templateUrl: 'modal/upload',
					controller: 'uploadController',
					controllerAs: '$ctrl',
					size: 'SM'
				});
			}else if(command=='fixed'){
				$ctrl = {'cmd':'020101'};
				$ctrl['commonCode'] = $scope.commonCode;
				var modalInstance = $uibModal.open({
					backdrop: 'static',
					templateUrl: 'modal/upload',
					controller: 'uploadController',
					controllerAs: '$ctrl',
					size: 'SM'
				});
			}
		}

}]);




