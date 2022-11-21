<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--          pageEncoding="utf-8"%> --%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html ng-app="myApp"> -->

<!-- <div class="d-flex justify-content-center" style="background-color:whitesmoke;width:100%;"> -->

<!--     left body -->
<!--     <div  style="width:20%;margin:3px;"> -->
<!--         메뉴 -->
<!--         <div class="d-flex flex-column left-body"> -->
<!--             <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;"> -->
<!--                 <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 대분류</span> -->
<!--                 <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : esB.newForm}" ng-click="formChangeB('reset')" style="width:60px;position:relative;bottom: -15px;border:1px solid #d3d3d3;padding-top:0!important;">신규</button> -->
<!--                 <button class="p-2 btn btn-outline-secondary" ng-click="formSave('BGub')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button> -->
<!--             </div> -->

<!--             <table class="table-bordered" style="width:99%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0"> -->
<!--                 <tr> -->
<!--                     <th>코드</th> -->
<!--                     <td style="width:70%;height: 40px;"><input type="text" class="form-control" ng-model="BGub.commCd" readonly></td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th style="width:30%;">코드명</th> -->
<!--                     <td style="width:70%;height: 40px;"><input type="text" class="form-control" ng-model="BGub.commCdNm"></td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <th>사용여부</th> -->
<!--                     <td> -->
<!--                         <select class="form-control" ng-model="BGub.useYn"> -->
<!--                             <option value="Y">Y</option> -->
<!--                             <option value="N">N</option> -->
<!--                         </select> -->
<!--                     </td> -->
<!--                 </tr> -->
<!--             </table> -->

<!--             <div class="table-box scroll-custom" style="max-height:812px;overflow: auto;"> -->
<!--                 <table class="table-bordered" style="width: 99%;text-align: center;font-size: 14px;"> -->
<!--                     <thead> -->
<!--                         <th style="width:30%">코드</th> -->
<!--                         <th style="width:70%;">코드명</th> -->
<!--                     </thead> -->
<!--                     <tbody class="pointer"> -->
<!--                         <tr ng-repeat="(key, value) in commCode" ng-if="value.codeLevel == 'B'" style="height: 50px;" ng-class="clickInit(key)"> -->
<!--                             <td ng-click="formChangeB('mod',value)" onclick="selectTr($(this))">{{value.commCd}}</td> -->
<!--                             <td ng-click="formChangeB('mod',value)" onclick="selectTr($(this))">{{value.commCdNm}}</td> -->
<!--                         </tr> -->
<!--                     </tbody> -->
<!--                 </table> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->

<!--     right body -->
<!--     <div style="width:80%;padding: 10px;"> -->

<!--         현재 메뉴명 : 메인페이지에서 숨김 -->
<!--         <div class="container-fluid d-flex justify-content-center" style="padding:0!important;" ng-show="!mainPage"> -->
<!--             <div class="d-flex align-items-end" style="width:30%;border-bottom:1px solid lightgray;"> -->
<!--                 <h4><a class="menuName" href="" ng-click="reload()">{{currentMenu.PGM_NM}}</a></h4> -->
<!--             </div> -->
<!--             <ol class="breadcrumb justify-content-end" style="bottom:0;margin:0;padding:10px 0 0 0;width:70%;background-color:transparent;border-bottom:1px solid lightgray;"> -->
<!--                 <li><a href="">{{currentMenu | menuGroup}}</a></li> &nbsp;>&nbsp; -->
<!--                 <li><a href="" >{{currentMenu.PGM_NM}}</a></li> -->
<!--             </ol> -->
<!--         </div> -->


<!--         contents -->
<!--         <div class="body-custom" style="width:100%;"> -->
<!--             <div class="d-flex justify-content-center"> -->


<!--                 중분류 -->
<!--                 <div style="width: 48%;margin:10px;"> -->
<!--                     <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;"> -->
<!--                         <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 중분류</span> -->
<!--                         <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : esM.newForm}" ng-click="formChangeM('reset')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button> -->
<!--                         <button class="p-2 btn btn-outline-secondary" ng-click="formSave('MGub')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button> -->
<!--                     </div> -->

