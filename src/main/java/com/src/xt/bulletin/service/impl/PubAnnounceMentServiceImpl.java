/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.bulletin.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.base.constant.QuartzJobName;
import com.icinfo.cs.base.model.CodeCertype;
import com.icinfo.cs.base.service.ICodeCertypeService;
import com.icinfo.cs.base.service.ICsQuartzJobService;
import com.icinfo.cs.bulletin.mapper.PubAnnounceMentMapper;
import com.icinfo.cs.bulletin.model.PubAnnounceMent;
import com.icinfo.cs.bulletin.service.IPubAnnounceMentService;
import com.icinfo.cs.common.constant.BulletinType;
import com.icinfo.cs.common.utils.AESEUtil;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.common.utils.SleepUtil;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.drcheck.mapper.PubDeptSctaskMapper;
import com.icinfo.cs.drcheck.mapper.PubScresultMapper;
import com.icinfo.cs.drcheck.mapper.PubSctaskMapper;
import com.icinfo.cs.drcheck.model.PubSctask;
import com.icinfo.cs.ext.model.MidBaseInfo;
import com.icinfo.cs.ext.service.IMidBaseInfoService;
import com.icinfo.cs.opanomaly.dto.PubOpanoMalyDto;
import com.icinfo.cs.opanomaly.mapper.PubOpaDetailMapper;
import com.icinfo.cs.opanomaly.mapper.PubOpanoMalyMapper;
import com.icinfo.cs.opanomaly.model.PubOpaDetail;
import com.icinfo.cs.opanomaly.model.PubOpanoMaly;
import com.icinfo.cs.opanomaly.service.IPubOpanoMalyService;
import com.icinfo.cs.other.model.PubJusticeInfo;
import com.icinfo.cs.other.service.IPubJusticeInfoService;
import com.icinfo.cs.secnocreditsup.mapper.ExpSeriousCrimeListMapper;
import com.icinfo.cs.secnocreditsup.model.ExpSeriousCrimeList;
import com.icinfo.cs.secnocreditsup.service.IExpSeriousCrimeListService;
import com.icinfo.cs.simpleesc.dto.ErEscAppinfoDto;
import com.icinfo.cs.simpleesc.mapper.ErEscAppinfoMapper;
import com.icinfo.cs.simpleesc.model.ErEscAppinfo;
import com.icinfo.cs.simpleesc.service.IErEscAppinfoService;
import com.icinfo.cs.yr.dto.PubOtherpunishDto;
import com.icinfo.cs.yr.model.Bulletins;
import com.icinfo.cs.yr.model.PubOtherpunish;
import com.icinfo.cs.yr.service.IBulletinsService;
import com.icinfo.cs.yr.service.IPubOtherpunishService;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_announcement 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2016年10月26日
 */
@Service
public class PubAnnounceMentServiceImpl extends MyBatisServiceSupport implements IPubAnnounceMentService {
	
	private static final Logger logger = LoggerFactory.getLogger(PubAnnounceMentServiceImpl.class);
	
	@Autowired
	PubAnnounceMentMapper pubAnnounceMentMapper;
	
	@Autowired
	IPubJusticeInfoService pubJusticeInfoService;
	
	@Autowired
	IPubOtherpunishService pubOtherpunishService;
	
	@Autowired
	private IPubOpanoMalyService pubOpanoMalyService;
	
	@Autowired
	PubOpanoMalyMapper pubOpanoMalyMapper;
	
	@Autowired
	PubOpaDetailMapper pubOpaDetailMapper;
	
	@Autowired
	PubDeptSctaskMapper pubDeptSctaskMapper;
	
	@Autowired
	PubSctaskMapper pubSctaskMapper;
	
	@Autowired
	PubScresultMapper pubScresultMapper;
	
	@Autowired
	IMidBaseInfoService midBaseInfoService;
	
	@Autowired
	IExpSeriousCrimeListService expSeriousCrimeListService;
	
	@Autowired
	ExpSeriousCrimeListMapper expSeriousCrimeListMapper;
	
	@Autowired
	ICodeCertypeService codeCertypeService;
	
	@Autowired
	ErEscAppinfoMapper erEscAppinfoMapper;
	
	@Autowired
	ICsQuartzJobService csQuartzJobService;
	
	@Autowired
	IErEscAppinfoService erEscAppinfoService;
	@Autowired
	IBulletinsService bulletinsService;
	
	//插入公告数据失败的个数
	private int insertFailsTotal = 0;
	
