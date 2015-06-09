app.factory('CustomService', [ '$http', function($http) {
	return {
		//
		test: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);