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
                        Class<T> ff = getBoxedClass(returnClass);
                        return ff.cast(method.invoke(object, params));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private static Class getBoxedClass(Class clazz) {
        char a = 1;
        switch (clazz.toString()) {
            case "int": return Integer.class;
            case "long": return Integer.class;
            case "float": return Float.class;
            case "double": return Double.class;
            case "boolean": return Boolean.class;
            case "void": return Void.class;
            case "char": return Character.class;
            default: return clazz;
        }
    }
}
