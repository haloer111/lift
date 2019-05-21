package com.gexiao.lift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gexiao.lift.entity.core.BaseEntity;
import lombok.Data;

/**
 * @Auther: gexiao
 * @Date: 2019/5/21 11:32
 * @Description:
 */
@TableName(value = "web_product")
@Data
public class WebProduct extends BaseEntity {

    /**
     * 类目id
     */
    private Long categoryId;
    /**
     * 媒体文件相对路径
     */
    private String relativePath;
    /**
     * 媒体类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 详情
     */
    private String detail;
    /**
     * 跳转类目id
     */
    private Long targetCategoryId;
    /**
     * 下载附件
     */
    private String accessory;
    /**
     * 排序
     */
    private Integer sort;


}
