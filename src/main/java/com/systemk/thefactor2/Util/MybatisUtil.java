package com.systemk.thefactor2.Util;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MybatisUtil {

    private HashMap tableSearch;
    private List search;
    private List sort;

    public MybatisUtil() {
        this.tableSearch = new HashMap();
        this.search = new ArrayList();
        this.sort = new ArrayList();
        this.tableSearch.put("list", search);
        this.tableSearch.put("sort", sort);
    }

    public void setTable(String tableName){
        tableSearch.put("table", tableName);
    }

    public void addEqual(String column, String findStr){
        String str = column + " = '"+ findStr+"'";
        search.add(str);
    }

    public void addLike(String column, String findStr){
        String str = column + " like '%"+ findStr+"%'";
        search.add(str);
    }

    public void addBetween(String column, String start, String end){
        String str = column + " between " + start + " and " + end;
        search.add(str);
    }

    public HashMap getTableSearch(){
        return tableSearch;
    }

    public void setLimit(int start, int end){
        this.tableSearch.put("start", start);
        this.tableSearch.put("end", end);
    }

    public void setSort(String sortName){
        if(sortName == null){
            return;
        }
        String[] sortSep = sortName.split(",");
        String str = StringUtil.camelToSnake(sortSep[0]) + " " + sortSep[1];
        this.sort.add(str);
    }

    private List content; // 조회 리스트
    private int page;  //number
    private int totalElements;  //totalElements
    private int totalPages; //totalPages
    private int startNumber; // 조회 시작 번호
    private int size;

    public void pager(){ //default
        this.page = 0;
        this.size = 10;
        this.startNumber = 0;
    }

    public void pager(Map<String, String> search){ //map에서 해당 데이터 찾아서 적용
        this.page = search.get("page") == null ? 0 : Integer.parseInt(search.get("page"));  //
        this.size = search.get("size") == null ? 10 : Integer.parseInt(search.get("size"));
        this.startNumber = size * page;
        setLimit(startNumber , size);
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        this.totalPages =  (int)Math.ceil((double) totalElements / size);
    }

    public Map<String, Object> getList(){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("content", this.content);
        result.put("totalElements", this.totalElements);
        result.put("totalPages", this.totalPages);
        result.put("number", this.page);
        return result;
    }
}
