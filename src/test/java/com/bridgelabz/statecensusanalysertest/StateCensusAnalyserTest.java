package com.bridgelabz.statecensusanalysertest;

import com.bridgelabzs.statecensusanalyser.StateCensusAnalyser;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    //GIVING FILE PATH
    private static final String STATE_CENSUS_DATA_PATH = "src/test/resources/StateCensusData.csv";

    //GIVING WRONG FILE PATH
    private static final String STATE_CENSUS_DATA_WRONG_PATH = "src/test/resources/StateCensusDa.csv";

    //GIVING WRONG FILE EXTENSION
    private static final String FILE_WITH_WRONG_EXTENSION = "src/test/resources/StateCensusDa.txt";

    //GIVING WRONG FILE DELIMITER
    private static final String FILE_WITH_INCORRECT_DELIMITER = "src/test/resources/StateCensusDataIncorrectDelimiter.csv";

    //CREATING OBJECT
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (IOException | StateCensusAnalyserException e) {
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
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenIncorrectDelimiter_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.loadStateCSVData(FILE_WITH_INCORRECT_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER, e.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}