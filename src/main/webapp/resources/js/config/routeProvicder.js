//기본 config 각 Url형식을 각각의 Controller에 연결시켜주는 부분
app.config(function($routeProvider, $httpProvider, $locationProvider, $provide) {
  //when(path, route),otherwise(params)이것의 장점은 새로고침없이 단일페이지에서 HTML Element의 교체로 쉽게 할수있다.
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




  //header에 공통으로   X-Requested-With: XMLHttpRequest를 넣어주는 부분, XMLHttpRequest 은 해당요청이 Ajax인걸 의미,
  // JSON Hijacking을 방어하는 용도로 사용
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  // initialize get if not there(header를 디폴트 초기값으로 받아온다)
  if (!$httpProvider.defaults.headers.get) {
    $httpProvider.defaults.headers.get = {};
  }

  // disable IE ajax request caching: cach 유효성 확인용
  $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
  // extra 서버의 과부하를 줄이기위하여 cach-control를 사용하는데 여기에선 nocach로 사용한다.
  $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
  // extra 요청 응답에 관련 헤더 Cache-Control와 유사 역할인데 no-cache유효성검사를 강제한다.
  $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

  
  $provide.decorator('$interval', function($delegate) {
    console.log("routeProvicder");
    var originalCancel = $delegate.cancel.bind($delegate);
    $delegate.cancel = function(intervalPromise) {
      var retValue = originalCancel(intervalPromise);
      if (retValue && intervalPromise) {
        intervalPromise.cancelled = true;
      }
      return retValue;
    };
    return $delegate;
  });
});

// 로그인 세션 체크
var onlyLoggedIn = function($q, $rootScope, $http, $location) {
  userLoginCheck($http, $rootScope, $location, $location, false, function() {
    if (!$rootScope.authenticated) {
      return $q.reject({
        authenticated : false
      });
    }
  });
};

// 레이아웃 구성
var pageCheck = function($rootScope, $location, $http) {

  // 메인
  if ($location.url() == '/main/home') {
    $rootScope.mainPage = true;
  } else {
    $rootScope.mainPage = false;
  }

  // 등록페이지
//  var regPage = [ '/base/comm', '/base/brand', '/system/user', '/base/device' ];
//  if (regPage.includes($location.url())) {
//    $rootScope.regPage = true;
//  } else {
//    $rootScope.regPage = false;
//  }
//
//  //today HOME 화면 입출고 수량 당일 데이터 조회
//  $http.get('/home/homeSimple').success(function(data) {
//    $rootScope.todayData = data.todayData;
//  });

};
