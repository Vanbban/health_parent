package com.tencent.health.dao;

import com.tencent.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Vanbban
 * @create 2021-01-12 16:15
 */
public interface OrderSettingDao {
    OrderSetting findByOrderDate(Date orderDate);

    void updateNumber(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);


    List<Map<String, Integer>> getOrderSettingByMonth(String month);

}
