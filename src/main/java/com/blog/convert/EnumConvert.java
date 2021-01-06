package com.blog.convert;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import com.blog.base.BaseEnum;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2018/12/18
 * @since 1.8
 */
public class EnumConvert<T extends BaseEnum> implements AttributeConverter<T,Integer> {

    private Method method;

    public EnumConvert(Class<T> tClass){
        try {
            this.method = tClass.getDeclaredMethod("valueOf",Integer.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer convertToDatabaseColumn(T t) {
        return t.getValue();
    }

    @Override
    public T convertToEntityAttribute(Integer integer) {
        try {
            return ObjectUtils.isEmpty(integer)?null: (T) this.method.invoke(null,integer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
