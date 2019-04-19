package com.egg.house.service.excel;

import java.util.List;

public class EggHouseDataService
{
    private ExcelRead excelData;

    public void saveDataList(List<EggHouse> eggHouses)
    {
        //save eggHouses
    }

    public List<EggHouse> getEggHouses()
    {
        List<EggHouse> eggHouses = null;
        try
        {
            eggHouses = excelData.getEggHouseData();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return eggHouses;
    }
}
