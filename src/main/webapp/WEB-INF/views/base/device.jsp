<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">


<div ng-show="authenticated" style="width:90%;margin:0 auto;">

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

            <p style="margin:20px 10px;">운용매장</p>
            <select class="form-control" ng-model="searchGroup.storeCd" style="width:200px;margin: 10px;height: 40px;">
                <option value="">매장</option>
                <option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
            </select>

            <p style="margin:20px 10px;">장비구분</p>
            <select class="form-control" ng-model="searchGroup.deviceGub" style="width:200px;margin: 10px;height: 40px;">
                <option value="">구분</option>
                <option ng-repeat="value in device" value="{{value.commCd}}">{{value.commCdNm}}</option>
            </select>

            <p style="margin:20px 10px;">운용상태</p>
            <select class="form-control" ng-model="searchGroup.deviceStat" style="width:200px;margin: 10px;height: 40px;">
                <option value="">운용상태</option>
                <option ng-repeat="value in deviceStat" value="{{value.commCd}}">{{value.commCdNm}}</option>
            </select>

            <button class="btn btn-outline-secondary" ng-click="searchBtn('group')" style="width:70px;margin:10px 30px;">검색</button>
        </div>
    </div>

    <section class="d-flex justify-content-center">

        <!--contents-->
        <div class="container-fluid body-custom" style="width:100%;">
            <div class="body-contents ">

                <form ng-submit="formSave()">
                    <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
                        <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 디바이스 추가/수정</span>
                        <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : es.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('deviceList')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                        <button class="p-2 btn btn-outline-secondary" type='submit' style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                    </div>

                    <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                        <tr>
                            <th style="width:10%;height:40px"><span style="color:red;">*</span> 매장명</th>
                            <td style="width:15%">
                                <select class="form-control" ng-model="form.storeCd" ng-required="true">
                                    <option value="">매장선택</option>
                                    <option ng-repeat="value in store" value="{{value.commCd}}">{{value.commCdNm}}</option>
                                </select>
                            </td>
                            <th style="width:10%"><span style="color:red;">*</span> 구분</th>
                            <td style="width:15%">
                                <select class="form-control" ng-model="form.deviceGub" ng-required="true">
                                    <option value="">장비선택</option>
                                    <option ng-repeat="value in device" value="{{value.commCd}}">{{value.commCdNm}}</option>
                                </select>
                            <th style="width:10%"><span style="color:red;">*</span> Serial Num</th>
                            <td style="width:15%"><input type="text" class="form-control" ng-model="form.serialNo" ng-required="true"></td>
                            <th style="width:10%">Model Num</th>
                            <td style="width:15%"><input type="text" class="form-control" ng-model="form.modelNo"></td>
                        </tr>
                        <tr>
                            <th style="height:40px">등록IP</th>
                            <td><input type="text" class="form-control" ng-model="form.setIp"></td>
                            <th>MAC</th>
                            <td><input type="text" class="form-control" ng-model="form.macNo"></td>
                            <th>운영별칭</th>
                            <td><input type="text" class="form-control" ng-model="form.setName"></td>
                            <th>설치일시</th>
                            <td>
                                <div class="input-group">
                                    <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="inView.setDate" is-open="openSetDate" datepicker-options=" {'showWeeks':false}" close-text="Close" ng-readonly="true"/>
                                    <span class="input-group-append" >
                                        <button type="button" class="btn btn-secondary" ng-click="openSetDate = true"> <i class="xi-calendar"></i></button>
                                    </span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th style="height:40px">운용상태</th>
                            <td>
                                <select class="form-control" ng-model="form.deviceStat">
                                    <option value="">운용상태</option>
                                    <option ng-repeat="value in deviceStat" value="{{value.commCd}}">{{value.commCdNm}}</option>
                                </select>
<%--                                <input type="text" class="form-control" ng-model="form.deviceStat"></td>--%>
                            <th>통신방식</th>
                            <td>
                                <select class="form-control" ng-model="form.commType">
                                    <option value="">통신구분</option>
                                    <option ng-repeat="value in commType" value="{{value.commCd}}">{{value.commCdNm}}</option>
                                </select>
<%--                                <input type="text" class="form-control" ng-model="form.commType">--%>
                            </td>
                            <th>제조사</th>
                            <td><input type="text" class="form-control" ng-model="form.manufact"></td>
                            <th>등록자</th>
                            <td><input type="text" class="form-control" ng-model="form.regId" readonly></td>
                        </tr>
                    </table>
                </form>



                <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 디바이스 목록</span>
                </div>

                <!--테이블 상단 구성-->
                <div class="d-flex" style="margin:10px 0;">
                    <div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;">
                        <select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;">
                            <option value="10">10개씩 보기</option>
                            <option value="20">20개씩 보기</option>
                            <option value="50">50개씩 보기</option>
                        </select>

                        <h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6>
                    </div>

                    <button class="btn btn-danger p-2 table-top-btn" ng-click="tableBtn('delete')">삭제</button>
                </div>
                <!-- 테이블 생성 -->
                <div class="table-box">
                    <table id="deviceList" class="table custom-table-1 table-hover text-center custom-align-middle table table-striped-odd" style="min-width:1100px;">
                        <thead>
                        <tr class="pointer">
                            <th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'deviceSeq')"></th>
                            <th>No</th>
                            <th>매장명</th>
                            <th>구분</th>
                            <th>Serial Num</th>
                            <th>Model Num</th>
                            <th>등록IP</th>
                            <th>MAC</th>
                            <th>운영별칭</th>
                            <th>설치일</th>
                            <th>운용상태</th>
                            <th>통신방식</th>
                            <th>제조사</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="(key, value) in list" class="pointer" ng-init="value.isSelected = false;">
                            <td style="padding:25px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox(!{{value.isSelected}}, {{value.deviceSeq}})" ></td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.deviceSeq}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.storeNm}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.deviceGub | code: commCode}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.serialNo}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.modelNo}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.setIp}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.macNo}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.setName}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.setDate | date:'yyyy-MM-dd'}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.deviceStat | code: commCode}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.commType | code: commCode}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.manufact}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="row d-flex justify-content-center">
                    <!-- 네비게이션 바 -->
                    <nav class=" text-center" >
                        <ul class="pagination">
                            <li class="page-item" ng-if="paging.current > 10">
                                <a href="" aria-label="Previous" ng-click="goPage(1)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
                            </li>
                            <li class="page-item" ng-if="paging.current > 10">
                                <a href="" aria-label="Previous" ng-click="goPage(paging.begin - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
                            </li>
                            <li ng-repeat="pageNum in [paging.begin, paging.end] | makeRange" class="page-item" ng-class="{'active-page' : paging.current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
                            <li class="page-item" ng-if="paging.end != paging.last">
                                <a href="" aria-label="Next" ng-click="goPage(paging.end + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a>
                            </li>
                            <li class="page-item" ng-if="paging.end != paging.last">
                                <a href="" aria-label="Next" ng-click="goPage(paging.last)" class="page-link"><span aria-hidden="true">&raquo;</span></a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </section>
</div>
</html>


