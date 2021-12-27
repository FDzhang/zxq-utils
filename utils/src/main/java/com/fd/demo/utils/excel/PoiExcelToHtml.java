package com.fd.demo.utils.excel;

import cn.hutool.core.io.FileUtil;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Iterator;
import java.util.List;


public class PoiExcelToHtml {
    final static String path = "e:/";
    final static String file = "TestExcel4.xls";

    public static void main(String args[]) throws Exception {
        InputStream input = new FileInputStream(path + file);
        HSSFWorkbook excelBook = new HSSFWorkbook(input);

        ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
        );
        //去掉Excel头行
        excelToHtmlConverter.setOutputColumnHeaders(false);
        //去掉Excel行号
        excelToHtmlConverter.setOutputRowNumbers(false);

        excelToHtmlConverter.processWorkbook(excelBook);

//        List pics = excelBook.getAllPictures();
//        if (pics != null) {
//            for (int i = 0; i < pics.size(); i++) {
//                Picture pic = (Picture) pics.get(i);
//                try {
//                    pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName()));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        Document htmlDocument = excelToHtmlConverter.getDocument();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();

        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();

        String content = new String(outStream.toByteArray());

        FileUtil.writeUtf8String(content,"e:/TestExcel43.html");
    }


}