	/**
     * 获取信息公示公告列表数据
     * @author yujingwei
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public List<PubAnnounceMent> queryBulletinInfoList(PageRequest request) throws Exception{
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Map<String, Object> searchMap=  request.getParams();
		return pubAnnounceMentMapper.doGetBulletinInfo(searchMap);
	}
	
	/**
     * 通过UID获取公告信息
     * @author yujingwei
     * @date 2016-10-17
     * @param uID
     * @return PubAnnounceMent
     * @throws Exception
     */
	public PubAnnounceMent doGetpubAnnounceMentInfo(String UID,String pubType) throws Exception{
		PubAnnounceMent newPubAnnounceMent = new PubAnnounceMent(); 
		newPubAnnounceMent.setLinkUID(UID);
		newPubAnnounceMent.setPubType(pubType);
		return pubAnnounceMentMapper.selectOne(newPubAnnounceMent);
	}
	
	/**
     * 根据公告类型返回不同的视图
     * @author yujingwei
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public ModelAndView doGetViewByPubType(String UID, String pubType,ModelAndView view) throws Exception{
		// 1.司法协助(股权冻结，股权变更)
		if(pubType.equals(BulletinType.JusticeInfoAlter.getCode()) || pubType.equals(BulletinType.JusticeInfoFroz.getCode())){
			view.setViewName("pub/infobulletin/justice");
			PubJusticeInfo pubJusticeInfo = pubJusticeInfoService.doGetPubJusticeInfoForPub(UID);
			List<CodeCertype> codeCertypeList = codeCertypeService.selectAll();
			view.addObject("codeCertypeList", codeCertypeList);
			view.addObject("pubJusticeInfo",pubJusticeInfo);
		}
		// 2.行政处罚公告
		if(pubType.equals(BulletinType.PunishInfo.getCode())){
			view.setViewName("pub/infobulletin/punish");
			PubOtherpunishDto pubOtherpunish = pubOtherpunishService.doGetOtherPunishInfo(UID);
			view.addObject("pubOtherpunish", pubOtherpunish);
		}
		// 3.列入列出经营异常名录、期满三年提醒公告
		if(pubType.equals(BulletinType.AnomalyIn.getCode()) || pubType.equals(BulletinType.AnomalyRemind.getCode())){
			view.setViewName("/pub/infobulletin/anomalydetail");
			PubOpanoMaly newPubOpanoMaly = new PubOpanoMaly();
			newPubOpanoMaly.setBusExcList(UID);
			PubOpanoMaly pubOpanoMaly = pubOpanoMalyMapper.selectOne(newPubOpanoMaly);
			view.addObject("pubOpanoMaly",pubOpanoMaly);
			if(pubType.equals(BulletinType.AnomalyRemind.getCode())){
				view.addObject("pubAnnounceMent", doGetpubAnnounceMentInfo(UID,BulletinType.AnomalyRemind.getCode()));	
			}
		}if(pubType.equals(BulletinType.AnomalyOut.getCode())){
			view.setViewName("/pub/infobulletin/anomalydetail");
			PubOpaDetail newPubOpaDetail =new PubOpaDetail();
			newPubOpaDetail.setBusExcList(UID);
			PubOpaDetail pubOpaDetail = pubOpaDetailMapper.selectOne(newPubOpaDetail);
			view.addObject("pubOpaDetail",pubOpaDetail);
		}
		// 4.抽查检查公告
		if(pubType.equals(BulletinType.SpotBulletin.getCode())){
			view.setViewName("/pub/infobulletin/spotcheck");
			PubAnnounceMent pubAnnounceMent = this.doGetpubAnnounceMentInfo(UID,BulletinType.SpotBulletin.getCode());
			view.addObject("pubAnnounceMent", pubAnnounceMent);
		}
		// 5.严重违法列入、列出公告
		if(pubType.equals(BulletinType.IlldisIn.getCode())){
			view.setViewName("/pub/infobulletin/illdisin");
			ExpSeriousCrimeList expSeriousCrimeList = new ExpSeriousCrimeList();
			expSeriousCrimeList.setUid(UID);
			ExpSeriousCrimeList expSeriousCrimeListInfo = expSeriousCrimeListMapper.selectOne(expSeriousCrimeList);
			view.addObject("expSeriousCrimeList", expSeriousCrimeListInfo);
		}
		// 6.简易注销公告
		if(pubType.equals(BulletinType.SimpleLogout.getCode())){
			view.setViewName("/pub/infobulletin/simpcan");
			ErEscAppinfoDto erEscAppinfo  = erEscAppinfoService.getErEscAppinfoByPriPID(UID);
			erEscAppinfo.setPriPID(AESEUtil.encodeCorpid(erEscAppinfo.getPriPID()));
			view.addObject("erEscAppinfo", erEscAppinfo);
		}
		// 7.拟吊销营业执照听证公告
		if(pubType.equals(BulletinType.ReadyRevokeLicense.getCode()) 
				 || pubType.equals(BulletinType.RevokeLicense.getCode())  || pubType.equals(BulletinType.RegOrgRevoke.getCode())
				 || pubType.equals(BulletinType.YearCheck.getCode())){
			Bulletins bulletins = bulletinsService.selectByUID(UID);
			view.addObject("Bulletins", bulletins);
			String files = bulletins.getBulData();
			if (files != null && !files.isEmpty()) {
				view.addObject("files", files.split("\\|"));
			}
			view.setViewName("/pub/infobulletin/buldata");
		}
		return view;
	}
	
	/**
     * 公告信息数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public void doCreatBulletinAllInfo() throws Exception{
		try {
			//阻塞任务，防止并发访问数据库
			SleepUtil.threadSleep();
			if(csQuartzJobService.checkJobIsExecute(QuartzJobName.BULLETIN_JOB.getJobName())){
				return ;
			}
			csQuartzJobService.insert(QuartzJobName.BULLETIN_JOB.getJobName());
			long time1 = System.currentTimeMillis();
			String	updateDate = DateUtil.getNdaylater2(new Date(), -1);
			// a.司法协助公告数据
			List<PubJusticeInfo> pubJusticeInfoList  = pubJusticeInfoService.doGetInfoByJusticeType(updateDate);
			int Justicetotal =insertInfoForJustice(pubJusticeInfoList);			
			// b.行政处罚公告数据
			List<PubOtherpunish> pubOtherpunishList = pubOtherpunishService.selectOtherPunishForBulletin(updateDate);
			int punishTotal = insertInfoForPunish(pubOtherpunishList);
			// c.经营异常公告数据
			List<PubOpanoMalyDto> pubOpanoMalyList = pubOpanoMalyService.queryPubOpanoMalyListForNotice(updateDate);
			int opanoMalyTotal = insertInfoForPubOpanoMaly(pubOpanoMalyList);
			// d.简易注销公告
			List<ErEscAppinfo> erEscAppinfoList = erEscAppinfoMapper.selectEscAppInfoForBulletin(updateDate);
			int erEscAppinfoTotal = insertInfoForErEscAppinfo(erEscAppinfoList);
			// e.抽查检查公告数据
			List<PubSctask> pubSctaskList = pubSctaskMapper.selectScTaskListForBulletin(updateDate);
			int SoptCheckTotal = insertInfoForSoptCheck(pubSctaskList);
			// f.严重违法失信企业公告
			List<ExpSeriousCrimeList> expSeriousCrimeList = expSeriousCrimeListService.selectSeriousCrimeInfoForBulletin(updateDate);
			int expCrimeTotal = insertInfoForCrimeInfo(expSeriousCrimeList);
			
			int count = Justicetotal + punishTotal + opanoMalyTotal + SoptCheckTotal + expCrimeTotal + erEscAppinfoTotal;
			long time2 = System.currentTimeMillis();
			logger.info("-----公告数据生成定时任务结束-----");
			logger.info("耗时"+(time2-time1)+"ms,一共生成【"+count+"】条数据"+",其中有【"+insertFailsTotal+"】条数据插入失败！");
		} catch (Exception ex) {
			logger.info("公告定时任务发生异常：{}",ex);
			throw new BusinessException(ex.getCause().getMessage(),ex);
		}
	}
    
	
	/**
     * 列入严重违法失信名单数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubDeptSctaskList
     * @return void
     * @throws Exception
     */
	private int insertInfoForCrimeInfo(List<ExpSeriousCrimeList> expSeriousCrimeList) throws Exception {
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(expSeriousCrimeList)){
			for(ExpSeriousCrimeList expSeriousCrimeListInfo : expSeriousCrimeList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(expSeriousCrimeListInfo.getUid());
					pubAnnounceMent.setPriPID(expSeriousCrimeListInfo.getPriPID());
					pubAnnounceMent.setAuditDate(expSeriousCrimeListInfo.getAddSecDate());
					pubAnnounceMent.setEntName(expSeriousCrimeListInfo.getEntName());
					pubAnnounceMent.setAuditDeptName(expSeriousCrimeListInfo.getAddSecOrg());
					pubAnnounceMent.setCreateTime(new Date());
					pubAnnounceMent.setPubType(BulletinType.IlldisIn.getCode());
					pubAnnounceMent.setPubTitle("关于"+expSeriousCrimeListInfo.getEntName()+BulletinType.IlldisIn.getDesc());
					MidBaseInfo midBaseInfo = midBaseInfoService.selectByPripid(expSeriousCrimeListInfo.getPriPID());
					if(midBaseInfo != null && midBaseInfo.getRegOrg() !=null){
						pubAnnounceMent.setAuditArea(midBaseInfo.getRegOrg().substring(0, 4));
					}
					if(expSeriousCrimeListInfo.getUid() != null){
						newPubAnnounceMentInfo.setLinkUID(expSeriousCrimeListInfo.getUid());
						newPubAnnounceMentInfo.setPubType(BulletinType.IlldisIn.getCode());
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
					pubAnnounceMentMapper.insert(pubAnnounceMent);
					total ++;
				} catch (Exception e) {
					logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				}
			}
		}
		return total;
	}

	/**
     * 抽查公告数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubDeptSctaskList
     * @return void
     * @throws Exception
     */
	private int insertInfoForSoptCheck(List<PubSctask> pubSctaskList) throws Exception{
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(pubSctaskList)){
			for(PubSctask pubSctask : pubSctaskList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(pubSctask.getUID());
					pubAnnounceMent.setEntName(pubSctask.getTaskName());
					pubAnnounceMent.setPubType(BulletinType.SpotBulletin.getCode());
					pubAnnounceMent.setPubTitle(pubSctask.getTaskName() + BulletinType.SpotBulletin.getDesc());
					pubAnnounceMent.setAuditDate(pubSctask.getCreateTime());
					pubAnnounceMent.setAuditDeptName(pubSctask.getLeaderDeptDesc());
					pubAnnounceMent.setCreateTime(new Date());
					if(pubSctask.getUID() != null){
						newPubAnnounceMentInfo.setLinkUID(pubSctask.getUID());
						newPubAnnounceMentInfo.setPubType(BulletinType.SpotBulletin.getCode());
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
					pubAnnounceMentMapper.insert(pubAnnounceMent);
					total ++;
				} catch (Exception e) {
					logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				}
			}
		}
		return total;
	}

	/**
     * 列入列出经营异常数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubOpanoMalyList
     * @return void
     * @throws Exception
     */
	private int insertInfoForPubOpanoMaly(List<PubOpanoMalyDto> pubOpanoMalyList) throws Exception {
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(pubOpanoMalyList)){
			for(PubOpanoMalyDto pubOpanoMalyDto :pubOpanoMalyList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(pubOpanoMalyDto.getBusExcList());
					pubAnnounceMent.setPriPID(pubOpanoMalyDto.getPriPID());
					pubAnnounceMent.setAuditDate(pubOpanoMalyDto.getAuditDate());
					if(StringUtil.isEmpty(pubOpanoMalyDto.getEntName())){
						continue;
					}
					pubAnnounceMent.setEntName(pubOpanoMalyDto.getEntName());
					pubAnnounceMent.setAuditDeptName(pubOpanoMalyDto.getDeptName());
					pubAnnounceMent.setCreateTime(new Date());
					if("1".equals(pubOpanoMalyDto.getPubState())){
						//列入异常公告
						pubAnnounceMent.setPubType(BulletinType.AnomalyIn.getCode());
						pubAnnounceMent.setPubTitle("关于"+pubOpanoMalyDto.getEntName() + BulletinType.AnomalyIn.getDesc());
					}else if("2".equals(pubOpanoMalyDto.getPubState())){
						//列出异常公告
						pubAnnounceMent.setPubType(BulletinType.AnomalyOut.getCode());
						pubAnnounceMent.setPubTitle("关于"+pubOpanoMalyDto.getEntName() + BulletinType.AnomalyOut.getDesc());
					}else if("3".equals(pubOpanoMalyDto.getPubState())){
							//期满三年公告
							Date abnTimeDate = DateUtil.getNdaylaterDate(DateUtil.getNyearlaterDateObject(pubOpanoMalyDto.getAuditDate(), 3),-60);
							pubAnnounceMent.setAuditDate(abnTimeDate);
							pubAnnounceMent.setPubType(BulletinType.AnomalyRemind.getCode());
							pubAnnounceMent.setPubTitle("关于"+pubOpanoMalyDto.getEntName() + BulletinType.AnomalyRemind.getDesc());
					}
					MidBaseInfo midBaseInfo = midBaseInfoService.selectByPripid(pubOpanoMalyDto.getPriPID());
					if(midBaseInfo != null && midBaseInfo.getRegOrg() !=null){
						pubAnnounceMent.setAuditArea(midBaseInfo.getRegOrg().substring(0, 4));
					}
					if(pubOpanoMalyDto.getBusExcList() != null){
						newPubAnnounceMentInfo.setLinkUID(pubOpanoMalyDto.getBusExcList());
						newPubAnnounceMentInfo.setPubType(pubOpanoMalyDto.getPubState());
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
					pubAnnounceMentMapper.insert(pubAnnounceMent);
					total ++;
				} catch (Exception e) {
					logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				}
			}
		}
		return total;
	}
	
	//判断经营异常列入时间是否满列入条件（届满三年前60天）
