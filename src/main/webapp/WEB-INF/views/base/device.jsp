<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated">

    <div class="container-fluid d-flex justify-content-center" style="padding:0!important;" ng-show="!mainPage">
        <div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;">
            <h4><a class="menuName" href="" ng-click="reload()">{{currentMenu.PGM_NM}}</a></h4>
        </div>
        <ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;">
            <li><a href="">{{currentMenu | menuGroup}}</a></li> &nbsp;>&nbsp;
            <li><a href="" >{{currentMenu.PGM_NM}}</a></li>
        </ol>
    </div>
    <!--상단 검색 보드-->
    <div class="d-flex container-fluid body-custom flex-column" style="width:100%;min-height: 80px;padding:5px 3%;">

        <!-- 검색 -->
        <div class="d-flex justify-content-center" style="width: 100%;">

            <select class="form-control" style="width:200px;margin: 10px;height: 40px;">
                <option>매장</option>
            </select>

            <select class="form-control" style="width:200px;margin: 10px;height: 40px;">
                <option>구분</option>
            </select>

            <select class="form-control" style="width:200px;margin: 10px;height: 40px;">
                <option>운용상태</option>
            </select>

            <select class="form-control" style="width:200px;margin: 10px;height: 40px;">
                <option>통신방식</option>
            </select>

            <select class="form-control" style="width:200px;margin: 10px;height: 40px;">
                <option>상품분류</option>
            </select>
            <button class="btn btn-outline-secondary" style="width:70px;margin:10px 30px;">검색</button>
        </div>
    </div>

    <section class="d-flex justify-content-center">

        <!--contents-->
        <div class="container-fluid body-custom" style="width:100%;">
            <div class="body-contents ">

                <div>
                    <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
                        <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 디바이스 추가/수정</span>
                        <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                        <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                    </div>

                    <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                        <tr>
                            <th style="width:10%;height:40px">매장명</th>
                            <td style="width:15%;padding: 0;"><input type="text" class="form-control" placeholder="논현본점" ></td>
                            <th style="width:10%">구분</th>
                            <td style="width:15%"><input type="text" class="form-control" placeholder="고정형리더기"></td>
                            <th style="width:10%">Serial Num</th>
                            <td style="width:15%"><input type="text" class="form-control" placeholder="2523464363"></td>
                            <th style="width:10%">Model Num</th>
                            <td style="width:15%"><input type="text" class="form-control" placeholder="afwe33452df"></td>
                        </tr>
                        <tr>
                            <th style="height:40px">등록IP</th>
                            <td><input type="text" class="form-control" placeholder="112.343.321.33"></td>
                            <th>MAC</th>
                            <td><input type="text" class="form-control" placeholder="Ed:34:AD:32:EA"></td>
                            <th>운영별칭</th>
                            <td><input type="text" class="form-control" placeholder="매장 게이트1"></td>
                            <th>설치일시</th>
                            <td><input type="text" class="form-control" placeholder="2021.04.15"></td>
                        </tr>
                        <tr>
                            <th style="height:40px">운용상태</th>
                            <td><input type="text" class="form-control" placeholder="운영"></td>
                            <th>통신방식</th>
                            <td><input type="text" class="form-control" placeholder="시리얼(RS232)"></td>
                            <th>제조사</th>
                            <td><input type="text" class="form-control" placeholder="에일리언아시아"></td>
                        </tr>

                    </table>
                </div>



                <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 디바이스 목록</span>
                </div>

                <!--테이블 상단 구성-->
                <div class="d-flex" style="margin:10px 0;">
                    <div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;">
                        <select class="custom-select" ng-model="sizeValue" ng-change="pageSize(sizeValue)" style="width:150px;margin-right: 10px;">
                            <option value="">10개씩 보기</option>
                            <option value="20">20개씩 보기</option>
                            <option value="50">50개씩 보기</option>
                            <option value="total" style="color: red;">전체보기</option>
                        </select>

                        <h6 class="align-self-center">TOTAL ( 10 )</h6>
                    </div>

                    <button class="btn btn-secondary p-2 table-top-btn" ng-click="assetUpdate('DEL')">삭제</button>
                </div>
                <!-- 테이블 생성 -->
                <div class="table-box">
                    <table class="table custom-table-1 table-hover text-center custom-align-middle" style="min-width:1100px;">
                        <thead>
                        <tr class="pointer">
                            <th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}})"></th>
                            <th>No</th>
                            <th>매장명</th>
                            <th>구분</th>
                            <th>Serial Num</th>
                            <th>Model Num</th>
                            <th>등록IP</th>
                            <th>MAC</th>
                            <th>운영별칭</th>
                            <th>설치일시</th>
                            <th>운용상태</th>
                            <th>통신방식</th>
                            <th>제조사</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="(key, value) in [1,2,3,4,5,6,7,8,9,10]" class="pointer" ng-init="value.isSelected = false;">
                            <td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox({{value.assetManagementSeq}}, !{{value.isSelected}})" ></td>
                            <td>{{value}}</td>
                            <td>논현본점</td>
                            <td>고정형 리더기</td>
                            <td>3252232342</td>
                            <td style="text-align: left;">bse324sd34115</td>
                            <td style="text-align: left;">192.168.0.112</td>
                            <td style="text-align: left;">ED:35:GR:98:E4</td>
                            <td>PDA1</td>
                            <td>2021.04.15</td>
                            <td>운영</td>
                            <td>시리얼(RS232)</td>
                            <td>에일리언아시아</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="row d-flex justify-content-center">
                    <!-- 네비게이션 바 -->
                    <nav class=" text-center" >
                        <ul class="pagination">
                            <li class="page-item">
                                <a href="" aria-label="Previous" ng-click="goPage(begin)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
                            </li>
                            <li class="page-item">
                                <a href="" aria-label="Previous" ng-click="goPage(current - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
                            </li>
                            <%--                            <li ng-repeat="pageNum in [begin, end] | makeRange" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>--%>
                            <li ng-repeat="pageNum in [1,2,3,4,5]" class="page-item" ng-class="{'active-page' : current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
                            <li class="page-item">
                                <a href="" aria-label="Next" ng-click="goPage(current + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
                            </li>
                            <li class="page-item">
                                <a href="" aria-label="Next" ng-click="goPage(end)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
                            </li>
                        </ul>
                    </nav>

                </div>
            </div>
        </div>
    </section>
</div>
</html>


