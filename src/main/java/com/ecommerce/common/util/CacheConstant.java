package com.ecommerce.common.util;

/**
 * Cache related Constants
 * Contains cache names used in the application.
 */
public class CacheConstant {



    private CacheConstant() {
    }

    public static final String USERS = "users";
    public static final String ALL_USERS_KEY = "'allUsers'";
    public static final String ORDERS_BY_USER_KEY = "'user_' + #username";
    public static final String USERNAME_USER_KEY= "'username_' + #username";
    public static final String EMAIL_USER_KEY= "'email_' + #email";
    public static final String PHONE_USER_KEY="'phone_' + #phoneNumber";
    public static final String RESULT_USER_KEY ="'username_' + #result.username";

    public static final String PRODUCTS = "products";
    public static final String ALL_PRODUCTS_KEY= "'allProducts'";


    public static final String ORDERS = "orders";
    public static final String ORDERS_BY_USER = "ordersByUser";
    public static final String ALL_ORDERS_KEY = "'allOrders'";



    // OTP cache TTL
    public static final long OTP_TTL_MINUTES = 3;
    public static final long DEFAULT_TTL = 0L;

    // IP based rate limit TTL
    public static final long OTP_REQUEST_TTL_MINUTES = 5;
    public static final long OTP_ATTEMPT_TTL_MINUTES = 3;
    public static final long IP_BLOCK_TTL_MINUTES = 5;

    // Identity based rate limit TTL
    public static final long OTP_REQUEST_IDENTITY_TTL_MINUTES = 60;
    public static final long VERIFY_IDENTITY_TTL_MINUTES = 3;

    // IP based limits
    public static final int MAX_OTP_REQUEST_PER_IP = 5;
    public static final int MAX_VERIFY_REQUEST_PER_IP = 10;

    // Identity based limits
    public static final int MAX_OTP_REQUEST_PER_IDENTITY = 3;
    public static final int MAX_VERIFY_REQUEST_PER_IDENTITY = 5;

    // OTP Redis key prefixes
    public static final String OTP_PREFIX = "OTP:";

    // IP based Redis key prefixes
    public static final String OTP_IP_COUNT_PREFIX = "OTP_IP_COUNT:";
    public static final String VERIFY_IP_COUNT_PREFIX = "VERIFY_IP_COUNT:";
    public static final String IP_BLOCK_PREFIX = "IP_BLOCK:";

    // Identity based Redis key prefixes
    public static final String OTP_COUNT_PREFIX = "OTP_COUNT:";
    public static final String VERIFY_COUNT_PREFIX = "VERIFY_COUNT:";

    // Redis values
    public static final String BLOCKED_VALUE = "BLOCKED";

    // OTP messages
    public static final String OTP_SENT = "OTP sent successfully";

    public static final String OTP_ALREADY_SENT =
            "OTP already sent. Please use existing OTP or wait for expiry.";

    public static final String OTP_EXPIRED = "OTP expired";

    public static final String IP_BLOCKED = "Your IP is blocked";

    public static final String TOO_MANY_REQUESTS =
            "Too many OTP requests. IP blocked";

    public static final String TOO_MANY_OTP_REQUESTS_FOR_IDENTITY =
            "Too many OTP requests for this email/phone. Please try again later.";

    public static final String TOO_MANY_VERIFY_ATTEMPTS_FOR_IDENTITY =
            "Too many OTP verification attempts for this email/phone. Please request a new OTP.";

    public static final String VERIFICATION_LIMIT_EXCEED =
            "Maximum OTP verification attempts exceeded";

    public static final String OTP_VERIFIED = "OTP verified successfully";

    public static final String INVALID_OTP = "Invalid OTP";
}





