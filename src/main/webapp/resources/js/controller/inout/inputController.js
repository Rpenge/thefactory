
//회원 리스트
app.controller('inputController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);

		if($rootScope.searchMove == 1){									//페이지 이동후 검색
			$scope.search = {};
			if($rootScope.quickSearch.brand != null){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.brand.substr(0,2);
			}
			if($rootScope.quickSearch.gender != null){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.gender.substr(0,4);
			}
			if($rootScope.quickSearch.cls != null){
				$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.cls;
			}
			if($rootScope.quickSearch.detatilGub != null){
				$scope.search['ST_IN_TYPE'] = $rootScope.quickSearch.detatilGub;
			}
			$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;

			// const endDate = $rootScope.quickSearch.endDate;
			// const newEndDate = formatDate3(new Date(endDate.setDate(endDate.getDate() +1)));
			const startDate = formatDate3($rootScope.quickSearch.startDate);
			const endDate = formatDate3($rootScope.quickSearch.endDate);

			$scope.search['startDate'] = startDate;
			$scope.search['endDate'] = endDate;

			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inout/inputList', param );
		}else if($rootScope.searchMove == 2) {	//단어 검색
			$scope.search = $rootScope.quickSearchWord;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inout/inputList', param )
		}else{															//일반 페이지 이동
			httpGetList($http, $scope,'/inout/inputList' );
		}
		$rootScope.searchMove = false;


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
					if(key == '$$hashKey'){
						continue;
					}
					formData[key] = data[key];
				}
				$scope.form = formData;
				$scope.inView = formData;
				// $scope.inView.brand = formData.brand;
				// $scope.brandSelect(formData.brand, $scope.genderSelect, formData.gender, formData.cls);
				// $scope.inView.gender = formData.gender;
				$scope.inView.prdSize = codeToNm(formData.prdSize, $rootScope.prdSize);
			}
		}

		// //브랜드 선택
		// $scope.brandSelect = function(code, callback, gender, cls){
		// 	$scope.inView = {};
		// 	$scope.inView.brand = code;
		// 	$scope.inView.gender = null;
		// 	$scope.inView.cls = null;
		// 	$scope.subBrand = [];
		// 	$scope.subBrandCls = [];
		// 	if(code){
		// 		$http.get('/member/brandSub?brandCd='+ code.substr(0,2)).success(function(data) {
		// 			$scope.subBrand = data.brandSubList;
		// 			if(callback){
		// 				callback(gender, cls);
		// 			}
		// 		});
		// 	}
		// }
		//
		// //성별 선택
		// $scope.genderSelect = function(gender, cls){
		// 	$scope.inView.cls = null;
		// 	$scope.subBrandCls = [];
		// 	if(!gender){
		// 		return;
		// 	}
		//
		// 	for(const value of $scope.subBrand){
		// 		if(gender.substr(0,4) == value.brandKindCd.substr(0,4) && value.codeLevel == 'S'){
		// 			$scope.subBrandCls.push(value);
		// 		}
		// 	}
		// 	if(cls) {
		// 		$scope.inView.cls = cls;
		// 	}
		// }


		//태그ID로 출고 데이터 조회
		$scope.tagSearch = false;
		$scope.inputAdd = function(){
			$http.get('/inout/inputAdd?tagId='+ $scope.form.tfPrdTagid).success(function(data) {
				$scope.form = {};
				$scope.inView = {};
				$scope.es ={};
				$scope.es.newForm = true;
				$scope.form.tfPrdTagid = data.tfPrdTagid;
				$scope.form.barcode = data.btPrdBarcode;
				$scope.inView = data;
				$scope.tagSearch = true;
				console.log(data);
			});
		}

		//웹에서 입고는 반품과 점간이동만, 또는 수정
		$scope.formSave = function(){
			if($scope.es.newForm == true){
				if(!$scope.tagSearch){
					modalAlert($uibModal, "입고등록","태그ID 조회 후 등록 가능합니다");
					return;
				}
				if(!(($scope.form.stInType == '060102') || ($scope.form.stInType == '060104'))){
					modalAlert($uibModal, "입고등록","웹에서는 점간입고와 반품입고만 가능합니다");
					return;
				}
				modalCheck($uibModal, "입고등록", "입고 등록하시겠습니까?", function(){
					$http({
						method : 'POST',
						url : "/inout/inputAddResult",
						data  : $scope.form,
						headers: {'Content-Type':'application/json; charset=utf-8'}
					}).success(function(data){
						if(data.resultCode == 'S') {
							modalAlert($uibModal, "입고등록", "입고정보가 추가되었습니다");
						}
						$rootScope.reload();
					}).error(function(data){
						alert('정보 업데이트 실패');
					});

				});
				// $http({
				// 	method : 'POST',
				// 	url : "/inout/inputAddResult",
				// 	data  : $scope.form,
				// 	headers: {'Content-Type':'application/json; charset=utf-8'}
				// }).success(function(data){
				// 	if(data.resultCode == 'S') {
				// 		modalAlert($uibModal, "입고등록", "입고정보가 추가되었습니다");
				// 	}
				// 	$rootScope.reload();
				// }).error(function(data){
				// 	alert('정보 업데이트 실패');
				// });
			}else if($scope.es.modForm == true) {

				//수정 : 입고테이블 데이터 변경 , 실재고테이블 데이터 위치 변경, 재고테이블 재고 위치 변경
				modalCheck($uibModal, "입고수정", "입고 정보를 수정하시겠습니까?", function () {
					$http({
						method: 'POST',
						url: "/inout/inputAddResult",
						data: $scope.form,
						headers: {'Content-Type': 'application/json; charset=utf-8'}
					}).success(function (data) {
						if (data.resultCode == 'S') {
							modalAlert($uibModal, "사용자 수정", "사용자 정보가 변경 되었습니다.");
						}
						$rootScope.reload();
					}).error(function (data) {
						alert('정보 업데이트 실패');
					});

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
			httpGetList($http, $scope,'/inout/inputList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inout/inputList', param );
		}

}]);
