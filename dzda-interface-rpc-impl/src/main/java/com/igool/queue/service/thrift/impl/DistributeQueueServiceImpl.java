package com.igool.queue.service.thrift.impl;

import com.facebook.swift.codec.ThriftField;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import com.igool.queue.service.thrift.DistributeQueueservice;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by igool on 2017/7/27.
 */
@Service
public class DistributeQueueServiceImpl implements DistributeQueueservice {
    private static final Logger log = LoggerFactory.getLogger(DistributeQueueServiceImpl.class);

    @Autowired
    private HazelcastInstance instance;
    private @Value("#{config['min.process']}") int min;
    private @Value("#{config['common.queue.size']}") int queueSize;

    public void close() {
        this.close();
    }

    public void addQueueStr(@ThriftField(value = 1, name = "key", requiredness = ThriftField.Requiredness.NONE) String key, @ThriftField(value = 2, name = "jsonStr", requiredness = ThriftField.Requiredness.NONE) String jsonStr) throws TException {
        try {
            IQueue<Object> queues = instance.getQueue("queue");
            instance.getQueue(key).put(jsonStr);
            log.info("存入一般队列地址{}成功,长度{}", jsonStr, queues.size());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("放入队列异常", e);
        }
    }

    public List<String> getQueueListStr(@ThriftField(value = 1, name = "key", requiredness = ThriftField.Requiredness.NONE) String key) throws TException {
        try {
            List<String> entryList = new ArrayList<String>();
            IQueue<String> data = instance.getQueue(key);
            log.info("偿试取出队列,当前队列长度{}", data.size());
            // 当队列为空不处理
            if (data == null || data.isEmpty() /* || data.size() < queueSize */) {
                return Collections.emptyList();
            }
            // 只要有值，就取出所有的值
            for (int i = 0; i < data.size(); i++) {
                entryList.add(data.take());
            }
            log.info("最终取出数据为{},长度{}", entryList, entryList.size());
            return entryList;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("取队列异常", e);
        }
        return Collections.EMPTY_LIST;
    }
}
