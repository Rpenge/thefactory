
//회원 리스트
app.controller('inputController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location); //현재페이지 정보


		// $scope.tab = {'in':false,'out':false,'stk':false};
		//
		// $scope.addTabsOn = function(){
		// 	$scope.addTabs = true;
		// 	$scope.divFadeIn = true;
		// 	$scope.tab.in = true;
		// }




		// $scope.tabChange =function(command, data){
		//
		// 	$scope.divFadeIn = false;
		// 	if(command == 'in'){
		// 		$scope.tab ={};
		// 		$scope.tab.in = true;
		// 	}else if(command == 'out'){
		// 		$scope.tab ={};
		// 		$scope.tab.out = true;
		// 	}else if(command == 'stk'){
		// 		$scope.tab ={};
		// 		$scope.tab.stk = true;
		// 	}else if(command == 'list'){
		// 		$scope.tab ={};
		// 		$scope.addTabs = false;
		// 	}
		// 	setTimeout(function(){
		// 		$scope.divFadeIn = true;
		// 		$scope.$apply();
		// 	},300);
		//
		//
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
