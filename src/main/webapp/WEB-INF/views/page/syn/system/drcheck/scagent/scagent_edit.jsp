<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="/css/reg.server.css">
	<link rel="stylesheet" href="/css/vendor/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="/js/lib/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="/js/lib/jquerymultiselect/jquery.multiselect.css">
</head>
<body>
<form id="sysUserForm">
    <div class="pd20 clearfix">
        <h3 class="h3-title center"></h3>
        <table cellpadding="0" cellspacing="0" border="0" width="100%" class="table-horizontal mt10 f12">
            <tr>
                <td class="bg-gray right" width="15%">单位工号：</td>
                <td width="30%">
                    <div class="ipt-box pd0">
                        <input type="hidden" name="UID" value="${pubScagent.UID }"/>
                        <input type="text" id="employeeNO" name="employeeNO" value="${pubScagent.employeeNO}" class="ipt-txt required"/>
                    </div>
                </td>
                <td class="bg-gray right" width="15%"><span class="light">*</span>工作单位：</td>
                <td colspan="3" width="40%">
                    <div class="ipt-box pd0">
                    	<input type="hidden" name="deptCode" id="deptCode" value="${sessionDeptCode}"/>
                        <input type="text" id="deptCodeName"  class="ipt-txt" value="${deptName}" placeholder="请选择部门（机构）" disabled="disabled"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right"><span class="light">*</span>性别：</td>
                <td>
                    <div class="ipt-box pd0" id="agentSexDiv">
                    	<input type="radio" name="agentSex" id="agentSex1" value="1" <c:if test="${pubScagent.agentSex == '1'}">checked="checked"</c:if>/>男
                    	<input type="radio" name="agentSex" value="2" <c:if test="${pubScagent.agentSex == '2'}">checked="checked"</c:if>/>女
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>单位所属地区：</td>
                <td colspan="3">
                    <div class="ipt-box pd0">
                      <select id="agentArea" name="agentArea" class="required">
                        <option value=""></option>
                        <option value="ZJ" ${pubScagent.agentArea =='ZJ'?'selected':''}>省本级</option>
                        <option value="H" ${pubScagent.agentArea =='H'?'selected':''}>杭州</option>
                        <option value="N" ${pubScagent.agentArea =='N'?'selected':''}>宁波</option>
                        <option value="W" ${pubScagent.agentArea =='W'?'selected':''}>温州</option>
                        <option value="Jx" ${pubScagent.agentArea =='Jx'?'selected':''}>嘉兴</option>
                        <option value="Hu" ${pubScagent.agentArea =='Hu'?'selected':''}>湖州</option>
                        <option value="S" ${pubScagent.agentArea =='S'?'selected':''}>绍兴</option>
                        <option value="J" ${pubScagent.agentArea =='J'?'selected':''}>金华</option>
                        <option value="Q" ${pubScagent.agentArea =='Q'?'selected':''}>衢州</option>
                        <option value="Z" ${pubScagent.agentArea =='Z'?'selected':''}>舟山</option>
                        <option value="T" ${pubScagent.agentArea =='T'?'selected':''}>台州</option>
                        <option value="L" ${pubScagent.agentArea =='L'?'selected':''}>丽水</option>
                      </select>
                    </div>
                </td>
            </tr>
            <tr>
            	<td class="bg-gray right"><span class="light">*</span>姓名：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="agentName" id="agentName" value="${pubScagent.agentName }" class="ipt-txt required"/>
                    </div>
                </td>
            	<td class="bg-gray right"><span class="light">*</span>单位层级：</td>
                <td colspan="3">
                    <div class="ipt-box pd0" id="unitLevelDiv">
                    	<input type="hidden" id="unitType" name="unitType"/>
                    	<input type="radio" value="3" name="unitLevel" <c:if test="${pubScagent.unitLevel == '3'}">checked="checked"</c:if>/>省级
                    	<input type="radio" value="1" name="unitLevel" <c:if test="${pubScagent.unitLevel == '1'}">checked="checked"</c:if>/>市级
                    	<input type="radio" value="2" name="unitLevel" <c:if test="${pubScagent.unitLevel == '2'}">checked="checked"</c:if>/>县级
                    	<input type="radio" value="4" name="unitLevel" <c:if test="${pubScagent.unitLevel == '4'}">checked="checked"</c:if>/>所级
                    </div>
                </td>
            </tr>
            
             <tr>
             	<td class="bg-gray right"><span class="light">*</span>身份证号：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="agentNO" id="agentNO" class="ipt-txt  idCard" value="${pubScagent.agentNO}" <c:if test="${!empty pubScagent.UID }">disabled="disabled"</c:if>/>
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>所在业务部门（机构）：</td>
                <td colspan="3">
                    <div class="ipt-box pd0">
