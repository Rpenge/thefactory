app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};
});

app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {


	//현재페이지 정보
	pageInfo($rootScope, $location);
	// $window.scrollTo(0,0);


	$http.get('/member/reUserAuth').success(function(data) {
		console.log(data.userId);
		if (data.userId) {
			sessionStorage.setItem('id', data.userId);
			$rootScope.topMenu = data.auth;
		} else {
			console.log(111);
			logout($http, $rootScope, $location);
			return;
		}
		$rootScope.commCode = data.commCode;
		code();
	});

	//공통코드
	$rootScope.grade = []; // 권한목록
	$rootScope.store = []; // 매장목록
	function code() {
		for (value of $rootScope.commCode) {
			var bcd = value.commCd.substr(0, 2);
			var mcd = value.commCd.substr(2, 2);
			var scd = value.commCd.substr(4, 2);
			if (bcd == '01' && mcd == '01' && scd != '00') { // 사용자 권한
				$rootScope.grade.push(value);
			} else if (bcd == '01' && mcd == '02' && scd != '00') { // 매장
				$rootScope.store.push(value);
			}
		}
	}

	$scope.logout = function() {
		logout($http, $rootScope, $location);
		// $http({
		// 	method: 'POST',
		// 	url: '/member/logout',
		// 	headers: {'Content-Type': 'application/json; charset=utf-8'}
		// }).success(function(data, status, headers, config) {
		// 	if(status == 200) {
		// 		$rootScope.authenticated = false;
		// 		$location.url("/");
		// 		sessionStorage.clear();
		// 	} else {
		// 		modalCall("에러발생");
		// 	}
		// }).error(function(data, status, headers, config) {
		//     $rootScope.authenticated = false;
		//     $location.url("/");
		// });

	};



	$scope.client;

	$scope.disconnection = function(){
		if ($scope.client != null) {
			$scope.client.disconnect();
			$scope.resData = {};
	    }
	};

	$scope.$watch(function(){
		return $rootScope.authenticated;
		}, function() {
		if($rootScope.authenticated != undefined){
			if($rootScope.authenticated){
				if($location.url() != "/main/home"){
				}
			} else {
				$scope.disconnection();
			}
		}
	}, true);

	$scope.goMain = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/main/home");
    };

	$scope.goAssetManagementList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetList");
	};

	$scope.goMenu = function(data){
		$location.url(data.PGM_URL);
	}

	$scope.content = {'width':'100%'};
	//사이드 메뉴 토글
	$scope.menuToggle = function(){
		$scope.menu = $scope.menu ? false : true;
	}

	$scope.dock = [true, true, true, true, true];
	$scope.sideMenuDock = function(idx){
		$scope.dock[idx] = $scope.dock[idx] ? false : true;
	}


}]);


app.controller('modalController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$uibModalInstance',
	function ($scope, $http, $location, $routeParams, $rootScope, $uibModalInstance) {
		$scope.title = $ctrl.title;
		$scope.body = $ctrl.body;
		$scope.ok = function() {
			$uibModalInstance.close();
		};
		$scope.cancel = function() {
			$uibModalInstance.dismiss();
		};
	}
]);
