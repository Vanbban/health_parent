package com.tencent.health.service;


import com.tencent.health.exception.HealthException;
import com.tencent.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author: Vanbban
 * @create 2021-01-12 16:04
 */

public interface OrderSettingService {
    /**
     * 批量导入预约设置
     * @param orderSettingList
     */

    void addBatch(List<OrderSetting> orderSettingList);
    /**
     * 按月查询预约设置信息
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMont(String month);

    void editNumberByDate(OrderSetting orderSetting)throws HealthException;
}
