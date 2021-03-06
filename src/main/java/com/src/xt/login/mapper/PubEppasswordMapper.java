/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年08月28日
 * @version 2.0
 */
package com.icinfo.cs.login.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.icinfo.cs.login.dto.PubEppasswordDto;
import com.icinfo.cs.login.model.PubEppassword;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

/**
 * 描述:    cs_pub_eppassword 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年08月28日
 */
public interface PubEppasswordMapper extends Mapper<PubEppassword> {
	
	/** 
	 * 描述: 查询企业联络员登录信息
	 * @auther ZhouYan
	 * @date 2016年8月30日 
	 * @param pubEppasswordDto
	 * @return 
	 */
	List<PubEppassword> selectLoginPubEppassword(PubEppasswordDto pubEppasswordDto);
	
	
	/**
	 * 
	 * 描述: 联络员备案查询联络员信息
	 * @auther gaojinling
	 * @date 2016年8月31日 
	 * @param map
	 * @return
	 */
	List<PubEppasswordDto> selectPubEppasswordList(Map<String, Object> map);

	/**
	 * 
	 * 描述: 获取确认书接收记录
	 * @auther yujingwei
	 * @date 2016年8月31日 
	 * @param params
	 * @return
	 */
	List<PubEppassword> selectPubPhoneInfoForHisList(Map<String, Object> params);

	/**
	 * 
	 * 描述: 获取回执单打印数据
	 * @auther yujingwei
	 * @date 2016年8月31日 
	 * @param map
	 * @return
	 */
	PubEppassword selectPubEppasswordForPrint(HashMap<String, Object> map);

    /**
     * 
     * 描述: 工商的联络员备案信息查询
     * @auther chenyl
     * @date 2016年9月21日 
     * @param params
     * @return
     */
	List<PubEppasswordDto> selectPubEppasswordListNew(Map<String, Object> params);
	
	/**
	 * 获取联络员相关信息
	 * 
	 * @author zhuyong
	 * @param priPid
	 * @param year
	 * @return
	 */
	List<PubEppassword> selectLiaInfo(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * 描述: 个体户有效登录校验码查询
	 * @auther gaojinling
	 * @date 2017年1月4日 
	 * @param params
	 * @return
	 */
	List<PubEppasswordDto> selectPbEppasswordList(Map<String, Object> params);
	
	/** 
 * 描述: 根据联络电话获取3条企业名称数据 
 * @author 张文男
 * @date 2017年5月22日 
 * @param tel
 * @return 
 */

List<String> queryEntNameFirst3ListByPubEppasswordTel(String tel);

/** 
 * 描述: 获取联络员年报信息列表
 * @author 张文男
 * @date 2017年6月9日 
 * @param year
 * @return 
 */

List<PubEppasswordDto> queryYearReportList(Map<String, Object> params);

/** 
 * 描述: 根据年份、是否年报、联络员手机获取企业名称列表
 * @author 张文男
 * @date 2017年6月9日 
 * @param params
 * @return 
 */

List<PubEppasswordDto> queryEntNameList(Map<String, Object> params);

/** 
 * 描述: 获取联络员年报信息列表-总数
 * @author 张文男
 * @date 2017年6月9日 
 * @param year
 * @return 
 */
Long queryYearReportCount(Map<String, Object> params);
	
}