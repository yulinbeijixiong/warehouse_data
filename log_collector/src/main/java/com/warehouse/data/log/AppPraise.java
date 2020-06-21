package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 用户点赞
 * @date 2020/5/13
 **/
@Data
public class AppPraise {
    /**
     * 主键 id
     */
    private int id;

    /**
     * 用户 id
     */
    private int userId;

    /**
     * 点赞的对象 id
     */
    private int targetId;

    /**
     * 点赞类型 1 问答点赞 2 问答评论点赞 3 文章点赞数 4 评论点赞
     */
    private int type;
    /**
     * 添加时间
     */
    private String addTime;
}
