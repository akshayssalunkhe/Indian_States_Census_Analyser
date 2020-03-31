package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusDAOMap = null;

    //CONSTRUCTOR
    public StateCensusAnalyser() {
        this.censusList = new ArrayList<>();
        this.censusDAOMap = new HashMap<>();
    }

    public enum Country {INDIA, US}

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }


    public int loadStateCensusCSVData(Country country, String... csvFilePath) throws StateCensusAnalyserException {
        censusDAOMap = CensusAdapterFactory.getCensusData(country, csvFilePath);
        censusList = censusDAOMap.values().stream().collect(Collectors.toList());
        return censusDAOMap.size();
    }

    //METHOD TO GET STATE WISE SORTED DATA
    public String getStateWiseSortedCensusData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sortCSVCensusData(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO GET SORTED STATE CODE DATA
    public String getStateCodeWiseSortedData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        this.sortCSVCensusData(stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT STATE CENSUS DATA AS PER POPULATION
    public String getPopulationWiseSortedCensusData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sortCSVCensusData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA AS PER POPULATION DENSITY PER SQUARE KILOMETER
    public String getSortedDataAccordingToPopulationDensityPerSqKm(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.density);
        this.sortCSVCensusData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA AS PER POPULATION DENSITY PER SQUARE KILOMETER
    public String getSortedDataAccordingToAreaInSquareKilometer(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.area);
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