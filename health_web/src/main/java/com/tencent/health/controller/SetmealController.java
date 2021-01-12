package com.tencent.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tencent.health.constant.MessageConstant;
import com.tencent.health.entity.PageResult;
import com.tencent.health.entity.QueryPageBean;
import com.tencent.health.entity.Result;
import com.tencent.health.pojo.Setmeal;
import com.tencent.health.service.SetmealService;
import com.tencent.health.utils.qiniuyunoss.QiNiuUtils;
import org.apache.ibatis.mapping.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: Vanbban
 * @create 2021-01-11 17:20
 */

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    private static Logger log = LoggerFactory.getLogger(SetmealController.class);

    @Reference
    private SetmealService setmealService;

    /**
     * 套餐上传图片
     * @param imgFile
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //- 获取原有图片名称，截取到后缀名
        String originalFilename = imgFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;
        //- 调用七牛上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //- 返回数据给页面
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            Map<String,String> map = new HashMap<String,String>();
            map.put("imgName",filename);
            map.put("domain", QiNiuUtils.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    /**
     * 新增套餐
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
   @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
       // 调用业务服务添加
       setmealService.add(setmeal,checkgroupIds);
       // 响应结果
       return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
   }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
   @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult<Setmeal>pageResult=setmealService.findPage(queryPageBean);
       return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
   }

    /**
     * 通过id查询套餐信息
     * @param id
     * @return
     */
   @GetMapping("findById")
    public Result findById(int id){
       Setmeal setmeal=setmealService.findById(id);
       Map<String, Object>resultMap = new HashMap<String, Object>();
       resultMap.put("setmeal",setmeal);
       resultMap.put("domain",QiNiuUtils.DOMAIN);
       return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,resultMap);

   }

    /**
     * 通过id查询选中的检查组ids
     * @param id
     * @return
     */
    @GetMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id){
        List<Integer> checkgroupIds=setmealService.findCheckgroupIdsBySetmealId(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroupIds);

    }

    /**
     * 修改
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        // 调用业务服务修改
        setmealService.update(setmeal, checkgroupIds);
        // 响应结果
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    /**
     * 删除套餐
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        setmealService.deleteById(id);
        return new Result(true, "删除套餐成功");
    }
}