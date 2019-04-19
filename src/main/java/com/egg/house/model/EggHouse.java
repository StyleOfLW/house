package com.egg.house.model;


import javax.persistence.*;

@Entity
@Table(name="egghouse")
public class EggHouse
{
    @Id
    private int id;

    @Column(name = "eggHouseName")
    private String eggHouseName;

    public EggHouse()
    {

    }

    public EggHouse(int houseNumber, String eggHouseName)
    {
        this.id = houseNumber;
        this.eggHouseName = eggHouseName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEggHouseName()
    {
        return eggHouseName;
    }

    public void setEggHouseName(String eggHouseName)
    {
        this.eggHouseName = eggHouseName;
    }
}
