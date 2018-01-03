package com.igool.ssp.web.annotation;

import java.lang.annotation.*;

/***
 * 用作菜单权限处理的父节点定义
 * ***/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogAnnotation {
    /**
     * 该控制器的请求类型 操作类型，1 登录 ；2 登出 3 访问主页 等等
     * **/
    public int type();
 
    /***
     * 网页TITLE
     * **/
    public String description() default "首页";
}
