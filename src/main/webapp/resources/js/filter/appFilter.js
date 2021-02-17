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

app.filter('makeRange2', function() {
    return function(current, total, end) {
    	//5개씩 보기
//    	var end = parseInt( total/10 ) + 1;
//     	var current = parseInt(current-1);
//     	var five = parseInt(current / 5) ;
//     	var last = five*5 + 5;
//     	last = last > end ? end : last ;
//     	result = [];
//     	for(var i = five * 5 + 1 ; i <= last; i++ ){
//     		result.push(i);
//     	}
//     	return result;
    	
    	//10개씩 보기
    	var end = parseInt( total/10 ) + 1;
        var current = parseInt(current-1);
        var ten = parseInt(current / 10) ;
        var last = ten*10 + 10;
        last = last > end ? end : last ;
        result = [];
        for(var i = ten * 10 + 1 ; i <= last; i++ ){
            result.push(i);
        }
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

//날짜 형식 변경 : 오라클에 String으로 저장된 값 변경용, DB수정된다면 뺄거
app.filter('StringToDateForm', function() {
    return function(st) {
        return st.substr(0,4)+'/'+st.substr(4,2)+'/'+st.substr(6,2)+' ' +st.substr(8,2)+':'+st.substr(10,2)+':'+st.substr(12,2);
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
    return function(code, data, def) {
    	if(data[code] != null){
    		return data[code].codeName;
    	}else if(def){
    		return def;
    	}else{
    		return "empty";
    	}
    };
});
