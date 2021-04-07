<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">

<div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;">

    <!-- left body -->
    <div  style="width:20%;margin:3px;">
        <!--메뉴-->
        <div class="d-flex flex-column left-body">
            <%--대분류 신규, 저장 input 영역 시작--%>
            <form ng-submit="bCodeSave()">
                <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 대분류</span>
                    <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : form.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('list')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                    <button class="p-2 btn btn-outline-secondary" type="submit" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                </div>
                <%--{{commCode}}--%>
                <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                    <tr>
                        <th>코드</th>
                        <td style="width:70%;height: 40px;">
                            <input type="text" class="form-control" ng-model="form.bCommCd" placeholder="대분류코드">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:30%;">코드명</th>
                        <td style="width:70%;height: 40px;">
                            <input type="text" class="form-control" ng-model="form.bCommCdNm" placeholder="대분류코드명">
                        </td>
                    </tr>
                    <tr>
                        <th>사용여부</th>
                        <td>
                            <select class="form-control" ng-model="form.bUseYn" ng-init="form.bUseYn='Y'">
                                <option value="Y" >사용</option>
                                <option value="N">미사용</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
            <%--대분류 신규, 저장 input 영역 끝--%>
            <%--대분류 목록 영역 시작--%>
            <table class="table custom-table-1 table-hover text-center table-striped custom-align-middle" style="width: 100%;text-align: center;font-size: medium;min-height: 120px;">
                <thead>
                    <tr>
                        <th style="width:30%" ng-click="sort('')">코드</th>
                        <th style="width:70%;" ng-click="sort('')">코드명</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="value in commCode" class="pointer" ng-init="value.isSelected = false;" ng-if="value.codeLevel=='B'">
                        <td ng-click="bclick(value)" onclick="selectTr($(this))" style="height: 50px;">{{value.commCd}}</td>
                        <td ng-click="bclick(value)" onclick="selectTr($(this))">{{value.commCdNm}}</td>
                    </tr>
                </tbody>
            </table>

            <%--대분류 목록 영역 끝--%>
        </div>
    </div>

    <!--right body-->
    <div style="width:80%;padding: 10px;">

        <!--현재 메뉴명 : 메인페이지에서 숨김-->
        <div class="container-fluid d-flex justify-content-center" style="padding:0!important;">
            <div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;">
                <h4><a class="menuName" href="" ng-click="reload()">{{currentMenu.PGM_NM}}</a></h4>
                <%--							<h6 style="color:gray;">재고현황조회</h6>--%>
            </div>
            <ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;">
                <li><a href="#">기초정보관리</a></li> &nbsp;>&nbsp;
                <li><a href="#">공통코드 관리</a></li>
            </ol>
        </div>
        <div>
        </div>

        <!--contents-->
        <div class="body-custom" style="width:100%;">

            <div class="d-flex justify-content-center">
                <div style="width: 48%;margin:10px;">
                    <%--중분류 신규, 저장 input 영역 시작--%>
                    <form ng-submit="formSave1()">
                    <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
                        <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 중분류</span>
                        <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : es.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('commMList')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                        <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                    </div>

                    <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                        <tr>
                            <th style="width:20%;height:40px">대분류코드</th>
                            <td style="width:30%;padding: 0;">
                                <input type="text" class="form-control" ng-model="form.bCommCd" placeholder="대분류코드" readonly>
                            </td>
                            <th style="width:20%">대분류명</th>
                            <td style="width:30%">
                                <input type="text" class="form-control" ng-model="form.bCommCdNm" placeholder="대분류코드명" readonly>
                            </td>
                        </tr>
                        <tr>
                            <th style="height:40px">코드</th>
                            <td><input type="text" class="form-control" ng-model="form.mCommCd" placeholder="중분류코드" readonly></td>

                            <th>코드명</th>
                            <td><input type="text" class="form-control" ng-model="form.mCommCdNm" placeholder="중분류코드명"></td>
                        </tr>
                        <tr>
                            <th>LEVEL</th>
                            <td>
                                <input type="text" class="form-control" ng-model="form.mCodeLevel">
                            </td>
                            <th>사용여부</th>
                            <td>
                                <select class="form-control" ng-model="form.mUseYn" ng-init="form.mUseYn='Y'">
                                    <option value="Y" >사용</option>
                                    <option value="N">미사용</option>
                                </select>
                            </td>


                        </tr>
                    </table>

                    <%--중분류 신규, 저장 input 영역 끝--%>
                    <%--중분류 목록 영역 시작--%>
                    <table style="width: 100%;text-align: center;font-size: medium; height: 231px; overflow: auto;" class="table custom-table-1 table-hover text-center table-striped custom-align-middle">
                        <thead>
                        <th style="width:25%" ng-click="sort('')">코드</th>
                        <th style="width:40%" ng-click="sort('')">코드명</th>
                        <th style="width:20%" ng-click="sort('')">사용여부</th>
                        </thead>
                    </table>
                    <div style="height: 231px; overflow: auto; border:1px solid whitesmoke;">
                        <table class="table custom-table-1 table-hover text-center table-striped custom-align-middle" style="width: 100%;text-align: center;font-size: medium;">
                            <tbody>
                                <tr ng-repeat="value in commCode" class="pointer" ng-init="value.isSelected = false;" ng-if="value.codeLevel=='M'">
                                    <td ng-click="mclick(value)" onclick="selectTr($(this))" style="height: 50px;" >{{value.commCd}}</td>
                                    <td ng-click="mclick(value)" onclick="selectTr($(this))" >{{value.commCdNm}}</td>
                                    <td ng-click="mclick(value)" onclick="selectTr($(this))" >{{value.useYn | YnWord: 2}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    </form>
                    <%--중분류 목록 영역 끝--%>
                </div>

                <div style="width: 48%;margin:10px;">
                    <%--소분류 신규, 저장 input 영역 시작--%>
                    <form ng-submit="formSave1()">
                    <div class="d-flex">
                        <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
                            <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 소분류</span>
                            <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                            <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                        </div>
                    </div>
                    <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                        <tr>
                            <th style="width:20%;height:40px">중분류코드</th>
                            <td style="width:30%;padding: 0;">
                                <input type="text" class="form-control" ng-model="form.mCommCd" readonly>
                            </td>
                            <th style="width:20%">중분류명</th>
                            <td style="width:30%">
                                <input type="text" class="form-control" ng-model="form.mCommCdNm" readonly>
                            </td>
                        </tr>
                        <tr>
                            <th style="height:40px">코드</th>
                            <td>
                                <input type="text" class="form-control" ng-model="form.sCommCd" placeholder="소분류코드" readonly>
                            </td>
                            <th>코드명</th>
                            <td>
                                <input type="text" class="form-control" ng-model="form.sCommCdNm" placeholder="소분류코드명">
                            </td>
                        </tr>
                        <tr>
                            <th>LEVEL</th>
                            <td>
                                <input type="text" class="form-control" ng-model="form.sCodeLevel">
                            </td>
                            <th>사용여부</th>
                            <td>
                                <select class="form-control" ng-model="form.sUseYn" ng-init="form.useYn='Y'" name="useYn">
                                    <option value="Y" >사용</option>
                                    <option value="N">미사용</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <%--소분류 신규, 저장 input 영역 끝--%>
                    <%--소분류 목록 영역 시작--%>

                        <table style="width: 100%;text-align: center;font-size: medium; height: 231px; overflow: auto;" class="table custom-table-1 table-hover text-center table-striped custom-align-middle">
                            <thead>
                            <th style="width:25%">코드</th>
                            <th style="width:40%;">코드명</th>
                            <th style="width:20%">사용여부</th>
                            </thead>
                        </table>
                        <div style="height: 231px; overflow: auto; border:1px solid whitesmoke;">
                            <table class="table custom-table-1 table-hover text-center table-striped custom-align-middle" style="width: 100%;text-align: center;font-size: medium;">
                                <tbody>
                                    <tr ng-repeat="value in commCode" class="pointer" ng-init="value.isSelected = false;" ng-if="value.codeLevel=='S'">
                                        <td ng-click="sclick(value)" onclick="selectTr($(this))" style="height: 50px;" >{{value.commCd}}</td>
                                        <td ng-click="sclick(value)" onclick="selectTr($(this))">{{value.commCdNm}}</td>
                                        <td ng-click="sclick(value)" onclick="selectTr($(this))">{{value.useYn | YnWord: 2}}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>
                    <%--소분류 목록 영역 시작--%>
                </div>
            </div>

            <!--코드목록-->
            <hr>
            <div style="width: 98%;margin:10px;padding:5px 0 20px 10px;">
                <div class="d-flex" style="border-bottom: 1px solid lightgray;width:100%;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 코드목록</span>
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
                </div>
                <!-- 테이블 생성 -->
                <div class="table-box">
                    <table id="findList" class="table custom-table-1 table-hover text-center table-striped custom-align-middle" style="width:100%;text-align: center;font-size: medium;">
                        <thead>
                            <tr class="pointer">
                                <th><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'commCd')"></th>
                                <th ng-click="sort('')" style="width:10%">코드번호</th>
                                <th ng-click="sort('')" style="width:10%;">코드명</th>
                                <th ng-click="sort('')" style="width:5%;">사용여부</th>
                                <th ng-click="sort('')" style="width:10%">LEVEL</th>
                                <th ng-click="sort('')" style="width:15%">등록자</th>
                                <th ng-click="sort('')" style="width:10%">등록일자</th>
                                <th ng-click="sort('')" style="width:15%">수정자</th>
                                <th ng-click="sort('')" style="width:5%">수정일자</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="value in list" class="pointer" ng-init="value.isSelected = false;">
                                <td style="width: 2%; padding:20px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox(!{{value.isSelected}}, {{value.commCd}})" ></td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.commCd}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.commCdNm}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.useYn | YnWord: 2}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.codeLevel}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regId}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regDate | date:'yyyy-MM-dd HH:mm'}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.modId}}</td>
                                <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.modDate | date:'yyyy-MM-dd HH:mm'}}</td>
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
    </div>
</div>
</html>

