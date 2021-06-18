
//회원 리스트
app.controller('invController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location);
		httpGetList($http, $scope,'/inven/invList' );

		if($rootScope.searchMove == 1){									//페이지 이동후 검색
			$scope.search = {};
			if($rootScope.quickSearch.storeCd){
				$scope.search['stInvStore'] = $rootScope.quickSearch.storeCd;
			}
			const startDate = formatDate3($rootScope.quickSearch.startDate);
			const endDate = formatDate3($rootScope.quickSearch.endDate);
			$scope.search['startDate'] = startDate;
			$scope.search['endDate'] = endDate;

			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inven/invList', param );
		}else{															//일반 페이지 이동
			httpGetList($http, $scope,'/inven/invList' );
		}

		var checkList = []; //체크박스 리스트
		//체크박스 전체 체크
		$scope.checkAll = function(status, prKey){
			const tempList = [];
			for(const key in $scope.list){
				// if($scope.list[key].stInvYn == 'Y' || $scope.list[key].modDate != null){
				if($scope.list[key].stInvYn == 'Y'){
					continue;
				}
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

		//quick search
		$rootScope.goInvInfo = function(stInvSeq){
			for(const value of $scope.topMenu){
				if(value.PGM_CD == 'IV2'){
					$location.url(value.PGM_URL+'?seq='+stInvSeq);
					break;
				}
			}
		}

		//테이블 버튼 사용
		$scope.tableBtn = function(command){
			if(command == 'delete'){

				for(const key in $scope.list){
					if(checkList.includes($scope.list[key].stInvSeq)&&$scope.list[key].modDate!=null){
						modalAlert($uibModal, "출고삭제", "수정 된 재고실사 데이터는 삭제가 불가능합니다");
						return
					}
				}
				if(checkList.length < 1){
					modalAlert($uibModal, "출고삭제", "데이터를 선택해주세요.");
					return;
				}

				modalCheck($uibModal, "재고실사삭제", "데이터를 삭제하시겠습니까", function() {
					$http({
						method: 'POST',
						url: "/inven/invenDelete",
						data: {'list': checkList},
						headers: {'Content-Type': 'application/json; charset=utf-8'}
					}).success(function (data) {
						if (data.resultCode == 'S') {
							modalAlert($uibModal, "재고실사삭제", "데이터가 삭제되었습니다");
						}
						$rootScope.reload();
					}).error(function (data) {
						alert('정보 업데이트 실패');
					});
				});
			}
		}


}]);
