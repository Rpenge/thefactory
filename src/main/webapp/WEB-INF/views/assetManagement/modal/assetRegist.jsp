<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
</head>
<body>
	<div style="padding:10px 20px; border-radius:3px;">
	<h2 style="border-bottom:1px solid black; padding:10px;margin-bottom:20px;">브랜드 등록</h2>


		브랜드명
		<select multiple style="width:100%;">
			<option>1</option>
			<option>2</option>
			<option>3</option>

		</select>
		성별
		<select multiple style="width:100%;">
			<option>남성</option>
			<option>여성</option>
		</select>
		상품분류
		<select multiple style="width:100%;">
			<option>아우터</option>
			<option>상의</option>
			<option>하의</option>
			<option>슈즈</option>
			<option>가방</option>
			<option>지갑</option>
			<option>시계/잡화</option>
		</select>

		<button class="btn btn-primary">등록</button>
		<button class="btn" ng-click="close()" style="margin:0 30px;">닫기 </button>
	</div>
</body>
</html>
