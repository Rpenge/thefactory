
app.controller('homeController', ['$scope', '$http', '$location','$rootScope', '$interval', '$rootScope', '$timeout', '$window','$uibModal',
	function ($scope, $http, $location,$rootScope, $interval, $rootScope, $timeout, $window, $uibModal) {

		//현재페이지 정보
		pageInfo($rootScope, $location);
		var monthList = [];

		$http.get('/home/homeInfo').success(function(data) {
			$scope.todayData = data.todayData;
			monthList = data.monthData;
			chartDraw();
		});


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
					}
					// , {
					// 	label: '재고',
					// 	data: [
					// 		monthList[5].stockTotcnt,
					// 		monthList[4].stockTotcnt,
					// 		monthList[3].stockTotcnt,
					// 		monthList[2].stockTotcnt,
					// 		monthList[1].stockTotcnt,
					// 		monthList[0].stockTotcnt,
					// 	],
					// 	backgroundColor: 'rgba(75, 192, 192, 0.2)',
					// 	borderColor: 'rgba(25, 99, 132, 1)',
					// 	borderWidth: 1
					// }
					]
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
					}, title: {
						display: true,
						text: '입출고 현황',
						fontSize: 20,
						padding: 20
					}
				}

			});


		}
		function customStr(str){
			return (str.substr(4,2)*1) + '월';
		}


		//modal창 열기
		$scope.modalOpen = function(command, data){

			//자산등록창
			if(command=='assetReg'){
				$ctrl = {};
				$ctrl['commonCode'] = $scope.commonCode;
				var modalInstance = $uibModal.open({
					backdrop: 'static',
					templateUrl: 'assetManagement/modal/assetRegist',
					controller: 'assetMgController_reg',
					controllerAs: '$ctrl',
					size: 'SM'
				});
			}
			//자산등록창
			else if(command=='proReg'){
				$ctrl = {};
				$ctrl['commonCode'] = $scope.commonCode;
				var modalInstance = $uibModal.open({
					backdrop: 'static',
					templateUrl: 'sample/proReg',
					controller: 'assetMgController_reg',
					controllerAs: '$ctrl',
					size: 'lg'
				});
			}
			//유저등록창
			else if(command=='userReg'){
				$ctrl = {};
				// $ctrl['commonCode'] = $scope.commonCode;
				var modalInstance = $uibModal.open({
					backdrop: 'static',
					templateUrl: 'sample/userReg',
					controller: 'assetMgController_reg',
					controllerAs: '$ctrl',
					size: 'lg'
				});
			}

		}

}]);

