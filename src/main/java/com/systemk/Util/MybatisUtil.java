package com.systemk.Util;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MybatisUtil {

    private HashMap<String,Object> tableSearch;    //테이블 조회 맵
    private List<String> search;            //검색 리스트
    private List content;           //조회 리스트
    private int page;               //number
    private int totalElements;      //totalElements
    private int totalPages;         //totalPages
    private int start;              //조회 시작 번호
    private int size;               //페이지 사이즈

    public MybatisUtil() {
        this.tableSearch = new HashMap<String,Object>();
        this.search = new ArrayList<String>();
        this.content = new ArrayList();
        this.page = 0;
        this.size = 10;
        this.start = 0;
        this.tableSearch.put("list", search);
        this.tableSearch.put("start", start);
        this.tableSearch.put("size", size);
    }

    public void setTable(String tableName){
        tableSearch.put("table", tableName);
    }

    public void leftJoin(String tableName, String join1, String join2){
        String str = "left outer join " + tableName + " on "+ tableSearch.get("table") +"." + join1 +" = " + tableName+"."+ join2;
        tableSearch.put("joinTable", str);
    }

    public void addStr(String key, String value){
        tableSearch.put(key, value);
    }

    public void addEqual(String column, String findStr){
        String str = "AND " + column + " = '"+ findStr+"'";
        search.add(str);
    }

    public void notEqual(String column, String findStr){
        String str = "AND " + column + " != '"+ findStr+"'";
        search.add(str);
    }

    public void addLike(String column, String findStr){
        String str = "AND " +column + " like '%"+ findStr+"%'";
        search.add(str);
    }
    
    /*210903 추가*/
    public void addAndORLike(String column1, String column2, String column3, String findStr){
        String str = "AND(" +column1 + " like '%"+ findStr+"%'";
        str += " OR " +column2 + " like '%"+ findStr+"%'";
        str += " OR " +column3 + " like '%"+ findStr+"%')";
        search.add(str);
    }

    public void addWord(List<String> columnList, String findStr){
        String str = "AND ( " ;
        for(int i=0 ; i < columnList.size(); i++){
            if(i==0){
                str += columnList.get(i) + " like '%"+ findStr +"%' ";
                continue;
            }else if(i == (columnList.size() - 1)){
                str += "OR " + columnList.get(i) + " like '%"+ findStr +"%' )";
                break;
            }else{
                str += "OR " + columnList.get(i) + " like '%"+ findStr +"%' ";
            }
        }
        search.add(str);
    }

    public void addStartLike(String column, String findStr){
        String str = "AND " +column + " like '"+ findStr+"%'";
        search.add(str);
    }

    public void addORLike(String column, String findStr){
        String str = "OR " +column + " like '%"+ findStr+"%'";
        search.add(str);
    }

    public void addBetween(String column, String start, String end){
        String str = "AND " +column + " between " + start + " and " + end;
        search.add(str);
    }

    public HashMap getTableSearch(){
        return this.tableSearch;
    }

    public void setSort(String sort, String direct){
        this.tableSearch.put("sort", sort);
        this.tableSearch.put("direct", direct);
    }

    public void pager(Map<String, String> search){ //map에서 해당 데이터 찾아서 적용
        this.page = search.get("page") == null ?  0 : Integer.parseInt(search.get("page"));
        this.size = search.get("size") == null ? 10 : Integer.parseInt(search.get("size"));
        this.start = size * page;
    }

    public void setSize(int size){
        this.size = size;
        this.start = size * this.page;
        this.tableSearch.put("start", start);
        this.tableSearch.put("size", size);
        this.totalPages =  (int)Math.ceil((double) totalElements / size);
    }

    public void setPage(int page){
        this.page = page;
        this.start = this.size * page;
        this.tableSearch.put("start", start);
        this.tableSearch.put("size", size);
    }

    public void setTotal(){
        this.tableSearch.put("total", true);
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
        result.put("size", this.size);
        return result;
    }
}
