package cracking.chapter1;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class StringArraysSolution {

    public boolean hasOnlyUniqueCharacters_streams(String e) {
        if (e == null) throw new IllegalArgumentException("Null is not allowed");

        Map<Character, Integer> counts = new HashMap<>(e.length());

        return e.chars().distinct().count() == e.length();
    }


    public static List<Method> getAllPublicMethods() {
        return Arrays.stream(StringArraysSolution.class.getMethods())
                .filter(Method::isAccessible)
                .collect(toList());
    }

}
