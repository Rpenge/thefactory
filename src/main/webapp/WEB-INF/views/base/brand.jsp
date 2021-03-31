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
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 브랜드</span>
                    <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : form.newForm}"  ng-click="formChange('reset')" onclick="tableTrDel('list')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                    <button class="p-2 btn btn-outline-secondary" type="submit" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                </div>
                <%--{{commCode}}--%>
                <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                    <tr>
                        <th>브랜드코드</th>
                        <td style="width:70%;height: 40px;">
                            <input type="text" class="form-control" ng-model="form.bCommCd" placeholder="대분류코드">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:30%;">브랜드명</th>
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
                    <th style="width:30%" ng-click="sort('')">브랜드코드</th>
                    <th style="width:70%;" ng-click="sort('')">브랜드명</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="value in brandList" class="pointer" ng-init="value.isSelected = false;" ng-if="value.codeLevel=='B'">
                    <td ng-click="bclick(value)" onclick="selectTr($(this))" style="height: 50px;">{{value.brandKindCd}}</td>
                    <td ng-click="bclick(value)" onclick="selectTr($(this))">{{value.brandNm}}</td>
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
                <li><a href="#">브랜드 관리</a></li>
            </ol>
        </div>
        <div>


        </div>


        <!--contents-->
        <div class="body-custom" style="width:100%;">
            <div class="d-flex justify-content-center">
                <div style="width: 48%;margin:10px;">
                    <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;">
                        <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 중분류</span>
                        <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
                        <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
                    </div>

                    <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
                        <tr>
                            <th style="width:20%;height:40px">대분류코드</th>
                            <td style="width:30%;padding: 0;"><input type="text" class="form-control" placeholder="010000" readonly></td>
                            <th style="width:20%">대분류명</th>
                            <td style="width:30%"><input type="text" class="form-control" placeholder="발렌시아가" readonly></td>
                        </tr>
                        <tr>
                            <th style="height:40px">코드</th>
                            <td><input type="text" class="form-control" placeholder="010100" readonly></td>
                            <th>코드명</th>
                            <td><input type="text" class="form-control"></td>
                        </tr>
                        <tr>
                            <th>LEVEL</th>
                            <td><input type="text" class="form-control"></td>
                            <th>사용여부</th>
                            <td><select class="form-control"><option>Y</option></select></td>
                        </tr>

                    </table>

                    <div style="height: 231px;overflow: auto;border:1px solid whitesmoke;">
                        <table class="table-bordered" style="width: 100%;text-align: center;font-size: medium;">
                            <thead>
                            <th style="width:15%">NO</th>
                            <th style="width:25%">코드</th>
                            <th style="width:40%;">코드명</th>
                            <th style="width:20%">사용여부</th>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td style="height: 50px;">010000</td>
                                <td>발렌시아가</td>
                                <td>Y</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td>Y</td>

                            </tr>
                            <tr>
                                <td>3</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>

                <div style="width: 48%;margin:10px;">
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
                            <td style="width:30%;padding: 0;"><input type="text" class="form-control" placeholder="010000" readonly></td>
                            <th style="width:20%">중분류명</th>
                            <td style="width:30%"><input type="text" class="form-control" placeholder="발렌시아가" readonly></td>
                        </tr>
                        <tr>
                            <th style="height:40px">코드</th>
                            <td><input type="text" class="form-control" placeholder="010100" readonly></td>
                            <th>코드명</th>
                            <td><input type="text" class="form-control"></td>
                        </tr>
                        <tr>
                            <th>LEVEL</th>
                            <td><input type="text" class="form-control"></td>
                            <th>사용여부</th>
                            <td><select class="form-control"><option>Y</option></select></td>
                        </tr>

                    </table>

                    <div style="height: 231px;overflow: auto;border:1px solid whitesmoke;">
                        <table class="table-bordered" style="width: 100%;text-align: center;font-size: medium;">
                            <thead>
                            <th style="width:15%">NO</th>
                            <th style="width:25%">코드</th>
                            <th style="width:40%;">코드명</th>
                            <th style="width:20%">사용여부</th>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td style="height: 50px;">010000</td>
                                <td>발렌시아가</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td></td>

                            </tr>
                            <tr>
                                <td>3</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td style="height: 50px;">020000</td>
                                <td>장비</td>
                                <td></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <!--브랜드목록-->
            <hr>
            <div style="width: 98%;margin:10px;padding:5px 0 20px 10px;">
                <div class="d-flex" style="border-bottom: 1px solid lightgray;width:100%;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 브랜드목록</span>
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
                    <table class="table custom-table-1 table-hover text-center table-striped custom-align-middle" style="width:100%;text-align: center;font-size: medium;">
                        <thead>
                        <tr class="pointer">
                            <th ng-click="sort('')" style="width:10%">브랜드번호</th>
                            <th ng-click="sort('')" style="width:10%;">브랜드명</th>
                            <th ng-click="sort('')" style="width:5%;">사용여부</th>
                            <th ng-click="sort('')" style="width:10%">LEVEL</th>
                            <th ng-click="sort('')" style="width:15%">등록자</th>
                            <th ng-click="sort('')" style="width:10%">등록일자</th>
                            <th ng-click="sort('')" style="width:15%">수정자</th>
                            <th ng-click="sort('')" style="width:5%">수정일자</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="value in list" class="pointer">
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.brandKindCd}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.brandNm}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.useYn | YnWord: 2}}</td>
                            <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.codeLevel | YnWord: 3}}</td>
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


