package com.fd.demo.utils.excel;

import com.aspose.cells.HtmlSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.cells.WorksheetCollection;

/**
 * @author zhangxinqiang
 * @create 2021/12/27 18:29
 */
public class Excel2HtmlUtil {
    public static void main(String[] args) throws Exception {
        // Load Excel file
        HtmlSaveOptions options = new HtmlSaveOptions();
        options.setExportActiveWorksheetOnly(true);
        options.setExportImagesAsBase64(true);

        Workbook workbook = new Workbook("e:/TestExcel.xlsx");
        WorksheetCollection sheets = workbook.getWorksheets();
        int count = sheets.getCount();
        for (int i = 0; i < count; i++) {
            sheets.setActiveSheetIndex(i);
            workbook.save("E:/" + sheets.get(i).getName() + ".html", options);
        }
    }
}
