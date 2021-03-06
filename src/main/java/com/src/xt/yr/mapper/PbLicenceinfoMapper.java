/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年09月12日
 * @version 2.0
 */
package com.icinfo.cs.yr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.yr.dto.PbLicenceinfoDto;
import com.icinfo.cs.yr.model.PbLicenceinfo;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

/**
 * 描述:    cs_pb_licenceinfo 对应的Mapper接口.(个体户公示许可信息)<br>
 *
 * @author framework generator
 * @date 2016年09月12日
 */
public interface PbLicenceinfoMapper extends Mapper<PbLicenceinfo> {

	/**
	 * 描述：查询个体户年报行政许可
	 * @author baifangfang
	 * @date 2016年10月9日
	 * @param params
	 * @return
	 */
	List<PbLicenceinfoDto> queryPageResult(Map<String, Object> params);
	
	/** 
	 * 描述: 根据年报id保存公示信息
	 * @auther ZhouYan
	 * @date 2016年10月10日 
	 * @param anCheID
	 * @return 
	 */
	int insertByAnCheID(@Param(Constants.CS_ANCHEID)String anCheID);
}