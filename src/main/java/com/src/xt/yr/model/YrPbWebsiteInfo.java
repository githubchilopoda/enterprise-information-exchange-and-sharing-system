/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.cs.yr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icinfo.framework.mybatis.mapper.annotation.Before;

/**
 * 描述:    cs_yr_pb_websiteinfo 对应的实体类.（个体户网站信息表）<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年09月10日
 */
@Table(name = "cs_yr_pb_websiteinfo")
public class YrPbWebsiteInfo implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 网站（网店）网址
     */
    @Column(name = "WebSite")
    private String webSite;

    /**
     * 网站网店类型
     */
    @Column(name = "WebType")
    private String webType;

    /**
     * 时间戳
     */
    @Column(name = "CreateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")  
    private Date createTime;

    /**
     * 年报ID
     */
    @Column(name = "AnCheID")
    private String anCheID;

    /**
     * 网站标识
     */
    @Column(name = "WEBID")
    @Before
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="select replace(uuid(), '-', '')")
    private String webID;

    /**
     * 网站（网店）名称
     */
    @Column(name = "WebSitName")
    private String webSitName;
    
    /**
     * 网络交易平台运营类型:(第三方网络交易类平台-1;自营网络交易类平台-2;二者皆有-3)
     */
    @Column(name = "WebPlatFormType")
    private String webPlatFormType;
    
    /**
     * 网络交易产品类型:(商品交易类平台-1;服务交易类平台-2;二者皆有-3)
     */
    @Column(name = "WebGoodsType")
    private String webGoodsType;
    
    /**
     * 电商类型:(B2B=1、B2C=2、C2C=3、团购=4、其他=5)
     */
    @Column(name = "ECommerceType")
    private String eCommerceType;
    
    /**
     * ICP证号/备案号
     */
    @Column(name = "IcpBackNO")
    private String icpBackNO;

    /**
     * 微信公众服务号
     */
    @Column(name = "WeChatPublicNO")
    private String weChatPublicNO;

    /**
     * APP软件名称
     */
    @Column(name = "AppNames")
    private String appNames;
    
    /**
     * 所属平台名称:如天猫、淘宝、京东、1688、蘑菇街、贝贝网、义乌购、海宁皮城等）
     */
    @Column(name = "BelongPlatFormName")
    private String belongPlatFormName;
    
    /**
     * 发货地址
     */
    @Column(name = "SendAddress")
    private String sendAddress;
    
    /**
     * 退货地址
     */
    @Column(name = "BackAddress")
    private String backAddress;

    /**
     * 网站类型：网络交易平台，企业信息管网
     */
    @Column(name = "WebSmallType")
    private String webSmallType;
    
	//状态标志 （添加，修改，删除）
    @Transient
	private String status;  //值只能为  1 insert,2 delete,3 update

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取网站（网店）网址
     *
     * @return WebSite - 网站（网店）网址
     */
    public String getWebSite() {
        return webSite;
    }

    public void setWebID(String webID) {
		this.webID = webID;
	}
    
    public String getWebID() {
		return webID;
	}
    /**
     * 设置网站（网店）网址
     *
     * @param webSite 网站（网店）网址
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    /**
     * 获取网站网店类型
     *
     * @return WebType - 网站网店类型
     */
    public String getWebType() {
        return webType;
    }

    /**
     * 设置网站网店类型
     *
     * @param webType 网站网店类型
     */
    public void setWebType(String webType) {
        this.webType = webType;
    }

    /**
     * 获取时间戳
     *
     * @return CreateTime - 时间戳
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间戳
     *
     * @param createTime 时间戳
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取年报ID
     *
     * @return AnCheID - 年报ID
     */
    public String getAnCheID() {
        return anCheID;
    }

    /**
     * 设置年报ID
     *
     * @param anCheID 年报ID
     */
    public void setAnCheID(String anCheID) {
        this.anCheID = anCheID;
    }



    /**
     * 获取网站（网店）名称
     *
     * @return WebSitName - 网站（网店）名称
     */
    public String getWebSitName() {
        return webSitName;
    }

    /**
     * 设置网站（网店）名称
     *
     * @param webSitName 网站（网店）名称
     */
    public void setWebSitName(String webSitName) {
        this.webSitName = webSitName;
    }

	public String getWebPlatFormType() {
		return webPlatFormType;
	}

	public void setWebPlatFormType(String webPlatFormType) {
		this.webPlatFormType = webPlatFormType;
	}

	public String getWebGoodsType() {
		return webGoodsType;
	}

	public void setWebGoodsType(String webGoodsType) {
		this.webGoodsType = webGoodsType;
	}

	public String geteCommerceType() {
		return eCommerceType;
	}

	public void seteCommerceType(String eCommerceType) {
		this.eCommerceType = eCommerceType;
	}

	public String getIcpBackNO() {
		return icpBackNO;
	}

	public void setIcpBackNO(String icpBackNO) {
		this.icpBackNO = icpBackNO;
	}

	public String getWeChatPublicNO() {
		return weChatPublicNO;
	}

	public void setWeChatPublicNO(String weChatPublicNO) {
		this.weChatPublicNO = weChatPublicNO;
	}

	public String getAppNames() {
		return appNames;
	}

	public void setAppNames(String appNames) {
		this.appNames = appNames;
	}

	public String getBelongPlatFormName() {
		return belongPlatFormName;
	}

	public void setBelongPlatFormName(String belongPlatFormName) {
		this.belongPlatFormName = belongPlatFormName;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getBackAddress() {
		return backAddress;
	}

	public void setBackAddress(String backAddress) {
		this.backAddress = backAddress;
	}

	public String getWebSmallType() {
		return webSmallType;
	}

	public void setWebSmallType(String webSmallType) {
		this.webSmallType = webSmallType;
	}
    
    
}