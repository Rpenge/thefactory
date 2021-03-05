
app.controller('homeController', ['$scope', '$http', '$location','$rootScope', '$interval', '$rootScope', '$timeout', '$window','$uibModal',
	function ($scope, $http, $location,$rootScope, $interval, $rootScope, $timeout, $window, $uibModal) {


		//그래프
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: ['1월', '2월', '3월', '4월','5월','6월'],
				datasets: [{
					label: '입고',
					data: [12, 19, 3, 5,12,15],
					backgroundColor: 'rgba(255, 99, 132, 0.2)',
					borderColor: [
						'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				},{
					label: '출고',
					data: [11, 18, 2, 4,1,1],
					backgroundColor: [
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)'
					],
					borderColor: [
						'rgba(25, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)'
					],
					borderWidth: 1
				},{
					label: '재고',
					data: [11, 18, 2, 4,1,1],
					backgroundColor: [
						'rgba(75, 192, 192, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(75, 192, 192, 0.2)',
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
				maintainAspectRatio: false,
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						}
					}]
				}
			}
		});


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

