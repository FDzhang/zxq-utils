package com.fd.demo.utils.excel;

import cn.hutool.core.io.FileUtil;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author zhangxinqiang
 * @create 2021/12/20 14:14
 */
public class TestDemo {
    final static String path = "E:/";
    final static String file = "TestExcel.xlsx";
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args)
    {
        try{
            InputStream input = new FileInputStream(path +"/"+ file);
            HSSFWorkbook excelBook = new HSSFWorkbook();
            //判断Excel文件将07+版本转换为03版本
            if(file.endsWith(EXCEL_XLS)){  //Excel 2003
                excelBook = new HSSFWorkbook(input);
            }
            else if(file.endsWith(EXCEL_XLSX)){  // Excel 2007/2010
                Transform xls = new Transform();
                XSSFWorkbook workbookOld = new XSSFWorkbook(input);
                xls.transformXSSF(workbookOld, excelBook);
            }

            ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
            );
            //去掉Excel头行
            excelToHtmlConverter.setOutputColumnHeaders(false);
            //去掉Excel行号
            excelToHtmlConverter.setOutputRowNumbers(false);


            excelToHtmlConverter.processWorkbook(excelBook);

            Document htmlDocument = excelToHtmlConverter.getDocument();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();

//            serializer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");

            serializer.transform(domSource, streamResult);
            outStream.close();

            //Excel转换成Html
            String content = new String(outStream.toByteArray());
            FileUtil.writeUtf8String(content, "e:/html_test.html");
            System.out.println(content);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
