package org.wyc.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 */
public class ReflectionUtils {
    /**
     * 根据class创建对象
     * @param clazz 待创建对象的类
     * @return
     * @param <T>
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取某个类的公有方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMenthods(Class clazz) {
        //取到所有方法
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<Method>();
        //遍历找到所有公有的方法
        for (Method method : methods) {
            if(Modifier.isPublic(method.getModifiers())) {
                methodList.add(method);
            }
        }
        //new Method[0] 的作用是告诉 toArray() 方法返回一个 Method[] 类型的数组
        return methodList.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
