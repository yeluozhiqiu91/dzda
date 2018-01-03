package com.igool.test;

import com.igool.file.service.thrift.FileService;
import com.igool.file.service.thrift.TestService;
import com.igool.thrift.rpc.ThriftClientProxy;
import com.igool.video.service.thrift.VideoService;
import org.apache.thrift.TException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by igool on 2017/7/26.
 */

@ContextConfiguration(locations={"classpath*:spring-context-thrift-client.xml"})
public class ServiceTest extends AbstractTestNGSpringContextTests {
    private static final Logger logger = LoggerFactory.getLogger(ServiceTest.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private TestService testService;

    @Autowired
    private ThriftClientProxy thriftClientProxy;

    @Autowired
    private VideoService videoService;
    @Test
    public void fileTest() throws TException {
        String text = "abc祖雄";
        logger.info("file path {}",fileService.storeFile("test",text.getBytes(),true));
        logger.info("db file path {}",testService.storeFile("test",text.getBytes(),true));
        logger.info("client file {}", ((TestService)thriftClientProxy.getService(TestService.class)).storeFile("test",text.getBytes(),true));
    }

    @Test
    public void testVideo() throws TException {
        String path = "C:\\code\\imgscalr-master\\imgscalr-master\\src\\test\\resources\\video.mp4";
        path = "http://219.140.62.214:8088/ssposs/servlet/offer/video?videoPath=/home/yitop/csssposs/videos/2017/20170811/35419703999187/20170811152236926_1_copy.mp4";
        logger.info(" pic path {}",videoService.getVideoPicByEachSec(path,true));
    }

    @Test
    public void testPicWater() throws TException {
        String path = "http://139.224.42.154:9081/13/076355287610/pic1";
        String water = "/root/ffmpeg/water.png";
        logger.info(" pic water path {}",videoService.getWaterMarkImage(path, water, 2));
    }
}
