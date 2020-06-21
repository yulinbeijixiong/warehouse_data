package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 日志基础信息
 * @date 2020/5/13
 **/
@Data
public class AppBase {
    /**
     * (String) 设备唯一标识
     */
    private String mid;

    /**
     * 用户id
     */
    private String uid;
    /**
     * 程序版本号
     */
    private String vc;
    /**
     * 程序版本名
     */
    private String vn;
    /**
     * 系统语言
     */
    private String l;
    /**
     * 渠道号
     */
    private String sr;
    /**
     * Android 系统版本
     */
    private String os;
    /**
     * 区域
     */
    private String ar;
    /**
     * 手机型号
     */
    private String md;
    /**
     * 手机品牌
     */
    private String ba;
    /**
     * sdkVersion
     */
    private String sv;
    /**
     * gmail 邮箱
     */
    private String g;
    /**
     * heightXwidth，屏幕宽高
     */
    private String hw;
    /**
     * 客户端日志产生时的时间
     */
    private String t;
    /**
     * 网络模式
     */
    private String nw;
    /**
     * 经度
     */
    private String ln;
    /**
     * 纬度
     */
    private String la;
}
