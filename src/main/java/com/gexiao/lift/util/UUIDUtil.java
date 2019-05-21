package com.gexiao.lift.util;

import java.util.UUID;

/**
 * 生成UUID工具类
 *
 * @author : lzx
 * created on 2018/12/19
 */
public class UUIDUtil {

    /**
     * 生成去掉 "-" 的UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
