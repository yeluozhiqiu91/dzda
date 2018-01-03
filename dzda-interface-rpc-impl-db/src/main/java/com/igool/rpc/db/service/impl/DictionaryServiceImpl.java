package com.igool.rpc.db.service.impl;

import com.igool.rpc.db.model.thrift.Dictionary;
import com.igool.rpc.db.mysql.mapper.DictionaryMaper;
import com.igool.rpc.db.service.thrift.DictionaryService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/11/16.
 */
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryMaper dictionaryMaper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<Dictionary> findAllDictionary() throws TException {
        List<Dictionary> dictionaryList=dictionaryMaper.findAllDictionary();
        if(dictionaryList!=null&&dictionaryList.size()>0){
            return dictionaryList;
        }else{
            return new ArrayList<>();
        }
    }
}
