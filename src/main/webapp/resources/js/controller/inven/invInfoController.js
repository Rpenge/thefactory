
//회원 리스트
app.controller('invInfoController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);
		$scope.select = {};

		if($location.$$search.seq){
			$scope.search = {};
			$scope.search.stInvSeq = $location.$$search.seq;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inven/invenList', param);
		}else{
			httpGetList($http, $scope,'/inven/invenList');
		}



		// if($rootScope.searchMove == 1){									//페이지 이동후 검색
		// 	$scope.search = {};
		//
		// 	// const endDate = $rootScope.quickSearch.endDate;
		// 	// const newEndDate = formatDate3(new Date(endDate.setDate(endDate.getDate() +1)));
		// 	const startDate = formatDate3($rootScope.quickSearch.startDate);
		// 	const endDate = formatDate3($rootScope.quickSearch.endDate);
		//
		// 	$scope.search['startDate'] = startDate;
		// 	$scope.search['endDate'] = endDate;
		//
		// 	const param = generateParam($scope.search);
		// 	httpGetList($http, $scope,'/inven/invList', param );
		// }else{															//일반 페이지 이동
		// 	httpGetList($http, $scope,'/inven/invList' );
		// }

		//체크박스 전체 체크
		var checkList = []; //체크박스 리스트
		$scope.checkAll = function(status, prKey){
			const tempList = [];
			for(const key in $scope.list){
				if($scope.list[key].invYn == 'Y'){
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


		// //폼 형식
		$scope.es = {'modForm':false};
		$scope.formChange =function(command, data){
			if(command == 'mod'){
				$scope.es ={};
				$scope.es.modForm = true;
				const formData = {}
				for(const key in data){
					formData[key] = data[key];
				}
				$scope.form = formData;
			}
		}

		$scope.formSave = function(){
			if($scope.es.modForm == true){
				$http({
					method : 'POST',
					url : "/inven/invenUpdate",
					data  : $scope.form,
					headers: {'Content-Type':'application/json; charset=utf-8'}
				}).success(function(data){
					if(data.resultCode == 'S') {
						modalAlert($uibModal, "재고실사내역", "해당 상품의 실사내역이 확정 되었습니다.");
					}
					$scope.reload();
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
			httpGetList($http, $scope,'/inven/invenList', param );
		};

		//페이지 사이즈 변경
		$scope.pageSize = function(){
			$scope.search.page = 0;
			const param = generateParam($scope.search);
			httpGetList($http, $scope,'/inven/invenList', param );
		}

		//차이 발생 목록
		$scope.stkDif = function(command){
			if(command == 'dis') {
				Discordance = true;
				$scope.search['invYn'] = 'N';
				$scope.search['page'] = 0;
				const param = generateParam($scope.search);
				httpGetList($http, $scope, '/inven/invenList', param);
			}else{
				Discordance = false;
				$scope.search['invYn'] = null;
				$scope.search['page'] = 0;
				const param = generateParam($scope.search);
				httpGetList($http, $scope,'/inven/invenList', param );
			}
		}


		//테이블 버튼 사용
		$scope.tableBtn = function(command){
			if(command == 'confirm'){
				if(checkList.length < 1){
					modalAlert($uibModal, "일괄확정", "데이터를 선택해주세요.");
					return;
				}
				modalCheck($uibModal, "일괄확정", "데이터를 확정처리 하시겠습니까?", function() {
					$http({
						method: 'POST',
						url: "/inven/invenUpdateList",
						data: {'list': checkList, 'stInvDate':$scope.list[0].stInvDate, 'invStoreCd':$scope.list[0].invStoreCd, 'misWork':$scope.select.misWork},
						headers: {'Content-Type': 'application/json; charset=utf-8'}
					}).success(function (data) {
						if (data.resultCode == 'S') {
							modalAlert($uibModal, "일괄확정", "데이터가 확정처리 되었습니다");
						}
						$rootScope.reload();
					}).error(function (data) {
						alert('정보 업데이트 실패');
					});
				});
			}
		}


	}]
);
