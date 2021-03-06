<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>公示系统信息公告页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/pub.css"/>"/>
</head>
<body class="enterprise-info-bg">
<jsp:include page="../common/noticeheader.jsp"></jsp:include>
<div class="mod">
    <div class="mb20 clearfix mt20">
        <div class="sidebar">
            <ul class="navbar">
                <li><a href="javascript:void(0)" state='01' class="info selected"  id="initSelected"><i class="icon-anomaly"></i>经营异常名录公告</a>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="经营异常名录列入公告" state="1">列入公告</a></li>
                        <li><a href="javascript:void(0)" desc="经营异常名录移出公告" state="2">移出公告</a></li>
                        <li><a href="javascript:void(0)" desc="经营异常名录期满三年提醒公告" state="3">期满三年提醒公告</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" state='02' class="info"><i class="icon-bulletin"></i>严重违法失信企业公告</a>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="严重违法失信企业名单列入公告" state="4">列入公告</a></li>
                        <li><a href="javascript:void(0)" desc="严重违法失信企业名单移出公告" state="5">移出公告</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" state='03' class="info"><i class="icon-spotcheck"></i>抽查检查公告</a>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="抽查公告" state="6">抽查公告</a></li>
                        <li><a href="javascript:void(0)" desc="检查公告" state="7">检查公告</a></li>
                        <li><a href="javascript:void(0)" desc="抽查计划" state="17">抽查计划</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" state='04' class="info"><i class="icon-punishment"></i>行政处罚公告</a>
                </li>
                <li><a href="javascript:void(0)" state='05' class="info"><i class="icon-justice"></i>司法协助公告</a>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="司法股权冻结公告" state="9">司法股权冻结公告</a></li>
                        <li><a href="javascript:void(0)" desc="司法股东变更公告" state="10">司法股东变更公告</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" state='06' class="info"><i class="icon-punishment"></i>简易注销公告</a>
                </li>
                <li><a href="javascript:void(0)" state='07' class="info"><i class="icon-punishment"></i>其他公告</a>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="拟吊销企业营业执照听证公告" state="13">拟吊销营业执照听证公告</a></li>
                    </ul>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="吊销企业营业执照公告" state="14">吊销企业营业执照公告</a></li>
                    </ul>
                    <ul class="subnavbar">
                        <li><a href="javascript:void(0)" desc="企业登记机关注销公告" state="15">企业登记机关注销公告</a></li>
                    </ul>
                </li>
                 <li><a href="javascript:void(0)" state='08' class="info"><i class="icon-punishment"></i>催报公告</a>
                </li>
            </ul>
        </div>
        <div class="notice-cont fl">
            <div class="notice-query border-box mb20">
                <div class="query-title clearfix">
                    <label class="i-title fl"><i></i>公告标题</label>
                    <div class="notice-search clearfix">
                        <div class="ipt-search-wrap fl">
                            <input type="text" placeholder="请输入需要查询信息" id="pubTitle">
                        </div>
                        <a href="javascript:void(0);" class="search-btn fl" id="search_notice_list"></a>
                    </div>
                </div>
                <div class="query-area clearfix">
                    <label class="i-title fl"><i></i>区域筛选</label>
						<div class="notice-area" id="notice-area-id">
							<span><a href="javascript:void(0)" class="selected" regOrgLike="">不限</a></span>
							<span><a href="javascript:void(0)">浙江省</a></span>
							<p>
							<span><a href="javascript:void(0)" regOrgLike="3301">杭州市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3302">宁波市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3303">温州市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3304">嘉兴市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3305">湖州市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3306">绍兴市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3307">金华市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3308">衢州市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3309">舟山市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3310">台州市</a></span>
							<span><a href="javascript:void(0)" regOrgLike="3325">丽水市</a></span>
							</p>
					</div>
                </div>
            </div>
            
            <div id="single-box" class="border-box notice-list-box bulletin-type">
					<input type="hidden" id="regOrgLike" value=""/>
					<h3 id="border-title" class="border-title">
						<i class="icon1"></i>
                        <span></span>
					</h3>
					<ul class="notice-list" id="notice-list-id">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide">
	                        <span class="page-total">共查询到<em id="notice-total"></em>条信息，共<em id="notice_pageNum"></em>页</span>
	                        <div id="" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata"></div>
                    </div>
				</div>
				
			<div class="info-anomaly bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title01" class="border-title">
						<i class="icon1"></i>
                        <span>经营异常名录列入公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id01">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide01">
	                        <span class="page-total">共查询到<em id="notice-total01"></em>条信息，共<em id="notice_pageNum01"></em>页</span>
	                        <div id="pagination01" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata01"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title02" class="border-title">
						<i class="icon1"></i>
                        <span>经营异常名录移出公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id02">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide02">
	                        <span class="page-total">共查询到<em id="notice-total02"></em>条信息，共<em id="notice_pageNum02"></em>页</span>
	                        <div id="pagination02" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata02"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title03" class="border-title">
						<i class="icon1"></i>
                        <span>经营异常名录期满三年提醒公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id03">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide03">
	                        <span class="page-total">共查询到<em id="notice-total03"></em>条信息，共<em id="notice_pageNum03"></em>页</span>
	                        <div id="pagination03" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata03"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-expcre bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title04" class="border-title">
						<i class="icon1"></i>
                        <span>严重违法失信企业名单列入公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id04">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide04">
	                        <span class="page-total">共查询到<em id="notice-total04"></em>条信息，共<em id="notice_pageNum04"></em>页</span>
	                        <div id="pagination04" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata04"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title05" class="border-title">
						<i class="icon1"></i>
                        <span>严重违法失信企业名单移出公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id05">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide05">
	                        <span class="page-total">共查询到<em id="notice-total05"></em>条信息，共<em id="notice_pageNum05"></em>页</span>
	                        <div id="pagination05" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata05"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-checkspot bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title06" class="border-title">
						<i class="icon1"></i>
                        <span>抽查公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id06">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide06">
	                        <span class="page-total">共查询到<em id="notice-total06"></em>条信息，共<em id="notice_pageNum06"></em>页</span>
	                        <div id="pagination06" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata06"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title07" class="border-title">
						<i class="icon1"></i>
                        <span> 检查公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id07">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide07">
	                        <span class="page-total">共查询到<em id="notice-total07"></em>条信息，共<em id="notice_pageNum07"></em>页</span>
	                        <div id="pagination07" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata07"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title17" class="border-title">
						<i class="icon1"></i>
                        <span> 抽查计划</span>
					</h3>
					<ul class="notice-list" id="notice-list-id17">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide17">
	                        <span class="page-total">共查询到<em id="notice-total17"></em>条信息，共<em id="notice_pageNum17"></em>页</span>
	                        <div id="pagination17" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata17"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-punish bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title08" class="border-title">
						<i class="icon1"></i>
                        <span>行政处罚公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id08">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide08">
	                        <span class="page-total">共查询到<em id="notice-total08"></em>条信息，共<em id="notice_pageNum08"></em>页</span>
	                        <div id="pagination08" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata08"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-justice bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title09" class="border-title">
						<i class="icon1"></i>
                        <span>司法股权冻结公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id09">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide09">
	                        <span class="page-total">共查询到<em id="notice-total09"></em>条信息，共<em id="notice_pageNum09"></em>页</span>
	                        <div id="pagination09" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata09"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title10" class="border-title">
						<i class="icon1"></i>
                        <span>司法股东变更公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id10">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide10">
	                        <span class="page-total">共查询到<em id="notice-total10"></em>条信息，共<em id="notice_pageNum10"></em>页</span>
	                        <div id="pagination10" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata10"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-logout bulletin-type" style="display:none">
				<div  class="border-box notice-list-box">
					<h3 id="border-title12" class="border-title">
						<i class="icon1"></i>
                        <span>企业简易注销公告</span>
						<div class="notice12-tips">
							<p>如要公告：请到“企业信息填报”登陆，点击“简易注销公告填报”进行填报！</p>
							<p>如要异议：请点击“首页”，查询您想要异议的企业，进入企业详情页点击“我要异议”按钮提交异议信息！</p>
						</div>
					</h3>
					<ul class="notice-list" id="notice-list-id12">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide12">
	                        <span class="page-total">共查询到<em id="notice-total12"></em>条信息，共<em id="notice_pageNum12"></em>页</span>
	                        <div id="pagination12" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata12"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-otherbull bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title13" class="border-title">
						<i class="icon1"></i>
                        <span>拟吊销企业营业执照听证公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id13">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide13">
	                        <span class="page-total">共查询到<em id="notice-total13"></em>条信息，共<em id="notice_pageNum13"></em>页</span>
	                        <div id="pagination13" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata13"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title14" class="border-title">
						<i class="icon1"></i>
                        <span>吊销企业营业执照公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id14">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide14">
	                        <span class="page-total">共查询到<em id="notice-total14"></em>条信息，共<em id="notice_pageNum14"></em>页</span>
	                        <div id="pagination14" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata14"></div>
                    </div>
				</div>
				<div  class="border-box notice-list-box">
					<h3 id="border-title15" class="border-title">
						<i class="icon1"></i>
                        <span>企业登记机关注销公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id15">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide15">
	                        <span class="page-total">共查询到<em id="notice-total15"></em>条信息，共<em id="notice_pageNum15"></em>页</span>
	                        <div id="pagination15" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata15"></div>
                    </div>
				</div>
			</div>
			
			<div class="info-report bulletin-type" style="display:none">
			  <div  class="border-box notice-list-box">
					<h3 id="border-title16" class="border-title">
						<i class="icon1"></i>
                        <span>催报公告</span>
					</h3>
					<ul class="notice-list" id="notice-list-id16">
					</ul>
					 <div class="notice-pagination-box clearfix">
					    <div id="pageforhide16">
	                        <span class="page-total">共查询到<em id="notice-total16"></em>条信息，共<em id="notice_pageNum16"></em>页</span>
	                        <div id="pagination16" class="pagination">
	
	                        </div>
                        </div>
                        <div id="nodata16"></div>
                    </div>
				</div>
			</div>
				
         </div>
    </div>
</div>
<script id="notice_li_list" type="text/x-handlebars-template">
    {{#each data}}
	<li><a href="javascript:void(0);"  title="{{pubTitle}}" class="fl" linkUID="{{linkUID}}">{{pubTitle}}</a>
        <span class="fl">{{auditDeptName}}</span>
		<span class="time">{{auditDate}}</span>
	</li>
    {{/each}}
	</script>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script src="/js/lib/jquery/jquery-1.12.3.min.js"></script>
<script src="/js/lib/pagination/jquery.pagination.js"></script>
<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/pub/infobulletin/index.js"></script>
</body>
</html>