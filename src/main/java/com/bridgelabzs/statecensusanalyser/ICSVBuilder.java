package com.bridgelabzs.statecensusanalyser;

import com.bridgelabzs.statecensusanalyserexception.StateCensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvLoaderClass) throws StateCensusAnalyserException;
}

