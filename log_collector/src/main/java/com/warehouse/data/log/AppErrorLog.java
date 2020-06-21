package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 错误日志
 * @date 2020/5/13
 **/
@Data
public class AppErrorLog extends AppBase{
    /**
     * 错误摘要
     */
    private String errorBrief;
    /**
     * 错误详情
     */
    private String errorDetail;
}
