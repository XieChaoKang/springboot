package com.xck.demo.constant;

/**
 * @author xck
 * @date 2020/5/27 17:22
 * @deprecated redis的一些常量定义
 */
public class RedisConstant {

    /**
     * shiro用户权限前缀
     * */
    public static final String SHIRO_PERMISSIONS_PREFIX = "shiro:permissions::";

    /**
     * shiro用户角色前缀
     * */
    public static final String SHIRO_ROLES_PREFIX = "shiro:roles::";

    /**
     * shiro缓存时间
     * */
    public static final Long SHIRO_EXPIRE = 1000 * 60 * 60 * 2L;

    /**
     * token前缀
     * */
    public static final String TOKEN_PREFIX = "token::";

    /**
     * token过期时间 30分钟
     * */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 课表前缀
     * */
    public static final String COURSE_PREFIX = "course::";

    public static final String TEST_PREFIX = "test::";
}
