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
                          ["签约终止", "签约终止"]],
           //合同状态
           contractState: [
                        ["运作", "运作"],
                        ["暂停", "暂停"],
                        ["终止", "终止"]],
          //职位状态
            jobState: [
	                     ["运作", "运作"],
	                     ["结束", "结束"],
                        ["暂停", "暂停"],
                        ["关闭", "关闭"]],
			 //全国省市
		    zone: [
		                 ["北京", "北京"],
		                ["上海", "上海"],
		                ["广州", "广州"]],
            //职业
		    duty: [
		                 ["经营管理类", "经营管理类"],
		                ["财务/审计/统计类", "财务/审计/统计类"],
		                ["人力资源类", "人力资源类"]],
            //学历
            edu: [
                   ["博士以上", "博士以上"],
                   ["硕士以上", "硕士以上"],
                   ["本科以上", "本科以上"],
                   ["大专以上", "大专以上"]
                   ],
			//年薪
		   yearpay: [
			       ["10万以下", "10万以下"],
			       ["10-15万", "10-15万"],
			       ["15-20万", "15-20万"],
			       ["20-30万", "20-30万"],
			       ["30-50万", "30-50万"],
			       ["50-100万", "50-100万"],
			       ["100-150万", "100-150万"],
			       ["150-200万", "150-200万"],
			       ["200万以上", "200万以上"]
			       ]
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
        	
        	$scope.click = function(val ,target){
        		ngModel.$setViewValue(val);
        		if(target){
        			var $target = $(target);
        			$target.siblings().removeClass("select");
        			$target.addClass("select");
        		}
        	};
        }
    };
}]);