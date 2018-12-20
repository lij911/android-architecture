package com.lijing.dev.utils;

import java.util.Collection;

public class NullObjects {

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isEmpty(Object object) {
        if (isNull(object)) {
            return false;
        }

        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }

        if (object instanceof String) {
            return ((String) object).isEmpty();
        }

        return true;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
