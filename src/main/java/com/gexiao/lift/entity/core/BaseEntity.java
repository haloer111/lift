package com.gexiao.lift.entity.core;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 *
 * @author : lzx
 * created on 2018/12/14 10:47
 */
@Data
public class BaseEntity implements IDEntity<Integer>, CreateTimeEntity, UpdateTimeEntity, Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    protected Integer id;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * '最近更新时间'
     */
    protected Date updateTime;


    /**
     * 设置创建以及更新时间（若创建时间存在则不去设置）
     */
    public void setCreateAndUpdateTime() {
        Date now = new Date();
        if (this.getCreateTime() == null) {
            this.setCreateTime(now);
        }
        this.setUpdateTime(now);
    }

}
