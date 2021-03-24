//코드 리스트
app.controller('codeController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {
		$scope.data = {
			useYn : ""
		}
		$scope.useYnList = [
			{id:"", name: "사용여부"},
			{id:"Y", name: "사용"},
			{id:"N", name: "미사용"}
		];

		pageInfo($rootScope, $location); //현재페이지 정보

		httpGetList($http, $scope,'/base/findList' ); //코드리스트 조회

		var checkList = []; //체크박스 리스트
		$scope.form = {}; //초기 form 상태
		$scope.es = {};


		//입력양식(필수입력, readOnly) 변경
		$scope.formChange = function(command, data){

			if(command === 'reset'){
				$scope.form = {'useYn':'Y'};
				$scope.es = {};
				$scope.es.newForm = true;
			}else if(command === 'mod'){
				$scope.es = {};
				$scope.es.modForm = true;
				const formData = {}
				for(const key in data){
					if(key == '$$hashKey'){
						continue;
					}
					formData[key] = data[key];
				}
				$scope.form = formData;
			}
			$scope.formChangeStat = command;
		}

		//유저 등록, 수정, 비밀번호 변경
		$scope.formSave = function(){
			// console.log($scope.form);
			if($scope.es.newForm == true){  //신규 코드 추가
				$http({
					method : 'POST',
					url : "/base/commSave",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode === 'S') {
						modalAlert($uibModal, "코드 추가", "신규 코드가 등록되었습니다");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}else if($scope.es.modForm == true){
				$http({
					method : 'POST',
					url : "/base/commUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "코드 수정", "코드 정보가 변경 되었습니다.");
					}
					$rootScope.reload();
				}).error(function(data){
					alert('정보 업데이트 실패');
				});
			}
		}

		//검색
		$scope.searchBtn = function(command){
			if(command == 'group'){
				const param = generateParam($scope.searchGroup);
				httpGetList($http, $scope,'/base/findList', param );
				$scope.search.commCd = $scope.searchGroup.commCd;
				$scope.search.commLevel = $scope.searchGroup.commLevel;
				$scope.search.useYn = $scope.searchGroup.useYn;
			}else if(command == 'word'){
				const param = generateParam($scope.searchWord);
				httpGetList($http, $scope,'/base/findList', param);
				$scope.search.word = $scope.searchWord.word;
			}
		}

		//테이블 버튼 사용(삭제)
		$scope.tableBtn = function(command){
			if(command == 'Withdrawal'){
				if(checkList.length < 1){
					modalAlert($uibModal, "코드 삭제", "삭제할 코드를 선택해주세요.");
					return;
				}
				$http({
					method : 'POST',
					url : "/base/commDelete",
					data  :  {'prList' :checkList},
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "코드 삭제", "코드 정보가 삭제되었습니다.");
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
			httpGetList($http, $scope,'/base/findList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/base/findList', param );
		}



		//체크박스 전체 체크
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


	}]);