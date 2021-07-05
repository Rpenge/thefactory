app.controller('stkTagController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location);
		// var expansion = true;
		$scope.search = {};

		httpGetList($http, $scope,'/stock/stkTagList' );

		if($rootScope.searchMove == 1){	//페이지 이동후 검색
			$scope.search = {};
			if($rootScope.quickSearch.brand){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.brand.substr(0,2);
			}
			if($rootScope.quickSearch.gender){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.gender.substr(0,4);
			}
			if($rootScope.quickSearch.cls){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.cls;
			}
			$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		}else if($rootScope.searchMove == 2) { //단어 검색
			$scope.search = $rootScope.quickSearchWord;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param )
		}else{
			// $scope.search['ex'] = 'rfid';
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		}
		$rootScope.searchMove = false;

		// //차이 발생 목록
		// $scope.stkDif = function(command){
		// 	if(command == 'dis') {
		// 		expansion = true;
		// 		$scope.search['ex'] = command;
		// 		$scope.search['page'] = 0;
		// 		const param = generateParam($scope.search);
		// 		httpGetList($http, $scope, '/stock/stockList', param);
		// 	}else if(command == 'rfid'){
		// 		expansion = true;
		// 		$scope.search['ex'] = command;
		// 		$scope.search['page'] = 0;
		// 		const param = generateParam($scope.search);
		// 		httpGetList($http, $scope, '/stock/stockList', param);
		// 	}else{
		// 		expansion = false;
		// 		$scope.search['ex'] = null;
		// 		$scope.search['page'] = 0;
		// 		const param = generateParam($scope.search);
		// 		httpGetList($http, $scope,'/stock/stockList', param );
		// 	}
		// }

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			// if(expansion){
			// 	$scope.search['ex'] = true;
			// }else{
			// 	$scope.search['ex'] = null;
			// }
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param );
		}

}]);