<%--                         <input type="hidden" name="unitCode" id="unitCode" value="${pubScagent.unitCode }"/> --%>
                        <input type="text" id="unitName" name="unitName" value="${pubScagent.unitName }" class="fl ipt-txt">
<!--                         <span class="add-icon" id="unitChoose"><i></i></span> -->
                    </div>
                </td>
            </tr>
            <tr>
             	<td class="bg-gray right">出生年月：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="birthDay" id="birthDay" value="${pubScagent.birthDay}" disabled="disabled" id="birthDay" class="ipt-txt required"/>
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>担任职务：</td>
                <td colspan="3">
                    <div class="ipt-box pd0">
                      <select name="agentPosition" id="agentPosition" class="required">
                            <option value=""></option>
                            <option value="1" <c:if test="${pubScagent.agentPosition == '1'}">selected</c:if>>局领导</option>
                            <option value="2" <c:if test="${pubScagent.agentPosition == '2'}">selected</c:if>>科室（处、办、中心）负责人（正职）</option>
                            <option value="3" <c:if test="${pubScagent.agentPosition == '3'}">selected</c:if>>科室（处、办、中心）负责人（副职）</option>
                            <option value="4" <c:if test="${pubScagent.agentPosition == '4'}">selected</c:if>>所（站、分局）长（正职）</option>
                            <option value="5" <c:if test="${pubScagent.agentPosition == '5'}">selected</c:if>>所（站、分局）长（副职）</option>
                            <option value="6" <c:if test="${pubScagent.agentPosition == '6'}">selected</c:if>>一般干部</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
             	<td class="bg-gray right">年龄：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="agentAge" value="${pubScagent.agentAge}" disabled="disabled" id="agentAge" class="ipt-txt required"/>
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>从事岗位（大类）：</td>
                <td colspan="3">
                    <div class="ipt-box pd0" id="deptCatgDiv">
                        <input type="hidden" class="ipt-txt" name="deptCatg" id="deptCatg" value=""/>
                        <select id="deptCatgM" multiple="multiple">
                            <option value="1" ${fn:indexOf(pubScagent.deptCatg,'1')!=-1?'selected="selected"':''}>工商行政管理类</option>
                            <option value="2" ${fn:indexOf(pubScagent.deptCatg,'2')!=-1?'selected="selected"':''}>食品药品管理类</option>
                            <option value="3" ${fn:indexOf(pubScagent.deptCatg,'3')!=-1?'selected="selected"':''}>质量技术监督类</option>
                            <option value="4" ${fn:indexOf(pubScagent.deptCatg,'4')!=-1?'selected="selected"':''}>安全生产类</option>
                            <option value="5" ${fn:indexOf(pubScagent.deptCatg,'5')!=-1?'selected="selected"':''}>物价管理类</option>
                        </select>
                    </div>
                </td>
            </tr>
             <tr>
             	<td class="bg-gray right"><span class="light">*</span>手机号码：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="mobile" id="mobile" class="ipt-txt required" value="${pubScagent.mobile}"/>
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>岗位管辖片区：</td>
                <td colspan="3">
                    <div class="ipt-box pd0">
                      <input type="hidden" name="slicenNO" id="localAdm" value="${pubScagent.slicenNO}"/>
                      <input type="text" class="ipt-txt" id="localAdmName" value="${codeRegunitDesc}" readonly/>
                       <span class="add-icon" id="choseregUnit">
                           <i></i>
                      </span>
                    </div>
                </td>
            </tr>
            <tr>
            	<td class="bg-gray right"><span class="light">*</span>最高学历：</td>
                <td>
                    <div class="ipt-box pd0">
                        <select name="studyLevel" id="studyLevel">
                            <option value=""></option>
                            <option value="8" <c:if test="${pubScagent.studyLevel == '8'}">selected</c:if>>小学</option>
                            <option value="4" <c:if test="${pubScagent.studyLevel == '4'}">selected</c:if>>初中</option>
                            <option value="5" <c:if test="${pubScagent.studyLevel == '5'}">selected</c:if>>高中</option>
                            <option value="1" <c:if test="${pubScagent.studyLevel == '1'}">selected</c:if>>大专</option>
                            <option value="2" <c:if test="${pubScagent.studyLevel == '2'}">selected</c:if>>本科</option>
                            <option value="3" <c:if test="${pubScagent.studyLevel == '3'}">selected</c:if>>研究生</option>
                            <option value="6" <c:if test="${pubScagent.studyLevel == '6'}">selected</c:if>>硕士</option>
                            <option value="7" <c:if test="${pubScagent.studyLevel == '7'}">selected</c:if>>博士</option>
                            <option value="9" <c:if test="${pubScagent.studyLevel == '9'}">selected</c:if>>博士后</option>
                        </select>
                    </div>
                </td>
                <td class="bg-gray right"><span class="light">*</span>从事本岗起始日期：</td>
                <td colspan="3">
                    <div class="ipt-box pd0">
                        <input type="text" class="ipt-txt laydate-icon" name="workTime" id="workTime" value="<fmt:formatDate  value='${pubScagent.workTime}' type='both' pattern='yyyy-MM-dd' />" readonly onclick="laydate()">
                    </div>
                </td>
             </tr>
             <tr>
                <td class="bg-gray right"><span class="light">*</span>学历专业：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="professionals" id="professionals" class="ipt-txt " placeholder="与最高学历对应的院校专业名称" value="${pubScagent.professionals}"/>
                    </div>
                </td>
                <td class="bg-gray right">工商部门执法证编号：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="lawNO" id="lawNO" class="ipt-txt" value="${pubScagent.lawNO }"/>
                    </div>
                </td>
                <td class="bg-gray right" width="10%">有效期至：</td>
                <td width="10%">
                    <div class="ipt-box pd0">
	                        <input type="text" class="ipt-txt laydate-icon" name="lawEndDate" value="<fmt:formatDate  value='${pubScagent.lawEndDate}' type='both' pattern='yyyy-MM-dd' /> " readonly onclick="laydate()">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right">个人专业技术职称：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="technicalTitle" id="technicalTitle" class="ipt-txt" value="${pubScagent.technicalTitle }"/>
                    </div>
                </td>
                <td class="bg-gray right">省政府执法证编号：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" name="govLawNO" id="govLawNO" class="ipt-txt" value="${pubScagent.govLawNO }"/>
                    </div>
                </td>
                <td class="bg-gray right">有效期至：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" class="ipt-txt laydate-icon" name="govLawEndDate" value="<fmt:formatDate  value='${pubScagent.govLawEndDate}' type='both' pattern='yyyy-MM-dd' /> " readonly onclick="laydate()">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right"><span class="light">*</span>获得岗位资格资质：</td>
                <td colspan="5">
                    <div class="ipt-box pd0" id="stationTermDiv">
                    	<input type="hidden" id="stationTerm" name="stationTerm"/>
                        <input type="radio" value="N" id="stationTermN" name="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'N')!=-1?'checked':''}/>无
                        <input type="radio" value="Y" id="stationTermM" name="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'Y')!=-1?'checked':''}/>有
                        <span style="color: blue;">(</span>
                        <input type="checkbox" value="1" id="stationTermM1" class="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'1')!=-1?'checked':''} ${fn:indexOf(pubScagent.stationTerm,'N')!=-1?'disabled="disabled"':''}/>保化检查员
                        <input type="checkbox" value="2" id="stationTermM2" class="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'2')!=-1?'checked':''} ${fn:indexOf(pubScagent.stationTerm,'N')!=-1?'disabled="disabled"':''}/>药品检查员
                        <input type="checkbox" value="3" id="stationTermM3" class="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'3')!=-1?'checked':''} ${fn:indexOf(pubScagent.stationTerm,'N')!=-1?'disabled="disabled"':''}/>医疗器械检查员
                        <input type="checkbox" value="4" id="stationTermM4" class="stationTermM" ${fn:indexOf(pubScagent.stationTerm,'4')!=-1?'checked':''} ${fn:indexOf(pubScagent.stationTerm,'N')!=-1?'disabled="disabled"':''}/>特种设备安全监察员
                        <span style="color: blue;">)</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right"><span class="light">*</span>是否属于专家：</td>
                <td colspan="5">
                    <div class="ipt-box pd0" id="expertFlagDiv">
                    	<input type="hidden" id="expertFlag" name="expertFlag"/>
                        <input type="radio" id="expertFlagN" name="expertFlagM" value="N" ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'checked':''}/>否
                        <input type="radio" id="expertFlagM" name="expertFlagM" value="Y" ${fn:indexOf(pubScagent.expertFlag,'Y')!=-1?'checked':''}/>是
                        <span style="color: blue;">(</span>
                        <input type="checkbox" value="1" id="expertFlagM1" class="expertFlagK" ${fn:indexOf(pubScagent.expertFlag,'1')!=-1?'checked':''} ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'disabled="disabled"':''}/>保化组长
                        <input type="checkbox" value="2" id="expertFlagM2" class="expertFlagK" ${fn:indexOf(pubScagent.expertFlag,'2')!=-1?'checked':''} ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'disabled="disabled"':''}/>药品GSP
                        <input type="checkbox" value="3" id="expertFlagM3" class="expertFlagK" ${fn:indexOf(pubScagent.expertFlag,'3')!=-1?'checked':''} ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'disabled="disabled"':''}/>药品GMP
                        <input type="checkbox" value="4" id="expertFlagM4" class="expertFlagK" ${fn:indexOf(pubScagent.expertFlag,'4')!=-1?'checked':''} ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'disabled="disabled"':''}/>医疗器械GSP
                        <input type="checkbox" value="5" id="expertFlagM5" class="expertFlagK" ${fn:indexOf(pubScagent.expertFlag,'5')!=-1?'checked':''} ${fn:indexOf(pubScagent.expertFlag,'N')!=-1?'disabled="disabled"':''}/>医疗器械GMP
                        <span style="color: blue;">)</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right"><span class="light">*</span>执法事项清单范围：</td>
                <td colspan="5">
                    <div class="ipt-box pd0">
                    	<input type="hidden" id="agentRange" name="agentRange"/>
                    	<a href="javascript:void(0);" id="agentRangeA">选择执法事项清单</a>
                        <textarea rows="" cols="" style="height:80px;margin-top: 5px;" id="agentRangeName" name="agentRangeName" readonly="readonly">${pubScagent.agentRangeName }</textarea>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right"><span class="light">*</span>执法人员状态：</td>
                <td colspan="5">
                    <div class="ipt-box pd0" id="agentStateDiv">
                    	<input type="radio" id="agentState1" name="agentState" value="1" <c:if test="${pubScagent.agentState == '1' || empty pubScagent.agentState}">checked</c:if>/>有效
                    	<input type="radio" id="agentState2" name="agentState" value="2" <c:if test="${pubScagent.agentState == '2'}">checked</c:if> />失效
                    	<span style="color: red;">（失效人员不纳入检查执法人员抽取范围）</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="bg-gray right">设置部门：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" class="ipt-txt" value="${deptName}" disabled="disabled"/>
                    </div>
                </td>
                <td class="bg-gray right">设置人：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text"  class="ipt-txt" disabled="disabled" value="${sessionScope.session_sys_user.realName}"/>
                    </div>
                </td>
                <td class="bg-gray right">设置日期：</td>
                <td>
                    <div class="ipt-box pd0">
                        <input type="text" class="ipt-txt" disabled="disabled" value="${sysdate}">
                    </div>
                </td>
            </tr>
        </table>
        <p class="center mt10">
	        <input type="button" id="save" class="btn mr20" value="保 存"/>
	        <input type="button" class="btn" value="关 闭" id="cancel"/>
        </p>
    </div>
</form>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/syn/system/drcheck/scagent/scagent_edit_main.js"></script>
</body>
</html>