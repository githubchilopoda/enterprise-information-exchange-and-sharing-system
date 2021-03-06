<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">
	<meta charset="utf-8">
	<title>限制对象提前解除审核查询</title>
	<link rel="stylesheet" href="/css/reg.server.css">
	<link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/js/lib/jquerymultiselect/jquery.multiselect.css">
</head>
<body class="pd10">
	<div class="form-box mb5">
		<input type="hidden" value="${sysdate }" id="sysdate"/>
		<div class="form-list">
		  <form id="entSearchForm">
			<div class="form-item clearfix">
				<div class="col-4">
                    <label class="item-name col-6">限制对象姓名：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                        	<input type="text" class="ipt-txt clx" name="clientName" placeholder="可输入关键词查询" />
                        </div>
                    </div>
                </div>
				<div class="col-4">
					<label class="item-name col-6">失信受限企业统一代码/注册号：</label>
					<div class="col-6">
						<div class="ipt-box col-12">
							<input type="text" class="ipt-txt clx" name="regNO" placeholder="精确输入" />
						</div>
					</div>
				</div>
<!-- 				<div class="col-4"> -->
<!-- 					<label class="item-name col-4">限制执行部门：</label> -->
<!-- 					<div class="col-7"> -->
<!-- 						<div class="ipt-box col-12"> -->
<!-- 							<input type="hidden" class="ipt-txt" name="activeDep" id="activeDep" value=""/> -->
<!--                             <input type="text" readonly="readonly" class="ipt-txt" id="activeDepName" value="" /> -->
<!-- 	                        <span class="add-icon" id="chooseActiveDep"> -->
<!--                                 <i></i> -->
<!-- 	                        </span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			<div class="form-item clearfix">
				<div class="col-4">
                    <label class="item-name col-6">限制对象证件号：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt" id="certNO" name="certNO" value="" placeholder="精确输入" />
                        </div>
                    </div>
	             </div>
				<div class="col-4">
					<label class="item-name col-6">失信受限企业名称：</label>
					<div class="col-6">
						<div class="ipt-box col-12">
							<input type="text" class="ipt-txt clx" name="entName" placeholder="精确输入" />
						</div>
					</div>
				</div>
