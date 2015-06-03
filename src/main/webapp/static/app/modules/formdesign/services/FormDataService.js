app.factory('FormDataService', [ '$http', function($http) {
	return {
		//查询表单列表
		del: function(params, cb){
			$http.post("formdesign/formdatadel", params).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);