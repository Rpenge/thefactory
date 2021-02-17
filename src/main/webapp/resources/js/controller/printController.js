//프린트
app.controller('printController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window','$uibModalInstance',
    function ($scope, $http, $location, $routeParams, $rootScope, $window, $uibModalInstance) {

	//체크 자산 확인
	$scope.list = [];
	if($ctrl.checkList.length > 0){
		for(seq in $ctrl.list){		
			if($ctrl.checkList.includes($ctrl.list[seq].assetManagementSeq)){
				$scope.list.push($ctrl.list[seq]);
			}
		}
	}else{
		$scope.list = $ctrl.list;
	}
	
	$scope.commonCode = $ctrl.commonCode;
	$scope.search = $ctrl.search;
	$scope.total = $ctrl.total;
	$scope.user = $rootScope.user.principal;
	$scope.today = new Date();

	//날짜 검색 확인
	if($scope.search.endDate == null){
		$scope.endDate = new Date();
	}else{
		$scope.startDate = strToDate($scope.search.startDate);
		$scope.endDate = strToDate($scope.search.endDate);
	}
	function strToDate(strDt){
		var date = new Date(strDt.substring(0,4), strDt.substring(4,6), strDt.substring(6,8));
		date.setMonth(date.getMonth() -1);
		return date;
	}
	
	//출력
	$scope.onPrint = function(){
		const html = document.querySelector('html');
		const printContents = document.querySelector('.modal-body').innerHTML;
		const printStyle = document.querySelector('.modal-body').style;
		const printDiv = document.createElement('DIV');
		html.appendChild(printDiv);
		
		var tempClass = document.body.getAttribute('class');
		
		printDiv.innerHTML = printContents;
		printDiv.style.padding = printStyle.padding;
		document.body.setAttribute('class', 'noPrint');
		//document.body.style.display = 'none';  // 테스트용
		window.print();
		document.body.setAttribute('class', tempClass);
		//document.body.style.display = 'block'; //  테스트용
		printDiv.style.display = 'none';
		
	}
	
	
	//사이드 공백 확대, 축소	
	$scope.blank = function(command){
		var modalDom = document.getElementsByClassName('modal-body')[0];
		var padding = parseInt(modalDom.style.paddingLeft);
			
		if( command == 'up' ){
			if(padding > 125 ) return;
			padding += 25;
		}else if(command == 'down'){
			if(padding <= 25) return;
			padding -= 25;
		}else if(command == 'base'){
			padding = 50;
		}
		padding += 'px';
		modalDom.style.paddingLeft = padding;
		modalDom.style.paddingRight = padding;
	}
	
	//view사이즈 변경
	var viewSize = 1;
	$scope.sizeUp = function(){
		var printContents = document.getElementsByClassName('modal-body')[0];
		viewSize += 0.1;
		printContents.style.transform = "scale("+viewSize+","+viewSize+")";
	}
	$scope.sizeDown = function(){
		var printContents = document.getElementsByClassName('modal-body')[0];
		viewSize -= 0.1;
		printContents.style.transform = "scale("+viewSize+","+viewSize+")";
	}
	$scope.sizeDefault = function(){
		var printContents = document.getElementsByClassName('modal-body')[0];
		viewSize = 1;
		printContents.style.transform = "scale("+viewSize+","+viewSize+")";
	}
	
	$scope.close = function() {
		$uibModalInstance.close();
	};
	
	//테이블 라인 굵기 변경
	var lineWidth = 1;
	$scope.lineWeight = function(direct){
		if(direct == 'thick'){
			lineWidth += 1;
		}else if(direct == 'thin' && lineWidth > 1){
			lineWidth -= 1;
		}else if(direct == 'base'){
			lineWidth = 1;
		}
		var lineWidthPx = lineWidth + 'px';
		var modalDom = document.getElementsByClassName('modal-body')[0];
		var tableContents = modalDom.getElementsByTagName('table');
		var tdContents = modalDom.getElementsByTagName('td');
		var thContents = modalDom.getElementsByTagName('th');
		for(key in tableContents){
			if(tableContents[key].style != null){
				tableContents[key].style.borderWidth = lineWidthPx;
			}
		}
		for(key in tdContents){
			if(tdContents[key].style != null){
				tdContents[key].style.borderWidth = lineWidthPx;
			}
		}
		for(key in thContents){
			if(thContents[key].style != null){
				thContents[key].style.borderWidth = lineWidthPx;
			}
		}
	}
	//선 색상 변경
	$scope.lineColor = function(color){
		var modalDom = document.getElementsByClassName('modal-body')[0];
		var tableContents = modalDom.getElementsByTagName('table');
		var tdContents = modalDom.getElementsByTagName('td');
		var thContents = modalDom.getElementsByTagName('th');
		for(key in tdContents){
			if(tdContents[key].style != null){
				tdContents[key].style.borderColor= color;
			}
		}
		for(key in thContents){
			if(thContents[key].style != null){
				thContents[key].style.borderColor= color;
			}
		}
		for(key in tableContents){
			if(tableContents[key].style != null){
				tableContents[key].style.borderColor= color;
			}
		}
	}
	
	$scope.fontColor = function(color){
		var modalDom = document.getElementsByClassName('modal-body')[0];
		modalDom.style.color = color;
	}
	
	var fontSize = 13;
	$scope.fontSize = function(direct){
		if(direct == 'big'){
			fontSize += .5;
		}else if(direct == 'small'){
			fontSize -= .5;
		}else if(direct == 'base'){
			fontSize =13;
		}
		var size = fontSize + 'px';
		var modalDom = document.getElementsByClassName('modal-body')[0];
		var tdContents = modalDom.getElementsByTagName('td');
		var thContents = modalDom.getElementsByTagName('th');

		for(key in tdContents){
			if(tdContents[key].style != null){
				tdContents[key].style.fontSize = size;
			}
		}
		for(key in thContents){
			if(thContents[key].style != null){
				thContents[key].style.fontSize = size;
			}
		}
	}
}]);

