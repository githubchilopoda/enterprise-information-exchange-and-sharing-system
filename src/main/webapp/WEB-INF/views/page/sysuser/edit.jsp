<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">

    <title>用户修改页面</title>
    <link rel="stylesheet" href="/css/reg.server.css">
    <link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
</head>
<body class="pd10">
<form id="sysUserForm">
    <h3 class="h22-title center"></h3>
    <div class="pd10 bg-gray">
        <div class="border-bottom clearfix">
            <div class="fl w70p">
                <h5 class="h6-title mb10">用户基本信息</h5>
                <div class="form-list border-bottom pdr10">
                    <div class="form-item clearfix">
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> *</span>姓名：</div>
                            <div class="col-6">
                                <div class="ipt-box">
                                    <input type="text" name="realName" class="ipt-txt" value="${sysUser.realName}" >
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                                <div class="item-name col-3"><span class="light"> *</span>所属部门：</div>
                                <div class="col-9">
                                    <div class="ipt-box">
                                        <input type="text" id="s_deptName" class="ipt-txt" value="${dept.deptName}"  readonly />
                                        <input type="hidden" name="deptCode" id="deptCode" value="${sysUser.deptCode}"/>
                                        <span class="add-icon" id="selectDept"><i></i></span>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="form-item clearfix">
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> *</span>手机：</div>
                            <div class="col-6">
                                <div class="ipt-box">
                                    <input type="text" name="telPhone" class="ipt-txt" value="${sysUser.telPhone}" />
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="item-name col-3">科室：</div>
                            <div class="col-9">
                                <div class="ipt-box">
                                    <input type="text" name="deptName" class="ipt-txt"  value="${sysUser.deptName}" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-item clearfix">
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> *</span>座机：</div>
                            <div class="col-6">
                                <div class="ipt-box">
                                    <input type="text" name="phone" class="ipt-txt" value="${sysUser.phone}"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="item-name col-3">职务：</div>
                            <div class="col-9">
                                <div class="ipt-box">
                                    <input type="text" name="post" class="ipt-txt" value="${sysUser.post}" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-item clearfix">
                        <div class="col-12">
                            <div class="item-name col-2"><span class="light"> *</span>工作电话：</div>
                            <div class="col-3">
                                <div class="ipt-box">
                                    <input type="text" name="phoneExt" class="ipt-txt" value="${sysUser.phoneExt}"/>
                                </div>
                            </div>
                            <div class="item-name col-6 left">（用于必要性对外文件显示）</div>
                        </div>
                    </div>
                    <div class="form-item clearfix">

                            <div class="col-6">
                                <div class="item-name col-4"><span class="light"> </span>单位：</div>
                                <div class="col-6">
                                <div class="ipt-box">
                                <input type="text" name="jurUnit" class="ipt-txt"  value="${sysUser.jurUnit}"/>
                                </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="item-name col-3"><span class="light"> </span>电子邮箱：</div>
                                <div class="col-9">
                                    <div class="ipt-box">
                                        <input type="text" name="email" class="ipt-txt" value="${sysUser.email}" />
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="form-item clearfix">
                        <div class="col-12">
                            <div class="item-name col-2"><span class="light"> </span>办公地址：</div>
                            <div class="col-10">
                                <div class="ipt-box">
                                    <input type="text" name="desc" class="ipt-txt" value="${sysUser.desc}" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h5 class="h6-title mb10 mt10">用户账户信息</h5>
                <div class="form-list border-bottom pdr10">
                    <div class="form-item clearfix">
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> *</span>用户名：</div>
                            <div class="col-6">
                                <div class="ipt-box">
                                    <input type="hidden" id="userId" name="id" value="${sysUser.id}"/>
                                    <input type="text" id="username" name="username" placeholder="请输入手机号码"
                                           <c:if test="${sysUser!=null}">readonly value="${sysUser.username}"</c:if> class="ipt-txt"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> *</span>账户状态：</div>
                            <div class="col-8">
                                <div class="radio-box">
                                    <label class="font-12">
                                        <input type="radio" name="status"
                                               value="1" ${sysUser.status=='1'||sysUser.status==null?"checked":""}/>有效
                                    </label>
                                    <label class="font-12">
                                        <input type="radio" name="status"
                                               value="0" ${sysUser.status=='0'?"checked":""}/>无效
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:if test="${sessionScope.session_sys_user.isAdmin=='1'}">
                        <%--<div class="form-item clearfix mb10">--%>
                            <%--<div class="col-6">--%>
                                <%--<div class="item-name col-4"><span class="light"> *</span>isAdmin：</div>--%>
                                <%--<div class="col-6">--%>
                                    <%--<div class="radio-box">--%>
                                        <%--<label class="font-12">--%>
                                            <%--<input type="radio" name="isAdmin"--%>
                                                   <%--value="0" checked/>0--%>
                                        <%--</label>--%>
                                        <%--<label class="font-12">--%>
                                            <%--<input type="radio" name="isAdmin"--%>
                                                   <%--value="1"/>1--%>
                                        <%--</label>--%>
                                        <%--<label class="font-12">--%>
                                            <%--<input type="radio" name="isAdmin"--%>
                                                   <%--value="1"/>2--%>
                                        <%--</label>--%>

                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="col-6">--%>
                                <%--<label>isAdmin 0:非超级管理员；1：超级官员；3：汇信维护人员</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </c:if>
                    <div class="form-item clearfix mb10">
                        <div class="col-6">
                            <div class="item-name col-4"><span class="light"> <c:if test="${sysUser==null}">*</c:if></span>密码：</div>
                            <div class="col-6">
                                <div class="ipt-box">
                                    <input type="password" name="password" id="password"  class="ipt-txt" placeholder="${empty sysUser.id?"请输入登录密码":"如需修改密码,请输入新密码"}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-item clearfix">
                        <div class="col-12">
                            <div class="item-name col-2"><span class="light"> </span>数据查询范围：</div>
                            <div class="col-10">
                                <div class="radio-box">
                                    <c:if test="${sessionScope.session_sys_user.isAdmin=='1'||sessionScope.session_sys_user.isAdmin=='2'||sessionScope.session_sys_user.searchRangeLevel=='4'}">
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='4'?"checked":""} value="4">本省</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='3'?"checked":""} value="3">本市</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel"  ${sysUser.searchRangeLevel=='2'?"checked":""} value="2">本局</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='1'?"checked":""} value="1">本所</label>
                                        （如果不选，默认为本所）
                                    </c:if>
                                    <c:if test="${sessionScope.session_sys_user.searchRangeLevel=='3'&&sessionScope.session_sys_user.isAdmin!='1'&&sessionScope.session_sys_user.isAdmin!='2'}">
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='3'?"checked":""} value="3">本市</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel"  ${sysUser.searchRangeLevel=='2'?"checked":""} value="2">本局</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='1'?"checked":""} value="1">本所</label>
                                        （如果不选，默认为本所）
                                    </c:if>
                                    <c:if test="${sessionScope.session_sys_user.searchRangeLevel=='2'&&sessionScope.session_sys_user.isAdmin!='1'&&sessionScope.session_sys_user.isAdmin!='2'}">
                                        <label class="font-12"><input type="radio" name="searchRangeLevel"  ${sysUser.searchRangeLevel=='2'?"checked":""} value="2">本局</label>
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='1'?"checked":""} value="1">本所</label>
                                        （如果不选，默认为本所）
                                    </c:if>
                                    <c:if test="${sessionScope.session_sys_user.searchRangeLevel=='1'&&sessionScope.session_sys_user.isAdmin!='1'&&sessionScope.session_sys_user.isAdmin!='2'}">
                                        <label class="font-12"><input type="radio" name="searchRangeLevel" ${sysUser.searchRangeLevel=='1'?"checked":""} value="1">本所</label>
                                        （如果不选，默认为本所）
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h5 class="h6-title mb10 mt10">岗位群组选择</h5>
                <div class="col-offset-1 pdr10">
                    <table class="mb10">
                        <tr>
                            <td width="40%">
                                <div class="ipt-box">
                                    <select multiple class="select-mul" id="js-select-del">
                                        <c:forEach var="role" items="${allRoles}">
                                            <c:if test="${empty userRoles[role.id]}">
                                                <option  value="${role.id}" >${role.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td width="15%">
                                <div class="center " id="roleLabel">
                                    <input type="button" class="btn mb10 btn-sm" value=">>添加" id="js-item-add"/></br>
                                    <input type="button" class="btn btn-sm" value="删除<<" id="js-item-del"/>
                                </div>
                            </td>
                            <td width="40%">
                                <div class="ipt-box">
                                    <input type="hidden" id="roles" name="roles">
                                    <select  id="js-select-add" class="select-mul" multiple>
                                        <c:forEach var="role" items="${allRoles}">
                                            <c:if test="${!empty userRoles[role.id]}">
                                                <option class="roles_ck"  value="${role.id}" >${role.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="fl w30p">
                <h5 class="h6-title mb10">岗位角色说明</h5>
                <div class="ipt-box pd0 " style="height: 100px;" id="roleRemark">
                
                </div>
            </div>
            <div class="fl w30p">
                <h5 class="h6-title mb10">岗位角色权限</h5>
                <div class="ipt-box pd0 " style="height: 413px;">
                <ul id="permisionTree" class="ztree" ></ul>
                  <%--  <label style="line-width:215px;">
                        <input type="checkbox" id="selectAll"> 全选
                    </label>
                    <c:forEach items="${codeEntcatgs}" var="entCatg" varStatus="index">
                        <label style="line-width:215px;width: 255px;">
                            <input class="entTypes" ${!empty searchRangeEntTypes[entCatg.code]?"checked":""} type="checkbox" value="${entCatg.code}" name="searchRangeEntTypes[]"/> ${entCatg.content}
                        </label>
                    </c:forEach> --%>
                </div>
            </div>
        </div>
        <%--<div class="form-list mt10">--%>
            <%--<div class="form-item clearfix">--%>
                <%--<div class="col-6">--%>
                    <%--<div class="item-name col-6"><span class="light"> *</span>设置人：</div>--%>
                    <%--<div class="col-4">--%>
                        <%--<div class="ipt-box">--%>
                            <%--<input type="text" class="ipt-txt">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-6">--%>
                    <%--<div class="item-name col-2"><span class="light"> *</span>设置日期：</div>--%>
                    <%--<div class="col-4">--%>
                        <%--<div class="ipt-box">--%>
                            <%--<input type="text" class="ipt-txt">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <p class="center mt10 mb10">
        <input type="button" id="save" class="btn mr20" value="提 交"/>
        <input type="button" class="btn" value="取 消"  id="cancel"/>
    </p>
</form>
<script>
    window._CONFIG = {
        <c:if test="${sessionScope.session_sys_user.isAdmin!='1'}">
        select_dept_url: '<c:url value="/common/system/dept/tree/select"/>',
        </c:if>
        <c:if test="${sessionScope.session_sys_user.isAdmin=='1'}">
        select_dept_url: '<c:url value="/common/system/dept/tree/selectAll"/>',
        </c:if>
        userType: '${userType}',
        _username: '${sysUser.username}',
        namespace:'${allRoles}'
    }
</script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/business/sysuser/edit_main.js"></script>
</body>
</html>