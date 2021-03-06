package com.warehouse.data.log;

import com.warehouse.data.log.AppBase;
import lombok.Data;

/**
 * @author annyu
 * @description 启动日志
 * @date 2020/5/13
 **/
@Data
public class AppStart extends AppBase {
    /**
     * 入口：push=1，widget=2，icon=3，notification=4, lockscreen_widget =5
     */
    private String entry;

    /**
     * 开屏广告类型: 开屏原生广告=1, 开屏插屏广告=2
     */
    private String openAdType;
    /**
     * 状态：成功=1 失败=2
     */
    private String action;

    /**
     * 加载时长：计算下拉开始到接口返回数据的时间，（开始加载报0，加载成功或加载失败才上报时间）
     */
    private String loadingTime;

    /**
     * 失败码（没有则上报空）
     */
    private String detail;

    /**
     * 失败的 message（没有则上报空）
     */
    private String extend1;

    /**
     * 启动日志类型标记
     */
    private String en;


}