//QR프린트
app.controller('QRprintController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$window','$uibModalInstance',
    function ($scope, $http, $location, $routeParams, $rootScope, $window, $uibModalInstance) {
	
	//체크 자산 확인
	$scope.list = [];
	if($ctrl.checkList.length > 0){
		for(seq in $ctrl.list){		
			if($ctrl.checkList.includes($ctrl.list[seq].assetManagementSeq)){
				$scope.list.push($ctrl.list[seq]);
			}
		}
	}else{
		$scope.list = $ctrl.list;
	}
	
	$scope.commonCode = $ctrl.commonCode;
	$scope.search = $ctrl.search;
	$scope.total = $ctrl.total;
	$scope.user = $rootScope.user.principal;
	$scope.today = new Date();
	$scope.command = $ctrl.command;
	
	$http({
		method: 'POST', 
		url: '/assetQRList',
		data : $scope.list
	}).success(function(data) {
		$scope.list = data;
	}).error(function(data) {
	    alert('조회 실패');
	});
	
	//출력
	$scope.onPrint = function(){
		const html = document.querySelector('html');
		const printContents = document.querySelector('.modal-body').innerHTML;
		const printStyle = document.querySelector('.modal-body').style;
		const printDiv = document.createElement('DIV');
		html.appendChild(printDiv);
		
		var tempClass = document.body.getAttribute('class');
		
		printDiv.innerHTML = printContents;
		printDiv.style.padding = printStyle.padding;
		document.body.setAttribute('class', 'noPrint');
		//document.body.style.display = 'none';  // 테스트용
		
		window.onbeforeprint = function() {}
		$("img:last").one('load', function() {
		     window.print();
		});
		window.onafterprint = function(){
			document.body.setAttribute('class', tempClass);
			//document.body.style.display = 'block'; //  테스트용
			printDiv.remove();
		}
	}
	
	//닫기
	$scope.close = function() {
		$uibModalInstance.close();
	};

}]);