angular.module('datepicker', []).directive('datepicker',[function(){
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl){
        	$(function(){
        		var format = "yy-mm-dd";
        		if(attrs.format){
        			format = attrs.format;
        		}
        		
        		var config = {
        				dateFormat: format,
                        changeMonth: true, 
                        changeYear: true, 
                        showButtonPanel: true,
                        onSelect:function (date) {
                            scope.$apply(function () {
                                ngModelCtrl.$setViewValue(date);
                            });
                        }};
        		
        		if(format == "yy-mm"){
        			config.closeText = "确定";
        			config.onClose = function(){
        				if(format == "yy-mm"){
                    		var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                    		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    		$(this).datepicker('setDate', new Date(year, month, 1));
                    		scope.$apply(function () {
                    			if(month && month.length == 1){
                    				month = "0" + month;
                    			}
                                ngModelCtrl.$setViewValue(year + '-' + month);
                            });
                    	}
        			};
        		}
        		config.currentText = "今天";
                element.datepicker(config);
                
                element.focus(function () {
                	if(format == "yy-mm"){
                		$(".ui-datepicker-calendar").hide();
                	}else{
                		$(".ui-datepicker-calendar").show();
                	}
                });
                
                //添加清空按钮
                var $clear = $('<i class="fa fa-refresh hover"></i>')
                	.click(function(){
                		element.val("");
                		scope.$apply(function () {
                            ngModelCtrl.$setViewValue("");
                        });
                	});
                element.after($clear);
            });
        }
    };
}]);