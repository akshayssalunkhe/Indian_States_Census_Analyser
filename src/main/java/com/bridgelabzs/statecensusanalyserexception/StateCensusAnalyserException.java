package com.bridgelabzs.statecensusanalyserexception;

public class StateCensusAnalyserException extends Exception {
    //CREATING OBJECT
    public ExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    //CREATING ENUM
    public enum ExceptionType {
        NO_SUCH_FILE_FOUND, NO_SUCH_FILE_EXTENSION, WRONG_DELIMITER_OR_HEADER;
    }
}