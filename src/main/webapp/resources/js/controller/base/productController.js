
//회원 리스트
app.controller('productController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location);
		// httpGetList($http, $scope,'/base/productList' );
		$scope.form = {};
		$scope.form.regId = sessionStorage.getItem('id');

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
			if($rootScope.quickSearch.prdSize){
				$scope.search['EC_SIZE_NM'] = $rootScope.quickSearch.prdSize;
			}
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/productList', param );
		}else if($rootScope.searchMove == 2) {	//단어 검색
			$scope.search = $rootScope.quickSearchWord;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/productList', param )
		}else{															//일반 페이지 이동
			httpGetList($http, $scope,'/base/productList' );
		}
		$rootScope.searchMove = false;

		//체크박스 전체 체크
		var checkList = []; //체크박스 리스트
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

		//폼 형식
		$scope.es = {'newForm':true,'modForm':false};
		$scope.formChange =function(command, data){
			if(command == 'reset'){
				$scope.form = {};
				$scope.inView = {};
				$scope.es ={};
				$scope.es.newForm = true;
				$scope.form.regId = sessionStorage.getItem('id');

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
				$scope.brandSelect(formData.brand, $scope.genderSelect, formData.gender, formData.cls);
			}
		}

		//유저 등록, 수정, 비밀번호 변경
		$scope.formSave = function(){

			if($scope.es.newForm == true){  //신규 상품 추가
				$scope.form.brandKindCd = $scope.inView.cls;
				for(value of $rootScope.brandList){
					if(value.brandKindCd == $scope.inView.brand){
						$scope.form.brandNm = value.brandNm;
						break;
					}
				}
				$http({
					method : 'POST',
					url : "/base/productSave",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "상품추가", "신규 상품이 등록되었습니다");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}else if($scope.es.modForm == true){ //상품 정보 수정
				$scope.form.modId = sessionStorage.getItem('id');
				$http({
					method : 'POST',
					url : "/base/productUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "상품정보수정", "상품정보가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}
		}



		// //브랜드 선택
		$scope.brandSelect = function(code, callback, gender, cls){
			// $scope.inView = {};
			$scope.inView.brand = code;
			$scope.inView.gender = null;
			$scope.inView.cls = null;
			$scope.subBrand = [];
			$scope.subBrandCls = [];
			if(code){
				$http.get('/member/brandSub?brandCd='+ code.substr(0,2)).success(function(data) {
					$scope.subBrand = data.brandSubList;
					if(callback){
						callback(gender, cls);
					}
				});
			}
		}
		//성별 선택
		$scope.genderSelect = function(gender, cls){
			$scope.inView.gender = gender;
			$scope.inView.cls = null;
			$scope.subBrandCls = [];
			if(!gender){
				return;
			}
			for(const value of $scope.subBrand){
				if(gender.substr(0,4) == value.brandKindCd.substr(0,4) && value.codeLevel == 'S'){
					$scope.subBrandCls.push(value);
				}
			}
			if(cls) {
				$scope.inView.cls = cls;
			}
		}

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/productList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/productList', param );
		}

		//테이블 버튼 사용(삭제)
		$scope.tableBtn = function(command){
			if(command == 'delete'){
				if(checkList.length < 1){
					modalAlert($uibModal, "상품정보삭제", "데이터를 선택해주세요.");
					return;
				}
				modalCheck($uibModal, "상품정보삭제", "데이터를 삭제하시겠습니까", function() {
					$http({
						method: 'POST',
						url: "/base/productDelete",
						data: {'list': checkList},
						headers: {'Content-Type': 'application/json; charset=utf-8'}
					}).success(function (data) {
						if (data.resultCode == 'S') {
							modalAlert($uibModal, "상품정보삭제", "데이터가 삭제되었습니다");
						}
						$rootScope.reload();
					}).error(function (data) {
						alert('정보 업데이트 실패');
					});
				});
			}
		}


		//////////////////////엑셀 업로드

		$scope.formDown = function(){
			window.location.href = 'resources/xlsx/product_form.xlsx'
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
				modalAlert($uibModal, "상품정보 Excel 업로드", "파일을 선택해주세요");
				return;
			}
			modalCheck($uibModal, "상품정보 Excel 업로드", "상품정보를 업로드 하시겠습니까?", function(){
				const form = $('#excelForm')[0];
				const formData = new FormData(form);
				$http({
					method : 'POST',
					enctype: 'multipart/form-data',
					url : "/productUpload",
					data : formData,
					headers: {"Content-Type": undefined, },
				}).success(function(data){
					if(data.resultCode == 'S'){
						modalAlert($uibModal, "상품정보 Excel 업로드", "상품정보가 업데이트 되었습니다");
					}else{
						modalAlert($uibModal, "상품정보 Excel 업로드", "업데이트 오류");
					}
					$rootScope.reload();
				}).error(function(){
					modalAlert($uibModal, "상품정보 Excel 업로드", "업데이트 오류!");
				});

			});
		}

}]);
