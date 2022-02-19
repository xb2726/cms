package com.serendipity.extra.query;

import com.serendipity.core.exception.BusinessException;

/**
 * @Auther bin
 */
public class Assert {
    public static void isNull(Object o, String errorMsg) {
        if (o != null) {
            throw new BusinessException(errorMsg);
        }
    }

    public static void isNull(Object o, String errorMsg, Object... args) {
        if (args != null && args.length > 0) {
            isNull(o, String.format(errorMsg, args));
            return;
        }
        isNull(o, errorMsg);
    }

    public static void isNotNull(Object o, String errorMsg) {
        if (o == null) {
            throw new BusinessException(errorMsg);
        }
    }

    public static void isNotNull(Object o, String errorMsg, Object... args) {
        if (args != null && args.length > 0) {
            isNotNull(o, String.format(errorMsg, args));
            return;
        }
        isNotNull(o, errorMsg);
    }

    public static void isTrue(Boolean o, String errorMsg) {
        if (o == null || !o) {
            throw new BusinessException(errorMsg);
        }
    }

    public static void isTrue(Boolean o, String errorMsg, Object... args) {
        if (args != null && args.length > 0) {
            isTrue(o, String.format(errorMsg, args));
            return;
        }
        isTrue(o, errorMsg);
    }

    public static void isFalse(Boolean o, String errorMsg) {
        if (o != null && o) {
            throw new BusinessException(errorMsg);
        }
    }

    public static void isFalse(Boolean o, String errorMsg, Object... args) {
        if (args != null && args.length > 0) {
            isFalse(o, String.format(errorMsg, args));
            return;
        }
        isFalse(o, errorMsg);
    }

    public static void hasText(String text, String errorMsg) {
        if (text == null || text.trim().equals("")) {
            throw new BusinessException(errorMsg);
        }
    }

    public static void hasText(String text, String errorMsg, Object... args) {
        if (args != null && args.length > 0) {
            hasText(text, String.format(errorMsg, args));
            return;
        }
        hasText(text, errorMsg);
    }
}
