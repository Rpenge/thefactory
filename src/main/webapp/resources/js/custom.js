function userLoginCheck($http, $rootScope,$window, $location){
  //  ID를 받아오는 유무에 따라 권한 설정
    if(sessionStorage.getItem("id")){
      $rootScope.authenticated = true;
    }else{
      $rootScope.authenticated = false;
      $window.location.url("/");
      sessionStorage.clear();
    }
    $rootScope.currentMenu = {};
  }
//쿠키사용set
function setCookie(name, value, exp) {
  var date = new Date();
  date.setTime(date.getTime() + exp*24*60*60*1000);
  document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

//쿠키사용get
function getCookie(name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value? value[2] : null;
}

//쿠키지우기
function deleteCookie(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}
