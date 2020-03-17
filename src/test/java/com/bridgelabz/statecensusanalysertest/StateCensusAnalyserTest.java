package com.bridgelabz.statecensusanalysertest;

import com.bridgelabzs.statecensusanalyser.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA_PATH = "StateCensusData.csv";
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ShouldReturnTrue() {
      int numberOfRecord = stateCensusAnalyser.loadStateCSVData(STATE_CENSUS_DATA_PATH);
      Assert.assertEquals(30,numberOfRecord);
    }
}
//public class CensusAnalyserProblemTest {
//    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
//    @Test
//    public void name() {
//        CensusAnalyserProblem censusAnalyserProblem=new CensusAnalyserProblem();
//        int numberOfRecord = censusAnalyserProblem.recordCheck(STATE_CENSUS_DATA_PATH);
//        Assert.assertEquals(30,numberOfRecord);
//    }