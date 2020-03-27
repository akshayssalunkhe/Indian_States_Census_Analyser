package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusMap = null;

    //CONSTRUCTOR
    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
        this.censusList = new ArrayList<>();
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

    //METHOD TO LOAD CSV FILE
    public int loadStateCSVData(String filePath) throws IOException, StateCensusAnalyserException, CSVBuilderException {
        String extension = getFileExtension(filePath);
        //CHECKING FILE EXTENSION
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder icsvBuilder = new OpenCSVBuilder();
            Iterator<CSVStateCensus> stateCensusIterator = icsvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            while (stateCensusIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCensusIterator.next());
                this.censusMap.put(censusDAO.state, censusDAO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = censusMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "DELIMITER_OR_HEADER_INCORRECT_ERROR_ BUILDING_CSV");
        }
    }

    //METHOD TO LOAD CSV STATE CODE FILE
    public int loadStatesCodeCSVData(String filePath) throws IOException, StateCensusAnalyserException, CSVBuilderException {
        String extension = getFileExtension(filePath);
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder icsvBuilder = new OpenCSVBuilder();
            Iterator<CSVStates> stateCodeIterator = icsvBuilder.getCSVFileIterator(reader, CSVStates.class);
            while (stateCodeIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCodeIterator.next());
                this.censusMap.put(censusDAO.stateCode, censusDAO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = censusMap.size();
            return numberOfRecords;
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "DELIMITER_OR_HEADER_INCORRECT_ERROR_ BUILDING_CSV");
        }
    }

    //METHOD TO GET EXTENSION OF FILE
    public String getFileExtension(String filePath) {
        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0)
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        else return "";
    }

    //METHOD TO GET STATE WISE SORTED DATA
    public String getStateWiseSortedCensusData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        loadStateCSVData(csvFilePath);
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sortCSVCensusData(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO GET SORTED STATE CODE DATA
    public String getStateCodeWiseSortedData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        loadStatesCodeCSVData(csvFilePath);
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        this.sortCSVCensusData(stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT STATE CENSUS DATA AS PER POPULATION
    public String getPopulationWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sortCSVCensusData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    // METHOD TO SORT CSV DATA
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