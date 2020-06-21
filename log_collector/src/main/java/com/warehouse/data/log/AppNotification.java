package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 消息通知日志
 * @date 2020/5/13
 **/
@Data
public class AppNotification {
    /**
     * 通知 id：预警通知=1，天气预报（早=2，晚=3），常驻=4
     */
    private String type;
    /**
     * 客户端弹出时间
     */
    private String apTime;

    /**
     * 备用字段
     */
    private String content;
    /**
     * 动作
     */
    private String action;

}
