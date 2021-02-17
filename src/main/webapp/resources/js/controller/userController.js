app.controller('loginController', ['$scope', '$http', '$location', '$routeParams', '$rootScope',
                                   function ($scope, $http, $location, $routeParams, $rootScope) {

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {authorization : "Basic "
			+ btoa(credentials.userId + ":" + credentials.userPw)
		} : {};

		$http.get('/member/userAuth', {headers : headers}).success(function(data) {
			console.log("::::::",data)
			if (data.userId) {
				sessionStorage.setItem('id', data.userId);
		        $rootScope.authenticated = true;

		        //로그인했을때 메뉴목록 가져와서 저장
				$rootScope.topMenu = data.menu;
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
app.controller('userListController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {
	$scope.search ={};
	var param = generateParam($scope.search);
	httpGetList("/member", param, $scope, $http, true);

	$scope.userInfoMod = function (userId){
		$ctrl = {}
		for(key in $scope.list){
			if($scope.list[key].userId == userId){
				$ctrl['list'] = $scope.list[key];
				break;
			}
		}
		$ctrl['commonCode'] = $scope.commonCode;
		var modalInstance = $uibModal.open({
			templateUrl: 'member/userAuthMod',
			controller: 'userAuthModController',
			controllerAs: '$ctrl'
		});
	}

	$scope.goPage = function(page){
		if($scope.current == page){
	    	return;
	    }
		if($scope.end < page){
			return;
		}
		if(page == 0){
			return;
		}
		$scope.search.page = page - 1;
		param = generateParam($scope.search);
		httpGetList("/member", param, $scope, $http);
	};

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
