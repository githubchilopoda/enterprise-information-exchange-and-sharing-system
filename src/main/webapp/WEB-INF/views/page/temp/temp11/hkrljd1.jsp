<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>户口认领建档</title>
    <link rel="stylesheet" href="/css/reg.server.css">
</head>
<body class="pd10">
<div class="form-box">
    <div class="form-list">
        <div class="form-item clearfix">
            <div class="col-4">
                <label class="item-name col-5">统一信用代码/注册号：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <input type="text" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5">企业类型（大类）：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <select name="">
                            <option value=""></option>
                            <option value=""></option>
                            <option value=""></option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5"> 管辖单位：</label>
                <div class="col-6">
                    <div class="ipt-box col-5">
                        <input type="text" class="fl ipt-txt" placeholder="选择">
                            <span class="add-icon">
                                <i></i>
                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-item clearfix">
            <div class="col-4">
                <label class="item-name col-5">企业名称：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <input type="text" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5">成立日期：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <div class="ipt-box col-5">
                            <input type="text" class="ipt-txt">
                        </div>
                        <span class="item-txt col-2"><i class="line-icon"></i></span>
                        <div class="ipt-box col-5">
                            <input type="text" class="ipt-txt">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5"> 片区/商圈：</label>
                <div class="col-6">
                    <div class="ipt-box col-5">
                        <input type="text" class="fl ipt-txt" placeholder="选择">
                            <span class="add-icon">
                                <i></i>
                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-item clearfix">
            <div class="col-4">
                <label class="item-name col-5">法定代表人：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <input type="text" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5">认领状态：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <select name="">
                            <option value=""></option>
                            <option value=""></option>
                            <option value=""></option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5"> 乡镇/街道：</label>
                <div class="col-6">
                    <div class="ipt-box col-5">
                        <input type="text" class="fl ipt-txt" placeholder="选择">
                            <span class="add-icon">
                                <i></i>
                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-item clearfix">
            <div class="col-4">
                <label class="item-name col-5">经营范围：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <input type="text" class="ipt-txt">
                    </div>
                </div>
            </div>
            <div class="col-4">
                <label class="item-name col-5">住所（经营场所）：</label>
                <div class="col-6">
                    <div class="ipt-box col-12">
                        <select name="">
                            <option value=""></option>
                            <option value=""></option>
                            <option value=""></option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-item clearfix">
            <div class="btn-box">
                <input type="button" value="查 询" class="btn mr20">
                <input type="button" value="重 置" class="btn">
            </div>
        </div>
    </div>
</div>
<div class="clearfix mb5 mt20">
    <p class="fl mr10 mt5"><label><input type="checkbox">全选</label></p>
    <p class="fl"><input type="button" class="btn btn-sm mr5" value="批量建档"><input type="button" class="btn btn-sm mr5" value="批量退回"><input type="button" class="btn btn-sm" value="导出"></p>
</div>
<div>
    <table border="0" cellspacing="0" cellpadding="0" class="table-row mt30">
        <tbody>
        <tr>
            <th width="7%">序号</th>
            <th width="7%"> 操作  </th>
            <th width="10%">认领状态 </th>
            <th width="7%">认领人  </th>
            <th width="10%"> 认领时间</th>
            <th width="10%">分配时间</th>
            <th width="13%">  统一信用代码/注册号</th>
            <th width="10%">企业名称</th>
            <th width="7%"> 法定代表人</th>
            <th>住所（经营场所）</th>
        </tr>
        <tr>
            <td><input type="checkbox">1</td>
            <td><a href="#">建档录入</a><a href="#">认领</a></td>
            <td>无证无照</td>
            <td>桐庐县工商局</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>