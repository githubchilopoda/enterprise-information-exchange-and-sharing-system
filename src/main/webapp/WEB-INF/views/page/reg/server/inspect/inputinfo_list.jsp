<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>补报核查录入</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/reg.server.css">
    <link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/js/lib/jquerymultiselect/jquery.multiselect.css">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body class="pd10">
<div class="form-box">
    <form id="qryForm">
        <input type="hidden" id="regOrg" name="regOrg">
        <input type="hidden" id="localAdm" name="localAdm">
        <input type="hidden" id="sliceNO" name="sliceNO">

        <div class="form-list pd14-mr">
            <div class="form-item clearfix">
                <div class="col-4">
                    <label class="item-name col-5"><span class="long-label">补报主体：</span></label>
                    <div class="col-6">
                        <div class="ipt-box col-12">
                            <input type="checkbox" name="subject" value="ent" checked> 企业
                            <input type="checkbox" name="subject" value="sfc" checked> 农专社
                        </div>
                    </div>
                </div>

                <div class="col-4">
                    <label class="item-name col-4"><span class="long-label">统一信用代码/注册号：</span></label>
                    <div class="col-8">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt" name="uniCode" placeholder="可输入尾号后4位查询">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5"> 登记机关：</label>
                    <div class="ipt-box col-6">
                        <input type="text" class="fl ipt-txt" readonly="readonly" id="regOrgName" placeholder="选择">
                        <span class="add-icon" id="choseorgReg">
                             <i></i>
                         </span>
                    </div>
                </div>


            </div>
            <div class="form-item clearfix">
                <div class="col-4">
                    <label class="item-name col-5">补报年度：</label>
                    <div class="col-7">
                        <div class="ipt-box col-12">
                            <select name="year">
                                <option value="">全部</option>
                                <option value="2015">2015</option>
                                <option value="2014">2014</option>
                                <option value="2013">2013</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-4">
                    <label class="item-name col-4">企业名称：</label>
                    <div class="col-8">
                        <div class="ipt-box col-12">
                            <input type="text" class="ipt-txt" name="entName" placeholder="可输入关键词查询">
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5"> 管辖单位：</label>
                    <div class="ipt-box col-6">
                        <input type="text" readonly="readonly" class="fl ipt-txt" id="localAdmName" placeholder="选择">
                        <span class="add-icon" id="choseregUnit">
                            <i></i>
                        </span>
                    </div>
                </div>



            </div>
            <div class="form-item clearfix">

                <div class="col-4">
                    <label class="item-name col-5">状态：</label>
                    <div class="col-7">
                        <div class="ipt-box col-12">
                            <select name="infoAuditResult">
                                <option value="">全部</option>
                                <option value="3">待录入</option>
                                <option value="0">待审核</option>
                                <option value="2">审核不通过</option>
                                <option value="4">审核通过</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-4">检查结果：</label>
                    <div class="col-8">
                        <div class="ipt-box col-12">
                            <select name="resResult" id="resResult"   multiple="multiple">
                                <option value="1">正常（符合信息公示相关规定）</option>
                                <option value="2">未按规定公示年报</option>
                                <option value="3">未按规定公示其他应当公示的信息</option>
                                <option value="4">公示信息隐瞒真实情况、弄虚作假</option>
                                <option value="5">通过登记的住所（经营场所）无法联系</option>
                                <option value="6">不予配合情节严重</option>
                                <option value="7">企业注销、被吊销、被撤销或迁出</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-4">
                    <label class="item-name col-5"> 片区/商圈：</label>
                    <div class="ipt-box col-6">
                        <input type="text" class="fl ipt-txt" readonly="readonly" id="sliceNoName" placeholder="选择">
                        <span class="add-icon" id="choseSilceno">
                        <i></i>
                    </span>
                    </div>
                </div>
            </div>

            <div class="form-item clearfix mt10">
                <div class="btn-box">
                    <input id="qry" type="button" value="查 询" class="btn mr20">
                    <input id="cancel" type="button" value="重 置" class="btn">
                </div>
            </div>
        </div>
    </form>
</div>
<div class="clearfix mb5 mt5">

</div>
<input type="hidden" value="Y" id="old-new-flag">
<div id="div-new-history" >
<div class="light-info">
    <span class="light">
    <input id="add-new-report" type="button" class="btn btn-sm mr5" value="新增"><br/>
    提示：此处仅查询本局登记及上级委托管辖的已补报在册企业与农专社。 录入的检查信息审核通过后自动通过公示系统予以公示，审核不通过时可修改后重新提交。
    </span>
</div>
<table id="user-table" border="0" cellspacing="0" cellpadding="0" class="table-row mt30 nowrap" width="100%">
    <thead>
    <tr>
        <th width="12%">序号</th>
        <th width="16%">操作</th>
        <th width="10%" class="uniCode">统一信用代码/注册号</th>
        <th width="12%">企业名称</th>
        <th width="6%">成立日期</th>
        <th width="7%">补报年度</th>
        <th width="7%">补报日期</th>
        <th width="10%">状态</th>
        <th width="7%">检查日期</th>
        <th width="7%">检查结果</th>
        <th width="7%">公示日期</th>
        <th width="7%">检查人员</th>
        <th width="7%">检查部门</th>
        <th width="7%">录入日期</th>
        <th width="7%">录入人员</th>
        <th width="7%">审核日期</th>
        <th width="7%">审核人员</th>
        <th width="7%">住所地</th>
        <th width="7%">登记机关</th>
        <th width="7%">管辖单位</th>
        <th width="7%">片区/商圈</th>
    </tr>
    </thead>
</table>
</div>
<div id="div-new" style="display:none;">
<div class="light-info">
    <span class="light">
    <input id="back-history" type="button" class="btn btn-sm mr5" value="返回""><br/>
    提示：此处仅查询本局登记及上级委托管辖的已补报在册企业与农专社。 录入的检查信息审核通过后自动通过公示系统予以公示，审核不通过时可修改后重新提交。
    </span>
</div>
<table id="user-new-table" border="0" cellspacing="0" cellpadding="0" class="table-row mt30 nowrap" width="100%">
    <thead>
    <tr>
        <th style="padding: 0 20px;">序号</th>
        <th>操作</th>
        <th class="uniCode">统一信用代码/注册号</th>
        <th>企业名称</th>
        <th>成立日期</th>
        <th>补报年度</th>
        <th>补报日期</th>
        <th>住所地</th>
        <th>登记机关</th>
        <th>管辖单位</th>
        <th>片区/商圈</th>
    </tr>
    </thead>
</table>
</div>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/reg/server/inspect/inputinfo_list.js"></script>
</body>
</html>
