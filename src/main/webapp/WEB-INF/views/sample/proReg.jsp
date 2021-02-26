<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
</head>
<body>

	<div style="padding:10px 20px; border-radius:3px;border-top:4px solid seagreen;">
		<button class="btn btn-outline-secondary" ng-click="close()" style="position:relative;float: right;font-size: 13px;">X</button>
		<h3 style="border-bottom:1px solid black; padding:10px;margin-bottom:20px;">상품정보 등록</h3>



		<!-- /.box-header -->
		<div class="box-body">
			<form role="form">

				<!-- text input -->
				<div class="form-group">
					<label>브랜드 상품 분류</label>
					<input type="text" ng-model="input.brand" uib-typeahead="state for state in states | filter:$viewValue | limitTo:8" class="form-control" placeholder="Enter ...">
				</div>

				<!-- Select multiple-->
				<div class="form-group">
					<label>브랜드</label>
					<select multiple class="form-control ">
						<option>option 1</option>
						<option>option 2</option>
						<option>option 3</option>
						<option>option 4</option>
						<option>option 5</option>
					</select>
				</div>

				<!-- select -->
				<div class="form-group">
					<label>성별</label>
					<select class="form-control">
						<option>남성</option>
						<option>성별</option>
						<option>여성</option>
					</select>
				</div>

				<!-- select -->
				<div class="form-group">
					<label>상품구분</label>
					<select class="form-control">
						<option>option 1</option>
						<option>option 2</option>
						<option>option 3</option>
						<option>option 4</option>
						<option>option 5</option>
					</select>
				</div>






				<div class="form-group ">
					<label>상품코드</label>
					<input type="text" class="form-control is-valid" >
				</div>

				<div class="form-group">
					<label>자체상품코드</label>
					<input type="text" class="form-control" placeholder="Enter ...">
				</div>


				<div class="form-group">
					<label>상품명</label>
					<input type="text" class="form-control" placeholder="Enter ...">
				</div>

				<div class="form-group">
					<label>자체상품바코드</label>
					<input type="text" class="form-control" placeholder="Enter ...">
				</div>

				<div class="form-group">
					<label>등록자</label>
					<input type="text" class="form-control" placeholder="manager" disabled>
				</div>

				<br>
				추가입력 정보
				<hr>

				<div class="form-group">
					<label>모델명</label>
					<input type="text " class="form-control is-invalid" placeholder="Enter ...">
				</div>

				<div class="form-group">
					<label>제조사명</label>
					<input type="text" class="form-control" placeholder="Enter ...">
				</div>

			</form>
		</div>

		<!-- /.box -->
	</div>

	<div>
		<button class="btn btn-primary" style="position:relative;float:right;margin: 10px;">등록</button>
	</div>
</body>
</html>
