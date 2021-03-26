app.filter('makeRange', function() {
    return function(input) {
        var lowBound, highBound;
        switch (input.length) {
        case 1:
            lowBound = 0;
            highBound = parseInt(input[0]) - 1;
            break;
        case 2:
            lowBound = parseInt(input[0]);
            highBound = parseInt(input[1]);
            break;
        default:
            return input;
        }
        var result = [];
        for (var i = lowBound; i <= highBound; i++)
            result.push(i);
        return result;
    };
});


app.filter('stringToHex', function () {

	return function(input) {
		return Number(input).toString(16);
	};
});

app.filter('hexToString', function () {
	return function(input) {
		var hex  = input.toString();
		var str = '';
		for (var n = 0; n < hex.length; n += 2) {
			str += String.fromCharCode(parseInt(hex.substr(n, 2), 16));
		}
		return str;
	};
});

app.filter('percentage', ['$filter', function ($filter) {
	 return function (input, decimals) {

		var calc = $filter('number')(input * 100, decimals);

		if(calc == ""){
			calc = "0.00";
		}
	    return calc + '%';
	 };
}]);

app.filter('myLimitTo', [function(){
    return function(obj, limit){
        var keys = Object.keys(obj);
        if(keys.length < 1){
            return [];
        }

        var ret = new Object,
        count = 0;
        angular.forEach(keys, function(key, arrayIndex){
           if(count >= limit){
                return false;
            }
            ret[key] = obj[key];
            count++;
        });
        return ret;
    };
}]);

//날짜 형식 변경 : 오라클에 String으로 저장된 값 변경
app.filter('StringToDateForm', function() {
    return function(st) {
        return st.substr(0,4)+'/'+st.substr(4,2)+'/'+st.substr(6,2)+' ' +st.substr(8,2)+':'+st.substr(10,2)+':'+st.substr(12,2);
    };
});

//상태코드에 따른 값
app.filter('dateFormCustom', function() {
    return function(date, ext) {
        if(date == null){
            return;
        }
        return date.substr(0,4)+ext+date.substr(4,2)+ext+date.substr(6,2)
    };
});

//상태코드에 따른 값
app.filter('StatusChange', function() {
    return function(status_no) {
    	switch (status_no){
    	case '1': return '사용';
    	case '2': return '사용불가';
    	}
    };
});

app.filter('code', function() {
    return function(code, data) {
        if(!data){
            return null;
        }
    	for(value of data){
    	    if(value.commCd == code){
    	        return value.commCdNm;
            }
        }
    };
});

//상태코드에 따른 값
app.filter('menuGroup', function() {
    return function(data) {
        switch (data.GROUP_CD){
            case 'SYSTEM': return '시스템관리';
            case 'BASE': return '기초정보관리';
            case 'INOUT': return '입출고관리';
            case 'INVEN': return '재고실사';
            case 'STOCK': return '재고현황';
        }
    };
});

//상태코드에 따른 값
app.filter('YnWord', function() {
    return function(data, command) {
        switch (command){
            case 1: {
                if(data == 'Y') return '사용';
                else if(data == 'N') return '탈퇴';
            };
            case 2: {
                if(data == 'Y') return '사용';
                else if(data == 'N') return '미사용';
            };
        }
    };
});

//값이 없으면 0
app.filter('zeroCheck', function() {
    return function(data) {
        if(data) {
            return data;
        }
        else{
            return 0;
        }

    };
});



