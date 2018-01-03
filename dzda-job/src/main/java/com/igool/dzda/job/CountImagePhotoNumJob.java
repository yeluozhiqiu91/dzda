package com.igool.dzda.job;

import com.igool.dzda.job.server.CountImagePhotoNumServer;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by igool on 2016/11/21.
 */

public class CountImagePhotoNumJob extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(CountImagePhotoNumJob.class);

    @Autowired
    CountImagePhotoNumServer countImagePhotoNumServer;

    static boolean make=true;

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
            countImagePhotoNumServer.count();
    }

    public CountImagePhotoNumServer getCountImagePhotoNumServer() {
        return countImagePhotoNumServer;
    }

    public void setCountImagePhotoNumServer(CountImagePhotoNumServer countImagePhotoNumServer) {
        this.countImagePhotoNumServer = countImagePhotoNumServer;
    }
}