<!--                     <table class="table-bordered" style="width:99.5%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0"> -->
<!--                         <tr> -->
<!--                             <th style="width:20%;height:40px">대분류코드</th> -->
<!--                             <td style="width:30%;padding: 0;"><input type="text" class="form-control" ng-model="inView.BGubCd" readonly></td> -->
<!--                             <th style="width:20%">대분류명</th> -->
<!--                             <td style="width:30%"><input type="text" class="form-control" ng-model="inView.BGubCdNm" readonly></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th style="height:40px">코드</th> -->
<!--                             <td><input type="text" class="form-control" ng-model="MGub.commCd" readonly></td> -->
<!--                             <th>코드명</th> -->
<!--                             <td><input type="text" class="form-control" ng-model="MGub.commCdNm"></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<%-- <%--                            <th>LEVEL</th>--%> --%>
<%-- <%--                            <td><input type="text" class="form-control" ng-model="MGub.codeAlign"></td>--%> --%>
<!--                             <th>사용여부</th> -->
<!--                             <td> -->
<!--                                 <select class="form-control" ng-model="MGub.useYn"> -->
<!--                                     <option value="Y">Y</option> -->
<!--                                     <option value="N">N</option> -->
<!--                                 </select> -->
<!--                             </td> -->
<!--                         </tr> -->

<!--                     </table> -->

<!--                     <div class="table-box scroll-custom" style="height: 220px;border-top:1px solid whitesmoke;border-bottom:1px solid whitesmoke;"> -->
<!--                         <table class="table-bordered" style="width: 99.5%;text-align: center;font-size: 14px;;"> -->
<!--                             <thead> -->
<!--                                 <th style="width:15%">NO</th> -->
<!--                                 <th style="width:25%">코드</th> -->
<!--                                 <th style="width:40%;">코드명</th> -->
<!--                                 <th style="width:20%">사용여부</th> -->
<!--                             </thead> -->
<!--                             <tbody class="pointer"> -->
<!--                                 <tr ng-repeat="(key, value) in MCode" id="{{value.brandKindCd}}" ng-class="clickInit(key)" style="height: 40px;"> -->
<!--                                     <td ng-click="formChangeM('mod',value)" onclick="selectTr($(this))">{{key+1}}</td> -->
<!--                                     <td ng-click="formChangeM('mod',value)" onclick="selectTr($(this))">{{value.commCd}}</td> -->
<!--                                     <td ng-click="formChangeM('mod',value)" onclick="selectTr($(this))">{{value.commCdNm}}</td> -->
<!--                                     <td ng-click="formChangeM('mod',value)" onclick="selectTr($(this))">{{value.useYn}}</td> -->
<!--                                 </tr> -->
<!--                             </tbody> -->
<!--                         </table> -->
<!--                     </div> -->
<!--                 </div> -->


<!--                 소분류 -->
<!--                 <div style="width: 48%;margin:10px;"> -->
<!--                     <div class="d-flex"> -->
<!--                         <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;"> -->
<!--                             <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 소분류</span> -->
<!--                             <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : esS.newForm}" ng-click="formChangeS('reset')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;" ng-disabled="inView.MGubCd=='010200'">신규</button> -->
<!--                             <button class="p-2 btn btn-outline-secondary" ng-click="formSave('SGub')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;" ng-disabled="inView.MGubCd=='010200'">저장</button> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <table class="table-bordered" style="width:99.5%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0;"> -->
<!--                         <tr> -->
<!--                             <th style="width:20%;height:40px">중분류코드</th> -->
<!--                             <td style="width:30%;padding: 0;"><input type="text" class="form-control" ng-model="inView.MGubCd" readonly></td> -->
<!--                             <th style="width:20%">중분류명</th> -->
<!--                             <td style="width:30%"><input type="text" class="form-control" ng-model="inView.MGubCdNm" readonly></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th style="height:40px">코드</th> -->
<!--                             <td><input type="text" class="form-control" ng-model="SGub.commCd" readonly></td> -->
<!--                             <th>코드명</th> -->
<!--                             <td><input type="text" class="form-control" ng-model="SGub.commCdNm" ng-readOnly="inView.MGubCd=='010200'&&role!='010101'"></td> -->
<!--                         </tr> -->
<!--                         <tr> -->
<!--                             <th>사용여부</th> -->
<!--                             <td> -->
<!--                                 <select class="form-control" ng-model="SGub.useYn" ng-readOnly="inView.MGubCd!='010200'"> -->
<!--                                     <option value="Y">Y</option> -->
<!--                                     <option value="N">N</option> -->
<!--                                 </select> -->
<!--                             </td> -->
<!--                         </tr> -->
<!--                     </table> -->

