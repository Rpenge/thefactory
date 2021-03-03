// RFID 사용내역
app.controller('rfidChangeController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window','$route',
    function ($scope, $http, $location, $routeParams, $rootScope, $window, $route) {
	 
	
	$scope.search = {};	
	$scope.checkList = new Array();
	
	//주소에 따라 해당 데이터 조회
	if($location.$$path == '/RFID/rfidRegList'){
		$scope.search.work = 'AR';
	}else if($location.$$path == '/RFID/rfidMoveList'){
		$scope.search.work = 'AM';
	}else if($location.$$path == '/RFID/rfidWiList'){
		$scope.search.work = 'WI';
	}else if($location.$$path == '/RFID/rfidDisList'){
		$scope.search.work = 'AD';
	}else if($location.$$path == '/RFID/rfidFailList'){
		$scope.search.updateYn = 'N';
	}
	var param = generateParam($scope.search);
	httpGetList("/rfidChange", param, $scope, $http);
	
	
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
	
	
	//페이지 이동
	$scope.goPage = function(page){
		if($scope.current == page){
	    	return;
	    }
		if($scope.end < page){
			return;
		}
		if(page == 0){
			return;
		}
		$scope.search.page = page - 1;
		param = generateParam($scope.search);
		httpGetList("/rfidChange", param, $scope, $http);
	};
	
	//검색
	$scope.clickSearch = function(){
		if(document.getElementsByName('dateSearch')[1].checked){
			$scope.search.startDate = formatDate($scope.startDate);
			var endDate = new Date($scope.endDate.getYear()+1900, $scope.endDate.getMonth(), $scope.endDate.getDate()+1);
			$scope.search.endDate = formatDate(endDate);
		}
		$scope.search.page = 0;
		param = generateParam($scope.search);
		httpGetList("/rfidChange", param, $scope, $http);
	};
	
	//테이블 정렬
	$scope.sort = function(sortName){
		$scope.search.sortOrder = $scope.search.sortOrder=="asc" ? "desc" : "asc";
		$scope.search.sortName = sortName;
		param = generateParam($scope.search);
		httpGetList("/rfidChange", param, $scope, $http);
		
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
		$scope.search.size = size;
		$scope.search.page = 0;
		param = generateParam($scope.search);
		httpGetList("/rfidChange", param, $scope, $http);
	}
	
	//테이블 클릭으로 검색
	$scope.tableSearch = function(ctrlCode){
		$scope.search.text = ctrlCode;
		$scope.search.option = 'astCtrlCd';
		param = generateParam($scope.search);
		httpGetList("/rfidChange", param, $scope, $http);
	}
	
//	//테이블 데이터 구분선
//	$scope.divLine = function(pre, data){
//		if(!(pre && data)){
//			return;
//		}
//		var sortNm = $scope.search.sortName;
//		if(sortNm == null){
//			sortNm= 'chgDt';
//		}else if(sortNm == 'astChgSeq'){
//			return;
//		}
//		
//		if(sortNm == 'chgDt'){
//			if(Math.floor(pre[sortNm]/60000) != Math.floor(data[sortNm]/60000) ){
//				return {'border-top' : '3px solid lightgray'};
//			}
//		}else if(pre[sortNm] != data[sortNm] ){
//			return {'border-top' : '3px solid lightgray'};
//		}
//	}
	
	//하위 코드 정리
	$scope.locList = [];
	codeSearch('LOC', '', $scope);
	
	//엑셀 파일 다운로드
	$scope.excelDown = function(){
		var fileName = '자산변동내역_'+formatDate3(new Date())+'.xlsx';
		$http({
			method : 'POST',
			url : "/excelDown",
			data  : tableData(),
			responseType : 'blob',
		}).success(function(data){
			var filename = "test.xlsx";
			var blob = data;
			var downloadLink = window.document.createElement('a');
			var contentTypeHeader = 'application/x-www-form-urlencoded;charset=UTF-8';
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
	
	
	
}]);