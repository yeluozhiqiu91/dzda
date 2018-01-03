package com.igool.dzda.job.server.impl;

import com.igool.dzda.job.JobMain;
import com.igool.dzda.job.mapper.ImageInfoMapper;
import com.igool.dzda.job.server.CountImagePhotoNumServer;
import com.igool.rpc.db.model.thrift.MinAndMaxId;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author H J .
 * @date 2017/12/18.
 */
@Service("countImagePhotoNumServer")
public class CountImagePhotoNumServerImpl implements CountImagePhotoNumServer {
    private static final Logger logger = LoggerFactory.getLogger(CountImagePhotoNumServerImpl.class);

    @Autowired
    ImageInfoMapper imageInfoMapper;
    @Autowired
    ImagePhotoRefService imagePhotoRefService;

    @Override
    public void count() {
        logger.info(" CountImagePhotoNumServerImpl ----  count  start ");
        try {
            int countDay = imagePhotoRefService.countImagePhoto();
            logger.info(" CountImagePhotoNumServerImpl ---- 当天统计数量为 ：{}",countDay );
            imageInfoMapper.add(countDay,new Date());
            logger.info(" CountImagePhotoNumServerImpl ---- 已添加到统计表 " );
        } catch (TException e) {
            e.printStackTrace();
            logger.error(" CountImagePhotoNumServerImpl ---- 出现异常  TException  " +e.getMessage());
            count();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(" CountImagePhotoNumServerImpl ---- 出现异常  Exception  " +e.getMessage());
            count();
        }

        logger.info(" CountImagePhotoNumServerImpl ----  count  end ");
    }
}
