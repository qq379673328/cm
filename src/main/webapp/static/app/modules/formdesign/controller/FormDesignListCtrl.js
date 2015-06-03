//表单列表
app.controller('FormDesignListCtrl',function($scope, $http, ngTableParams,
		FormDesignListService, FormDesignService) {
	//重置
	$scope.reset = function(){
		$scope.searchTitle = null;
		$scope.dtCreateBegin = null;
		$scope.dtCreateEnd = null;
		$scope.dtPubBegin = null;
		$scope.dtPubEnd = null;
		$scope.ispub = null;
		$scope.pubtype = null;
	};
	//分页查询
	var initpage = 1,
		initrows = 10;
	$scope.queryParams = {
			page: initpage,
			rows: initrows,
			isreload: true
	};
	/*重新加载*/
	$scope.reload = function(){
		$scope.queryParams = {
				page: $scope.queryParams.page,
				rows: $scope.queryParams.rows,
				'dtcreate_begin': $scope.dtCreateBegin,
				'dtcreate_end': $scope.dtCreateEnd,
				'dtpub_begin': $scope.dtPubBegin,
				'dtpub_end': $scope.dtPubEnd,
				ispub: $scope.ispub,
				title: $scope.searchTitle,
				pubtype: $scope.pubtype,
				isreload: true
		};
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
			FormDesignListService.queryList($scope.queryParams, function(data){
				var total = data.pagingResult.total;
				$scope.queryParams.total = total;
				params.total(total);
				$defer.resolve(data.pagingResult.rows);
			});
		}
	});
	
	/*
	 * 发布
	 */
	$scope.pub = function(id, type){
		FormDesignService.pub({id: id, type: type}, function(){
			$scope.reload();
		});
	};
	/*
	 * 撤销发布
	 */
	$scope.canclepub = function(id){
		FormDesignService.canclepub({id: id}, function(){
			$scope.reload();
		});
	};
	/*
	 * 删除
	 */
	$scope.del = function(form){
		if(confirm("确认删除?") === false) return;
		form.isdeling = true;
		FormDesignService.del({id: form.id}, function(){
			$scope.reload();
		});
	};
	
});