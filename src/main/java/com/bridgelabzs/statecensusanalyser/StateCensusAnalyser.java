package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.csvstates.CSVStates;
import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyser {
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

    //METHOD TO LOAD CSV FILE
    public int loadStateCSVData(String filePath) throws IOException, StateCensusAnalyserException, CSVBuilderException {
        String extension = getFileExtension(filePath);
        //CHECKING FILE EXTENSION
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder icsvBuilder = new OpenCSVBuilder();
            List csvList = icsvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return csvList.size();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "DELIMITER_OR_HEADER_INCORRECT_ERROR_ BUILDING_CSV");
        }
    }

    //METHOD TO LOAD CSV STATE CODE FILE
    public int loadStatesCodeCSVData(String filePath) throws IOException, StateCensusAnalyserException, CSVBuilderException {
        String extension = getFileExtension(filePath);
        if (!extension.equals("csv"))
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_EXTENSION, "NO_SUCH_EXTENSION");
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder icsvBuilder = new OpenCSVBuilder();
            List csvList = icsvBuilder.getCSVFileList(reader, CSVStates.class);
            return csvList.size();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE_FOUND, "NO_SUCH_FILE_FOUND");
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "DELIMITER_OR_HEADER_INCORRECT_ERROR_ BUILDING_CSV");
        }
    }

    //METHOD TO GET EXTENSION OF FILE
    public String getFileExtension(String filePath) {
        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0)
            return filePath.substring(filePath.lastIndexOf(".") + 1);
        else return "";
    }
}