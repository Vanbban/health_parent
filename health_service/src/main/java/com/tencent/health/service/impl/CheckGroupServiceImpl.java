package com.tencent.health.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.sts.model.v20150401.GetCallerIdentityRequest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.dao.CheckGroupDao;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.exception.HealthException;
import com.tencent.health.pojo.CheckGroup;
import com.tencent.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-10 18:02
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId=checkGroup.getId();
        if (null!=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }

    }

    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        // 使用PageHelper.startPage
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 有查询条件的处理, 模糊查询
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        // 紧接着的查询会被分页
        Page<CheckGroup> page=checkGroupDao.findByCondiotion(queryPageBean.getQueryString());
        return new PageResult<CheckGroup>(page.getTotal(),page.getResult());
    }

    /**
     * 通过id获取检查组
     * @param checkGroupId
     * @return
     */
    /**
     * 通过id获取检查组
     * @param checkGroupId
     * @return
     */
    @Override
    public CheckGroup findById(int checkGroupId) {
        return checkGroupDao.findById(checkGroupId);
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 先更新检查组
        checkGroupDao.update(checkGroup);
        // 删除旧关系
        checkGroupDao.deleteCheckGroupCheckItem(checkGroup.getId());
        // 建立新关系
        if (null!=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(), checkitemId);
            }
        }


    }

    /**
     * 通过检查组id查询选中的检查项id
     * @param checkGroupId
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(checkGroupId);
    }

    /**
     * 删除检查组
     * @param id
     */
    @Transactional
    @Override
    public void deleteById(int id) {
        // 检查 这个检查组是否被套餐使用了
        int count=checkGroupDao.findSetmealCountByCheckGroupId(id);
        // 没有被套餐使用,就可以删除数据
        if(count>0){
            // 被使用了
            throw new HealthException(MessageConstant.CHECKGROUP_IN_USE);
        }
        // 先删除检查组与检查项的关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        // 删除检查组
        checkGroupDao.deleteById(id);
    }


}
