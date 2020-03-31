package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusAnalyserException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String csvFilePath) throws StateCensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = new HashMap<>();
        String extension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_FILE_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> stateCensusIterator = icsvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> stateCensuses = () -> stateCensusIterator;
            if (censusCSVClass.getName().equals("com.bridgelabzs.statecensusanalyser.CSVStateCensus")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(CSVStateCensus.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().equals("com.bridgelabzs.statecensusanalyser.CSVUSCensus")) {
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .map(CSVUSCensus.class::cast)
                        .forEach(censusCSV -> censusDAOMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, "WRONG_DELIMITER_OR_HEADER");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "No such file");
        } catch (IOException e) {
            e.getStackTrace();
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return censusDAOMap;
    }
}