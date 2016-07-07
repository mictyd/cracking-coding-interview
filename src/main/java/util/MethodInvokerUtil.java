package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodInvokerUtil {

    public static List<Method> getDeclaredPublicInstanceMethods(Class clazz) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.getDeclaringClass() == clazz)
                .filter(method -> !Modifier.isStatic(method.getModifiers()) && !Modifier.isAbstract(method.getModifiers()))
                .collect(Collectors.toList());
    }

    public static <T> List<T> invokeMethods(Object object, List<Method> methods, Class<T> returnClass, Object[] params) {
        return methods.stream()
                .map(method -> {
                    try {
                        returnClass.get
                        System.out.println(returnClass);
                        return returnClass.cast(method.invoke(object, params));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
//        return new ArrayList<T>();
    }

}
