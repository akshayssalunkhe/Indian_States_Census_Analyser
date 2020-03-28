package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVUnitedStatesCensus {
    @CsvBindByName(column = " State Id", required = true)
    public String stateId;
    @CsvBindByName(column = "State", required = true)
    public String usState;
    @CsvBindByName(column = "Population", required = true)
    public int usPopulation;
    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;
    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;
}
