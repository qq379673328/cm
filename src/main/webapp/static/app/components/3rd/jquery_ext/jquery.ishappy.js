
/*global $*/
(function happyJS($) {
    function trim(el) {
        return (''.trim) ? el.val().trim() : $.trim(el.val());
    }
    $.fn.isHappy = function isHappy(config) {
        var fields = [], item = null;
        var $form = $(this);
        
        var methods = {
    		required: function( value, element, param ) {
    			if(param == false){
    				return true;
    			}
    			return $.trim( value ).length > 0;
    		},
    		email: function( value, element ) {
    			return optional( element ) || /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test( value );
    		},
    		url: function( value, element ) {
    			return optional( element ) || /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test( value );
    		},
    		date: function( value, element ) {
    			return optional( element ) || !/Invalid|NaN/.test( new Date( value ).toString() );
    		},
    		dateISO: function( value, element ) {
    			return optional( element ) || /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/.test( value );
    		},
    		number: function( value, element ) {
    			return optional( element ) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( value );
    		},
    		digits: function( value, element ) {
    			return optional( element ) || /^\d+$/.test( value );
    		},
    		minlength: function( value, element, param ) {
    			var length = $.isArray( value ) ? value.length : getLength( value, element );
    			return optional( element ) || length >= param;
    		},
    		maxlength: function( value, element, param ) {
    			var length = $.isArray( value ) ? value.length : getLength( value, element );
    			return optional( element ) || length <= param;
    		},
    		rangelength: function( value, element, param ) {
    			var length = $.isArray( value ) ? value.length : getLength( value, element );
    			return optional( element ) || ( length >= param[ 0 ] && length <= param[ 1 ] );
    		},
    		min: function( value, element, param ) {
    			return optional( element ) || value >= param;
    		},
    		max: function( value, element, param ) {
    			return optional( element ) || value <= param;
    		},
    		range: function( value, element, param ) {
    			return optional( element ) || ( value >= param[ 0 ] && value <= param[ 1 ] );
    		},
    		equalTo: function( value, element, param ) {
    			var target = $( param );
    			return optional( element ) || value === target.val();
    		},
    		telephone: function(value, element){//电话校验（包括手机和固话）
    			var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;//固话
    			//var isMob =/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;//手机
    			var isMob =/^[012356789]{11}$/;//手机
    			return optional(element)||isPhone.test(value)||isMob.test(value);
    		},
    		idCard: function(value,element){//身份证
    			var isIdCard = /^\d{14}(\d|\d{4}|\d{3}[a-z])$/;
    			return optional(element)||isIdCard.test(value);
    		},
    		username: function(value, element) {//用户名验证
    			var usernameRep = /^[\w\d_]{6,20}$/;
    			return optional(element) || usernameRep.test(value);         
    		}
        };
        function format( source, params ) {
        	if ( arguments.length === 1 ) {
        		return function() {
        			var args = $.makeArray( arguments );
        			args.unshift( source );
        			return format.apply( this, args );
        		};
        	}
        	if ( arguments.length > 2 && params.constructor !== Array  ) {
        		params = $.makeArray( arguments ).slice( 1 );
        	}
        	if ( params.constructor !== Array ) {
        		params = [ params ];
        	}
        	$.each( params, function( i, n ) {
        		source = source.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
        			return n;
        		});
        	});
        	return source;
        };
        var methodMessage = {
    		required: "内容不能为空",
    		remote: "请修正内容",
    		email: "请输入正确格式的电子邮件",
    		url: "请输入合法的网址",
    		date: "请输入合法的日期",
    		dateISO: "请输入合法的日期 (例：1999-01-01)",
    		number: "请输入合法的数字",
    		digits: "只能输入整数",
    		creditcard: "请输入合法的信用卡号",
    		equalTo: "请再次输入相同的值",
    		accept: "请输入拥有合法后缀名的字符串",
    		maxlength: format("请输入一个 长度最多是 {0} 的字符串"),
    		minlength: format("请输入一个 长度最少是 {0} 的字符串"),
    		rangelength: format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
    		range: format("请输入一个介于 {0} 和 {1} 之间的值"),
    		max: format("请输入一个最大为 {0} 的值"),
    		min: format("请输入一个最小为 {0} 的值"),
    		telephone: "请填写正确的电话号码",
    		idCard: "请填写正确的身份证号",
    		username:  "用户名只能由6到20位字母、下划线、数字组成"
        };
        
        function getLength( value, element ) {
			switch ( element.nodeName.toLowerCase() ) {
			case "select":
				return $( "option:selected", element ).length;
			case "input":
				if ( checkable( element ) ) {
					return findByName( element.name ).filter( ":checked" ).length;
				}
			}
			return value.length;
		}
        function checkable( element ) {
			return ( /radio|checkbox/i ).test( element.type );
		}
        function findByName( name ) {
			return $form.find( "[name='" + name + "']" );
		}
        function optional( element ) {
			var val = elementValue( element );
			return !methods.required.call( this, val, element ) && "dependency-mismatch";
		}
        function elementValue( element ) {
			var val,
				$element = $( element ),
				type = element.type;
			if ( type === "radio" || type === "checkbox" ) {
				return $form.find( "input[name='" + element.name + "']:checked" ).val();
			} else if ( type === "number" && typeof element.validity !== "undefined" ) {
				return element.validity.badInput ? false : $element.val();
			}
			val = $element.val();
			if ( typeof val === "string" ) {
				return val.replace(/\r/g, "" );
			}
			return val;
		}
		
        function isFunction(obj) {
            return !!(obj && obj.constructor && obj.call && obj.apply);
        }
        function defaultError(error) { //Default error template
            var msgErrorClass = config.classes && config.classes.message || 'unhappyMessage';
            return $('<div id="' + error.id + '" class="' + msgErrorClass + '" role="alert">' + error.message + '</div>');
        }
        function getError(error) { //Generate error html from either config or default
            if (isFunction(config.errorTemplate)) {
                return config.errorTemplate(error);
            }
            return defaultError(error);
        }
        //处理表单项
        function processField(opts, selector, $field) {
        	
        	var flag = true;
        	if(!$field){
        		//分开选择器选择以提升效率
        		$field = $form.find("input[name=" + selector + "]");
        		if($field.length == 0){
        			$field = $form.find("select[name=" + selector + "]");
        		}
        		if($field.length == 0){
        			$field = $form.find("textarea[name=" + selector + "]");
        		}
        	}
        	
            //验证对象过滤
            if(!$field || $field.length == 0){
            	return true;
            }
            $.each(opts, function(key, val){
            	var element = $field[0];
            	if(methods[key]){
            		var re = methods[key](elementValue(element), element, val);
            		if(re == false && !$field.is(":hidden")){//验证未通过并且数据项显示
            			flag = false;
            			
        				var message = methodMessage[key],
            				theregex = /\$?\{(\d+)\}/g;
        				if ( typeof message === "function" ) {
        					message = message.call( this, val, element );
        				} else if ( theregex.test( message ) ) {
        					message = format( message.replace( theregex, "{$1}" ), val );
        				}
        				if($field.length > 1){
        					$field.parent().append("<div class='valid-error'>" + message + "</div>");
        				}else{
        					$field.after("<div class='valid-error'>" + message + "</div>");
        				}
            		}
            	}
            });
            
            return flag;
        }
        
        var ret = true;
        
        //移除所有验证提示
        $form.find(".valid-error").remove();
        
        for (item in config.fields) {
            var r = processField(config.fields[item], item);
            if(r == false){
            	ret = false;
            }
        }
       
        //处理子表动态添加的验证规则
        var rulesData = $form.data("rulesData");
        if(rulesData && rulesData.length > 0){
        	for(var i in rulesData){
        		var ruleData = rulesData[i];
        		var r = processField(ruleData["rules"], null, ruleData["$el"]);
                if(r == false){
                	ret = false;
                }
        	}
        }
        
        return ret;
    };
})(this.jQuery || this.Zepto);

/*
 * 动态添加验证规则
 */
(function addRules($) {
	$.fn.rules = function rules(method, fieldRules) {
		var $this = $(this),
			$form = $this.parents("form");
		if(method == "add"){//添加
			var rulesData = $form.data("rulesData");
			if(!rulesData){
				rulesData = [];
			}
			rulesData.push({
				$el: $this,
				rules: fieldRules
			});
			$form.data("rulesData", rulesData);
		}
	};
})(this.jQuery || this.Zepto);
	