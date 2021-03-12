
//회원 리스트
app.controller('ioInfoController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location); //현재페이지 정보

		$scope.tab = {'in':false,'out':false,'stk':false};

		$scope.addTabsOn = function(){
			$scope.addTabs = true;
			$scope.divFadeIn = true;
			$scope.tab.in = true;
		}




		$scope.tabChange =function(command, data){

			$scope.divFadeIn = false;
			if(command == 'in'){
				$scope.tab ={};
				$scope.tab.in = true;
			}else if(command == 'out'){
				$scope.tab ={};
				$scope.tab.out = true;
			}else if(command == 'stk'){
				$scope.tab ={};
				$scope.tab.stk = true;
			}else if(command == 'list'){
				$scope.tab ={};
				$scope.addTabs = false;
			}
			setTimeout(function(){
				$scope.divFadeIn = true;
				$scope.$apply();
			},300);


		}


		//검색
		// $scope.searchBtn = function(command){
		// 	if(command == 'group'){
		// 		const param = generateParam($scope.searchGroup);
		// 		httpGetList($http, $scope,'/system/userList', param );
		// 		$scope.search.storeCd = $scope.searchGroup.storeCd;
		// 		$scope.search.grade = $scope.searchGroup.grade;
		// 		$scope.search.userStat = $scope.searchGroup.userStat;
		// 	}else if(command == 'word'){
		// 		const param = generateParam($scope.searchWord);
		// 		httpGetList($http, $scope,'/system/userList', param );
		// 		$scope.search.word = $scope.searchWord.word;
		//
		// 	}
		// }

		//테이블 버튼 사용(탈퇴)
		// $scope.tableBtn = function(command){
		// 	if(command == 'Withdrawal'){
		// 		if(checkList.length < 1){
		// 			modalAlert($uibModal, "사용자 수정", "탈퇴할 회원을 선택해주세요.");
		// 			return;
		// 		}
		// 		$http({
		// 			method : 'POST',
		// 			url : "/system/userWd",
		// 			data  :  {'prList' :checkList},
		// 			headers: {'Content-Type':'application/json; charset=utf-8'}
		// 		}).success(function(data){
		// 			if(data.resultCode == 'S') {
		// 				modalAlert($uibModal, "사용자 수정", "사용자 정보가 변경 되었습니다.");
		// 			}
		// 			$rootScope.reload();
		// 		}).error(function(data){
		// 			alert('정보 업데이트 실패');
		// 		});
		// 	}
		// }

		//페이지 이동
		$scope.goPage = function(page){
			if($scope.current == page || $scope.end < page || page == 0){
				return;
			}
			$scope.search.page = page - 1;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/system/userList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/system/userList', param );
		}

}]);
