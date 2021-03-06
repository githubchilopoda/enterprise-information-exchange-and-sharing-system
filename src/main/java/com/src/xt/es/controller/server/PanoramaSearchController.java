package com.icinfo.cs.es.controller.server;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.utils.AESEUtil;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.common.utils.OssClient;
import com.icinfo.cs.common.utils.RepTypeUtil;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.concern.dto.CsConcernObjDto;
import com.icinfo.cs.concern.service.ICsConcernObjService;
import com.icinfo.cs.drcheck.dto.PubScitemResultDto;
import com.icinfo.cs.drcheck.service.IPubScitemResultService;
import com.icinfo.cs.es.dto.PanoramaResultDto;
import com.icinfo.cs.es.dto.PubEntSearchDto;
import com.icinfo.cs.es.model.CsAttachmentEstate;
import com.icinfo.cs.es.model.CsBrandInfo;
import com.icinfo.cs.es.model.CsMortEstateInfo;
import com.icinfo.cs.es.model.CsPatentiInfo;
import com.icinfo.cs.es.model.CsPubIlldisdetail;
import com.icinfo.cs.es.service.ICsAttachmentEstateService;
import com.icinfo.cs.es.service.ICsMortEstateInfoService;
import com.icinfo.cs.es.service.ICsPatentiInfoService;
import com.icinfo.cs.es.service.ICsPubIlldisdetailService;
import com.icinfo.cs.es.service.IEBrandInfoService;
import com.icinfo.cs.es.service.IEntSearchService;
import com.icinfo.cs.es.service.IPanoramaSearchService;
import com.icinfo.cs.es.util.ExcelFileUtil;
import com.icinfo.cs.ext.dto.MidAltitemDto;
import com.icinfo.cs.ext.dto.MidBaseInfoDto;
import com.icinfo.cs.ext.dto.MidBranchDto;
import com.icinfo.cs.ext.dto.MidInvDto;
import com.icinfo.cs.ext.dto.MidMemberDto;
import com.icinfo.cs.ext.dto.TmDetailInfoDto;
import com.icinfo.cs.ext.model.MidCaseInfo;
import com.icinfo.cs.ext.service.IMidAltitemService;
import com.icinfo.cs.ext.service.IMidBaseInfoService;
import com.icinfo.cs.ext.service.IMidBranchService;
import com.icinfo.cs.ext.service.IMidCaseInfoService;
import com.icinfo.cs.ext.service.IMidInvService;
import com.icinfo.cs.ext.service.IMidMemberService;
import com.icinfo.cs.ext.service.ITmDetailInfoService;
import com.icinfo.cs.favorable.model.FavorAbleInfo;
import com.icinfo.cs.favorable.service.IFavorAbleInfoService;
import com.icinfo.cs.im.dto.ImCaseDto;
import com.icinfo.cs.im.model.ImCasalt;
import com.icinfo.cs.im.model.ImInvestment;
import com.icinfo.cs.im.model.ImInvsralt;
import com.icinfo.cs.im.model.ImIppldg;
import com.icinfo.cs.im.model.ImPermit;
import com.icinfo.cs.im.service.IImCasaltService;
import com.icinfo.cs.im.service.IImCaseService;
import com.icinfo.cs.im.service.IImInvestmentService;
import com.icinfo.cs.im.service.IImInvsraltService;
import com.icinfo.cs.im.service.IImIppldgService;
import com.icinfo.cs.im.service.IImPermitService;
import com.icinfo.cs.login.model.PubEppassword;
import com.icinfo.cs.login.service.IPubEppasswordService;
import com.icinfo.cs.mainmark.service.IMainMarkRelService;
import com.icinfo.cs.nocreditPunish.model.NoCreditPunishInfo;
import com.icinfo.cs.nocreditPunish.service.NoCreditPunishInfoService;
import com.icinfo.cs.opanomaly.dto.PubOpaDetailDto;
import com.icinfo.cs.opanomaly.dto.PubOpanoMalyDto;
import com.icinfo.cs.opanomaly.dto.PubPbopanomalyDto;
import com.icinfo.cs.opanomaly.model.PubPbopanomaly;
import com.icinfo.cs.opanomaly.service.IPubOpaDetailService;
import com.icinfo.cs.opanomaly.service.IPubOpanoMalyService;
import com.icinfo.cs.opanomaly.service.IPubPbopanomalyService;
import com.icinfo.cs.other.dto.PubOtherlicenceDto;
import com.icinfo.cs.other.model.MortRegInfo;
import com.icinfo.cs.other.model.PubJusticeInfo;
import com.icinfo.cs.other.model.PubServerModrecord;
import com.icinfo.cs.other.model.SpPledge;
import com.icinfo.cs.other.service.IMortRegInfoService;
import com.icinfo.cs.other.service.IPubJusticeInfoService;
import com.icinfo.cs.other.service.IPubOtherlicenceService;
import com.icinfo.cs.other.service.IPubServerModrecordService;
import com.icinfo.cs.other.service.ISpPledgeService;
import com.icinfo.cs.registinfo.dto.PubApprMarkRelDto;
import com.icinfo.cs.registinfo.service.IPubApprMarkRelService;
import com.icinfo.cs.registmanage.model.QualificationLimit;
import com.icinfo.cs.registmanage.service.IQualificationLimitService;
import com.icinfo.cs.risk.dto.CsEntWarnDto;
import com.icinfo.cs.risk.service.ICsEntWarnService;
import com.icinfo.cs.secnocreditsup.model.ExpSeriousCrimeList;
import com.icinfo.cs.secnocreditsup.service.IExpSeriousCrimeListService;
import com.icinfo.cs.simpleesc.dto.ErEscAppinfoDto;
import com.icinfo.cs.simpleesc.service.IErEscAppinfoService;
import com.icinfo.cs.supervise.dto.CsSuperviseObjDto;
import com.icinfo.cs.supervise.service.ICsSuperviseObjService;
import com.icinfo.cs.system.controller.CSBaseController;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.cs.system.model.SendSmsTaskInfo;
import com.icinfo.cs.yr.dto.BaseInfoDto;
import com.icinfo.cs.yr.dto.PbBaseinfoDto;
import com.icinfo.cs.yr.dto.PubOtherpunishDto;
import com.icinfo.cs.yr.dto.SfcBaseInfoDto;
import com.icinfo.cs.yr.model.BaseInfo;
import com.icinfo.cs.yr.model.ForGuaranteeInfo;
import com.icinfo.cs.yr.model.Subcapital;
import com.icinfo.cs.yr.model.YrForinvestMent;
import com.icinfo.cs.yr.model.YrRegCheck;
import com.icinfo.cs.yr.service.IBaseInfoService;
import com.icinfo.cs.yr.service.IForGuaranteeInfoService;
import com.icinfo.cs.yr.service.IPbBaseinfoService;
import com.icinfo.cs.yr.service.IPubOtherPunishAlterService;
import com.icinfo.cs.yr.service.IPubOtherpunishService;
import com.icinfo.cs.yr.service.ISfcBaseInfoService;
import com.icinfo.cs.yr.service.ISubcapitalService;
import com.icinfo.cs.yr.service.IYrForinvestMentService;
import com.icinfo.cs.yr.service.IYrRegCheckService;
import com.icinfo.framework.common.ajax.AjaxResult;
import com.icinfo.framework.mybatis.pagehelper.Page;
import com.icinfo.framework.mybatis.pagehelper.PageHelper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageResponse;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * 全景查询控制器
 * 
 * @author zhuyong
 */

