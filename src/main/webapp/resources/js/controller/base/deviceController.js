app.controller('deviceController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {


		pageInfo($rootScope, $location);
		$scope.inView = {};
		$scope.inView.setDate = new Date();


		httpGetList($http, $scope,'/base/deviceList' );

		//검색
		$scope.searchBtn = function(command){
			if(command == 'group'){
				const param = generateParam($scope.searchGroup);
				httpGetList($http, $scope,'/base/deviceList', param );
				$scope.search.storeCd = $scope.searchGroup.storeCd;
				$scope.search.deviceGub = $scope.searchGroup.deviceGub;
				$scope.search.deviceStat = $scope.searchGroup.deviceStat;
			}
		}


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
			console.log(checkList);
		}

		//체크박스 리스트 추가, 삭제
		$scope.checkBox = function(status, select){
			const index = checkList.indexOf(select);
			if( index == -1 && status ){
				checkList.push(select);
			}else{
				checkList.splice(index, index+1);
			}
			console.log(checkList);
		}

		//폼 형식
		$scope.es = {'newForm':true,'modForm':false};
		$scope.formChange =function(command, data){
			if(command == 'reset'){
				$scope.form = {};
				$scope.inView = {};
				$scope.es ={};
				$scope.es.newForm = true;
				// $scope.form.regId = sessionStorage.getItem('id');

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
				// $scope.brandSelect(formData.brand, $scope.genderSelect, formData.gender, formData.cls);
			}
		}

		$scope.formSave = function(){
			$scope.form.setDate = formatDate4(new Date($scope.inView.setDate));
			if($scope.es.newForm == true){  //신규 장비 추가
				$http({
					method : 'POST',
					url : "/base/deviceSave",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "장비추가", "신규 상품이 등록되었습니다");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}else if($scope.es.modForm == true){ //장비 정보 수정
				$http({
					method : 'POST',
					url : "/base/deviceUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "장비정보수정", "장비정보가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
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
			httpGetList($http, $scope,'/base/deviceList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/deviceList', param );
		}

		//테이블 버튼 사용(삭제)
		$scope.tableBtn = function(command){
			if(command == 'delete'){
				if(checkList.length < 1){
					modalAlert($uibModal, "디바이스삭제", "데이터를 선택해주세요.");
					return;
				}
				modalCheck($uibModal, "디바이스삭제", "데이터를 삭제하시겠습니까", function() {
					$http({
						method: 'POST',
						url: "/base/deviceDelete",
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

}]);
