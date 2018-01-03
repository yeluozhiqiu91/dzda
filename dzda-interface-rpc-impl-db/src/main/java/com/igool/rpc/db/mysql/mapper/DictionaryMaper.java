package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wang on 2017/11/16.
 */
@Repository
public interface DictionaryMaper {

    List<Dictionary> findAllDictionary();

}
