package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Total area", required = true)
    public int totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public int populationDensity;
}
