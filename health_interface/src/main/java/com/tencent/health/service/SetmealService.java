package com.tencent.health.service;

import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.exception.HealthException;
import com.tencent.health.pojo.Setmeal;

import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-11 18:42
 */

public interface SetmealService {
    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询套餐信息
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 通过id查询选中的检查组ids
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(int id);

    /**
     * 修改
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 删除套餐
     * @param id
     */
     void deleteById(int id) throws HealthException;
    /**
     * 查出数据库中的所有图片
     * @return
     */
    List<String> findImgs();
}
