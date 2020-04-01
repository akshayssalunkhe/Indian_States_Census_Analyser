package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;
    private Country country;

    //CONSTRUCTOR
    public StateCensusAnalyser(Country country) {
        this.country = country;
    }

    //ENUM FOR COUNTRY
    public enum Country {INDIA, US}

    //ENUM FOR SORTING MODE
    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

    //METHOD TO LOAD CENSUS CSV DATA
    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StateCensusAnalyserException {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }

    //METHOD TO SORT CENSUS DATA
    public String getSortedCensusData(SortingMode mode) throws StateCensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        ArrayList arrayList = censusDAOMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(arrayList);
    }
}