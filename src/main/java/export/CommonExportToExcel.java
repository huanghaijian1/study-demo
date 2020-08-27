package export;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommonExportToExcel {
//    /**
//     * 导出
//     * @param response
//     * @param jsonArray
//     * @param exportColumns
//     * @throws IOException
//     */
//    public static void export(HttpServletResponse response, JSONArray jsonArray , List<String> exportColumns) throws Exception {
//        response.setContentType("application/force-download");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Disposition", "attachment;fileName="+System.currentTimeMillis() + ".xlsx");
//
//        getWorkBook(response , jsonArray, exportColumns);
//    }
//
//    private static void getWorkBook(HttpServletResponse response,JSONArray jsonArray, List<String> exportColumns) throws Exception {
//        // 创建工作簿
//        Workbook wb = new XSSFWorkbook();
//
//        // 创建一个工作表sheet
//        Sheet sheet = wb.createSheet();
//
//        if (jsonArray == null || jsonArray.length() == 0){
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            wb.write(os);
//            InputStream is = new ByteArrayInputStream(os.toByteArray());
//            OutputStream out = response.getOutputStream();
//            byte[] buffer = new byte[1024];
//            int i = is.read(buffer);
//            while (i != -1) {
//                out.write(buffer, 0, i);
//                i = is.read(buffer);
//            }
//            os.close();
//            return;
//        }
//
//        // 申明行
//        Row row = sheet.createRow(0);
//        // 申明单元格
//        Cell cell = null;
//
//        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
//        //   HSSFCellStyle titleStyle = (HSSFCellStyle) wb.createCellStyle();
//        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//        //边框
//        titleStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//        titleStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//        titleStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        titleStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
//        // 设置前景色
//        titleStyle.setFillForegroundColor(HSSFColor.GREEN.index);
//        //titleStyle.setFillForegroundColor(new HSSFColor(new java.awt.Color(159, 213, 183)));
//        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        Font font = wb.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        // 设置字体
//        titleStyle.setFont(font);
//
//        row.setHeight((short)(2 * 256));
//        int columnIndex = 0;
//        //   Column column;
//
//        //设置标题
//        for (String field : exportColumns) {
//
//            // 列宽注意乘256
//            sheet.setColumnWidth(columnIndex, 20 * 256);
//            // 写入标题
//            String title = field.split(";;")[1];
//            cell = row.createCell(columnIndex);
//            cell.setCellStyle(titleStyle);
//            cell.setCellValue(title);
//
//            columnIndex++;
//        }
//
//        int rowIndex = 1;
//
//        //数据内容样式
//        CellStyle cs = wb.createCellStyle();
//        cs.setAlignment(CellStyle.ALIGN_CENTER);
//        cs.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//        cs.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//        cs.setBorderRight(XSSFCellStyle.BORDER_THIN);
//        cs.setBorderTop(XSSFCellStyle.BORDER_THIN);
//
//        int n = jsonArray.length();
//        for (int i = 0 ; i < n ; i++) {
//            row = sheet.createRow(rowIndex);
//            columnIndex = 0;
//            Object o = null;
//
//            JSONObject json = jsonArray.getJSONObject(i);
//            for (String field : exportColumns) {
//
//                // 数据
//                cell = row.createCell(columnIndex);
//                //   o = field.get(t);
//                try{
//                    o = json.get(field.split(";;")[0]);
//                }catch (Exception e) {
//                    o = null;
//                }
//
//                // 如果数据为空
//                cell.setCellStyle(cs);
//                if (o == null){
//                    cell.setCellValue("");
//                    columnIndex++;
//                    continue;
//                }
//
//
//                if(o instanceof  Long){
//                    //处理时间戳
//                    cell.setCellValue(timeStamp2Date(json.get(field.split(";;")[0]).toString(),null));
//
//                }else {
//                    cell.setCellValue(json.get(field.split(";;")[0]).toString());
//                }
//
//                columnIndex++;
//            }
//
//            rowIndex++;
//        }
//
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        wb.write(os);
//        InputStream is = new ByteArrayInputStream(os.toByteArray());
//        OutputStream out = response.getOutputStream();
//        byte[] buffer = new byte[1024];
//        int i = is.read(buffer);
//        while (i != -1) {
//            out.write(buffer, 0, i);
//            i = is.read(buffer);
//        }
//        os.close();
//    }
//
//    public static String timeStamp2Date(String seconds,String format) {
//        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
//            return "";
//        }
//        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        return sdf.format(new Date(Long.valueOf(seconds)));
//    }
}
