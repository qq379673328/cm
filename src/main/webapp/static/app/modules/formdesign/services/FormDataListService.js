app.factory('FormDataListService', [ '$http', function($http) {
	return {
		//查询表单列表
		queryList: function(queryParams, cb){
			$http.post("formdesign/getformdatas", queryParams).success(function(data){
				 var newItems = [],
				 	rows = data.pagingResult.rows;
				 if(rows && rows.length > 0){
					 for(var i in rows){
						 var row = rows[i];
						 newItems.push({
							 id: row.id,
							 dt_create: row.dt_create,
							 form_data: JSON.parse(row.form_data)
						 });
					 }
				 }
				data.pagingResult.rows = newItems;
				if(cb) cb(data);
			});
		}
	};
}]);