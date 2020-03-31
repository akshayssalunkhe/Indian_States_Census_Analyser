package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

import java.util.Map;

public class CensusAdapterFactory {
    public static Map<String, CensusDAO> getCensusData(StateCensusAnalyser.Country country, String[] csvFilePath) throws StateCensusAnalyserException {
        if (country.equals(StateCensusAnalyser.Country.INDIA))
            return new IndianCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(StateCensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INVALID_COUNTRY, "INVALID_COUNTRY");
    }
}