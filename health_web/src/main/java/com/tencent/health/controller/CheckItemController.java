package com.tencent.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.entity.Result;
import com.tencent.health.pojo.CheckItem;
import com.tencent.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author: Vanbban
 * @create 2021-01-07 22:18
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    /**
     * 查询所有的检查项
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(){
        // 调用服务查询
        List<CheckItem> list = checkItemService.findAll();
        // 封装到Result再返回
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    /**
     *添加检查项
     * @param checkItem
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        checkItemService.add(checkItem);

        return  new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     *分页条件查询
     * @param queryPageBean
     * @return
     */
   @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       // 调用业务来分页，获取分页结果
       PageResult<CheckItem> pageResult= checkItemService.findPage(queryPageBean);

       // 返回给页面, 包装到Result, 统一风格
       return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);

   }

    /**
     * 删除检查项
     * @param id
     * @return
     */
   @GetMapping("/deleteById")
    public  Result deleteById(int id){
       checkItemService.deleteById(id);
       return new  Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
   }


    /**
     * 通过Id查询检查项
     * @param id
     * @return
     */
   @GetMapping ("/findById")
    public Result findById(int id){
       CheckItem checkItem=checkItemService.findById(id);
       return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
   }

    /**
     * 编辑检查项
     * @param checkItem
     * @return
     */
   @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
       checkItemService.update(checkItem);
       return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
   }
}
