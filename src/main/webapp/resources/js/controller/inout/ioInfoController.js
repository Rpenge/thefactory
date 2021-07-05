app.controller('ioInfoController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal','$timeout',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal,$timeout) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location); //현재페이지 정보

		if($rootScope.searchMove == 1){									//페이지 이동후 검색
			$scope.search = {};

			const startDate = formatDate3($rootScope.quickSearch.startDate);
			const endDate = formatDate3($rootScope.quickSearch.endDate);

			$scope.search['startDate'] = startDate;
			$scope.search['endDate'] = endDate;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;

			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inout/inoutList', param );
		}else{															//일반 페이지 이동
			httpGetList($http, $scope,'/inout/inoutList' );
		}
		$rootScope.searchMove = false;

		$scope.excelForm = {};
		$scope.excelFormDate = {};
		$scope.excelFormDate.startDate = new Date( new Date().setMonth( new Date().getMonth() - 1));
		$scope.excelFormDate.endDate = new Date();

		tableAbs(); //테이블 병합

		$scope.tab = {'in': false, 'out': false, 'sell': false};
		$scope.form = {};
		$scope.inView = {};

		$scope.addTabsOn = function (data, tab) {
			$scope.addTabs = true;
			$scope.divFadeIn = true;
			$scope.tab.in = true;

			$scope.inView['STORE_CD'] = data.storeCd;
			$scope.inView['ST_DATE'] = data.inOutDate;

			if(tab == 'in') {
				$scope.tabChange('in');
			}else if(tab == 'out'){
				$scope.tabChange('out');
			}else if(tab == 'sell'){
				$scope.tabChange('sell');
			}

		}

		$scope.tabChange = function (command) {
			$scope.form ={};
			$scope.tab = {};
			$scope.divFadeIn = false;
			if (command == 'in') {
				$scope.tab.in = true;
				$scope.inView['ST_TYPE'] = '060100';

				$scope.form['IN_STORE_CD'] =$scope.inView['STORE_CD'];
				$scope.form['ST_IN_DATE'] = $scope.inView['ST_DATE'];
				const param = generateParam($scope.form);
				httpGetSubList($http, $scope, '/inout/inoutSubList', param);
			} else if (command == 'out') {
				$scope.tab.out = true;
				$scope.inView['ST_TYPE'] = '060200';

				$scope.form['OUT_STORE_CD'] =$scope.inView['STORE_CD'];
				$scope.form['ST_OUT_DATE'] = $scope.inView['ST_DATE'];
				$scope.form['ST_OUT_TYPE'] = '060200';
				const param = generateParam($scope.form);
				httpGetSubList($http, $scope, '/inout/inoutSubList', param);
			} else if (command == 'sell') {
				$scope.tab.sell = true;
				$scope.inView['ST_TYPE'] = '060300';

				$scope.form['OUT_STORE_CD'] =$scope.inView['STORE_CD'];
				$scope.form['ST_OUT_DATE'] = $scope.inView['ST_DATE'];
				$scope.form['ST_OUT_TYPE'] = '060300';
				const param = generateParam($scope.form);
				httpGetSubList($http, $scope, '/inout/inoutSubList', param);
			} else if (command == 'list') {
				$scope.inView = {};
				$scope.addTabs = false;
			}
			setTimeout(function () {
				$scope.divFadeIn = true;
				$scope.$apply();
			}, 300);


		}


		//페이지 이동
		$scope.goPage = function (page) {
			if ($scope.current == page || $scope.end < page || page == 0) {
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope, '/inout/inoutList', param);
			tableAbs();

		}

		$scope.goSubPage = function (page) {
			if ($scope.current == page || $scope.end < page || page == 0) {
				return;
			}
			$scope.form.page = page - 1;
			const param = generateParam($scope.form);
			httpGetSubList($http, $scope, '/inout/inoutSubList', param);
		}

		$scope.excelDown = function(){
			$scope.excelForm['startDate'] = formatDate3($scope.excelFormDate.startDate);
			$scope.excelForm['endDate'] = formatDate3($scope.excelFormDate.endDate);
			var fileName = '입출고내역_'+formatDate3(new Date())+'.xlsx';
			console.log($scope.excelForm);
			$http({
				method : 'POST',
				url : "/excelDown",
				data  : $scope.excelForm,
				responseType : 'blob',
			}).success(function(data){
				var blob = data;
				var downloadLink = window.document.createElement('a');
				downloadLink.href = window.URL.createObjectURL(new Blob([blob]));
				downloadLink.download = fileName;
				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
			}).error(function(data){
				alert('실패');
			});
		}



		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inout/inoutList', param );
			tableAbs();
		}

		function tableAbs(){
			$scope.result = $timeout(function(){
				$('#listTable').rowspan(0);
				return;
			},70);
		}


}]);
