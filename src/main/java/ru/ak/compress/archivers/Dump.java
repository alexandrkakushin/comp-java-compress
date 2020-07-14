package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Dump extends ApacheCommonCompressArchive {
    
    public Dump(String fileName) {
        super(ArchiveStreamFactory.DUMP, fileName);
    }      
}