
//회원 리스트
app.controller('invController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);

		httpGetList($http, $scope,'/inven/invList' );

		if($rootScope.searchMove == 1){									//페이지 이동후 검색
			$scope.search = {};

			// const endDate = $rootScope.quickSearch.endDate;
			// const newEndDate = formatDate3(new Date(endDate.setDate(endDate.getDate() +1)));
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
					// $rootScope.stInvSeq = stInvSeq;
					$location.url(value.PGM_URL+'?seq='+stInvSeq);
					break;
				}
			}
		}


}]);
