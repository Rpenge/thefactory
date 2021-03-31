<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">

<div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;">


    <!-- left body -->
    <div  style="width:20%;margin:3px;">
        <!--메뉴-->
        <div class="d-flex flex-column left-body">
        <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;">
            <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 대분류</span>
            <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button>
            <button class="p-2 btn btn-outline-secondary" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button>
        </div>

        <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0">
            <tr>
                <th>코드</th>
                <td style="width:70%;height: 40px;"><input type="text" class="form-control" placeholder="010000"></td>
            </tr>
            <tr>
                <th style="width:30%;">코드명</th>
                <td style="width:70%;height: 40px;"><input type="text" class="form-control" placeholder="발렌시아가"></td>
            </tr>
            <tr>
                <th>사용여부</th>
                <td><select class="form-control"><option>Y</option></select></td>
            </tr>

        </table>
        <div>
            <table class="table-bordered" style="width: 100%;text-align: center;font-size: medium;min-height: 650px;">
                <thead>
                    <th style="width:30%">코드</th>
                    <th style="width:70%;">코드명</th>
                </thead>
                <tbody>
                    <tr ng-repeat="key in [1,2,3,4,5,6,7]">
                        <td style="height: 50px;">010000</td>
                        <td>발렌시아가</td>
                    </tr>

                <tr></tr>


                </tbody>
            </table>
        </div>
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

            <!--코드목록-->
            <hr>
            <div style="width: 98%;margin:10px;padding:5px 0 20px 10px;">
                <div class="d-flex" style="border-bottom: 1px solid lightgray;width:100%;">
                    <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 브랜드 목록</span>
                </div>
                <div style="max-height: 530px;overflow: auto;border:1px solid whitesmoke;margin-top: 15px;">

                    <table class="table-bordered" style="width:100%;text-align: center;font-size: medium;">
                        <thead>
                            <th style="width:5%;">NO</th>
                            <th style="width:10%">코드</th>
                            <th style="width:15%;">코드명</th>
                            <th style="width:10%;">분류</th>
                            <th style="width:5%;">LEVEL</th>
                            <th style="width:10%">등록자</th>
                            <th style="width:15%">등록일자</th>
                            <th style="width:10%">수정자</th>
                            <th style="width:15%">수정일자</th>
                            <th style="width:5%">사용여부</th>
                        </thead>
                        <tbody>
                            <tr ng-repeat="key in [1,2,3,4,5,6,7,8,9,10,11,12]">
                                <td style="height: 50px;">{{key}}</td>
                                <td>010000</td>
                                <td>발렌시아가</td>
                                <td>소분류</td>
                                <td>1</td>
                                <td>admin</td>
                                <td>2021-03-24</td>
                                <td>admin</td>
                                <td>2021-03-24</td>
                                <td>Y</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</html>


