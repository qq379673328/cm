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
				//申请人
				team: {required: true},
				//开票人
				checkUser: {required: true},
				//申请时间
				applyTime: {required: true, date: true},
				//客户+合同编号
				customContract: {required: true, maxlength: 100},
				//发票类型
				type: {required: true, maxlength: 50},
				//发票属性
				property: {required: true, maxlength: 100},
				//发票金额
				total: {required: true, number: true},
				//发票状态
				state: {required: true, maxlength: 100},
				//回款状态
				incomeState: {required: true, maxlength: 50},
				//备注
				comment: {maxlength: 500}
			}
		});
	}
	
});