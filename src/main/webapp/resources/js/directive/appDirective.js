/**
 * 뒤로가기 버튼
 */
app.directive('backBtn', function () {
    return {
        restrict: 'E',
        template: '<button class="btn btn-lg">{{back}}</button>',
        scope: {
            back: '@back',
            icons: '@icons'
        },
        link: function(scope, element, attrs) {
            $(element[0]).on('click', function() {
                history.back();
                scope.$apply();
            });
        }
    };
});

app.directive('backBlockBtn', function () {
    return {
        restrict: 'E',
        template: '<button class="btn btn-lg btn-block">{{back}}</button>',
        scope: {
            back: '@back',
            icons: '@icons'
        },
        link: function(scope, element, attrs) {
            $(element[0]).on('click', function() {
                history.back();
                scope.$apply();
            });
        }
    };
});

/**
 * 서클형 로딩바
 */
app.directive('loading', [ '$http', function($http) {
	return {
		restrict : 'A',
		link : function(scope, elm, attrs) {
			scope.isLoading = function() {
				return $http.pendingRequests.length > 0;
			};
			scope.$watch(scope.isLoading, function(v) {
				if (v) {
					elm.show();
				} else {
					elm.hide();
				}
			});
		}
	};
} ]);

