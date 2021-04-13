package club.gclmit.chaos.core.utils;

import club.gclmit.chaos.core.exception.ChaosException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 基于 Hutool 的 String工具类
 * </p>
 *
 * @author gclm
 */
@UtilityClass
public class StringUtils extends StrUtil {

    /**
     * 采用反射获取toString,null会返回“null”
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return NULL;
        }
        List<Field> fieldList = new ArrayList<>();
        Class clazz = obj.getClass();
        String className = clazz.getSimpleName();

        /**
         *  1. 当父类为null的时候说明到达了最上层的父类(Object类).
         *  2. 得到父类,然后赋给自己
         */
        while (clazz != null) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }

        /**
         * 获取 参数属性封装
         */
        StringBuilder fieldBuilder = StringUtils.builder();
        for (Field field : fieldList) {
            field.setAccessible(true);
            try {
                Object value = ObjectUtil.isEmpty(field.get(obj)) ? null : field.get(obj);
                fieldBuilder.append(field.getName()).append("=").append(value).append(", ");
            } catch (IllegalAccessException e) {
                throw new ChaosException("通过反射拼接ToString异常", e);
            }
            field.setAccessible(false);
        }
        String result = StringUtils.subBefore(fieldBuilder, ", ", true);
        return StringUtils.builder(className).append("{").append(result).append("}").toString();
    }

    /**
     * 去除全部空格
     * @author gclm
     * @param str  要处理的字符串
     * @return java.lang.String
     */
    public static String trimAll(String str) {
        return isBlank(str) ? null : str.replaceAll("\\s*", "");
    }
}