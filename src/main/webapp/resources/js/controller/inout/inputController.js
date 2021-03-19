
//회원 리스트
app.controller('inputController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);


		if($rootScope.searchMove){	//페이지 이동후 검색
			console.log($rootScope.searchData);
			const param = generateParam($scope.searchGroup);
			console.log(param);
			httpGetList($http, $scope,'/inout/inputList', param );
		}else{
			httpGetList($http, $scope,'/inout/inputList' );
		}

		//검색
		$scope.searchBtn = function(command){
			if(command == 'group'){
				const param = generateParam($scope.searchGroup);
				httpGetList($http, $scope,'/inout/inputList', param );
				$scope.search.storeCd = $scope.searchGroup.storeCd;
				$scope.search.grade = $scope.searchGroup.grade;
				$scope.search.userStat = $scope.searchGroup.userStat;
			}else if(command == 'word'){
				const param = generateParam($scope.searchWord);
				httpGetList($http, $scope,'/inout/inputList', param);
				$scope.search.word = $scope.searchWord.word;
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
