package com.xu.region;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileWriter;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GetRegion {
    private static String tablePath = "E:/region.xlsx";

    public static void main(String[] args){
        if (args.length > 0 && !args[0].isEmpty() && args[0].endsWith("xlsx")){
            tablePath = args[0];
        }

        try {
            findResouce(tablePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findResouce(String fileName) throws Exception {
        XSSFWorkbook book = new XSSFWorkbook(new File(fileName));
        if(book.sheetIterator().hasNext())
            insertTemp(book.sheetIterator().next());
    }

    private static void insertTemp(Sheet sheet) throws Exception {
        String tableName = sheet.getSheetName();
        int rowNo = 0;
        XSSFRow row = ((XSSFSheet)sheet).getRow(rowNo++);
        String base = " insert into " + tableName + " ( " + StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.iterator(),0), false)
                .map(x -> x.getStringCellValue())
                .collect(Collectors.joining("','", "'", "'")) + " ) ";

        String result= StreamSupport.stream(Spliterators.spliteratorUnknownSize(((XSSFSheet)sheet).rowIterator(),0), false)
                .filter(r -> r.getRowNum() > 0)
                .map(r -> base + " VALUES ( " + StreamSupport.stream(Spliterators.spliteratorUnknownSize(r.iterator(),0), false)
                .map(x -> GetRegion.getCellValue(x)).collect(Collectors.joining("','", "'", "'")) + " ) ")
                .collect(Collectors.joining("\n"));
        GetRegion.write(tableName, result);
        System.out.println(result);
    }

    public static String getCellValue(Cell cell){
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                return "";
            case BLANK:
                return "";
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case NUMERIC:
                return cell.getNumericCellValue() + "";
            case STRING:
                return cell.getStringCellValue();
            default:
                return cell.getStringCellValue();
        }
    }

    public static void write(String fileName, String lines) throws Exception {
        FileWriter fw = new FileWriter("../" + fileName + ".sql");
        fw.write(lines);
        fw.flush();
        fw.close();
    }
}
