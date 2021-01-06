package com.blog.enums;


import com.blog.base.BaseEnum;
import com.blog.convert.EnumConvert;

import javax.persistence.Convert;

/**
 * 文章枚举
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2018/12/18
 * @since 1.8
 */
public enum FileEnum implements BaseEnum<FileEnum> {

    /**
     * 图片
     */
    PICTURE(0,1,"PIC","图片"),
    /**
     * 文本
     */
    TEXT(0,2,"text","文本");

    private Integer id;
    private Integer value;
    private String code;
    private String name;

    FileEnum(Integer id, Integer value, String code, String name) {
        this.id = id;
        this.value = value;
        this.code = code;
        this.name = name;
    }
    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getCode() {
        return this.code;
    }


    public static FileEnum valueOf(Integer value) {
        return BaseEnum.valueOf(FileEnum.class, value);
    }

    public static FileEnum fromString(String str) {
        return BaseEnum.fromString(FileEnum.class, str);
    }


    @Convert
    public static class AsCode extends EnumConvert<FileEnum> {
        public AsCode() {
            super(FileEnum.class);
        }
    }
}