@Controller
@RequestMapping({ "/syn/panoramasearch", "/reg/server/panoramasearch" })
public class PanoramaSearchController extends CSBaseController {
	// private static final Logger logger =
	// LoggerFactory.getLogger(PanoramaSearchController.class);
	@Autowired
	IPbBaseinfoService pbBaseinfoService;
	@Autowired
	ISfcBaseInfoService sfcBaseInfoService;
	@Autowired
	private IImCaseService imCaseService;
	@Autowired
	private IImIppldgService imIppldgService;
	@Autowired
	private IImPermitService imPermitService;
	@Autowired
	private IPanoramaSearchService panoramaSearchService;
	@Autowired
	private IEntSearchService entSearchService;
	@Autowired
	private IMidMemberService midMemberService;
	@Autowired
	private IMidInvService midInvService;
	@Autowired
	private IPubOtherlicenceService pubOtherlicenceService;
	@Autowired
	private IMidCaseInfoService midCaseInfoService;
	@Autowired
	private IPubOpanoMalyService pubOpanoMalyService;
	@Autowired
	private NoCreditPunishInfoService noCreditPunishInfoService;
	@Autowired
	private ISpPledgeService spPledgeService;
	@Autowired
	private IPubScitemResultService pubScitemResultService;
	@Autowired
	private IMortRegInfoService mortRegInfoService;
	@Autowired
	private IPubJusticeInfoService pubJusticeInfoService;
	@Autowired
	private IEBrandInfoService eBrandInfoService;
	@Autowired
	private ICsPubIlldisdetailService csPubIlldisdetailService;
	@Autowired
	private ICsPatentiInfoService csPatentiInfoService;
	@Autowired
	private ICsMortEstateInfoService csMortEstateInfoService;
	@Autowired
	private IImInvestmentService imInvestmentService;
	@Autowired
	private IImInvsraltService imInvsraltService;
	@Autowired
	private ICsAttachmentEstateService csAttachmentEstateService;
	@Autowired
	private IMidBranchService midBranchService;
	@Autowired
	private ISubcapitalService subcapitalService;
	@Autowired
	private IYrRegCheckService yrRegCheckService;
	@Autowired
	private IYrForinvestMentService forinvestMentService;
	@Autowired
	private ITmDetailInfoService tmDetailInfoService;
	@Autowired
	private IPubEppasswordService pubEppasswordService;
	@Autowired
	private IQualificationLimitService qualificationLimitService;
	@Autowired
	private IExpSeriousCrimeListService expSeriousCrimeListService;
	@Autowired
	private IMidBaseInfoService midBaseInfoService;

	@Autowired
	ICsEntWarnService csEntWarnService;
	@Autowired
	ICsSuperviseObjService csSuperviseObjService;
	@Autowired
	ICsConcernObjService csConcernObjService;
	@Autowired
	IMidAltitemService midAltitemService;
	@Autowired
	IBaseInfoService baseInfoService;
	@Autowired
	IPubOtherpunishService pubOtherpunishService;
	@Autowired
	IPubOtherPunishAlterService pubOtherPunishAlterService;
	@Autowired
	IErEscAppinfoService erEscAppinfoService;
	@Autowired
	private IImCasaltService casaltService;
	@Autowired
	private IPubServerModrecordService pubServerModrecordService;
	@Autowired
	private IPubPbopanomalyService pubPbopanomalyService;
	@Autowired
	private IForGuaranteeInfoService forGuaranteeInfoService;
	@Autowired
	private IPubOpaDetailService pubOpaDetailService;

	@Autowired
	ExcelFileUtil excelFileUtil;

	@Autowired
	HttpServletResponse response;

	@Autowired
	OssClient ossClient;

	@Autowired
	private IFavorAbleInfoService favorAbleInfoService;

	@Autowired
	IMainMarkRelService mainMarkRelService;
	
	@Autowired
    private IPubApprMarkRelService pubApprMarkRelService;

	/**
	 * 生成全景索引
	 * 
	 * @author zhuyong
	 * @return
	 */
	@RequestMapping("createIndex")
	public String createIndex() throws Exception {
		panoramaSearchService.doInsertIndexToGs();
		return "";
	}

	/**
	 * 进入企业综合查询页面
	 * 
	 * @author zhuyong
	 * @return
	 */
	@RequestMapping("doenappsearch")
	public String doEnAppSearch() {
		return "syn/system/search/appsearch/appsearch";
	}

