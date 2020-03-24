package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class StateCensusAnalyser {
    //CREATING GLOBAL LIST OBJECT
    List<CSVStateCensus> csvList = null;

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
            csvList = icsvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return csvList.size();
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
            csvList = icsvBuilder.getCSVFileList(reader, CSVStates.class);
            return csvList.size();
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

    // GENERIC METHOD TO SORT CENSUS DATA
    public void sortCSVCensusData(List<CSVStateCensus> csvList, Comparator<CSVStateCensus> censusComparator) {
        for (int indexOne = 0; indexOne < csvList.size(); indexOne++) {
            for (int indexTwo = 0; indexTwo < csvList.size() - indexOne - 1; indexTwo++) {
                CSVStateCensus census1 = csvList.get(indexTwo);
                CSVStateCensus census2 = csvList.get(indexTwo + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvList.set(indexTwo, census2);
                    csvList.set(indexTwo + 1, census1);
                }
            }
        }
    }
}