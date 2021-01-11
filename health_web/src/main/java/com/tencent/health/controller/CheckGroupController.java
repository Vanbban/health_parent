package com.tencent.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.entity.Result;
import com.tencent.health.pojo.CheckGroup;
import com.tencent.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-10 17:52
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

    }

    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup>pageResult=checkGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS, pageResult);
    }

    /**
     * 通过id获取检查组
     * @param checkGroupId
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int checkGroupId){
        CheckGroup checkGroup=checkGroupService.findById(checkGroupId);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);

    }

    /**
     * 通过检查组id查询选中的检查项id
     * @param checkGroupId
     * @return
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int checkGroupId){
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(checkGroupId);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
   @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.update(checkGroup,checkitemIds);
       return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
   }

    /**
     * 删除检查组
     * @param id
     * @return
     */
   @PostMapping("/deleteById")
   public Result deleteById(int id){
       checkGroupService.deleteById(id);
       return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
   }
}
