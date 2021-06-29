app.controller('brandController', ['$scope', '$http', '$location', '$rootScope', '$window', '$filter', '$uibModal','$timeout',
	function ($scope, $http, $location, $rootScope, $window, $filter, $uibModal,$timeout) {

		menuCheck($rootScope, $location);
		pageInfo($rootScope, $location); //현재페이지 정보
		$scope.inView = {};

		$http.get('/member/getTotBrand').success(function(data) {
			$scope.brandList = data.brandList;
		});

		//대분류
		$scope.esB = {'newForm':true,'modForm':false};
		$scope.formChangeB =function(command, data){
			if(command == 'reset'){
				$scope.esB ={};
				$scope.esB.newForm = true;
				var maxNum = '010000';
				for(const value of $scope.brandList){
					if(value.brandKindCd > maxNum){
						maxNum = value.brandKindCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,2)*1) + 1),2) + '0000';
				$scope.BGub = {'useYn':'Y', 'brandKindCd':maxNum};
			}else if(command == 'mod'){
				$scope.esB ={};
				$scope.esB.modForm = true;
				$scope.genderList = [];
				const formData = {}
				for(const key in data){
					if(key == '$$hashKey'){
						continue;
					}
					formData[key] = data[key];
				}
				$scope.BGub = formData;
				$scope.inView.BGubBrandCd = formData.brandKindCd;
				$scope.inView.BGubBrandNm = formData.brandNm;

				$http.get('/member/brandSub?brandCd='+ formData.brandKindCd.substr(0,2)).success(function(resultData) {
					if(!resultData['brandSubList'].length){
						$scope.MGub = {};
						$scope.SGub = {};
						$scope.genderList = [];
						$scope.clsList = [];
						$scope.subBrand = [];
						$scope.inView.SGubBrandCd = null;
						return;
					}
					$scope.subBrand = resultData.brandSubList;

					for(value of $scope.subBrand) {
						if(value.codeLevel == 'M') {
							$scope.genderList.push(value);
						}
					}
					$scope.formChangeM('mod',$scope.genderList[0]);
				});
			}
		}


		//중분류
		$scope.esM = {'newForm':false,'modForm':true};
		$scope.formChangeM =function(command, data){
			if(command == 'reset'){
				$scope.esM ={};
				$scope.esM.newForm = true;
				var maxNum = $scope.inView.BGubBrandCd;
				for(const value of $scope.genderList){
					if(value.brandKindCd > maxNum){
						maxNum = value.brandKindCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,4)*1) + 1), 4) + '00';
				$scope.MGub = {'useYn':'Y', 'brandKindCd':maxNum};
			}else if(command == 'mod'){
				var gender = data.brandKindCd;
				$scope.clsList = [];
				gender = gender.substr(0,4);

				$scope.esM ={};
				$scope.esM.modForm = true;
				const formData = {}
				for(const key in data){
					if(key == '$$hashKey'){continue;}
					formData[key] = data[key];
				}
				$scope.MGub = formData;
				$scope.inView.MGubBrandCd = formData.brandKindCd;
				$scope.inView.MGubBrandNm = formData.brandNm;
				for(value of $scope.subBrand){
					if(value.brandKindCd.indexOf(gender) == 0 && value.codeLevel == 'S'){
						$scope.clsList.push(value);
					}
				}
				if($scope.clsList.length) {
					$scope.formChangeS('mod', $scope.clsList[0]);
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
				var maxNum = $scope.inView.MGubBrandCd;
				for(const value of $scope.clsList){
					if(value.brandKindCd > maxNum){
						maxNum = value.brandKindCd ;
					}
				}
				maxNum = pad(((maxNum.substring(0,6)*1) + 1), 6);
				$scope.SGub = {'useYn':'Y', 'brandKindCd':maxNum};

			}else if(command == 'mod'){
				var gender = data.brandKindCd;
				gender = gender.substr(0,4);
				$scope.esS ={};
				$scope.esS.modForm = true;
				const formData = {}
				for(const key in data){
					if(key == '$$hashKey'){continue;}
					formData[key] = data[key];
				}
				$scope.SGub = formData;
			}
		}

		$scope.formSave = function(cmd){
			if(cmd == 'BGub'){
				if($scope.esB.newForm == true){
					$scope.BGub.codeLevel = 'B';
					brandSave($scope.BGub);
				}else if($scope.esB.modForm == true){
					brandUpdate($scope.BGub);
				}
			}else if(cmd == 'MGub'){
				if($scope.esM.newForm == true){
					$scope.MGub.codeLevel = 'M';
					brandSave($scope.MGub);
				}else if($scope.esM.modForm == true){
					brandUpdate($scope.MGub);
				}
			}else if(cmd == 'SGub'){
				if($scope.esS.newForm == true){
					$scope.SGub.codeLevel = 'S';
					brandSave($scope.SGub);
				}else if($scope.esS.modForm == true){
					brandUpdate($scope.SGub);
				}
			}
		}

		//브랜드 추가
		function brandSave(data){
			modalCheck($uibModal, "브랜드추가", "브랜드 정보를 추가하시겠습니까?", function () {
				$http({
					method: 'POST',
					url: "/base/brandSave",
					data: data,
					headers: {'Content-Type': 'application/json; charset=utf-8'}
				}).success(function (data) {
					console.log(data);
					if (data.resultCode == 'S') {
						modalAlert($uibModal, "브랜드추가", "브랜드 정보가 추가 되었습니다.");
					}
					$scope.reload();
				}).error(function (data) {
					alert('정보 업데이트 실패');
				});

			});
		}

		//브랜드 정보 수정
		function brandUpdate(data){
			modalCheck($uibModal, "브랜드변경", "브랜드 정보를 변경하시겠습니까?", function () {
				$http({
					method: 'POST',
					url: "/base/brandUpdate",
					data: data,
					headers: {'Content-Type': 'application/json; charset=utf-8'}
				}).success(function (data) {
					if (data.resultCode == 'S') {
						modalAlert($uibModal, "브랜드변경", "브랜드 정보가 변경 되었습니다.");
					}
					$scope.reload();
				}).error(function (data) {
					alert('정보 업데이트 실패');
				});

			});
		}

		$scope.fileUpload = function(path){
			const fileValue = path.split("\\");
			const fileName = fileValue[fileValue.length-1]; // 파일명
			$scope.file_path = fileName;
			$scope.$apply();
		}
		//파일 업로드
		$scope.upload = function(){

			if(!$scope.file_path){
				modalAlert($uibModal, "브랜드정보 Excel 업로드", "파일을 선택해주세요");
				return;
			}
			modalCheck($uibModal, "브랜드정보 Excel 업로드", "브랜드정보를 업로드 하시겠습니까?", function(){
				const form = $('#excelForm')[0];
				const formData = new FormData(form);
				$http({
					method : 'POST',
					enctype: 'multipart/form-data',
					url : "/brandUpload",
					data : formData,
					headers: {"Content-Type": undefined, },
				}).success(function(data){
					if(data.resultCode == 'S'){
						modalAlert($uibModal, "브랜드정보 Excel 업로드", "재고정보가 업데이트 되었습니다");
					}else{
						modalAlert($uibModal, "브랜드정보 Excel 업로드", "업데이트 오류");
					}
					$rootScope.reload();
				}).error(function(){
					modalAlert($uibModal, "브랜드정보 Excel 업로드", "업데이트 오류!");
				});

			});
		}

		$scope.clickInit = function(key){
			if(key == 0){
				return {'bg-secondary':'true', 'font-weight-bold':'true'}
			}
		}

		$(document).ready(function() {
			$scope.formChangeB('mod', $scope.brandList[0]);
		});


}]);

