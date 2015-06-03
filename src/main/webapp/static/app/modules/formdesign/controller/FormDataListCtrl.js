
//表单数据列表
app.controller('FormDataListCtrl',function($scope, $http, $routeParams, 
		ngTableParams, FormDataListService, FormDesignService, FormDataService) {
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.initParams = function(){
		$scope.queryParams = {
				page: initpage,
				rows: initrows,
				formid: $routeParams.formid,
				isreload: true
		};
	};
	$scope.initParams();
	/*重新加载*/
	$scope.reload = function(){
		$scope.initParams();
		$scope.tableParams.reload();
	};
	$scope.tableParams = new ngTableParams({
		page: initpage,
		count: initrows
	}, {
		total: 0,
		getData: function($defer, params) {
			if(!$scope.queryParams.isreload){
				$scope.queryParams.page = params.page();
				$scope.queryParams.rows = params.count();
				$scope.queryParams.total = params.total();
			}else{
				$scope.queryParams.isreload = false;
			}
			FormDataListService.queryList($scope.queryParams, function(data){
				var total = data.pagingResult.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.pagingResult.rows);
			});
		}
	});
	
	 /*
	 * 删除
	 */
	$scope.del = function(formdata){
		if(confirm("确认删除?") === false) return;
		formdata.isdeling = true;
		FormDataService.del({id: formdata.id}, function(data){
			$scope.reload();
		});
	};
	  
	/*
	 * 加载表单信息
	 */
	FormDesignService.queryFormById({id: $routeParams.formid}, function(data){
		 $scope.formtitle = data.formResult.data.title;
		 $scope.formdtcreate = data.formResult.data.dtCreate;
		 $scope.formdtupdate = data.formResult.data.dtUpdate;
		 $scope.formdtpub = data.formResult.data.dtPub;
		 $scope.formFields = data.formFields;
	});
	
});