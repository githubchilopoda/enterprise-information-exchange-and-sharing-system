/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.rpt.rptdto;

import com.icinfo.cs.rpt.rptmodel.RptSmNormal;

/**
 * 描述:    cs_rpt_sm_normal 对应的DTO类.<br>
 *
 * @author framework generator
 * @date 2017年05月04日
 */
public class RptSmNormalDto extends RptSmNormal {
	
	//三大产业
	private String industryCodeName;
	//行业
	private String industryCoName;
	//小微企业大类（跟企业小类挂钩）
	private String entyTypeCatgName;
	//企业细分类型（小类）
	private String entyTypeName;
	//登记机关
	private String regorgName;
	//管辖单位
	private String localAdmName;
	public String getIndustryCodeName() {
		return industryCodeName;
	}
	public void setIndustryCodeName(String industryCodeName) {
		this.industryCodeName = industryCodeName;
	}
	public String getIndustryCoName() {
		return industryCoName;
	}
	public void setIndustryCoName(String industryCoName) {
		this.industryCoName = industryCoName;
	}
	public String getEntyTypeCatgName() {
		return entyTypeCatgName;
	}
	public void setEntyTypeCatgName(String entyTypeCatgName) {
		this.entyTypeCatgName = entyTypeCatgName;
	}
	public String getEntyTypeName() {
		return entyTypeName;
	}
	public void setEntyTypeName(String entyTypeName) {
		this.entyTypeName = entyTypeName;
	}
	public String getRegorgName() {
		return regorgName;
	}
	public void setRegorgName(String regorgName) {
		this.regorgName = regorgName;
	}
	public String getLocalAdmName() {
		return localAdmName;
	}
	public void setLocalAdmName(String localAdmName) {
		this.localAdmName = localAdmName;
	}
	
	
}