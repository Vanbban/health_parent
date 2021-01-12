package com.tencent.health.dao;

import com.github.pagehelper.Page;
import com.tencent.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-11 18:46
 */
public interface SetmealDao {
    void add(Setmeal setmeal);

    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId")Integer checkgroupId);

    Page<Setmeal> findByCondition(String queryString);

    Setmeal findById(int id);

    List<Integer> findCheckgroupIdsBySetmealId(int id);

    void update(Setmeal setmeal);

    void deleteSetmealCheckGroup(Integer id);

    int findOrderCountBySetmealId(int id);

    void deleteById(int id);

    List<String> findImgs();
}
