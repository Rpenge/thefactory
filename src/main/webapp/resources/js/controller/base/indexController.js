app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};
});

app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

	$rootScope.quickSearch = {}; //검색조건
	$rootScope.quickSearchWord = {}; // 검색어
	$scope.view = {};
	$scope.dateOptions = {'showWeeks':false}; // 달력 옵션
	$rootScope.quickCommand = 'IO1';	//quick검색 초기값
	$rootScope.currentMenu ={};

	$rootScope.quickSearch.startDate = new Date();
	$rootScope.quickSearch.endDate = new Date();

	//공통코드 , 브랜드코드 조회
	$http.get('/member/getCode').success(function(data) {
		$rootScope.commCode = data.commCode;
		$rootScope.brandList = data.brandList;
		code($rootScope);
	});


	//새로고침 : 세션확인 후 메뉴 다시 조회
	if(sessionStorage.getItem('id')){
		$http.get('/member/reUserAuth').success(function(data) {
			if (data.userId) {
				$rootScope.topMenu = data.auth;
				$rootScope.role = data.role;
				if(data.role == '010101' && $rootScope.quickSearch.storeCd == null){
					$rootScope.quickSearch.storeCd = null;
				}else if($rootScope.quickSearch.storeCd == null){
					$rootScope.quickSearch.storeCd = sessionStorage.getItem('storeCd');
				}
			} else {
				logout($http, $rootScope, $location);
				return;
			}
		});

		$http.get('/home/homeSimple').success(function(data){
			$scope.todayData = data.todayData;
		});
	}


	$scope.logout = function() {
		logout($http, $rootScope, $location);
	};


	//$scope.client;

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


	//페이지 이동
	$scope.goMenu = function(data){
		$location.url(data.PGM_URL);
	}

	//quick search
	$rootScope.goSearch = function(command, word){
		for(const value of $scope.topMenu){
			if(value.PGM_CD == command){
				if(!word){
					$rootScope.searchMove = 1;
				}else{
					$rootScope.searchMove = 2;
				}

				if($location.url() == value.PGM_URL){
					$scope.reload();
				}else{
					$location.url(value.PGM_URL);
				}
				break;
			}
		}
	}


	//브랜드 선택
	$scope.brandSelect = function(data){
		if(!data){
			$scope.quickSearch.brand = null;
			$scope.view.brand = null;
			$scope.quickSearch.gender = "";
			$scope.quickSearch.cls = "";
			$scope.subBrandCls = [];
			return;
		}
		$scope.quickSearch.brand = data.brandKindCd;
		$scope.view.brand = data.brandNm;

		$http.get('/member/brandSub?brandCd='+ data.brandKindCd.substr(0,2)).success(function(data) {
			$scope.subBrand = data.brandSubList;
		});
	}

	//성별 선택
	$scope.genderSelect = function(data){
		$scope.subBrandCls = [];
		for(const value of $scope.subBrand){
			if(data.substr(0,4) == value.brandKindCd.substr(0,4) && value.codeLevel == 'S'){
				$scope.subBrandCls.push(value);
			}
		}
	}


	//quick search 검색구분 변경
	$rootScope.quick1List = ['IO1', 'IO2','IO3','IO4'];
	$rootScope.addQuick = function(command){
		$rootScope.quickSearch.workGub = null;
		if($rootScope.quick1List.includes(command)){
			$rootScope.quick1 = true;
		}else{
			$rootScope.quick1 = false;
		}
		workGub(command)
	}
	$rootScope.addQuick($rootScope.quickCommand);

	//quick search 검색구분 변경 : 구분값 변경 & disalbed 처리
	function workGub(command){
		const input = ['IO1'];
		const output = ['IO2'];
		const sell = ['IO3'];
		if(input.includes(command)){
			$rootScope.quickSearch.workGub = '060100';
			$scope.gubDisabled = true;
		}else if(output.includes(command)){
			$rootScope.quickSearch.workGub = '060200';
			$scope.gubDisabled = true;
		}else if(sell.includes(command)){
			$rootScope.quickSearch.workGub = '060300';
			$scope.gubDisabled = true;
		}else{
			$scope.gubDisabled = false;
		}
	}

	//상위 작업구분 변경
	$scope.detatilGub = function(data){
		if($scope.quickSearch.workGub == null){
			return false;
		}
		if($scope.quickSearch.workGub.substr(0,4) == data.substr(0,4)){
			return true;
		}
		return false;
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

