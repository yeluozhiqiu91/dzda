package com.igool.id.service.thrift.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.id.service.thrift.IdGenerateService;
import com.noctarius.snowcast.SnowcastEpoch;
import com.noctarius.snowcast.SnowcastSequencer;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by igool on 2017/8/22.
 */
@Service
public class IdGenerateServiceImpl implements IdGenerateService {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerateServiceImpl.class);
    public void close() {
        this.close();
    }

    @Autowired
    private SnowcaseClient snowCaseClient;

    public long getGenerateIdByName(String seqName) {
        // TODO Auto-generated method stub
        SnowcastSequencer snowSeq = snowCaseClient.getSnowcast().createSequencer(seqName, buildEpoch());
        try {
            return snowSeq.next();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            logger.error("生成自增ID出异常",e);
        }
        return Long.valueOf(0);
    }

    private SnowcastEpoch buildEpoch() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2017, 1, 1, 0, 0, 0);
        return SnowcastEpoch.byCalendar(calendar);
    }
}
