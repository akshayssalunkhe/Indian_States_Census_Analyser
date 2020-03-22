package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

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
        String extension = getFileExtension(filePath);
        //CHECKING FILE EXTENSION
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<CSVStateCensus> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStateCensus.class);
            return this.getNumberOfRecords(censusCSVIterator);
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        }
    }

    //METHOD TO LOAD CSV STATE CODE FILE
    public int loadStatesCodeCSVData(String filePath) throws IOException, StateCensusAnalyserException {
        String extension = getFileExtension(filePath);
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<CSVStates> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStates.class);
            return this.getNumberOfRecords(censusCSVIterator);
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        }
    }

    //METHOD TO GET EXTENSION OF FILE
    public String getFileExtension(String filePath) throws StateCensusAnalyserException {
        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0)
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        else return "";
    }

    //GENERIC METHOD TO COUNT NUMBER OF RECORDS
    private <E> int getNumberOfRecords(Iterator<E> iterator) {
        int numberOfRecords = 0;
        //LOOP TO ITERATE THROUGH FILE
        while (iterator.hasNext()) {
            numberOfRecords++;
            iterator.next();
        }
        return numberOfRecords;
    }
}
