package com.bridgelabzs.statecensusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    public <E> List getCSVFileList(Reader reader, Class<E> csvLoaderClass) throws CSVBuilderException;

    public <E> Iterator getCSVFileIterator(Reader reader, Class<E> csvStateCensusClass) throws CSVBuilderException;
}
