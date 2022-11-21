//run, indexController, 
app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
	  //리로드로 기본정보를 가져온다 .(route를 리로딩 해오는것)
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};
	
	$rootScope.imgPath = "/resources/systemk";
	//프로젝트 이미지 변경   true:시스템케이  false:더팩토리
//	$rootScope.systemk = true;
//	if($rootScope.systemk){
//		$rootScope.imgPath = "/resources/systemk";
//	}else{
//		$rootScope.imgPath = "/resources";
//	}

	$rootScope.hiddenFunction = false;
});

app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

  //퀵서치 초기화 부분
	$rootScope.quickSearch = {}; //검색조건
	$rootScope.quickSearchWord = {}; // 검색어
	$rootScope.quickCmd = {};
 
  //이부분이 view 초기화 하는 부분
	$scope.view = {};

//	$rootScope.quickCmd.cmd = 'IO1';	//quick검색 초기값
//	$rootScope.currentMenu ={};

	//현재의 시간을 설정
//	$rootScope.quickSearch.startDate = new Date( new Date().setMonth( new Date().getMonth() - 1));
//	$rootScope.quickSearch.endDate = new Date();
//	$scope.startDateOptions = {'showWeeks':false, 'maxDate':$rootScope.quickSearch.endDate}; // 달력 옵션
//	$scope.endDateOptions = {'showWeeks':false,'maxDate':new Date()}; // 달력 옵션
//
//	$scope.today = new Date();

	//관리자의 영역으로 공통 브랜드 조회
//	//공통코드 , 브랜드코드 조회
//	$http.get('/member/getCode').success(function(data) {
//		$rootScope.commCode = data.commCode;
//		$rootScope.brandList = data.brandList;
//		$rootScope.brandList.sort(function(a, b) {
//			var nameA = a.brandNm.toUpperCase(); // ignore upper and lowercase
//			var nameB = b.brandNm.toUpperCase(); // ignore upper and lowercase
//			if (nameA < nameB) {
//				return -1;
//			}
//			if (nameA > nameB) {
//				return 1;
//			}
//			return 0;
//		});
//		code($rootScope);
//	});

	//새로고침 : 세션확인 후 메뉴 다시 조회
