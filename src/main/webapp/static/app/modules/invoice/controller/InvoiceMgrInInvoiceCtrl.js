//发票管理-录入发票
app.controller('InvoiceMgrInInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "invoice";
	
	var invoiceId = $routeParams.id;
	$scope.isReady = false;
	
	if(invoiceId){//编辑
		//请求发票信息
		$http.post("invoice/getInvoiceViewById", {id: invoiceId}).success(function(data){
			$scope.invoice = data.invoice;
			$scope.isReady = true;
		});
	}else{//新增
		$scope.isReady = true;
	}
	
	//删除发票
	$scope.delCommun = function(item){
		$http.post("invoice/del", {id: item.id}).success(function(data){
			$scope.formresult = data;
		});
	};
	//保存发票信息
	$scope.save = function(){
		validFormAndSubmit(function(data){
			$scope.formresult = data;
		});
	};
	
	//验证表单并提交
	function validFormAndSubmit(cb){
		if(validForm()){
			$scope.isrequest = true;
			$http.post("invoice/edit", $scope.invoice).success(function(data){
				$scope.formresult = data;
				$scope.isrequest = false;
				if(cb) cb(data);
			});
		}

	};
	
	//验证表单
	function validForm(){
		return $("#invoiceeditform").isHappy({
			fields: {
				
			}
		});
	}
	
});