//angular는 불러오는 순서가 app.config, app.run, DOM, app.controller
//처음 실행해주는부분
app.run(function($rootScope, $http, $route, $window){
  $rootScope.reload = function(){
    $route.reload();
    $window.sessionStorage.removeItem("current");
  };
  $rootScope.imgPath = "/resources/systemk";
  console.log("index");
});

 
  app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

    //초기화 설정
//    $rootScope.quickSearch = {}; //검색조건
//    $rootScope.quickSearchWord = {}; // 검색어
//    $rootScope.quickCmd = {};
//    $scope.view = {};
//
//    $rootScope.quickCmd.cmd = 'IO1';  //quick검색 초기값
//    $rootScope.currentMenu ={};
//    
//  //data초기화 및 설정
//    $rootScope.quickSearch.startDate = new Date( new Date().setMonth( new Date().getMonth() - 1));
//    $rootScope.quickSearch.endDate = new Date();
//    $scope.startDateOptions = {'showWeeks':false, 'maxDate':$rootScope.quickSearch.endDate}; // 달력 옵션
//    $scope.endDateOptions = {'showWeeks':false,'maxDate':new Date()}; // 달력 옵션
//
//    $scope.today = new Date();

    //공통코드 , 브랜드코드 조회
    //toUpperCase 메서드는 문자열을 대문자로 변화해 반환
//    $http.get('/member/getCode').success(function(data) {
//      $rootScope.commCode = data.commCode;
//      $rootScope.brandList = data.brandList;
//      $rootScope.brandList.sort(function(a, b) {
//        var nameA = a.brandNm.toUpperCase(); // ignore upper and lowercase
//        var nameB = b.brandNm.toUpperCase(); // ignore upper and lowercase
//        if (nameA < nameB) {
//          return -1;
//        }
//        if (nameA > nameB) {
//          return 1;
//        }
//        return 0;
//      });
//      code($rootScope);
//    });

    //새로고침 : 세션확인 후 메뉴 다시 조회
    //아이디를 가져왔을대, 권한요청
    //'010101' -> 관리자, storeCd ->매장코드
    if(sessionStorage.getItem('id')){
      $rootScope.userId = sessionStorage.getItem('id');
      $http.get('/member/reUserAuth').success(function(data) {
        //시스템 관리자면서,  매장코드가 없을시
        if (data.userId) {
          $rootScope.topMenu = data.auth;
          $rootScope.role = data.role;
          //시스템 관리자면서,  매장코드가 없을시
          if(data.role == '010101' && $rootScope.quickSearch.storeCd == null){
            $rootScope.quickSearch.storeCd = null;
            //관리자는 아니면서 매장 코드가 없을시 세션에서  메장코드를 얻어온다.
          }else if($rootScope.quickSearch.storeCd == null){
            $rootScope.quickSearch.storeCd = sessionStorage.getItem('storeCd');
          }
          //권한 들고 메뉴체크를실행한다.
          menuCheck($rootScope, $location);
        } else {
          //권한 없어서 로그아웃을 실행한다.
          logout($http, $rootScope, $location);
          return;
        }
      });
    }
  //로그아웃 함수 
//    $scope.logout = function() {
//      logout($http, $rootScope, $location);
//    };

    //연결이 끝는 함수
    $scope.disconnection = function(){
      if ($scope.client != null) {
        $scope.client.disconnect();
        $scope.resData = {};
        }
    };
    
    //권한이 있다면 보여줘라.
    $scope.$watch(function(){
      return $rootScope.authenticated;
      }, function() {
        //만약 권한이 정의 되지 않았으면 아래를 실행해라.
      if($rootScope.authenticated != undefined){
        if($rootScope.authenticated){
          // if($location.url() != "/main/home"){}
        } else {
          $scope.disconnection();
        }
      }
    }, true);
    
    //"current"의 키값을 제거하고 main/home을 가져와라
    //   $location의 기능은 param없는 getter/setter이다.
    $scope.goMain = function(){
        $window.sessionStorage.removeItem("current");
        $location.url("/main/home");
      };

    //페이지 이동
    $scope.goMenu = function(data){
      $location.url(data.PGM_URL);
    }

    //quick search
    $rootScope.goSearch = function(command, word, $event){

      //enter 이벤트
//      if($event){
//        const pressedKey = window.event ? $event.keyCode : $event.which;
//        if(pressedKey  != '13'){
//          return;
//        }
//      }
      //searchMove  1:페이지 이동후 검색, 2: 단어 검색, 그 외: 일반 페이지 이동 조회

      for(const value of $scope.topMenu){
        if(value.PGM_CD == command){
         console.log(value.PGM_CD);
//          if(!word){
//            $rootScope.searchMove = 1;
//          }else{
//            $rootScope.searchMove = 2;
//          }
//
//          if($location.url().split('?')[0] == value.PGM_URL) {
//            $scope.reload();
//          }else{
//            $location.url(value.PGM_URL);
//          }
          break;
        }
      }
    }

    //브랜드 선택
