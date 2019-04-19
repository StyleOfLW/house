package com.egg.house.service.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EggHouse
{
    private String eggHouseNameAndNumber;
    private Map<String,List> freshEgg;
    private Map<String,List> dirtyEgg;
    private Map<String,List> brokenEgg;

    public String getEggHouseNameAndNumber()
    {
        return eggHouseNameAndNumber;
    }

    public void setEggHouseNameAndNumber(String eggHouseNameAndNumber)
    {
        this.eggHouseNameAndNumber = eggHouseNameAndNumber;
    }

    public Map<String, List> getFreshEgg()
    {
        return freshEgg;
    }

    public void setFreshEgg(Map<String, List> freshEgg)
    {
        this.freshEgg = freshEgg;
    }

    public Map<String, List> getDirtyEgg()
    {
        return dirtyEgg;
    }

    public void setDirtyEgg(Map<String, List> dirtyEgg)
    {
        this.dirtyEgg = dirtyEgg;
    }

    public Map<String, List> getBrokenEgg()
    {
        return brokenEgg;
    }

    public void setBrokenEgg(Map<String, List> brokenEgg)
    {
        this.brokenEgg = brokenEgg;
    }

    public EggHouse()

    {
        freshEgg = new HashMap<>();
        dirtyEgg = new HashMap<>();
        brokenEgg = new HashMap<>();
        freshEgg.put("weight", new ArrayList());
        freshEgg.put("number", new ArrayList());
        dirtyEgg.put("weight", new ArrayList());
        dirtyEgg.put("number", new ArrayList());
        brokenEgg.put("weight", new ArrayList());
        brokenEgg.put("number", new ArrayList());
    }
}
