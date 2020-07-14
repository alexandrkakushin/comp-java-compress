package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Arj extends ApacheCommonCompressArchive {

    public Arj(String fileName) {
        super(ArchiveStreamFactory.ARJ, fileName);
    }    
}