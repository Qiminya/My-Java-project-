package com.nb.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtils {
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    /**
     * 判断字符串是不是纯数字
     * @param aa
     * @return
     */
    public static boolean isNum(String aa){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher  = pattern.matcher(aa);
        if(!matcher.matches()){
            return false;
        } else{
            return true;
        }
    }
}
