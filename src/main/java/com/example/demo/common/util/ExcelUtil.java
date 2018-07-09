package com.example.demo.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * excel工具类
 *
 * @author bowensun
 * @date 2018/5/23
 */
public class ExcelUtil {

    //日期格式化模板
    private static String parttern = "yyyy-MM-dd HH:mm:ss";

    //数字正则模板
    private static String numberPattern = "^\\d+(\\.\\d+)?$";

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * excel XSSF导出
     *
     * @param fileNamePath  导出文件路径
     * @param sheetName 表格名
     * @param list  传入数据
     * @param titles 表头
     * @param fieldNames 字段名
     * @param <T> 包装类
     * @return 文件
     */
    public static <T> File exportXSSF(String fileNamePath, String sheetName,
                                  List<T> list, String[] titles, String[] fieldNames) {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.createRow(list.size() + 1);

        //获取样式
        CellStyle style1 = getCellStyle(workbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, HSSFColor.LIGHT_GREEN.index, null);
        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        CellStyle firstStyle = getCellStyle(workbook, CellStyle.BORDER_THIN, CellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, null, font);
        //设置表头
        XSSFRow firstRow = sheet.createRow(0);
        for(int i = 0; i < titles.length; i++){
            XSSFCell cell = firstRow.createCell(i);
            XSSFRichTextString xssfRichTextString = new XSSFRichTextString(titles[i]);
            cell.setCellValue(xssfRichTextString);
            cell.setCellStyle(firstStyle);
        }

        //设置数据行
        for (int i = 1; i < list.size(); i++) {
            T t = list.get(i);
            XSSFRow row = sheet.createRow(i);
            Class<? extends Object> clazz = t.getClass();
            Method method = null;
            Object invoke = null;
            logger.info("=======直接生成文件，正在处理第" + row.getRowNum() + "行===========");
            for (int j = 0; j < fieldNames.length; j++) {
                String methodName = "get" + capitalize(fieldNames[j]);
                try {
                    method = clazz.getDeclaredMethod(methodName);
                    invoke = method.invoke(t);
                    //null值默认为空字符串
                    invoke = invoke == null ? "" : invoke;
                    //格式化日期
                    if(invoke instanceof Date){
                        invoke = new SimpleDateFormat(parttern).format(invoke);
                    }
                    XSSFCell cell = row.createCell(j);
                    XSSFRichTextString xssfRichTextString = new XSSFRichTextString(invoke.toString());
                    cell.setCellValue(xssfRichTextString);
                    cell.setCellStyle(style1);
                } catch (Exception e) {
                    logger.info("=================设置数据行异常======================");
                    e.printStackTrace();
                }
            }
        }
        File file = generateFile(fileNamePath);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != os) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("生成完毕");
        return file;
    }

    /**
     * excel HSSF导出
     *
     * @param fileNamePath
     * @param sheetName
     * @param list
     * @param titles
     * @param fieldNames
     * @param <T>
     * @return
     */
    public static <T> File exportHSSF(String fileNamePath, String sheetName,
                                      List<T> list, String[] titles, String[] fieldNames) {
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.createRow(list.size() + 1);

        //获取样式
        CellStyle style1 = getCellStyle(workbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, HSSFColor.LIGHT_GREEN.index, null);
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle firstStyle = getCellStyle(workbook, CellStyle.BORDER_THIN, CellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, null, font);
        //设置表头
        HSSFRow firstRow = sheet.createRow(0);
        for(int i = 0; i < titles.length; i++){
            HSSFCell cell = firstRow.createCell(i);
            HSSFRichTextString hssfRichTextString = new HSSFRichTextString(titles[i]);
            cell.setCellValue(hssfRichTextString);
            cell.setCellStyle(firstStyle);
        }

        //设置数据行
        for (int i = 1; i < list.size(); i++) {
            T t = list.get(i);
            HSSFRow row = sheet.createRow(i);
            Class<? extends Object> clazz = t.getClass();
            Method method = null;
            Object invoke = null;
            logger.info("=======直接生成文件，正在处理第" + row.getRowNum() + "行===========");
            for (int j = 0; j < fieldNames.length; j++) {
                String methodName = "get" + capitalize(fieldNames[j]);
                try {
                    method = clazz.getDeclaredMethod(methodName);
                    invoke = method.invoke(t);
                    //null值默认为空字符串
                    invoke = invoke == null ? "" : invoke;
                    //格式化日期
                    if(invoke instanceof Date){
                        invoke = new SimpleDateFormat(parttern).format(invoke);
                    }
                    HSSFCell cell = row.createCell(j);
                    HSSFRichTextString hssfRichTextString = new HSSFRichTextString(invoke.toString());
                    cell.setCellValue(hssfRichTextString);
                    cell.setCellStyle(style1);
                } catch (Exception e) {
                    logger.info("=================设置数据行异常======================");
                    e.printStackTrace();
                }
            }
        }
        File file = generateFile(fileNamePath);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != os) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("生成完毕");
        return file;
    }

    /**
     * 基于模板的excel XSSF生成
     *
     * @param sheetName
     * @param list
     * @param fieldNames
     * @param <T>
     */
    public static <T> void insertToExcelXSSFFile(String templateFilePath, String destFilePath, String sheetName, List<T> list, String [] fieldNames){
        XSSFWorkbook workbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(templateFilePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("生成工作簿失败");
        }
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook, 100);
        Sheet sheet = sxssfWorkbook.getSheet(sheetName);

        //样式设置
        CellStyle style1 = getCellStyle(sxssfWorkbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, null, null);
        CellStyle style2 = getCellStyle(sxssfWorkbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, HSSFColor.LIGHT_GREEN.index, null);

        logger.info("=======导出发货单开始===========");

        //设置数据行
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            Row row = sheet.createRow(i + 1);
            Class<? extends Object> clazz = t.getClass();
            Method method = null;
            Object invoke = null;
            for (int j = 0; j < fieldNames.length; j++) {
                String methodName = "get" + capitalize(fieldNames[j]);
                try {
                    method = clazz.getDeclaredMethod(methodName);
                    invoke = method.invoke(t);
                    //null值默认为空字符串
                    invoke = invoke == null ? "" : invoke;
                    //格式化日期
                    if (invoke instanceof Date) {
                        invoke = new SimpleDateFormat(parttern).format(invoke);
                    }
                } catch (Exception e) {
                    logger.info("设置数据行数据异常");
                }
                Cell cell = row.createCell(j);
                cell.setCellStyle(style1);
                if (cell.getRowIndex() % 2 == 0){
                    cell.setCellStyle(style2);
                }
                if (cell.getColumnIndex() == 5) {
                    cell.setCellValue(Double.parseDouble(invoke.toString()));
                } else {
                    if(cell.getColumnIndex() == 7){
                        if("0".equals(invoke.toString())){
                            invoke = "非自提";
                        }else if ("1".equals(invoke.toString())){
                            invoke = "自提";
                        }
                    }
                    XSSFRichTextString string = new XSSFRichTextString(invoke.toString());
                    cell.setCellValue(string);
                }
            }
        }
        FileOutputStream outputStream = null;
        File file = generateFile(destFilePath);
        try {
            outputStream = new FileOutputStream(file);
            sxssfWorkbook.write(outputStream);
        } catch (IOException e) {
            logger.info("=========获取响应流失败或将相应流写入工作簿失败=======");
            e.printStackTrace();
        }finally {
            sxssfWorkbook.dispose();
            try {
                outputStream.close();
                fileInputStream.close();
                logger.info("生成完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 基于模板的excel HSSF生成
     *
     * @param sheetName
     * @param list
     * @param fieldNames
     * @param <T>
     */
    public static <T> void insertToExcelHSSFFile(String templateFilePath, String destFilePath, String sheetName, List<T> list, String [] fieldNames){
        HSSFWorkbook workbook = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(templateFilePath);
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("生成工作簿失败");
        }
        HSSFSheet sheet = workbook.getSheet(sheetName);

        //样式设置
        CellStyle style1 = getCellStyle(workbook, HSSFCellStyle.BORDER_THIN, HSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, null, null);
        CellStyle style2 = getCellStyle(workbook, HSSFCellStyle.BORDER_THIN, HSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, HSSFColor.LIGHT_GREEN.index, null);

        //设置数据行
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            Class<? extends Object> clazz = t.getClass();
            Method method = null;
            Object invoke = null;
            logger.info("=======直接生成文件，正在处理第" + row.getRowNum() + "行===========");
            for (int j = 0; j < fieldNames.length; j++) {
                String methodName = "get" + capitalize(fieldNames[j]);
                try {
                    method = clazz.getDeclaredMethod(methodName);
                    invoke = method.invoke(t);
                    //null值默认为空字符串
                    invoke = invoke == null ? "" : invoke;
                    //格式化日期
                    if (invoke instanceof Date) {
                        invoke = new SimpleDateFormat(parttern).format(invoke);
                    }
                } catch (Exception e) {
                    logger.info("设置数据行数据异常");
                }
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style1);
                if (cell.getRowIndex() % 2 == 0){
                    cell.setCellStyle(style2);
                }
                if (cell.getColumnIndex() == 5) {
                    cell.setCellValue(Double.parseDouble(invoke.toString()));
                } else {
                    if(cell.getColumnIndex() == 7){
                        if("0".equals(invoke.toString())){
                            invoke = "非自提";
                        }else if ("1".equals(invoke.toString())){
                            invoke = "自提";
                        }
                    }
                    HSSFRichTextString string = new HSSFRichTextString(invoke.toString());
                    cell.setCellValue(string);
                }
            }
        }
        FileOutputStream outputStream = null;
        File file = new File(destFilePath);
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.info("=========获取响应流失败或将相应流写入工作簿失败=======");
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
                fileInputStream.close();
                logger.info("生成完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    /**
     * 基于模板的excel XSSF下载
     *
     * @param filePathName
     * @param sheetName
     * @param list
     * @param fieldNames
     * @param <T>
     */
    public static <T> void insertToExcelXSSF(HttpServletResponse response, String filePathName, String sheetName, List<T> list, String [] fieldNames){
        InputStream resourceAsStream = ExcelUtil.class.getClassLoader().getResourceAsStream(filePathName);
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(resourceAsStream);
        } catch (Exception e) {
            logger.info("生成工作簿失败");
        }
            XSSFSheet sheet = workbook.getSheet(sheetName);

        //样式设置
        CellStyle style1 = getCellStyle(workbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, null, null);
        CellStyle style2 = getCellStyle(workbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.ALIGN_CENTER, IndexedColors.GREY_40_PERCENT.index, HSSFColor.LIGHT_GREEN.index, null);

            //设置数据行
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                XSSFRow row = sheet.createRow(i + 1);
                Class<? extends Object> clazz = t.getClass();
                Method method = null;
                Object invoke = null;
                //StringBuffer buffer = new StringBuffer();
                for (int j = 0; j < fieldNames.length; j++) {
                    String methodName = "get" + capitalize(fieldNames[j]);
                    try {
                        method = clazz.getDeclaredMethod(methodName);
                        invoke = method.invoke(t);
                        //null值默认为空字符串
                        invoke = invoke == null ? "" : invoke;
                        //格式化日期
                        if (invoke instanceof Date) {
                            invoke = new SimpleDateFormat(parttern).format(invoke);
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    XSSFCell cell = row.createCell(j);
                    //数字转为double
                    //使用正则匹配有一个问题，类似快递单号这样的长数字会自动转换成科学计数
/*                    Pattern pattern = Pattern.compile(numberPattern);
                    Matcher matcher = pattern.matcher(invoke.toString());*/
                    cell.setCellStyle(style1);
                    if (cell.getRowIndex() % 2 == 0){
                        cell.setCellStyle(style2);
                    }
                    if (cell.getColumnIndex() == 4 ||  cell.getColumnIndex() == 6 || cell.getColumnIndex() == 7) {
                        cell.setCellValue(Double.parseDouble(invoke.toString()));
                    }else {
                        XSSFRichTextString string = new XSSFRichTextString(invoke.toString());
                        cell.setCellValue(string);
                    }
                }
            }
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            setResponse(response, filePathName);
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.info("=========获取响应流失败或将相应流写入工作簿失败=======");
        }
    }

    /**
     * 设置响应体
     *
     * @param response 响应
     * @param fileName 文件名
     */
    public static void setResponse(HttpServletResponse response, String fileName){
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        //处理中文文件名乱码
        try {
            response.setHeader("Content-disposition", "attachment;filename="
                                + fileName
                                + ";filename*=utf-8''"
                                + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.info("设置响应头异常{}", e.getCause());
        }
    }

    /**
     * 设置单元格边框粗细，颜色，对齐方式和字体,字体默认为微软雅黑，十号字体
     *
     * @param workbook
     * @param cellStyle
     * @param alignment
     * @param cellBorderColor
     * @param cellFont
     * @return
     */
    private static CellStyle  getCellStyle(Workbook workbook, Short cellStyle, Short alignment, Short cellBorderColor, Short foregroundColor, Font cellFont){
        CellStyle style = workbook.createCellStyle();
        if (cellStyle != null) {
            style.setBorderBottom(cellStyle);
            style.setBorderLeft(cellStyle);
            style.setBorderRight(cellStyle);
            style.setBorderTop(cellStyle);
        }
        if (alignment != null){
            style.setAlignment(alignment);
        }
        if (cellBorderColor != null) {
            style.setBottomBorderColor(cellBorderColor);
            style.setLeftBorderColor(cellBorderColor);
            style.setRightBorderColor(cellBorderColor);
            style.setTopBorderColor(cellBorderColor);
        }
        if (cellFont != null){
            style.setFont(cellFont);
        }
        if(cellFont == null){
            Font font = workbook.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
            style.setFont(font);
        }
        if (foregroundColor != null){
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(foregroundColor);
        }
        return style;
    }

    /**
     * 设置方法首字母大写
     *
     * @param str
     * @return
     */
    private static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        final char firstChar = str.charAt(0);
        final char newChar = Character.toTitleCase(firstChar);
        if (firstChar == newChar) {
            return str;
        }
        char[] newChars = new char[strLen];
        newChars[0] = newChar;
        str.getChars(1,strLen, newChars, 1);
        return String.valueOf(newChars);
    }

    //生成文件
    private static File generateFile(String fileNamePath) {
        File file = new File(fileNamePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
