app.controller('stockController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location);
		var expansion = false;
		$scope.search = {};

		// searchMove = 1: 페이지 이동후 검색, 2: 단어 검색, 그 외: 일반 페이지 이동 조회
		if($rootScope.searchMove == 1){
			if($rootScope.quickSearch.brand){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.brand.substr(0,2);
			}
			if($rootScope.quickSearch.gender){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.gender.substr(0,4);
			}
			if($rootScope.quickSearch.cls){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.cls;
			}
			$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param );
		}else if($rootScope.searchMove == 2) {
			// $scope.search = $rootScope.quickSearchWord;
			$scope.search['word'] = $rootScope.quickSearchWord.word;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stockList', param )
		}else{
			httpGetList($http, $scope,'/stock/stockList' );
		}
		$rootScope.searchMove = false;

		//차이 발생 목록
		$scope.stkDif = function(command){
			if(command == 'dis') {
				expansion = true;
				$scope.search['ex'] = command;
				$scope.search['page'] = 0;
				const param = generateParam($scope.search);
				httpGetList($http, $scope, '/stock/stockList', param);
			}else if(command == 'rfid'){
				expansion = true;
				$scope.search['ex'] = command;
				$scope.search['page'] = 0;
				const param = generateParam($scope.search);
				httpGetList($http, $scope, '/stock/stockList', param);
			}else{
				expansion = false;
				$scope.search['ex'] = null;
				$scope.search['page'] = 0;
				const param = generateParam($scope.search);
				httpGetList($http, $scope,'/stock/stockList', param );
			}
		}

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			// if(expansion){
			// 	$scope.search['ex'] = true;
			// }else{
			// 	$scope.search['ex'] = null;
			// }
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

		$scope.formDown = function(){
			window.location.href = 'resources/xlsx/stock_form.xlsx'
		}

		$scope.fileUpload = function(path){
			const fileValue = path.split("\\");
			const fileName = fileValue[fileValue.length-1]; // 파일명
			$scope.file_path = fileName;
			$scope.$apply();
		}
		//파일 업로드
		$scope.upload = function(){

			if(!$scope.file_path){
				modalAlert($uibModal, "재고정보 Excel 업로드", "파일을 선택해주세요");
				return;
			}
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

		$scope.excelDown = function(){
			var fileName = '전체재고내역_'+formatDate3(new Date())+'.xlsx';
			console.log($scope.excelForm);
			$http({
				method : 'POST',
				url : "/stkBaseExcelDown",
				data  : $scope.excelForm,
				responseType : 'blob',
			}).success(function(data){
				var blob = data;
				var downloadLink = window.document.createElement('a');
				downloadLink.href = window.URL.createObjectURL(new Blob([blob]));
				downloadLink.download = fileName;
				document.body.appendChild(downloadLink);
				downloadLink.click();
				document.body.removeChild(downloadLink);
			}).error(function(data){
				alert('실패');
			});
		}


}]);
