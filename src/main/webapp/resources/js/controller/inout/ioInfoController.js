app.controller('ioInfoController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal','$timeout',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal,$timeout) {

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


		tableAbs(); //테이블 병합


		$scope.tab = {'in': false, 'out': false, 'sell': false};
		$scope.form = {};
		$scope.inView = {};

		$scope.addTabsOn = function (data) {
			$scope.addTabs = true;
			$scope.divFadeIn = true;
			$scope.tab.in = true;

			$scope.inView['STORE_CD'] = data.storeCd;
			$scope.inView['ST_DATE'] = data.inOutDate;
			$scope.tabChange('in')

		}

		$scope.tabChange = function (command) {
			const form ={};
			$scope.tab = {};
			$scope.divFadeIn = false;
			if (command == 'in') {
				$scope.tab.in = true;
				$scope.inView['ST_TYPE'] = '060100';

				form['IN_STORE_CD'] =$scope.inView['STORE_CD'];
				form['ST_IN_DATE'] = $scope.inView['ST_DATE'];
				const param = generateParam(form);
				httpGetSubList($http, $scope, '/inout/inoutSubList', param);
			} else if (command == 'out') {
				$scope.tab.out = true;
				$scope.inView['ST_TYPE'] = '060200';

				form['OUT_STORE_CD'] =$scope.inView['STORE_CD'];
				form['ST_OUT_DATE'] = $scope.inView['ST_DATE'];
				form['ST_OUT_TYPE'] = '060200';
				const param = generateParam(form);
				httpGetSubList($http, $scope, '/inout/inoutSubList', param);
			} else if (command == 'sell') {
				$scope.tab.sell = true;
				$scope.inView['ST_TYPE'] = '060300';

				form['OUT_STORE_CD'] =$scope.inView['STORE_CD'];
				form['ST_OUT_DATE'] = $scope.inView['ST_DATE'];
				form['ST_OUT_TYPE'] = '060300';
				const param = generateParam(form);
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


		//검색
		// $scope.searchBtn = function(command){
		// 	if(command == 'group'){
		// 		const param = generateParam($scope.searchGroup);
		// 		httpGetList($http, $scope,'/system/userList', param );
		// 		$scope.search.storeCd = $scope.searchGroup.storeCd;
		// 		$scope.search.grade = $scope.searchGroup.grade;
		// 		$scope.search.userStat = $scope.searchGroup.userStat;
		// 	}else if(command == 'word'){
		// 		const param = generateParam($scope.searchWord);
		// 		httpGetList($http, $scope,'/system/userList', param );
		// 		$scope.search.word = $scope.searchWord.word;
		//
		// 	}
		// }

		//테이블 버튼 사용(탈퇴)
		// $scope.tableBtn = function(command){
		// 	if(command == 'Withdrawal'){
		// 		if(checkList.length < 1){
		// 			modalAlert($uibModal, "사용자 수정", "탈퇴할 회원을 선택해주세요.");
		// 			return;
		// 		}
		// 		$http({
		// 			method : 'POST',
		// 			url : "/system/userWd",
		// 			data  :  {'prList' :checkList},
		// 			headers: {'Content-Type':'application/json; charset=utf-8'}
		// 		}).success(function(data){
		// 			if(data.resultCode == 'S') {
		// 				modalAlert($uibModal, "사용자 수정", "사용자 정보가 변경 되었습니다.");
		// 			}
		// 			$rootScope.reload();
		// 		}).error(function(data){
		// 			alert('정보 업데이트 실패');
		// 		});
		// 	}
		// }

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
