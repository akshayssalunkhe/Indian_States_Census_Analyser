package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

    //METHOD TO LOAD CSV FILE
    public int loadStateCSVData(String filePath) throws IOException, StateCensusAnalyserException {
        int numberOfRecords = 0;
        String extension = getFileExtension(filePath);
        //CHECKING FILE EXTENSION
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();
            //LOOP TO ITERATE THROUGH FILE
            while (csvStateCensusIterator.hasNext()) {
                csvStateCensusIterator.next();
                numberOfRecords++;
            }
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER, "WRONG_DELIMITER_OR_HEADER");
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        }
        return numberOfRecords;
    }

    //METHOD TO GET EXTENSION OF FILE
    public String getFileExtension(String filePath) throws StateCensusAnalyserException {
        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0) {
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        } else return "";
    }

    //METHOD TO LOAD CSV STATE CODE FILE
    public int loadStatesCodeCSVData(String filePath) throws IOException {
        int numberOfRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
            CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStates.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStates> csvStatesIterator = csvToBean.iterator();
            //LOOP TO ITERATE THROUGH FILE
            while (csvStatesIterator.hasNext()) {
                csvStatesIterator.next();
                numberOfRecords++;
            }
        }
        return numberOfRecords;
    }
}