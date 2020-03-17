package com.bridgelabz.statecensusanalysertest;

import com.bridgelabzs.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    //GIVING FILE PATH
    private static final String STATE_CENSUS_DATA_PATH = "src/test/resources/StateCensusData.csv";

    //CREATING OBJECT
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
        try {
            int numberOfRecord = stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}