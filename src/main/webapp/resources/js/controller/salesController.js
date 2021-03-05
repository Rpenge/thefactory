app.controller('salesController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window','$route','$uibModal',
    function ($scope, $http, $location, $routeParams, $rootScope, $window, $route, $uibModal) {

		// for(var value of $rootScope.topMenu){
		// 	// console.log($location.url());
		// 	// console.log(value.PGM_URL);
		// 	if(value.PGM_URL == $location.url()){
		// 		$rootScope.currentMenu = value;
		// 		// console.log(value.PGM_NM);
		// 		// console.log($rootScope.currentMenu);
		// 	}
		// }
		//현재페이지 정보
		pageInfo($rootScope, $location);



	$scope.fileUpload = function(path){

		console.log(document.getElementById("ex_filename").value);
		console.log("path", path);
		var fileValue = path.split("\\");
		var fileName = fileValue[fileValue.length-1]; // 파일명
		$scope.file_path = fileName;
		$scope.$apply();
	}



	$scope.search = {};
	$scope.checkList = new Array();

	if($location.$$path == '/assetManagement/assetDisList'){
		$scope.search.status = 'DIS';
	}

	//날짜 검색 현재일로 초기화
	$scope.dateUse = true;
	if($scope.startDate == "" || $scope.startDate == null || $scope.endDate == ""  || $scope.endDate == null){
		var today = new Date();
		$scope.startDate = new Date(today.getYear()+1900,today.getMonth()-1, today.getDate());
		$scope.endDate = new Date();
	}
	$scope.dateUseChange =function(status){
		$scope.dateUse = status;
		if(status){
			$scope.search.startDate = null;
			$scope.search.endDate = null;
		}
	}

	var param = generateParam($scope.search);
	// httpGetList("/assetManagementList", param, $scope, $http);

	//체크박스 리스트 추가, 삭제
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
		for(key in $scope.list){
			$scope.list[key].isSelected = status;
			tempList.push($scope.list[key].assetManagementSeq);
		}
		if(status){
			$scope.checkList = tempList;
		}else{
			$scope.checkList = [];
		}
	}

	//자산 데이터 수정
	$scope.assetUpdate = function(command){
		$http({
			method : 'PUT',
			url : "/assetManagement/status",
			data : {"list" : $scope.checkList, "command":command },
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).success(function(data){
			if(command == 'DEL'){
				alert('삭제되었습니다');
			}else{
				alert('변경되었습니다');
			}
			$route.reload();
		})
		.error(function(data){
			alert('실패');
		});
	}

	//검색
	$scope.clickSearch = function(){
		if(document.getElementsByName('dateSearch')[1].checked){
			$scope.search.startDate = formatDate2($scope.startDate);
			var endDate = new Date($scope.endDate.getYear()+1900, $scope.endDate.getMonth(), $scope.endDate.getDate(), 23, 59, 59);
			$scope.search.endDate = formatDate2(endDate);
		}
		$scope.search.page = 0;
		param = generateParam($scope.search);
		httpGetList("/assetManagementList", param, $scope, $http);
	};

	//페이지 이동
	$scope.goPage = function(page){
		if($scope.current == page || $scope.end < page || page == 0){
	    	return;
		}
		$scope.search.page = page - 1;
		param = generateParam($scope.search);
		httpGetList("/assetManagementList", param, $scope, $http);
	};

	//정렬
	$scope.sort = function(sortName){
		$scope.search.sortOrder = $scope.search.sortOrder=="asc" ? "desc" : "asc";
		$scope.search.sortName = sortName;
		param = generateParam($scope.search);
		httpGetList("/assetManagementList", param, $scope, $http);

		var sortIcon = document.getElementById('sortIcon');
		if(sortIcon != null){
			document.getElementById('sortIcon').remove('');
		}

		var sortThead = document.getElementById(sortName);
		if($scope.search.sortOrder=="desc"){
			sortThead.innerHTML+=" <i id='sortIcon' class='xi-caret-down'/>";
		}else{
			sortThead.innerHTML+=" <i id='sortIcon' class='xi-caret-up'/>";
		}
	};

	//달력 사용
	$scope.startDateOpen = function() {
	    $scope.search.startDateOpened = true;
	};
	$scope.endDateOpen = function() {
	    $scope.search.endDateOpened = true;
	};

	//페이지 사이즈 변경
	$scope.pageSize = function(size){
		$scope.search.size = $scope.size;
		$scope.search.size = size != 'total' ? size : $scope.total;
		$scope.search.page = 0;
		param = generateParam($scope.search);
		httpGetList("/assetManagementList", param, $scope, $http);
	}

	//하위 코드 정리
	$scope.locList = [];
	codeSearch('LOC', '', $scope);

	//엑셀 저장 양식 다운
	$scope.formDown = function(){
		window.location.href = 'resources/xlsx/asset_save_form.xlsx'
	}
	//엑셀 자산 자료 업로드
	$scope.excelClick = function(){
		document.getElementsByName('excel')[0].click();
	}
	$scope.excelUpload = function(){
		modalCheck($uibModal,'upload','데이터를 업로드 하시겠습니까?', excelUpload);
	}
	function excelUpload(){
		var form = $('#excelUpload')[0];
	    var formData = new FormData(form);
		$http({
			method : 'POST',
			enctype: 'multipart/form-data',
			url : "/excelUpload",
			data : formData,
			headers: {"Content-Type": undefined },
		}).success(function(data){
			if(data.resultCode=='e'){
				alert('양식을 확인해 주세요.');
			}else{
				alert('데이터가 등록되었습니다.');
			}
			$rootScope.reload();
		})
		.error(function(data){
			alert('에러');
		});
	}




	//엑셀 파일 다운로드
	$scope.excelDown = function(){
		var fileName = '자산정보_'+formatDate3(new Date())+'.xlsx';
		$http({
			method : 'POST',
			url : "/excelDown",
			data  : tableData('tableData'),
			responseType : 'blob',
		}).success(function(data){
			var blob = data;
			var downloadLink = window.document.createElement('a');
			downloadLink.href = window.URL.createObjectURL(new Blob([blob]));
			downloadLink.download = fileName;
			document.body.appendChild(downloadLink);
			downloadLink.click();
			document.body.removeChild(downloadLink);
		})
		.error(function(data){
			alert('실패');
		});
	}

	//엑셀 파일 다운로드  all
	$scope.excelDownAll = function(cmd){
		var title ='';
		if(cmd=='asset'){
			title='자산목록';
		}
		var fileName = title+'_'+formatDate3(new Date())+'.xlsx';
		var data = {};
		data.search = $scope.search;
		data.cmd = cmd;
		$http({
			method : 'POST',
			url : "/excelDown2",
			data  : data,
			responseType : 'blob',
		}).success(function(data){
			var blob = data;
			var downloadLink = window.document.createElement('a');
			downloadLink.href = window.URL.createObjectURL(new Blob([blob]));
			downloadLink.download = fileName;
			document.body.appendChild(downloadLink);
			downloadLink.click();
			document.body.removeChild(downloadLink);
		})
		.error(function(data){
			alert('실패');
		});
	}

	//modal창 열기
	$scope.modalOpen = function(command, data){
		//자산 상세
		if(command=='assetDetail'){
			$ctrl = {};
			$ctrl['controlCode'] = data;
			$ctrl['commonCode'] = $scope.commonCode;
			var modalInstance = $uibModal.open({
				templateUrl: 'assetManagement/modal/assetDetail',
				controller: 'assetMgController_dt',
				controllerAs: '$ctrl',
				size: 'xlg'
			});
		}
		//자산등록창
		else if(command=='assetReg'){
			$ctrl = {};
			$ctrl['commonCode'] = $scope.commonCode;
			var modalInstance = $uibModal.open({
				backdrop: 'static',
				templateUrl: 'assetManagement/modal/assetRegist',
				controller: 'assetMgController_reg',
				controllerAs: '$ctrl',
				size: 'lg'
			});
		}
		else {
			//일반 프린트
			if (command == 'print') {
				url = 'assetManagement/modal/assetPrint';
				controller = 'printController';
				size = 'xlg';
				$ctrl = $scope;
			}
			//QR프린트창 열기
			if (command == 'QR') {
				url = 'assetManagement/modal/qrPrint';
				controller = 'QRprintController';
				size = 'xlg';
				$ctrl = $scope;
			}
			//수리등록창 열기
			if (command == 'repair') {
				if (!$scope.checkList[0]) {
					modalAlert($uibModal, '알림', '선택 된 데이터가 없습니다.');
					return;
				}
				url = 'assetManagement/modal/repairReg';
				controller = 'repairRegController';
				size = 'lg';
				$ctrl = $scope;
			}

			var modalInstance = $uibModal.open({
				backdrop: 'static',
				templateUrl: url,
				controller: controller,
				controllerAs: '$ctrl',
				size: size
			});
		}
	}

}]);
