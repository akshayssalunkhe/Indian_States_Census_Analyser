package com.bridgelabzs.statecensusanalyser;

public class CSVBuilderException extends Exception {
    //ENUM
    enum ExceptionType {
        DELIMITER_OR_HEADER_INCORRECT
    }

    //OBJECT
    ExceptionType type;

    //CONSTRUCTOR
    CSVBuilderException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}