	/**
	 * 进入变更历史页面
	 * 
	 * @author zhuyong
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doEnChangeHis/{priPID}")
	public ModelAndView doEnChangeHis(@PathVariable(value = "priPID") String priPID) throws Exception {
		ModelAndView mav = new ModelAndView("syn/system/search/appsearch/appchangehis");

		return mav;
	}

	/**
	 * 进入企业综合查询页面
	 * 
	 * @author zhuyong
	 * @date 2017-03-24
	 * @param priPID,session
	 * @return view
	 */
	@RequestMapping("/doEnAppSearchDetails/{priPID}")
	public ModelAndView doEnAppSearchDetails(@PathVariable(value = "priPID") String priPID, HttpSession session)
			throws Exception {
		priPID = AESEUtil.decodeCorpid(priPID);
		ModelAndView mav = new ModelAndView("syn/system/search/appsearch/details");
		SysUserDto sysUser = (SysUserDto) session.getAttribute(Constants.SESSION_SYS_USER);
		Map<String, Object> qryMap = new HashMap<String, Object>();
		qryMap.put("priPID", priPID);
		MidBaseInfoDto midBaseInfoDto = entSearchService.selectByPripid(priPID);
		List<MidBaseInfoDto> midDtos = midBaseInfoService.selectPunishDate(priPID);
		if (midDtos != null && midDtos.size() > 0) {
			midBaseInfoDto.setPunishDate(midDtos.get(0).getPunishDate());
		}
		if (midBaseInfoDto != null) {
			QualificationLimit qualificationLimit = qualificationLimitService
					.selectLimitPersonInfo(midBaseInfoDto.getLeRep(), midBaseInfoDto.getCerNO());
			mav.addObject("isLimit", qualificationLimit);
		}
		// 联络员信息
		PubEppassword pubEppassword = pubEppasswordService.selectPubEppasswordByPriPid(priPID);
		mav.addObject("liaName", pubEppassword == null ? "" : pubEppassword.getLianame());
		mav.addObject("liaTel", pubEppassword == null ? "" : pubEppassword.getTel());
		mav.addObject("dto", midBaseInfoDto);
		mav.addObject("isOpanomaly", checkIsOpanomaly(midBaseInfoDto.getEntTypeCatg(), priPID));
		mav.addObject("isEscApp", erEscAppinfoService.getErEscAppinfoByPriPID(priPID));
		mav.addObject("sysUser", sysUser);
		// 加密主体身份代码
		mav.addObject("encryPriPID", AESEUtil.encodeCorpid(priPID));

		// 监管项目标签
		List<PubApprMarkRelDto> apprMarkRelDtos = pubApprMarkRelService.selectPubApprMarkRelDtoByPriPID(priPID);
		int apprMarkRelSccount = 0;
		if (apprMarkRelDtos != null && apprMarkRelDtos.size() > 0) {
			for (PubApprMarkRelDto apprMarkRelDto : apprMarkRelDtos) { // 查询出
				if (StringUtil.isNotEmpty(apprMarkRelDto.getDoublyStoState())
						&& "1".equals(apprMarkRelDto.getDoublyStoState())) {
					apprMarkRelDto.setMarkName(apprMarkRelDto.getMarkName() + "(专项库)");
					apprMarkRelSccount = apprMarkRelSccount + 1;
				}
			}
		}
		mav.addObject("apprMarkRelDtos", apprMarkRelDtos);
		mav.addObject("apprMarkRelDtoSize", apprMarkRelDtos.size());
		mav.addObject("apprMarkRelSccount", apprMarkRelSccount);
		// 重点监管对象
		List<CsSuperviseObjDto> csSuperviseObjDtoList = csSuperviseObjService.qryCsSuperviseObjDtoByPriPID(priPID);
		mav.addObject("csSuperviseObjDtoList", csSuperviseObjDtoList);
		mav.addObject("csSuperviseObjDtoSize", csSuperviseObjDtoList.size());

		// 关注服务对象
		List<CsConcernObjDto> csConcernObjDtoList = csConcernObjService.qryCsConcernObjDtoByPriPID(priPID);
		mav.addObject("csConcernObjDtoList", csConcernObjDtoList);
		mav.addObject("csConcernObjDtoSize", csConcernObjDtoList.size());

		// 预警提醒提示
		List<MidBaseInfoDto> midBaseInfoDtoList = midBaseInfoService.doGetListByPriPID(priPID);
		Date opTo = null;// 经营期限至
		Date licValTo = null;// 许可证有效期至
		Date abnTime = null;// 经营异常名录列入日期
		if (midBaseInfoDtoList != null && midBaseInfoDtoList.size() > 0) {
			opTo = midBaseInfoDtoList.get(0).getOpTo();
		}
		List<PubOtherlicenceDto> pubOtherlicenceDtoList = pubOtherlicenceService.selectOtherlicenceListByPriPID(priPID);
		if (pubOtherlicenceDtoList != null && pubOtherlicenceDtoList.size() > 0) {
			licValTo = pubOtherlicenceDtoList.get(0).getLicValTo();
		}
		if ("50".endsWith(midBaseInfoDto.getEntTypeCatg())) {
			List<PubPbopanomalyDto> pubPbopanomalyDtoList = pubPbopanomalyService.selectPubPbopanomalyPriPID(priPID);
			if (pubPbopanomalyDtoList != null && pubPbopanomalyDtoList.size() > 0) {
				abnTime = pubPbopanomalyDtoList.get(0).getCogDate();
			}
		} else {
			List<PubOpanoMalyDto> pubOpanoMalyDtoList = pubOpanoMalyService.selectPubOpanoMalyServicePriPID(priPID);
			if (pubOpanoMalyDtoList != null && pubOpanoMalyDtoList.size() > 0) {
				abnTime = pubOpanoMalyDtoList.get(0).getAbnTime();
			}
		}

		List<String> tips = new ArrayList<String>();
		Date after30 = DateUtil.addDays(new Date(), 30);// 当前时间后30天
		Date after60 = DateUtil.addDays(new Date(), 60);// 当前时间后60天

		if (opTo != null && (opTo.getTime() > new Date().getTime() && opTo.getTime() <= after30.getTime())) {
			tips.add("经营期限即将到期");
		} else if (opTo != null && opTo.getTime() <= new Date().getTime()) {
			tips.add("经营期限已经到期");
		}
		if (licValTo != null
				&& (licValTo.getTime() > new Date().getTime() && licValTo.getTime() <= after30.getTime())) {
			tips.add("许可证即将到期");
		} else if (licValTo != null && licValTo.getTime() <= new Date().getTime()) {
			tips.add("许可证已经到期");
		}

		if (abnTime != null) {
			Date abnTimeAfter3year = DateUtil.addYears(abnTime, 3);// 列异时间3年后
			if (abnTimeAfter3year.getTime() < after60.getTime() && abnTimeAfter3year.getTime() > new Date().getTime()) {
				tips.add("列入经营异常即将届满3年");
			}
		}
		mav.addObject("tips", tips);
		mav.addObject("tipsSize", tips.size());
		return mav;
	}

