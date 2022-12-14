package com.systemk.Util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultUtil {

	// Common Result
	public static Map<String, Object> setCommonResult(String resultCode, String resultMessage) {

		Map<String, Object> obj = new LinkedHashMap<String, Object>();

		obj.put("resultCode", resultCode);
		obj.put("resultMessage", resultMessage);

		return obj;
	}

	// Common Object Result
	public static Map<String, Object> setCommonResult(String resultCode, String resultMessage, Object tempObj){

	    Map<String, Object> obj = new LinkedHashMap<String, Object>();
	    Map<String, Object> detailObj = new LinkedHashMap<String, Object>();

	    obj.put("resultCode", resultCode);
	    obj.put("resultMessage", resultMessage);
	    obj.put("resultData", tempObj);

	    return obj;
	}

	// Common List Result
	public static Map<String, Object> setCommonResult(String resultCode, String resultMessage, List<Object> tempList){

	    Map<String, Object> obj = new LinkedHashMap<String, Object>();
	    Map<String, Object> detailObj = new LinkedHashMap<String, Object>();

	    obj.put("resultCode", resultCode);
	    obj.put("resultMessage", resultMessage);
	    obj.put("resultData", tempList);

	    return obj;
	}

	// Common Result Error
	public static Map<String, Object> setCommonResult(String resultCode, String resultMessage, String errorMessage) {

		Map<String, Object> obj = new LinkedHashMap<String, Object>();

		obj.put("resultCode", resultCode);
		obj.put("resultMessage", resultMessage);
		obj.put("errorMessage", errorMessage);

		return obj;
	}
}
