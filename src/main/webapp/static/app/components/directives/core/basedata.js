//基础数据加载指令
app.directive('coreBasedata', ["$http", function($http){
	var baseData = {
			//客户行业
			customIndustry: [
			                 ["房地产开发/建筑与工程", "房地产开发/建筑与工程"],
			                 ["金融业(投资/保险/证券/银行/基金)", "金融业(投资/保险/证券/银行/基金)"]],
			//客户来源
			customSource: [
			               ["广告呼入", "广告呼入"],
			               ["主动BD", "主动BD"],
			               ["机构合作", "机构合作"],
			               ["人脉推荐", "人脉推荐"]],
           //客户状态
           customState: [
                          ["潜在客户", "潜在客户"],
                          ["签约运作", "签约运作"],
                          ["签约暂停", "签约暂停"],
                          ["签约终止", "签约终止"]]
	};
	
    return {
        restrict: 'A',
        scope: {
        	value: "@value",//值
        	type: "@type",
        	key: "@key",
        	name: "@name",
        	bindModel: "=bindModel",
        	allSelectName: "@allSelectName"
        },
        templateUrl:'static/app/components/directives/core/views/basedata.html',
        replace: true,
        require: '?ngModel',
        link: function($scope, $el, attrs, ngModel){
        	$scope.items = baseData[$scope.key];
        	
        	$scope.click = function(val){
        		ngModel.$setViewValue(val);
        	};
        }
    };
}]);