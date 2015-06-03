app.factory('FormDesignService', [
		'$http',
		function($http) {
			return {
				// 查询单条表单信息并处理
				queryFormById : function(queryParams, cb) {
					$http.post("formdesign/getformbyid", queryParams).success(function(data) {
						var fields = JSON.parse(data.formResult.data.fields);
						var newFields = [];
						var maxField = 5;
						var flag = 0;
						$.each(fields, function(key, val) {
							if (flag >= maxField) {
								return false;
							}
							flag++;
							if (val && val.fielddesc) {
								newFields.push({
									name : key,
									desc : val.fielddesc
								});
							}
						});
						data.formFields = newFields;
						if(cb) cb(data);
					});
				},
				//删除表单
				del: function(params, cb){
					$http.post("formdesign/del", params).success(function(data){
						if(cb) cb(data);
					});
				},
				//发布表单
				pub: function(params, cb){
					$http.post("formdesign/pub", params).success(function(data){
						if(cb) cb(data);
					});
				},
				//撤销发布表单
				canclepub: function(params, cb){
					$http.post("formdesign/canclepub", params).success(function(data){
						if(cb) cb(data);
					});
				}
			};
		} ]);
