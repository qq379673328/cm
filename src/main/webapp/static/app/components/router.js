var fdRouterViewsBasepath = "static/app/modules/";

//路由
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/', {//首页
		templateUrl : fdRouterViewsBasepath + 'index/views/index.html',
		controller : 'IndexCtrl'
	})
	
	.when('/custommgr/list', {//客户管理-列表页
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrlist.html',
		controller : 'CustomMgrListCtrl'
	})
	.when('/custommgr/incustom', {//客户管理-录入客户
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrincustom.html',
		controller : 'CustomMgrInCustomCtrl'
	})
	.when('/custommgr/editcustom/:id', {//客户管理-编辑客户
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrincustom.html',
		controller : 'CustomMgrInCustomCtrl'
	})
	.when('/custommgr/viewcustom/:id', {//客户管理-客户信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/custommgrviewcustom.html',
		controller : 'CustomMgrViewCustomCtrl'
	})
	
	.when('/jobmgr/list', {//职位管理-列表页
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrlist.html',
		controller : 'JobMgrListCtrl'
	})
	.when('/jobmgr/injob', {//职位管理-录入职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/editjob/:id', {//职位管理-编辑职位
		templateUrl : fdRouterViewsBasepath + 'job/views/jobmgrinjob.html',
		controller : 'JobMgrInJobCtrl'
	})
	.when('/jobmgr/viewjob/:id', {//职位管理-职位信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/jobmgrviewjob.html',
		controller : 'JobMgrViewJobCtrl'
	})
	
	.when('/resumemgr/list', {//简历管理-列表页
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrlist.html',
		controller : 'ResumeMgrListCtrl'
	})
	.when('/resumemgr/inresume', {//简历管理-录入简历
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrinresume.html',
		controller : 'ResumeMgrInResumeCtrl'
	})
	.when('/resumemgr/editresume/:id', {//简历管理-编辑简历
		templateUrl : fdRouterViewsBasepath + 'resume/views/resumemgrinresume.html',
		controller : 'ResumeMgrInResumeCtrl'
	})
	.when('/resumemgr/viewresume/:id', {//简历管理-简历信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/resumemgrviewresume.html',
		controller : 'ResumeMgrViewResumeCtrl'
	})
	
	.when('/contractmgr/list', {//合同管理-列表页
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrlist.html',
		controller : 'ContractMgrListCtrl'
	})
	.when('/contractmgr/incontract', {//合同管理-录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/incontract/:customid', {//合同管理-为指定客户录入合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/editcustom/:id', {//合同管理-编辑合同
		templateUrl : fdRouterViewsBasepath + 'contract/views/contractmgrincontract.html',
		controller : 'ContractMgrInContractCtrl'
	})
	.when('/contractmgr/viewcontract/:id', {//合同管理-合同信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/contractmgrviewcontract.html',
		controller : 'ContractMgrViewContractCtrl'
	})
	
	.when('/invoicemgr/list', {//发票管理-列表页
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrlist.html',
		controller : 'InvoiceMgrListCtrl'
	})
	.when('/invoicemgr/ininvoice', {//发票管理-录入发票
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrininvoice.html',
		controller : 'InvoiceMgrInInvoiceCtrl'
	})
	.when('/invoicemgr/editcustom/:id', {//发票管理-编辑发票
		templateUrl : fdRouterViewsBasepath + 'invoice/views/invoicemgrininvoice.html',
		controller : 'InvoiceMgrInInvoiceCtrl'
	})
	.when('/invoicemgr/viewinvoice/:id', {//发票管理-发票信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/invoicemgrviewinvoice.html',
		controller : 'InvoiceMgrViewInvoiceCtrl'
	})
	
	.when('/performancemgr/list', {//绩效管理-列表页
		templateUrl : fdRouterViewsBasepath + 'performance/views/performancemgrlist.html',
		controller : 'PerformanceMgrListCtrl'
	})
	
	.when('/teammgr/list', {//团队管理-列表页
		templateUrl : fdRouterViewsBasepath + 'team/views/teammgrlist.html',
		controller : 'TeamMgrListCtrl'
	})
	.when('/teammgr/myinfo', {//团队管理-我的信息
		templateUrl : fdRouterViewsBasepath + 'team/views/teammgrmyinfo.html',
		controller : 'TeamMgrMyInfoCtrl'
	})
	
	.when('/usermgr/list', {//用户管理-列表页
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrlist.html',
		controller : 'UserMgrListCtrl'
	})
	.when('/usermgr/inuser', {//用户管理-录入用户
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrinuser.html',
		controller : 'UserMgrInUserCtrl'
	})
	.when('/usermgr/editcustom/:id', {//用户管理-编辑用户
		templateUrl : fdRouterViewsBasepath + 'user/views/usermgrinuser.html',
		controller : 'UserMgrInUserCtrl'
	})
	.when('/usermgr/viewuser/:id', {//用户管理-用户信息
		templateUrl : fdRouterViewsBasepath + 'custom/views/usermgrviewuser.html',
		controller : 'UserMgrViewUserCtrl'
	})
	
	.when('/usermgr/resetpwd', {//修改密码管理-列表页
		templateUrl : fdRouterViewsBasepath + 'user/views/userresetpwd.html',
		controller : 'UserResetPwdCtrl'
	})
	
	.when('/pubmgr/list', {//公告管理-列表页
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgrlist.html',
		controller : 'PubMgrListCtrl'
	})
	.when('/pubmgr/inpub', {//公告管理-录入公告
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgrinpub.html',
		controller : 'PubMgrInPubCtrl'
	})
	.when('/pubmgr/editpub', {//公告管理-编辑公告
		templateUrl : fdRouterViewsBasepath + 'pub/views/pubmgreditpub.html',
		controller : 'PubMgrEditPubCtrl'
	})
	
	.otherwise({
		redirectTo : '/'
	});
} ]);