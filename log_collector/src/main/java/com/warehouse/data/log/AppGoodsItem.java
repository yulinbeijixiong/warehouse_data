package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 商品详情
 * @date 2020/5/13
 **/
@Data
public class AppGoodsItem {
    /**
     * 页面入口来源：应用首页=1、push=2、详情页相关推荐=3
     */
    private String entry;

    /**
     * 动作：开始加载=1，加载成功=2（pv），加载失败=3, 退出页面=4
     */
    private String action;
    /**
     * 商品 ID（服务端下发的 ID）
     */
    private String goodsId;

    /**
     * 商品样式：0、无图 1、一张大图 2、两张图 3、三张小图 4、一张小图 5、一张大图两张小图来源于详情页相关推荐的商品，上报样式都为 0（因为都是左文右图）
     */
    private String showType;

    /**
     * 商品来源
     */
    private String goodsSource;

    /**
     * 页面停留时长：从商品开始加载时开始计算，到用户关闭页面
     * 所用的时间。若中途用跳转到其它页面了，则暂停计时，待回到详情页时恢复计时。或中途划出的时间超
     * 过 10 分钟，则本次计时作废，不上报本次数据。如未加载成功退出，则报空。
     */
    private String newsStayTime;

    /**
     * 加载时长：计算页面开始加载到接口返回数据的时间 （开始加载报 0，加载成功或加载失败才上报时间）
     */
    private String loadingTime;
    /**
     * 加载失败码：把加载失败状态码报回来（报空为加载成功，没有失败）
     */
    private String type1;

    /**
     * 分类 ID
     */
    private String category;
}
