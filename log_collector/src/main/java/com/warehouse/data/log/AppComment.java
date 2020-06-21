package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 用户评论
 * @date 2020/5/13
 **/
@Data
public class AppComment {
    /**
     * 评论表
     */
    private int commentId;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 父级评论 id(为 0 则是一级评论,不为 0 则是回复)
     */
    private	int pCommentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String addTime;

    /**
     * 评论的相关 id
     */
    private int otherId;

    /**
     * 点赞数量
     */
    private int praiseCount;
    /**
     * 回复数量
     */
    private int replyCount;
}
