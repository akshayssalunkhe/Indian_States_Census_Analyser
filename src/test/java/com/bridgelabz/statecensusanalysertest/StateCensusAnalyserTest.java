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
}