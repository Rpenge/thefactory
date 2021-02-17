package com.systemk.ams.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;



public interface ChartService {

	
	
	public List divCount() throws Exception;
	
	public Map<String, Integer> statusCount() throws Exception;

	public Map<String, Integer> countNewOrder() throws Exception; 
}
