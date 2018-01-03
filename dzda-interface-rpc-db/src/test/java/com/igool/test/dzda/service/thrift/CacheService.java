package com.igool.test.dzda.service.thrift;

import java.util.Map;

public interface CacheService {

    public Map<String,String> getBusinessType();

    public Map<String, String> getPlatType();
}
