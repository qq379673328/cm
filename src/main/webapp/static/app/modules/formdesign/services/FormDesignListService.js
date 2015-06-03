app.factory('FormDesignListService', [ '$http', function($http) {
	return {
		//查询表单列表
		queryList: function(queryParams, cb){
			$http.post("formdesign/getforms", queryParams).success(function(data){
				if(cb) cb(data);
			});
		}
	};
}]);