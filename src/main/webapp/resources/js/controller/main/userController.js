app.controller('loginController', ['$scope', '$http', '$location', '$routeParams', '$rootScope','$cookieStore',
  function ($scope, $http, $location, $routeParams, $rootScope, $cookieStore) {
//  credentials초기화
  $scope.credentials ={};
  console.log("config");
  //자동저장을 선택유무 : 선택했다면, 쿠키로 id를 넘겨준다.
  if(getCookie('idSave')){
    $scope.idSaveCheck = true;
    // $scope.credentials.userId = $cookieStore.get("uesrId");
    $scope.credentials.userId = getCookie('userIdC');
  }

  //로그인 함수
  $scope.login = function() {
    authenticate($scope.credentials, function() {
       //만약 권한이 있다면 에러 메시지 x, 만약 권한이 없다면 에러메시지를 띄운다.

      if ($rootScope.authenticated) {
        console.log("ok");
        $rootScope.reload();
        $scope.error = '';
      } else {
        console.log("no");
        $location.url("/");
        $scope.error = $rootScope.authErrorMsg;
      }
    });
  };

  //setCookie(name, value, exp)
  // getCookie(name) 
  //deleteCookie(name) 
  //document에 세팅하고 지우는 함수
  var authenticate = function(credentials, callback) {
    console.log("권한세팅");
    if($scope.idSaveCheck) {
      setCookie('idSave','true',7);
      setCookie('userIdC',credentials.userId,7);
      console.log("자동로그인일때");
    }else{
      deleteCookie('idSave');
      deleteCookie('userIdC');
      console.log("아닐때");
    }
    //header 파일 생성 유저id :유저 PW
    var headers = credentials ? {authorization : "Basic "
      + btoa(credentials.userId + ":" + credentials.userPw)
    } : {};

    //권한 통신 
    $http.get('/member/userAuth', {headers : headers}).success(function(data) {
      if (data.userId) {
	console.log("userAuth보낸다.");
        $rootScope.userId = data.userId;
        sessionStorage.setItem('id', data.userId);
        sessionStorage.setItem('role', data.role);
        sessionStorage.setItem('storeCd', data.storeCd);
        $rootScope.role = data.role;
            $rootScope.authenticated = true;
        $rootScope.topMenu = data.auth;
        
        //관리자면 
        if(data.role == '010101'){
          console.log("관");
          $rootScope.storeCd = null;
        }
        $rootScope.storeCd = data.storeCd;

        if($scope.autoLogin == true){
          setCookie('autoLogin',true,7);
          setCookie('userId',credentials.userId,7);
          setCookie('userPw',credentials.userPw,7);
        }else if($scope.idSaveCheck){
          deleteCookie('autoLogin');
          deleteCookie('userPw');
        }else{
          deleteCookie('autoLogin');
          deleteCookie('userId');
          deleteCookie('userPw');
        }
      } else {
            $rootScope.authenticated = false;
            $rootScope.authErrorMsg = 3002;
        }
      callback();
    }).error(function(data) {
      $rootScope.authErrorMsg = data.trim();
      $rootScope.authenticated = false;
      callback();
    });
  }

  //인증 되었으면 home으로 이동
  if($rootScope.authenticated == null){
    $rootScope.authenticated = false;
  }
    if ($rootScope.authenticated) {
     $location.url("/main/home");
    } else {
     $location.url("/");
    }

//  if(getCookie('autoLogin')){
//    $scope.autoLogin = true;
//    $scope.credentials.userId = getCookie('userId');
//    $scope.credentials.userPw = getCookie('userPw');
//    $scope.login();
//  }

//  $scope.clickUserReg = function(){
//    $location.url("/member/userReg");
//  };
}]);