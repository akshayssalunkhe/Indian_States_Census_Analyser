package com.bridgelabzs.statecensusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> List <E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    <E> Iterator <E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}