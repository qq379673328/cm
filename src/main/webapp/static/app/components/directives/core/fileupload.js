//文件上传指令
app.directive('coreFileupload', ["$http", function($http){
	return {
		restrict:'A',
		scope: {
			attas: "=attas"
		},
	  	require: '?ngModel',
	  	templateUrl:'static/app/components/directives/core/views/fileupload.html',
	  	link: function(scope, element, attrs, ngModel) {
	  		scope.$watch("attaresult", function(){
	  			var data = scope.attaresult;
	  			scope.attaformresult = data;
	  			
	  			if(data && data.success == "1"){
	  				if(!scope.attas){
	  					scope.attas = [];
	  				}
	  				scope.attas.push(data.data);
  					ngModel.$setViewValue(handleAttasId(scope.attas));
  				}
	  		});
	  		function handleAttasId(attas){
	  			var ret = "";
	  			if(attas && attas.length > 0){
	  				for(var i = 0; i < attas.length; i++){
	  					ret += attas[i]["id"];
	  					if(i != attas.length - 1){
	  						ret += ",";
	  					}
	  				}
	  			}
	  			return ret;
	  		}
	  		
	  		/**
	  		 * 删除附件
	  		 */
	  		scope.delAttach = function(atta){
	  			$http.post("fileDel", {id: atta.id}).success(function(data){
	  				scope.attaformresult = data;
	  				removeAtta(atta.id);
	  				ngModel.$setViewValue(handleAttasId(scope.attas));
	  			});
	  		};
	  		function removeAtta(id){
	  			var newAttas = [];
	  			for(var idx in scope.attas){
	  				var atta = scope.attas[idx];
	  				if(atta.id != id){
	  					newAttas.push(atta);
	  				}
	  			}
	  			scope.attas = newAttas;
	  		}
	  		
	  	}
	  };

}]);