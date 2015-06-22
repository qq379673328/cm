//文件上传指令-内部
app.directive('coreFu', ["$http", function($http){
	return {
		restrict:'A',
		require: '?ngModel',
	  	link: function(scope, element, attrs, ngModel) {
	  		var optionsObj = {
	  			dataType: 'json',
	  			url: "fileUpload"
	  		};
	  		//开始上传
	  		optionsObj.started = function(e, data){
	  			scope.$apply(function(){
  					ngModel.$setViewValue({
  						success: "0",
  						message: "上传中..."
  					});
  				});
	  		};
	  		//上传成功
  			optionsObj.done = function(e, data) {
  				scope.$apply(function(){
  					ngModel.$setViewValue(data.result);
  				});
  			};
  			//上传失败
  			optionsObj.fail = function(e, data) {
  				scope.$apply(function(){
  					ngModel.$setViewValue({
  						success: "0",
  						message: "上传失败"
  					});
  				});
  			};
	  		//以上内容可以简单地在一个循环中完成，这里是为了覆盖Fileupload控件所提供的API
	  		element.fileupload(optionsObj);
	  	}
	  };

}]);