	/**
	 * 获取搜索结果JSON数据列表
	 *
	 * @author zhuyong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appsearchlist.json")
	@ResponseBody
	public List<PanoramaResultDto> listJSON(@NotBlank String keyword) throws Exception {
		return panoramaSearchService.doGetSearchList(keyword);
	}

	/**
	 * 获取高管人员信息
	 *
	 * @author zhuyong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetMemberList")
	@ResponseBody
	public PageResponse<MidMemberDto> doGetMemberList(PageRequest request) throws Exception {
		List<MidMemberDto> data = midMemberService.queryListResult(request);
		return new PageResponse<MidMemberDto>(data);
	}

	/**
	 * 获取高管人员信息(人员受限)
	 *
	 * @author yujingwei
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/limitMemberList")
	@ResponseBody
	public PageResponse<MidMemberDto> limitMemberList(PageRequest request) throws Exception {
		List<MidMemberDto> data = midMemberService.selectMemberListForLimit(request);
		return new PageResponse<MidMemberDto>(data);
	}

	/**
	 * 获取分支机构信息
	 *
	 * @author yujingwei
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetBranchList")
	@ResponseBody
	public PageResponse<MidBranchDto> doGetBranchList(PageRequest request) throws Exception {
		List<MidBranchDto> data = midBranchService.queryListResult(request);
		return new PageResponse<MidBranchDto>(data);
	}

	/**
	 * 获取变更信息
	 *
	 * @author yujingwei
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/midaltitemList")
	@ResponseBody
	public PageResponse<MidAltitemDto> doGetMidaltitemList(PageRequest request) throws Exception {
		List<MidAltitemDto> data = midAltitemService.queryPageResult(request);
		return new PageResponse<MidAltitemDto>(data);
	}

	/**
	 * 获取股东及出资信息
	 *
	 * @author zhuyong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetMidInvList")
	@ResponseBody
	public PageResponse<MidInvDto> doGetMidInvList(PageRequest request) throws Exception {
		List<MidInvDto> data = midInvService.queryPageResult(request);
		return new PageResponse<MidInvDto>(data);
	}

	/**
	 * 获取股东及出资信息(数据来源于最近一年企业年报)
	 *
	 * @author yujingwei
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/subcapitList")
	@ResponseBody
	public PageResponse<Subcapital> doGetSubcapitList(PageRequest request) throws Exception {
		String ancheID = null;
		Map<String, Object> paramMap = request.getParams();
		if (paramMap != null) {
			String priPID = (String) paramMap.get("priPID");
			List<YrRegCheck> yrRegChecks = yrRegCheckService.doGetYrRegCheckInit(priPID);
			if (yrRegChecks != null && yrRegChecks.size() > 0) {
				Integer year = yrRegChecks.get(0).getYear();
				String entTypeCatg = yrRegChecks.get(0).getEntTypeCatg();
				if (!entTypeCatg.equals(Constants.entTypeCatgPB)
						&& Constants.entTypeCatgSFC.indexOf(entTypeCatg) == -1) {
					BaseInfo BaseInfo = baseInfoService.selectInfoByPripidAndYear(priPID, year);
					ancheID = BaseInfo.getAnCheID();
				}
			}
		}
		if (ancheID != null) {
			paramMap.put("anCheID", ancheID);
			List<Subcapital> data = subcapitalService.queryPage(request);
			return new PageResponse<Subcapital>(data);
		}
		return null;
	}

	/**
	 * 获取对外投资信息(数据来源于最近一年的企业年报)
	 *
	 * @author yujingwei
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forinvestMentList")
	@ResponseBody
	public PageResponse<YrForinvestMent> doGetForinvestMentList(PageRequest request) throws Exception {
		// 年度 ， 主体代码
		Integer year = null;
		String priPID = null;
		Map<String, Object> paramMap = request.getParams();
		if (paramMap != null) {
			priPID = (String) paramMap.get("priPID");
			List<YrRegCheck> yrRegChecks = yrRegCheckService.doGetYrRegCheckInit(priPID);
			if (CollectionUtils.isNotEmpty(yrRegChecks)) {
				for (YrRegCheck yrRegCheck : yrRegChecks) {
					if ("1".equals(yrRegCheck.getIsReported())) {
						// 获取最近已年报年度
						year = yrRegCheck.getYear();
						break;
					}
				}
			}
		}
		if (priPID != null && year != null) {
			paramMap.put("priPID", priPID);
			paramMap.put("year", year);
			List<YrForinvestMent> data = forinvestMentService.queryPagePreview(request);
			return new PageResponse<YrForinvestMent>(data);
		}
		return new PageResponse<YrForinvestMent>(null);
	}

	/**
	 * 获取企业许可审批信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetPubOtherlicenceList")
	@ResponseBody
	public PageResponse<PubOtherlicenceDto> doGetPubOtherlicenceList(PageRequest request) throws Exception {
		// List<PubOtherlicence> data =
		// pubOtherlicenceService.selectOtherlicenceListByPriPid2(request);
		List<PubOtherlicenceDto> data = pubOtherlicenceService.select_queryPage(request);// 执行参数条件查询
		for (PubOtherlicenceDto pubOtherlicenceDto : data) {
			// 变更信息
			List<PubServerModrecord> pubOtherlicenceModList = pubServerModrecordService
					.doGetAlterInfoList(pubOtherlicenceDto.getLicID());
			pubOtherlicenceDto.setPubOtherlicenceModList(pubOtherlicenceModList);
		}
		return new PageResponse<PubOtherlicenceDto>(data);
	}

	/**
	 * 获取查询企业列表
	 * 
	 * @author zhuyong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/panoQueryPage" })
	@ResponseBody
	public PageResponse<PanoramaResultDto> panoQueryPage(PageRequest request) throws Exception {
		Page<PanoramaResultDto> data = panoramaSearchService.doGetSearchList(request);
		return new PageResponse<PanoramaResultDto>(data);
	}

	/**
	 * 获取短信群发企业列表(业务辅助管理-短信群发)
	 * 
	 * @author zhuyong
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/smsQueryPage" })
	@ResponseBody
	public PageResponse<PanoramaResultDto> smsQueryPage(PageRequest request) throws Exception {
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Page<PanoramaResultDto> data = panoramaSearchService.doGetSmsSearchList(request);
		return new PageResponse<PanoramaResultDto>(data);
	}

	/**
	 * 获取查询企业综合查询列表(公示信息管理-年报综合查询)
	 * 
	 * @author baifangfang
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/comPanoQueryPage" })
	@ResponseBody
	public PageResponse<PanoramaResultDto> comPanoQueryPage(PageRequest request) throws Exception {
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Page<PanoramaResultDto> data = panoramaSearchService.doGetComSearchList(request);
		return new PageResponse<PanoramaResultDto>(data);
	}

	/**
	 * 获取查询企业综合查询列表(全部导出功能)
	 * 
	 * @author baifangfang
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "/comPanoQueryPageDownLoad" })
	@ResponseBody
	public void comPanoQueryPageDownLoad(@RequestParam Map<String, Object> map) throws Exception {
		Object query = map.get("map");
		JSONObject jasonObject = JSONObject.fromObject(query.toString());
		Map<String, Object> queryMap = (Map<String, Object>) jasonObject;
		PageRequest request = new PageRequest();
		request.setParams(queryMap);
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Page<PanoramaResultDto> data = panoramaSearchService.doGetComSearchListAll(request);
		List<PanoramaResultDto> panoramaResultDtoList = data.getResult();
		HSSFWorkbook hwb = excelFileUtil.write(panoramaResultDtoList, queryMap.get("yrYear").toString());
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(("年报信息综合查询.xls").getBytes("GB2312"), "ISO8859-1"));
		OutputStream ouputStream = response.getOutputStream();
		hwb.write(ouputStream);
	}

	/**
	 * 获取即将要导出的数量(全部导出功能)
	 * 
	 * @author baifangfang
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/comPanoQueryPageDownLoadCount" })
	@ResponseBody
	public Integer comPanoQueryPageDownLoadCount(@RequestBody Map<String, Object> map) throws Exception {
		PageRequest request = new PageRequest();
		request.setParams(map);
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Integer count = panoramaSearchService.doGetComSearchListAllCount(request);
		return count;
	}

	/**
	 * 获取查询所有企业名单(群发全部，目前没有用到)
	 * 
	 * @author baifangfang
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "/smsQueryPageAll" })
	public ModelAndView smsQueryPageAll(@RequestParam Map<String, Object> map, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("reg/server/smsgroupsend/smsshow");
		PageRequest request = new PageRequest();
		Object query = map.get("map");
		JSONObject jasonObject = JSONObject.fromObject(query.toString());
		Map<String, Object> queryMap = (Map<String, Object>) jasonObject;
		request.setParams(queryMap);
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Page<PanoramaResultDto> data = panoramaSearchService.doGetSmsSearchList(request);
		List<PanoramaResultDto> panoramaResultDtoList = data.getResult();

		SysUserDto sysUser = (SysUserDto) session.getAttribute(Constants.SESSION_SYS_USER);
		mav.addObject("setName", sysUser.getRealName());
		mav.addObject("deptId", sysUser.getDepartMent().getDeptDID());
		mav.addObject("deptName", sysUser.getDept());

		// 拼接短信任务详情放入map中，SendSmsTaskInfo
		Map<String, SendSmsTaskInfo> sendSmsTaskInfoMap = new HashMap<String, SendSmsTaskInfo>();

		// 任务原始名单
		List<String> lerepTelList = new ArrayList<String>();
		List<String> liaPhoneList = new ArrayList<String>();
		List<String> priPIDList = new ArrayList<String>();
		for (int i = 0; i < panoramaResultDtoList.size(); i++) {
			SendSmsTaskInfo sendSmsTaskInfo = new SendSmsTaskInfo();
			sendSmsTaskInfo.setSmsid((i + 1) + "");
			PanoramaResultDto panoramaResultDto = panoramaResultDtoList.get(i);
			BeanUtils.copyProperties(panoramaResultDto, sendSmsTaskInfo);
			String uniSCID = panoramaResultDto.getUniscid();
			sendSmsTaskInfo.setUniSCID(uniSCID);
			sendSmsTaskInfoMap.put(panoramaResultDto.getPriPID(), sendSmsTaskInfo);

			if (!StringUtil.isEmpty(panoramaResultDto.getTel())) {
				lerepTelList.add(panoramaResultDto.getTel());
			}
			if (!StringUtil.isEmpty(panoramaResultDto.getLiaTel())) {
				liaPhoneList.add(panoramaResultDto.getLiaTel());
			}
			if (!StringUtil.isEmpty(panoramaResultDto.getPriPID())) {
				priPIDList.add(panoramaResultDto.getPriPID());
			}
		}
		int liaPhoneCount = liaPhoneList.size();
		int lerepTelCount = lerepTelList.size();
		int totalTelCount = liaPhoneCount + lerepTelCount;
		int entTotalCount = priPIDList.size();
		String taskStart = "企业共" + entTotalCount + "户，手机号共" + totalTelCount + "条，其中负责人手机号" + lerepTelCount + "条，联络员手机号"
				+ liaPhoneCount + "条";
		mav.addObject("smsIntroduction", taskStart);

		// 联络员和法人都去重名单
		Set<String> lerepTelSet = new HashSet<String>(lerepTelList);
		List<String> lerepTelListQC = new ArrayList<String>();
		lerepTelListQC.addAll(lerepTelSet);
		Set<String> liaPhoneSet = new HashSet<String>(liaPhoneList);
		List<String> liaPhoneListQC = new ArrayList<String>();
		liaPhoneListQC.addAll(liaPhoneSet);
		int liaPhoneCountQC = liaPhoneListQC.size();
		int lerepTelCountQC = lerepTelListQC.size();
		int totalTelCountQC = liaPhoneCountQC + lerepTelCountQC;

		String taskEndAnd = "企业共" + entTotalCount + "户，手机号去重后共" + totalTelCountQC + "条，其中负责人手机号去重后" + lerepTelCountQC
				+ "条，联络员手机号去重后" + liaPhoneCountQC + "条";
		String taskEndLerep = "企业共" + entTotalCount + "户，手机号去重后共" + totalTelCountQC + "条，其中负责人手机号去重后" + lerepTelCountQC
				+ "条，联络员手机号" + liaPhoneCount + "条";
		String taskEndLia = "企业共" + entTotalCount + "户，手机号去重后共" + totalTelCountQC + "条，其中负责人手机号" + lerepTelCount
				+ "条，联络员手机号去重后" + liaPhoneCountQC + "条";

		mav.addObject("rsmsIntroductionAnd", taskEndAnd);
		mav.addObject("rsmsIntroductionLerep", taskEndLerep);
		mav.addObject("rsmsIntroductionLia", taskEndLia);

		session.setAttribute("sendSmsTaskInfoMap", sendSmsTaskInfoMap);
		return mav;
	}

	/**
	 * 描述：企业年报多年度综合查询(公示信息管理-企业多年度年报名单查询)
	 *
	 * @author baifangfang
	 * @date 2017年1月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yearsPanoQueryPage")
	@ResponseBody
	public PageResponse<PanoramaResultDto> yearsPanoQueryPage(PageRequest request) throws Exception {
		creatDefaultDBAuthEnv(request, "localAdm", "localAdm");
		Page<PanoramaResultDto> data = panoramaSearchService.doGetYearsComSearchList(request);
		return new PageResponse<PanoramaResultDto>(data);
	}

	/**
	 * 获取企业行政处罚信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetMidCaseInfoList")
	@ResponseBody
	public PageResponse<MidCaseInfo> doGetMidCaseInfoList(PageRequest request) throws Exception {
		List<MidCaseInfo> data = midCaseInfoService.selectMidCaseInfoListByPriId(request);
		return new PageResponse<MidCaseInfo>(data);
	}

	/**
	 * 获取企业经营异常信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetPubOpanoMalyList")
	@ResponseBody
	public PageResponse<PubOpanoMalyDto> doGetPubOpanoMalyList(PageRequest request) throws Exception {
		List<PubOpanoMalyDto> data = pubOpanoMalyService.queryPubOpanoMalyListForPub(request);
		return new PageResponse<PubOpanoMalyDto>(data);
	}

	/**
	 * 
	 * 描述 个体户异常 @author 赵祥江 @date 2017年3月20日 下午3:00:26 @param @return
	 * PageResponse<PubPbopanomalyDto> @throws
	 */
	@RequestMapping("/doGetPbOpanoMalyList")
	@ResponseBody
	public PageResponse<PubPbopanomaly> doGetPbOpanoMalyList(PageRequest request) throws Exception {
		List<PubPbopanomaly> data = pubPbopanomalyService.queryPubPbopanomalyListForPub(request);
		return new PageResponse<PubPbopanomaly>(data);
	}

