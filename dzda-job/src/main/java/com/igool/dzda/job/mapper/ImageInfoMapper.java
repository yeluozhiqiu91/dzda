package com.igool.dzda.job.mapper;

import com.igool.rpc.db.model.thrift.MinAndMaxId;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author H J .
 * @date 2017/12/18.
 */
@Repository
public interface ImageInfoMapper {

    void add(@Param("countDay")int countDay, @Param("date")Date date);
}
