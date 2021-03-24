app.controller('outputController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);
		$scope.form ={};

		if($rootScope.searchMove == 1){									//페이지 이동후 검색
			$scope.search = {};
			if($rootScope.quickSearch.storeCd != null){
				$scope.search['OUT_STORE_CD'] = $rootScope.quickSearch.storeCd;
			}

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
				$scope.search['ST_OUT_TYPE'] = $rootScope.quickSearch.detatilGub;
			}
			$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;

			const startDate = formatDate3($rootScope.quickSearch.startDate);
			const endDate = formatDate3($rootScope.quickSearch.endDate);

			$scope.search['startDate'] = startDate;
			$scope.search['endDate'] = endDate;

			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/output/outList', param );
		}else if($rootScope.searchMove == 2) {	//단어 검색
			$scope.search = $rootScope.quickSearchWord;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/output/outList', param )
		}else{															//일반 페이지 이동
			httpGetList($http, $scope,'/output/outList' );
		}
		$rootScope.searchMove = false;


		// //폼 형식
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

			}
		}


		//태그ID로 출고 데이터 조회
		$scope.tagSearch = false;
		$scope.inputAdd = function(){
			if($scope.form.tfPrdTagid == null){
				modalAlert($uibModal, "출고등록","태그ID를 확인해 주세요.");
				return;
			}
			$http.get('/stock/stkSearch?tagId='+ $scope.form.tfPrdTagid).success(function(data) {
				$scope.form = {};
				$scope.inView = {};
				$scope.es ={};
				$scope.es.newForm = true;
				$scope.form.tfPrdTagid = data.tfPrdTagid;
				$scope.form.barcode = data.btPrdBarcode;
				$scope.form.outStoreCd = data.outStoreCd;
				$scope.inView = data;
				$scope.tagSearch = true;
				if(Object.keys(data).length == 0){
					modalAlert($uibModal, "출고등록","태그ID를 확인해 주세요. 재고가 존재하는 상품만 조회가능합니다.");
				}
			});
		}


		//웹에서 입고는 반품과 점간이동만, 또는 수정
		$scope.formSave = function(){
			if($scope.es.newForm == true){
				if(!$scope.tagSearch){
					modalAlert($uibModal, "출고등록","태그ID 조회 후 등록 가능합니다");
					return;
				}

				if($scope.form.stOutType == "060202" && $scope.form.inStoreCd == null){
					modalAlert($uibModal, "출고등록","점간이동은 입고예정매장을 선택해주세요");
					return;
				}
				modalCheck($uibModal, "출고등록", "출고 등록하시겠습니까?", function(){
					$http({
						method : 'POST',
						url : "/output/outputAdd",
						data  : $scope.form,
						headers: {'Content-Type':'application/json; charset=utf-8'}
					}).success(function(data){
						if(data.resultCode == 'S') {
							modalAlert($uibModal, "출고등록", "출고정보가 추가되었습니다");
						}
						$rootScope.reload();
					}).error(function(data){
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
			httpGetList($http, $scope,'/output/outList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/output/inputList', param );
		}

		// //정렬
		// $scope.sort = function(sortName){
		// 	$scope.search.direct = $scope.search.direct=="asc" ? "desc" : "asc";
		// 	$scope.search.sort = sortName;
		// 	param = generateParam($scope.search);
		// 	httpGetList($http, $scope,'/inout/inputList', param );
		//
		// 	var sortIcon = document.getElementById('sortIcon');
		// 	if(sortIcon != null){
		// 		document.getElementById('sortIcon').remove('');
		// 	}
		//
		// 	var sortHead = document.getElementById(sortName);
		// 	if($scope.search.direct=="desc"){
		// 		sortHead.innerHTML+=" <i id='sortIcon' class='xi-caret-down'/>";
		// 	}else{
		// 		sortHead.innerHTML+=" <i id='sortIcon' class='xi-caret-up'/>";
		// 	}
		// };


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

		//테이블 버튼 사용(삭제)
		$scope.tableBtn = function(command){
			console.log(command);
			if(command == 'Withdrawal'){
				if(checkList.length < 1){
					modalAlert($uibModal, "출고삭제", "데이터를 선택해주세요.");
					return;
				}
				$http({
					method : 'POST',
					url : "/output/outputDelete",
					data  :  {'list' :checkList},
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "출고삭제", "데이터가 삭제되었습니다");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}
		}

}]);
