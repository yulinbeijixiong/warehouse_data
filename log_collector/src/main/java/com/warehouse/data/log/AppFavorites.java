package com.warehouse.data.log;

import lombok.Data;

/**
 * @author annyu
 * @description 用户收藏
 * @date 2020/5/13
 **/
@Data
public class AppFavorites {
    /**
     * 主键
     */
    private int id;

    /**
     * 商品 id
     */
    private int courseId;

    /**
     * 用户 ID
     */
    private int userId;
    /**
     * 创建时间
     */
    private String addTime;
}
