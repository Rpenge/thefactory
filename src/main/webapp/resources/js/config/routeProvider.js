app.config(function ($routeProvider, $httpProvider, $locationProvider, $provide) {
	$routeProvider
		.when('/main/home', {templateUrl: '/main/home', controller: 'homeController', resolve: { auth: onlyLoggedIn , layout: pageCheck }})

		// .when('/member/login', {templateUrl: '/member/login', controller: 'loginController'})
		// .when('/member/appDown', {templateUrl:'/member/appDown', controller:'appController'})

		.when('/system/user', {templateUrl:'/system/user', controller:'userController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/base/comm', {templateUrl:'/base/comm', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/brand', {templateUrl:'/base/brand', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/product', {templateUrl:'/base/product', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/device', {templateUrl:'/base/device', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/inout/input', {templateUrl:'/inout/input', controller:'inputController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/output', {templateUrl:'/inout/output', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/sales', {templateUrl:'/inout/sales', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/ioInfo', {templateUrl:'/inout/ioInfo', controller:'ioInfoController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/inven/inv', {templateUrl:'/inven/inv', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inven/invInfo', {templateUrl:'/inven/invInfo', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/stock/stk', {templateUrl:'/stock/stk', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/assetManagement/assetList', {templateUrl:'/assetManagement/assetList', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		// .otherwise({redirectTo: '/member/login', templateUrl: '/member/login', controller: 'loginController' });
		.otherwise({redirectTo: '/member/appDown', templateUrl: '/member/appDown', controller: 'appController' });

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

	//메인
	if($location.url() == '/main/home'){
		$rootScope.mainPage = true;
	}else{
		$rootScope.mainPage = false;
	}

	//등록페이지
	var regPage = ['/base/comm', '/base/brand','/system/user','/base/device'];
	if(regPage.includes($location.url())){
		$rootScope.regPage = true;
	}else{
		$rootScope.regPage = false;
	}

	//퀵 서치 추가
	var addQuick1 = ['/base/product','/inout/input','/inout/ioInfo'];
	if(addQuick1.includes($location.url())){
		$rootScope.addQuick1 = true;
	}else{
		$rootScope.addQuick1 = false;
	}


};


