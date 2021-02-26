app.config(function ($routeProvider, $httpProvider, $locationProvider, $provide) {
	$routeProvider
		// home
		.when('/main/home', {templateUrl: '/main/home', controller: 'homeController', resolve: { auth: onlyLoggedIn , layout: pageCheck }})

		.when('/member/login', {templateUrl: '/member/login', controller: 'loginController'})
		.when('/member/userList', {templateUrl: '/member/userList', controller: 'userListController', resolve: { auth: onlyLoggedIn }})
		.when('/member/userReg', {templateUrl: '/member/userReg', controller: 'userRegController'})
		.when('/member/userInfoMod', {templateUrl: '/member/userInfoMod', controller: 'userInfoModController', resolve: { auth: onlyLoggedIn }})

		.when('/assetManagement/assetRepair', {templateUrl:'/assetManagement/assetRepair', controller:'assetRepairController', resolve:{ auth: onlyLoggedIn }})
		.when('/assetManagement/assetList', {templateUrl:'/assetManagement/assetList', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/sample/codeList', {templateUrl:'/sample/codeList', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/assetManagement/assetPrintList', {templateUrl:'/assetManagement/assetPrintList', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn }})
		.when('/assetManagement/assetStatusChange', {templateUrl:'/assetManagement/assetStatusChange', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn }})
		.when('/assetManagement/assetDisList', {templateUrl:'/assetManagement/assetDisList', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn }})

		.when('/assetManagement/assetChange', {templateUrl:'/assetManagement/assetChange', controller:'assetChangeController', resolve:{ auth: onlyLoggedIn }})

		.when('/RFID/rfidChange', {templateUrl:'/RFID/rfidChange', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})
		.when('/RFID/rfidMoveList', {templateUrl:'/RFID/rfidMoveList', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})
		.when('/RFID/rfidRegList', {templateUrl:'/RFID/rfidRegList', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})
		.when('/RFID/rfidWiList', {templateUrl:'/RFID/rfidWiList', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})
		.when('/RFID/rfidFailList', {templateUrl:'/RFID/rfidFailList', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})
		.when('/RFID/rfidDisList', {templateUrl:'/RFID/rfidDisList', controller:'rfidChangeController', resolve:{ auth: onlyLoggedIn }})

		.when('/setting/commonCode', {templateUrl:'/setting/commonCode', controller:'commonCodeController', resolve:{ auth: onlyLoggedIn }})

		.when('/assetManagement/modal/assetRegist', {templateUrl:'/assetManagement/modal/assetRegist', controller:'assetMgController_reg', resolve:{ auth: onlyLoggedIn }})
		.otherwise({redirectTo: '/', templateUrl: '/member/login', controller: 'loginController' });

	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

	//initialize get if not there
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }

    //disable IE ajax request caching
    $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
    // extra
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

    $provide.decorator('$interval', function ($delegate) {
        var originalCancel = $delegate.cancel.bind($delegate);
        $delegate.cancel = function (intervalPromise) {
            var retValue = originalCancel(intervalPromise);
            if (retValue && intervalPromise) {
                intervalPromise.cancelled = true;
            }
            return retValue;
        };
        return $delegate;
    });
});

//로그인 세션 체크
var onlyLoggedIn = function ($q, $rootScope, $http, $location) {
	userLoginCheck($http, $rootScope, $location, $location, false, function(){
		if (!$rootScope.authenticated) {
	        return $q.reject({ authenticated: false });
	    }
	});
};

//레이아웃 구성
var pageCheck = function ($rootScope, $location) {
	if($location.url() == '/main/home'){
		$rootScope.mainPage = true;
	}else{
		$rootScope.mainPage = false;
	}


};


