package com.systemk.thefactor2.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtil {


	public static Map ConvertObjectToMap(Object obj){
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			Map resultMap = new HashMap();
			for(int i=0; i<=fields.length-1;i++){
				fields[i].setAccessible(true);
				resultMap.put(fields[i].getName(), fields[i].get(obj));
			}
			return resultMap;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map<String, String>> ConvertListObjectToMap(Object obj){
		List<Object> list = (List<Object>) obj;
		List<Map<String, String>> resultList = new ArrayList<Map<String,String>>();

		try {
			for(int i=0;i<list.size();i++) {
				Field[] fields = list.get(i).getClass().getDeclaredFields();
				Map resultMap = new HashMap();

				for(int j=0; j<fields.length;j++){

					fields[j].setAccessible(true);
					if(fields[j].get(list.get(i)) != null) {
						resultMap.put(fields[j].getName(), fields[j].get(list.get(i)).toString());
					}else {
						resultMap.put(fields[j].getName(), "");
					}
				}
				resultList.add(resultMap);
			}
			return resultList;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
