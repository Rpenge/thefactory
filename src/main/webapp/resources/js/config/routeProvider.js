app.config(function ($routeProvider, $httpProvider, $locationProvider, $provide) {
	$routeProvider
		.when('/main/home', {templateUrl: '/main/home', controller: 'homeController', resolve: { auth: onlyLoggedIn , layout: pageCheck }})

		// .when('/member/login', {templateUrl: '/member/login', controller: 'loginController'})
		.when('/member/appDown', {templateUrl:'/member/appDown', controller:'appController'})
		// .when({redirectTo: '/member/appDown', templateUrl: '/member/appDown', controller: 'appController' })

		.when('/system/user', {templateUrl:'/system/user', controller:'userController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/base/comm', {templateUrl:'/base/comm', controller:'codeController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/brand', {templateUrl:'/base/brand', controller:'brandController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/product', {templateUrl:'/base/product', controller:'productController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/base/device', {templateUrl:'/base/device', controller:'deviceController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/inout/input', {templateUrl:'/inout/input', controller:'inputController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/output', {templateUrl:'/inout/output', controller:'outputController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/sales', {templateUrl:'/inout/sales', controller:'salesController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inout/ioInfo', {templateUrl:'/inout/ioInfo', controller:'ioInfoController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/inven/inv', {templateUrl:'/inven/inv', controller:'invController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/inven/invInfo', {templateUrl:'/inven/invInfo', controller:'invInfoController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/stock/stk', {templateUrl:'/stock/stk', controller:'stockController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.when('/stock/stkTag', {templateUrl:'/stock/stkTag', controller:'stkTagController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})

		.when('/assetManagement/assetList', {templateUrl:'/assetManagement/assetList', controller:'assetMgListController', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})


		.when('/ex/stk', {templateUrl:'/ex/stk', controller:'stock01Controller', resolve:{ auth: onlyLoggedIn, layout: pageCheck }})
		.otherwise({redirectTo: '/member/login', templateUrl: '/member/login', controller: 'loginController' });


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
var pageCheck = function ($rootScope, $location, $http) {

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

	$http.get('/home/homeSimple').success(function(data){
		$rootScope.todayData = data.todayData;
	});

};


