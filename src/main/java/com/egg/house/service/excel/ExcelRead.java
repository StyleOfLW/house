package com.egg.house.service.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelRead
{
    private static  FormulaEvaluator  formulaEvaluator;
    private static List<String> headList = new ArrayList<>();
    private static List<List> dataList = new LinkedList<>();
    private static String excelPath = "D:\\test\\3月生产统计日报.xlsx";

    public static List<EggHouse> getEggHouseData() throws Exception
    {
        FileInputStream file = new FileInputStream(new File(excelPath));

        Workbook workbook = new XSSFWorkbook(file);
        formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);

        // This is get sheet ; Temporary default sheet 0;
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (isRowDeletedInExcel(row))  // This is deleted row in excel
            {
                extractExcelDataToCollection(row,formulaEvaluator);
            }
        }

        return filterAndStoreDataToCollection();
    }

    private static List<EggHouse> filterAndStoreDataToCollection()
    {
        filterDataList(dataList);
        filterList(headList);

        List<EggHouse> eggHouses = initEggHouse(headList);
        setDataToEggHouseList(eggHouses,dataList);
        return eggHouses;
    }



    private static boolean isRowDeletedInExcel(Row row)
    {
        return !row.getZeroHeight();
    }

    private static void setDataToEggHouseList(List<EggHouse> eggHouses, List<List> dataList)
    {
        int i = 0;

        for (EggHouse eggHouse : eggHouses)
        {
            eggHouse.getFreshEgg().put("weight",dataList.get(i++));
            eggHouse.getFreshEgg().put("number",dataList.get(i++));

            eggHouse.getDirtyEgg().put("weight", dataList.get(i++));
            eggHouse.getDirtyEgg().put("number", dataList.get(i++));

            eggHouse.getBrokenEgg().put("weight", dataList.get(i++));
            eggHouse.getBrokenEgg().put("number", dataList.get(i++));
        }
    }

    private static void testData(List<EggHouse> eggHouses)
    {
        for (EggHouse eggHouse : eggHouses)
        {
            System.out.println(eggHouse.getEggHouseNameAndNumber());
            System.out.println("Fresh:");
            eggHouse.getFreshEgg().forEach((key,Value) -> {
                System.out.println(key + " : " +Value);
            });
            System.out.println("");
            System.out.println("Dirty:");
            eggHouse.getDirtyEgg().forEach((key,Value) -> {
                System.out.println(key + " : " +Value);
            });
            System.out.println("");
            System.out.println("Broken:");
            eggHouse.getBrokenEgg().forEach((key,Value) -> {
                System.out.println(key + " : " +Value);
            });
            System.out.println("======================================================");

        }
    }

    private static List<EggHouse> initEggHouse(List<String> headList)
    {
        List<EggHouse> eggHouses = new LinkedList<>();
        headList.forEach(eggHouseNameAndNumber -> {
            if (!StringUtils.isEmpty(eggHouseNameAndNumber))
            {
                EggHouse e = new EggHouse();
                e.setEggHouseNameAndNumber(eggHouseNameAndNumber);
                eggHouses.add(e);
            }
        });
        return eggHouses;
    }

    private static void filterList(List<String> headList)
    {
        headList.removeIf(str -> str == null || str.equals("null"));
        headList.remove(0);
    }

    private static void filterDataList(List dataList)
    {
        for(int i=0; i<9;i++)
        {
            dataList.remove(0);
        }

        dataList.remove(dataList.size() - 1);
    }

    private static void extractExcelDataToCollection(Row row , FormulaEvaluator formulaEvaluator)
    {
        int i = 0;
        List<String> dataList = new ArrayList<>();
        for (Cell cell : row)
        {
            if (i == 0)
            {
                headList.add(getCellValueFormula(cell,formulaEvaluator));
            }
            if (i < 3)
            {
                i++;
                continue;
            }

            // extract all of its column about one month
            dataList.add(getCellValueFormula(cell,formulaEvaluator));
        }

        //store one row data
        ExcelRead.dataList.add(dataList);
    }


    private static void print(Cell cell, FormulaEvaluator formulaEvaluator)
    {
        System.out.print(getCellValueFormula(cell, formulaEvaluator) + "  ,  ");
    }

    //未处理公式
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//非线程安全
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    //处理公式
    public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            String number = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
            try
            {
                Double lnumber = Double.parseDouble(number);
                number = String.format("%.2f", lnumber);
            } catch (Exception e)
            {

            }
            return number;
        }
        return getCellValue(cell);
    }
}
