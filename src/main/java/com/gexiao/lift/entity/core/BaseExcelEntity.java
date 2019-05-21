package com.gexiao.lift.entity.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础excel实体
 *
 * @author : lzx
 * created on 2019/4/3
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseExcelEntity extends BaseEntity {

    /**
     * 批次
     */
    private String batch;

}
