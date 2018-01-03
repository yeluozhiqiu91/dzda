package com.igool.test.id.service.thrift;

/**
 * Created by igool on 2017/8/22.
 */
/**
 * 类描述:全局蜼 一性ID生成.
 **/
public interface IdGenerateService {

    /**
     * @param string name 自增ID的名称定义，用来区分其它ID
     * @return 64位无符号数值
     * @throws InterruptedException 出异常，直接返回0
     * 参考https://github.com/twitter/snowflake的实现
     * **/
    public Long getGenerateIdByName(String name);
}

