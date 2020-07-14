package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Ar extends ApacheCommonCompressArchive {
    
    public Ar(String fileName) {
        super(ArchiveStreamFactory.AR, fileName);
    }      
}