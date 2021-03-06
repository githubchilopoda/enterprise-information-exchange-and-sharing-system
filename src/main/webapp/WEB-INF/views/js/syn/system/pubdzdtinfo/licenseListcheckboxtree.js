require(['common/util', 'component/iframeLayer', 'common/http', 'handlebars', 'layer1', 'ztree','ztreeCheck'], function (util, layer, http, handlebars) {
    init();
    /**
     * 初始化函数集合
     */
    function init() {
        bind();
        initCodeLicenseTree(null);
      }
    
    var ycCodeLicensetreeSetting = {
    		check: {
				enable: true,
//				chkStyle: "checkbox",
				chkStyle:$("#isRadio").val() =='true'?'radio':'checkbox',
				radioType: "level"
			},
			data: {
				simpleData: {
					enable: true
				}
			} 
		}; 
    function initCodeLicenseTree(licConent) {
    	var data;
        if($("#isLisence").val() =='true'){
        	data = {"licConent":licConent,"licType":''};
        }else{
        	data =  {"licConent":licConent,"licType":1};
        }
        http.httpRequest({
            type: 'post',
            data: data,
//            url: '/syn/pubdtinfo/licenseListTreeJSON',
            url: window._CONFIG.chooseUrl+'/pubdzdtinfo/syslicenseListTreeJSON',
            success: function (data) {
                
            	if (data.status == 'success') {
                	//初始化权限树
                    treeObj = $.fn.zTree.init($('#ycCodeLicenseTree'), ycCodeLicensetreeSetting, data.data);
                    var treeObj = $.fn.zTree.getZTreeObj("ycCodeLicenseTree");
					  //展开树
				    treeObj.expandAll(true);
				    if(data.data != null && data.data.length == 1){
    					layer.msg("当前条件下没有查询到备案事项", {ltype: 0,time:2000}); 
				    }
                }
            }
        });
    }

    function bind() {
        util.bindEvents([{
            el: '#tree-box-searcher',
            event: 'click',
            handler: function () {
            	var licConent=$.trim($("#tree-box-searchertree_Njjg").val());
            	initCodeLicenseTree(licConent);
            }
        },
        {
            el: '#codelicenseSubmit',
            event: 'click',
            handler: function () {
            	var treeObj = $.fn.zTree.getZTreeObj("ycCodeLicenseTree");
    				var nodes = treeObj.getCheckedNodes(true); 
    				if(nodes.length == 0){
    					layer.msg("请选择备案事项", {ltype: 0,time:2000}); 
    					return false;
    				}else{ 
    					 if(nodes.length==1 &&nodes[0].pcode==0){
     						 layer.msg("必须选择具体的备案事项", {ltype: 0,time:2000});
    						 return false;
    					 } 
    					var codeStr="";
 						var contentStr="";
  						for(var a=0;a<nodes.length;a++){ 
 							codeStr+=nodes[a].id+",";
 							contentStr+=nodes[a].name+",";
 							licDept = nodes[a].licdept+",";
  						}  
 						if(codeStr!=""&&contentStr!=""){
 							codeStr=codeStr.substr(0,codeStr.length-1);
 							contentStr=contentStr.substr(0,contentStr.length-1); 
 							licDept=licDept.substr(0,licDept.length-1); 
 						}
 						var data=new Object(); 
 						data.code=codeStr;
 						data.content=contentStr;
 						data.licDept=licDept;
  						layer.close({reload:data});
    			 }
            }
        },
        {
            el: '#codelicenseClose',
            event: 'click',
            handler: function () {
            	layer.close(); 
            }
        }
        ])
    }
})