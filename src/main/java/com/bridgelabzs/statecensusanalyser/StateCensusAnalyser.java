package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    public static void main(String[] args) {
        System.out.println("Welcome To Indian States Census Analyser Problem");
    }

    public int loadStateCSVData(String filePath) throws IOException {
        int numberOfRecords = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStateCensus> csvStateCensusIterator = csvToBean.iterator();
            //LOOP TO ITERATE THROUGH FILE
            while (csvStateCensusIterator.hasNext()) {
                csvStateCensusIterator.next();
                numberOfRecords++;
            }
        }
        return numberOfRecords;
    }
}