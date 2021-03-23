
//회원 리스트
app.controller('stockController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);



		// if($rootScope.searchMove == 1){	//페이지 이동후 검색
		// 	$scope.search = {};
		// 	if($rootScope.quickSearch.brand != null){
		// 		$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.brand.substr(0,2);
		// 	}
		// 	if($rootScope.quickSearch.gender != null){
		// 		$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.gender.substr(0,4);
		// 	}
		// 	if($rootScope.quickSearch.cls != null){
		// 		$scope.search['BRAND_KIND_CD'] = $rootScope.quickSearch.cls;
		// 	}
		// 	$scope.search['PRD_SIZE'] = $rootScope.quickSearch.prdSize;
		// 	const param = generateParam($scope.search);
		// 	httpGetList($http, $scope,'/inout/inputList', param );
		// }else if($rootScope.searchMove == 2) { //단어 검색
		// 	const param = generateParam($rootScope.quickSearchWord);
		// 	httpGetList($http, $scope,'/inout/inputList', param )
		// }else{
		// 	httpGetList($http, $scope,'/inout/inputList' );
		// }
		// $rootScope.searchMove = false;
		//
		// //검색
		// $scope.searchBtn = function(command){
		// 	if(command == 'group'){
		// 		const param = generateParam($scope.searchGroup);
		// 		httpGetList($http, $scope,'/inout/inputList', param );
		// 		$scope.search.storeCd = $scope.searchGroup.storeCd;
		// 		$scope.search.grade = $scope.searchGroup.grade;
		// 		$scope.search.userStat = $scope.searchGroup.userStat;
		// 	}else if(command == 'word'){
		// 		const param = generateParam($scope.searchWord);
		// 		httpGetList($http, $scope,'/inout/inputList', param);
		// 		$scope.search.word = $scope.searchWord.word;
		// 	}
		// }
		//
		//
		//
		// //페이지 이동
		// $scope.goPage = function(page){
		// 	if($scope.current == page || $scope.end < page || page == 0){
		// 		return;
		// 	}
		// 	$scope.search.page = page - 1;
		// 	const param = generateParam($scope.search);
		// 	httpGetList($http, $scope,'/inout/inputList', param );
		// };
		//
		// //페이지 사이즈 변경
		// $scope.pageSize = function(){
		// 	$scope.search.page = 0;
		// 	const param = generateParam($scope.search);
		// 	httpGetList($http, $scope,'/inout/inputList', param );
		// }

}]);
