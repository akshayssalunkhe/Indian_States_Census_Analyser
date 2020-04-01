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

    public enum Country {INDIA, US}

    public enum SortingMode {STATE, POPULATION, DENSITY, AREA}

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

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

    //METHOD TO SORT US CENSUS DATA BY POPULATION
    public String getPopulationWiseUSSortedCensusData() throws StateCensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sortCSVCensusData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT CSV DATA
    public void sortCSVCensusData(Comparator<CensusDAO> censusComparator) {
        for (int indexOne = 0; indexOne < censusList.size() - 1; indexOne++) {
            for (int indexTwo = 0; indexTwo < censusList.size() - indexOne - 1; indexTwo++) {
                CensusDAO censusOne = censusList.get(indexTwo);
                CensusDAO censusTwo = censusList.get(indexTwo + 1);
                if (censusComparator.compare(censusOne, censusTwo) > 0) {
                    censusList.set(indexTwo, censusTwo);
                    censusList.set(indexTwo + 1, censusOne);
                }
            }
        }
    }
}