package com.igool.ssp.web.annotation;

import java.lang.annotation.*;

/***
 * 用作菜单权限处理的父节点定义
 * ***/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AclAnnotation {
    /**
     * 该控制器的父请求地址
     * **/
    public String pUrl();
 
    /***
     * 描述
     * **/
    public String description() default "请自定义该请求的描述信息";
}
