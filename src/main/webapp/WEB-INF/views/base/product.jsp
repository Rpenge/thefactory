<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--          pageEncoding="utf-8"%> --%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html ng-app="myApp"> -->


<!-- <div ng-show="authenticated"> -->

<!--     <section class="d-flex justify-content-center"> -->

<!--         contents -->
<!--         <div class="container-fluid body-custom" style="width:100%;"> -->
<!--             <div class="body-contents "> -->
<!--                 <div> -->
<!--                     <form ng-submit="formSave()"> -->
<!--                         <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;"> -->
<!--                             <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-caret-down-circle-o"></i> 상품 추가/수정 <span style="color:red;font-size:15px;"> * 표시는 필수 입력 항목입니다.</span></span> -->
<!--                             <button class="p-2 btn btn-outline-secondary" ng-class="{'active-btn' : es.newForm}" ng-click="formChange('reset')" onclick="tableTrDel('productTable')" style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;">신규</button> -->
<!--                             <button type='submit' class="p-2 btn btn-outline-secondary"  style="width:60px;position:relative;bottom: -15px;border:1px solid lightgray;padding-top:0!important;margin:0 5px;">저장</button> -->
<!--                         </div> -->

<!--                         <table class="table-bordered" style="width:100%;height:120px;text-align: center;background: whitesmoke;margin:10px 0 10px 0"> -->
<!--                             <tr> -->
<!--                                 <th style="width:10%;height:40px"><span style="color:red;" ng-if="es.newForm">*</span> 브랜드</th> -->
<!--                                 <td style="width:15%;padding: 0;"> -->
<!--                                     <select class="form-control" ng-model="inView.brand" ng-change="brandSelect(inView.brand)" ng-disabled="es.modForm" ng-required="es.newForm"> -->
<!--                                         <option value="">브랜드선택</option> -->
<!--                                         <option ng-repeat="value in brandList"  value="{{value.brandKindCd}}">{{value.brandNm}}</option> -->
<!--                                     </select> -->
<!--                                 </td> -->
<!--                                 <th style="width:10%"><span style="color:red;" ng-if="es.newForm">*</span> 성별</th> -->
<!--                                 <td style="width:15%"> -->
<!--                                     <select class="form-control" ng-model="inView.gender" ng-change="genderSelect(inView.gender)" ng-disabled="es.modForm" ng-required="es.newForm"> -->
<!--                                         <option value="">성별선택</option> -->
<!--                                         <option ng-repeat="value in subBrand" ng-if="value.codeLevel=='M' " value="{{value.brandKindCd}}">{{value.brandNm}}</option> -->
<!--                                     </select> -->
<!--                                 </td> -->
<!--                                 <th style="width:10%"><span style="color:red;" ng-if="es.newForm">*</span> 상품분류</th> -->
<!--                                 <td style="width:15%"> -->
<!--                                     <select class="form-control" ng-model="inView.cls" ng-disabled="es.modForm" ng-required="es.newForm"> -->
<!--                                         <option value="">분류선택</option> -->
<!--                                         <option ng-repeat="value in subBrandCls" value="{{value.brandKindCd}}">{{value.brandNm}}</option> -->
<!--                                     </select> -->
<!--                                 </td> -->
<!--                                 <th style="width:10%">사이즈</th> -->
<!--                                 <td style="width:15%"><input type="text" class="form-control" ng-model="form.ecSizeNm"></td> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                                 <th style="height:40px"><span style="color:red;" ng-if="es.newForm">*</span> 상품코드</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.ecPrdCd" ng-disabled="es.modForm" ng-required="es.newForm"></td> -->
<!--                                 <th><span style="color:red;" ng-if="es.newForm">*</span> 자체상품코드</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.tfPrdCd" ng-disabled="es.modForm" ng-required="es.newForm"></td> -->
<!--                                 <th><span style="color:red;" ng-if="es.newForm">*</span> 상품명</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.ecPrdNm" ng-required="es.newForm"></td> -->
<!--                                 <th><span style="color:red;" ng-if="es.newForm">*</span> 자체상품명</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.tfPrdNm" ng-required="es.newForm"></td> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                                 <th>모델명</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.prdModelNm"></td> -->
<!--                                 <th>제조사</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.brandMakeNm"></td> -->
<!--                                 <th style="height:40px">원산지</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.orgCountryNm"></td> -->
<!--                                 <th style="height:40px">등록자</th> -->
<!--                                 <td><input type="text" class="form-control" ng-model="form.regId" ng-readOnly="true"></td> -->
<!--                             </tr> -->
<!--                         </table> -->
<!--                     </form> -->
<!--                 </div> -->


<!--                 <div class="d-flex" style="border-bottom: 1px solid lightgray;overflow: hidden;width:100%;margin-top:20px;margin-bottom: 10px;"> -->
<!--                     <span class="mr-auto p-2" style="font-size: 22px;color:gray;"><i class="xi-list"></i> 상품목록</span> -->
<!--                 </div> -->

<!--                 테이블 상단 구성 -->
<!--                 <div class="d-flex" style="margin:10px 0;"> -->
<!--                     <div class="d-flex mr-auto p-2" style="margin:0;padding:0!important;"> -->
<!--                         <select class="custom-select" ng-model="search.size" ng-init="search.size = '10'" ng-change="pageSize()" style="width:150px;margin-right: 10px;"> -->
<!--                             <option value="10">10개씩 보기</option> -->
<!--                             <option value="20">20개씩 보기</option> -->
<!--                             <option value="50">50개씩 보기</option> -->
<!--                             <option value="100">100개씩 보기</option> -->
<!--                             <option value="500">500개씩 보기</option> -->
<!--                         </select> -->

