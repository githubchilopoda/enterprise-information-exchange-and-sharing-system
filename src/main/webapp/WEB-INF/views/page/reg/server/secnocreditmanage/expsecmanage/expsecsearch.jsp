<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>异常类严违名单检索</title>
    <link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/css/reg.server.css">
</head>
<body class="pd10">
<div class="form-box pd14-mr">
    <form id="searchForm" class="searchForm">
        <div class="form-list">
            <div class="form-item clearfix">
                <div class="col-4">
                    <label class="item-name col-5">
                        <span class="long-label">统一信用代码/注册号：</span>
                    </label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt clearall" name="regNO">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">企业名称：</label>
                    <div class="col-7">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt clearall" name="entName">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">
                        <span class="long-label">法定代表人/负责人：</span>
                    </label>
                    <div class="col-7">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt clearall" name="leRep">
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="form-item clearfix">
                <div class="col-4">
                    <label class="item-name col-5">业务类型：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <select name="businessType" class="clearall">
                                <option value="">全部</option>
                                <option value="1">提醒公告</option>
                                <option value="2">列入</option>
                                <option value="3">届满移出</option>
                                <option value="4">届满延期</option>
                                <option value="5">更正移出</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">列入异常日期：</label>
                    <div class="col-7">
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="abnTimeStart" value="">
                        </div>
                        <span class="item-line col-05">-</span>
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="abnTimeEnd" value="">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">列入严违日期：</label>
                    <div class="col-7">
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="addSecDateStart" value="">
                        </div>
                        <span class="item-line col-05">-</span>
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="addSecDateEnd" value="">
                        </div>
                    </div>
                </div>
