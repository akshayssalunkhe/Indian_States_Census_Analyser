package com.bridgelabzs.statecensusanalyserexception;

public class StateCensusAnalyserException extends Exception {
    //CREATING ENUM
    public enum ExceptionType {
        NO_SUCH_FILE_FOUND;
    }

    //CREATING OBJECT
    public ExceptionType type;

    //CONSTRUCTOR
    public StateCensusAnalyserException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}