<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h5 class="modal-title">{{title}}</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" ng-click="cancel()">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="modal-body">{{body}}</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" ng-click="ok()">ok</button>
			<button type="button" class="btn btn-secondary" ng-click="cancel()" data-dismiss="modal">cancel</button>
		</div>
	</div>
</body>
</html>