//    $scope.brandSelect = function(data){
//      //초기화
//      console.log(data);
//      $rootScope.quickSearch.gender = "";
//      $rootScope.quickSearch.cls = "";
//      //데이터가 없을때 
//      if(!data){
//        $rootScope.quickSearch.brand = null;
//        $scope.view.brand = null;
//        $scope.subBrandCls = [];
//        return;
//      }
//      $rootScope.quickSearch.brand = data.brandKindCd;
//      $scope.view.brand = data.brandNm;
//
//      $http.get('/member/brandSub?brandCd='+ data.brandKindCd.substr(0,2)).success(function(data) {
//        $scope.subBrand = data.brandSubList;
//      });
//    }

    //성별 선택
//    $scope.genderSelect = function(data){
//      $scope.subBrandCls = [];
//      for(const value of $scope.subBrand){
//        if(data.substr(0,4) == value.brandKindCd.substr(0,4) && value.codeLevel == 'S'){
//          $scope.subBrandCls.push(value);
//        }
//      }
//    }

    //quick search 검색구분 변경
//    $rootScope.quick1List = ['IO1','IO2','IO3'];
//    $rootScope.quick2List = ['IO4','IV1'];
//    $rootScope.quick3List = ['IO1','IO2','IO3','ST1','BS3','ST2','IV2'];
//    $rootScope.quick4List = [];
//    $rootScope.addQuick = function(command){
//      $rootScope.quickSearch.workGub = null;
//      if($rootScope.quick1List.includes(command)){
//        $rootScope.quick1 = true;
//      }else{
//        $rootScope.quick1 = false;
//      }
//
//      if($rootScope.quick2List.includes(command)){
//        $rootScope.quick2 = true;
//      }else{
//        $rootScope.quick2 = false;
//      }
//
//      if($rootScope.quick3List.includes(command)){
//        $rootScope.quick3 = true;
//      }else{
//        $rootScope.quick3 = false;
//      }
//
//      if($rootScope.quick4List.includes(command)){
//        $rootScope.quick4 = true;
//      }else{
//        $rootScope.quick4 = false;
//      }
//      workGub(command)
//    }
//    $rootScope.addQuick($rootScope.quickCommand);
//
//    //quick search 검색구분 변경 : 구분값 변경 & disalbed 처리
//    function workGub(command){
//      const input = ['IO1'];
//      const output = ['IO2'];
//      const sell = ['IO3'];
//      if(input.includes(command)){
//        $rootScope.quickSearch.workGub = '060100';
//        $scope.gubDisabled = true;
//      }else if(output.includes(command)){
//        $rootScope.quickSearch.workGub = '060200';
//        $scope.gubDisabled = true;
//      }else if(sell.includes(command)){
//        $rootScope.quickSearch.workGub = '060300';
//        $scope.gubDisabled = true;
//      }else{
//        $scope.gubDisabled = false;
//      }
//    }
//
//    //상위 작업구분 변경
//    $scope.detatilGub = function(data){
//      if($scope.quickSearch.workGub == null){
//        return false;
//      }
//      if($scope.quickSearch.workGub.substr(0,4) == data.substr(0,4)){
//        return true;
//      }
//      return false;
//    }

  }]);