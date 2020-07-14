package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Jar extends ApacheCommonCompressArchive {
    
    public Jar(String fileName) {
        super(ArchiveStreamFactory.JAR, fileName);
    }      
}