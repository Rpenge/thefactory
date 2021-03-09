app.controller('loginController', ['$scope', '$http', '$location', '$routeParams', '$rootScope','$cookieStore',
	function ($scope, $http, $location, $routeParams, $rootScope, $cookieStore) {

	$scope.credentials ={};
	if($cookieStore.get("idSave")){
		$scope.idSaveCheck = true;
		$scope.credentials.userId = $cookieStore.get("uesrId");
	}

	var authenticate = function(credentials, callback) {

		if($scope.idSaveCheck) {
			$cookieStore.put("idSave", true);
			$cookieStore.put("uesrId",credentials.userId);
		}else{
			$cookieStore.remove("idSave");
			$cookieStore.remove("uesrId");
		}


		var headers = credentials ? {authorization : "Basic "
			+ btoa(credentials.userId + ":" + credentials.userPw)
		} : {};

		$http.get('/member/userAuth', {headers : headers}).success(function(data) {
			if (data.userId) {
				sessionStorage.setItem('id', data.userId);
		        $rootScope.authenticated = true;

		        //로그인했을때 메뉴목록 가져와서 저장
				$rootScope.topMenu = data.auth;
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


	//로그인
	$scope.login = function() {
		authenticate($scope.credentials, function() {
	        if ($rootScope.authenticated) {
	        	$location.url("/main/home");
	        	$scope.error = '';
	        } else {
	        	$location.url("/");
	        	$scope.error = $rootScope.authErrorMsg;
	        }
	    });
	};

	$scope.clickUserReg = function(){
		$location.url("/member/userReg");
	};

}]);

//회원 리스트
app.controller('userController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location); //현재페이지 정보

		httpGetList($http, $scope,'/system/userList' ); //사용자 리스트 조회

		$scope.form = {'regUserId' : sessionStorage.getItem('id'),'pdaUseYn' : 'Y', 'userStat':'Y'}; //초기 form 상태



		//입력양식(필수입력, readOnly) 변경
		$scope.es = {'newForm':true,'pwForm':false,'modForm':false};
		$scope.formChange =function(command, formData){

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
				$scope.form = formData;
			}
			$scope.formChangeStat = command;
		}

		$scope.formSave = function(){
			// console.log($scope.form);
			if($scope.es.newForm == true){
				if(!($scope.form.userPwd === $scope.pwCheck)){
					modalAlert($uibModal, "사용자 추가","비밀번호를 확인해주세요");
				}
				// $http({
				// 	method : 'POST',
				// 	url : "/system/userSave",
				// 	data  : $scope.form,
				// 	headers: {'Content-Type':'application/json; charset=utf-8'}
				// }).success(function(data){
				// 	console.log(11);
				// }).error(function(data){
				// 	alert('정보 업데이트 실패');
				// });
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
				httpGetList($http, $scope,'/system/userList', param );
				$scope.search.word = $scope.searchWord.word;

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

}]);




//modal 사용자 권한 수정
app.controller('userAuthModController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window','$uibModalInstance',
    function ($scope, $http, $location, $routeParams, $rootScope, $window, $uibModalInstance) {
	$scope.commonCode = $ctrl.commonCode;

	$scope.regDateOpen = function() {
	    $scope.regDate.open = true;
	};
	$scope.commonCode = $ctrl.commonCode;
	$scope.regData = $ctrl.list;

	$scope.regData.role = $scope.regData.empAuthorization;

	//권한 수정
	$scope.submit = function() {
		if($scope.regData.role == null || $scope.regData.role == $scope.regData.empAuthorization){
			alert('권한을 변경해주세요.');
			retrun;
		}else{
			$scope.regData.empAuthorization = $scope.regData.role;
		}

		$http({
			method : 'PUT',
			url : "/member/roleUpdate",
			data  : $scope.regData,
			headers: {'Content-Type':'application/json; charset=utf-8'}
		}).success(function(data){
			alert("권한이 수정되었습니다");
			$uibModalInstance.close();
			$rootScope.reload();
		})
		.error(function(data){
			alert('실패');
		});
	};
	$scope.close = function() {
		$uibModalInstance.close();
	};
}]);

//유저정보수정
app.controller('userInfoModController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window',
    function ($scope, $http, $location, $routeParams, $rootScope, $window) {

	$http({
		method : 'GET',
		url : "/member/userInfo",
		headers: {'Content-Type':'application/json; charset=utf-8'}
	}).success(function(data){
		$scope.regData = data;
	})
	.error(function(data){
		alert('실패');
	});

	//사용자 정보 수정
	$scope.submit = function() {
		if($scope.regData.password && $scope.regData.password != $scope.regData.passwordC){
			alert("비밀번호를 재확인 해주세요.");
			return;
		}
		$http({
			method : 'PUT',
			url : "/member/roleUpdate",
			data  : $scope.regData,
			headers: {'Content-Type':'application/json; charset=utf-8'}
		}).success(function(data){
			alert("수정되었습니다");
			$location.url("/");
		})
		.error(function(data){
			alert('실패');
		});
	};
}]);


//회원가입
app.controller('userRegController', ['$scope', '$http', '$location', '$routeParams', '$rootScope',
    function ($scope, $http, $location, $routeParams, $rootScope) {
	$scope.regData;
	$scope.submit = function(){
		$http({
			method : 'POST',
			url : "/member",
			data : $scope.regData,
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).success(function(data){
			alert('가입신청 완료');
			$location.url("/");
		})
		.error(function(data){
			alert('실패');
		});
	}
	$scope.goLogin = function(){
		$location.url("/");
	}
}]);
