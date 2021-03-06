/*
 *  Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
 */
package com.icinfo.cs.system.service.impl;

import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.framework.core.exception.BusinessException;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;
import com.icinfo.framework.mybatis.mapper.entity.Example;
import com.icinfo.framework.tools.utils.StringUtils;
import com.icinfo.cs.system.mapper.SysRolePermisionMapper;
import com.icinfo.cs.system.model.SysRolePermision;
import com.icinfo.cs.system.service.ISysRolePermisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:  角色权限业务实现.<br>
 *
 * @author xiajunwei
 * @date 2016年04月14日
 */
@Service
public class SysRolePermisionServiceImpl extends MyBatisServiceSupport implements ISysRolePermisionService {

    /**
     * 系统角色权限mapper
     */
    @Autowired
    private SysRolePermisionMapper sysRolePermisionMapper;

    /**
     * 查询角色的全部权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysRolePermision> selectAllRolePermision(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return new ArrayList<SysRolePermision>();
        }
        Example example = new Example(SysRolePermision.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        return sysRolePermisionMapper.selectByExample(example);
    }

    /**
     * 添加用户角色
     *
     * @param permision
     * @return
     * @throws Exception
     */
    @Override
    public int insert(SysRolePermision permision) throws Exception {
        return sysRolePermisionMapper.insert(permision);
    }

    /**
     * 添加角色权限
     *
     * @param permisions
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(List<SysRolePermision> permisions) throws Exception {
        for (SysRolePermision permision : permisions) {
            if (sysRolePermisionMapper.insert(permision) <= 0) {
                throw new BusinessException("保存角色权限失败");
            }
        }
        return permisions.size();
    }

    /**
     * 删除角色所有权限
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(String roleId) {
        if (StringUtils.isBlank(roleId)) {
            return 0;
        }
        Example example = new Example(SysRolePermision.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        return sysRolePermisionMapper.deleteByExample(example);
    }


    /**
     * 根据角色id来查询权限
     *
     * @param roleds
     * @return
     * @throws Exception
     * @author ZhuDefeng
     * @date 2017-01-19
     */
    @Override
    public List<SysRolePermision> selectRolePermisionByRoleIds(String roleds) throws Exception {
        if(StringUtil.isBlank(roleds)) return null;
        String[] roleidArr=roleds.split(",");
        List<String> roleIDList=new ArrayList<>();
        for(String roleid:roleidArr){
            roleIDList.add(roleid);
        }
        Example example = new Example(SysRolePermision.class);
        example.createCriteria().andIn("roleId",roleIDList);
        return sysRolePermisionMapper.selectByExample(example);
    }
}