	/**
	 * 获取失信联合惩戒信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetNoCreditPunishInfoList")
	@ResponseBody
	public PageResponse<NoCreditPunishInfo> doGetNoCreditPunishInfoList(PageRequest request) throws Exception {
		List<NoCreditPunishInfo> data = noCreditPunishInfoService.selectInfoListByPriPID(request);
		return new PageResponse<NoCreditPunishInfo>(data);
	}

	/**
	 * 获取企业股权出质信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetSpPledgeList")
	@ResponseBody
	public PageResponse<SpPledge> doGetSpPledgeList(PageRequest request) throws Exception {
		List<SpPledge> data = spPledgeService.selectSpPledgeListByPriPID(request);
		return new PageResponse<SpPledge>(data);
	}

	/**
	 * 获取抽查检查信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetPubScitemResultList")
	@ResponseBody
	public PageResponse<PubScitemResultDto> doGetPubScitemResultList(PageRequest request) throws Exception {
		List<PubScitemResultDto> data = pubScitemResultService.selectPubScitemResultListByPriPID(request);
		return new PageResponse<PubScitemResultDto>(data);
	}

	/**
	 * 获取企业动产抵押信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetMortRegInfoList")
	@ResponseBody
	public PageResponse<MortRegInfo> doGetMortRegInfoList(PageRequest request) throws Exception {
		List<MortRegInfo> data = mortRegInfoService.selectMortRegInfoListByPriPID(request);
		return new PageResponse<MortRegInfo>(data);
	}

	/**
	 * 获取企业股权冻结信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetPubJusticeInfoList")
	@ResponseBody
	public PageResponse<PubJusticeInfo> doGetPubJusticeInfoList(PageRequest request) throws Exception {
		List<PubJusticeInfo> data = pubJusticeInfoService.selectPubJusticeInfoListByPriPID(request);
		return new PageResponse<PubJusticeInfo>(data);
	}

	/**
	 * 获取企业商标信息
	 *
	 * @author chenyu
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetEBrandInfoList")
	@ResponseBody
	public PageResponse<CsBrandInfo> doGetEBrandInfoList(PageRequest request) throws Exception {
		List<CsBrandInfo> data = eBrandInfoService.selectCsBrandInfoListByPriPID(request);
		return new PageResponse<CsBrandInfo>(data);
	}

	/**
	 * 描述: 商标注册信息查询
	 * 
	 * @auther yujingwei
	 * @date 2017年3月20日
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/brandInfoList")
	@ResponseBody
	public PageResponse<TmDetailInfoDto> doGetBrandInfoList(PageRequest request) throws Exception {
		List<TmDetailInfoDto> tmDetailInfoDtos = tmDetailInfoService.doGetBrandInfoList(request);
		if (tmDetailInfoDtos != null && tmDetailInfoDtos.size() > 0) {
			for (TmDetailInfoDto tmDetailInfoDto : tmDetailInfoDtos) {
				tmDetailInfoDto.setTM_IMAGE(downFormOSS("brand/" + tmDetailInfoDto.getREG_NUM() + ".jpg"));
				BASE64Encoder encoder = new BASE64Encoder();
				tmDetailInfoDto.setImageString(encoder.encode(tmDetailInfoDto.getTM_IMAGE()));
			}
			return new PageResponse<TmDetailInfoDto>(tmDetailInfoDtos);
		}
		return new PageResponse<TmDetailInfoDto>(null);
	}

	/**
	 * 获取企业严重违法信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetCsPubIlldisDetailList")
	@ResponseBody
	public PageResponse<CsPubIlldisdetail> doGetCsPubIlldisDetailList(PageRequest request) throws Exception {
		List<CsPubIlldisdetail> data = csPubIlldisdetailService.selectCsPubIlldisDetailListByPriPID(request);
		return new PageResponse<CsPubIlldisdetail>(data);
	}

	/**
	 * 获取企业专利信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetCsPatentiInfoList")
	@ResponseBody
	public PageResponse<CsPatentiInfo> doGetCsPatentiInfoList(PageRequest request) throws Exception {
		List<CsPatentiInfo> data = csPatentiInfoService.selectCsPatentiInfoListByPriPID(request);
		return new PageResponse<CsPatentiInfo>(data);
	}

	/**
	 * 获取企业不动产抵押信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetCsMortEstateInfoList")
	@ResponseBody
	public PageResponse<CsMortEstateInfo> doGetCsMortEstateInfoList(PageRequest request) throws Exception {
		List<CsMortEstateInfo> data = csMortEstateInfoService.selectCsMortEstateInfoListByPriPID(request);
		return new PageResponse<CsMortEstateInfo>(data);
	}

	/**
	 * 获取企业不动产查封信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetCsAttachmentEstateList")
	@ResponseBody
	public PageResponse<CsAttachmentEstate> doGetCsAttachmentEstateList(PageRequest request) throws Exception {
		List<CsAttachmentEstate> data = csAttachmentEstateService.selectCsAttachmentEstateListByPriPID(request);
		return new PageResponse<CsAttachmentEstate>(data);
	}

	/**
	 * 
	 * 描述 获取最近一年的对外担保信息 @author 赵祥江 @date 2017年3月20日 下午4:39:30 @param @return
	 * PageResponse<ForGuaranteeInfo> @throws
	 */
	@RequestMapping("/doGetforGuaranteeInfoList")
	@ResponseBody
	public PageResponse<ForGuaranteeInfo> doGetforGuaranteeInfoList(PageRequest request) throws Exception {
		String priPID = request.getParams().get("priPID").toString();
		int preYear = Integer.parseInt(DateUtil.getYear()) - 1;
		BaseInfo baseInfo = baseInfoService.selectInfoByPripidAndYear(priPID, preYear);
		String anCheID = baseInfo == null ? "00000" : baseInfo.getAnCheID();
		request.getParams().put("anCheID", anCheID);
		List<ForGuaranteeInfo> data = forGuaranteeInfoService.selectForGuaranteeInfoByAnCheIDPage(request);
		return new PageResponse<ForGuaranteeInfo>(data);
	}

