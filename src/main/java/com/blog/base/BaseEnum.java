package com.blog.base;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2018/12/18
 * @since 1.8
 */
public interface BaseEnum<T extends Enum & BaseEnum>  {
    Integer getId();

    String getName();

    Integer getValue();

    String getCode();

    public static <X extends Enum & BaseEnum> X valueOf(Class<X> clazz, Integer value) {
        X[] values = clazz.getEnumConstants();

        for(int i = 0; i < values.length; ++i) {
            if (((BaseEnum)values[i]).getId().equals(value) || ((BaseEnum)values[i]).getValue().equals(value)) {
                return values[i];
            }
        }

        return null;
    }

    static <X extends Enum & BaseEnum> X fromString(Class<X> clazz, String str) {
        Enum target = null;

        try {
            target = Enum.valueOf(clazz, str);
        } catch (Exception var6) {
            ;
        }

        if (!Objects.nonNull(target)) {
            X[] values = clazz.getEnumConstants();
            if (isNumber(str)) {
                Integer numberValue = Integer.parseInt(str);

                for(int i = 0; i < values.length; ++i) {
                    if (((BaseEnum)values[i]).getCode().equals(str)) {
                        target = values[i];
                        break;
                    }

                    if (((BaseEnum)values[i]).getId().equals(numberValue) || ((BaseEnum)values[i]).getValue().equals(numberValue)) {
                        target = values[i];
                        break;
                    }
                }
            } else {
                for(int i = 0; i < values.length; ++i) {
                    if (((BaseEnum)values[i]).getCode().equals(str)) {
                        target = values[i];
                        break;
                    }
                }
            }
        }

        if (Objects.isNull(target)) {
            throw new IllegalArgumentException("No enum constant " + clazz.getCanonicalName() + "." + str);
        } else {
            return (X) target;
        }
    }

    default boolean in(T... items) {
        if (!Objects.isNull(items) && items.length != 0) {
            for(int i = 0; i < items.length; ++i) {
                if (this.equals(items[i])) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }
    static boolean isNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == '-' ? 1 : 0;
            int i;
            if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while(i < chars.length) {
                        if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for(i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (chars[i] == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] != '+' && chars[i] != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < chars.length) {
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        return true;
                    } else if (chars[i] != 'e' && chars[i] != 'E') {
                        if (chars[i] == '.') {
                            return !hasDecPoint && !hasExp ? foundDigit : false;
                        } else if (allowSigns || chars[i] != 'd' && chars[i] != 'D' && chars[i] != 'f' && chars[i] != 'F') {
                            if (chars[i] != 'l' && chars[i] != 'L') {
                                return false;
                            } else {
                                return foundDigit && !hasExp;
                            }
                        } else {
                            return foundDigit;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }
}