<!--                         <h6 class="align-self-center">TOTAL ( {{paging.total}} )</h6> -->
<!--                     </div> -->
<!--                     <button class="btn btn-primary mobile-none" ng-click="formDown()" ng-if="hiddenFunction==true"><i class="xi-file-download-o"></i> 양식</button> -->
<!--                     <form method="post" id="excelForm" action="upload" enctype="multipart/form-data" onsubmit="return false" ng-if="role=='010101'|| role=='010102'"> -->
<!--                         <div class="input-group btn p-2" style="width:330px;margin:0px;padding:0!important;height:40px;"> -->
<!--                             <input ng-model="file_path" style=";border: 1px solid lightgray;width: 180px;background: white;border-radius: 5px 0 0 5px;" disabled=disabled"> -->
<!--                             <div class="input-group-append"> -->
<!--                                 <label class="btn btn-outline-secondary" style="margin:0;border:1px solid lightgray;border-left:0px">찾기 -->
<!--                                     <input class="btn-outline-secondary" type="file" name="excelFile" accept=".xlsx" onchange="angular.element(this).scope().fileUpload(this.value)" hidden> -->
<!--                                 </label> -->
<!--                             </div> -->
<!--                             <button class="btn btn-outline-secondary" ng-click="upload()" style="border: 1px solid lightgray;border-radius: 0 5px 5px 0;border-left:0px">업로드</button> -->
<!--                         </div> -->
<!--                     </form> -->

<!--                     <button class="btn btn-danger p-2 table-top-btn" ng-click="tableBtn('delete')">삭제</button> -->
<!--                 </div> -->

<!--                 테이블 생성 -->
<!--                 <div class="table-box"> -->
<!--                     <table id="productTable" class="table custom-table-1 table-hover text-center table table-striped-odd custom-align-middle" style="width:100%;min-width:1450px;"> -->
<!--                         <thead> -->
<!--                             <tr> -->
<!--                                 <th style="width:30px;"><input type="checkbox" ng-init="checkAll.isSelected=false" ng-model="checkAll.isSelected" ng-change="checkAll(!{{checkAll.isSelected}}, 'prdRegSeq')"></th> -->
<!--                                 <th style="width:70px;">No</th> -->
<!--                                 <th style="width:130px;">브랜드</th> -->
<!--                                 <th style="width:85px;">성별</th> -->
<!--                                 <th style="width:85px;">상품분류</th> -->
<!--                                 <th style="width:110px;">사이즈</th> -->
<!--                                 <th style="width:110px;">상품코드</th> -->
<!--                                 <th style="width:220px;">자체상품코드</th> -->
<!--                                 <th style="width:330px;">상품명</th> -->
<!--                                 <th>등록자</th> -->
<!--                                 <th>등록일시</th> -->
<!--                             </tr> -->
<!--                         </thead> -->
<!--                         <tbody> -->
<!--                             <tr ng-repeat="value in list" class="pointer" ng-init="value.isSelected = false;"> -->
<!--                                 <td style="padding:13px;height:60px;"><input type="checkbox" ng-model="value.isSelected" ng-change="checkBox(!{{value.isSelected}}, {{value.prdRegSeq}})" ></td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.prdRegSeq}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.brandNm}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.genderNm}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.clsNm}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.ecSizeNm}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.ecPrdCd}}</td> -->
<!--                                 <td class='table-text-left' ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.tfPrdCd}}</td> -->
<!--                                 <td class='table-text-left' ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.tfPrdNm}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regId}}</td> -->
<!--                                 <td ng-click="formChange('mod',value)" onclick="selectTr($(this))">{{value.regDate | date:'yyyy-MM-dd HH:mm'}}</td> -->
<!--                             </tr> -->
<!--                         </tbody> -->
<!--                     </table> -->
<!--                 </div> -->

<!--                 <div class="row d-flex justify-content-center"> -->
<!--                     네비게이션 바 -->
<!--                     <nav class=" text-center" > -->
<!--                         <ul class="pagination"> -->
<!--                             <li class="page-item" ng-if="paging.current > 10"> -->
<!--                                 <a href="" aria-label="Previous" ng-click="goPage(1)" class="page-link"><span aria-hidden="true">&laquo;</span></a> -->
<!--                             </li> -->
<!--                             <li class="page-item" ng-if="paging.current > 10"> -->
<!--                                 <a href="" aria-label="Previous" ng-click="goPage(paging.begin - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a> -->
<!--                             </li> -->
<!--                             <li ng-repeat="pageNum in [paging.begin, paging.end] | makeRange" class="page-item" ng-class="{'active-page' : paging.current == pageNum}" ><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li> -->
<!--                             <li class="page-item" ng-if="paging.end != paging.last"> -->
<!--                                 <a href="" aria-label="Next" ng-click="goPage(paging.end + 1)" class="page-link"><span aria-hidden="true">&gt;</span></a> -->
<!--                             </li> -->
<!--                             <li class="page-item" ng-if="paging.end != paging.last"> -->
<!--                                 <a href="" aria-label="Next" ng-click="goPage(paging.last)" class="page-link"><span aria-hidden="true">&raquo;</span></a> -->
<!--                             </li> -->
<!--                         </ul> -->
<!--                     </nav> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </section> -->
<!-- </div> -->
<!-- </html> -->


