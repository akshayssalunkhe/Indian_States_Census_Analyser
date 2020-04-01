package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVUSCensus {
    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public double population;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    //PARAMETRISED CONSTRUCTOR
    public CSVUSCensus(String stateId, String state, double population, double totalArea, double populationDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.totalArea = totalArea;
        this.populationDensity = populationDensity;
    }

    //DEFAULT CONSTRUCTOR
    public CSVUSCensus() {
    }
}