app.controller('stkTagController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location);
		$scope.search = {};
		$scope.form = {};

		// searchMove = 1: 페이지 이동후 검색, 2: 단어 검색, 그 외: 일반 페이지 이동 조회
		if($rootScope.searchMove == 1){
			$scope.search = {};
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
			httpGetList($http, $scope,'/stock/stkTagList', param );
		}else if($rootScope.searchMove == 2) {
			$scope.search['word'] = $rootScope.quickSearchWord.word;
			$scope.search['STORE_CD'] = $rootScope.quickSearch.storeCd;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param )
		}else{
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param );
		}
		$rootScope.searchMove = false;

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/stock/stkTagList', param );
		}


		$scope.es = {'newForm':true,'modForm':false};
		$scope.formChange =function(command, data){

			if(command == 'reset'){
				$scope.form = {};
				$scope.inView = {};
				$scope.es ={};
				$scope.es.newForm = true;

			}else if(command == 'mod'){
				$scope.es ={};
				$scope.es.modForm = true;
				const formData = {}
				for(const key in data){
					if(key == 'REG_DATE'){
						formData[key] = formatDate4(new Date(data[key]));
						continue;
					}else if(key == 'TF_PRD_TAGID'){
						formData['tfPrdTagid'] = data[key];
						continue;
					}else if(key == 'TF_PRD_BARCODE'){
						formData['barcode'] = data[key];
						continue;
					} else if(key == 'STOCK_STORE_CD'){
						formData['outStoreCd'] = data[key];
						continue;
					}
					formData[key] = data[key];
				}
				$scope.form = formData;
				$scope.inView = formData;
			}
		}

		$scope.formSave = function() {
			modalCheck($uibModal, "출고/판매등록", "출고/판매 등록하시겠습니까?", function () {
				$http({
					method: 'POST',
					url: "/output/outputAdd",
					data: $scope.form,
					headers: {'Content-Type': 'application/json; charset=utf-8'}
				}).success(function (data) {
					if (data.resultCode == 'S') {
						modalAlert($uibModal, "출고/판매등록", "출고/판매정보가 등록 되었습니다");
					}
					$rootScope.reload();
				}).error(function (data) {
					alert('정보 업데이트 실패');
				});
			});
		}

		$scope.excelDown = function(){
			var fileName = '보유재고내역_'+formatDate3(new Date())+'.xlsx';
			$http({
				method : 'POST',
				url : "/stockExcelDown",
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