/*	private boolean judgeDateIsThreeYear(Date abnTime) {
		try {
			Date abnTimeDate = DateUtil.getNdaylaterDate(DateUtil.getNyearlaterDateObject(abnTime, 3),-60);
			if(abnTimeDate.getTime() < new Date().getTime()){
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}*/

	/**
     * 行政处罚数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubOtherpunishList
     * @return void
     * @throws Exception
     */
	private int insertInfoForPunish(List<PubOtherpunish> pubOtherpunishList) throws Exception {
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(pubOtherpunishList)){
			for(PubOtherpunish pubOtherpunish :pubOtherpunishList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(pubOtherpunish.getCaseNO());
					pubAnnounceMent.setPriPID(pubOtherpunish.getPriPID());
					pubAnnounceMent.setAuditDate(pubOtherpunish.getAuditDate());
					pubAnnounceMent.setEntName(pubOtherpunish.getEntName());
					if(StringUtil.isEmpty(pubOtherpunish.getEntName())){
						continue;
					}
					pubAnnounceMent.setAuditDeptName(pubOtherpunish.getJudAuth());
					pubAnnounceMent.setCreateTime(new Date());
					pubAnnounceMent.setPubType(BulletinType.PunishInfo.getCode());
					pubAnnounceMent.setPubTitle(pubOtherpunish.getEntName() + BulletinType.PunishInfo.getDesc());
					MidBaseInfo midBaseInfo = midBaseInfoService.selectByPripid(pubOtherpunish.getPriPID());
					if(midBaseInfo != null && midBaseInfo.getRegOrg() !=null){
						pubAnnounceMent.setAuditArea(midBaseInfo.getRegOrg().substring(0, 4));
					}
					if(pubOtherpunish.getCaseNO() != null){
						newPubAnnounceMentInfo.setLinkUID(pubOtherpunish.getCaseNO());
						newPubAnnounceMentInfo.setPubType(BulletinType.PunishInfo.getCode());
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
					pubAnnounceMentMapper.insert(pubAnnounceMent);
					total ++;
				} catch (Exception e) {
					logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				}
			}
		}
		return total;
	}

	/**
     * 司法协助数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubJusticeInfoList
     * @return void
     * @throws Exception
     */
	private int insertInfoForJustice(List<PubJusticeInfo> pubJusticeInfoList) throws Exception{
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(pubJusticeInfoList)){
			for(PubJusticeInfo pubJusticeInfo :pubJusticeInfoList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(pubJusticeInfo.getUID());
					pubAnnounceMent.setPriPID(pubJusticeInfo.getPriPID());
					pubAnnounceMent.setAuditDate(pubJusticeInfo.getAuditDate());
					pubAnnounceMent.setEntName(pubJusticeInfo.getEntName());
					pubAnnounceMent.setAuditDeptName(pubJusticeInfo.getAuditName());
					pubAnnounceMent.setCreateTime(new Date());
		            if(pubJusticeInfo.getJusticeType().equalsIgnoreCase("1")){
		            	pubAnnounceMent.setPubType(BulletinType.JusticeInfoFroz.getCode());
		            	pubAnnounceMent.setPubTitle(pubJusticeInfo.getEntName()+BulletinType.JusticeInfoFroz.getDesc());
		            }else if(pubJusticeInfo.getJusticeType().equalsIgnoreCase("2")){
		            	pubAnnounceMent.setPubType(BulletinType.JusticeInfoAlter.getCode());
		            	pubAnnounceMent.setPubTitle(pubJusticeInfo.getEntName()+BulletinType.JusticeInfoAlter.getDesc());
		            }
		            MidBaseInfo midBaseInfo = midBaseInfoService.selectByPripid(pubJusticeInfo.getPriPID());
					if(midBaseInfo != null && midBaseInfo.getRegOrg() !=null){
						pubAnnounceMent.setAuditArea(midBaseInfo.getRegOrg().substring(0, 4));
					}
					if(pubJusticeInfo.getUID() != null){
						newPubAnnounceMentInfo.setLinkUID(pubJusticeInfo.getUID());
						if(pubJusticeInfo.getJusticeType().equals("1")){
						newPubAnnounceMentInfo.setPubType(BulletinType.JusticeInfoFroz.getCode());
						}else{
						newPubAnnounceMentInfo.setPubType(BulletinType.JusticeInfoAlter.getCode());
						}
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
		            pubAnnounceMentMapper.insert(pubAnnounceMent);
		            total ++;
				 } catch (Exception e) {
					 logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				 }
			 }
		}
		return total;
	}
	
	/**
     * 简易注销定时插入
     * @author yujingwei
     * @date 2016-10-17
     * @param pubJusticeInfoList
     * @return void
     * @throws Exception
     */
	private int insertInfoForErEscAppinfo(List<ErEscAppinfo> erEscAppinfoList) throws Exception{
		int total = 0;
		PubAnnounceMent newPubAnnounceMentInfo = new PubAnnounceMent();
		if(CollectionUtils.isNotEmpty(erEscAppinfoList)){
			for(ErEscAppinfo erEscAppinfo : erEscAppinfoList){
				try {
					PubAnnounceMent pubAnnounceMent = new PubAnnounceMent();
					pubAnnounceMent.setLinkUID(erEscAppinfo.getPriPID());
					pubAnnounceMent.setPriPID(erEscAppinfo.getPriPID());
					pubAnnounceMent.setAuditDate(erEscAppinfo.getAppDate());
					pubAnnounceMent.setEntName(erEscAppinfo.getEntName());
					pubAnnounceMent.setCreateTime(new Date());
					pubAnnounceMent.setPubType(BulletinType.SimpleLogout.getCode());
					pubAnnounceMent.setPubTitle(erEscAppinfo.getEntName() +"关于申请"+BulletinType.SimpleLogout.getDesc());
					MidBaseInfo midBaseInfo = midBaseInfoService.selectByPripid(erEscAppinfo.getPriPID());
					if(midBaseInfo != null && midBaseInfo.getRegOrg() !=null){
						pubAnnounceMent.setAuditArea(midBaseInfo.getRegOrg().substring(0, 4));
					}
					if(erEscAppinfo.getPriPID() != null){
						newPubAnnounceMentInfo.setLinkUID(erEscAppinfo.getPriPID());
						newPubAnnounceMentInfo.setPubType(BulletinType.SimpleLogout.getCode());
						pubAnnounceMentMapper.delete(newPubAnnounceMentInfo);
					}
					pubAnnounceMentMapper.insert(pubAnnounceMent);
			        total ++;
				} catch (Exception e) {
				    logger.info("公告定时任务发生异常：{}",e);
					insertFailsTotal ++;
					continue;
				}
			}
		}
		return total;
	}
	
	/**
	 * 
	 * 描述: 新增
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param pubAnnounceMent
	 * @return
	 * @throws Exception
	 */
	public int insertOne(PubAnnounceMent pubAnnounceMent) throws Exception{
		pubAnnounceMent.setCreateTime(new Date());
		return pubAnnounceMentMapper.insert(pubAnnounceMent);
	}
	
	/**
	 * 
	 * 描述: 修改(通过linkUid更新)
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param pubAnnounceMent
	 * @return
	 * @throws Exception
	 */
	public int UpdateOne(PubAnnounceMent pubAnnounceMent) throws Exception{
		Example ex = new Example(PubAnnounceMent.class);
		ex.createCriteria().andEqualTo("linkUID", pubAnnounceMent.getLinkUID());
		pubAnnounceMent.setCreateTime(new Date());
		return pubAnnounceMentMapper.updateByExampleSelective(pubAnnounceMent, ex);
	}
	
	/**
	 * 
	 * 描述: 删除
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param linkuid
	 * @return
	 * @throws SQLException
	 */
    public int deleteOne(String linkuid) throws Exception {
		 Example ex = new Example(PubAnnounceMent.class);
		 ex.createCriteria().andEqualTo("linkUID", linkuid);
		 return pubAnnounceMentMapper.deleteByExample(ex);
	}
	
	
	
	

	
	
}