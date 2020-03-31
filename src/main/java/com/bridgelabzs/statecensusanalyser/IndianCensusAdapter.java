package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndianCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = super.loadCensusData(CSVStateCensus.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDAOMap;
        return loadStateCodeCSVData(censusDAOMap, csvFilePath[1]);
    }

    private Map<String, CensusDAO> loadStateCodeCSVData(Map<String, CensusDAO> censusDAOMap, String csvFilePath) throws StateCensusAnalyserException {
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> stateCodeIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class);
            Iterable<CSVStates> stateCodes = () -> stateCodeIterator;
            StreamSupport.stream(stateCodes.spliterator(), false)
                    .filter(csvStateCode -> censusDAOMap.get(csvStateCode.stateName) != null)
                    .forEach(csvStateCode -> censusDAOMap.get(csvStateCode.stateName).stateCode = csvStateCode.stateCode);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, "WRONG_DELIMITER_OR_HEADER");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        } catch (IOException e) {
            e.getStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return censusDAOMap;
    }
}