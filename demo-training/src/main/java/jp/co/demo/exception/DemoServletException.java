package jp.co.demo.exception;

import javax.servlet.ServletException;

/**
 * DemoServletException
 */
public class DemoServletException extends ServletException {

    private static final String PERMISSION_DENIED = "アクセス拒否";

    public DemoServletException(String message) {
        super(message);
    }

    public static DemoServletException permissionDenied() {
        return new DemoServletException(PERMISSION_DENIED);
    }
}