	/**
	 * 
	 * 描述: 即时股东出资信息
	 * 
	 * @auther chenyu
	 * @date 2016年9月26日
	 * @param request
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investmentListJSON")
	@ResponseBody
	public PageResponse<ImInvestment> investmentListJSON(PageRequest request) throws Exception {
		request.getParams().put("pubFlag", "1");
		request.getParams().put("pageNum", request.getPageNum());
		List<ImInvestment> list = imInvestmentService.findInvestmentWithDetatilForPub(request);
		return new PageResponse<ImInvestment>(list);
	}

	/**
	 * 
	 * 描述: 股权变更信息
	 * 
	 * @auther chenyu
	 * @date 2016年9月24日
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("invsraListJSON")
	@ResponseBody
	public PageResponse<ImInvsralt> invsraListJSON(PageRequest request) throws Exception {
		Map<String, Object> map = request.getParams();
		// map.put("pageNum", 0);
		String priPID = map.get("priPID").toString();
		List<ImInvsralt> list = new ArrayList<ImInvsralt>();
		if (StringUtils.isNotEmpty(priPID)) {
			// request.setParams(map);
			list = imInvsraltService.queryPageForPub(request, priPID);
		}
		return new PageResponse<ImInvsralt>(list);
	}

	/**
	 * 
	 * 描述:查询行政许可
	 * 
	 * @auther chenyu
	 * @date 2016年9月24日
	 * @param request
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("permitListJSON")
	@ResponseBody
	public PageResponse<ImPermit> permitListJSON(PageRequest request) throws Exception {
		Map<String, Object> map = request.getParams();
		String priPID = map.get("priPID").toString();
		// String priPID = "3301830000205091";
		List<ImPermit> list = new ArrayList<ImPermit>();
		if (StringUtils.isNotEmpty(priPID)) {
			// priPID=AESEUtil.decodeCorpid(priPID);
			map.put("pageNum", request.getPageNum());
			list = imPermitService.queryPageForPub(request, priPID);
		}
		return new PageResponse<ImPermit>(list);
	}

	/**
	 * 
	 * 描述: 知识产权登记
	 * 
	 * @auther chenyu
	 * @date 2016年9月24日
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ippldgListJSON")
	@ResponseBody
	public PageResponse<ImIppldg> ippldgListJSON(PageRequest request) throws Exception {
		Map<String, Object> map = request.getParams();
		String priPID = map.get("priPID").toString();
		// String priPID = "3301830000205091";
		List<ImIppldg> list = new ArrayList<ImIppldg>();
		if (StringUtils.isNotEmpty(priPID)) {
			// priPID=AESEUtil.decodeCorpid(priPID);
			map.put("pageNum", request.getPageNum());
			list = imIppldgService.queryPageForPUb(request, priPID);
		}
		return new PageResponse<ImIppldg>(list);
	}

	/**
	 * 
	 * 描述: 查询行政处罚
	 * 
	 * @auther chenyu
	 * @date 2016年9月24日
	 * @param request
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("caseListJSON")
	@ResponseBody
	public PageResponse<ImCaseDto> caseListJSON(PageRequest request) throws Exception {
		Map<String, Object> map = request.getParams();
		String priPID = map.get("priPID").toString();
		map.put("pageNum", request.getPageNum());
		List<ImCaseDto> list = imCaseService.queryToPub(request, priPID);
		for (ImCaseDto dto : list) {
			List<ImCasalt> lis = casaltService.queryForPub(dto.getCaseNO());
			dto.setCaslatList(lis);
		}
		return new PageResponse<ImCaseDto>(list);
	}

	/**
	 * 
	 * 描述 查询工商和其他部门的行政处罚 @author 赵祥江 @date 2017年3月16日 下午3:59:50 @param @return
	 * PageResponse<PubOtherpunishDto> @throws
	 */
	@RequestMapping("selectPubOtherpunishDtoJSON")
	@ResponseBody
	public PageResponse<PubOtherpunishDto> selectPubOtherpunishDtoJSON(PageRequest request) throws Exception {
		/*
		 * PageRequest request=new PageRequest(); Map<String,Object>
		 * queryMap=new HashMap<String,Object>(); queryMap.put("priPID",
		 * "3300000000020341"); request.setLength(999999);
		 * request.setParams(queryMap);
		 */
		List<PubOtherpunishDto> pubOtherpunishDtoList = pubOtherpunishService.selectPubOtherpunishDtoJSON(request);
		if (pubOtherpunishDtoList.size() > 0) {
			for (PubOtherpunishDto pubOtherpunishDto : pubOtherpunishDtoList) {
				String caseNo = com.icinfo.framework.mybatis.mapper.util.StringUtil
						.isEmpty(pubOtherpunishDto.getCaseNO()) ? "00000" : pubOtherpunishDto.getCaseNO();
				String pripid = com.icinfo.framework.mybatis.mapper.util.StringUtil
						.isEmpty(pubOtherpunishDto.getPriPID()) ? "00000" : pubOtherpunishDto.getPriPID();
				// 变更信息
				pubOtherpunishDto
						.setPubOtherPunishAlterList(pubOtherPunishAlterService.doGetAlterInfoList(pripid, caseNo));
			}
		}
		return new PageResponse<PubOtherpunishDto>(pubOtherpunishDtoList);
	}

