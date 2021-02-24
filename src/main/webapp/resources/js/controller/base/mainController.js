app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};
});

app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

	// $http({
	// 	method: 'GET',
	// 	url: '/commonCode',
	// 	data: {},
	// 	headers: {'Content-Type': 'application/json; charset=utf-8'}
	// }).success(function(data) {
	// 	$scope.commonCode = data;
	// }).error(function(data) {
	//     alert('code조회 실패');
	// });
	//
	// $http({
	// 	method: 'GET',
	// 	url: '/reload',
	// 	headers: {'Content-Type': 'application/json; charset=utf-8'}
	// }).success(function(data) {
	// 	$rootScope.topMenu = data.menu;
	//
	// }).error(function(data) {
	// 	alert('menu 조회 실패');
	// });

	$scope.logout = function() {
		$http({
			method: 'POST',
			url: '/member/logout',
			data: {},
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).success(function(data, status, headers, config) {
			if(status == 200) {
				$rootScope.authenticated = false;
				$location.url("/");
				sessionStorage.clear();
			} else {
				modalCall("에러발생");
			}
		}).error(function(data, status, headers, config) {
		    $rootScope.authenticated = false;
		    $location.url("/");
		});
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
    	closeSideMenu();
    };

    $scope.goUserList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userList");
    	closeSideMenu();
    };

	$scope.goAssetManagementList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetList");
    	closeSideMenu();
	};

	$scope.goAssetStatusChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetStatusChange");
    	closeSideMenu();
	};

	$scope.goAssetDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetDisList");
    	closeSideMenu();
	};

	$scope.goRfidChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidChange");
    	closeSideMenu();
	};
	$scope.goCommonCode = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/setting/commonCode");
    	closeSideMenu();
	};

	$scope.goUserInfo = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userInfoMod");
	};

	$scope.goRfidRegList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidRegList");
    	closeSideMenu();
	};

	$scope.goRfidMoveList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidMoveList");
    	closeSideMenu();
	};

	$scope.goRfidWiList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidWiList");
    	closeSideMenu();
	};

	$scope.goRfidDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidDisList");
    	closeSideMenu();
	};

	$scope.goRfidFailList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidFailList");
    	closeSideMenu();
	};

	$scope.goMenu = function(url){
		$window.sessionStorage.removeItem("current");
		$location.url(url);
		closeSideMenu();
	}

	//페이지 이동
	$scope.goPage = function(data){
		$location.url(data.url);
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
