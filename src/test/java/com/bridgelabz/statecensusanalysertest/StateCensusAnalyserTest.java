package com.bridgelabz.statecensusanalysertest;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyser.CSVBuilderException;
import com.bridgelabzs.statecensusanalyser.CSVStateCensus;
import com.bridgelabzs.statecensusanalyser.CensusDAO;
import com.bridgelabzs.statecensusanalyser.StateCensusAnalyser;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    //GIVING FILE PATH
    private static final String STATE_CENSUS_DATA_PATH = "src/test/resources/StateCensusData.csv";
    private static final String STATES_CODE_CSV_PATH = "src/test/resources/StateCode.csv";
    private static final String US_CENSUS_DATA_CSV_FILE_PATH = "src/test/resources/USCensusData.csv";


    //GIVING WRONG FILE PATH
    private static final String STATE_CENSUS_DATA_WRONG_PATH = "src/test/resources/StateCensusDa.csv";
    private static final String STATE_CODE_CSV_WRONG_PATH = "src/test/resources/StateCo.csv";

    //GIVING WRONG FILE EXTENSION
    private static final String FILE_WITH_WRONG_EXTENSION = "src/test/resources/StateCensusDa.txt";
    private static final String STATE_CODE_CSV_FILE_WITH_WRONG_EXTENSION = "src/test/resources/StateCode.txt";

    //GIVING WRONG FILE DELIMITER
    private static final String FILE_WITH_INCORRECT_DELIMITER = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";
    private static final String STATE_CODE_CSV_FILE_WITH_INCORRECT_DELIMITER = "src/test/resources/StateCodeIncorrectDelimiter.csv";

    //GIVING WRONG FILE HEADER
    private static final String FILE_WITH_WRONG_HEADER = "src/test/resources/StateCensusDataIncorrectHeader.csv";
    private static final String STATE_CODE_CSV_FILE_WITH_WRONG_HEADER = "src/test/resources/StateCodeIncorrectHeader.csv";

    //CREATING OBJECT
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrect_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_WRONG_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectExtension_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStateCSVData(FILE_WITH_WRONG_EXTENSION);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectDelimiter_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStateCSVData(FILE_WITH_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectHeader_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStateCSVData(FILE_WITH_WRONG_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadStatesCodeCSVData(STATES_CODE_CSV_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrect_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStatesCodeCSVData(STATE_CODE_CSV_WRONG_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectExtension_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStatesCodeCSVData(STATE_CODE_CSV_FILE_WITH_WRONG_EXTENSION);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectDelimiter_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStatesCodeCSVData(STATE_CODE_CSV_FILE_WITH_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectHeader_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStatesCodeCSVData(STATE_CODE_CSV_FILE_WITH_WRONG_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenSortedOnStateAlphabetically_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(STATE_CENSUS_DATA_PATH);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCodeData_WhenSortedOnStateCode_ShouldReturnSortedResult() {
        String sortedStatesCodeData = null;
        try {
            sortedStatesCodeData = stateCensusAnalyser.getStateCodeWiseSortedData(STATES_CODE_CSV_PATH);
            CSVStates[] censusCSVStateCode = new Gson().fromJson(sortedStatesCodeData, CSVStates[].class);
            Assert.assertEquals("AD", censusCSVStateCode[0].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData(STATE_CENSUS_DATA_PATH);
            CSVStateCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensuses[0].population);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedDataAccordingToPopulationDensityPerSqKm(STATE_CENSUS_DATA_PATH);
            CensusDAO[] csvStateCensus = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, csvStateCensus[0].density);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        try {
            String sortedCensusData = stateCensusAnalyser.getSortedDataAccordingToAreaInSquareKilometer(STATE_CENSUS_DATA_PATH);
            CensusDAO[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].area);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecords = stateCensusAnalyser.loadCSVDataFileForUSCensus(US_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}