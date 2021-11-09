package com.no1.cz.util;

import java.util.UUID;

public class StringUtil {

    // 设置页码，默认为3
    public static final int PAGE_SIZE = 4;

    /**
     * 获取到fileName的后缀名称并返回
     * @param fileName
     * @return
     */
    public static String getSuffixName(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成32位随机字符序列
     * @return
     */
    public static String getRandomFileName(){
        return UUID.randomUUID().toString().replace("-", "");
    }



}
