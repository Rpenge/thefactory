<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<div class="starter-template" ng-show="authenticated">

	<section class="container-fluid d-flex justify-content-between" style="width:90%;border-bottom:1px solid lightgray;padding:0!important;">
		<div class="d-flex align-items-end">
			<h4><a class="menuName" href="" ng-click="reload()">User</a></h4>
			<h6 style="color:black;">사용자정보</h6>
		</div>
		<ol class="breadcrumb" style="height:10px;background-color:transparent;">
			<li><a href="#">시스템관리</a></li> &nbsp;>&nbsp;
			<li><a href="#">사용자관리</a></li>
		</ol>
	</section>

	<section class="container-fluid body-custom">
		<div class="body-contents">

			<h6 class="total-text" style="margin-bottom: 30px;">TOTAL ( {{total}} )</h6>

			<div style="overflow:auto;">
			<table class="table table-hover text-center custom-align-middle" style="min-width:1100px;">
				<thead>
					<tr class="pointer">
						<th style="width: 80px;" ng-click="sort('no')">No</th>
						<th ng-click="sort('userId')">아이디</th>
						<th ng-click="">이름</th>
						<th ng-click="">연락처</th>
						<th ng-click="">부서</th>
						<th ng-click="">이메일</th>
						<th ng-click="">가입일</th>
						<th ng-click="">권한</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in list" class="pointer" ng-if="list.length > 0" ng-click="userInfoMod(user.userId)">
						<td>{{user.userInfoSeq}}</td>
						<td>{{user.userId}}</td>
						<td>{{user.empName}}</td>
						<td>{{user.empContact}}</td>
						<td>{{user.dept | code: commonCode}}</td>
						<td>{{user.email}}</td>
						<td>{{user.userRegDate | date:'yyyy-MM-dd HH:mm' }}</td>
						<td>{{user.empAuthorization | code: commonCode}}</td>
					</tr>
				</tbody>
			</table>
			</div>

			<div>
				<nav class="text-center">
					<ul class="pagination justify-content-center">
						<li class="page-item">
							<a href="" aria-label="Previous" ng-click="goPage(begin)" class="page-link"><span aria-hidden="true">&laquo;</span></a>
				    	</li>
				    	<li class="page-item">
							<a href="" aria-label="Previous" ng-click="goPage(current - 1)" class="page-link"><span aria-hidden="true">&lt;</span></a>
				    	</li>
				    	<li ng-repeat="pageNum in [begin, end] | makeRange" ng-class="{'active-page' : current == pageNum}" class="page-item"><a href="" ng-click="goPage(pageNum)" class="page-link">{{pageNum}}</a></li>
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
	</section>
</div>
</html>
