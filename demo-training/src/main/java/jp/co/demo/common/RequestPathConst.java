package jp.co.demo.common;

/**
 * RequestPathConst
 */
public class RequestPathConst {

    // Home path
    public static final String HOME = "/";
    // Screen M path
    public static final String M = "/M";
    // Error page
    public static final String PAGE_ERROR = "/error";

    // ------------------------------------------------------------------------
    // LIST PATH MANAGE
    // ------------------------------------------------------------------------

    // M01
    public static final String M01 = "/M/M01";
    public static final String M01_LOGIN = "/M/M01/login";
    public static final String M01_LOGOUT = "/M/M01/logout";

    // M02
    public static final String M02 = "/M/M02";
    public static final String M02_1 = "/M/M02/M02_1";
    public static final String M02_2 = "/M/M02/M02_2";

    // M04
    public static final String M04 = "/M/M04";

    // ------------------------------------------------------------------------
    // LIST ERROR
    // ------------------------------------------------------------------------
    public static final String PAGE_FORBIDDEN = "forward:/view/403.html";
    public static final String PAGE_NOT_FOUND = "forward:/view/404.html";
    public static final String ALL_ERROR = "forward:/view/500.html";
}