	/**
	 * 
	 * 描述 根据主体身份代码获取简易注销公告 @author 赵祥江 @date 2017年3月17日
	 * 上午11:16:46 @param @return PageResponse<PubOtherpunishDto> @throws
	 */
	@RequestMapping("selectResultByPriPID")
	@ResponseBody
	public PageResponse<ErEscAppinfoDto> selectResultByPriPID(PageRequest request) throws Exception {
		/*
		 * PageRequest request=new PageRequest(); Map<String,Object>
		 * queryMap=new HashMap<String,Object>(); queryMap.put("priPID",
		 * "3300000000020341");
		 */
		request.setLength(999999);
		List<ErEscAppinfoDto> erEscAppinfoDtoList = erEscAppinfoService.queryErEscAppinfoPageResult(request);
		return new PageResponse<ErEscAppinfoDto>(erEscAppinfoDtoList);
	}

	/**
	 * 描述：企业年报综合查询页面
	 *
	 * @author baifangfang
	 * @date 2016年9月21日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public ModelAndView list() throws Exception {
		ModelAndView mav = new ModelAndView("reg/server/search/entreportsearch/entreportsearch_view");
		List<String> yearList = DateUtil.getYearToNow();
		yearList.remove(yearList.size() - 1);
		mav.addObject("yearList", yearList);
		return mav;
	}

	/**
	 * 描述：企业年报多年度综合查询
	 *
	 * @author baifangfang
	 * @date 2017年1月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yearsStatuslist")
	public ModelAndView yearsStatuslist() throws Exception {
		ModelAndView mav = new ModelAndView("reg/server/search/entreportsearch/entreportsearchyears_view");
		List<String> yearList = DateUtil.getYearToNow();
		yearList.remove(yearList.size() - 1);
		mav.addObject("yearList", yearList);
		return mav;
	}

	/**
	 * 进入风险标示页面
	 * 
	 * @author zhuyong
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCsEntWarn/{priPID}")
	@ResponseBody
	public ModelAndView doCsEntWarn(@PathVariable("priPID") String priPID) throws Exception {
		ModelAndView mav = new ModelAndView("/syn/system/search/appsearch/appcsentwarn");
		mav.addObject("priPID", priPID);
		return mav;
	}

	/**
	 * 进入重点行业标示页面
	 * 
	 * @author zhuyong
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCsSuperviseObj/{priPID}")
	@ResponseBody
	public ModelAndView doCsSuperviseObj(@PathVariable("priPID") String priPID) throws Exception {
		ModelAndView mav = new ModelAndView("/syn/system/search/appsearch/appcssuperviseobj");
		mav.addObject("priPID", priPID);
		return mav;
	}

	/**
	 * 进入关注服务标识页面
	 * 
	 * @author zhuyong
	 * @param priPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCsConcernObj/{priPID}")
	@ResponseBody
	public ModelAndView doCsConcernObj(@PathVariable("priPID") String priPID) throws Exception {
		ModelAndView mav = new ModelAndView("/syn/system/search/appsearch/appcsconcernobj");
		mav.addObject("priPID", priPID);
		return mav;
	}

	/**
	 * 获取风险标示信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doGetCsEntWarnList.json")
	@ResponseBody
	public PageResponse<CsEntWarnDto> doGetCsEntWarnList(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Map<String, Object> qryMap = request.getParams();
		List<CsEntWarnDto> data = csEntWarnService.selectEffectiveWarnMark(qryMap);
		return new PageResponse<CsEntWarnDto>(data);
	}

	/**
	 * 获取重点行业标示信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/doGetCsSuperviseObjList.json", "list.xml" })
	@ResponseBody
	public PageResponse<CsSuperviseObjDto> doGetCsSuperviseObjList(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Map<String, Object> qryMap = request.getParams();
		List<CsSuperviseObjDto> data = csSuperviseObjService.selectEffectiveSupMark(qryMap);
		return new PageResponse<CsSuperviseObjDto>(data);
	}

	/**
	 * 获取关注服务标示信息
	 * 
	 * @author chenyu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/doGetCsConcernObjList.json", "list.xml" })
	@ResponseBody
	public PageResponse<CsConcernObjDto> doGetCsConcernObjList(PageRequest request) throws Exception {
		PageHelper.startPage(request.getPageNum(), request.getLength());
		Map<String, Object> qryMap = request.getParams();
		List<CsConcernObjDto> data = csConcernObjService.selectEffectiveConMark(qryMap);
		return new PageResponse<CsConcernObjDto>(data);
	}

	/**
	 * 描述：企业基本信息
	 * 
	 * @author baifangfang
	 * @date 2016年12月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("baseinfosyn")
	public ModelAndView selectByDocId(@RequestParam String encryPriPID) throws UnsupportedEncodingException {
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("depsyn/search/baseinfosyn");

		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);
		mav.addObject("entBasicInfo", entBasicInfo);
		// 即时信息报表配置
		mav.addObject("imReportType",
				RepTypeUtil.getImReportTypeList(entBasicInfo.getEntTypeCatg(), entBasicInfo.getEntType()).toString());

		return mav;
	}

	/**
	 * 描述：企业年报信息
	 * 
	 * @author baifangfang
	 * @date 2016年12月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yrinfosyn")
	public ModelAndView yrcheckinfo(@RequestParam String encryPriPID, @RequestParam String year) throws Exception {
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);

		ModelAndView mav = new ModelAndView("depsyn/search/yrinfosyn");
		mav.addObject("entBasicInfo", entBasicInfo);

		String entTypeCatgStr = entBasicInfo.getEntTypeCatg();// 企业大类

		// 50 个体户，16,17 农专，其他 企业
		if ("50".equals(entTypeCatgStr)) {
			PbBaseinfoDto pbBaseinfoDto = pbBaseinfoService.queryPbYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = pbBaseinfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				pbBaseinfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", pbBaseinfoDto);
		} else if ("16".equals(entTypeCatgStr) || "17".equals(entTypeCatgStr)) {
			SfcBaseInfoDto sfcBaseInfoDto = sfcBaseInfoService.querySfcYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = sfcBaseInfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				sfcBaseInfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", sfcBaseInfoDto);
		} else {
			BaseInfoDto baseInfoDto = baseInfoService.queryYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = baseInfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				baseInfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", baseInfoDto);
		}

		// 获取企业报表类型
		String repType = RepTypeUtil.getReportTypeList(entTypeCatgStr, entBasicInfo.getEntType(), year).toString();
		mav.addObject("repType", repType);
		return mav;
	}

	/**
	 * 
	 * 描述: 企业综合查询年度年报树
	 * 
	 * @auther baifangfang
	 * @date 2017年1月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yearStatusTree")
	public ModelAndView yearStatusTree() throws Exception {
		ModelAndView view = new ModelAndView("reg/server/search/entreportsearch/yearsreportstatus_tree");
		return view;
	}

	/**
	 * 企业综合查询年度年报树JSON
	 * 
	 * @auther baifangfang
	 * @date 2017年1月12日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/yearStatusTreeJSON")
	@ResponseBody
	public AjaxResult treeEntcatgListJSON() throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		List<String> yearList = DateUtil.getYearToNow();
		List<String> acYearList = new ArrayList<>();
		yearList.remove(0);

		if (yearList.size() > 4) {
			acYearList = yearList.subList(0, 4);
		} else {
			acYearList = yearList;
		}

		for (int i = 0; i < acYearList.size(); i++) {
			String year = acYearList.get(i);

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", year);
			map1.put("pId", "0");
			map1.put("id", year);
			data.add(map1);
			Map<String, Object> map1_1 = new HashMap<String, Object>();
			map1_1.put("name", "未年报");
			map1_1.put("pId", year);
			map1_1.put("id", year + "-0");
			data.add(map1_1);
			Map<String, Object> map1_2 = new HashMap<String, Object>();
			map1_2.put("name", "已年报");
			map1_2.put("pId", year);
			map1_2.put("id", year + "-1");
			data.add(map1_2);
			Map<String, Object> map1_3 = new HashMap<String, Object>();
			map1_3.put("name", "已年报(逾期)");
			map1_3.put("pId", year);
			map1_3.put("id", year + "-2");
			data.add(map1_3);
		}
		return AjaxResult.success("查询成功", data);
	}

	/**
	 * 从OSS下载文件接口
	 * 
	 * @param fileName
	 * @return
	 */
	private byte[] downFormOSS(String fileName) {
		if (fileName == null || StringUtils.isBlank(fileName)) {
			return null;
		}
		try {// 从OSS获取文件流
			byte[] by = null;
			by = ossClient.getStream(fileName);
			return by;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 描述 根据主体身份代码获取良好企业信息 @author 赵祥江 @date 2017年3月8日 下午3:37:44 @param @return
	 * PageResponse<FavorAbleInfoDto> @throws
	 */
	@RequestMapping("/selectFavorAbleInfoJSON")
	@ResponseBody
	public PageResponse<FavorAbleInfo> selectFavorAbleInfoJSON(PageRequest request) throws Exception {
		/*
		 * Map<String,Object> queryMap=request.getParams();
		 * queryMap.put("priPID", queryMap.g); queryMap.put("efftStatus",
		 * efftStatus); request.setParams(queryMap);
		 */
		request.setLength(999999);
		List<FavorAbleInfo> favorAbleInfoList = favorAbleInfoService.selectFavorAbleInfoBypriPIDAndEfftStatus(request);
		return new PageResponse<FavorAbleInfo>(favorAbleInfoList);
	}

	/**
	 * 
	 * 描述 获取每个tab的总数 @author 赵祥江 @date 2017年3月27日 下午2:59:49 @param @return
	 * AjaxResult @throws
	 */
	@RequestMapping("/getTabTotal")
	@ResponseBody
	public AjaxResult getTabTotal(PageRequest request) throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		return AjaxResult.success("查询成功", data);
	}

	/**
	 * 
	 * 描述 检查企业是否是异常名录和严重违法 @author 赵祥江 @date 2017年3月21日 下午2:41:58 @param @return
	 * String @throws
	 */
	private String checkIsOpanomaly(String entTypeCatg, String priPID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("priPID", priPID);
		String opanomaly = "0";
		String seriousCrime = "0";
		if ("50".equals(entTypeCatg)) {
			List<PubPbopanomalyDto> dataList = pubPbopanomalyService.pubPbopanomalySearchRecoverList(map);
			if (dataList != null && dataList.size() > 0) {
				opanomaly = "1";
			}
		} else {
			List<PubOpaDetailDto> dataList = pubOpaDetailService.selectAddMoveOutSearch(map);
			if (dataList != null && dataList.size() > 0) {
				opanomaly = "1";
			}
		}
		List<ExpSeriousCrimeList> expSeriousCrimeList = expSeriousCrimeListService
				.selectSeriousCrimeInfoByPriPID(priPID);
		if (expSeriousCrimeList != null && expSeriousCrimeList.size() > 0) {
			seriousCrime = "1";
		}
		// 同时被列入异常和严重违法
		if ("1".equals(opanomaly) && "1".equals(seriousCrime)) {
			return "1";
		} else if ("1".equals(opanomaly) && !"1".equals(seriousCrime)) {// 只列入异常
			return "2";
		} else if (!"1".equals(opanomaly) && "1".equals(seriousCrime)) {// 只列入严重违法
			return "3";
		} else {
			return "0";
		}
	}
}
