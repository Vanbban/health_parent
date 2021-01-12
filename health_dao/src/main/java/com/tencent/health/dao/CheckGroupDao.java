package com.tencent.health.dao;

import com.github.pagehelper.Page;
import com.tencent.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-10 18:04
 */
public interface CheckGroupDao {
    /**
     * 添加检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系
     * @param checkGroup
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroup, @Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> findByCondiotion(String queryString);

    CheckGroup findById(int checkGroupId);

    void update(CheckGroup checkGroup);

    void deleteCheckGroupCheckItem(Integer id);


    List<Integer> findCheckItemIdsByCheckGroupId(int checkGroupId);

    int findSetmealCountByCheckGroupId(int id);

    void deleteById(int id);

    List<CheckGroup> findAll();
}
