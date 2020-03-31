package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;

public class CensusDAO {
    public int tin;
    public String stateName;
    public int serialNumber;
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.state;
        this.population = csvStateCensus.population;
        this.area = csvStateCensus.areaInSqKm;
        this.density = csvStateCensus.densityPerSqKm;
    }

    public CensusDAO(CSVStates csvStates) {
        this.serialNumber = csvStates.serialNumber;
        this.stateName = csvStates.stateName;
        this.tin = csvStates.tin;
        this.stateCode = csvStates.stateCode;
    }

    public CensusDAO(CSVUSCensus csvUSCensus) {
        this.state = csvUSCensus.state;
        this.stateCode = csvUSCensus.stateId;
        this.population = csvUSCensus.population;
        this.area = csvUSCensus.totalArea;
        this.density = csvUSCensus.populationDensity;
    }
}