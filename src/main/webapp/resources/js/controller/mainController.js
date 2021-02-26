app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};

});
app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

	$http({
		method: 'GET',
		url: '/commonCode',
		data: {},
		headers: {'Content-Type': 'application/json; charset=utf-8'}
	}).success(function(data) {
		$scope.commonCode = data;
	}).error(function(data) {
	    alert('code조회 실패');
	});

	$http({
		method: 'GET',
		url: '/reload',
		headers: {'Content-Type': 'application/json; charset=utf-8'}
	}).success(function(data) {
		$rootScope.topMenu = data.menu;

	}).error(function(data) {
		alert('menu 조회 실패');
	});

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

    };

    $scope.goUserList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userList");

    };

	$scope.goAssetManagementList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetList");

	};

	$scope.goAssetStatusChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetStatusChange");

	};

	$scope.goAssetDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetDisList");

	};

	$scope.goRfidChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidChange");

	};
	$scope.goCommonCode = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/setting/commonCode");

	};

	$scope.goUserInfo = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userInfoMod");
	};

	$scope.goRfidRegList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidRegList");

	};

	$scope.goRfidMoveList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidMoveList");

	};

	$scope.goRfidWiList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidWiList");

	};

	$scope.goRfidDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidDisList");

	};

	$scope.goRfidFailList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidFailList");

	};

	// $scope.goMenu = function(url){
	// 	$window.sessionStorage.removeItem("current");
    // 	$location.url(url);
    //
	// }

	//페이지 이동
	$scope.goMenu = function(data){
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


	//모바일 사이즈시 사이드메뉴 닫기
	// function closeSideMenu(){
	// 	if(window.innerWidth <= 768){
	// 		$scope.menu = false;
	// 	}else{
	// 		$scope.menu = false;
	// 	}
	// }


	var mql = window.matchMedia("screen and (max-width: 768px)");
	mql.addListener(function(e) {
	    if(e.matches) {
	    	$scope.menu = false;
	    	$scope.$apply();
	    } else {
	    	$scope.menu = true;
	    	$scope.$apply();
	    }
	});
}]);

// app.controller('homeController', ['$scope', '$http', '$location', '$interval', '$rootScope', '$timeout', '$window',
// 	function ($scope, $http, $location, $interval, $rootScope, $timeout, $window) {
//
// }]);

app.controller('statsModalController', ['$scope', '$http', '$element', 'close', function ($scope, $http, $element, close) {
	$scope.close = function(result) {
		$element.modal('hide');
	};
	$scope.cancel = function() {
	    $element.modal('hide');
	};
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
}]);
