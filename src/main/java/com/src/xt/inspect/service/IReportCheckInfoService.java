/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.inspect.service;

import com.icinfo.cs.ext.model.MidBaseInfo;
import com.icinfo.cs.inspect.dto.ReportCheckInfoDto;
import com.icinfo.cs.inspect.model.ReportCheckCode;
import com.icinfo.cs.inspect.model.ReportCheckInfo;
import com.icinfo.cs.inspect.model.ReportCheckItem;
import com.icinfo.cs.inspect.model.ReportCheckResult;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 描述:  cs_report_check_info 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2017年01月17日
 */
public interface IReportCheckInfoService extends BaseService {
    List<ReportCheckInfoDto> inspectInputInfoQueryPage(PageRequest request);

    List<ReportCheckInfoDto> inspectCheckInfoQueryPage(PageRequest request);

    List<ReportCheckInfoDto> inspectViewInfoQueryPage(PageRequest request);

    int inspectInputInfoQueryPage_total(PageRequest request);

    int del(String uid);

    Map countCheckNum(Map<String, Object> params);

    ReportCheckInfo getCheckInfoByUid(String uid);

    List<ReportCheckResult> getCheckResByUid(String uid);

    List<ReportCheckCode> getCommonKeyCodeList(String pripid, String common,int year);

    List<ReportCheckCode> getCodeListByEntType(MidBaseInfo midBaseInfo, String checkType,int year);

    int add(ReportCheckInfoDto reportCheckInfoDto);
    int modi(ReportCheckInfoDto reportCheckInfoDto);

    List<ReportCheckItem> getCheckedItem(String uid);

    int modiPre(ReportCheckInfoDto reportCheckInfoDto,String fromtype);

    String getLinkManAndTel(MidBaseInfo midBaseInfo);

    List<ReportCheckInfoDto> inspectInputInfoQueryPage_all(PageRequest request);
    
    /**
     * 描述:新增补报核查记录
     * @author chenxin
     * @date 2017-02-03
     * @param request
     * @return
     */
    List<ReportCheckInfoDto> inspectInputInfoQueryPageNew(PageRequest request);
}