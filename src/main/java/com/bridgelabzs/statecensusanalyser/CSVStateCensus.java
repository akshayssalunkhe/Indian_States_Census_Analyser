package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public double population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double area;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public double density;

    //PARAMETRISED CONSTRUCTOR FOR STATE CENSUS
    public CSVStateCensus(String state, double population, double area, double density) {
        this.state = state;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    //DEFAULT CONSTRUCTOR
    public CSVStateCensus() {
    }
}