package com.igool.ssp.web.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: QRCodeUtil
 * @Description:    二维码编码
 * @author Liuy
 * @date 2016年7月9日 下午3:03:24
 *
 */
public class QRCodeUtil {
    public static void encodeBar(String contents, int width, int height, String imgPath) {
        // 条形码的最小宽度
        int codeWidth = 98;
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, codeWidth, height, null);

            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encodeBar(String contents, int width, int height) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        // 条形码的最小宽度
        int codeWidth = 98;
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, codeWidth, height, null);

            MatrixToImageWriter.writeToStream(bitMatrix, "png", bs);
            ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
            BufferedImage img = ImageIO.read(bis);
            return saveToString(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    public static void main(String[] args) {
        encodeBar("420101268619", 110, 40, "/myself/1112.png");
    }
}