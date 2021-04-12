app.controller('loginController', ['$scope', '$http', '$location', '$routeParams', '$rootScope','$cookieStore',
	function ($scope, $http, $location, $routeParams, $rootScope, $cookieStore) {



	$scope.credentials ={};
	if(getCookie('idSave')){
		$scope.idSaveCheck = true;
		// $scope.credentials.userId = $cookieStore.get("uesrId");
		$scope.credentials.userId = getCookie('userId');
	}


	//로그인
	$scope.login = function() {
		authenticate($scope.credentials, function() {
			if ($rootScope.authenticated) {
				$rootScope.reload();
				$scope.error = '';
			} else {
				$location.url("/");
				$scope.error = $rootScope.authErrorMsg;
			}
		});
	};


	var authenticate = function(credentials, callback) {
		if($scope.idSaveCheck) {
			setCookie('idSave','true',7);
			setCookie('userId',credentials.userId,7);
		}else{
			deleteCookie('idSave');
			deleteCookie('userId');
		}

		var headers = credentials ? {authorization : "Basic "
			+ btoa(credentials.userId + ":" + credentials.userPw)
		} : {};

		$http.get('/member/userAuth', {headers : headers}).success(function(data) {
			if (data.userId) {
				$rootScope.userId = data.userId;
				sessionStorage.setItem('id', data.userId);
				sessionStorage.setItem('role', data.role);
				sessionStorage.setItem('storeCd', data.storeCd);
				$rootScope.role = data.role;
		        $rootScope.authenticated = true;
				$rootScope.topMenu = data.auth;
				if(data.role == '010101'){
					$rootScope.storeCd = null;
				}
				$rootScope.storeCd = data.storeCd;

				if($scope.autoLogin == true){
					setCookie('autoLogin',true,7);
					setCookie('userId',credentials.userId,7);
					setCookie('userPw',credentials.userPw,7);
				}else{
					deleteCookie('autoLogin');
					deleteCookie('userId');
					deleteCookie('userPw');
				}
			} else {
		        $rootScope.authenticated = false;
		        $rootScope.authErrorMsg = 3002;
		    }
				callback && callback();
		    }).error(function(data) {
		    	$rootScope.authErrorMsg = data.trim();
		    	$rootScope.authenticated = false;
		    	callback && callback();
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

	if(getCookie('autoLogin')){
		$scope.autoLogin = true;
		$scope.credentials.userId = getCookie('userId');
		$scope.credentials.userPw = getCookie('userPw');
		$scope.login();
	}

	$scope.clickUserReg = function(){
		$location.url("/member/userReg");
	};
}]);

//회원 리스트
app.controller('userController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location); //현재페이지 정보

		httpGetList($http, $scope,'/system/userList' ); //사용자 리스트 조회

		var checkList = []; //체크박스 리스트
		$scope.form = {'regUserId' : sessionStorage.getItem('id'),'pdaUseYn' : 'Y', 'userStat':'Y'}; //초기 form 상태
		$scope.es = {'newForm':true,'pwForm':false,'modForm':false};


		//입력양식(필수입력, readOnly) 변경
		$scope.formChange =function(command, data){

			if(command == 'reset'){
				$scope.form = {'regUserId' : sessionStorage.getItem('id'),'pdaUseYn' : 'Y', 'userStat':'Y' };
				$scope.es ={};
				$scope.es.newForm = true;

			}else if(command == 'pw'){
				if($scope.formChangeStat != 'mod'){
					alert('변경할 사용자를 선택해주세요')
				}else{
					$scope.es ={};
					$scope.es.pwForm = true;
				}
			}else if(command == 'mod'){
				$scope.es ={};
				$scope.es.modForm = true;
				const formData = {}
				for(const key in data){
					if(key == '$$hashKey'){
						continue;
					}
					formData[key] = data[key];
				}
				$scope.form = formData;
			}
			$scope.formChangeStat = command;
		}

		//유저 등록, 수정, 비밀번호 변경
		$scope.formSave = function(){
			if($scope.es.newForm == true){  //신규 계정 추가
				if(!($scope.form.userPwd === $scope.pwCheck)){
					modalAlert($uibModal, "사용자 추가","비밀번호를 확인해주세요");
					return;
				}
				$http({
					method : 'POST',
					url : "/system/userSave",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "사용자 추가", "신규 사용자가 등록되었습니다");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}else if($scope.es.pwForm == true){
				if(!($scope.form.userPwd === $scope.pwCheck)){
					modalAlert($uibModal, "비밀번호 변경","비밀번호를 확인해주세요.");
					return;
				}
				$http({
					method : 'POST',
					url : "/system/userPwUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "비밀번호 변경", "비밀번호가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}else if($scope.es.modForm == true){
				$http({
					method : 'POST',
					url : "/system/userUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "사용자 수정", "사용자 정보가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}
		}


		//검색
		$scope.searchBtn = function(command){
			if(command == 'group'){
				const param = generateParam($scope.searchGroup);
				httpGetList($http, $scope,'/system/userList', param );
				$scope.search.storeCd = $scope.searchGroup.storeCd;
				$scope.search.grade = $scope.searchGroup.grade;
				$scope.search.userStat = $scope.searchGroup.userStat;
			}else if(command == 'word'){
				const param = generateParam($scope.searchWord);
				httpGetList($http, $scope,'/system/userList', param);
				$scope.search.word = $scope.searchWord.word;
			}
		}

		//테이블 버튼 사용(탈퇴)
		$scope.tableBtn = function(command){
			if(command == 'Withdrawal'){
				if(checkList.length < 1){
					modalAlert($uibModal, "사용자 수정", "탈퇴할 회원을 선택해주세요.");
					return;
				}
				$http({
					method : 'POST',
					url : "/system/userWd",
					data  :  {'prList' :checkList},
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "사용자 수정", "사용자 정보가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}
		}

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/system/userList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/system/userList', param );
		}



		//체크박스 전체 체크
		$scope.checkAll = function(status, prKey){
			const tempList = [];
			for(const key in $scope.list){
				$scope.list[key].isSelected = status;
				tempList.push($scope.list[key][prKey]);
			}
			if(status){
				checkList = tempList;
			}else{
				checkList = [];
			}
		}
		//체크박스 리스트 추가, 삭제
		$scope.checkBox = function(status, select){
			const index = checkList.indexOf(select);
			if( index == -1 && status ){
				checkList.push(select);
			}else{
				checkList.splice(index, index+1);
			}
		}


}]);
