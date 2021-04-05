//코드 리스트
app.controller('codeController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal) {

		pageInfo($rootScope, $location);
		$scope.inView = {};
		console.log(document.referrer);

		$http.get('/member/getTotCode').success(function(data) {
			$rootScope.commCode = data.commCode;
		});

		//대분류
		$scope.esB = {'newForm':false,'modForm':false};
		$scope.formChangeB = function(command, data){

			if(command == 'reset'){
				$scope.esB ={};
				$scope.esB.newForm = true;
				var maxNum = '010000';
				for(const value of $scope.commCode){
					if(value.commCd > maxNum){
						maxNum = value.commCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,2)*1) + 1),2) + '0000';
				$scope.BGub = {'useYn':'Y', 'commCd':maxNum};
			}else if(command == 'mod'){
				$scope.esB ={};
				$scope.esB.modForm = true;
				$scope.genderList = [];
				const formData = {}
				for(const key in data){
					formData[key] = data[key];
				}
				$scope.BGub = formData;
				$scope.inView.BGubCd = formData.commCd;
				$scope.inView.BGubCdNm = formData.commCdNm;

				$scope.MCode = [];
				$scope.commList = [];
				const currentCode = formData.commCd.substr(0, 2);
				for(value of $rootScope.commCode){
					if(value.commCd.indexOf(currentCode) == 0){
						$scope.commList.push(value);
					}
					if(value.commCd.indexOf(currentCode) == 0 && value.codeLevel == 'M') {
						$scope.MCode.push(value);
					}
				}
				// $scope.formChangeM('mod', $scope.MCode[0]);


				if($scope.MCode.length) {
					$scope.formChangeM('mod', $scope.MCode[0]);
				}else{
					//하위코드 데이터가 없는 경우
					$scope.SCode = [];
					$scope.MGub ={};
					$scope.SGub ={};
					$scope.inView.MGubCd = null;
					$scope.inView.MGubCdNm = null;
					return;
				}
			}
		}


		//중분류
		$scope.esM = {'newForm':false,'modForm':true};
		$scope.formChangeM =function(command, data){
			if(command == 'reset'){
				$scope.esM ={};
				$scope.esM.newForm = true;
				var maxNum = $scope.inView.BGubCd;
				for(const value of $scope.MCode){
					if(value.commCd > maxNum){
						maxNum = value.commCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,4)*1) + 1), 4) + '00';
				$scope.MGub = {'useYn':'Y', 'commCd':maxNum};
			}else if(command == 'mod'){
				$scope.SCode = [];
				$scope.esM ={};
				$scope.esM.modForm = true;
				const formData = {}
				for(const key in data){
					formData[key] = data[key];
				}
				$scope.MGub = formData;
				$scope.inView.MGubCd = formData.commCd;
				$scope.inView.MGubCdNm = formData.commCdNm;
				const currentCode = formData.commCd.substr(0, 4);
				for(value of $scope.commCode){
					if(value.commCd.indexOf(currentCode) == 0 && value.codeLevel == 'S'){
						$scope.SCode.push(value);
					}
				}
				// $scope.formChangeS('mod', $scope.SCode[0]);

				if($scope.SCode.length) {
					$scope.formChangeS('mod', $scope.SCode[0]);
				}else{
					$scope.SGub ={};
					return;
				}
			}
		}

		//소분류
		$scope.esS = {'newForm':false,'modForm':true};
		$scope.formChangeS =function(command, data){
			if(command == 'reset'){
				$scope.esS ={};
				$scope.esS.newForm = true;
				var maxNum = $scope.inView.MGubCd;
				for(const value of $scope.SCode){
					if(value.commCd > maxNum){
						maxNum = value.commCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,6)*1) + 1), 6);
				$scope.SGub = {'useYn':'Y', 'commCd':maxNum};
			}else if(command == 'mod'){
				$scope.esS ={};
				$scope.esS.modForm = true;
				const formData = {}
				for(const key in data){
					formData[key] = data[key];
				}
				$scope.SGub = formData;
			}
		}

		$scope.formSave = function(cmd){
			if(cmd == 'BGub'){
				if($scope.esB.newForm == true){
					$scope.BGub.codeLevel = 'B';
					commSave($scope.BGub);
				}else if($scope.esB.modForm == true){
					commUpdate($scope.BGub);
				}
			}else if(cmd == 'MGub'){
				if($scope.esM.newForm == true){
					$scope.MGub.codeLevel = 'M';
					commSave($scope.MGub);
				}else if($scope.esM.modForm == true){
					commUpdate($scope.MGub);
				}
			}else if(cmd == 'SGub'){
				if($scope.esS.newForm == true){
					$scope.SGub.codeLevel = 'S';
					commSave($scope.SGub);
				}else if($scope.esS.modForm == true){
					commUpdate($scope.SGub);
				}
			}
		}

		//브랜드 추가
		function commSave(data){
			modalCheck($uibModal, "코드추가", "코드 정보를 추가하시겠습니까?", function () {
				$http({
					method: 'POST',
					url: "/base/commSave",
					data: data,
					headers: {'Content-Type': 'application/json; charset=utf-8'}
				}).success(function (data) {
					if (data.resultCode == 'S') {
						modalAlert($uibModal, "코드추가", "코드 정보가 추가 되었습니다.");
					}
					$scope.reload();
				}).error(function (data) {
					alert('정보 업데이트 실패');
				});

			});
		}

		//브랜드 정보 수정
		function commUpdate(data){
			modalCheck($uibModal, "코드변경", "코드 정보를 변경하시겠습니까?", function () {
				$http({
					method: 'POST',
					url: "/base/commUpdate",
					data: data,
					headers: {'Content-Type': 'application/json; charset=utf-8'}
				}).success(function (data) {
					if (data.resultCode == 'S') {
						modalAlert($uibModal, "코드변경", "코드 정보가 변경 되었습니다.");
					}
					$scope.reload();
				}).error(function (data) {
					alert('정보 업데이트 실패');
				});

			});
		}

		$scope.clickInit = function(key){
			if(key == 0){
				return {'bg-secondary':'true', 'font-weight-bold':'true'}
			}
		}

		$(document).ready(function() {
			$scope.formChangeB('mod', $rootScope.commCode[0]);
		});

	}]
);
