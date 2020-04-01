package com.bridgelabz.statecensusanalysertest;

import com.bridgelabzs.statecensusanalyser.CSVStateCensus;
import com.bridgelabzs.statecensusanalyser.CensusDAO;
import com.bridgelabzs.statecensusanalyser.StateCensusAnalyser;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import static com.bridgelabzs.statecensusanalyser.StateCensusAnalyser.Country.INDIA;
import static com.bridgelabzs.statecensusanalyser.StateCensusAnalyser.Country.US;

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
    StateCensusAnalyser indianCensusAnalyser = new StateCensusAnalyser(INDIA);
    StateCensusAnalyser usCensusAnalyser = new StateCensusAnalyser(US);

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrect_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_WRONG_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectExtension_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, FILE_WITH_WRONG_EXTENSION);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectDelimiter_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, FILE_WITH_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectHeader_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, FILE_WITH_WRONG_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH, STATES_CODE_CSV_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrect_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CODE_CSV_WRONG_PATH);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, e.type);
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectExtension_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CODE_CSV_FILE_WITH_WRONG_EXTENSION);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, e.type);
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectDelimiter_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CODE_CSV_FILE_WITH_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCodeCSVFile_WhenIncorrectHeader_ShouldReturnCustomException() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CODE_CSV_FILE_WITH_WRONG_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenSortedOnStateAlphabetically_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH);
            sortedCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH);
            String sortedCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensus[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensuses[0].population);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH);
            String sortedCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CensusDAO[] csvStateCensus = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, csvStateCensus[0].density);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        try {
            indianCensusAnalyser.loadStateCensusCSVData(INDIA, STATE_CENSUS_DATA_PATH);
            String sortedCensusData = indianCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CensusDAO[] censusDAOS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, censusDAOS[0].area, 0);
        } catch (StateCensusAnalyserException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecords = usCensusAnalyser.loadStateCensusCSVData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenTheUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            int numberOfRecords = usCensusAnalyser.loadStateCensusCSVData(StateCensusAnalyser.Country.US, US_CENSUS_DATA_CSV_FILE_PATH);
            String sortedCensusData = usCensusAnalyser.getPopulationWiseUSSortedCensusData();
            CensusDAO[] censusDAOS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusDAOS[0].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}