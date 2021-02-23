package com.fd.demo.utils.utils;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/23 13:48
 */

public class PdfUtil {

    private static Logger log = LoggerFactory.getLogger(PdfUtil.class);

    public static void main(String[] args) throws IOException {
        genPdf();
    }

    private static HashMap<String, String> getHashMap() {
        HashMap<String, String> map = new HashMap<>(32);
        map.put("name", "悟空");
        map.put("age", "500");
        return map;
    }

    private static void genPdf() throws IOException {
        FileOutputStream fos = new FileOutputStream("test.pdf");
        pdf(fos);
    }

    public static void pdf(OutputStream outputStream) {
        HashMap<String, String> map = getHashMap();

        String sourceFile = PdfUtil.class.getClassLoader().getResource("template.pdf").getPath();
        File templateFile = new File(sourceFile);

        fillParam(map, FileUtil.readBytes(templateFile), outputStream);
    }

    private static void fillParam(Map<String, String> fieldMap, byte[] template, OutputStream os) {
        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            reader = new PdfReader(template);
            stamper = new PdfStamper(reader, os);

            AcroFields acroFields = stamper.getAcroFields();

            setFieldProperty(acroFields);

            fillField(fieldMap, acroFields);

        } catch (Exception e) {
            log.error("生成pdf文件异常");
        } finally {
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) {
                    log.error("stamper关闭异常");
                }
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void setFieldProperty(AcroFields acroFields) throws IOException, DocumentException {
        BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        for (String key : acroFields.getFields().keySet()) {
            acroFields.setFieldProperty(key, "textfont", font, null);
            acroFields.setFieldProperty(key, "textsize", new Float(9), null);
        }
    }

    private static void fillField(Map<String, String> fieldMap, AcroFields acroFields) throws IOException, DocumentException {
        if (fieldMap != null) {
            for (String fieldName : fieldMap.keySet()) {

                int fieldType = acroFields.getFieldType(fieldName);

                if (fieldType == AcroFields.FIELD_TYPE_CHECKBOX) {
                    acroFields.setField(fieldName, fieldMap.get(fieldName), true);
                } else if (fieldType == AcroFields.FIELD_TYPE_PUSHBUTTON) {

                    Image image = Image.getInstance(fieldMap.get(fieldName));

                    PushbuttonField bt = acroFields.getNewPushbuttonFromField(fieldName);
                    bt.setImage(image);
                    PdfFormField ff = bt.getField();

                    acroFields.replacePushbuttonField(fieldName, ff);
                } else {
                    acroFields.setField(fieldName, fieldMap.get(fieldName));
                }
            }
        }
    }
}
