function userLoginCheck($http, $rootScope,$window, $location){
	if(sessionStorage.getItem("id")){
		$rootScope.authenticated = true;
	}else{
		$rootScope.authenticated = false;
		$window.location.url("/");
		sessionStorage.clear();
	}
	$rootScope.currentMenu = {};
}

function menuCheck($rootScope, $location){
	var menuCehck = false ;
	var url = $location.url().split('?');
	for(const i in $rootScope.topMenu){
		if($rootScope.topMenu[i].PGM_URL == url[0]){
			menuCehck = true;
			break;
		}
	}
	if(!menuCehck){
		$location.url("/");
	}
}

//현재페이지 정보 저장
function pageInfo($rootScope, $location){
	var url = $location.url();
	url = url.split('?');
	if($rootScope.topMenu) {
		for (var value of $rootScope.topMenu) {
			if (value.PGM_URL == url[0]) {
				$rootScope.currentMenu = value;

				if($rootScope.quick1List.includes(value.PGM_CD)){
					$rootScope.quick1 = true;
				}else{
					$rootScope.quick1 = false;
				}
				$rootScope.quickCmd.cmd = value.PGM_CD;
				$rootScope.addQuick($rootScope.quickCmd.cmd);
			}
		}
	}
}


/*
 * 특수문자 체크 로직 업데이트
 */
function rtn_engnum_mix_chk(str)	{
	var pattern1 = /[0-9]/;	// 숫자
	var pattern2 = /[a-zA-Z]/;	// 문자
	var pattern3 = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;	// 특수문자
	if(!pattern1.test(str) || !pattern2.test(str) || !pattern3.test(str) || str.length < 8) {
		//alert("비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다.");
		return false;
	} else {
		return true;
	}

}

function rtn_engnum_chk(str){
	for( var i = 0; i <= str.length -1 ; i++ ){
  		if('a' <= str.charAt(i) && str.charAt(i) <= 'z' || str.charAt(i) >= '0' && str.charAt(i) <= '9'){

  		} else {
  			return false;
  		}
 	}
	return true;
}

function isEmpty(obj) {
    for(var prop in obj) {
        if(obj.hasOwnProperty(prop))
            return false;
    }
    return true;
}

String.prototype.ltrim = function() { return this.replace(/^[ ]*/g, ''); };
String.prototype.rtrim = function() { return this.replace(/[ ]*$/g, ''); };
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, '');};
Date.prototype.yyyymmdd = function() {
	  var mm = this.getMonth() + 1; // getMonth() is zero-based
	  var dd = this.getDate();

	  return [this.getFullYear(),
	          (mm>9 ? '' : '0') + mm,
	          (dd>9 ? '' : '0') + dd
	         ].join('');
	};

/**
 * String to Date
 * @param str
 * @returns
 */
function parse(str){
	var y = str.substr(0,4),
	m = str.substr(4,2) - 1,
	d = str.substr(6,2);
	var D = new Date(y,m,d);
	return (D.getFullYear() == y && D.getMonth() == m && D.getDate() == d) ? D : 'invalid date';
}


function generateParam(obj){
	var current = obj;
	var addPath = '?';
	for( var i in current){
		if(i == 'sortName'){
			addPath += 'sort=' + current[i] + ',' + current['sortOrder'] + '&';
			continue;
		}else if(i=='text'){
			addPath += i + '=' + current[i] + '&option=' + current['option'] + '&';
			continue;
		}else if(i=='sortOrder' || i=='option'){
			continue;
		}
		if(current[i] !='' && current[i] != undefined){
			addPath += i + '=' + current[i] + '&';
		}
	}
	return addPath.slice(0, -1);
}

/**
 * 뒤로가기 시 날짜 검색 셋팅
 * @param obj
 * @returns
 */
function initDate(obj){
	if(obj == undefined || obj == ""){
		return "";
	}
	return parse(obj);
}

/**
 * 뒤로가기 시 검색 초기데이터 셋팅
 * @param option
 * @param sortName
 * @param param
 * @returns
 */
function initSearch(option, sortName, sortOrder, param){

	var obj;
	if(param){
		if(param.sortName != sortName){
			obj = {
				option : option,
				sortName : sortName,
				sortOrder : sortOrder,
				page : 0
			};
		} else {
			obj = param;
		}
	} else {
		obj = {
				option : option,
				sortName : sortName,
				sortOrder : sortOrder,
				page : 0
			};
	}

	return obj;
}

/**
 * 상세페이지 이동 시 검색 데이터 셋팅
 * @param search
 * @returns
 */
function setSearch(search){
	var obj = {
		page : search.page,
		text : search.text,
		option : search.option,
		sortName : search.sortName,
		sortOrder : search.sortOrder
	}
	return obj;
}

/**
 * 공통 AJAX 리스트 통신
 * @param flag
 * @param search
 * @returns
 */
