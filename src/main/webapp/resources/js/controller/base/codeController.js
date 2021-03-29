//코드 리스트
app.controller('codeController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location); //현재페이지 정보
		httpGetList($http, $scope,'/base/findList' ); //코드리스트 조회

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


		$scope.bclick = function(value){
			console.log(value);
			$scope.form.bCommCd = value.commCd;
			$scope.form.bCommCdNm = value.commCdNm;
			$scope.form.bUseYn = value.useYn;
		}

		$scope.mclick = function(value) {
			console.log(value);
			$scope.form.mCommCd = value.commCd;
			$scope.form.mCommCdNm = value.commCdNm;
			$scope.form.mCodeLevel = value.codeLevel;
			$scope.form.mUseYn = value.useYn;
		}

		$scope.sclick = function(value) {
			console.log(value);
			$scope.form.sCommCd = value.commCd;
			$scope.form.sCommCdNm = value.commCdNm;
			$scope.form.sCodeLevel = value.codeLevel;
			$scope.form.sUseYn = value.useYn;
		}









		/*httpBList($http, $scope,'/base/commBList' ); //대분류코드 리스트 조회*/
		/*$http({
			method: 'GET',
			url: '/base/commBList',
			responseType: 'json',
			headers : {
				"Content-Type": "application/json; charset=utf-8",
				"Accept": "application/json"
			}
		}).then(function(response) {
			console.log("Success");
			console.log(response.data);
			$scope.ccode = response.data;
		}, function(error) {
			console.log("Error" + error);
		});*/
	}]);
