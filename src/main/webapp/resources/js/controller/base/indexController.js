app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};
});

app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {


	//현재페이지 정보
	pageInfo($rootScope, $location);

	$http.get('/member/getCode').success(function(data) {
		$rootScope.commCode = data.commCode;
		code($rootScope);
	});



	if(sessionStorage.getItem('id')){
		$http.get('/member/reUserAuth').success(function(data) {
			if (data.userId) {
				// sessionStorage.setItem('id', data.userId);
				$rootScope.topMenu = data.auth;
				$rootScope.role = data.role;
			} else {
				logout($http, $rootScope, $location);
				return;
			}
			// $rootScope.commCode = data.commCode;
			// code($rootScope);
		});
	}

	// //공통코드
	// $rootScope.grade = []; // 권한목록
	// $rootScope.store = []; // 매장목록
	// $rootScope.device = []; // 매장목록
	// function code() {
	// 	for (value of $rootScope.commCode) {
	// 		var bcd = value.commCd.substr(0, 2);
	// 		var mcd = value.commCd.substr(2, 2);
	// 		var scd = value.commCd.substr(4, 2);
	// 		if (bcd == '01' && mcd == '01' && scd != '00') { // 사용자 권한
	// 			$rootScope.grade.push(value);
	// 		} else if (bcd == '01' && mcd == '02' && scd != '00') { // 매장
	// 			$rootScope.store.push(value);
	// 		} else if (bcd == '02' && mcd == '01' && scd != '00') { // 장비구분
	// 			$rootScope.device.push(value);
	// 		}
	// 	}
	// }

	$scope.logout = function() {
		logout($http, $rootScope, $location);
		// $http({
		// 	method: 'POST',
		// 	url: '/member/logout',
		// 	headers: {'Content-Type': 'application/json; charset=utf-8'}
		// }).success(function(data, status, headers, config) {
		// 	if(status == 200) {
		// 		$rootScope.authenticated = false;
		// 		$location.url("/");
		// 		sessionStorage.clear();
		// 	} else {
		// 		modalCall("에러발생");
		// 	}
		// }).error(function(data, status, headers, config) {
		//     $rootScope.authenticated = false;
		//     $location.url("/");
		// });

	};



	$scope.client;

	$scope.disconnection = function(){
		if ($scope.client != null) {
			$scope.client.disconnect();
			$scope.resData = {};
	    }
	};

	$scope.$watch(function(){
		return $rootScope.authenticated;
		}, function() {
		if($rootScope.authenticated != undefined){
			if($rootScope.authenticated){
				if($location.url() != "/main/home"){
				}
			} else {
				$scope.disconnection();
			}
		}
	}, true);

	$scope.goMain = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/main/home");
    };

	$scope.goAssetManagementList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetList");
	};

	$scope.goMenu = function(data){
		$location.url(data.PGM_URL);
	}

	$scope.content = {'width':'100%'};
	//사이드 메뉴 토글
	$scope.menuToggle = function(){
		$scope.menu = $scope.menu ? false : true;
	}

	$scope.dock = [true, true, true, true, true];
	$scope.sideMenuDock = function(idx){
		$scope.dock[idx] = $scope.dock[idx] ? false : true;
	}


}]);


app.controller('modalController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$uibModalInstance',
	function ($scope, $http, $location, $routeParams, $rootScope, $uibModalInstance) {
		$scope.title = $ctrl.title;
		$scope.body = $ctrl.body;
		$scope.ok = function() {
			$uibModalInstance.close();
		};
		$scope.cancel = function() {
			$uibModalInstance.dismiss();
		};
}]);



app.controller('uploadController', ['$scope', '$http', '$location','$rootScope', '$window','$uibModalInstance','$uibModal',
	function ($scope, $http, $location,$rootScope, $window, $uibModalInstance, $uibModal) {

		$scope.input ={};
		$http({
			method : 'GET',
			url : "/appInfo",
			data : {},
		}).success(function(data){
			$scope.view = data;
		}).error(function(){
		});

		$scope.fileUpload = function(path){
			const fileValue = path.split("\\");
			const fileName = fileValue[fileValue.length-1]; // 파일명
			$scope.file_path = fileName;
			$scope.$apply();
		}

		//파일 업로드
		$scope.upload =function(){
			const inputData = {};
			const appGub = $scope.input.appGub;
			const version = $scope.input.version;
			if(appGub && version){
				inputData.appGub = appGub;
				inputData.version = version;
				if(isNaN(version)){
					modalAlert($uibModal, "어플리케이션 등록", "숫자를 입력해주세요");
					return;
				}
				if($scope.view.version >= version){
					modalAlert($uibModal, "어플리케이션 등록", "기존 version 보다 큰 값을 넣어주세요");
					return;
				}
			}else{
				modalAlert($uibModal, "어플리케이션 등록", "version 또는 App구분을 선택하여 주세요");
				return;
			}

			const form = $('#appForm')[0];
			const formData = new FormData(form);

			if(!$scope.file_path){
				modalAlert($uibModal, "어플리케이션 등록", "업로드 파일을 선택해 주세요");
			}
			$http({
				method : 'POST',
				enctype: 'multipart/form-data',
				url : "/upload",
				data : formData,
				headers: {"Content-Type": undefined, },
			}).success(function(data){
				inputData.path = data.path;
				inputData.fileName = data.fileName;
				uploadReg(inputData);
			}).error(function(){
				modalAlert($uibModal, "어플리케이션 등록", "업로드 실패");
			});
		}

		function uploadReg(data){
			console.log(data);

			$http({
				method : 'POST',
				url : "/uploadReg",
				data : data,
			}).success(function(data){
				modalAlert($uibModal, "어플리케이션 등록", "업로드 완료 되었습니다");
				$uibModalInstance.close();
			}).error(function(data){
				modalAlert($uibModal, "어플리케이션 등록", "업로드 데이터 등록 실패");
			});
		}

		$scope.appDown = function(url){
			window.location.href = url;
		}

		$scope.close = function() {
			$uibModalInstance.close();
		};


	}
]);


app.controller('appController', ['$scope', '$http', '$location','$rootScope', '$window',
	function ($scope, $http, $location,$rootScope, $window) {

		$http({
			method : 'GET',
			url : "/appInfo",
			data : {},
		}).success(function(data){
			$scope.view = data;
			$scope.view.appGub = codeToNm($scope.view.appGub, $rootScope.commCode);
		}).error(function(){
		});


		$scope.appDown = function(url){
			window.location.href = url;
		}

	}
]);