<!--                     <div class="table-box scroll-custom" style="height: 220px;border-top:1px solid whitesmoke;border-bottom:1px solid whitesmoke;"> -->
<!--                         <table class="table-bordered" style="width: 99.5%;text-align: center;font-size: 14px;;"> -->
<!--                             <thead> -->
<!--                                 <th style="width:15%">NO</th> -->
<!--                                 <th style="width:25%">코드</th> -->
<!--                                 <th style="width:40%;">코드명</th> -->
<!--                                 <th style="width:20%">사용여부</th> -->
<!--                             </thead> -->
<!--                             <tbody class="pointer"> -->
<!--                                 <tr ng-repeat="(key, value) in SCode" style="height: 40px;" ng-class="clickInit(key)"> -->
<!--                                     <td ng-click="formChangeS('mod',value)" onclick="selectTr($(this))">{{key+1}}</td> -->
<!--                                     <td ng-click="formChangeS('mod',value)" onclick="selectTr($(this))">{{value.commCd}}</td> -->
<!--                                     <td ng-click="formChangeS('mod',value)" onclick="selectTr($(this))">{{value.commCdNm}}</td> -->
<!--                                     <td ng-click="formChangeS('mod',value)" onclick="selectTr($(this))">{{value.useYn}}</td> -->
<!--                                 </tr> -->
<!--                             </tbody> -->
<!--                         </table> -->
<!--                     </div> -->
<!--                 </div> -->

<!--             </div> -->
<!--             <div style="height:8px;background: whitesmoke;width:100%;"></div> -->
<!--             코드목록 -->
<!--             <div style="width: 98%;margin:10px;padding:0 0 20px 10px;height: 510px;"> -->
<!--                 <div class="d-flex" style="border-bottom: 1px solid lightgray;width:100%;"> -->
<!--                     <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 공통코드 목록</span> -->
<!--                 </div> -->
<!--                 <div class="scroll-custom" style="max-height: 430px;overflow: auto;border:1px solid whitesmoke;margin-top: 15px;"> -->
<!--                     <table class="table-bordered table-striped-odd" style="width:100%;text-align: center;font-size: 14px;table-layout: fixed;"> -->
<!--                         <thead> -->
<!--                         <th style="width:10%;height:30px;">코드</th> -->
<!--                         <th style="width:15%;">대분류명</th> -->
<!--                         <th style="width:8%;">중분류명</th> -->
<!--                         <th style="width:10%;">소분류명</th> -->
<!--                         <th style="width:10%;">분류</th> -->
<!--                         <th style="width:8%;">등록자</th> -->
<!--                         <th style="width:10%;">등록일자</th> -->
<!--                         <th style="width:8%;">수정자</th> -->
<!--                         <th style="width:10%;">수정일자</th> -->
<!--                         <th style="width:5%;">사용여부</th> -->
<!--                         </thead> -->
<!--                         <tbody> -->
<!--                         <tr ng-repeat="value in commList"> -->
<!--                             <td style="height: 40px;">{{value.commCd}}</td> -->
<!--                             <td>{{inView.BGubCdNm}}</td> -->
<!--                             <td><span ng-if="value.codeLevel == 'M'">{{value.commCdNm}}</span></td> -->
<!--                             <td><span ng-if="value.codeLevel == 'S'">{{value.commCdNm}}</span></td> -->
<!--                             <td>{{value.codeLevel | codeLevelChange}}</td> -->
<!--                             <td>{{value.regId}}</td> -->
<!--                             <td>{{value.regDate | date:'yyyy-MM-dd HH:mm'}}</td> -->
<!--                             <td>{{value.modId}}</td> -->
<!--                             <td>{{value.modDate | date:'yyyy-MM-dd HH:mm'}}</td> -->
<!--                             <td>{{value.useYn}}</td> -->
<!--                         </tr> -->
<!--                         </tbody> -->
<!--                     </table> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<!-- </html> -->

