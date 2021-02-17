app.run(function($rootScope, $http, $route, $window){
	$rootScope.reload = function(){
		$route.reload();
		$window.sessionStorage.removeItem("current");
	};

});
app.controller('indexController', ['$scope', '$http', '$location', '$rootScope', '$window', function ($scope, $http, $location, $rootScope, $window) {

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

	$http({
		method: 'GET',
		url: '/reload',
		headers: {'Content-Type': 'application/json; charset=utf-8'}
	}).success(function(data) {
		$rootScope.topMenu = data.menu;
	}).error(function(data) {
		alert('code조회 실패2');
	});

	$scope.logout = function() {
		$http({
			method: 'POST',
			url: '/member/logout',
			data: {},
			headers: {'Content-Type': 'application/json; charset=utf-8'}
		}).success(function(data, status, headers, config) {
			if(status == 200) {
				$rootScope.authenticated = false;
				$location.url("/");
				sessionStorage.clear();
			} else {
				modalCall("에러발생");
			}
		}).error(function(data, status, headers, config) {
		    $rootScope.authenticated = false;
		    $location.url("/");
		});
	};

	$scope.client;

	$scope.disconnection = function(){
		if ($scope.client != null) {
			$scope.client.disconnect();
			$scope.resData = {};
	    }
	};

	$scope.$watch(function(){
		return $rootScope.authenticated;
		}, function() {
		if($rootScope.authenticated != undefined){
			if($rootScope.authenticated){
				if($location.url() != "/main/home"){
				}
			} else {
				$scope.disconnection();
			}
		}
	}, true);

	$scope.goMain = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/main/home");
    	closeSideMenu();
    };

    $scope.goUserList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userList");
    	closeSideMenu();
    };

	$scope.goAssetManagementList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetList");
    	closeSideMenu();
	};

	$scope.goAssetStatusChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetStatusChange");
    	closeSideMenu();
	};

	$scope.goAssetDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/assetManagement/assetDisList");
    	closeSideMenu();
	};

	$scope.goRfidChange = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidChange");
    	closeSideMenu();
	};
	$scope.goCommonCode = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/setting/commonCode");
    	closeSideMenu();
	};

	$scope.goUserInfo = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/member/userInfoMod");
	};

	$scope.goRfidRegList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidRegList");
    	closeSideMenu();
	};

	$scope.goRfidMoveList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidMoveList");
    	closeSideMenu();
	};

	$scope.goRfidWiList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidWiList");
    	closeSideMenu();
	};

	$scope.goRfidDisList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidDisList");
    	closeSideMenu();
	};

	$scope.goRfidFailList = function(){
    	$window.sessionStorage.removeItem("current");
    	$location.url("/RFID/rfidFailList");
    	closeSideMenu();
	};

	// $scope.goMenu = function(url){
	// 	$window.sessionStorage.removeItem("current");
    // 	$location.url(url);
    // 	closeSideMenu();
	// }

	$scope.goMenu = function(data){
		$location.url(data.url);
	}


	$scope.content = {'width':'100%'};
	//사이드 메뉴 토글
	$scope.menuToggle = function(){
		$scope.menu = $scope.menu ? false : true;
	}

	$scope.dock = [true, true, true, true, true];
	$scope.sideMenuDock = function(idx){
		$scope.dock[idx] = $scope.dock[idx] ? false : true;
	}


	//모바일 사이즈시 사이드메뉴 닫기
	function closeSideMenu(){
		if(window.innerWidth <= 768){
			$scope.menu = false;
		}else{
			$scope.menu = false;
		}
	}
	closeSideMenu();
	var mql = window.matchMedia("screen and (max-width: 768px)");
	mql.addListener(function(e) {
	    if(e.matches) {
	    	$scope.menu = false;
	    	$scope.$apply();
	    } else {
	    	$scope.menu = true;
	    	$scope.$apply();
	    }
	});


}]);

app.controller('homeController', ['$scope', '$http', '$location', '$interval', '$rootScope', '$timeout', '$window',
	function ($scope, $http, $location, $interval, $rootScope, $timeout, $window) {

	$scope.chart = {};

	$http({
		method: 'GET',
		url: '/homeChart',
		data: {},
		headers: {'Content-Type': 'application/json; charset=utf-8'}
	}).success(function(data) {
		$scope.chart = data;

		//바 그래프
		google.charts.load('current', {'packages':['bar']});
	    google.charts.setOnLoadCallback(drawChart1);

	    //원형 그래프
		google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawChart);
	}).error(function(data) {
		alert('error');
	});


    function drawChart1() {
    	var status = $scope.chart.status;
      var data = google.visualization.arrayToDataTable([
        ['분류', '사용', '수리', '대여', '폐기'],
        [' ', status['사용'], status['수리'], status['대여'], status['폐기']],

      ]);

      var options = {
        chart: {
			title: '자산 현황',
			subtitle: '자산 상태 현황',

        },
        bar: { groupWidth: '50%' },
        bars: 'Vertical' // Required for Material Bar Charts.

      };

      if(window.innerWidth <= 768){
    	  options.legend = { position: "none" };
    	  options.width = 150;
    	  options.height = 250;
      }

      var chart = new google.charts.Bar(document.getElementById('barchart_material'));
      chart.draw(data, google.charts.Bar.convertOptions(options));
    }

	//원형 차트


	function drawChart() {
		var divChart = [];
		divChart.push(['Task', 'number per total']);
		for(var key in $scope.chart.div){
			var codeList = $scope.chart.div[key];
			codeList[0] = $scope.commonCode[codeList[0]]['codeName'];
			divChart.push(codeList);
		}
        var data = google.visualization.arrayToDataTable(divChart);
        var options = {
          title: '자산 현황',
          legend: 'none',
          pieSliceText: 'label'
        };

        if(window.innerWidth <= 768){
      	  options.width = 150;
      	  options.height = 250;
        }

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
      }


}]);

app.controller('statsModalController', ['$scope', '$http', '$element', 'close', function ($scope, $http, $element, close) {
	$scope.close = function(result) {
		$element.modal('hide');
	};
	$scope.cancel = function() {
	    $element.modal('hide');
	};
}]);


app.controller('modalController', ['$scope', '$http', '$location', '$routeParams', '$rootScope', '$uibModalInstance',
    function ($scope, $http, $location, $routeParams, $rootScope, $uibModalInstance) {
		$scope.title = $ctrl.title;
		$scope.body = $ctrl.body;
		$scope.ok = function() {
			$uibModalInstance.close();
	    };
	    $scope.cancel = function() {
	    	$uibModalInstance.dismiss();
	    };
}]);
