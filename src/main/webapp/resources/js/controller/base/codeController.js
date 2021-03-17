//코드 리스트
app.controller('codeController', ['$scope', '$http', '$location','$rootScope', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location,$rootScope, $rootScope, $window, $filter, $uibModal) {

	pageInfo($rootScope, $location); //현재페이지 정보

	httpGetList($http, $scope,'/base/commList'); //코드 리스트 조회

	$scope.codeSelect = function(){};
	$scope.form = {};

}]);

