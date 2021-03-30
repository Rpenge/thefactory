
//회원 리스트
app.controller('stockController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);


		// httpGetList($http, $scope,'/stock/stockList' );

		if($rootScope.searchMove == 1){	//페이지 이동후 검색
			$scope.search = {};
			if($rootScope.quickSearch.brand){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.brand.substr(0,2);
			}
			if($rootScope.quickSearch.gender){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.gender.substr(0,4);
			}
			if($rootScope.quickSearch.cls){
				console.log($rootScope.quickSearch.cls);
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.cls;
			}
			$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		}else if($rootScope.searchMove == 2) { //단어 검색
			const param = generateParam($rootScope.quickSearchWord);
			console.log(param);
			httpGetList($http, $scope,'/stock/stockList', param )
		}else{
			httpGetList($http, $scope,'/stock/stockList' );
		}
		$rootScope.searchMove = false;

		//차이 발생 목록
		$scope.stkDif = function(command){
			if(command == 'dis') {
				const cmd = {"ex": "true"};
				const param = generateParam(cmd);
				httpGetList($http, $scope, '/stock/stockList', param);
			}else{
				const param = generateParam($scope.search);
				httpGetList($http, $scope,'/stock/stockList', param );
			}
		}

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		}

		//////////////////////엑셀 업로드

		$scope.fileUpload = function(path){
			const fileValue = path.split("\\");
			const fileName = fileValue[fileValue.length-1]; // 파일명
			$scope.file_path = fileName;
			$scope.$apply();
		}
		//파일 업로드
		$scope.upload = function(){
			modalCheck($uibModal, "재고정보 Excel 업로드", "재고정보를 업로드 하시겠습니까?", function(){
				const form = $('#excelForm')[0];
				const formData = new FormData(form);
				$http({
					method : 'POST',
					enctype: 'multipart/form-data',
					url : "/stockUpload",
					data : formData,
					headers: {"Content-Type": undefined, },
				}).success(function(data){
					if(data.resultCode == 'S'){
						modalAlert($uibModal, "재고정보 Excel 업로드", "재고정보가 업데이트 되었습니다");
					}else{
						modalAlert($uibModal, "재고정보 Excel 업로드", "업데이트 오류");
					}
					$rootScope.reload();
				}).error(function(){
					modalAlert($uibModal, "재고정보 Excel 업로드", "업데이트 오류!");
				});

			});
		}

		// function uploadReg(data){
		// 	$http({
		// 		method : 'POST',
		// 		url : "/uploadReg",
		// 		data : data,
		// 	}).success(function(data){
		// 		modalAlert($uibModal, "어플리케이션 등록", "업로드 완료 되었습니다");
		// 		$uibModalInstance.close();
		// 	}).error(function(data){
		// 		modalAlert($uibModal, "어플리케이션 등록", "업로드 데이터 등록 실패");
		// 	});
		// }

}]);
