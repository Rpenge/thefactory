<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script>

</script>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h6 class="modal-title">{{title}}</h6>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" ng-click="cancel()">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="modal-body">{{body}}</div>
	</div>
</body>
</html>