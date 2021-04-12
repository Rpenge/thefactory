<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
<meta charset="UTF-8">
</head>
<body>
	<div>
		<div class="modal-header">
			<h6 class="modal-title">{{title}}</h6>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" ng-click="cancel()">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="modal-body" style="height: 150px;">{{body}}</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" ng-click="cancel()">확인</button>
		</div>
	</div>
</body>
