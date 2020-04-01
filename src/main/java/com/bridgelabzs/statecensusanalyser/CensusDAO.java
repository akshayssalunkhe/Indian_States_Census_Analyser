package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;

import java.util.Comparator;

public class CensusDAO {
    public int tin;
    public String stateName;
    public int serialNumber;
    public String state;
    public double population;
    public double area;
    public double density;
    public String stateCode;

    //CONSTRUCTOR FOR STATE CENSUS
    public CensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.state;
        this.population = csvStateCensus.population;
        this.area = csvStateCensus.area;
        this.density = csvStateCensus.density;
    }

    //CONSTRUCTOR FOR STATE CODE
    public CensusDAO(CSVStates csvStates) {
        this.serialNumber = csvStates.serialNumber;
        this.stateName = csvStates.stateName;
        this.tin = csvStates.tin;
        this.stateCode = csvStates.stateCode;
    }

    //CONSTRUCTOR FOR US CENSUS
    public CensusDAO(CSVUSCensus csvUSCensus) {
        this.state = csvUSCensus.state;
        this.stateCode = csvUSCensus.stateId;
        this.population = csvUSCensus.population;
        this.area = csvUSCensus.totalArea;
        this.density = csvUSCensus.populationDensity;
    }

    //METHOD TO SORT COMPARATOR
    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyser.SortingMode mode) {
        if (mode.equals(StateCensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CensusDAO::getPopulation).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CensusDAO::getArea).reversed();
        if (mode.equals(StateCensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CensusDAO::getDensity).reversed();
        return null;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    //METHOD TO RETURN DTO OBJECT
    public Object getCensusDTO(StateCensusAnalyser.Country country) {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new CSVStateCensus(state, population, area, density);
        if (country.equals(StateCensusAnalyser.Country.US))
            return new CSVUSCensus(stateCode, state, population, area, density);
        return null;
    }
}