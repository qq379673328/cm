app.directive('corePermission', ["BaseInfoService", function(BaseInfoService){
    return {
        restrict: 'A',
        link: function(scope, element, attrs, ctrl) {
        	var oldStyle = element.style.display;
        	//先隐藏节点
        	element.style.display = "none";
        	BaseInfoService.getUserInfo(function(userInfo){
        		//角色-用户类型
        		var rolePass = false;
        		var fundIdPass = false;
        		var userType = userInfo.userType;
        		if(userType != "1"){//超级管理员直接过滤
        			//角色-以","分隔
            		var rolesSp = attrs.roles.split(",");
            		if(rolesSp.length > 0){
            			for(var i in rolesSp){
            				var role = rolesSp[i];
            				if(role == userType){
            					rolePass = true;
            					break;
            				}
            			}
            		}
                	
                	//功能点id
                	var funId = attrs.funid;
                	
                	fundIdPass = true;//TODO 待实现
                	
                	//是否验证通过
                	if(rolePass && fundIdPass){
                		//恢复显示
                		element.style.display = oldStyle;
                	}
        		}else{
        			element.style.display = oldStyle;
        		}
        		
            	
            	
        	});
        	
        }
    };
}]);