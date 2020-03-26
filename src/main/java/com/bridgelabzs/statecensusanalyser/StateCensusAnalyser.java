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
    //CREATING GLOBAL LIST OBJECT
    List<CSVStateCensus> csvList = null;
    List<CSVStates> stateCodeList = null;

    //CREATING GLOBAL MAP OBJECT
    Map<String, CSVStateCensus> stateCensusMap;
    Map<String, CSVStates> csvStateCodeMap;

    //CONSTRUCTOR
    public StateCensusAnalyser() {
        this.stateCensusMap = new HashMap<>();
        this.csvStateCodeMap = new HashMap<>();
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
                CSVStateCensus stateCensus = stateCensusIterator.next();
                this.stateCensusMap.put(stateCensus.state, stateCensus);
                csvList = stateCensusMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = stateCensusMap.size();
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
                CSVStates stateCode = stateCodeIterator.next();
                this.csvStateCodeMap.put(stateCode.stateCode, stateCode);
                stateCodeList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            int numberOfRecords = csvStateCodeMap.size();
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
        if (csvList == null || csvList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
        this.sortCSVCensusData(csvList, censusComparator);
        String toJson = new Gson().toJson(csvList);
        return toJson;
    }

    //METHOD TO GET SORTED STATE CODE DATA
    public String getStateCodeWiseSortedData(String csvFilePath) throws StateCensusAnalyserException, IOException, CSVBuilderException {
        loadStatesCodeCSVData(csvFilePath);
        if (stateCodeList == null || stateCodeList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        Comparator<CSVStates> stateCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.sortCSVCensusData(stateCodeList, stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(stateCodeList);
        return sortedStateCodeJson;
    }

    //GENERIC METHOD TO SORT CENSUS DATA
    public <E> void sortCSVCensusData(List<E> csvList, Comparator<E> censusComparator) {
        for (int indexOne = 0; indexOne < csvList.size(); indexOne++) {
            for (int indexTwo = 0; indexTwo < csvList.size() - indexOne - 1; indexTwo++) {
                E census1 = csvList.get(indexTwo);
                E census2 = csvList.get(indexTwo + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvList.set(indexTwo, census2);
                    csvList.set(indexTwo + 1, census1);
                }
            }
        }
    }
}