<!-- 				<div class="col-4"> -->
<!-- 					<label class="item-name col-4">限制解除部门：</label> -->
<!-- 					<div class="col-7"> -->
<!-- 						<div class="ipt-box col-12"> -->
<!-- 							<input type="hidden" class="ipt-txt" name="removeDept" id="removeDept" value=""/> -->
<!--                             <input type="text" readonly="readonly" class="ipt-txt" id="removeDeptName" value="" /> -->
<!-- 	                        <span class="add-icon" id="chooseRemoveDept"> -->
<!--                                 <i></i> -->
<!-- 	                        </span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			<div class="form-item clearfix">
				<div class="col-4">
					<label class="item-name col-6">审核状态：</label>
					<div class="col-6">
						<div class="ipt-box col-12">
                            <select id="aduitState" name="aduitState">
                                <option value=''>全部</option>
                                <option value='0'>待审核</option>
                                <option value='1'>审核通过</option>
                                <option value='2'>审核不通过</option>
                            </select>
	            		</div>
					</div>
				</div>
				<div class="col-4">
						<label class="item-name col-6">解除日期：</label>
						<div class="col-6">
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeDateStart">
							</div>
							<span class="item-txt col-2"><i class="line-icon"></i></span>
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeDateEnd">
							</div>
						</div>
					</div>
				<div class="col-4">
					<label class="item-name col-4">限制解除状态：</label>
					<div class="col-7">
						<div class="ipt-box col-12">
		            		<input type="hidden" class="ipt-txt" name="freeState" id="freeState" value=""/>
	                        <select name="freeStateM" id="freeStateM" multiple="multiple">
	                            <option value='1'>未解除</option>
	                            <option value='2'>已解除</option>
	                        </select>
	            		</div>
					</div>
				</div>
			</div>
			<div class="more-show " style="display:none;">
				<div class="form-item clearfix">
					<div class="col-4">
	                    <label class="item-name col-6">解除申请人：</label>
	                    <div class="col-6">
	                        <div class="ipt-box col-12">
			                 <input type="text"  id="removeAppLyName" name="removeAppLyName"  class="ipt-txt clx" value=""/>
	                        </div>
	                    </div>
	                </div>
	           		<div class="col-4">
						<label class="item-name col-6">限制日期：</label>
						<div class="col-6">
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="limitDateStart">
							</div>
							<span class="item-txt col-2"><i class="line-icon"></i></span>
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="limitDateEnd">
							</div>
						</div>
					</div>
					<div class="col-4">
						<label class="item-name col-4">限制原因类别：</label>
						<div class="col-7">
							<div class="ipt-box col-12">
	                            <input type="hidden" class="ipt-txt" name="limitType" id="limitType" value=""/>
	                            <select id="limitTypeM" multiple="multiple">
	                                <option value='0'>吊销企业</option>
	                                <option value='4'>严重违法失信企业（黑名单）</option>
	                            </select>
		            		</div>
						</div>
					</div>
	             </div>
	             	<div class="form-item clearfix">
					<div class="col-4">
	                    <label class="item-name col-6">解除审核人：</label>
	                    <div class="col-6">
	                        <div class="ipt-box col-12">
			                 <input type="text"  id="removeAuditName" name="removeAuditName"  class="ipt-txt clx" value=""/>
	                        </div>
	                    </div>
	                </div>
	           		<div class="col-4">
						<label class="item-name col-6">解除申请日期：</label>
						<div class="col-6">
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeAppLyDateStart">
							</div>
							<span class="item-txt col-2"><i class="line-icon"></i></span>
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeAppLyDateEnd">
							</div>
						</div>
					</div>
	           		<div class="col-4">
						<label class="item-name col-4">解除审核日期：</label>
						<div class="col-7">
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeAuditDateStart">
							</div>
							<span class="item-txt col-2"><i class="line-icon"></i></span>
							<div class="ipt-box col-5">
								<input type="text" class="ipt-txt clx" readonly="readonly" onclick="laydate();" name="removeAuditDateEnd">
							</div>
						</div>
					</div>
	             </div>
			</div>
			<div class="form-item clearfix">
				<div class="btn-box mt10">
					<input type="button" value="查 询" class="btn mr20" id="search"> 
					<input type="button" id="cancle" onclick="$('#entSearchForm')[0].reset();" value="重置" class="btn mr20">
					<input type="button" value="更多查询" class="btn mr20 js-more-query">
				</div>
			</div>
			</form>
		</div>
	</div>
	
	<div class="light-info mb5">
	    <span class="light">提示：此处仅查询由工商部门负责的任职资格限制提前解除记录信息。</span>	    
	</div>
	<p class="tip-info left mb5">查询结果：
        限制对象共<span id="clientNameCount" class="light-blue">0</span>人,
        限制原因企业共有<span id="pripidCount" class="light-blue">0</span>家。
    </p>
	<div>
		<table id="search-table" border="0" cellspacing="0" cellpadding="0" class="table-row perc-100  mt30 nowrap" style="width:100%;">
			<thead >
			<tr>
				<th style="padding: 0 20px;">序号</th>
				<th >操作</th>
				<th >姓名</th>
				<th >证件号码</th>
				<th >限制原因类别</th>
				<th >失信受限企业</th>
				<th >失信受限企业统一信用代码/注册号</th>
				<th >限制原因</th>
				<th >执行文号</th>
				<th >执行部门</th>
				<th >审核状态</th>
				<th >限制解除状态</th>
				<th >限制开始日期</th>
				<th >限制结束日期</th>
				<th >限制解除日期</th>
				<th >申请人</th>
				<th >申请日期</th>
				<th >审核人</th>
				<th >审核日期</th>
				<th >审核部门</th>
			</tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			</thead>
		</table>
	</div>
	<script src="/js/lib/laydate/laydate.js"></script>
	<script src="/js/lib/require.js"></script>
	<script src="/js/config.js"></script>
	<script src="/js/reg/server/registmanage/qualifiedmanage/limitremoveaudit_list.js"></script>
</body>
</html>