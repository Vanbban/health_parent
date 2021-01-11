package com.tencent.health.dao;

import com.github.pagehelper.Page;
import com.tencent.health.pojo.CheckItem;

import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-07 22:30
 */
public interface CheckItemDao {


    /**
     * 查询 所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页条件查询
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 查询检查项的id
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteById(int id);


    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 编辑检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);


}