//	if(sessionStorage.getItem('id')){
//		$rootScope.userId = sessionStorage.getItem('id');
//		$http.get('/member/reUserAuth').success(function(data) {
//		  //데이터를 가지
//			if (data.userId) {
//				$rootScope.topMenu = data.auth;
//				$rootScope.role = data.role;
//				if(data.role == '010101' && $rootScope.quickSearch.storeCd == null){
//					$rootScope.quickSearch.storeCd = null;
//				}else if($rootScope.quickSearch.storeCd == null){
//					$rootScope.quickSearch.storeCd = sessionStorage.getItem('storeCd');
//				}
//				menuCheck($rootScope, $location);
//			} else {
//				logout($http, $rootScope, $location);
//				return;
//			}
//		});
//	}

	$scope.logout = function() {
		logout($http, $rootScope, $location);
	};


	$scope.disconnection = function(){
		if ($scope.client != null) {
			$scope.client.disconnect();
			$scope.resData = {};
	    }
	};

	//권한이 있으면 아무것도 없고, ...(왜이렇게 설계했지 ? ) 권한이 없으면 disconnected
	$scope.$watch(function(){
		return $rootScope.authenticated;
		}, function() {
		if($rootScope.authenticated != undefined){
			if($rootScope.authenticated){
				// if($location.url() != "/main/home"){}
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
    //드롭다운으로 나오는 메인 메뉴의 목록
    
	$scope.goMenu = function(data){
		$location.url(data.PGM_URL);
	}

	//quick search
//	$rootScope.goSearch = function(command, word, $event){

		//enter 이벤트
//		if($event){
//			const pressedKey = window.event ? $event.keyCode : $event.which;
//			if(pressedKey  != '13'){
//				return;
//			}
//		}

//		for(const value of $scope.topMenu){
//			if(value.PGM_CD == command){
//				if(!word){
//					$rootScope.searchMove = 1;
//				}else{
//					$rootScope.searchMove = 2;
//				}
//
//				if($location.url().split('?')[0] == value.PGM_URL) {
//					$scope.reload();
//				}else{
//					$location.url(value.PGM_URL);
//				}
//				break;
//			}
//		}
//	}

	//브랜드 선택
//	$scope.brandSelect = function(data){
//		console.log(data);
//		$rootScope.quickSearch.gender = "";
//		$rootScope.quickSearch.cls = "";
//		if(!data){
//			$rootScope.quickSearch.brand = null;
//			$scope.view.brand = null;
//			$scope.subBrandCls = [];
//			return;
//		}
//		$rootScope.quickSearch.brand = data.brandKindCd;
//		$scope.view.brand = data.brandNm;
//
//		$http.get('/member/brandSub?brandCd='+ data.brandKindCd.substr(0,2)).success(function(data) {
//			$scope.subBrand = data.brandSubList;
//		});
//	}

	//성별 선택
//	$scope.genderSelect = function(data){
//		$scope.subBrandCls = [];
//		for(const value of $scope.subBrand){
//			if(data.substr(0,4) == value.brandKindCd.substr(0,4) && value.codeLevel == 'S'){
//				$scope.subBrandCls.push(value);
//			}
//		}
//	}

	//quick search 검색구분 변경
//	$rootScope.quick1List = ['IO1','IO2','IO3'];
//	$rootScope.quick2List = ['IO4','IV1'];
//	$rootScope.quick3List = ['IO1','IO2','IO3','ST1','BS3','ST2','IV2'];
//	$rootScope.quick4List = [];
//	$rootScope.addQuick = function(command){
//		$rootScope.quickSearch.workGub = null;
//		if($rootScope.quick1List.includes(command)){
//			$rootScope.quick1 = true;
//		}else{
//			$rootScope.quick1 = false;
//		}
//
//		if($rootScope.quick2List.includes(command)){
//			$rootScope.quick2 = true;
//		}else{
//			$rootScope.quick2 = false;
//		}
//
//		if($rootScope.quick3List.includes(command)){
//			$rootScope.quick3 = true;
//		}else{
//			$rootScope.quick3 = false;
//		}
//
//		if($rootScope.quick4List.includes(command)){
//			$rootScope.quick4 = true;
//		}else{
//			$rootScope.quick4 = false;
//		}
//		workGub(command)
//	}
//	$rootScope.addQuick($rootScope.quickCommand);

	//quick search 검색구분 변경 : 구분값 변경 & disalbed 처리
//	function workGub(command){
//		const input = ['IO1'];
//		const output = ['IO2'];
//		const sell = ['IO3'];
//		if(input.includes(command)){
//			$rootScope.quickSearch.workGub = '060100';
//			$scope.gubDisabled = true;
//		}else if(output.includes(command)){
//			$rootScope.quickSearch.workGub = '060200';
//			$scope.gubDisabled = true;
//		}else if(sell.includes(command)){
//			$rootScope.quickSearch.workGub = '060300';
//			$scope.gubDisabled = true;
//		}else{
//			$scope.gubDisabled = false;
//		}
//	}

	//상위 작업구분 변경
//	$scope.detatilGub = function(data){
//		if($scope.quickSearch.workGub == null){
//			return false;
//		}
//		if($scope.quickSearch.workGub.substr(0,4) == data.substr(0,4)){
//			return true;
//		}
//		return false;
//	}

}]);


//app.controller('modalController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$uibModalInstance',
//	function ($scope, $http, $location, $routeParams, $rootScope, $uibModalInstance) {
//		$scope.title = $ctrl.title;
//		$scope.body = $ctrl.body;
//		$scope.ok = function() {
//			$uibModalInstance.close();
//		};
//		$scope.cancel = function() {
//			$uibModalInstance.dismiss();
//		};
//}]);
//
//
//
//app.controller('uploadController', ['$scope', '$http', '$location','$rootScope', '$window','$uibModalInstance','$uibModal',
//	function ($scope, $http, $location,$rootScope, $window, $uibModalInstance, $uibModal) {
//
//		$scope.input ={};
//		$http({
//			method : 'POST',
//			url : "/appInfo",
//			data : {"deviceGub":$ctrl.cmd},
//		}).success(function(data){
//			$scope.view = data;
//		}).error(function(){
//		});
//
//		$scope.fileUpload = function(path){
//			const fileValue = path.split("\\");
//			const fileName = fileValue[fileValue.length-1]; // 파일명
//			$scope.file_path = fileName;
//			$scope.$apply();
//		}
//
//		//파일 업로드
//		$scope.upload =function(){
//			const inputData = {};
//			const appGub = $scope.input.appGub;
//			const version = $scope.input.version;
//			if(appGub && version){
//				inputData.appGub = appGub;
//				inputData.version = version;
//				inputData.comment = $scope.input.comment;
//				// if(isNaN(version)){
//				// 	modalAlert($uibModal, "어플리케이션 등록", "숫자를 입력해주세요");
//				// 	return;
//				// }
//				// if($scope.view.version >= version){
//				// 	modalAlert($uibModal, "어플리케이션 등록", "기존 version 보다 큰 값을 넣어주세요");
//				// 	return;
//				// }
//			}else{
//				modalAlert($uibModal, "어플리케이션 등록", "version 또는 App구분을 선택하여 주세요");
//				return;
//			}
//
//			const form = $('#appForm')[0];
//			const formData = new FormData(form);
//
//			if(!$scope.file_path){
//				modalAlert($uibModal, "어플리케이션 등록", "업로드 파일을 선택해 주세요");
//			}
//			$http({
//				method : 'POST',
//				enctype: 'multipart/form-data',
//				url : "/upload",
//				data : formData,
//				headers: {"Content-Type": undefined, },
//			}).success(function(data){
//				inputData.path = data.path;
//				inputData.fileName = data.fileName;
//				uploadReg(inputData);
//			}).error(function(){
//				modalAlert($uibModal, "어플리케이션 등록", "업로드 실패");
//			});
//		}
//
//		function uploadReg(data){
//			$http({
//				method : 'POST',
//				url : "/uploadReg",
//				data : data,
//			}).success(function(data){
//				modalAlert($uibModal, "어플리케이션 등록", "업로드 완료 되었습니다");
//				$uibModalInstance.close();
//			}).error(function(data){
//				modalAlert($uibModal, "어플리케이션 등록", "업로드 데이터 등록 실패");
//			});
//		}
//
//		$scope.appDown = function(url){
//			window.location.href = url;
//		}
//
//		$scope.close = function() {
//			$uibModalInstance.close();
//		};
//
//
//	}
//]);
//
//
//app.controller('appController', ['$scope', '$http', '$location','$rootScope', '$window',
//	function ($scope, $http, $location,$rootScope, $window) {
//
//		document.documentElement.style.overflowX = 'hidden';
//
//		$http({
//			method : 'POST',
//			url : "/appInfo",
//			data : {'deviceGub':'020102'},
//		}).success(function(data){
//			$scope.view = data;
//			$scope.view.appGub = codeToNm($scope.view.appGub, $rootScope.commCode);
//		}).error(function(){
//		});
//
//
//		$scope.appDown = function(url){
//			window.location.href = url;
//		}
//
//	}
//]);
//
