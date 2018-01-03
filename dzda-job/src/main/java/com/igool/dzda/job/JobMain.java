package com.igool.dzda.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author H J .
 * @date 2017/12/18.
 */
public class JobMain {

    private static final Logger logger = LoggerFactory.getLogger(JobMain.class);



    public static void main(String[] args) {
        logger.info(" JobMain main-- load ApplicationContext  start");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        logger.info(" JobMain main-- load end  ");
    }

}
