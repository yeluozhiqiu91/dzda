package com.igool.ssp.web.util;

import org.apache.commons.codec.binary.Base64;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/1/22 0022.
 */
public class BarcodeUtil {
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String GIF = "gif";
    public static final Double height20=20.0;
    public static final Double height15=15.0;
    public static final Double height10=10.0;
    public static String createBarcode(String code, double height){
        JBarcode jbcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
        jbcode.setEncoder(Code128Encoder.getInstance());
        jbcode.setPainter(WidthCodedPainter.getInstance());
        jbcode.setTextPainter(BaseLineTextPainter.getInstance());
        jbcode.setShowCheckDigit(false);
        jbcode.setBarHeight(height);

        try{
            jbcode.setXDimension(0.45);
            BufferedImage img = jbcode.createBarcode(code);
//            saveToFile(img,code+"."+PNG,PNG);
            return saveToString(img);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    static void saveToJPEG(BufferedImage img, String fileName){
        saveToFile(img, fileName, ImageUtil.JPEG);
    }

    static void saveToPNG(BufferedImage img, String fileName){
        saveToFile(img, fileName, ImageUtil.PNG);
    }

    static void saveToGIF(BufferedImage img, String fileName){
        saveToFile(img, fileName, ImageUtil.GIF);
    }

    static void saveToFile(BufferedImage img, String fileName, String format){
        try{
            FileOutputStream fos = new FileOutputStream("e://"+fileName);
            ImageUtil.encodeAndWrite(img, format, fos, 96, 96);
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String saveToString(BufferedImage img){
        img.flush();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(img, "png",imOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bs.toByteArray());
    }
}
