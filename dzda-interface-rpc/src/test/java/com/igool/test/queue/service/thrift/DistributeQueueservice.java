package com.igool.test.queue.service.thrift;

import java.util.List;

/**
 * Created by igool on 2017/7/26.
 */
public interface DistributeQueueservice {
    void addQueueStr(String key, String jsonStr);
    List<String> getQueueListStr(String key);
}
