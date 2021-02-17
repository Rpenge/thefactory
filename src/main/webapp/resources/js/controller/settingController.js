app.controller('commonCodeController', ['$scope', '$http', '$location', '$interval', '$rootScope', '$timeout', '$window','$uibModal',
	function ($scope, $http, $location, $interval, $rootScope, $timeout, $window, $uibModal) {
		$scope.list = {};
		
		//공통코드 다시 조회
		$http({
			method: 'GET', 
			url: '/commonCode',
			data: {},
			headers: {'Content-Type': 'application/json; charset=utf-8'} 
		}).success(function(data) {
			$scope.commonCode = data;
		}).error(function(data) {
		    alert('code조회 실패');
		});
		
		$scope.list.codeList=[];
		$scope.list.depth = [false,false,false,false];
		$scope.commonArray = new Array([],[],[],[]);
		$scope.codeFocus;
		
		//테이블 데이터 초기값
		for(key in $scope.commonCode) {
			if($scope.commonCode[key].parentCode == null){
				$scope.list.codeList.push($scope.commonCode[key]);
			}
		}
		
		//테이블에 코드 추가, 하위코드 있을 경우 셀렉트 추가, 코드 추가시 parentCode 선택
		$scope.codeChange = function(parent, dp){
			$scope.codeFocus = $scope.commonCode[parent];
			if(!$scope.codeFocus && dp > 0){
				$scope.codeFocus = $scope.commonCode[$scope.commonArray[dp-1][0].parentCode];
			}
			$scope.list.codeList = [];
			$scope.commonArray[dp] = [];
			var nextSelect = false;
			for(key in $scope.commonCode) {
				if($scope.commonCode[key].parentCode == parent){
					$scope.list.codeList.push($scope.commonCode[key]);  //테이블에 코드리스트 담기
					$scope.commonArray[dp].push($scope.commonCode[key]);
					for(key2 in $scope.commonCode){
						if($scope.commonCode[key2].parentCode==$scope.commonCode[key].code){
							nextSelect = true;
							break;
						}
					}
				}
			}
			if(!$scope.codeFocus){
				for(key in $scope.commonCode) {
					if($scope.commonCode[key].parentCode == null){
						$scope.list.codeList.push($scope.commonCode[key]);
					}
				}
			}
			
			if(nextSelect){
				$scope.list.depth[dp] = true;
			}else{
				for(var i=dp;$scope.list.depth.length>i;i++){
					$scope.list.depth[i] = false;
				}
			}
			//체크박스 해제
			$scope.checkAll.isSelected = false; 
			$scope.checkList = [];
		}
		
		
		//체크박스 리스트 추가, 삭제
		$scope.checkList = new Array();
		$scope.checkBox = function(select, status){
			var index = $scope.checkList.indexOf(select);
			if( index==-1 && status ){
				$scope.checkList.push(select);
			}else{
				$scope.checkList.splice(index, index+1);
			}
		}
		//전체 체크
		$scope.checkAll = function(status){
			var tempList = [];
			for(key in $scope.list.codeList){
				$scope.list.codeList[key].isSelected = status;
				tempList.push($scope.list.codeList[key].cmmnSeq);
			}
			if(status){
				$scope.checkList = tempList;
			}else{
				$scope.checkList = [];
			}
		}
		
		
		$scope.codeDelete = function(){
			$http({
				method : 'DELETE',
				url : "/codeDelete",
				data : $scope.checkList ,
				headers: {'Content-Type': 'application/json; charset=utf-8'} 
			}).success(function(data){
				alert('삭제되었습니다');
				$rootScope.reload();
			})
			.error(function(data){
				alert('실패');
			});	
		}
		
		//modal 코드 추가 창 열기
		$scope.addCode = function(value){
			$ctrl = {};
			var ctrlCd = "add";
			$ctrl['parentCode'] = $scope.codeFocus;
			$ctrl['childCode'] = $scope.list.codeList;

			if(value){
				$ctrl['codeNo'] = value.cmmnSeq;
				$ctrl['codeName'] = value.codeName;
				$ctrl['code'] = value.code;
			}
			var modalInstance = $uibModal.open({
				templateUrl: 'setting/addCode',
				controller: 'addCodeController',
				controllerAs: '$ctrl',
				size: 'm'
			});
		}
}]);


//modal 코드추가 컨트롤러
app.controller('addCodeController', ['$scope','$http','$location', '$rootScope','$uibModalInstance',
    function ($scope, $http, $location, $rootScope, $uibModalInstance) {
	
	$scope.list = {}
	$scope.selectCode = [];
	$scope.selectCode.push($ctrl.parentCode);
	$scope.selectCode=$scope.selectCode.concat($ctrl.childCode);
	
	//기본값 세팅
	if($ctrl.parentCode){
		$scope.list.parentCode = $ctrl.parentCode.code;
	}else{
		$scope.list.parentCode = '';
	}
	$scope.list.code = $ctrl.code;
	$scope.list.codeName = $ctrl.codeName;
	$scope.list.cmmnSeq = $ctrl.codeNo;

	//기초정보 수정 or 추가 서버 전송
	$scope.submit = function() {
		$http({
			method : 'POST',
			url : "/codeUpdate",
			data  : $scope.list,
			headers: {'Content-Type':'application/json; charset=utf-8'} 
		}).success(function(data){
			alert("완료");
			$uibModalInstance.close();
			$rootScope.reload();
		})
		.error(function(data){
			alert('실패');
		});	
	};
	
	$scope.close = function() {
		$uibModalInstance.close();
	};	
	
}]);