<!-- 					<div class="col-4"> -->
<!--                     <label class="item-name col-5">移出严违原因：</label> -->
<!--                     <div class="col-7"> -->
<!--                         <div class="ipt-box col-12"> -->
<!--                             <select name="" class="clearall"> -->
<!--                                 <option value="">全部</option> -->
<!--                                 <option value="1">新设</option> -->
<!--                                 <option value="2">变更</option> -->
<!--                             </select> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
            <div class="form-item clearfix">
                <div class="col-4">
                    <label class="item-name col-5">业务状态：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <select name="businessStatus" class="clearall">
                                <option value="">全部</option>
                                <option value="0">待列入</option>
                                <option value="1">待初审</option>
                                <option value="2">初审不通过</option>
                                <option value="3">待审核</option>
                                <option value="4">审核通过</option>
                                <option value="5">审核不通过</option>
                                <option value="6">待移出</option>
                                <option value="7">待延期</option>
                                <option value="8">异常将届满</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">异常届满日期：</label>
                    <div class="col-7">
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="expExpiredDateStart" value="">
                        </div>
                        <span class="item-line col-05">-</span>
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="expExpiredDateEnd" value="">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">严违届满日期：</label>
                      <div class="col-7">
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="secExpiredDateStart" >
                        </div>
                        <span class="item-line col-05">-</span>
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="secExpiredDateEnd" >
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-item clearfix">
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">公示状态：</label> -->
<!--                     <div class="col-6"> -->
<!--                         <div class="ipt-box col-12 clearall"> -->
<!--                             <select name=""> -->
<!--                                 <option value="">全部</option> -->
<!--                                 <option value="0">未认领</option> -->
<!--                                 <option value="1">已认领</option> -->
<!--                             </select> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
				<div class="col-4">
                    <label class="item-name col-5">登记状态：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <select name="regState" class="clearall">
                                <option value="">全部</option>
                                <option value="KABDA">存续</option>
                                <option value="X">注销</option>
                                <option value="C">撤销</option>
                                <option value="D">吊销</option>
                                <option value="Q">迁出</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">异常届满前：</label>
                    <div class="col-7">
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="expExpiredBeforeDateStart" value="">
                        </div>
                        <span class="item-line col-05">-</span>
                        <div class="ipt-box col-575">
                            <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()"
                                   name="expExpiredBeforeDateEnd" value="">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5">企业管理部门：</label>
                    <div class="col-7">
                        <div class="ipt-box col-12">
                            <input type="hidden" class="ipt-txt clearall" name="localAdm" id="localAdm"/>
                            <input type="text" class="ipt-txt clearall" id="localAdmName" placeholder="请选择企业管理部门"/>
	                            <span class="add-icon" id="choseregUnit">
	                                <i></i>
	                            </span>
                        </div>
                    </div>
                </div>
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">移出严违日期：</label> -->
<!--                     <div class="col-7"> -->
<!--                         <div class="ipt-box col-575"> -->
<!--                             <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()" -->
<!--                                    name="" value=""> -->
<!--                         </div> -->
<!--                         <span class="item-line col-05">-</span> -->
<!--                         <div class="ipt-box col-575"> -->
<!--                             <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()" -->
<!--                                    name="" value=""> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->

            </div>
            <div class="form-item clearfix">
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">提醒公告发布状态：</label> -->
<!--                     <div class="col-6"> -->
<!--                         <div class="ipt-box col-12"> -->
<!--                             <input type="text" name="" class="ipt-txt clearall"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">提醒公告发布日期：</label> -->
<!--                     <div class="col-7"> -->
<!--                         <div class="ipt-box col-575"> -->
<!--                             <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()" -->
<!--                                    name="" value=""> -->
<!--                         </div> -->
<!--                         <span class="item-line col-05">-</span> -->
<!--                         <div class="ipt-box col-575"> -->
<!--                             <input type="text" class="ipt-txt laydate-icon clearall" readonly="readonly" onclick="laydate()" -->
<!--                                    name="" value=""> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
                
            </div>
            <div class="form-item clearfix">
                
                <div class="col-4">
                    <label class="item-name col-5">列入异常原因：</label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <input type="hidden" class="ipt-txt clearall" name="speCause" id="speCause"/>
                            <input type="text" class="ipt-txt text-over clearall" id="SpeCauseCN" value="" placeholder="请选择列入异常原因"/>
	                            <span class="add-icon" id="chosespeCause">
	                                <i></i>
	                            </span>
                        </div>
                    </div>
                </div>
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">严违届满延期：</label> -->
<!--                     <div class="col-7"> -->
<!--                         <div class="ipt-box col-12"> -->
<!--                             <input type="text" name="" class="ipt-txt clearall"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-4"> -->
<!--                     <label class="item-name col-5">严违公示日期：</label> -->
<!--                     <div class="col-7"> -->
<!--                         <div class="ipt-box col-12"> -->
<!--                             <input type="text" name="" class="ipt-txt clearall"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
            <div class="form-item clearfix">
                

            </div>
            <div class="form-item">
                <div class="btn-box">
                    <input type="button" id="search" value="查 询" class="btn mr20">
                    <input type="button" id="reset" value="重 置" class="btn">
                </div>
            </div>
        </div>
    </form>
</div>
<div class="light-info mt5">
    <span class="light">提示：此处查询范围仅限被列入经营异常名录届满3年的企业。“异常届满”指企业列入经营异常名录满3年，“列入严违期限”指企业列入异常届满之日起第10个工作日。</span>
</div>
<div class="clearfix mb5 mt5">
    <span class="light-yellow fr mt5">查询结果：待列入<span>${dlrNum}</span>条，待移出<span>${dycNum}</span>条，待延期<span>${dyqNum}</span>条</span>
</div>

<div class="table-out">
    <div class="iframe-wrap">
        <table id="qua-table" border="0" cellspacing="0" cellpadding="0" class="table-row mt30"
               style="white-space: nowrap;width: 100%;">
            <thead>
            <tr>
                <th width="5%">序号</th>
                <th>操作</th>
                <th>统一代码/注册号</th>
                <th>企业名称</th>
                <th>法定代表人/负责人</th>
                <th>业务状态</th>
                <th>列入异常原因</th>
                <th>列入异常日期</th>
                <th>异常届满日期</th>
                <th>列入严违期限</th>
                <th>列入严违日期</th>
                <th>列入严违原因</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="{{this.class}}">{{this.text}}</button>
    {{/each}}
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/reg/server/secnocreditmanage/expsecmanage/expsecsearch.js"></script>
</body>
</html>