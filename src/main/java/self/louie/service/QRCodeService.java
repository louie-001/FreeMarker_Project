package self.louie.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

/**
 * Created by louie on 2017-11-13.
 */
public class QRCodeService {
    private Hashtable<EncodeHintType,Object> hintTypes;

    public QRCodeService(){
        hintTypes = new Hashtable<EncodeHintType, Object>();
        hintTypes.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);//设置编码
        hintTypes.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错级别
        hintTypes.put(EncodeHintType.MARGIN,1);//设置外边距
    }

    public String createQRCode() throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(getQRCodeBytes("https://www.xonro.com",300,300));
    }

    private byte[] getQRCodeBytes(String content,int width,int height) throws Exception{
        ByteArrayOutputStream outputStream = null;

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hintTypes);//获取二维码

            //将二维码写入图片
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bufferedImage.setRGB(i,j,bitMatrix.get(i,j)? Color.BLACK.getRGB():Color.WHITE.getRGB());
                }
            }

            //将图片转为字节数组
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"png",outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (outputStream != null){
                outputStream.close();
            }
        }
    }
}
