package com.fd.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import java.awt.*;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/2 9:52
 */

public class QrCodeUtilTest {


    @Test
    public void test(){
        // 第一种
        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));

        //  第二种
//        QrConfig config = new QrConfig(300, 300);
//        // 设置边距，既二维码和背景之间的边距
//        config.setMargin(3);
//        // 设置前景色，既二维码颜色（青色）
//        config.setForeColor(Color.CYAN);
//        // 设置背景色（灰色）
//        config.setBackColor(Color.GRAY);
//
//        // 生成二维码到文件，也可以到流
//        QrCodeUtil.generate("http://hutool.cn/", config, FileUtil.file("D:/qrcode.jpg"));

        // 第三种
//        QrCodeUtil.generate(//
//                "http://hutool.cn/", //二维码内容
//                QrConfig.create().setImg("D:/logo_small.jpg"), //附带logo
//                FileUtil.file("D:/qrcodeWithLogo.jpg")//写出到的文件
//        );

        // 第四种
        QrConfig config = new QrConfig();
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        QrCodeUtil.generate("https://hutool.cn/", config, FileUtil.file("d:/qrcodeCustom.jpg"));

        // 识别二维码
        // decode -> "http://hutool.cn/"
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcodeCustom.jpg"));
        System.out.println(decode);
    }
}
