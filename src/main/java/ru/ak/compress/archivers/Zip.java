package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Zip extends ApacheCommonCompressArchive {
    
    public Zip(String fileName) {
        super(ArchiveStreamFactory.ZIP, fileName);
    }      
}