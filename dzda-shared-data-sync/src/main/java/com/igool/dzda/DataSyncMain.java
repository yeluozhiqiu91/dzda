package com.igool.dzda;

import com.igool.dzda.server.ImagePhotoRelPastService;
import com.igool.dzda.server.impl.ImagePhotoRelPastServiceImpl;
import com.igool.id.service.thrift.IdGenerateService;
import com.igool.rpc.db.model.thrift.ImagePhotoRel;
import com.igool.rpc.db.service.thrift.ImagePhotoRefService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author H J .
 * @date 2017/8/30.
 */

public class DataSyncMain {

    private static final Logger logger = LoggerFactory.getLogger(DataSyncMain.class);

    private static final int indexEnd = 10000;


    public static void main(String[] args) {
        logger.info(" DataSyncMain -- load ApplicationContext  ");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        logger.info(" DataSyncMain -- main start  ");
        logger.info(" DataSyncMain -- for  ");
        int indexStart = 0;
        int db = 0;
        while (true) {
            try {
                syncData(db, context, indexStart);
                break;
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex.getMessage());
                logger.error(" DataSyncMain -- for db  【" + db + "】  Exception");
            }
        }
        logger.info(" DataSyncMain -- main end  ");
    }

    private static void syncData(int db, ApplicationContext context, int indexStart) throws Exception {

//        ThriftClientProxy thriftClientProxy = context.getBean(ThriftClientProxy.class);
        ImagePhotoRefService imagePhotoRefService = context.getBean(ImagePhotoRefService.class);
//        IdGenerateService idGenerateService = context.getBean(IdGenerateService.class);
        ImagePhotoRelPastService imagePhotoRelPastService = context.getBean(ImagePhotoRelPastServiceImpl.class);

        logger.info(" DataSyncMain -- for db 【" + db + "】");
        boolean mark = true;
        boolean whileis = true;
        int k = 0;
        List<ImagePhotoRel> imagePhotoRels = null;
        while (whileis) {
            logger.info(" DataSyncMain -- for indexStart 【" + indexStart + "】");
            if(mark){
                try {
                    imagePhotoRels = getImagePhotoRelByDb(imagePhotoRelPastService, db + "", indexStart * indexEnd, indexEnd);
                }catch (Exception ex){
                    ex.printStackTrace();
                    logger.error("=================================================================================================");
                    logger.error(ex.getMessage());
                    logger.error("=================================================================================================");
                    continue;
                }
            }

            try {
          NEXT: for (; k < imagePhotoRels.size(); k++) {
                    boolean b = filterImagePhotoRel(imagePhotoRels, imagePhotoRefService);
                    if (b) {
                        break;
                    }
                    ImagePhotoRel imagePhotoRel1 = imagePhotoRels.get(k);
                    List<ImagePhotoRel> imagePhotoRelList1 = imagePhotoRefService.getImagePhotoRelList(imagePhotoRel1.getImageId());
                    for (int j = 0; j < imagePhotoRelList1.size(); j++) {
                        if (imagePhotoRelList1.get(j).getPhoto().equals(imagePhotoRel1.getPhoto())) {
                            continue NEXT;
                        }
                    }
                    addImagePhotoRel(imagePhotoRefService, imagePhotoRel1);
                }
                indexStart++;
                if (imagePhotoRels.size() < indexEnd){
                    whileis = !whileis;
                }
                mark = true;
                k = 0;
            } catch (Exception e) {
                e.printStackTrace();
                k--;
                mark = false;
                logger.error("---------------------------------------------------------------------------------------------------");
                logger.error(e.getMessage());
                logger.error(" DataSyncMain db-- " + db + " -- for  addImagePhotoRel     Exception imagePhotoRels -- " + imagePhotoRels.get(k).toString());
                logger.error("---------------------------------------------------------------------------------------------------");
            }
        }
    }

    private static List<ImagePhotoRel> getImagePhotoRelByDb(ImagePhotoRelPastService imagePhotoRelPastService, String db, int indexStart, int indexEnd) throws Exception {
        return imagePhotoRelPastService.getImagePhotoRel(db, indexStart, indexEnd);
    }


    private static void addImagePhotoRel(ImagePhotoRefService imagePhotoRefService, ImagePhotoRel imagePhotoRel) throws Exception {
        imagePhotoRefService.insert(imagePhotoRel);
    }


    private static boolean filterImagePhotoRel(List<ImagePhotoRel> imagePhotoRels, ImagePhotoRefService imagePhotoRefService) throws Exception {

        ImagePhotoRel imagePhotoRel = imagePhotoRels.get(imagePhotoRels.size() - 1);

        List<ImagePhotoRel> imagePhotoRelList = imagePhotoRefService.getImagePhotoRelList(imagePhotoRel.getImageId());
        if (imagePhotoRelList.size() > 0) {
            for (int j = 0; j < imagePhotoRelList.size(); j++) {
                if (imagePhotoRelList.get(j).getPhoto().equals(imagePhotoRel.getPhoto())) {
                    return true;
                }
            }
        }
        return false;
    }
}
