package com.wuyiz.function;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @ClassName ImageUtil
 * @Description: TODO
 * @Author AllureLove
 * @Date 2019/10/16
 * @Version V1.0
 **/
public class ImageUtil {
    public static boolean cutImage(File sourcePath,int cutNumber,String savePath){
        try {
            BufferedImage image = ImageIO.read(sourcePath);     //write the icon to buffered

            int allWidth = image.getWidth();        //get the icon width & height
            int allHeight = image.getHeight();
            int width = allWidth / cutNumber;       //calculate the width after cutting
            int height = allHeight / cutNumber;

            for (int i = 0; i < cutNumber; i++){
                for (int j = 0; j < cutNumber; j++){
                    /*
                     * Writes an image using an arbitrary <code>ImageWriter</code>
                     * that supports the given format to a <code>File</code>.  If
                     * there is already a <code>File</code> present, its contents are
                     * iscarded.
                     */
                    ImageIO.write(image.getSubimage(j*width, i*height, width, height),
                            "jpg",
                            new File(savePath + "\\" + (i*cutNumber+j) + ".jpg"));
                }
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        ImageUtil.cutImage(new File("img\\type1\\index.jpg"),3,"img\\type1\\3");
    }
}
