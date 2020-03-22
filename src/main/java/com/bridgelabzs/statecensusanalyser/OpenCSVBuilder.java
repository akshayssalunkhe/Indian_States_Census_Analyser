package com.bridgelabzs.statecensusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class OpenCSVBuilder implements ICSVBuilder {
    @Override
    public <E> List getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBean csvToBeanBuilder = new CsvToBeanBuilder(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBeanBuilder.parse();
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.DELIMITER_OR_HEADER_INCORRECT, "DELIMITER_OR_HEADER_INCORRECT_ERROR_ BUILDING_CSV");
        }
    }
}