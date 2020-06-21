package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 广告
 * @date 2020/5/13
 **/
@Data
public class AppAd {
    /**
     * 入口：商品列表页=1	应用首页=2 商品详情页=3
     */
    private String entry;

    /**
     * 动作： 广告展示=1 广告点击=2
     */
    private String action;

    /**
     * Type: 1 商品 2 营销活动
     */
    private String contentType;
    /**
     * 展示时长 毫秒数
     */
    private String displayMills;

    /**
     * 商品 id
     */
    private String itemId;

    /**
     * 营销活动 id
     */
    private String activityId;

}