function httpGetList(http, scope, url, param ){
	if(!param){
		param = '';
	}
	http.get(url + param).success(
		function(data) {
			scope.list = angular.fromJson(data).content;
			scope.paging = {};
			scope.paging.current = angular.fromJson(data).number + 1;  // 1
			scope.paging.total = angular.fromJson(data).totalElements;  // 29
			scope.paging.begin = parseInt((scope.paging.current-1)/10) * 10 + 1  // 1 or -4  : 1  1~10, 11~20
			scope.paging.end = Math.min(scope.paging.begin + 9, angular.fromJson(data).totalPages);

			//10개씩
			scope.paging.prev = parseInt((scope.paging.current -1) / 10) * 10;
			scope.paging.next = scope.paging.prev + 11 ;
			scope.paging.last = parseInt( ((scope.paging.total - 1) /scope.search.size)+1);
		}
	);
}

function httpGetSubList(http, scope, url, param ){
	if(!param){
		param = '';
	}
	http.get(url + param).success(
		function(data) {
			scope.subList = angular.fromJson(data).content;
			scope.subPaging = {};
			scope.subPaging.current = angular.fromJson(data).number + 1;  // 1
			scope.subPaging.total = angular.fromJson(data).totalElements;  // 29
			scope.subPaging.begin = parseInt((scope.subPaging.current-1)/10) * 10 + 1  // 1 or -4  : 1  1~10, 11~20
			scope.subPaging.end = Math.min(scope.subPaging.begin + 9, angular.fromJson(data).totalPages);

			scope.subPaging.prev = parseInt((scope.paging.current -1) / 10) * 10;
			scope.subPaging.next = scope.subPaging.prevSub + 11 ;
			scope.subPaging.last = parseInt((scope.subPaging.total/10)+1);
		}
	);
}


/**
 * Modal confirm 콜백 공통
 * @param message
 * @param callback
 * @returns
 */
function confirmCall(massage, callback){

	$('#confirmModal').modal({
		show:true,
		backdrop: true,
	    keyboard: false,
	});

	$('#confirmModal .modal-body').html("<p>" + massage + "</p>");

	$("#confirmModal").find("#okBtn").off().on("click", function(){
		$('#confirmModal').modal('hide');
		if (callback) callback(true);
	});
	$("#confirmModal").find("#cancelBtn").off().on("click", function(event){
		$('#confirmModal').modal('hide');
		if (callback) callback(false);
	});
	$("#confirmModal").find("#closeBtn").off().on("click", function(event){
		$('#confirmModal').modal('hide');
		if (callback) callback(false);
	});
}

/**
 * Common Moal 공통 모달
 * @param massage
 * @returns
 */
function modalCall(massage, callback){
	$('#commonModal').modal({
		show:true,
		backdrop: true,
		keyboard: false,
	});
	$("#commonModal").find("#modalId").html("<p>" + massage + "</p>");
	$("#commonModal").find("#okBtn").off().on("click", function(event){
		$('#commonModal').modal('hide');
		if (callback) callback(true);
	});
}

/**
 * yyyy-MM-dd 변경
 * @param date
 * @returns
 */
function formatDate(date) {
	var d = new Date(date),
	month = '' + (d.getMonth() + 1),
	day = '' + d.getDate(),
	year = d.getFullYear();

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return [year, month, day].join('');
}
//yyyyMMdd HH:mm:ss
function formatDate2(date) {
	var tostr = date.toString()
	var month = '' + (date.getMonth() + 1);
	var tostr = tostr.split(' ');

	if (month.length < 2) month = '0' + month;
	var result = tostr[3]+month+tostr[2] +' '+tostr[4];
	return result;
}
//yyyyMMdd
function formatDate3(date) {
	var tostr = date.toString()
	var month = '' + (date.getMonth() + 1);
	var tostr = tostr.split(' ');

	if (month.length < 2) month = '0' + month;
	var result = tostr[3]+month+tostr[2];
	return result;
}
//yyyy-MM-dd HH:mm:ss
function formatDate4(date) {
	var tostr = date.toString()
	var month = '' + (date.getMonth() + 1);
	var tostr = tostr.split(' ');

	if (month.length < 2) month = '0' + month;
	var result = tostr[3]+'-'+month+'-'+tostr[2] +' '+tostr[4];
	return result;
}


//공통코드 찾기 : scope,http 먼저 추가후 찾을 공통코드 입력
function commonCode() {
	scope = arguments[0];
	http = arguments[1];
	var data = {};
	var list = new Array();
	var size = arguments.length;
	for(var i=2 ; i< size; i++){
		list.push(arguments[i]);
	}
	data.codeList = list;
	http({
		method : 'GET',
		url : "/commonCode",
		params : data
	}).success(function(data){
		for(var i in data){
			eval('scope.'+data[i].groupId);
			scope[data[i].groupId] = data[i];
		}
	});
};

//테이블을 list<map>형식으로 변환
function tableData(id){
	id = "#"+ id;
	var myRows = [];
	var $headers = $(id+" th");
	var $rows = $(id+" tbody tr").each(function(index) {
	     $cells = $(this).find("td");
	     myRows[index] = {};
	     $cells.each(function(cellIndex) {
	    	 if(!sc_chk($(this).html(), 0, 1)){
	    		 myRows[index][$($headers[cellIndex]).html()] = $(this).html();
	    	 }
	     });
	});
	return myRows;
}

