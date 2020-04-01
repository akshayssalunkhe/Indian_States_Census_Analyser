package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int area;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int density;

    public CSVStateCensus(String state, int population, int area, int density) {
        this.state = state;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    public CSVStateCensus() {
    }
}