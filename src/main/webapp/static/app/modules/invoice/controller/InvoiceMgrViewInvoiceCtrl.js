//发票管理-发票信息查看
app.controller('InvoiceMgrViewInvoiceCtrl',
		function($scope, $http, $routeParams, ngTableParams,
				$rootScope) {
	$rootScope.menu = "invoice";
	
	var invoiceId = $routeParams.id;
	$scope.isReady = false;
	
	//请求客户信息
	$http.post("invoice/getInvoiceViewById", {id: invoiceId}).success(function(data){
		$scope.invoice = data.invoice;
		$scope.isReady = true;
	});
	
});