function sc_chk(str, start, end){
	if(start!=null && end!=null){
		str = str.substr(start, end);
	}
	var pattern = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;	// 특수문자
	if(pattern.test(str)) {
		return true;
	} else {
		return false;
	}

}

//모달 알림창
function modalAlert(uibModal, title, body){
	$ctrl = {};
	$ctrl.title = title;
	$ctrl.body = body;
	var modalInstance = uibModal.open({
		templateUrl: "modal/alert",
		controller: "modalController",
		controllerAs: '$ctrl'
	});
}

//모달 확인창
function modalCheck($uibModal, title, body, cb){
	$ctrl = {};
	$ctrl.title = title;
	$ctrl.body = body;

	var modalInstance = $uibModal.open({
		templateUrl: "modal/check",
		controller: "modalController",
		controllerAs: '$ctrl'
	});
	modalInstance.result.then(function() {
		cb();
		//return true;
	}, function () {
		//return false;
	});
}


$.fn.rowspan = function(colIdx, isStats) {
	return this.each(function(){
		var that;
		$('tr', this).each(function(row) {
			$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) {
				if ($(this).html() == $(that).html() && (!isStats || isStats && $(this).prev().html() == $(that).prev().html())) {
					rowspan = $(that).attr("rowspan") || 1;
					rowspan = Number(rowspan)+1;
					$(that).attr("rowspan",rowspan);
					$(this).hide();
				} else {
					that = this;
				}
				that = (that == null) ? this : that;
			});
		});
	});
};


function logout(http, rootScope, location) {
	deleteCookie('autoLogin');
	http({
		method: 'POST',
		url: '/member/logout',
		headers: {'Content-Type': 'application/json; charset=utf-8'}
	}).success(function(data, status, headers, config) {
		if(status == 200) {
			rootScope.authenticated = false;
			location.url("/");
			sessionStorage.clear();
		} else {
			modalCall("에러발생");
		}
	}).error(function(data, status, headers, config) {
		rootScope.authenticated = false;
		location.url("/");
	});
};


//테이블 tr 선택효과
function selectTr(td){
	const tr = td.parent();
	const table = td.parent().parent();
	table.children().removeClass('bg-secondary');
	table.children().removeClass('font-weight-bold');
	tr.addClass('bg-secondary');
	tr.addClass('font-weight-bold');
}

//테이블 tr에 class 삭제
function tableTrDel(tableId){
	$("#"+tableId).children().children().removeClass('bg-secondary');
	$("#"+tableId).children().children().removeClass('font-weight-bold');
}

//테이블 td에 색상 삭제
function tableTdDel(tableId){
	$("#"+tableId).children().children().children().css('background', '');
	$("#"+tableId).children().children().children().css('color', '');
}

//테이블 td에 색상 추가
function selectTd(td){
	var table = td.parent().parent();
	table.children().children().css('background', '');
	table.children().children().css('color', '');
	td.css('background', 'lightsteelblue');
	td.css('color', 'white');
}

//코드정리
function code(rootScope) {
	rootScope.grade = []; // 권한목록
	rootScope.store = []; // 매장목록
	rootScope.device = []; //장비구분
	rootScope.workM = []; // 작업대분류
	rootScope.workS = []; // 작업소분류
	rootScope.prdSize = []; // 상품사이즈
	rootScope.deviceStat = []; // 장비상태
	rootScope.commType = []; // 통신구분
	for (value of rootScope.commCode) {
		var bcd = value.commCd.substr(0, 2);
		var mcd = value.commCd.substr(2, 2);
		var scd = value.commCd.substr(4, 2);
		if (bcd == '01' && mcd == '01' && scd != '00') { // 사용자 권한
			rootScope.grade.push(value);
		} else if (bcd == '01' && mcd == '02' && scd != '00') { // 매장
			rootScope.store.push(value);
		} else if (bcd == '02' && mcd == '01' && scd != '00') { // 장비구분
			rootScope.device.push(value);
		} else if (bcd == '06' && mcd != '00' && scd == '00') { // 작업중분류
			rootScope.workM.push(value);
		} else if (bcd == '06' && scd != '00') { // 작업소분류
			rootScope.workS.push(value);
		} else if (bcd == '05' && scd != '00') { // 장비구분
			rootScope.prdSize.push(value);
		} else if (bcd == '02' && mcd == '03' && scd != '00') { // 장비상태
			rootScope.deviceStat.push(value);
		} else if (bcd == '02' && mcd == '04' && scd != '00') { // 장비상태
			rootScope.commType.push(value);
		}
	}
}

//코드를 코드명으로 변환
function codeToNm(code, data) {
	for(value of data){
		if(value.commCd == code){
			return value.commCdNm;
		}
	}
};

//자릿수 채우기
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}

//쿠키사용
function setCookie(name, value, exp) {
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}


function getCookie(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
}


function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}


