package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 商品点击
 * @date 2020/5/13
 **/
@Data
public class AppGoodsClick {
    /**
     * 动作：曝光商品=1，点击商品=2，
     */
    private String action;
    /**
     * 商品 ID（服务端下发的 ID）
     */
    private String goodsId;
    /**
     * 顺序（第几条商品，第一条为 0，第二条为 1，如此类推）
     */
    private String place;

    /**
     * 曝光类型：1 - 首次曝光 2-重复曝光（没有使用）
     */
    private String extend1;

    /**
     * 类别
     */
    private String category;
}
